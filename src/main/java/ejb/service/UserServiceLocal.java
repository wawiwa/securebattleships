package ejb.service;

import java.util.List;

import javax.ejb.Local;

import web.data.User_reg;
import ejb.domain.Game;
import ejb.domain.User;

@Local
public interface UserServiceLocal {
	
	/**
	 * 
	 * @param user
	 * @return user
	 * 
	 * Creates a new user in the database. Also creates a new GameStat and associates 
	 * 	the initialized gameStat with this user.
	 * 
	 */
	public User createNewUserInDb(User user);
	
	/**
	 * 
	 * @param user
	 * @return user
	 * 
	 * Locates a user by email address of User object. 
	 * 
	 */
	public User findUserByEmail(User user);
	
	
	public User findUserByEmail(String email);
	
	/**
	 * 
	 * @param user
	 * 
	 * Increments the user's gameStat win column by 1.
	 * 
	 */
	public void addWin(User user);
	
	/**
	 * 
	 * @param user
	 * @return boolean
	 * 
	 * Checks if a user exists in the db.
	 */
	public boolean doesUserExist(User user);
	
	/**
	 * 
	 * @param user_reg
	 * @return
	 * 
	 * 'user_reg' is a non-persisted temporary placeholder that represents a person registering in the system.
	 * 	This method checks if that user has already registered.
	 */
	public boolean checkPassword(User_reg user_reg);
	
	/**
	 * 
	 * @param email
	 * @return boolean
	 * 
	 * Checks if a user exists by email address.
	 */
	public boolean doesUserExist(String email);
	
	/**
	 * 
	 * @param user1
	 * @param user2
	 * @return Game
	 * 
	 * Instantiates a new game with current system time.
	 * 
	 */
	public Game createNewGame(User user1, User user2); 
	
	/**
	 * 
	 * @param user
	 * @return List<Game>
	 * 
	 * Returns a list of games played by a specific user.
	 */
	public List<Game> getAllUserGames(User user);
	
	/**
	 * 
	 * @return List<User>
	 * 
	 * Returns a list of all users currently flagged as 'online'.
	 */
	public List<User> getUsersOnline();
	
	/**
	 * 
	 * @return List<User>
	 * 
	 * Returns a list of all users currently flagged as 'offline'.
	 */
	public List<User> getUsersOffline();
	
	/**
	 * 
	 * @return List<User>
	 * 
	 * Returns a list of all users currently playing a game.
	 */
	public List<User> getUsersInGame();
	
	/**
	 * 
	 * @param user
	 * 
	 * Updates a user's state in the db.
	 */
	public void updateUserState(User user);
	
	public GameService getGs();
	
}
