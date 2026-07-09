package org.example.tasktrackerscheduler.configuration;

import org.example.tasktrackerscheduler.InternalApiClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.support.RestClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

@Configuration
public class ClientConfiguration {

    @Value("${backend.url}")
    private String url;

    @Bean
    public RestClient restClient() {
        return RestClient.builder().baseUrl(url).build();
    }

    @Bean
    public RestClientAdapter restClientAdapter() {
        return RestClientAdapter.create(restClient());
    }

    @Bean
    public HttpServiceProxyFactory proxyFactory() {
        return HttpServiceProxyFactory.builder().exchangeAdapter(restClientAdapter()).build();
    }

    @Bean
    public InternalApiClient internalApiClient() {
        return proxyFactory().createClient(InternalApiClient.class);
    }
}