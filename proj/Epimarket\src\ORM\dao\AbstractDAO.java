package ORM.dao;

import java.util.Iterator;
import java.util.List;
import java.util.ArrayList;

import ORM.criteria.ICriteria;
import ORM.model.AbstractProduct;

public class AbstractDAO extends ADAO
{
	public AbstractDAO(String file)
	{
		super(file);
	}
        
        public AbstractDAO()
	{
            super("src\\ORM\\dao\\Abstract.xml");
	}
	
	public List<AbstractProduct>	find(ICriteria crit)
	{
		List<Object>		temp = super.getAll(crit);
		List<AbstractProduct>	address = new ArrayList<AbstractProduct>(0);
		Iterator<Object>	it = temp.iterator();
		
		while (it.hasNext())
			address.add((AbstractProduct)it.next());
		return (address);
	}
}
