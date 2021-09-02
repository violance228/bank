package com.bank.bank;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;
import lombok.AllArgsConstructor;
import org.flywaydb.core.Flyway;
import org.springdoc.core.GroupedOpenApi;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.sql.DataSource;
import java.util.Arrays;
import java.util.List;

@AllArgsConstructor
@SpringBootApplication
public class BankApplication implements CommandLineRunner {
	private final DataSource dataSource;

	public static void main(String[] args) {
		SpringApplication.run(BankApplication.class, args);
	}

	@Override
	public void run(String... args) {
		Flyway.configure().baselineOnMigrate(true).dataSource(dataSource).load().migrate();
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public GroupedOpenApi publicUserApi() {
		return GroupedOpenApi.builder()
				.group("Cards")
				.pathsToMatch("/card/**")
				.build();
	}

	@Bean
	public OpenAPI customOpenApi() {
		return new OpenAPI().info(new Info().title("Application API")
				.version("appVersion")
				.description("appDescription")
				.license(new License().name("Apache 2.0")
						.url("http://springdoc.org"))
				.contact(new Contact().name("Svyatoslav")
						.email("test@gmail.com")))
				.servers(Arrays.asList(new Server().url("http://localhost:8080")
								.description("Dev service"),
						new Server().url("http://localhost:8082")
								.description("Beta service")));
	}
}
