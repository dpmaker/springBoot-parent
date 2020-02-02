package boot.jdbc.jpa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/*
@Configuration
@ComponentScan(basePackages = {"boot"})
@EnableAutoConfiguration
@SpringBootApplication
*/
public class JpaSpringBoot {

	public static void main(String[] args){
		SpringApplication.run(JpaBookContorller.class, args);

	}
}
