package com.example.programming_project;

import com.example.programming_project.presentation.console.View;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class StartApplication {
    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(StartApplication.class, args);
//        View view = context.getBean(View.class);
//        view.show();
//        context.close();
    }
}
