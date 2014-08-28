package ORM;

import java.util.List;

import ORM.criteria.ICriteria;
import ORM.dao.XMLConfig;

public interface IDBFacade {
	public int				save(Object o, XMLConfig dao) throws OrmException;//INSERT
	public List<Object>                     getAll(ICriteria crit, XMLConfig dao) throws OrmException;// SELECT
	public boolean                          update(Object o, XMLConfig dao) throws OrmException;// UPDATE
	public boolean                          delete(Object o, XMLConfig dao) throws OrmException;// DELETE
	public void				initXML(XMLConfig xml) throws OrmException;
	public XMLConfig                        getXML(String table) throws OrmException;
}
