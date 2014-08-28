package ORM.dao;

import java.util.Iterator;
import java.util.List;
import java.util.ArrayList;

import ORM.criteria.ICriteria;
import ORM.model.Address;

public class AddressDAO extends ADAO
{
	public AddressDAO(String file)
	{
		super(file);
	}
	
        public AddressDAO()
	{
		super("src/ORM/dao/AddressDAO.xml");
	}
        
	public List<Address>	find(ICriteria crit)
	{
		List<Object>		temp = super.getAll(crit);
		List<Address>		address = new ArrayList<Address>(0);
		Iterator<Object>	it = temp.iterator();
		
		while (it.hasNext())
			address.add((Address)it.next());
		return (address);
	}
}
