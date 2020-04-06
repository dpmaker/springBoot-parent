//

import java.util.List;

import org.apache.activemq.command.ActiveMQQueue;
import org.apache.activemq.command.ActiveMQTopic;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import boot.anno.MyAnnotation;
import boot.common.UserBean;
import boot.jdbc.jdbc.UserController;
import boot.jdbc.jdbc.UserService;
import boot.jdbc.jpa.JpaBookContorller;
import boot.jdbc.mybatis.MybatisController;
import boot.jdbc.mybatis.MybatisUserService;
import boot.start.SectionMethodScan;
import boot.start.SectionScan;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@ComponentScan("boot")
@EnableAutoConfiguration
@MapperScan({"boot.generator.test.dao","boot.jdbc.mybatis","boot.redis"})
@SpringBootApplication
@MyAnnotation(value="main running..")


@SectionScan(value="boot.start")
@SectionMethodScan(value="boot.start.*")

//http://localhost:8020/mybatis/getUserByName/1

//@EnableSwagger2
@EnableCaching
public class SpringBoot {

	/*
	   @Bean 
	   public ActiveMQQueue queue() {
	      return new ActiveMQQueue("promoteAct");
	   }
	   
	   @Bean
	   public ActiveMQTopic topic(){
		   return new ActiveMQTopic("topicAct");
	   }
	   
	*/
	public static void main(String[] args){
		SpringApplication.run(SpringBoot.class, args);

	}
}
