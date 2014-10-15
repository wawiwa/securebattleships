package ejb.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;


@Entity
@Table(name="GAMES")
public class Game implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id @GeneratedValue
	private long id;
	@OneToOne(fetch=FetchType.LAZY)
	  @JoinColumn(name="USER1_ID")
	private Player user1;
	@OneToOne(fetch=FetchType.LAZY)
	  @JoinColumn(name="USER2_ID")
	private Player user2;
	private Date gameCreated;
	private boolean unfinished;
	private Player winner; // null if unfinished
	private Player loser; // null if unfinished
	private String user1json;
	private String user2json;
	@OneToOne(fetch=FetchType.LAZY)
	  @JoinColumn(name="LAST_MOVE_USER_ID")
	private Player lastUserToMove;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public Player getUser1() {
		return user1;
	}
	public void setUser1(Player user1) {
		this.user1 = user1;
	}
	public Player getUser2() {
		return user2;
	}
	public void setUser2(Player user2) {
		this.user2 = user2;
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
	public String getUser1json() {
		return user1json;
	}
	public void setUser1json(String user1json) {
		this.user1json = user1json;
	}
	public String getUser2json() {
		return user2json;
	}
	public void setUser2json(String user2json) {
		this.user2json = user2json;
	}
	public Player getLastUserToMove() {
		return lastUserToMove;
	}
	public void setLastUserToMove(Player lastUserToMove) {
		this.lastUserToMove = lastUserToMove;
	}
	
	
}
