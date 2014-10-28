package ejb.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.enterprise.event.Event;
import javax.inject.Inject;

import ejb.dao.GameDaoLocal;
import ejb.domain.Game;
import ejb.domain.Player;

@Stateless
public class GameServiceImpl implements GameServiceLocal {

	@Inject
	private Logger LOG;
	
	@Inject
	private PlayerServiceLocal psl;
	
	@Inject
	private Event<Game> gameEventSrvc;
	
	@EJB GameDaoLocal gdl;
	
	public Player getMyOpponent(Player me) {
		Game g = this.getActiveGame(me);
		if (g.getPlayer1().equals(me)) return g.getPlayer2();
		else return g.getPlayer1();
	}

	public List<Game> getAllActiveGamesFromDb() {
		List<Game> temp = new ArrayList<Game>();
		for (Game g : gdl.getAll()) {
			if (g.isActive()) {
				temp.add(g);
			}
		}
		return temp;
	}
	
	public List<Game> getAllGames() {
		return gdl.getAll();
	}
	
	public Game startNewGame(Player you, Player opponent) {
		Game game = new Game();
		game.setActive(true);
		game.setGameCreated(new Date());
		game.setPlayer1(you);
		game.setPlayer2(opponent);
		you.setInGame(true);
		opponent.setInGame(true);
		psl.getPdl().update(you);
		psl.getPdl().update(opponent);
		game = gdl.create(game);
		gameEventSrvc.fire(game);
		return game;
	}
	
	public void makeGameInactive(Game game) {
		game.setActive(false);
		getGdl().update(game);
		gameEventSrvc.fire(game);
	}
	
	public Game getActiveGame(Player player) {
		for (Game g : gdl.getAll()) {
			if (g.getPlayer1().equals(player) || g.getPlayer2().equals(player)) {
				return g;
			}
		}
		return null;
	}
	
	public boolean makeMyMove(Player player, String gameState) {
		Game g = this.getActiveGame(player);
		if (g.getLastUserToMove() != null && g.getLastUserToMove().equals(player)) {
			return false; // other player hasn't moved yet.
		}
		g.setLastUserToMove(player);
		player.setGameStateJson(gameState);
		psl.getPdl().update(player);
		gdl.update(g);
		return true;
	}
	
	public List<Game> getAllPlayerGames(Player player) {
		List<Game> playerGames = new ArrayList<Game>();
		List<Game> games = gdl.getAll();
		for (Game g : games) {
			if (g.getPlayer1().equals(player)) playerGames.add(g);
			if (g.getPlayer2().equals(player)) playerGames.add(g);
		}
		return playerGames;
	}
	
	public List<Player> getPlayersInGame() {
		List<Player> playersInGame = new ArrayList<Player>();
		for (Game g : this.getAllActiveGamesFromDb()) {
			playersInGame.add(g.getPlayer1());
			playersInGame.add(g.getPlayer2());
		}
		return playersInGame;
	}

	public GameDaoLocal getGdl() {
		return gdl;
	}
}
