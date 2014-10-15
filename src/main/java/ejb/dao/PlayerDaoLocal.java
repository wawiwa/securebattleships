package ejb.dao;

import javax.ejb.Local;

import web.data.User_reg;
import ejb.domain.Player;

@Local
public interface PlayerDaoLocal extends GenericDao<Player> {
	
	public Player findPlayerByEmail(String email);
	
	public boolean findUserReg(User_reg user_reg);

}
