package ORM.dao;

import java.util.Iterator;
import java.util.List;
import java.util.ArrayList;

import ORM.criteria.ICriteria;
import ORM.model.Category;

public class CategoryDAO extends ADAO
{
	public CategoryDAO(String file)
	{
		super(file);
	}
	
        public CategoryDAO()
	{
		super("src/ORM/dao/Category.xml");
	}
        
	public List<Category>	find(ICriteria crit)
	{
		List<Object>		temp = super.getAll(crit);
		List<Category>		Categorys = new ArrayList<Category>(0);
		Iterator<Object>	it = temp.iterator();
		
		while (it.hasNext())
			Categorys.add((Category)it.next());
		return (Categorys);
	}
}
