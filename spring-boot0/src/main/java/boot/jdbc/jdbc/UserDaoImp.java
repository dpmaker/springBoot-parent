package boot.jdbc.jdbc;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import boot.common.UserBean;

@Service
public class UserDaoImp implements IUserDao{

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	
	@Override
	public List<UserBean> findAllUser() {
		String sql="select * from user;";
		return jdbcTemplate.query(sql, new BeanPropertyRowMapper(UserBean.class));
	}

}
