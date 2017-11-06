package org.grpcsvrest.rcs;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.sleuth.SpanAdjuster;
import org.springframework.cloud.sleuth.sampler.AlwaysSampler;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Application {

    @Bean
    public SpanAdjuster customSpanAdjuster(@Value("${spring.application.name}") String appName) {
        return span -> span.toBuilder().name("#" + appName + "/" + span.getName().replace("http:/", "")).build();
    }

    public static void main(String... args) {
        SpringApplication.run(Application.class, args);
    }
}
