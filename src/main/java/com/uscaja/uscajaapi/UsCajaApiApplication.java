package com.uscaja.uscajaapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController("/")
public class UsCajaApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(UsCajaApiApplication.class, args);
	}

	@GetMapping("/")
	public String apiForwarding() {
		return "<h1>The API is located at '/api'</h1>";
	}

	@GetMapping("/api")
	public String apiWelcome() {
		return """
				<div>
					<h1>This is US-Caja API</h1>
					<p>Use the known routes to perform the desired operations</p>
				</div>
				""";
	}
}
