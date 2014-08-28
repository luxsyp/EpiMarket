package ORM;

import ORM.dao.*;
import ORM.criteria.ICriteria;

import java.util.List;
import java.util.Map;
import java.util.HashMap;

public class BuisnessFacade
{
	public BuisnessFacade()
	{
		this.daos = new HashMap<String, IDAO>();
		
		this.daos.put("ORM.model.AbstractProduct", new AbstractDAO("src/ORM/dao/Abstract.xml"));
		this.daos.put("ORM.model.Address", new AddressDAO("src/ORM/dao/Address.xml"));
		this.daos.put("ORM.model.Category", new CategoryDAO("src/ORM/dao/Category.xml"));
		this.daos.put("ORM.model.Client", new ClientDAO("src/ORM/dao/Client.xml"));
		this.daos.put("ORM.model.Dvd", new DvdDAO("src/ORM/dao/Dvd.xml"));
		this.daos.put("ORM.model.Game", new GameDAO("src/ORM/dao/Game.xml"));
		this.daos.put("ORM.model.Order", new OrderDAO("src/ORM/dao/Order.xml"));
		this.daos.put("ORM.model.OrderLine", new OrderLineDAO("src/ORM/dao/OrderLine.xml"));
	}
	
	public boolean	save(Object o)
	{
		IDAO	dao;
		
		if (o == null)
			return (false);
		dao = this.daos.get(o.getClass().getName());
		if (dao == null)
			return (false);
		dao.save(o);
		return (true);
	}
	
	public boolean	update(Object o)
	{
		IDAO	dao;
		
		if (o == null)
			return (false);
		dao = this.daos.get(o.getClass().getName());
		if (dao == null)
			return (false);
		dao.update(o);
		return (true);
	}
	
	public boolean	delete(Object o)
	{
		IDAO	dao;
		
		if (o == null)
			return (false);
		dao = this.daos.get(o.getClass().getName());
		if (dao == null)
			return (false);
		dao.delete(o);
		return (true);
	}
	
	@SuppressWarnings("rawtypes")
	public List	find(String entity, ICriteria crit)
	{
		IDAO	dao;
		
		dao = this.daos.get("ORM.model." + entity);
		if (dao == null)
                {
                    System.out.println("FAILED : " + "ORM.model." + entity);
			return (null);
                }
		return (dao.getAll(crit));
	}
	@SuppressWarnings("rawtypes")
	public List	find(String entity)
	{
		IDAO	dao;
		
		dao = this.daos.get("ORM.model." + entity);
		if (dao == null)
			return (null);
		return (dao.getAll(null));
	}
	private Map<String, IDAO>	daos;
                       
}
