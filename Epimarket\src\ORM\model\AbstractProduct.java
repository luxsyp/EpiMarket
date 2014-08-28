package ORM.model;





public abstract class AbstractProduct implements IBonusProducer
{
	private Category				category = new Category();
		
	private String 					name = "", designation = ""; 
	
	private int						price, id, idabstract;
	
	
	////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//Constructors
	////////////////////////////////////////////////////////////////////////////////////////////////////////////

	public AbstractProduct()		{}

	/** Constructor overloading:  */
	public AbstractProduct(String name, String designation, Category category, int price)
	{
		createStructure(name, designation, category, price);	
	}
	
	public void createStructure(String name, String designation, Category category, int price)
	{
		this.name				= name;
		this.designation		= designation;		   
		this.price				= price;	
		this.category			= category;
	}

	////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//Business logic: "behaviour" defined by the IBonusProducer business interface 
	////////////////////////////////////////////////////////////////////////////////////////////////////////////

	public int getBonusPoint()								{return 10;}
        public abstract String getType();
	
	////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//Getter and setter
	////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	public Category getCategory()							{return category;}
	public String getName()								{return name;}
	public String getDesignation()							{return designation;}
	public int getPrice()								{return price;}
	public int getId()								{return id;}
	public int getIdabstract()							{return idabstract;}
	
	public void setCategory(Category newCategory)                                   {this.category 			= newCategory;}
	public void setName(String newNameValue)                                        {this.name				= newNameValue;}
	public void setDesignation(String newDesignationValue)                          {this.designation		= newDesignationValue;}
	public void setPrice(int newPriceValue)                                         {this.price				= newPriceValue;}
	public void setId(int newIdValue)						{this.id				= newIdValue;}
	public void setIdabstract(int newIdAbstractValue)                               {this.idabstract				= newIdAbstractValue;}

	////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//Override java.lang.Object method 
	////////////////////////////////////////////////////////////////////////////////////////////////////////////

	public String toString()								
	{
		return "Product instance: id=" + id + ", designation=" + designation + ", price=" + price + ", getBonusPoint=" + getBonusPoint() + ";"
						+ 	"\r\n\t\t\t" + category.toString();
	}
		
}