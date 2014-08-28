package beans;

import ORM.BuisnessFacade;
import ORM.model.Client;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.faces.component.UIInput;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.application.FacesMessage;

import ORM.criteria.*;

import java.util.List;
import utils.KeyRepository;


public class UserAddBean {
    
    private Pattern             pattern = null;
    private Matcher             matcher = null;
    private Client              user = new Client();
    private BuisnessFacade      userBO;
    private KeyRepository       keyRepository;
           
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
     
     public void validateLastname(FacesContext context, UIComponent validate, Object value){
        String val = (String)value;
        pattern = Pattern.compile(ConstRegex.IDENT_PATTERN);
        matcher = pattern.matcher(val);
        if (matcher.matches() != true || (val.length() < 3 || val.length() > 24))
        {
            ((UIInput)validate).setValid(false);
            FacesMessage msg = new FacesMessage(keyRepository.getResourceBundle().getString("errors.lastname"));
            context.addMessage(validate.getClientId(context), msg);
        }
     }

     public void validateLogin(FacesContext context, UIComponent validate, Object value){
        String val = (String)value;
        pattern = Pattern.compile(ConstRegex.IDENT_PATTERN);
        matcher = pattern.matcher(val);
        ICriteria   crit = new Criteria("login", new CEqual(val));
        
        List<Client> t = this.userBO.find("Client", crit);
        if (t != null && t.size() >= 1)
        {
            ((UIInput)validate).setValid(false);
            FacesMessage msg = new FacesMessage(keyRepository.getResourceBundle().getString("errors.logintake"));
            context.addMessage(validate.getClientId(context), msg);
        }
        else if (matcher.matches() != true || (val.length() < 3 || val.length() > 24))
        {
            ((UIInput)validate).setValid(false);
            FacesMessage msg = new FacesMessage(keyRepository.getResourceBundle().getString("errors.login"));
            context.addMessage(validate.getClientId(context), msg);
        }
     }
     
     public void validateMail(FacesContext context, UIComponent validate, Object value){        
        String val = (String)value;
        pattern = Pattern.compile(ConstRegex.EMAIL_PATTERN);;
        matcher = pattern.matcher(val);
        if (matcher.matches() != true)
        {
            ((UIInput)validate).setValid(false);
            FacesMessage msg = new FacesMessage(keyRepository.getResourceBundle().getString("errors.mail"));
            context.addMessage(validate.getClientId(context), msg);
        }
     }
     
     public void validatePwd(FacesContext context, UIComponent validate, Object value){
        String val = (String)value;
        if (val.length() < 6 || val.length() > 12)
        {
            ((UIInput)validate).setValid(false);
            FacesMessage msg = new FacesMessage(keyRepository.getResourceBundle().getString("errors.pwd"));
            context.addMessage(validate.getClientId(context), msg);
        }
     }
    
    public void validatePwd2(FacesContext context, UIComponent validate, Object value){
        String val = (String)value;
        UIInput  hic = (UIInput)validate.findComponent("subscrite:pwd");
        if (val.equals((String)hic.getValue()) != true)
        {
            ((UIInput)validate).setValid(false);
            FacesMessage msg = new FacesMessage(keyRepository.getResourceBundle().getString("errors.pwd2"));
            context.addMessage(validate.getClientId(context), msg);
        }
    }
    
    public String addUser()
    {
        userBO.save(user);
        return "transition";
    }

    public Client getUser()                         { return user;          }
    public BuisnessFacade getUserBO()               { return userBO;        }
    public void setUser(Client user)                { this.user = user;     }
    public void setUserBO(BuisnessFacade userBO)    { this.userBO = userBO; }
    public KeyRepository getKeyRepository()         { return keyRepository; }
    public void setKeyRepository(KeyRepository keyRepository) { this.keyRepository = keyRepository; }   
}
