package ORM.dao;

public class Entry
{
	private String 				id, designation, type;
	private Entry				column;
	public boolean				mult = false;
	
	
	public Entry(String id, String designation, String type)
	{
		createStructure(id, designation, type);	
	}
	
	public void createStructure(String id, String designation, String type)
	{
		this.id					= id;
		this.designation		= designation;		   
		this.type				= type;
	}
	
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//Getter/Setter
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	public String	getId()			{return this.id;}
	public String	getType()		{return this.type;}
	public String	getDesignation(){return this.designation;}
	public Entry 	getColumn()		{return this.column;}
	public boolean 	getMult()		{return this.mult;}
	
	public void		setId(String id)					{this.id = id;}
	public void		setType(String type)				{this.type = type;}
	public void		setDesignation(String designation)	{this.designation = designation;}
	public void		setTable(Entry e)					{this.column = e;}
	public void		setMult(boolean mult)				{this.mult = mult;}
}
