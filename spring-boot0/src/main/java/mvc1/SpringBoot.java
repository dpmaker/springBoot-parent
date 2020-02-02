package mvc1;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import boot.common.UserBean;
import boot.jdbc.jdbc.UserController;
import boot.jdbc.jdbc.UserService;


public class SpringBoot {


	
	public static void main(String[] args){
		SpringApplication.run(UserController.class, args);

	}
}
