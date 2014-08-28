package ORM.model;

public class Category
{
	private String name = "";
	
	private int		id;

	
	public Category()	{}

	public Category(String name)
	{
		setName(name);
	}
	
	//another alternative to construct the returned String
	public String toString()
	{
		return "Category instance, name= " + name;
	}

	//////////////////////////////////////////////////////////////////////////////////////////////////////
	//Getters & Setters
	//////////////////////////////////////////////////////////////////////////////////////////////////////

	public String getName()					{return name;}
	public int getId()						{return id;}
	
	public void setName(String name)		{this.name	= name;}
	public void setId(int newIdValue)		{this.id	= newIdValue;}
}
