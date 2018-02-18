package org.grpcsvrest.rcs;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {

//    @Bean
//    public SpanAdjuster customSpanAdjuster(@Value("${spring.application.name}") String appName) {
//        return span -> span.toBuilder().name("#" + appName + "/" + span.getName().replace("http:/", "")).build();
//    }

    public static void main(String... args) {
        SpringApplication.run(Application.class, args);
    }
}
