package com.github.mkopylec.charon.test;

import com.github.mkopylec.charon.forwarding.interceptors.HttpResponse;
import io.github.resilience4j.circuitbreaker.CallNotPermittedException;

import java.util.function.Function;

import static org.springframework.http.HttpStatus.CREATED;

class CircuitBreakerFallback implements Function<CallNotPermittedException, HttpResponse> {

    @Override
    public HttpResponse apply(CallNotPermittedException e) {
        return new HttpResponse(CREATED);
    }
}
