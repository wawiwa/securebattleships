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
	private User user1;
	@OneToOne(fetch=FetchType.LAZY)
	  @JoinColumn(name="USER2_ID")
	private User user2;
	private Date gameCreated;
	private boolean unfinished;
	private User winner; // null if unfinished
	private User loser; // null if unfinished
	private String user1json;
	private String user2json;
	@OneToOne(fetch=FetchType.LAZY)
	  @JoinColumn(name="LAST_MOVE_USER_ID")
	private User lastUserToMove;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public User getUser1() {
		return user1;
	}
	public void setUser1(User user1) {
		this.user1 = user1;
	}
	public User getUser2() {
		return user2;
	}
	public void setUser2(User user2) {
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
	public User getWinner() {
		return winner;
	}
	public void setWinner(User winner) {
		this.winner = winner;
	}
	public User getLoser() {
		return loser;
	}
	public void setLoser(User loser) {
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
	public User getLastUserToMove() {
		return lastUserToMove;
	}
	public void setLastUserToMove(User lastUserToMove) {
		this.lastUserToMove = lastUserToMove;
	}
	
	
}
