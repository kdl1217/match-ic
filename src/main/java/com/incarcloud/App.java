package com.incarcloud;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Created by bzheng on 2019/11/28.
 */
@SpringBootApplication
public class App implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(App.class,args);
    }

    @Override
    public void run(String... args) {
        System.out.println("start run ...");
    }
}
