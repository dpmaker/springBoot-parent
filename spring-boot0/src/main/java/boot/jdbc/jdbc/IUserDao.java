package boot.jdbc.jdbc;

import java.util.List;

import boot.common.UserBean;

public interface IUserDao {

	public List<UserBean> findAllUser();
}
