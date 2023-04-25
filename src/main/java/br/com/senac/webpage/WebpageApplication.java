package br.com.senac.webpage;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication (exclude = {DataSourceAutoConfiguration.class})
public class WebpageApplication {

	public static void main(String[] args) {
		SpringApplication.run(WebpageApplication.class, args);
	}

}
