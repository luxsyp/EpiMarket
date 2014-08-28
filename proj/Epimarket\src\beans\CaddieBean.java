/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import ORM.BuisnessFacade;
import ORM.model.Order;
import ORM.model.OrderLine;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.faces.component.html.HtmlDataTable;
import utils.KeyRepository;

/**
 *
 * @author Nico
 */
public class CaddieBean {
    private BuisnessFacade      caddieBO;
    private Order               caddie = new Order();
    
    private HtmlDataTable       dataTable;
    private KeyRepository       keyRepository;
    
    public CaddieBean()
    {
    }
    
    public String   deleteProduct()
    {
        caddie.delLine((OrderLine)dataTable.getRowData()); 
        return "";
    }
    
    public List<OrderLine>  getAllProduct()
    {
        return (caddie.getLines());
    }
    
    public String   finalizeCaddie()
    {
        Calendar cal = Calendar.getInstance();

        this.caddie.setDateOrderEnded(new Date(cal.getTimeInMillis()));
        this.caddie.setDateOrderStarted(new Date(cal.getTimeInMillis()));
        this.caddieBO.save(this.caddie);
        this.caddie = new Order();
        return "transition";
    }
    
    public BuisnessFacade getCaddieBO()                                     { return caddieBO;}
    public HtmlDataTable getDataTable()                                     { return dataTable;}
    public Order getCaddie()                                                { return caddie;}
    public KeyRepository getKeyRepository()                                 { return keyRepository;}
    
    public void setCaddieBO(BuisnessFacade caddieBO)                        { this.caddieBO = caddieBO;}
    public void setDataTable(HtmlDataTable dataTable)                       { this.dataTable = dataTable;}
    public void setCaddie(Order caddie)                                     { this.caddie = caddie;}
    public void setKeyRepository(KeyRepository keyRepository)               {this.keyRepository = keyRepository;}
}
