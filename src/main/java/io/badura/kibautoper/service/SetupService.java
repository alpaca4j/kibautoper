package io.badura.kibautoper.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import io.badura.kibautoper.model.IndexPatternSearch;
import io.badura.kibautoper.model.IndiciesCat;
import io.badura.kibautoper.model.SavedObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.logging.Level;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Service
@Slf4j
public class SetupService {

    @Autowired
    ElkService elkService;

    String indexRegExPattern = "^(.*)(-+\\d\\d\\d\\d.\\d\\d.\\d\\d)$";
    Pattern pattern = Pattern.compile(indexRegExPattern);

    //TODO literówka
    public void rereshIndexPattern() {
        final Flux<String> indexPatterns = this.getIndexPatterns();
        indexPatterns.filter(p -> p.endsWith("$"))
                .doOnNext(s -> log.info("Refreshing pattern: " + s))
                .subscribe(elkService::initPatternRefresh);
    }

    private Flux<String> getIndexPatterns(){
        log.info("Starting search for patterns in Kibana");
        Flux<String> indexPatterns = elkService.getIndexPatterns()
                .flatMapIterable(IndexPatternSearch::getSavedObjects)
                .map(preparePatternName);

        indexPatterns.subscribe(p->{
            log.info("Found pattern: {}", p);
        });
        return indexPatterns;
    }


    public void setupIndexPatternsAndTemplates() {
        final Flux<String> indexPatterns = this.getIndexPatterns();
        log.info("Starting search for indicies in Elasticsearch");
        elkService.getIndexes()
                .map(IndiciesCat::getIndex)
                .doOnNext(i -> log.info("Found index {}",i))
                .map(prepareIndexName)
                .filterWhen(t -> indexPatterns.hasElement(t).map(b->!b)) //Validate if pattern already exists, if not - continue // TODO Zrobić z tego predykat
                .filter(isPatternNeeded())
                .distinct()
                .doOnNext(i -> log.info("Index for pattern creation selected {}", i))
                .subscribe(this::creatingIngexPattern);
    }

    /**
     * Gets the pattern name without the * character if exists
     */
    Function<SavedObject, String> preparePatternName = savedObject -> {
        String patternName= savedObject.getAttributes().getTitle();
        //TODO wywalić do funkcji
        if (!patternName.endsWith("*")) {
            return patternName;
        } else {
            return patternName.substring(0, patternName.length()-1);
        }
    };

    Function<String, String> prepareIndexName = indexName -> {
        //For optimization - if doesn't contain "-" don't do regex
        if (!indexName.contains("-"))
            return indexName;

        Matcher matcher = pattern.matcher(indexName);
        if (matcher.matches())
            return matcher.group(1);

        return indexName;
    };


    //TODO zmiana nazwy
    Predicate<String> isPatternNeeded() {
        return p ->
                !p.startsWith(".") && // ommit system indicies
                p.endsWith("$"); // include only automatically created indeces
    }

    //TODO wyciągnąć index template do metody wyżej, nie uzależniać tworzenia templatu od stworzonego patternu!
    private void creatingIngexPattern(String patternName) {
        elkService.createIndexPattern(patternName);
        elkService.createIndexTemplate(patternName);
    }



}
