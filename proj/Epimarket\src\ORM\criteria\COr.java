package ORM.criteria;

import java.util.List;

public class COr extends ACriteriaCond {
	public COr(List<ICriteria> crits)
	{
		super("OR", crits);
	}
}