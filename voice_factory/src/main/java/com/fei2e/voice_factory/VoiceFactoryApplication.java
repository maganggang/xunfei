package com.fei2e.voice_factory;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
public class VoiceFactoryApplication {

    public static void main(String[] args) {
        SpringApplication.run(VoiceFactoryApplication.class, args);
    }

}
