/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package business;

import ORM.dao.DvdDAO;
import ORM.model.Dvd;
import java.util.List;

/**
 *
 * @author locque_d
 */
public class DvdBO
{
    private DvdDAO dvdDAO;
    
    public List<Dvd>    find()
    {
       return (this.dvdDAO.find(null)); 
    }
    public boolean                  save(Dvd o)
    {
        this.dvdDAO.save(o);
        return (true);
    }
    public boolean                  update(Dvd o)
    {
        this.dvdDAO.update(o);
        return (true);
    }
    public boolean                  delete(Dvd o)
    {
        this.dvdDAO.delete(o);
        return (true);
    }

    public DvdDAO getDvdDAO() {
        return dvdDAO;
    }

    public void setDvdDAO(DvdDAO dvdDAO) {
        this.dvdDAO = dvdDAO;
    }

    
    
}
