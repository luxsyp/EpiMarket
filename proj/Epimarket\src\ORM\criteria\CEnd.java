package ORM.criteria;

public class CEnd extends ACriteriaOP
{
	public CEnd(String value)
	{
		super("LIKE", value + "%");
	}
}
