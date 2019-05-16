package edu.mum.tmcheck.configs;

import com.github.javafaker.Faker;
import com.github.javafaker.service.FakeValuesService;
import com.github.javafaker.service.RandomService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Locale;

@Configuration
public class FakerConfiguration {
    @Bean
    public Faker faker() {
        return new Faker();
    }

    @Bean
    public FakeValuesService fakeValuesService() {
        return new FakeValuesService(new Locale("en"), new RandomService());
    }
}
