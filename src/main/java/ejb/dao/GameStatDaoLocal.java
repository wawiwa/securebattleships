package ejb.dao;

import javax.ejb.Local;

import ejb.domain.GameStat;

@Local
public interface GameStatDaoLocal extends GenericDao<GameStat> {
	
	public void increaseWins(GameStat gameStat);

}
