package ejb.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.enterprise.event.Event;
import javax.inject.Inject;

import org.picketlink.idm.IdentityManager;
import org.picketlink.idm.PartitionManager;
import org.picketlink.idm.credential.Password;
import org.picketlink.idm.jpa.model.sample.simple.AccountTypeEntity;
import org.picketlink.idm.model.basic.User;

import ejb.dao.GameDaoLocal;
import ejb.dao.GameStatDaoLocal;
import ejb.dao.PlayerDaoLocal;
import ejb.domain.Game;
import ejb.domain.GameStat;
import ejb.domain.Player;

@Stateless
public class PlayerServiceImpl implements PlayerServiceLocal {

	@EJB PlayerDaoLocal pdl;
	@EJB GameStatDaoLocal gsdl;
	@EJB GameDaoLocal gdl;
	
	@EJB GameService gs;
	
    @Inject
    private PartitionManager partitionManager;
  
	
	@Inject
	private Logger LOGGER;
	
	
	@Inject
    private Event<Player> playerEvent;
	
	public void register(Player player) {
		playerEvent.fire(this.createNewPlayerInDb(player));
	}
	
	public Player register(User user,Password password) {
		LOGGER.info("CREATING player!! "+user.getId());
    	IdentityManager identityManager = this.partitionManager.createIdentityManager();
    	identityManager.add(user);
        identityManager.updateCredential(user, password);
		Player player = new Player();
		player.setUserId(user.getId()); 
		player.setEmail(user.getEmail());
		player.setName(user.getLoginName());
		GameStat gameStat = new GameStat();
		gameStat.setLosses(0);
		gameStat.setWins(0);
		gameStat.setUnfinished(0);
		gsdl.create(gameStat);
		player.setGameStat(gameStat);
		player = pdl.create(player);
		playerEvent.fire(player);
		return player;
	}


	public Player createNewPlayerInDb(Player player) {
		LOGGER.info("CREATING player!!");
		GameStat gameStat = new GameStat();
		gameStat.setLosses(0);
		gameStat.setWins(0);
		gameStat.setUnfinished(0);
		gsdl.create(gameStat);
		LOGGER.info("gamestat persisted: "+gameStat.getId());
		player.setGameStat(gameStat);
		return pdl.create(player);
	}

	
	public Player findPlayerByEmail(Player player) {
		return pdl.findPlayerByEmail(player.getEmail());
	}

	
	public void addWin(Player player) {
		gsdl.increaseWins(player.getGameStat());
	}

	
	public boolean doesPlayerExist(Player player) {
		return this.doesPlayerExist(player.getEmail());
	}
	
	public boolean doesPlayerExist(String email) {
		Player dbPlayer = pdl.findPlayerByEmail(email);
		if (dbPlayer.getEmail() == null || dbPlayer.getEmail().isEmpty()) return false;
		return true;
	}


	
	public Game createNewGame(Player player1, Player player2) {
		Game game = new Game();
		game.setGameCreated(new Date());
		game.setPlayer1(player1);
		game.setPlayer2(player2);
		return gdl.create(game);
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

	
	public List<Player> getPlayersOnline() {
		List<Player> playersOnline = new ArrayList<Player>();
		List<Player> playersInDb = pdl.getAll();
		for (Player u : playersInDb) {
			if (u.isOnline()) playersOnline.add(u);
		}
		return playersOnline;
	}

	
	public List<Player> getPlayersOffline() {
		List<Player> playersOffline = new ArrayList<Player>();
		List<Player> playersInDb = pdl.getAll();
		for (Player u : playersInDb) {
			if (!u.isOnline()) playersOffline.add(u);
		}
		return playersOffline;
	}

	
	public List<Player> getPlayersInGame() {
		List<Player> playersInGame = new ArrayList<Player>();
		List<Player> playersInDb = pdl.getAll();
		for (Player u : playersInDb) {
			if (u.isInGame()) playersInGame.add(u);
		}
		return playersInGame;
	}

	
	public void updatePlayerState(Player player) {
		pdl.update(player);
	}

	public GameService getGs() {
		return gs;
	}

	
	public Player findPlayerByEmail(String email) {
		Player player = new Player();
		player.setEmail(email);
		return this.findPlayerByEmail(player);
	}


	public PlayerDaoLocal getPdl() {
		return pdl;
	}
	
	

}
