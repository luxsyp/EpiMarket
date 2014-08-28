package ORM.model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import ORM.model.state.EOrderState;
import ORM.model.state.IStateChangeable;
import ORM.model.validation.ValidationException;
import utils.DateHelper;

import java.sql.Date;

/**
 * A Simple order entity providing:<BR> 
 * 
 * - java.util.Date properties, for usual Order business tracking<BR> 
 * - One reference to a Client instance (multiplicity= 1 to 1)<BR>
 * - A list of OrderLine instances (multiplicity= 1 to n)<BR>
 * - A Reference to EOrderState which symbolizes the determined states the Order is moving through, during its LifeCycle.<BR>
 * (Check EOrderState enum for more informations).
 * 
 * <P>
 * 
 * Please not: if you have a look at the OrderLine class, 
 * you'll see the relation between those two entities is uni-directionnal,
 * the OrderLine structure doesn't reference its Order, 
 * because in our "business case", 
 * we basically consider that an OrderLine is never manipulated on its own, 
 * but always through its inherent Order referencing class.<BR>
 * 
 * ->Order is a root node, on an object tree, and 
 * OrderLine is hereby considered as its sibling. 
 * 
 * @author Dim
 */
public class Order implements IStateChangeable
{
	/** The client related to the Order */
	private Client 					client;
	
	/** The object is created, in order to manipulate a not-null reference */
	private List<OrderLine> 		lines 		= new ArrayList<OrderLine>(0);
	
	/** The State (design Pattern) of the current Order instance */
	private EOrderState 			state;
	
        /** Dates to track the order evolution */
        private Address                                 address;
	private Date					dateOrderStarted;
	private Date					dateOrderEnded;
	
	private int						id;
	
	
	/** Class simple constructor (mandatory) */
	public Order()
	{
		dateOrderStarted = new Date(0);
		dateOrderEnded = new Date(0);
		lines = new ArrayList<OrderLine>(0);
		state = EOrderState.TRANSACTION_STARTED;
	}

	/** This method is a "derivated property", 
	 * meaning it is a member not related to a Field, 
	 * but to an internal use of one, or many, fields. */
	public int getTotal()
	{
		int total = 0;
		
		for (Iterator<OrderLine> i = lines.iterator(); i.hasNext(); )
		{
			OrderLine ol = i.next(); 
			
			total += ol.getQuantity() * ol.getProduct().getPrice();
		}
	
		return total;
	}
        
        public int getQuantity()
	{
		int total = 0;
		
		for (Iterator<OrderLine> i = lines.iterator(); i.hasNext(); )
		{
			OrderLine ol = i.next(); 
			
			total += ol.getQuantity();
		}
	
		return total;
	}

	/**
	 * This method OVERRIDES the java.lang.Object toString() method: 
	 * because the original one is only giving in return an instance className + its @ddress in the VM memory,
	 * which is not really "log-friendly" :) we re-defined its functionnal behaviour, with our own implementation.<P>
	 * 
	 * This method is using the DateHelper class to format the stored dates.<P>
	 * 
	 * Please Note: this method is chaining the appending of an Order String declaration, 
	 * by calling its references member (Client, List<OrderLines>) similar toString() overriding.
	 * This "chaining" is (very simply) illustrating the "Composite" design pattern, 
	 * which is structuring a Java classes Tree.
	 */
	public String toString()
	{
		StringBuilder sb = new StringBuilder();

		sb.append("Order: \r\n\tState=" 	+ state.toString() 
				+ ((dateOrderStarted != null)? 	", dateOrderStarted=" + DateHelper.getStringDate(dateOrderStarted)	: ", dateOrderStarted = null") 
				+ ((dateOrderEnded != null)? 	", dateOrderEnded=" + DateHelper.getStringDate(dateOrderEnded)	: ", dateOrderEnded = null"));

		sb.append("\r\n");

		//Ternary operator use
		sb.append("\r\n\tClient: " + ((client != null)? client.toString() : "null"));

		sb.append("\r\n");

		//Please Note: the simplified grammatical definition of a "for"
		for (OrderLine i : lines)
			sb.append("\r\n\t" + i.toString());

		return sb.toString();
	}
	
	////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//IValidable interface implementation
	////////////////////////////////////////////////////////////////////////////////////////////////////////////
		
	public void validate() throws ValidationException
	{
		if  (client == null)
			throw new ValidationException("Order instance: client IS NULL !!!");
		
		for (Iterator<OrderLine> i = lines.iterator(); i.hasNext(); )
		{
			OrderLine ol = i.next(); 
			
			if  ((ol.getProduct() == null) || (ol.getQuantity() == null))
				throw new ValidationException("Order line instance: instance not consistent, inner product reference, and/or quantity IS NULL !!!");
		}
	}
	
	////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//List common handling
	////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	public void addLine(OrderLine l)						{lines.add(l);}
        public void delLine(OrderLine l)						{lines.remove(l);}
	
	//////////////////////////////////////////////////////////////////////////////////////////////////////
	//Getters & Setters
	//////////////////////////////////////////////////////////////////////////////////////////////////////
	
        public boolean isEmptycaddie()                                                  {return this.lines.isEmpty();}
        
	public Client getClient()								{return client;}
        public Address getAddress()								{return address;}
	public List<OrderLine> getLines()						{return lines;}
	public EOrderState getState()							{return state;}
	public Date getDateOrderStarted()						{return dateOrderStarted;}
	public Date getDateOrderEnded()							{return dateOrderEnded;}
	public int getId()										{return id;}
        
	public void setClient(Client client)								{this.client 			= client;}
	public void setLines(List<OrderLine> lines)							{this.lines 			= lines;}
        public void setAddress(Address address)							{this.address 			= address;}
	public void setState(EOrderState state)								{this.state 			= state;}
	public void setDateOrderStarted(Date dateOrderStarted)				{this.dateOrderStarted 	= dateOrderStarted;}
	public void setDateOrderEnded(Date dateOrderEnded)					{this.dateOrderEnded 	= dateOrderEnded;}
	public void setDateOrderStarted(java.util.Date dateOrderStarted)	{this.dateOrderStarted.setTime(dateOrderStarted.getTime());}
	public void setDateOrderEnded(java.util.Date dateOrderEnded)		{this.dateOrderEnded.setTime(dateOrderEnded.getTime());}
	public void setId(int id)											{this.id 	= id;}
}
