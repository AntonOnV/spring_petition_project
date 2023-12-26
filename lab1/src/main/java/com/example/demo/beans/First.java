package com.example.demo.beans;

import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(Integer.MIN_VALUE)
public class First implements CommandLineRunner {

    @Override
    public void run(String... args) throws Exception {
        System.out.println("First");
    }
}
