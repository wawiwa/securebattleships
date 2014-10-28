package ejb.service;

import java.util.List;

import javax.ejb.Local;

import org.picketlink.idm.credential.Password;
import org.picketlink.idm.model.basic.User;

import ejb.dao.GameDaoLocal;
import ejb.dao.GameStatDaoLocal;
import ejb.dao.PlayerDaoLocal;
import ejb.domain.Game;
import ejb.domain.Player;

@Local
public interface PlayerServiceLocal {
	
	
	public void logsIn(Player player);
	
	public void logsOut(Player player);
	
	public Player register(User user,Password password);

	public GameStatDaoLocal getGsdl();
	
	
	/**
	 * 
	 * @param user
	 * @return user
	 * 
	 * Creates a new user in the database. Also creates a new GameStat and associates 
	 * 	the initialized gameStat with this user.
	 * 
	 */
//	public Player createNewPlayerInDb(Player player);
	
	/**
	 * 
	 * @param user
	 * @return user
	 * 
	 * Locates a user by email address of User object. 
	 * 
	 */
	public Player findPlayerByEmail(Player player);
	
	
	public Player findPlayerByEmail(String email);
	
	/**
	 * 
	 * @param user
	 * 
	 * Increments the user's gameStat win column by 1.
	 * 
	 */
	public void addWin(Player player);
	
	/**
	 * 
	 * @param user
	 * @return boolean
	 * 
	 * Checks if a user exists in the db.
	 */
	public boolean doesPlayerExist(Player player);
	
	/**
	 * @deprecated
	 * @param user_reg
	 * @return
	 * 
	 * 'user_reg' is a non-persisted temporary placeholder that represents a person registering in the system.
	 * 	This method checks if that user has already registered.
	 */
//	public boolean checkPassword(User_reg user_reg);
	
	/**
	 * 
	 * @param email
	 * @return boolean
	 * 
	 * Checks if a user exists by email address.
	 */
	public boolean doesPlayerExist(String email);
	
	
	/**
	 * 
	 * @return List<User>
	 * 
	 * Returns a list of all users currently flagged as 'online'.
	 */
	public List<Player> getPlayersOnline();
	
	/**
	 * 
	 * @return List<User>
	 * 
	 * Returns a list of all users currently flagged as 'offline'.
	 */
	public List<Player> getPlayersOffline();

	
	public PlayerDaoLocal getPdl();
	
}
