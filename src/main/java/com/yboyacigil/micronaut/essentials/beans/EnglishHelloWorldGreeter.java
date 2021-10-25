package com.yboyacigil.micronaut.essentials.beans;

import io.micronaut.core.annotation.NonNull;
import jakarta.inject.Named;
import jakarta.inject.Singleton;
import lombok.extern.slf4j.Slf4j;

import javax.annotation.PostConstruct;
import java.util.Objects;

@Singleton
@Named("en")
@Slf4j
public class EnglishHelloWorldGreeter implements Greeter {
    @Override
    public String greet() {
        return greetName("world");
    }

    @Override
    public String greetName(@NonNull  String name) {
        Objects.requireNonNull(name, "Name cannot be null");
        return String.format("Hello, %s!", name);
    }

    @PostConstruct
    public void initialized() {
        log.info("{} initialized", getClass().getSimpleName());
    }
}
