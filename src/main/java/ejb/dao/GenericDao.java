package ejb.dao;



import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;

public interface GenericDao<T extends Serializable> {
	
	public long count();

	public T create(T t);

	public void delete(Object id);

	public T find(Object id);

	public List<T> getAll();

	public T update(T t);
	
	public EntityManager getEm();
	
}