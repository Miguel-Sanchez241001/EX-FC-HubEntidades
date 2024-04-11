package com.hubmultiservice.servicesentity;

import org.springdoc.core.GroupedOpenApi;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ServicesentityApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServicesentityApplication.class, args);
	}


		@Bean
	public GroupedOpenApi myApi() {
		return GroupedOpenApi.builder()
			.group("Multi Servicio")
			.pathsToMatch("/v1/**","/entidad/**" )
			.build();
	}
}
