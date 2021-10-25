package com.yboyacigil.micronaut.essentials.beans;

import jakarta.inject.Named;
import jakarta.inject.Qualifier;
import jakarta.inject.Singleton;
import lombok.extern.slf4j.Slf4j;

import javax.annotation.PostConstruct;
import java.util.Objects;

@Singleton
@Named("tr")
@Slf4j
public class TurkishHelloWorldGreeter implements Greeter {
    @Override
    public String greet() {
        return greetName("dünya");
    }

    @Override
    public String greetName(String name) {
        Objects.requireNonNull(name, "Name cannot be null");
        return String.format("Merhaba, %s!", name);
    }

    @PostConstruct
    public void initialized() {
        log.info("{} initialized", getClass().getSimpleName());
    }
}
