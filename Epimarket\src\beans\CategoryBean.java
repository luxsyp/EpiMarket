/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import ORM.BuisnessFacade;
import ORM.model.Category;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import utils.KeyRepository;

/**
 *
 * @author Nico
 */
public class CategoryBean {
    private BuisnessFacade 			productBO;
    private KeyRepository 			keyRepository;
    private Category                            category;
    
    private Pattern                             pattern = null;
    private Matcher                             matcher = null;
    
    public String       goAddCategory()
    {
        category = new Category();
        return "addcategory";
    }
    
    public String       addCategory()
    {
        this.productBO.save(this.category);
        return "admin";
    }
    
    public List<Category>       getAllCategory()
    {
        return this.productBO.find("Category");
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
    
    public Category getCategory()                               {return category;}
    public BuisnessFacade getProductBO()                        {return productBO;}
    public KeyRepository getKeyRepository()                     {return keyRepository;}
    
    public void setCategory(Category category)                      {this.category = category;}
    public void setProductBO(BuisnessFacade productBO)              {this.productBO 		= productBO;}
    public void setKeyRepository(KeyRepository keyRepository)	{this.keyRepository 	= keyRepository;}
}
