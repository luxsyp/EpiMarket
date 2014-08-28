package ORM.criteria;

public class CBegin extends ACriteriaOP {
	public CBegin(String value)
	{
		super("LIKE", "%" + value);
	}
}
