package org.jr.waes;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan({ "org.jr.waes" })
@EnableAutoConfiguration
@SpringBootApplication
public class WaesRunner {

	public static void main(String[] args) throws Exception {
		SpringApplication.run(WaesRunner.class, args);
	}
}