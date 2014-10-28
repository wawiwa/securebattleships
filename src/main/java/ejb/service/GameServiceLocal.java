package ejb.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.Local;

import ejb.dao.GameDaoLocal;
import ejb.domain.Game;
import ejb.domain.Player;

@Local
public interface GameServiceLocal {
	

	public Player getMyOpponent(Player me);
	public List<Game> getAllActiveGamesFromDb();
	public List<Game> getAllGames();
	public Game startNewGame(Player you, Player opponent);
	public void makeGameInactive(Game game);
	public Game getActiveGame(Player player);
	public boolean makeMyMove(Player player, String gameState);
	public List<Game> getAllPlayerGames(Player player);
	public List<Player> getPlayersInGame();
	public GameDaoLocal getGdl();

}
