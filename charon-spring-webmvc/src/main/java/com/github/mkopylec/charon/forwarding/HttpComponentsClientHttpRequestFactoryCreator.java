package com.github.mkopylec.charon.forwarding;

import org.apache.hc.client5.http.classic.HttpClient;
import org.apache.hc.client5.http.impl.classic.HttpClientBuilder;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;

class HttpComponentsClientHttpRequestFactoryCreator implements ClientHttpRequestFactoryCreator {

    private HttpClient httpClient;

    HttpComponentsClientHttpRequestFactoryCreator() {
        httpClient = HttpClientBuilder.create()
                .disableRedirectHandling()
                .build();
    }

    @Override
    public ClientHttpRequestFactory createRequestFactory(TimeoutConfiguration configuration) {
        HttpComponentsClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory(httpClient);
        requestFactory.setConnectionRequestTimeout(configuration.getConnection());
        requestFactory.setReadTimeout(configuration.getRead());
        return requestFactory;
    }

    void setHttpClient(HttpClient httpClient) {
        this.httpClient = httpClient;
    }
}
