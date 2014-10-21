package web.controller;

import java.io.Serializable;
import java.util.logging.Logger;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import ejb.domain.Player;


@SessionScoped
@Named("gameClientController")
public class GameClientController implements Serializable {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

    
    @Inject
    private Logger LOG;
    
    private Player player;
    
	public void postInit(Player player){
		this.player = player;
		
		// This method should return a user object that corresponds to the passed in email
		// which should be grabbed from the session object (somehow).  
		//player= psl.findPlayerByEmail("someEmail@gmail.com");
		
		LOG.info("player in GCC: "+this.player);
		
		// Adding the user as a connected user on the game server
		GameServerController.AddConnectedUser(this.player);
	}

	public Player getPlayer() {
		return player;
	}
	
	
	
}