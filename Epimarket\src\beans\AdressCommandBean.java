
package beans;

import ORM.BuisnessFacade;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import utils.KeyRepository;

import javax.faces.component.UIInput;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.application.FacesMessage;

public class AdressCommandBean {
      
    private Pattern             pattern = null;
    private Matcher             matcher = null;
    private BuisnessFacade      BF;
    private KeyRepository       keyRepository;
        

  public void validateNum(FacesContext context, UIComponent validate, Object value){
        String val = (String)value;
        pattern = Pattern.compile(ConstRegex.NUMERICAL_PATTERN);
        matcher = pattern.matcher(val);
        if (matcher.matches() != true || (val.length() < 1 || val.length() > 9))
        {
            ((UIInput)validate).setValid(false);
            FacesMessage msg = new FacesMessage(keyRepository.getResourceBundle().getString("errors.name"));
            context.addMessage(validate.getClientId(context), msg);
        }
     }
  
  public void validateRue(FacesContext context, UIComponent validate, Object value){
        String val = (String)value;
        pattern = Pattern.compile(ConstRegex.IDENT_PATTERN);
        matcher = pattern.matcher(val);
        if (matcher.matches() != true || (val.length() < 3 || val.length() > 256))
        {
            ((UIInput)validate).setValid(false);
            FacesMessage msg = new FacesMessage(keyRepository.getResourceBundle().getString("errors.name"));
            context.addMessage(validate.getClientId(context), msg);
        }
     }

  public void validateZip(FacesContext context, UIComponent validate, Object value){
        String val = (String)value;
        pattern = Pattern.compile(ConstRegex.NUMERICAL_PATTERN);
        matcher = pattern.matcher(val);
        if (matcher.matches() != true || (val.length() < 1 || val.length() > 5))
        {
            ((UIInput)validate).setValid(false);
            FacesMessage msg = new FacesMessage(keyRepository.getResourceBundle().getString("errors.name"));
            context.addMessage(validate.getClientId(context), msg);
        }
     }
  
    public void validateCity(FacesContext context, UIComponent validate, Object value){
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
  
    public void validateCountry(FacesContext context, UIComponent validate, Object value){
        String val = (String)value;
        pattern = Pattern.compile(ConstRegex.IDENT_PATTERN);
        matcher = pattern.matcher(val);
        if (matcher.matches() != true || (val.length() < 2 || val.length() > 24))
        {
            ((UIInput)validate).setValid(false);
            FacesMessage msg = new FacesMessage(keyRepository.getResourceBundle().getString("errors.name"));
            context.addMessage(validate.getClientId(context), msg);
        }
     }

    public BuisnessFacade getBF()                               { return BF;                            }
    public KeyRepository getKeyRepository()                     { return keyRepository;                 }
    public void setBF(BuisnessFacade BF)                        { this.BF = BF;                         }
    public void setKeyRepository(KeyRepository keyRepository)   { this.keyRepository = keyRepository;   }
}
