package com.incarcloud.match;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 启动类
 *
 * Created by bzheng on 2019/11/28.
 */
@SpringBootApplication(scanBasePackages = {
        "com.incarcloud.mvc",
        "com.incarcloud.match"
})
public class App {

    public static void main(String[] args) {
        SpringApplication.run(App.class,args);
    }
}
