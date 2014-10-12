package ejb.dao;

import javax.ejb.Stateless;

import ejb.domain.Game;

@Stateless
public class GameDaoImpl extends GenericDaoImpl<Game> implements GameDaoLocal {

}
