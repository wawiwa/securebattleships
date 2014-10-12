package ejb.service;

import java.util.LinkedHashMap;
import java.util.Map;

import javax.ejb.Singleton;

@Singleton
public class GameService {

	private Map<String,Object> gameScope = new LinkedHashMap<String,Object>();

	public Map<String, Object> getGameScope() {
		return gameScope;
	}

	public void setGameScope(Map<String, Object> gameScope) {
		this.gameScope = gameScope;
	}
	
}
