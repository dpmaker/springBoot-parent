import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import boot.anno.MyAnnotation;
import boot.start.SectionMethodScan;
import boot.start.SectionScan;

/**
 * 
 */

/**
 * @author d_mail_p@sina.com
 *
 */

@ComponentScan("boot")
@EnableAutoConfiguration
@MapperScan({"boot.generator.test.dao","boot.jdbc.mybatis"})
@SpringBootApplication
public class KafkaApplication {

	public static void main(String[] args){
		SpringApplication.run(KafkaApplication.class, args);

	}
}
