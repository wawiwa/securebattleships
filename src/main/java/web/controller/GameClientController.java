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
    
    // Do NOT rely on this to get your opponent, use the method getMyOpponent()
    private Player selectedOpponent;


	public void postInit(Player player){
		me = player;

		
		
		LOG.info("I'm a new player in GCC: "+me);
		
		
		/////////////// temp code ////////////////////
		
//		this.opponent = new Player();
//		opponent.setName("theirry");
//		opponent.setOnline(true);
//		opponent.setInGame(false);
//		psl.createNewPlayerInDb(opponent);
//		gsl.startNewGame(this.me, opponent);

	
		//////////////////////////////////////////////
	}
	
	public void startGame() {
		LOG.info("Starting game with: "+this.selectedOpponent);
		gsl.startNewGame(me, selectedOpponent);
	}
	
	public Player getLastPlayerToMove() {
		Game g = this.getMyActiveGame();
		if (g==null) return new Player();
		return g.getLastUserToMove();
	}
	
	public boolean makeMyMove() {
		if (gsl.makeMyMove(this.me, "JSON GAME STATE")) {
			LOG.info(me+" just made my move");
			return true;
		}
		else {
			LOG.info("Your opponent, "+this.getMyOpponent()+" is still moving.");
			return false;
		}
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

	public Player getSelectedOpponent() {
		return selectedOpponent;
	}

	public void setSelectedOpponent(Player selectedOpponent) {
		this.selectedOpponent = selectedOpponent;
	}

	
	public Player getMyOpponent() {
		return gsl.getMyOpponent(me);
	}
	
	
	
	
}