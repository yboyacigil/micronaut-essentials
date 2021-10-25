package com.yboyacigil.micronaut.essentials.controllers;

import com.yboyacigil.micronaut.essentials.beans.Greeting;
import com.yboyacigil.micronaut.essentials.services.GreetingService;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.PathVariable;
import io.micronaut.http.annotation.QueryValue;
import lombok.val;

@Controller("/{language}/greeter")
public class GreetingController {

    private final GreetingService greetingService;

    public GreetingController(GreetingService greetingService) {
        this.greetingService = greetingService;
    }

    @Get()
    public HttpResponse<Greeting> greetUsingQueryValue(@PathVariable String language, @QueryValue("name") String name) {
        val greeting = greetingService.doGreeting(language, name);
        return HttpResponse.ok(Greeting.builder().greeting(greeting).build());
    }

    @Get("/{name}")
    public HttpResponse<Greeting> greetUsingPathVariable(@PathVariable String language, @PathVariable String name) {
        val greeting = greetingService.doGreeting(language, name);
        return HttpResponse.ok(Greeting.builder().greeting(greeting).build());
    }
}
