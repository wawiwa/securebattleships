package ejb.domain;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

@NamedQueries({
	@NamedQuery(name ="findUserByEmail", 
			query="SELECT u FROM Player u WHERE u.email LIKE :email"),
	@NamedQuery(name ="findPlayerByUserId", 
			query="SELECT p FROM Player p WHERE p.userId LIKE :userId"),
	@NamedQuery(name ="findPlayerByName", 
			query="SELECT p FROM Player p WHERE p.name LIKE :name"),
//	@NamedQuery(name ="findUserReg", 
//			query="SELECT u FROM Player u WHERE u.email LIKE :email AND u.password LIKE :password"),
//	@NamedQuery(name ="findAllDataSourceByCategoryName",
//			query="SELECT ds FROM DataSource ds WHERE ds.")
})
@Entity
@Table(name="PLAYERS")
public class Player implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue
	private Long id;
	
	private String userId;

	@OneToOne(fetch=FetchType.EAGER)
	  @JoinColumn(name="GAME_STAT_ID",referencedColumnName="id")
	private GameStat gameStat;
	
    @NotNull
    @NotEmpty
    @Email(message="Email invalid.")
	private String email;
    
	private String name;
	private boolean online;
	private boolean inGame;
	private String gameStateJson;
	private String ships;
	private String outgoingShots;
	private String incomingShots;
	
	public Long getId() {
		return id;
	}
	
	
	
	public boolean isOnline() {
		return online;
	}



	public void setOnline(boolean online) {
		this.online = online;
	}



	public boolean isInGame() {
		return inGame;
	}



	public void setInGame(boolean inGame) {
		this.inGame = inGame;
	}



	public GameStat getGameStat() {
		return gameStat;
	}

	public void setGameStat(GameStat gameStat) {
		this.gameStat = gameStat;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getGameStateJson() {
		return gameStateJson;
	}


	public void setGameStateJson(String gameStateJson) {
		this.gameStateJson = gameStateJson;
	}


	public String getUserId() {
		return this.userId;
	}



	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	



	public String getShips() {
		return ships;
	}



	public void setShips(String ships) {
		this.ships = ships;
	}



	public String getOutgoingShots() {
		return outgoingShots;
	}



	public void setOutgoingShots(String outgoingShots) {
		this.outgoingShots = outgoingShots;
	}



	public String getIncomingShots() {
		return incomingShots;
	}



	public void setIncomingShots(String incomingShots) {
		this.incomingShots = incomingShots;
	}



	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}



	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Player other = (Player) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}



	@Override
	public String toString() {
		return "Player [id=" + id + ", name=" + name + "]";
	}

	
	
	

}
