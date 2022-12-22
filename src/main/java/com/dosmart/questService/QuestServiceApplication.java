package com.dosmart.questService;

import com.dosmart.questService.utils.TokenValidator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class QuestServiceApplication {
	public static void main(String[] args) {
		SpringApplication.run(QuestServiceApplication.class, args);
	}

	@Bean
	public TokenValidator getTokenValidator(){
		return TokenValidator.getInstance();
	}
	@Bean
	public RestTemplate getRestTemplate(){
		return new RestTemplate();
	}

}
