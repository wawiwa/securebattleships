package ejb.dao;

import javax.ejb.Stateless;

import ejb.domain.GameStat;

@Stateless
public class GameStatDaoImpl extends GenericDaoImpl<GameStat> implements GameStatDaoLocal {

	public void increaseWins(GameStat gameStat) {
		gameStat.setWins(gameStat.getWins()+1);
	}
}
