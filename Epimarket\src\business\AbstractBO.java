/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package business;

import ORM.dao.AbstractDAO;
import ORM.model.AbstractProduct;
import java.util.List;

/**
 *
 * @author locque_d
 */
public class AbstractBO
{
    private AbstractDAO abstractDAO;
    
    public List<AbstractProduct>    find()
    {
       return (this.abstractDAO.find(null)); 
    }
    public boolean                  save(AbstractProduct o)
    {
        this.abstractDAO.save(o);
        return (true);
    }
    public boolean                  update(AbstractProduct o)
    {
        this.abstractDAO.update(o);
        return (true);
    }
    public boolean                  delete(AbstractProduct o)
    {
        this.abstractDAO.delete(o);
        return (true);
    }

    public AbstractDAO getAbstractDAO() {
        return abstractDAO;
    }

    public void setAbstractDAO(AbstractDAO abstractDAO) {
        this.abstractDAO = abstractDAO;
    }

    
}
