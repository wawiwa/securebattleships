package web.data;

import java.util.List;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.event.Observes;
import javax.enterprise.event.Reception;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.inject.Named;

import ejb.domain.Game;
import ejb.service.GameServiceLocal;

@RequestScoped
public class GameListProducer {
	
    @Produces
    @Named
	protected List<Game> activeGames;
    
    @Inject
    private GameServiceLocal gsl;
    
    @Inject
    private Logger LOG;
    
    @PostConstruct
    public void init() {
    	activeGames = gsl.getAllActiveGamesFromDb();
    }
    
    public List<Game> getActiveGames() {
		return activeGames;
	}
    
    public void onGameEvent(@Observes(notifyObserver = Reception.IF_EXISTS) final Game game) {
    	LOG.info("added " +game);
        gsl.getAllActiveGamesFromDb();
    }

}
