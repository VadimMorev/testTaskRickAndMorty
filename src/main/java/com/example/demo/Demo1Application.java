package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;
import java.util.Timer;

@SpringBootApplication
public class Demo1Application {

    public static void main(String[] args) throws IOException {
        SpringApplication.run(Demo1Application.class, args);
        EntityAssistant entityAssistant = new EntityAssistant();
        Timer t = new Timer();
        // This task is scheduled to run every 10 seconds

        t.scheduleAtFixedRate(entityAssistant, 0, 10000);
    }
}
