package web.controller;

import java.io.Serializable;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.enterprise.inject.Produces;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;
import javax.inject.Inject;
import javax.inject.Named;

import org.picketlink.credential.DefaultLoginCredentials;
import org.picketlink.idm.model.basic.User;

import ejb.domain.Player;
import ejb.service.PlayerServiceLocal;

@SessionScoped
@Named
public class GameClientController implements Serializable {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Player player;
	
	private String testString;
	
	@Inject
	private PlayerServiceLocal psl;
	
	@Inject
    private FacesContext facesContext;
    
    @Inject 
    private DefaultLoginCredentials credentials;
    
    @Inject
    private Logger LOG;
    
	@PostConstruct
	public void init(){
		testString = "Set inside GCC";
	/*	this.user = new User();
		user.setEmail("ted@gmail.com");
		user.setName("ted");
		user.setPassword("admin");
		user.setOnline(true);
		user.setInGame(false);
		user.setGameStat(null);*/
		
		// This method should return a user object that corresponds to the passed in email
		// which should be grabbed from the session object (somehow).  
		//player= psl.findPlayerByEmail("someEmail@gmail.com");
		
		LOG.info("player in GCC: "+player);
		
		// Adding the user as a connected user on the game server
		GameServerController.AddConnectedUser(this.player);
		


}
	public Player getPlayer() {
		return player;
	}
	public void setPlayer(Player player) {
		this.player = player;
	}
	public PlayerServiceLocal getPsl() {
		return psl;
	}
	public void setPsl(PlayerServiceLocal psl) {
		this.psl = psl;
	}
	public String getTestString() {
		return testString;
	}
	public void setTestString(String testString) {
		this.testString = testString;
	}
	
}