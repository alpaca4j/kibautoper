package io.badura.kibautoper.configuration;


import io.netty.handler.ssl.SslContext;
import io.netty.handler.ssl.SslContextBuilder;
import io.netty.handler.ssl.util.InsecureTrustManagerFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.reactive.function.client.ExchangeFilterFunction;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import reactor.netty.http.client.HttpClient;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLException;

import java.security.cert.X509Certificate;
import java.util.Properties;

@Configuration
@Slf4j
public class Config {

    @Value("${ELASTIC_USERNAME}")
    private String username;
    @Value("${ELASTIC_PASSWORD}")
    private String password;

    @Value("${ELASTIC_URL:https://localhost:9200}")
    private String elasticUrl;
    @Value("${KIBANA_URL:https://localhost:5601}")
    private String kibanaUrl;

    @Bean(name = "urls")
    public Properties elkUrls(){
        Properties properties = new Properties();
        properties.put("kibanaUrl",kibanaUrl);
        properties.put("elasticUrl", elasticUrl);
        return properties;
    }

    @Bean
    public WebClient client() throws SSLException {
        SslContext context = SslContextBuilder.forClient()
                .trustManager(InsecureTrustManagerFactory.INSTANCE)
                .build();

        HttpClient httpClient = HttpClient.create().secure(t -> t.sslContext(context));

        WebClient wc = WebClient
                .builder()
                .defaultHeaders(header -> header.setBasicAuth(username, password))
                .filter(logRequest())
                .clientConnector(new ReactorClientHttpConnector(httpClient)).build();
        return wc;
    }

    private static ExchangeFilterFunction logRequest() {
        return ExchangeFilterFunction.ofRequestProcessor(clientRequest -> {
            log.info("Request debug: {} {} {}", clientRequest.method(), clientRequest.url(), clientRequest.body().toString());
//            clientRequest.headers().forEach((name, values) -> values.forEach(value -> log.info("{}={}", name, value)));
            return Mono.just(clientRequest);
        });
    }
}
