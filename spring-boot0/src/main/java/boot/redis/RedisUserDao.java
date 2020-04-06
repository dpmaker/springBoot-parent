package boot.redis;

import org.apache.ibatis.annotations.Mapper;

import bean.User;

@Mapper
public interface RedisUserDao {

	void insert(User user);
	
	void update(User user);
	
	Object selectUserById(String id);
	
	void delete(User user);
}
