package boot.jdbc.mybatis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import boot.generator.test.dao.TUser2Mapper;
import boot.generator.test.entity.TUser2;

@RestController
@RequestMapping("/auto")
public class MybatisAutoController {

	@Autowired
	private TUser2Mapper tUser2Mapper;
	
	@RequestMapping("/getUserById/{id}")
	public TUser2 getUser(@PathVariable String id){
		return tUser2Mapper.selectByPrimaryKey(Integer.valueOf(id));
	}
}
