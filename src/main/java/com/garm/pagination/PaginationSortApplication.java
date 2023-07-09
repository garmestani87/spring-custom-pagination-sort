package com.garm.pagination;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.garm")
public class PaginationSortApplication {

    public static void main(String[] args) {
        SpringApplication.run(PaginationSortApplication.class, args);
    }

}
