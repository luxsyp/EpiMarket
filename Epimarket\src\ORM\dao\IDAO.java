package ORM.dao;

import java.util.List;

import ORM.criteria.ICriteria;

public interface IDAO {
	public int				save(Object o);
	public List<Object>		getAll(ICriteria crit);
	public void				update(Object o);
	public void				delete(Object o);
}
