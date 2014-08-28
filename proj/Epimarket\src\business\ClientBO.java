/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package business;

import ORM.dao.ClientDAO;
import ORM.model.Client;
import java.util.List;

/**
 *
 * @author locque_d
 */
public class ClientBO
{
    private ClientDAO clientDAO;
    
    public List<Client>    find()
    {
       return (this.clientDAO.find(null)); 
    }
    public boolean                  save(Client o)
    {
        this.clientDAO.save(o);
        return (true);
    }
    public boolean                  update(Client o)
    {
        this.clientDAO.update(o);
        return (true);
    }
    public boolean                  delete(Client o)
    {
        this.clientDAO.delete(o);
        return (true);
    }

    public ClientDAO getClientDAO() {
        return clientDAO;
    }

    public void setClientDAO(ClientDAO clientDAO) {
        this.clientDAO = clientDAO;
    }
    
    
}
