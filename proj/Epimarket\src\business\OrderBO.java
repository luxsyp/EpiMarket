/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package business;

import ORM.dao.OrderDAO;
import ORM.model.Order;
import java.util.List;

/**
 *
 * @author locque_d
 */
public class OrderBO
{
    private OrderDAO orderDAO;
    
    public List<Order>    find()
    {
       return (this.orderDAO.find(null)); 
    }
    public boolean                  save(Order o)
    {
        this.orderDAO.save(o);
        return (true);
    }
    public boolean                  update(Order o)
    {
        this.orderDAO.update(o);
        return (true);
    }
    public boolean                  delete(Order o)
    {
        this.orderDAO.delete(o);
        return (true);
    }

    public OrderDAO getOrderDAO() {
        return orderDAO;
    }

    public void setOrderDAO(OrderDAO orderDAO) {
        this.orderDAO = orderDAO;
    }
    
    
}
