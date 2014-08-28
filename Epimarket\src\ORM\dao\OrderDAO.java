package ORM.dao;

import java.util.Iterator;
import java.util.List;
import java.util.ArrayList;

import ORM.criteria.ICriteria;
import ORM.model.Order;

public class OrderDAO extends ADAO
{
	public OrderDAO(String file)
	{
		super(file);
	}
	
        public OrderDAO()
	{
		super("src/ORM/dao/Order.xml");
	}
	public List<Order>	find(ICriteria crit)
	{
		List<Object>	temp = super.getAll(crit);
		List<Order>		Orders = new ArrayList<Order>(0);
		Iterator<Object>	it = temp.iterator();
		
		while (it.hasNext())
			Orders.add((Order)it.next());
		return (Orders);
	}
}
