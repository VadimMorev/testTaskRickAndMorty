package com.example.demo;

import com.example.demo.jsonResponse.Response;
import com.example.demo.repo.CharacterRepository;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;

@SpringBootApplication
@Getter
@Setter
@ToString
@Slf4j
public class Demo1Application {
    @Autowired
    private CharacterRepository characterRepository;

    public static void main(String[] args) throws IOException {
        SpringApplication.run(Demo1Application.class, args);
//        EntityAssistant entityAssistant = new EntityAssistant();
//        Timer t = new Timer();
//        // This task is scheduled to run every 10 seconds
//
//        t.scheduleAtFixedRate(entityAssistant, 0, 10000);
    }

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        return builder.build();
    }

    @Bean
    public CommandLineRunner run(RestTemplate restTemplate) throws Exception {
        return args -> {
            Response response = restTemplate.getForObject(
                    "https://rickandmortyapi.com/api/character/", Response.class
            );

            boolean firstStepExecuted = false;
            while (response.getInfo().getNext() != null) {
                if (firstStepExecuted) {
                    response = restTemplate.getForObject(
                            response.getInfo().getNext(), Response.class
                    );
                }
                log.info(response.toString());
                for (int i = 0; i < response.getResults().size(); i++) {
                    if (!characterRepository.existsByName(response.getResults().get(i).getName())) {
                        characterRepository.save(response.getResults().get(i));
                    }
                }
                firstStepExecuted = true;
            }
        };
    }
}
