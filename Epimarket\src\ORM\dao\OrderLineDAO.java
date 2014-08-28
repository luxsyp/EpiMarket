package ORM.dao;

import java.util.Iterator;
import java.util.List;
import java.util.ArrayList;

import ORM.criteria.ICriteria;
import ORM.model.OrderLine;

public class OrderLineDAO extends ADAO
{
	public OrderLineDAO(String file)
	{
		super(file);
	}
	public OrderLineDAO()
	{
		super("src/ORM/dao/OrderLine.xml");
	}
	public List<OrderLine>	find(ICriteria crit)
	{
		List<Object>	temp = super.getAll(crit);
		List<OrderLine>		Orders = new ArrayList<OrderLine>(0);
		Iterator<Object>	it = temp.iterator();
		
		while (it.hasNext())
			Orders.add((OrderLine)it.next());
		return (Orders);
	}
}
