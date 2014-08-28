/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package business;

import ORM.dao.AddressDAO;
import ORM.model.Address;
import java.util.List;

/**
 *
 * @author locque_d
 */
public class AddressBO
{
    private AddressDAO addressDAO;

    
    public List<Address>    find()
    {
       return (this.addressDAO.find(null)); 
    }
    public boolean                  save(Address o)
    {
        this.addressDAO.save(o);
        return (true);
    }
    public boolean                  update(Address o)
    {
        this.addressDAO.update(o);
        return (true);
    }
    public boolean                  delete(Address o)
    {
        this.addressDAO.delete(o);
        return (true);
    }

    public AddressDAO getAddressDAO() {
        return addressDAO;
    }

    public void setAddressDAO(AddressDAO addressDAO) {
        this.addressDAO = addressDAO;
    }
    
    
}
