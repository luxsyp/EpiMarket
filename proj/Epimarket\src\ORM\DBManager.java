package ORM;


import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.util.Iterator;
import java.util.List;
import java.util.ArrayList;

import ORM.criteria.*;

import ORM.dao.XMLConfig;


public class DBManager
{
	private static DBManager	dbManager = null;
	
	//Objects spŽcifiques aux differents appels vers les bases (locales ou distantes).	
	private Connection			myConnect;	

	//Objects de Meta-Information sur la Database connecte, et sur la requte effectuŽe.
	private DatabaseMetaData	myDbMetaData;

	//////////////////
	//SINGLETON
	/////////////////
	
	public static DBManager     getInstance()
    {
        if (dbManager == null)
            dbManager = new DBManager();
        return (dbManager);
    }
	
	public DBManager()
	{
		dbConnect();
	}
        
        //////////////////
	//SELECT
	/////////////////
	
	public Object				getFromAbstract(String abs, int id, XMLConfig conf)
		throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException, InvocationTargetException, NoSuchMethodException, OrmException
	{
		String			query;
		ResultSet 		resultSet = null;
		ICriteria		crit = new Criteria(abs, new CEqual(Integer.toString(id)));

                query = "SELECT * FROM " + conf.getTable();
                query += " WHERE " + crit.toString(conf);
		resultSet = this.processQuery(query);
		if (resultSet.next())
		{
			List<Object>		temp = DBFacade.getInstance().getAll(crit, conf);
			
			Iterator<Object>	it = temp.iterator();
			if (it != null)
				return (it.next());
		}
		return (null);
	}
	
	private Object				doAbstract(Object targetObject, ICriteria crit, XMLConfig conf)
		throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException, InvocationTargetException, NoSuchMethodException, OrmException
	{
		String			query;
		ResultSet 		resultSet = null;
		List<String>	abstrs = conf.getAllAbstract();
		
		
                query = "SELECT * FROM " + conf.getTable();
                if (crit != null)
                    query += " WHERE " + crit.toString(conf);
		resultSet = this.processQuery(query);
		if (resultSet.next())
		{
			for (Iterator<String> it = abstrs.iterator(); it.hasNext();)
			{
				String temp = it.next();
				XMLConfig tconf = DBFacade.getInstance().getXML(temp);
			
				this.doAbstract(targetObject, crit, tconf);
			}
			targetObject = DBFactory.completeObject(targetObject, resultSet, conf);
		}
		return (targetObject);
	}
	
	public List<Object>			getAll(ICriteria crit, XMLConfig conf)
		throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException, InvocationTargetException, NoSuchMethodException, OrmException
	{	
		String			query;
		ResultSet 		resultSet = null;
		List<Object>	result = new ArrayList<Object>(0);
		List<String>	abstrs = conf.getAllAbstract();
		
                query = "SELECT * ";
                if (crit != null)
                    query += crit.toFrom(conf) + " WHERE " + crit.toString(conf);
                else
                    query += "FROM " + conf.getTable();
                resultSet = this.processQuery(query);
		while (resultSet.next())
		{
			List<String>	pos = conf.getPos();
			Object			targetObject = null;

			for (Iterator<String> it = pos.iterator();it.hasNext();)
			{
				String tit = (String)it.next();
                                //System.out.println("tit : " + tit);
				Object o = this.getFromAbstract(conf.getAbstractPos(tit),
								resultSet.getInt(conf.getIdCol()),
								DBFacade.getInstance().getXML(tit));

				if (o != null)
				{
					result.add(o);
					targetObject = o;
					break ;
				}
			}
			if (targetObject != null)
				continue ;
			Class<?> c = Class.forName(conf.getName());
			targetObject = c.newInstance();
			for (Iterator<String> it = abstrs.iterator(); it.hasNext();)
			{
				String temp = it.next();
				XMLConfig tconf = DBFacade.getInstance().getXML(temp);
				
				this.doAbstract(targetObject,
						new Criteria(tconf.getIdAttr(), new CEqual(resultSet.getString(conf.getAbstractCol(temp)))),
						tconf);
			}
			result.add(DBFactory.completeObject(targetObject, resultSet, conf));
		}
		return (result);
	}
	
        //////////////////
	//INSERT
	/////////////////
              
        public void				trySave(Object o, XMLConfig conf)
		throws NumberFormatException, IllegalAccessException, InvocationTargetException, NoSuchMethodException, OrmException
	{
		if (Integer.parseInt(DBFactory.getTrueValue(o, conf.getIdAttr()).toString()) == 0)
			DBFacade.getInstance().save(o, conf);
	}
        
