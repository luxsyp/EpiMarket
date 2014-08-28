package ORM.dao;

import java.util.Iterator;
import java.util.List;
import java.util.ArrayList;

import ORM.criteria.ICriteria;
import ORM.model.Client;

public class ClientDAO extends ADAO
{
	public ClientDAO(String file)
	{
		super(file);
	}
	
        public ClientDAO()
	{
            super("src\\ORM\\dao\\Client.xml"); 
	}
	public List<Client>	find(ICriteria crit)
	{
		List<Object>	temp = super.getAll(crit);
		List<Client>		Clients = new ArrayList<Client>(0);
		Iterator<Object>	it = temp.iterator();
		
		while (it.hasNext())
			Clients.add((Client)it.next());
		return (Clients);
	}
}
