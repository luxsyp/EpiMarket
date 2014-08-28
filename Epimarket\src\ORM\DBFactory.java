package ORM;

import java.util.List;
import java.util.Iterator;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.lang.reflect.InvocationTargetException;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.PropertyUtils;

import ORM.criteria.*;
import ORM.dao.XMLConfig;
import org.apache.commons.beanutils.NestedNullException;


public class DBFactory {
	public DBFactory()
	{
	}
	
	static public Object	instanceObject(ResultSet resultSet, XMLConfig conf)
		throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException, InvocationTargetException, NoSuchMethodException, OrmException
	{
		ResultSetMetaData resultMetadata = resultSet.getMetaData();		
		Class<?> c = DBFactory.getClass(resultMetadata.getTableName(1));
        Object targetObject = c.newInstance();
        List<String>	attrs = conf.getAllAttr();

        for (Iterator<String> i = attrs.iterator();
           	i.hasNext();)
        {
           	String 	attr = (String)i.next();
            String	col = conf.getColumnAttribute(attr);
            String 	type = conf.getTypeAttr(attr);
            Object	value = null;

            //System.out.println("get attr : " + attr + " - from col : "  + col);
            if (type != null)
            {
            	XMLConfig		temp = DBFacade.getInstance().getXML(conf.getColumnTable(attr));
            	List<Object>	obj = DBFacade.getInstance().getAll(new Criteria(conf.getDesignationAttr(attr), new CEqual(resultSet.getString(col))), temp);

            	//System.out.println(attr + " check mult");
            	if (conf.getAttrMult(attr))
            	{
            		//System.out.println("MULTIPLE");
            		value = obj;
            	}
            	else
            		value = obj.iterator().next();
            }
            else
               	value = resultSet.getObject(col);
            //System.out.println("value : " + value);
            DBFactory.setValue(targetObject, attr, value);
        }
        DBFactory.setValue(targetObject, conf.getIdAttr(), resultSet.getObject(conf.getIdCol()));
		return (targetObject);
	}
	
	
	static public Object	completeObject(Object targetObject, ResultSet resultSet, XMLConfig conf)
		throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException, InvocationTargetException, NoSuchMethodException, OrmException
	{
	    List<String>	attrs = conf.getAllAttr();
	
	    for (Iterator<String> i = attrs.iterator();
	       	i.hasNext();)
	    {
	       	String 	attr = (String)i.next();
	        String	col = conf.getColumnAttribute(attr);
	        String 	type = conf.getTypeAttr(attr);
	        Object	value = null;
	
	        if (type != null)
	        {
	        	XMLConfig		temp = DBFacade.getInstance().getXML(conf.getColumnTable(attr));
	        	List<Object>	obj = DBFacade.getInstance().getAll(new Criteria(conf.getDesignationAttr(attr), new CEqual(resultSet.getString(col))), temp);
	
	        	if (conf.getAttrMult(attr))
                            value = obj;
                        else if (obj.size() >= 1)
                            value = obj.iterator().next();
	        }
	        else
	           	value = resultSet.getObject(col);
	        //System.out.println("value : " + value);
	        DBFactory.setValue(targetObject, attr, value);
	    }
	    DBFactory.setValue(targetObject, conf.getIdAttr(), resultSet.getObject(conf.getIdCol()));
		return (targetObject);
	}
	
	static public Class<?>		getClass(String className)
		throws ClassNotFoundException
	{
		String	prefixe = "ORM.model.";
		
		className = Character.toUpperCase(className.charAt(0)) + className.substring(1);
		Class<?> myClass;
		myClass = Class.forName(prefixe + className);
		return (myClass);
	}
	
	static public void			setValue(Object o, String property, Object value)
		throws IllegalAccessException, InvocationTargetException
	{
		BeanUtils.setProperty(o, property, value);
	}
	static public Object		getTrueValue(Object o, String property)
                throws IllegalAccessException, NoSuchMethodException, InvocationTargetException
	{
            try {
                return (PropertyUtils.getProperty(o, property));
            } catch (NestedNullException ex) {
                return (null);
            }
	}
	
	static public Object		getValue(Object o, String property)
		throws IllegalAccessException, InvocationTargetException, NoSuchMethodException
	{
		return (BeanUtils.getProperty(o, property));
	}
}
