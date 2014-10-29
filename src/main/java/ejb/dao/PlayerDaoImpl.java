package ejb.dao;

import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.NoResultException;
import javax.persistence.Query;

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
    
    public Player findPlayerByName(String name) {
    	Player player = null;
    	try {
    		
    		Query query = em.createNamedQuery("findPlayerByName").setParameter("name", name);
    		LOG.info("query: "+query); //useless
			player = (Player) query.getSingleResult();
			
    	} catch (NoResultException nre) {
    		return new Player();
    	}
		return player;
    }
    
    
}