	private void			insertList(List<Object> lo, XMLConfig conf, String attr, int id)
		throws IllegalAccessException, InvocationTargetException, SQLException, NoSuchMethodException, OrmException
	{
		if (lo != null)
		{
			for (Iterator<Object> itt = lo.iterator();
				itt.hasNext();)
			{
				Object	o = itt.next();
				
				DBFactory.setValue(o, attr, id);
				this.insertObject(o, conf);
			}
		}
	}
	
        private void                    tryInsertList(Object o, XMLConfig conf, int id)
                throws IllegalAccessException, InvocationTargetException, SQLException, NoSuchMethodException, OrmException
        {
            for (Iterator<String> it = conf.getAllAttr().iterator();
		it.hasNext();)
		{
			String 	attr = (String)it.next();
			
			if (conf.getAttrMult(attr))
			{
				this.insertList((List<Object>)DBFactory.getTrueValue(o, attr),
						DBFacade.getInstance().getXML(conf.getColumnTable(attr)),
						conf.getDesignationAttr(attr), id);
			}
		}
        }
        
	@SuppressWarnings("unchecked")
	public int				insertObject(Object o, XMLConfig conf)
		throws SQLException, IllegalAccessException, InvocationTargetException, NoSuchMethodException, OrmException
	{
		List<String>            abstrs = conf.getAllAbstract();
		String			insert;
		String			columns;
		String			values;
		int				i;

		insert = "INSERT INTO " + conf.getTable();
		columns = "(";
		values = "(";
		i = 0;
		for (Iterator<String> it = abstrs.iterator(); it.hasNext();)
		{
			String temp			= it.next();
			XMLConfig tconf		= DBFacade.getInstance().getXML(temp);
			
			columns += ((i > 0) ? "," : "") + "`" + temp + "`";
			values += ((i > 0) ? "," : "") + "'" + this.insertObject(o, tconf) + "'";
			++i;
		}
		for (Iterator<String> it = conf.getAllAttr().iterator();
                    it.hasNext();)
		{
			String 	attr = (String)it.next();
			String	col = conf.getColumnAttribute(attr);
			String 	type = conf.getTypeAttr(attr);
			String 	value;

			if (!conf.getAttrMult(attr))
			{
                            if (type != null)
                            {
                                Object tvalue = DBFactory.getTrueValue(o, attr);// + "." + conf.getDesignationAttr(attr));
                                if (tvalue != null)
                                {
                                    /*System.out.println(conf.getColumnTable(attr));
                                    System.out.println(tvalue);*/
                                    this.trySave(tvalue, DBFacade.getInstance().getXML(conf.getColumnTable(attr)));
                                    columns += ((i > 0) ? "," : "") + "`" + col + "`";
                                    values += ((i > 0) ? "," : "") + "'" + DBFactory.getValue(tvalue, conf.getDesignationAttr(attr)) + "'";
                                    
                                    ++i;
                                }
                            }
                            else
                            {
                                value = (String) DBFactory.getValue(o, attr);
                                columns += ((i > 0) ? "," : "") + "`" + col + "`";
                                values += ((i > 0) ? "," : "") + "'" + escapeQuote(value) + "'";
                                ++i;
                            }
			}
		}
		columns += ")";
		values += ")";
		i = this.processUpdate(insert + " " + columns + " VALUES" + values);
		DBFactory.setValue(o, conf.getIdAttr(), i);
		this.tryInsertList(o, conf, i);
		return (i);
	}
        
        //////////////////
	//UPDATE
	/////////////////
        
        
        public boolean				trySaveOrUpdate(Object o, XMLConfig conf)
		throws NumberFormatException, IllegalAccessException, InvocationTargetException, NoSuchMethodException, OrmException
	{
		if (Integer.parseInt(DBFactory.getValue(o, conf.getIdAttr()).toString()) != 0)
			DBFacade.getInstance().update(o, conf);
		else
			DBFacade.getInstance().save(o, conf);
		return (true);
	}
        
	private void				updateList(List<Object> lo, XMLConfig conf)
		throws SQLException, IllegalAccessException, InvocationTargetException, NoSuchMethodException, NumberFormatException, OrmException
	{
		if (lo != null)
		{
			for (Iterator<Object> itt = lo.iterator();
				itt.hasNext();)
			{
				Object	o = itt.next();
				
				this.trySaveOrUpdate(o, conf);
			}
		}
	}
	
