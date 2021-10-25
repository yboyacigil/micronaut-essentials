package com.yboyacigil.micronaut.essentials.controllers;

import com.yboyacigil.micronaut.essentials.beans.Greeting;
import com.yboyacigil.micronaut.essentials.services.GreetingService;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.client.HttpClient;
import io.micronaut.http.client.annotation.Client;
import io.micronaut.http.client.exceptions.HttpClientResponseException;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import jakarta.inject.Inject;
import lombok.val;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

@MicronautTest
class GreetingControllerTest {

    @Inject
    GreetingService greetingService;

    @Inject
    @Client("/")
    HttpClient client;

    @ParameterizedTest
    @CsvSource({"tr,Merhaba", "en,Hello"})
    void testGreetingUsingPathVariable(String language, String prefix) {
        val greeting = client.toBlocking().retrieve(
            HttpRequest.GET(String.format("/%s/greeter/John", language)),
            Greeting.class);

        assertEquals(
            String.format("%s, John!", prefix),
            greeting.getGreeting()
        );
    }

    @ParameterizedTest
    @CsvSource({"tr,Merhaba", "en,Hello"})
    void testGreetingUsingQueryValue(String language, String prefix) {
        val greeting = client.toBlocking().retrieve(
            HttpRequest.GET(String.format("/%s/greeter?name=John", language)),
            Greeting.class);

        assertEquals(
            String.format("%s, John!", prefix),
            greeting.getGreeting()
        );
    }

    @ParameterizedTest
    @CsvSource({"tr,Merhaba,dünya", "en,Hello,world"})
    void testGreetingUsingQueryValueWithEmptyName(String language, String prefix, String suffix) {
        val greeting = client.toBlocking().retrieve(
            HttpRequest.GET(String.format("/%s/greeter?name", language)),
            Greeting.class);

        assertEquals(
            String.format("%s, %s!", prefix, suffix),
            greeting.getGreeting()
        );
    }

    @Test
    void testUnknownLanguage() {
        try {
            client.toBlocking().retrieve(
                HttpRequest.GET("/de/greeter/John"),
                Greeting.class);
            fail("Should throw exception");
        } catch (HttpClientResponseException e) {
            assertEquals(
                HttpStatus.INTERNAL_SERVER_ERROR,
                e.getStatus()
            );
        }
    }
}