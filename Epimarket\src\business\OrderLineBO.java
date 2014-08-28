/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package business;

import ORM.dao.OrderLineDAO;
import ORM.model.OrderLine;
import java.util.List;

/**
 *
 * @author locque_d
 */
public class OrderLineBO
{
    private OrderLineDAO orderLineDAO;
    
    public List<OrderLine>    find()
    {
       return (this.orderLineDAO.find(null)); 
    }
    public boolean                  save(OrderLine o)
    {
        this.orderLineDAO.save(o);
        return (true);
    }
    public boolean                  update(OrderLine o)
    {
        this.orderLineDAO.update(o);
        return (true);
    }
    public boolean                  delete(OrderLine o)
    {
        this.orderLineDAO.delete(o);
        return (true);
    }

    public OrderLineDAO getOrderLineDAO() {
        return orderLineDAO;
    }

    public void setOrderLineDAO(OrderLineDAO orderLineDAO) {
        this.orderLineDAO = orderLineDAO;
    }

    
}
