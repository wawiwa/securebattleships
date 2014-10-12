package ejb.domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="GAME_STATS")
public class GameStat implements Serializable {

	@Id @GeneratedValue
	private long id;
	
	private int wins;
	private int losses;
	private int unfinished;

	public int getWins() {
		return wins;
	}
	public void setWins(int wins) {
		this.wins = wins;
	}
	public int getLosses() {
		return losses;
	}
	public void setLosses(int losses) {
		this.losses = losses;
	}
	public int getUnfinished() {
		return unfinished;
	}
	public void setUnfinished(int unfinished) {
		this.unfinished = unfinished;
	}
	public long getId() {
		return id;
	}
	
	
}
