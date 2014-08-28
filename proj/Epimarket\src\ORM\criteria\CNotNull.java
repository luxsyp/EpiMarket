/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ORM.criteria;

import ORM.dao.XMLConfig;

/**
 *
 * @author Nico
 */
public class CNotNull extends ACriteriaOP {
    public CNotNull()
    {
        super("", "");
    }
    
    @Override
    public String	toString(XMLConfig conf)
    {
        return (" != NULL ");
    }
}
