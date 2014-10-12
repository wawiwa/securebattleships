package ejb.dao;

import javax.ejb.Local;

import ejb.domain.Game;

@Local
public interface GameDaoLocal extends GenericDao<Game>{
	
	// testing hot deploy

}
