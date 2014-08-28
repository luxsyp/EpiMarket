package ORM.model;


/**
 * A Simple order entity providing:<BR> 
 * 
 * - Litteral properties, for usual OrderLines fields
 * - A reference to a Product, associated to the quantity ordered.
 */  
public class OrderLine
{
	private AbstractProduct 	product;
	private Integer				quantity;
	
	private int					id;
	private int					orderId;
	
	
	public OrderLine()	{}

	public OrderLine(AbstractProduct product, int quantity)
	{
		setProduct(product);
		setQuantity(quantity);
	}

	public String toString()
	{
		return "OrderLine instance: quantity=" + quantity + "\r\n\t\tassociated Product=" + ((product != null)? product.toString() : "null");
	}

	//////////////////////////////////////////////////////////////////////////////////////////////////////
	//Getters & Setters
	//////////////////////////////////////////////////////////////////////////////////////////////////////

	public AbstractProduct getProduct()					{return product;}
	public Integer getQuantity()						{return quantity;}
	public Integer getId()								{return id;}
	public Integer getOrderId()							{return orderId;}
        public Integer getPrice()                               { return (this.product.getPrice() * this.quantity);}
	
	public void setProduct(AbstractProduct product)		{this.product 	= product;}
	public void setQuantity(Integer quantity)			{this.quantity 	= quantity;}
	public void setId(Integer id)						{this.id 	= id;}
	public void setOrderId(Integer orderId)				{this.orderId 	= orderId;}

}
