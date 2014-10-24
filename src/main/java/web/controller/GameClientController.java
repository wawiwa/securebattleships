package web.controller;

import java.io.Serializable;
import java.util.List;
import java.util.logging.Logger;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import ejb.domain.Game;
import ejb.domain.Player;
import ejb.service.GameServiceLocal;
import ejb.service.PlayerServiceLocal;


@SessionScoped
@Named("gameClientController")
public class GameClientController implements Serializable {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

    @Inject 
    private PlayerServiceLocal psl;

    @Inject
    private GameServiceLocal gsl;
    
    @Inject
    private Logger LOG;
    
    private Player me;
    
    private Player opponent;


	public void postInit(Player player){
		me = player;
		me.setOnline(true);
		me.setInGame(false);
		psl.getPdl().update(me);

		
		LOG.info("player in GCC: "+me);
		
		
		/////////////// temp code ////////////////////
		
		this.opponent = new Player();
		opponent.setName("theirry");
		opponent.setOnline(true);
		opponent.setInGame(false);
		psl.createNewPlayerInDb(opponent);
		gsl.startNewGame(this.me, opponent);
		gsl.makeMyMove(this.me, "JSON GAME STATE");
	
		//////////////////////////////////////////////
	}
	
	public List<Game> getAllMyGames() {
		return gsl.getAllPlayerGames(me);
	}
	
	public Game getMyActiveGame() {
		return gsl.getActiveGame(me);
	}
	
	public List<Game> getAllActiveGames() {
		return gsl.getAllActiveGamesFromDb();
	}
	
	public List<Game> getAllGames() {
		return gsl.getAllGames();
	}
	
	public List<Player> getPlayersOnline(){
		return psl.getPlayersOnline();
	}

	public Player getMe() {
		return me;
	}
	
	public Player getOpponent() {
		return opponent;
	}
	
	public void setOpponent(Player opponent) {
		this.opponent = opponent;
	}

	
	
	
	
}