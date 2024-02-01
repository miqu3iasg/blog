package com.project.msblog;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.project.msblog")
@EntityScan("com.project.msblog.models")
public class MsBlogApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsBlogApplication.class, args);
	}

}
