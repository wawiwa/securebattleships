package web.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import ejb.domain.Game;
import ejb.domain.Player;
import ejb.service.PlayerServiceLocal;
import web.data.ConnectedPlayer;

@ApplicationScoped
public class GameServerController {
	
	

	public static List <ConnectedPlayer> connectedUsers = new ArrayList<ConnectedPlayer>();
	public static Map<String,Game> games=new HashMap<String, Game>();
	@Inject
	private static PlayerServiceLocal psl;
	
    public static void AddConnectedUser(Player player){
    	// Adding a user that just logged on into the connect
    	// user object. 
    	ConnectedPlayer temp = new ConnectedPlayer(player);
    	connectedUsers.add(temp);
    }
    
    public static void RemoveConnectedUser(Player player) {
    	// removing the user from the connect user listing
    	connectedUsers.remove(player);
    }
    
    
    public static void AddToGames(Player player, String opponentEmail){
    	Game game = psl.createNewGame(player, psl.findPlayerByEmail(opponentEmail));
    	String tmpID = game.hashCode()  + "6";
    	games.put(tmpID, game);
    }
    
}
