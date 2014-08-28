
package beans;

import ORM.BuisnessFacade;
import ORM.model.AbstractProduct;

import ORM.model.Dvd;
import ORM.model.Game;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import utils.KeyRepository;

import javax.faces.component.UIInput;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.application.FacesMessage;

public class ProductAddBean {
    private BuisnessFacade      productBO;
    private Pattern             pattern = null;
    private Matcher             matcher = null;

    private AbstractProduct     product;
    private KeyRepository       keyRepository;
    
    private String              type;

    public String           addProduct()
    {
        this.productBO.save(this.product);
        return "admin";
    }
    
    public String         goAddProduct()
    {
        type = "Dvd";
        product = new Dvd();
        return "addproduct";
    }
    
    public String         addGame()
    {
        type = "Game";
        product = new Game();
        return "";
    }
    
    public String         addDvd()
    {
        type = "Dvd";
        product = new Dvd();
        return "";
    }
    
    public ArrayList      getAllCategory()
    {
        return ((ArrayList)this.productBO.find("Category"));
    }
    
  public void validateName(FacesContext context, UIComponent validate, Object value){
        String val = (String)value;
        pattern = Pattern.compile(ConstRegex.IDENT_PATTERN);
        matcher = pattern.matcher(val);
        if (matcher.matches() != true || (val.length() < 3 || val.length() > 24))
        {
            ((UIInput)validate).setValid(false);
            FacesMessage msg = new FacesMessage(keyRepository.getResourceBundle().getString("errors.name"));
            context.addMessage(validate.getClientId(context), msg);
        }
     }

  public void validateDesignation(FacesContext context, UIComponent validate, Object value){
        String val = (String)value;
        pattern = Pattern.compile(ConstRegex.IDENT_PATTERN);
        matcher = pattern.matcher(val);
        if (matcher.matches() != true || (val.length() < 3 || val.length() > 256))
        {
            ((UIInput)validate).setValid(false);
            FacesMessage msg = new FacesMessage(keyRepository.getResourceBundle().getString("errors.designation"));
            context.addMessage(validate.getClientId(context), msg);
        }
     }

  public void validatePrice(FacesContext context, UIComponent validate, Object value){
        String val = value.toString();
        pattern = Pattern.compile(ConstRegex.PRICE_PATTERN);
        matcher = pattern.matcher(val);
        if (matcher.matches() != true || (val.length() < 1 || val.length() > 24))
        {
            ((UIInput)validate).setValid(false);
            FacesMessage msg = new FacesMessage(keyRepository.getResourceBundle().getString("errors.price"));
            context.addMessage(validate.getClientId(context), msg);
        }
     }
  
  public void validateEditPrice(FacesContext context, UIComponent validate, Object value){
        String val = value.toString();
        pattern = Pattern.compile(ConstRegex.PRICE_PATTERN);
        matcher = pattern.matcher(val);
        if (matcher.matches() != true || (val.length() < 1 || val.length() > 24))
        {
            ((UIInput)validate).setValid(false);
            FacesMessage msg = new FacesMessage(keyRepository.getResourceBundle().getString("errors.price"));
            context.addMessage(validate.getClientId(context), msg);
        }
     }

  public void validateCategory(FacesContext context, UIComponent validate, Object value){
        String val = (String)value;
        pattern = Pattern.compile(ConstRegex.IDENT_PATTERN);
        matcher = pattern.matcher(val);
        if (matcher.matches() != true || (val.length() < 3 || val.length() > 24))
        {
            ((UIInput)validate).setValid(false);
            FacesMessage msg = new FacesMessage(keyRepository.getResourceBundle().getString("errors.category"));
            context.addMessage(validate.getClientId(context), msg);
        }
     }
  
    public String getType()                                       { return type;}
    public BuisnessFacade getProductBO()                        {return productBO;}
    public AbstractProduct getProduct()                         { return product;                       }
    public KeyRepository getKeyRepository()                     { return keyRepository;                 }
    
    public void setType(String type)                            { this.type = type;}
    public void setProductBO(BuisnessFacade productBO)          {this.productBO 		= productBO;}
    public void setProduct(AbstractProduct product)             { this.product = product;               }
    public void setKeyRepository(KeyRepository keyRepository)   { this.keyRepository = keyRepository;   }
}
