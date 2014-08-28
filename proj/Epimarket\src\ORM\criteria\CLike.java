package ORM.criteria;

public class CLike extends ACriteriaOP {
	public CLike(String value)
	{
		super("LIKE", "%" +  value + "%");
	}
}
