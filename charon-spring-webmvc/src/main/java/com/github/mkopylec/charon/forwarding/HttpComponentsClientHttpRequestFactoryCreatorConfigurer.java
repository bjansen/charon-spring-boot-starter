package com.github.mkopylec.charon.forwarding;


import org.apache.hc.client5.http.impl.classic.HttpClientBuilder;

public class HttpComponentsClientHttpRequestFactoryCreatorConfigurer extends ClientHttpRequestFactoryCreatorConfigurer<HttpComponentsClientHttpRequestFactoryCreator> {

    private HttpComponentsClientHttpRequestFactoryCreatorConfigurer() {
        super(new HttpComponentsClientHttpRequestFactoryCreator());
    }

    public static HttpComponentsClientHttpRequestFactoryCreatorConfigurer httpComponentsClientHttpRequestFactoryCreator() {
        return new HttpComponentsClientHttpRequestFactoryCreatorConfigurer();
    }

    public HttpComponentsClientHttpRequestFactoryCreatorConfigurer httpClient(HttpClientBuilder httpClient) {
        configuredObject.setHttpClient(httpClient.build());
        return this;
    }
}
