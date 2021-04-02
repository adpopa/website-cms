package com.ap00921.website;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class WebsiteCmsApplication {

	public static void main(String[] args) {
		SpringApplication application = new SpringApplication(WebsiteCmsApplication.class);
		application.setAdditionalProfiles("ssl");
		application.run(args);
	}

}

