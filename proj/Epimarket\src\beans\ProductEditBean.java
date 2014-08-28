package beans;


import ORM.BuisnessFacade;
import ORM.model.AbstractProduct;
import utils.KeyRepository;
import utils.WebHelper;
import javax.faces.application.FacesMessage;
        
public class ProductEditBean
{
	//Injected references
	private BuisnessFacade 			productBO;
	private KeyRepository 			keyRepository;
	
	//Session Associated references
	private AbstractProduct			currentProduct;
	
	private boolean 			stateDisplayValueOnly = true;
	
	
	public ProductEditBean()
	{
		
	}
	
	public void editProduct()
	{
		stateDisplayValueOnly = false;
	}
	
	public void saveProduct()
	{
                this.productBO.update(this.currentProduct);
		stateDisplayValueOnly = true;
		
		//ici on doit normalement invoquer l'enregistrement de l'objet par le BO
		
		WebHelper.addMessageFromBundle(keyRepository.getResourceBundle(), "message.save.ok", "Enregistrement OK", FacesMessage.SEVERITY_INFO);
	}
	
	public BuisnessFacade getProductBO()						{return productBO;}
	public AbstractProduct getCurrentProduct()						{return currentProduct;}
	public boolean isStateDisplayValueOnly()					{return stateDisplayValueOnly;}
	public KeyRepository getKeyRepository()						{return keyRepository;}

	public void setProductBO(BuisnessFacade productBO)				{this.productBO 				= productBO;}
	public void setCurrentProduct(AbstractProduct currentProduct)				{this.currentProduct 			= currentProduct;}
	public void setStateDisplayValueOnly(boolean stateDisplayValueOnly)		{this.stateDisplayValueOnly 	= stateDisplayValueOnly;}
	public void setKeyRepository(KeyRepository keyRepository)			{this.keyRepository 			= keyRepository;}

}
