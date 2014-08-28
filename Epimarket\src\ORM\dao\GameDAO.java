package ORM.dao;

import java.util.Iterator;
import java.util.List;
import java.util.ArrayList;

import ORM.criteria.ICriteria;
import ORM.model.Game;

public class GameDAO extends ADAO
{
	public GameDAO(String file)
	{
		super(file);
	}
        
	public GameDAO()
	{
		super("src/ORM/dao/Game.xml");
	}
	public List<Game>	find(ICriteria crit)
	{
		List<Object>	temp = super.getAll(crit);
		List<Game>		Games = new ArrayList<Game>(0);
		Iterator<Object>	it = temp.iterator();
		
		while (it.hasNext())
			Games.add((Game)it.next());
		return (Games);
	}
}
