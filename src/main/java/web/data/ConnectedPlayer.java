package web.data;

import ejb.domain.Player;



public class ConnectedPlayer {
	
	private Player player;
	private Player challenger;
	private boolean status;

	public ConnectedPlayer() {
		this.player = null;
		this.challenger = null;
		this.status = false;
	}

	public ConnectedPlayer(Player player) {
		this();
		this.player = player;
	}
	
	public ConnectedPlayer(Player player, Player challenger, boolean status) {
		this();
		this.player = player;
		this.challenger = challenger;
		this.status = status;
	}

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	public Player getChallenger() {
		return challenger;
	}

	public void setChallenger(Player challenger) {
		this.challenger = challenger;
	}

	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
	

}
