package ejb.service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.inject.Inject;

import web.data.User_reg;
import ejb.dao.GameDaoLocal;
import ejb.dao.GameStatDaoLocal;
import ejb.dao.UserDaoLocal;
import ejb.domain.Game;
import ejb.domain.GameStat;
import ejb.domain.User;

@Stateless
public class UserServiceImpl implements UserServiceLocal {

	@EJB UserDaoLocal udl;
	@EJB GameStatDaoLocal gsdl;
	@EJB GameDaoLocal gdl;
	
	@EJB GameService gs;
	
	@Inject
	private Logger LOGGER;
	
	public User createNewUserInDb(User user) {
		LOGGER.info("CREATING USER!!");
		GameStat gameStat = new GameStat();
		gameStat.setLosses(0);
		gameStat.setWins(0);
		gameStat.setUnfinished(0);
		gsdl.create(gameStat);
		LOGGER.info("gamestat persisted: "+gameStat.getId());
		user.setGameStat(gameStat);
		return udl.create(user);
	}

	
	public User findUserByEmail(User user) {
		return udl.findUserByEmail(user.getEmail());
	}

	
	public void addWin(User user) {
		gsdl.increaseWins(user.getGameStat());
	}

	
	public boolean doesUserExist(User user) {
		return this.doesUserExist(user.getEmail());
	}

	
	public boolean checkPassword(User_reg user_reg) {
		return udl.findUserReg(user_reg);
	}

	
	public boolean doesUserExist(String email) {
		User dbuser = udl.findUserByEmail(email);
		if (dbuser.getEmail() == null || dbuser.getEmail().isEmpty()) return false;
		return true;
	}

	
	public Game createNewGame(User user1, User user2) {
		Game game = new Game();
		game.setGameCreated(new Date());
		game.setUser1(user1);
		game.setUser2(user2);
		return gdl.create(game);
	}

	
	public List<Game> getAllUserGames(User user) {
		List<Game> userGames = new ArrayList<Game>();
		List<Game> games = gdl.getAll();
		for (Game g : games) {
			if (g.getUser1().equals(user)) userGames.add(g);
			if (g.getUser2().equals(user)) userGames.add(g);
		}
		return userGames;
	}

	
	public List<User> getUsersOnline() {
		List<User> usersOnline = new ArrayList<User>();
		List<User> usersInDb = udl.getAll();
		for (User u : usersInDb) {
			if (u.isOnline()) usersOnline.add(u);
		}
		return usersOnline;
	}

	
	public List<User> getUsersOffline() {
		List<User> usersOffline = new ArrayList<User>();
		List<User> usersInDb = udl.getAll();
		for (User u : usersInDb) {
			if (!u.isOnline()) usersOffline.add(u);
		}
		return usersOffline;
	}

	
	public List<User> getUsersInGame() {
		List<User> usersInGame = new ArrayList<User>();
		List<User> usersInDb = udl.getAll();
		for (User u : usersInDb) {
			if (u.isInGame()) usersInGame.add(u);
		}
		return usersInGame;
	}

	
	public void updateUserState(User user) {
		udl.update(user);
	}

	public GameService getGs() {
		return gs;
	}

	
	public User findUserByEmail(String email) {
		User user = new User();
		user.setEmail(email);
		return this.findUserByEmail(user);
	}
	
	

	
	
}
