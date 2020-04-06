package boot.generator.test.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import boot.common.UserBean;


@Component
@Mapper
public interface IMybatisUserDao {

	UserBean getUserByName(String name);
}
