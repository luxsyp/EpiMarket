package ORM;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

import ORM.criteria.ICriteria;
import ORM.dao.XMLConfig;

public class DBFacade implements IDBFacade {
	public DBFacade()
	{
		this.xmls = new HashMap<String, XMLConfig>(0);
	}
	public int			save(Object o, XMLConfig conf) throws OrmException
	{//INSERT
		try {
			return (DBManager.getInstance().insertObject(o, conf));
		} catch (SQLException e) {
			throw new OrmException("An SQL error has occured");
		} catch (IllegalAccessException e) {
			throw new OrmException("An Illegal Acces has occured");
		} catch (InvocationTargetException e) {
			throw new OrmException("An Invocation Target has occured");
		} catch (NoSuchMethodException e) {
			throw new OrmException("No Such method has been detected");
		}
	}
	public List<Object>		getAll(ICriteria crit, XMLConfig conf) throws OrmException
	{// SELECT
		try {
			return (DBManager.getInstance().getAll(crit, conf));
		} catch (SQLException e) {
			throw new OrmException("An SQL error has occured");
		} catch (ClassNotFoundException e) {
			throw new OrmException("Class not found has been detected");
		} catch (InstantiationException e) {
			throw new OrmException("Instantiation failed has been detected");
		} catch (IllegalAccessException e) {
			throw new OrmException("An Illegal Acces has occured");
		} catch (InvocationTargetException e) {
			throw new OrmException("An Invocation Target has occured");
		} catch (NoSuchMethodException e) {
			throw new OrmException("No Such method has been detected");
		}
	}
	public boolean			update(Object o, XMLConfig conf) throws OrmException
	{// UPDATE
		try {
			return (DBManager.getInstance().updateObject(o, conf));
		} catch (SQLException e) {
			throw new OrmException("An SQL error has occured");
		} catch (IllegalAccessException e) {
			throw new OrmException("An Illegal Acces has occured");
		} catch (InvocationTargetException e) {
			throw new OrmException("An Invocation Target has occured");
		} catch (NoSuchMethodException e) {
			throw new OrmException("No Such method has been detected");
		}
	}
	public boolean			delete(Object o, XMLConfig conf) throws OrmException
	{// DELETE
		try {
			return (DBManager.getInstance().deleteObject(o, conf));
		} catch (SQLException e) {
			throw new OrmException("An SQL error has occured");
		} catch (IllegalAccessException e) {
			throw new OrmException("An Illegal Acces has occured");
		} catch (InvocationTargetException e) {
			throw new OrmException("An Invocation Target has occured");
		} catch (NoSuchMethodException e) {
			throw new OrmException("No Such method has been detected");
		}
	}
	
	public void				initXML(XMLConfig conf)
	{
		if (this.xmls.get(conf.getTable()) == null)
		{
			System.out.println("put : " + conf.getTable());
			this.xmls.put(conf.getTable().toLowerCase(), conf);
		}
	}
	public XMLConfig		getXML(String table)
	{
		return (this.xmls.get(table.toLowerCase()));
	}
	
	static public IDBFacade		getInstance()
	{
		if (instance == null)
			instance = new DBFacade();
		return (instance);
	}
	
	
	private Map<String, XMLConfig>		xmls;
	static private IDBFacade	instance = null;
}
