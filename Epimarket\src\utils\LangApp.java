/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.util.Locale;
import javax.faces.context.FacesContext;

public class LangApp {
  private Locale locale = FacesContext.getCurrentInstance().getViewRoot().getLocale();
  private KeyRepository   keyRepository;
    
  public void activerFR() 
  {       
      FacesContext.getCurrentInstance().getViewRoot().setLocale(Locale.FRENCH);     
      locale = FacesContext.getCurrentInstance().getViewRoot().getLocale();
      keyRepository.createResourceBundle();
  }

  public void activerEN() 
  {
      FacesContext.getCurrentInstance().getViewRoot().setLocale(Locale.ENGLISH);
      locale = FacesContext.getCurrentInstance().getViewRoot().getLocale();
      keyRepository.createResourceBundle();
  }

    public KeyRepository getKeyRepository()                     { return keyRepository;                 }
    public void setKeyRepository(KeyRepository keyRepository)   { this.keyRepository = keyRepository;   }
    public Locale getLocale()                                   { return locale;                        }
    public void setLocale(Locale locale)                        { this.locale = locale;                 }
}