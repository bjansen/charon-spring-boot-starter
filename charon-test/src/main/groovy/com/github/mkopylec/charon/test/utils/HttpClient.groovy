package com.github.mkopylec.charon.test.utils

import org.apache.hc.client5.http.impl.classic.HttpClientBuilder
import org.springframework.boot.resttestclient.TestRestTemplate
import org.springframework.http.HttpEntity
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpMethod
import org.springframework.http.ResponseEntity
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory
import org.springframework.stereotype.Component

@Component
class HttpClient {

    private TestRestTemplate restTemplate

    protected HttpClient(TestRestTemplate restTemplate) {
        this.restTemplate = restTemplate
        setup()
    }

    ResponseEntity<String> sendRequest(HttpMethod method, String path) {
        return sendRequest(method, path, [:], null)
    }

    ResponseEntity<String> sendRequest(HttpMethod method, String path, String body) {
        return sendRequest(method, path, [:], body)
    }

    ResponseEntity<String> sendRequest(HttpMethod method, String path, Map<String, String> headers) {
        return sendRequest(method, path, headers, null)
    }

    ResponseEntity<String> sendRequest(HttpMethod method, String path, Map<String, String> headers, String body) {
        def httpHeaders = new HttpHeaders()
        headers.each { name, value -> httpHeaders.put(name, value.split(', ') as List<String>) }
        def request = new HttpEntity<>(body, httpHeaders)
        return restTemplate.exchange(path, method, request, String)
    }

    private void setup() {
        def client = HttpClientBuilder.create()
                .disableRedirectHandling()
                .build()
        def requestFactory = new HttpComponentsClientHttpRequestFactory(client)
        requestFactory.connectionRequestTimeout = 100
        requestFactory.readTimeout = 100000
        restTemplate.restTemplate.requestFactory = requestFactory
    }
}