	@SuppressWarnings("unchecked")
	public boolean				updateObject(Object o, XMLConfig conf)
		throws SQLException, IllegalAccessException, InvocationTargetException, NoSuchMethodException, OrmException
	{
		List<String>	abstrs = conf.getAllAbstract();
		String			update;
		String			updates;
		String			where;
		int				i;

		update = "UPDATE " + conf.getTable();
		updates = "SET ";
		i = 0;
		for (Iterator<String> it = abstrs.iterator(); it.hasNext();)
		{
			String temp			= it.next();
			XMLConfig tconf		= DBFacade.getInstance().getXML(temp);
			
			this.updateObject(o, tconf);
		}
		for (Iterator<String> it = conf.getAllAttr().iterator();
    		it.hasNext();)
		{
			String 	attr = (String)it.next();
			String	col = conf.getColumnAttribute(attr);
			String 	type = conf.getTypeAttr(attr);
			String 	value;

			if (!conf.getAttrMult(attr))
			{
                            if (type != null)
                            {
                                Object tvalue = DBFactory.getTrueValue(o, attr);// + "." + conf.getDesignationAttr(attr));
                                if (tvalue != null)
                                {
                                    this.trySaveOrUpdate(tvalue, DBFacade.getInstance().getXML(conf.getColumnTable(attr)));
                                    updates += ((i > 0) ? "," : "") + "" + col + " = '" + DBFactory.getValue(tvalue, conf.getDesignationAttr(attr)) + "'";
                                    ++i;
                                }
                            }
                            else
                            {
                                value = (String) DBFactory.getValue(o, attr);
                                updates += ((i > 0) ? "," : "") + "" + col + " = '" + escapeQuote(value) + "'";
                                ++i;
                            }
			}
			else
				this.updateList((List<Object>)DBFactory.getTrueValue(o, attr),
						DBFacade.getInstance().getXML(conf.getColumnTable(attr)));
		}
		where = "WHERE " + conf.getIdCol() + " = '" + DBFactory.getValue(o, conf.getIdAttr()) + "'";
		if (i != 0)
                    this.processUpdate(update + " " + updates + " " + where);
		return (true);
	}
	
        
        //////////////////
	//DELETE
	/////////////////
	public boolean				deleteObject(Object o, XMLConfig conf)
		throws SQLException, IllegalAccessException, InvocationTargetException, NoSuchMethodException, OrmException
	{
		List<String>	abstrs = conf.getAllAbstract();
		String			delete;
		String			where;
	
		for (Iterator<String> it = abstrs.iterator(); it.hasNext();)
		{
			String temp			= it.next();
			XMLConfig tconf		= DBFacade.getInstance().getXML(temp);
			
			this.deleteObject(o, tconf);
		}
		delete = "DELETE FROM " + conf.getTable();
		where = "WHERE " + conf.getIdCol() + " = '" + DBFactory.getValue(o, conf.getIdAttr()) + "'";
		this.processUpdate(delete + " " + where);
		return (true);
	}
	
	private ResultSet			processQuery(String query) throws SQLException
	{
		ResultSet 			myResultSet;
		Statement			myState;
		
                System.out.println("ProcessQuery : " + query);
		myState = myConnect.createStatement();
		myResultSet = myState.executeQuery(DBManager.escapeQuote(query));
		return (myResultSet);
	}
	
	private int                  processUpdate(String order) throws SQLException
        {
            Statement			myState;
            ResultSet			res;

            System.out.println("ProcessUpdate : " + order);
            myState = myConnect.createStatement();
            myState.executeUpdate(order, Statement.RETURN_GENERATED_KEYS);
            res = myState.getGeneratedKeys();
            if (res != null && res.next())
                return (res.getInt(1));
            return (-1);
        }
	private void dbConnect()
	{
		try
		{	
			Class.forName("com.mysql.jdbc.Driver");
			String strConnectURL = "jdbc:mysql://localhost:3306/epimarket";
			myConnect = DriverManager.getConnection(strConnectURL, "root", "");//login & password
		
			//Option: Acces a un jeu de meta information sur la base avec laquelle on dialogue.
			myDbMetaData = myConnect.getMetaData();

			System.out.println("DbManager: dbConnect: show DataBase MetaData:");
			System.out.println("DbManager: dbConnect: productName=" 	+ myDbMetaData.getDatabaseProductName());
			System.out.println("DbManager: dbConnect: productVersion=" 	+ myDbMetaData.getDatabaseProductVersion());
			
			//4eme etape: creation d'une instruction/formule, socle pour executer des requetes
		}
		catch (Exception e)
                {
                    System.out.println("dbConnect Exception: " + e.toString());
                    //e.printStackTrace();
                   }
	}
	public void             release()
    {
        try {myConnect.close();}
        catch (Exception e) {System.out.println("DBManager destructor (dbConnection): " + e.toString());}
        System.out.println("SQL connection released.");
    }
	//////////////////
	//MISC
	/////////////////
	
	public static String escapeQuote(String strIn)
	{	
		if (strIn == null)
			return "";
		
		String strOut		= "";
		int strLength		= strIn.length();
				
		for (int i = 0; i != strLength; i++)
			strOut			+= (strIn.substring(i, i + 1).equalsIgnoreCase("'"))? "''" : strIn.substring(i, i + 1);
		
		return strOut;
	}	
	
	public DatabaseMetaData	getDbMetaData()
	{
		return (this.myDbMetaData);
	}
}