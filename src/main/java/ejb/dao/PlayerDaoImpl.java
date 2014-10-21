package ejb.dao;

import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import org.picketlink.idm.jpa.model.sample.simple.AccountTypeEntity;

import ejb.domain.Player;

@Stateless
public class PlayerDaoImpl extends GenericDaoImpl<Player> implements PlayerDaoLocal{

    @Inject
	private Logger LOG;
    
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
    
    public Player findPlayerByUserId(String userId) {
    	Player player = null;
    	try {
    		
    		Query query = em.createNamedQuery("findPlayerByUserId").setParameter("userId", userId);
    		LOG.info("query: "+query); //useless
			player = (Player) query.getSingleResult();
			
    	} catch (NoResultException nre) {
    		return new Player();
    	}
		return player;
    }
    
//    public boolean findUserReg(User_reg user_reg) {
//    	try {
//			em.createNamedQuery("findUserReg")
//				.setParameter("email", user_reg.getEmail())
//				.setParameter("password", user_reg.getPassword()).getSingleResult();
//    	} catch (NoResultException nre) {
//    		return false;
//    	}
//		return true;
//    }
    
}
