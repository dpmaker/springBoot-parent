package boot.jdbc.mybatis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import boot.common.UserBean;
import boot.generator.test.dao.IMybatisUserDao;

@Service
public class MybatisUserService {
	
	@Autowired
	IMybatisUserDao iMybatisUserDao;
	
	public UserBean getUserByName(String name){
		return iMybatisUserDao.getUserByName(name);
	}

}
