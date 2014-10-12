package ejb.dao;

import javax.ejb.Stateless;
import javax.persistence.NoResultException;

import web.data.User_reg;
import ejb.domain.User;

@Stateless
public class UserDaoImpl extends GenericDaoImpl<User> implements UserDaoLocal{

	/**
	 * @param email address
	 * 
	 * @return empty User if not found
	 */
    public User findUserByEmail(String email) {
    	User user = null;
    	try {
			user = (User) em
				.createNamedQuery("findUserByEmail")
				.setParameter("email", email).getSingleResult();
    	} catch (NoResultException nre) {
    		return new User();
    	}
		return user;
    }
    
    public boolean findUserReg(User_reg user_reg) {
    	try {
			em.createNamedQuery("findUserReg")
				.setParameter("email", user_reg.getEmail())
				.setParameter("password", user_reg.getPassword()).getSingleResult();
    	} catch (NoResultException nre) {
    		return false;
    	}
		return true;
    }
    
}
