package boot.jdbc.mybatis;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/mybatis")

public class MybatisController {
	
	@Autowired
	MybatisUserService mybatisUserService;
	
	@ResponseBody
	@RequestMapping("/getUserByName/{name}")
	public String getUserByName(@PathVariable String name){
		return mybatisUserService.getUserByName(name).toString();
	}
}
