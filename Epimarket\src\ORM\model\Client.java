package ORM.model;

/**
 * A Client structure definition, with two addresses, one for billing, one for delivery (references can obviously be equals :)
 * 
 * 
 * @author Dim
 */
public class Client
{
	private Address		billingAddress = new Address();
	private Address		deliveryAddress = new Address();
	
	private String		firstName;
	
	private String		lastName;
        private String		login;
        private String          mail;
        private String          password;
	
	private int		id;

	
	
	public Client()
	{
            this.firstName = "";
            this.lastName = "";
            this.login = "";
            this.mail = "";
            this.password = "";
	}

	//usual toString overRiding
	public String toString()
	{
		return "Client instance: firstName=" 	+ firstName + ", login=" + login + ", lastName=" + lastName 
												+ "\r\n\t\tbillingAddress: " 	+ ((billingAddress != null)? 	billingAddress.toString() 	: "null") 
		 										+ "\r\n\t\tdeliveryAddress: " 	+ ((deliveryAddress != null)? 	deliveryAddress.toString() : "null");
	}

	//////////////////////////////////////////////////////////////////////////////////////////////////////
	//Getters & Setters
	//////////////////////////////////////////////////////////////////////////////////////////////////////

	public Address getBillingAddress()						{return billingAddress;}
	public Address getDeliveryAddress()						{return deliveryAddress;}
	public String getFirstName()							{return firstName;}
        public String getLogin() { return login;}
        public String getMail() { return mail; }
        public String getPassword() { return password; }
	public String getLastName()								{return lastName;}
	public int getId()										{return id;}

	public void setBillingAddress(Address billingAddress)	{this.billingAddress 	= billingAddress;}
	public void setDeliveryAddress(Address deliveryAddress)	{this.deliveryAddress 	= deliveryAddress;}
	public void setFirstName(String firstName)				{this.firstName 		= firstName;}
	public void setLastName(String lastName)				{this.lastName 			= lastName;}
        public void setLogin(String login) { this.login = login; }
        public void setMail(String mail) { this.mail = mail; }
        public void setPassword(String password) { this.password = password; }
	public void setId(int newIdValue)						{this.id	= newIdValue;}

}
