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

import ejb.domain.Player;
import ejb.service.PlayerServiceLocal;

@RequestScoped
public class PlayersOnlineListProducer {
	
    @Produces
    @Named
	protected List<Player> onlinePlayers;
    
    @Inject
    private PlayerServiceLocal psl;
    
    @Inject
    private Logger LOG;
    
    // INITIALIZE
    @PostConstruct
    public void init() {
    	onlinePlayers = psl.getPlayersOnline();
    }
    
    // GETTER
    public List<Player> getOnlinePlayers() {
		return onlinePlayers;
	}
    
    // UPDATER
    public void onPlayerEvent(@Observes @Online final Player player) {
    	LOG.info("Player status changed: " +player);
        psl.getPlayersOnline();
    }

}
