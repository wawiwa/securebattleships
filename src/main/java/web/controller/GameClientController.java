package web.controller;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
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
    
    private String oceanGrid;
    
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
		gsl.startNewGame(me, psl.findPlayerByName(selectedOpponent));
	}
	
	public Player getLastPlayerToMove() {
		Game g = this.getMyActiveGame();
		if (g==null) return new Player();
		return g.getLastUserToMove();
	}
	
	
	
	public String fireRound() {
		LOG.info(this.me + "Firing round");
		return this.checkRounds();
	}
	
	
	public String checkRounds() {
		
		// opponents ships    compare method () , compare the rounds against the opponents ships
		this.getMyOpponent().getShips();
		
		// set my outgoingShots
		me.setOutgoingShots(this.oceanGrid);
		
		// set incoming
		this.getMyOpponent().setIncomingShots(this.oceanGrid);
		
		this.getMyActiveGame().setLastUserToMove(me);
		return "checked coords";
	}
	
	public boolean makeMyMove() {
		if (gsl.makeMyMove(this.me, "JSON GAME STATE")) {
			LOG.info(me+" moves.");
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
	
	public void pushOceanGrid() {
		FacesContext context = FacesContext.getCurrentInstance();
	    Map<String,String> map = context.getExternalContext().getRequestParameterMap();
	    this.setOceanGrid(map.get("oceanGrid"));
	}

	public String getOceanGrid() {
		return oceanGrid;
	}

	public void setOceanGrid(String oceanGrid) {
		this.oceanGrid = oceanGrid;
	}
	
	
	
	
}