package ejb.service;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.enterprise.event.Event;
import javax.inject.Inject;

import org.picketlink.idm.IdentityManager;
import org.picketlink.idm.PartitionManager;
import org.picketlink.idm.credential.Password;
import org.picketlink.idm.model.basic.User;

import web.data.Online;
import ejb.dao.GameDaoLocal;
import ejb.dao.GameStatDaoLocal;
import ejb.dao.PlayerDaoLocal;
import ejb.domain.GameStat;
import ejb.domain.Player;

@Stateless
public class PlayerServiceImpl implements PlayerServiceLocal {

	@EJB PlayerDaoLocal pdl;
	@EJB GameStatDaoLocal gsdl;
	
    @Inject
    private PartitionManager partitionManager;
  
	
	@Inject
	private Logger LOGGER;
	
	
	@Inject
    private Event<Player> userEvent;
	
	@Inject @Online
    private Event<Player> playerEvent;

	public void logsIn(Player player) {
		player.setOnline(true);
		pdl.update(player);
		playerEvent.fire(player);
	}
	
	public void logsOut(Player player) {
		player.setOnline(false);
		pdl.update(player);
		playerEvent.fire(player);
	}
	
	public Player register(User user,Password password) {
    	IdentityManager identityManager = this.partitionManager.createIdentityManager();
    	identityManager.add(user);
        identityManager.updateCredential(user, password);
        LOGGER.info("CREATING player!! "+user.getId());
		Player player = new Player();
		player.setUserId(user.getId()); 
		player.setEmail(user.getEmail());
		player.setName(user.getLoginName());
		player.setInGame(false);
		GameStat gameStat = new GameStat();
		gameStat.setLosses(0);
		gameStat.setWins(0);
		gameStat.setUnfinished(0);
		gsdl.create(gameStat);
		player.setGameStat(gameStat);
		player = pdl.create(player);
		userEvent.fire(player);
		return player;
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

	
	public void updatePlayerState(Player player) {
		pdl.update(player);
	}

	public Player findPlayerByName(Player player) {
		return pdl.findPlayerByName(player.getName());
	}
	
	public Player findPlayerByEmail(String email) {
		Player player = new Player();
		player.setEmail(email);
		return this.findPlayerByEmail(player);
	}

	public PlayerDaoLocal getPdl() {
		return pdl;
	}

	public GameStatDaoLocal getGsdl() {
		// TODO Auto-generated method stub
		return gsdl;
	}

}
