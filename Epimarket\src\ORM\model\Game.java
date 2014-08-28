package ORM.model;



public class Game extends AbstractProduct
{	
	//furthermore members (fields, methods) to be added...
	
	
	public Game() {}	
	
	/** Constructor overloading:  */
	public Game(String name, String designation, Category category, int price)
	{
		createStructure(name, designation, category, price);	
	}

	////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//Business logic: "behaviour" defined by the IBonusProducer business interface 
	////////////////////////////////////////////////////////////////////////////////////////////////////////////

	public int getBonusPoint()
	{
		return 20;	
	}
        
        public String getType()        { return "Game";}

	
}