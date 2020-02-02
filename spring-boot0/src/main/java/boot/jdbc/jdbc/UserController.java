package boot.jdbc.jdbc;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import boot.common.UserBean;

@Controller
@RequestMapping("/jdbc")
@SpringBootApplication
public class UserController {

	@Autowired
	public UserService userService;
	
	
	@ResponseBody
	@RequestMapping("/findAllUser")
	public List<UserBean> findAllUser(){
		return userService.findAllUser();
	}
}
