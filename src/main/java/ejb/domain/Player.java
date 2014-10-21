package ejb.domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

import org.picketlink.idm.jpa.model.sample.simple.AccountTypeEntity;

@NamedQueries({
	@NamedQuery(name ="findUserByEmail", 
			query="SELECT u FROM Player u WHERE u.email LIKE :email"),
	@NamedQuery(name ="findPlayerByUserId", 
			query="SELECT p FROM Player p WHERE p.userId LIKE :userId"),
//	@NamedQuery(name ="findUserReg", 
//			query="SELECT u FROM Player u WHERE u.email LIKE :email AND u.password LIKE :password"),
//	@NamedQuery(name ="findAllDataSourceByCategoryName",
//			query="SELECT ds FROM DataSource ds WHERE ds.")
})
@Entity
@Table(name="PLAYERS")
@XmlRootElement
public class Player implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue
	private Long id;
	
	private String userId;

	@OneToOne(fetch=FetchType.LAZY)
	  @JoinColumn(name="GAME_STAT_ID",referencedColumnName="id")
	private GameStat gameStat;
	
	private String email;
	private String name;
	private boolean online;
	private boolean inGame;
	
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
	
	

	public String getUserId() {
		return this.userId;
	}



	public void setUserId(String userId) {
		this.userId = userId;
	}



	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((email == null) ? 0 : email.hashCode());
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
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Player [id=" + id + ", name=" + name + ", email=" + email + "]";
	}
	
	
	

}
