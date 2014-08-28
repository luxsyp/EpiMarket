package ORM.dao;

import java.util.Iterator;
import java.util.List;
import java.util.ArrayList;

import ORM.criteria.ICriteria;
import ORM.model.Dvd;

public class DvdDAO extends ADAO
{
	public DvdDAO(String file)
	{
		super(file);
	}
        public DvdDAO()
	{
		super("src/ORM/dao/Dvd.xml");
	}	
	public List<Dvd>	find(ICriteria crit)
	{
		List<Object>	temp = super.getAll(crit);
		List<Dvd>		dvds = new ArrayList<Dvd>(0);
		Iterator<Object>	it = temp.iterator();
		
		while (it.hasNext())
			dvds.add((Dvd)it.next());
		return (dvds);
	}
}
