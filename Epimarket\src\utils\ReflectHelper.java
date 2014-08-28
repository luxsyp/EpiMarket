package utils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.apache.commons.beanutils.PropertyUtils;


public class ReflectHelper
{
    
	public static Map<Object, Collection> reflectMap(Collection collection2map, String strProperty)
    {	
    	//Map mapElt                                        = new TreeMap();
        Map mapElt                                          = new HashMap();
        
        try
        {   
            for (Iterator iteratorElt = collection2map.iterator(); iteratorElt.hasNext(); )
            {
                try
                {
                    Object elt                              = iteratorElt.next();     
                    Object keyValue                         = PropertyUtils.getProperty(elt, strProperty);
                    Collection collectionEntry              = (Collection) mapElt.get(keyValue);    

                    if (collectionEntry == null)
                    {
                        collectionEntry                     = new ArrayList(); 

                        collectionEntry.add(elt);   

                        mapElt.put(keyValue, collectionEntry);
                    }

                    else
                        collectionEntry.add(elt);
                }
                catch (Exception e) {System.out.println("ReflectHelper: reflectMap: " + e.toString());}
            }   
        }
        catch (Exception e) {System.out.println("ReflectHelper: reflectMap: " + e.toString());}

        return mapElt;          
    }

}
