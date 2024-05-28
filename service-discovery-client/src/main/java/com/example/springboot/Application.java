package com.example.springboot;

import com.example.springboot.util.ServiceDiscoveryUtil;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.web.reactive.function.client.WebClient;
@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		ApplicationContext app = SpringApplication.run(Application .class, args);//init the context
		ServiceDiscoveryUtil serviceDiscoveryUtil = app.getBean(ServiceDiscoveryUtil.class);//get the bean by type
		serviceDiscoveryUtil.updateServiceDiscovery("UP");
	}
	@Bean
	public WebClient getWebClient() {
		return WebClient.builder().build();
	}

}
