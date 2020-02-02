package boot.generator.test.dao;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import boot.common.UserBean;


public interface IMybatisUserDao {

	UserBean getUserByName(String name);
}
