package com.docencia.com.proc_cli;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.docencia.com.proc_cli.controllers.CliController;

@SpringBootApplication
public class ProcCliApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProcCliApplication.class, args);
	}

	@Bean
	CommandLineRunner demo(CliController controller) {
		return args -> {
			controller.consoleMenu();
		};
	}
}
