
package beans;

import ORM.BuisnessFacade;
import ORM.model.Client;
import utils.KeyRepository;

import javax.faces.application.FacesMessage;
import utils.WebHelper;

public class UserEditBean {
    private BuisnessFacade          userBO;
    private KeyRepository           keyRepository;
    private Client                  currentUser;
    private boolean                 stateDisplayValueOnly = true;

    public UserEditBean() {}
    public void editUser() 
    {
        stateDisplayValueOnly = false; 
    }
    
    public void saveUser() 
    {
        this.userBO.update(this.currentUser);
        stateDisplayValueOnly = true;
        WebHelper.addMessageFromBundle(keyRepository.getResourceBundle(), "message.save.ok", "Enregistrement OK", FacesMessage.SEVERITY_INFO);
    }
    

    public BuisnessFacade getUserBO()                           { return userBO;                        }
    public Client getCurrentUser()                              { return currentUser;                   }
    public boolean isStateDisplayValueOnly()                    { return stateDisplayValueOnly;         }
    public KeyRepository getKeyRepository()                     { return keyRepository;                 }

    public void setUserBO(BuisnessFacade userBO)                { this.userBO = userBO; }    
    public void setCurrentUser(Client currentUser)              { this.currentUser = currentUser;       }
    public void setKeyRepository(KeyRepository keyRepository)   { this.keyRepository = keyRepository;   }
    public void setStateDisplayValueOnly(boolean stateDisplayValueOnly) { this.stateDisplayValueOnly = stateDisplayValueOnly;   }

}
