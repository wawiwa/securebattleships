package ejb.dao;

import javax.ejb.Local;

import ejb.domain.Player;

@Local
public interface PlayerDaoLocal extends GenericDao<Player> {
	
	public Player findPlayerByEmail(String email);
	
	public Player findPlayerByUserId(String userId);
	
	public Player findPlayerByName(String name);

}
