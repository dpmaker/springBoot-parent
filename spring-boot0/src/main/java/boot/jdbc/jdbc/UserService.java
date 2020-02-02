package boot.jdbc.jdbc;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import boot.common.UserBean;


@Service
public class UserService {

	@Autowired
	public UserDaoImp userDaoImp;
	
	public List<UserBean> findAllUser(){
		return userDaoImp.findAllUser();
				
	}
}
