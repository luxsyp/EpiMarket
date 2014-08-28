package beans;

import ORM.BuisnessFacade;
import ORM.model.AbstractProduct;
import ORM.model.OrderLine;
import ORM.criteria.*;
import java.util.Iterator;
import java.util.List;

import utils.KeyRepository;
import javax.faces.context.FacesContext;
import javax.faces.application.FacesMessage;
        
public class ProductBuyBean
{
	//Injected references
	private BuisnessFacade 			productBO;
        private UserBean                        userBean;
	private KeyRepository 			keyRepository;
	
	//Session Associated references
	private AbstractProduct			currentProduct;
	
	public ProductBuyBean()
	{
		
	}
        
        public String   viewProduct()
        {
            FacesContext context = FacesContext.getCurrentInstance();
            Object value = context.getExternalContext().getRequestParameterMap().get("id");
            ICriteria       crit = new Criteria("idabstract", new CEqual(value.toString()));
            
            List<AbstractProduct> temp = this.productBO.find("AbstractProduct", crit);
            if (temp != null && temp.size() == 1)
                this.currentProduct = temp.get(0);
            return "visu";
        }
	public void buyProduct()
	{
            FacesMessage        msg;
            OrderLine           line = new OrderLine();
            List<OrderLine>     lines;
            
            line.setProduct(currentProduct);
            line.setQuantity(1);
            lines = this.userBean.getCaddieBean().getCaddie().getLines();
            for (Iterator<OrderLine> it = lines.iterator(); it.hasNext();)
            {
                OrderLine       t = it.next();
                if (t != null && t.getProduct().getId() == this.currentProduct.getId())
                {
                    msg = new FacesMessage(keyRepository.getResourceBundle().getString("visu.ajoutko"));
                    FacesContext context = FacesContext.getCurrentInstance();
                    context.addMessage(null, msg);
                    return ;
                }
            }
            msg = new FacesMessage(keyRepository.getResourceBundle().getString("visu.ajoutok"));
            this.userBean.getCaddieBean().getCaddie().addLine(line);
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, msg);
	}
        
	public BuisnessFacade getProductBO()						{return productBO;}
	public AbstractProduct getCurrentProduct()                                      {return currentProduct;}
	public KeyRepository getKeyRepository()						{return keyRepository;}
        public UserBean getUserBean()                                                   {return userBean;}

        public void setUserBean(UserBean userBean)                                      { this.userBean = userBean;}
	public void setProductBO(BuisnessFacade productBO)				{this.productBO 			= productBO;}
	public void setCurrentProduct(AbstractProduct currentProduct)			{this.currentProduct 			= currentProduct;}
	public void setKeyRepository(KeyRepository keyRepository)			{this.keyRepository 			= keyRepository;}

}
