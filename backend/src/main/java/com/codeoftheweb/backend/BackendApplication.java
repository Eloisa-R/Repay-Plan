package com.codeoftheweb.backend;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;

import java.util.Date;
import java.util.List;

@SpringBootApplication
public class BackendApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(BackendApplication.class, args);
	}

}
