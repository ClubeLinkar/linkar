package br.com.clubelinkar;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.groovy.template.GroovyTemplateAutoConfiguration;
import org.springframework.boot.autoconfigure.jmx.JmxAutoConfiguration;
import org.springframework.boot.autoconfigure.web.ErrorMvcAutoConfiguration;
import org.springframework.boot.autoconfigure.web.WebMvcAutoConfiguration;
import org.springframework.boot.autoconfigure.websocket.WebSocketAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication
@EnableWebMvc
@ComponentScan(basePackages = {
        "br.com.clubelinkar.api",
        "br.com.clubelinkar.config",
        "br.com.clubelinkar.support",
        "br.com.clubelinkar.controller", // FIXME
        "br.com.clubelinkar.exception" // FIXME
})
@Configuration
//@EnableWebSecurity
//@EnableGlobalMethodSecurity(securedEnabled = true)
//@EnableTransactionManagement
//@EnableAspectJAutoProxy
//@EnableCaching
@EnableAutoConfiguration(exclude = {ErrorMvcAutoConfiguration.class,
        WebSocketAutoConfiguration.class, JmxAutoConfiguration.class, GroovyTemplateAutoConfiguration.class})




public class LinkarApplication extends WebMvcAutoConfiguration {

    public static void main(String[] args) {
        SpringApplication.run(LinkarApplication.class, args);
    }
}
