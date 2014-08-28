package ORM.model;


public class Dvd extends AbstractProduct
{
	//furthermore members (fields, methods) to be added...
	
	
	public Dvd() {}	

	/** Constructor overloading:  */
	public Dvd(String name, String designation, Category category, int price)
	{
		createStructure(name, designation, category, price);	
	}
        
        public String getType()        { return "Dvd";}
}