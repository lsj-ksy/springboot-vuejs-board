package com.sjsy.springvue;

import com.sjsy.springvue.config.properties.AppProperties;
import com.sjsy.springvue.config.properties.CorsProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties({
        AppProperties.class,
        CorsProperties.class
})
public class SpringVueApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringVueApplication.class, args);
    }

}
