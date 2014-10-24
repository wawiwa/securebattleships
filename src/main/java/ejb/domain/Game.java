package ejb.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@NamedQueries({
	@NamedQuery(name ="findGameByPlayer", 
			query="SELECT u FROM Player u WHERE u.email LIKE :email"),
})
@Entity
@Table(name="GAMES")
public class Game implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id @GeneratedValue
	private long id;
	@OneToOne(fetch=FetchType.EAGER)
	  @JoinColumn(name="PLAYER1_ID")
	private Player player1;
	@OneToOne(fetch=FetchType.EAGER)
	  @JoinColumn(name="PLAYER2_ID")
	private Player player2;
	private Date gameCreated;
	private boolean unfinished;
	private boolean active;
	private Player winner; // null if unfinished
	private Player loser; // null if unfinished
	
	@OneToOne(fetch=FetchType.EAGER)
	  @JoinColumn(name="LAST_MOVE_USER_ID")
	private Player lastUserToMove;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public Player getPlayer1() {
		return player1;
	}
	public void setPlayer1(Player player1) {
		this.player1 = player1;
	}
	public Player getPlayer2() {
		return player2;
	}
	public void setPlayer2(Player player2) {
		this.player2 = player2;
	}
	public Date getGameCreated() {
		return gameCreated;
	}
	public void setGameCreated(Date gameCreated) {
		this.gameCreated = gameCreated;
	}
	public boolean isUnfinished() {
		return unfinished;
	}
	public void setUnfinished(boolean unfinished) {
		this.unfinished = unfinished;
	}
	public Player getWinner() {
		return winner;
	}
	public void setWinner(Player winner) {
		this.winner = winner;
	}
	public Player getLoser() {
		return loser;
	}
	public void setLoser(Player loser) {
		this.loser = loser;
	}
	public Player getLastUserToMove() {
		return lastUserToMove;
	}
	public void setLastUserToMove(Player lastUserToMove) {
		this.lastUserToMove = lastUserToMove;
	}
	public boolean isActive() {
		return active;
	}
	public void setActive(boolean active) {
		this.active = active;
	}
	
	
}
