package ejb.dao;

import javax.ejb.Local;

import models.User_reg;
import ejb.domain.User;

@Local
public interface UserDaoLocal extends GenericDao<User> {
	
	public User findUserByEmail(String email);
	
	public boolean findUserReg(User_reg user_reg);

}
