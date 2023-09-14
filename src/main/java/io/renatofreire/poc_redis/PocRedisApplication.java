package io.renatofreire.poc_redis;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class PocRedisApplication {

    public static void main(String[] args) {
        SpringApplication.run(PocRedisApplication.class, args);
    }

}
