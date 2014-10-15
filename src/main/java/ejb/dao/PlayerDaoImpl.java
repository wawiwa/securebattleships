package ejb.dao;

import javax.ejb.Stateless;
import javax.persistence.NoResultException;

import web.data.User_reg;
import ejb.domain.Player;

@Stateless
public class PlayerDaoImpl extends GenericDaoImpl<Player> implements PlayerDaoLocal{

	/**
	 * @param email address
	 * 
	 * @return empty User if not found
	 */
    public Player findPlayerByEmail(String email) {
    	Player user = null;
    	try {
			user = (Player) em
				.createNamedQuery("findPlayerByEmail")
				.setParameter("email", email).getSingleResult();
    	} catch (NoResultException nre) {
    		return new Player();
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
