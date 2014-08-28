package utils;

import java.util.ResourceBundle;
import javax.faces.context.FacesContext;

public class KeyRepository
{
	private ResourceBundle 					resourceBundle;

	public KeyRepository()
	{
		init();
	}

	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//Init local config
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	public void init()
	{	
		createResourceBundle();
	}

	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//Resource
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	public void createResourceBundle()
	{
		System.out.println("KeyRepository: createResourceBundle: enter !");
                FacesContext context = FacesContext.getCurrentInstance();
		resourceBundle = ResourceBundle.getBundle("message", context.getViewRoot().getLocale(), Thread.currentThread().getContextClassLoader());
	}

	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//Getters & Setters
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	public ResourceBundle getResourceBundle()			{ return resourceBundle;                }
	public void setResourceBundle(ResourceBundle resourceBundle)    { this.resourceBundle = resourceBundle; }

}
