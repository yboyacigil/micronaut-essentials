package com.yboyacigil.micronaut.essentials.services;

import com.yboyacigil.micronaut.essentials.beans.Greeter;
import io.micronaut.core.util.StringUtils;
import jakarta.inject.Named;
import jakarta.inject.Singleton;
import lombok.extern.slf4j.Slf4j;

import javax.annotation.PostConstruct;

@Singleton
@Slf4j
public class GreetingService {

    private final Greeter englishGreeter;

    private final Greeter turkishGreeter;

    public GreetingService(@Named("en") Greeter englishGreeter, @Named("tr") Greeter turkishGreeter) {
        this.englishGreeter = englishGreeter;
        this.turkishGreeter = turkishGreeter;
    }

    public String doGreeting(String language, String name) {
        return switch (language) {
            case "en" -> StringUtils.isEmpty(name) ? englishGreeter.greet() : englishGreeter.greetName(name);
            case "tr" -> StringUtils.isEmpty(name) ? turkishGreeter.greet() : turkishGreeter.greetName(name);
            default -> throw new IllegalArgumentException("Unknown language: " + language);
        };
    }

    @PostConstruct
    public void initialized() {
        log.info("GreeterService initialized with {} and {}",
            englishGreeter.getClass().getSimpleName(),
            turkishGreeter.getClass().getSimpleName());
    }

}
