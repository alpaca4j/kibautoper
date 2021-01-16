package io.badura.kibautoper.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.JsonpCharacterEscapes;
import com.fasterxml.jackson.core.io.JsonStringEncoder;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.badura.kibautoper.model.*;
import io.badura.kibautoper.model.pattern.AttributesRequest;
import io.badura.kibautoper.model.pattern.IndexPatternRequest;
import io.badura.kibautoper.model.template.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import java.io.UncheckedIOException;
import java.util.Properties;
import java.util.function.Function;

@Service
@Slf4j
public class ElkService {
    @Autowired
    WebClient webClient;

    @Autowired
    @Qualifier("urls")
    Properties urls;


    public Mono<IndexPatternSearch> getIndexPatterns() {
        return webClient.get()
                .uri(urls.get("kibanaUrl") + "/api/saved_objects/_find?fields=title&fields=type&type=index-pattern&per_page=10000")
                .retrieve()
                /*.onStatus(httpStatus -> HttpStatus.NOT_FOUND.equals(httpStatus),
                        clientResponse -> Mono.empty())*/
                .bodyToMono(IndexPatternSearch.class);
    }

    public Flux<IndiciesCat> getIndexes() {
        return webClient.get()
                .uri(urls.get("elasticUrl") +"/_cat/indices?format=json")
                .retrieve()
                /*.onStatus(httpStatus -> HttpStatus.NOT_FOUND.equals(httpStatus),
                        clientResponse -> Mono.empty())*/
                .bodyToFlux(IndiciesCat.class);

    }

    public void createIndexPattern(String name) {
        log.info("Creating index pattern: " + name);
        webClient.post()
                .uri(urls.get("kibanaUrl") +"/api/saved_objects/index-pattern/" + name)
                .body(Mono.just(new IndexPatternRequest(new AttributesRequest(name + "*"))), IndexPatternRequest.class)
                .headers(header -> header.setContentType(MediaType.APPLICATION_JSON))
                .headers(header -> header.set("kbn-xsrf", "true"))
                .retrieve()
                .bodyToMono(String.class)
                .subscribe(response -> log.info("Response from creating index pattern: " + response));

    }

    public void createIndexTemplate(String name) {
        log.info("Creating Index Template: " + name);
        webClient.put()
                .uri(urls.get("elasticUrl") +"/_index_template/" + name)
                .contentType(MediaType.APPLICATION_JSON)
                .headers(header -> header.set("kbn-xsrf", "true"))
                .body(Mono.just(new TemplateRequest(name)), TemplateRequest.class)
                .retrieve()
                .bodyToMono(String.class)
                .subscribe(response -> log.info("Response from creating index template" + response));
    }

    public void initPatternRefresh(String indexPattern) {
        final String indexPatternStar = indexPattern + "*";
        log.info("Lookup up fields for refresh for pattern: " + indexPatternStar);
        webClient.get()
                .uri(urls.get("kibanaUrl") +"/api/index_patterns/_fields_for_wildcard?pattern=" + indexPatternStar + "&meta_fields=_source&meta_fields=_id&meta_fields=_type&meta_fields=_index&meta_fields=_score")
                .retrieve()
                .bodyToMono(String.class)
                .map(prepareFields)
                .onErrorResume(e -> {
                    log.error(e.getMessage());
                    return Mono.empty();
                })
                .filter(s -> !s.isEmpty())
                .subscribe(fields -> refreshIndexPattern(indexPattern, fields));
    }

    private void refreshIndexPattern(String indexPattern, String fields) {
        log.info("Starting refresh: " + indexPattern);
        webClient.put()
                .uri(urls.get("kibanaUrl") +"/api/saved_objects/index-pattern/" + indexPattern)
                .headers(header -> header.set("kbn-xsrf", "true"))
                .contentType(MediaType.APPLICATION_JSON)
                .body(Mono.just(new IndexPatternRequest(new AttributesRequest(indexPattern + "*", fields))), IndexPatternRequest.class)
                .retrieve()
                .bodyToMono(String.class)
                .subscribe(resp -> log.info("Response from creation" + resp));
    }

    Function<String, String> prepareFields = response -> {
        ObjectMapper mapper = new ObjectMapper();
        try {
            JsonNode jsonNode = mapper.readTree(response);
            return jsonNode.get("fields").toString();
        } catch (JsonProcessingException e) {
            throw new UncheckedIOException(e);
        }
    };


}