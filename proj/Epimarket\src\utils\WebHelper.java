package utils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ResourceBundle;

import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

import org.apache.commons.beanutils.BeanUtils;



public class WebHelper
{
	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//Message
	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	//{Utils.addMessage(e.toString(), new String[]{e.getMessage()}, FacesMessage.SEVERITY_ERROR);}
	//WebHelper.addMessage("reply type update OK", "The row has been updated", FacesMessage.SEVERITY_INFO);
	public static void addMessage(String detail, String summary, Severity severity)
	{
		FacesMessage fm = new FacesMessage();
		
		fm.setDetail(detail);
		fm.setSummary(summary);
		fm.setSeverity(severity);
		
		FacesContext.getCurrentInstance().addMessage(null, fm);
	}

	public static void addMessageFromBundle(ResourceBundle bundle, String key, String summary, Severity severity)
	{
		//UIViewRoot viewRoot		= context.getViewRoot();
		FacesMessage fm 		= new FacesMessage();
		
		fm.setDetail(summary);
		fm.setSummary(bundle.getString(key));
		fm.setSeverity(severity);
		
		FacesContext.getCurrentInstance().addMessage(null, fm);
	}
	
	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//SelectItem wrapping
	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	//To wrap String arrays 	
    public static List wrapItemsWithLabel(Collection collection)
    {
        List<SelectItem> listItems          	= new ArrayList<SelectItem>();
        
        if (collection != null)
        {
            int index                           = 1;
            
            for (Iterator i = collection.iterator(); i.hasNext(); index++)
            {
            	Object o = i.next();
            	
            	if ((o != null) && (o.toString() != null))
            		listItems.add(new SelectItem(o.toString(), o.toString()));
            }
        }

        return listItems;
    }

    public static List wrapItemsWithLabelAndIndex(Collection collection)
    {
        List<SelectItem> listItems          	= new ArrayList<SelectItem>();
        
        if (collection != null)
        {
            int index                           = 1;
            
            for (Iterator i = collection.iterator(); i.hasNext(); index++)
            {
            	Object o 						= i.next();
            	
            	if ((o != null) && (o.toString() != null))
            		listItems.add(new SelectItem(index + "", o.toString()));
            }
        }

        return listItems;
    }

    public static List wrapItemsWithLabelAndIndexBlankFirst(Collection collection)
    {
        List<SelectItem> listItems          	= new ArrayList<SelectItem>();
        
        listItems.add(new SelectItem("", ""));
       
        if (collection != null)
        {
            int index                           = 1;
            
            for (Iterator i = collection.iterator(); i.hasNext(); index++)
            {
            	Object o 						= i.next();
            	
            	if ((o != null) && (o.toString() != null))
            		listItems.add(new SelectItem(index + "", o.toString()));
            }
        }

        return listItems;
    }

    //To wrap String arrays 
    public static List wrapAsSelectItems(Collection collection, String strIdPropertyName, String strLabelPropertyName)
    {
    	List<SelectItem> listItems              = new ArrayList<SelectItem>();
    	
    	if (collection != null)
    	{
    		for (Iterator i = collection.iterator(); i.hasNext(); )
    		{
    			Object o                        = i.next();
    			
    			try {listItems.add(new SelectItem(BeanUtils.getProperty(o, strIdPropertyName).toString(), BeanUtils.getProperty(o, strLabelPropertyName).toString()));}
    			catch (Exception e) {System.out.println("WebHelper: wrapAsSelectItems: " + e.toString() + ", for " + o.toString());}
    		}
    	}
    	
    	return listItems;
    }

    
}

