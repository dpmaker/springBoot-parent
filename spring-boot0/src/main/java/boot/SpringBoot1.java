package boot;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.embedded.EmbeddedServletContainerFactory;
import org.springframework.boot.context.embedded.tomcat.TomcatEmbeddedServletContainerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import boot.common.UserBean;
import boot.jdbc.jdbc.UserController;
import boot.jdbc.jdbc.UserService;

@Controller
@RequestMapping("/test")
@SpringBootApplication
public class SpringBoot1 {

	@ResponseBody
	@RequestMapping("/hello")
	
	public String home(){
		return "Hello,World!";
	}

	
	public static void main(String[] args){
		//SpringApplication.run(SpringBoot1.class, args);
		SpringApplication.run(UserController.class, args);
		//new SpringApplicationBuilder(UserController.class).web(true).run(args);

	}
	
	@Bean
    public EmbeddedServletContainerFactory servletContainer() {
        TomcatEmbeddedServletContainerFactory factory = new TomcatEmbeddedServletContainerFactory();
        factory.setPort(8020);
        return factory;
    }
}
