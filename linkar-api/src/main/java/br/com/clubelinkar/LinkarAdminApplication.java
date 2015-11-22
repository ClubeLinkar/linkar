package br.com.clubelinkar;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.WebMvcAutoConfiguration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication
@EnableWebMvc
public class LinkarAdminApplication extends WebMvcAutoConfiguration {

    public static void main(String[] args) {
        SpringApplication.run(LinkarAdminApplication.class, args);
    }
}
