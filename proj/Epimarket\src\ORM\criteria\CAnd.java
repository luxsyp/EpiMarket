package ORM.criteria;

import java.util.List;

public class CAnd extends ACriteriaCond {
	public CAnd(List<ICriteria> crits)
	{
		super("AND", crits);
	}
}
