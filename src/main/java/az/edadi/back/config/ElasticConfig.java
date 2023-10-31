package az.edadi.back.config;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.json.jackson.JacksonJsonpMapper;
import co.elastic.clients.transport.ElasticsearchTransport;
import co.elastic.clients.transport.rest_client.RestClientTransport;
import co.elastic.clients.util.ContentType;
import org.apache.http.Header;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponseInterceptor;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.message.BasicHeader;
import org.elasticsearch.client.RestClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;
import org.springframework.http.HttpHeaders;

import java.util.List;

@Configuration
@EnableElasticsearchRepositories
public class ElasticConfig{

    @Bean
    public RestClient getRestClient() {
        final CredentialsProvider credentialsProvider = new BasicCredentialsProvider();
        credentialsProvider.setCredentials(AuthScope.ANY, new UsernamePasswordCredentials("", ""));
        return RestClient.builder(new HttpHost("34.68.213.40", 9200))
                .setHttpClientConfigCallback(httpClientBuilder -> {
                    httpClientBuilder.disableAuthCaching();
                    httpClientBuilder.setDefaultHeaders(List.of(
                            new BasicHeader(
                                    HttpHeaders.CONTENT_TYPE, ContentType.APPLICATION_JSON)));
                    httpClientBuilder.addInterceptorLast((HttpResponseInterceptor)
                            (response, context) ->
                                    response.addHeader("X-Elastic-Product", "Elasticsearch"));
                    return httpClientBuilder.setDefaultCredentialsProvider(credentialsProvider);

                }).build();
    }

    @Bean
    public ElasticsearchTransport getElasticsearchTransport() {
        return new RestClientTransport(getRestClient(), new JacksonJsonpMapper());
    }

    @Bean
    public ElasticsearchClient getElasticsearchClient() {
        return new ElasticsearchClient(getElasticsearchTransport());
    }
}
