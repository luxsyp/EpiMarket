/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import ORM.model.Dvd;
import ORM.model.Order;
import java.util.ArrayList;
import java.util.List;
import javax.faces.component.html.HtmlDataTable;

public class HistoricalBean {
    
    private HtmlDataTable 			dataTable;
    private Order                               currentOrder;
   private List commandList = new ArrayList();
   
    public HistoricalBean() {
        for (int i = 0 ; i < 15 ; ++i)
            commandList.add(new Dvd("name" + i, "Designation" + i, null, 1 + i));        
    }
    
    public String searchData()
    {
        currentOrder = ((Order)dataTable.getRowData()); 
        return "historicalPage";
    }

    public HtmlDataTable getDataTable()             { return dataTable; }
    public Order getCurrentOrder()                  { return currentOrder; }
    
    public void setDataTable(HtmlDataTable dataTable)   { this.dataTable = dataTable; }
    public void setCurrentOrder(Order currentOrder)     { this.currentOrder = currentOrder; }
    
    public List getCommandList()                    { return commandList;               }
    public void setCommandList(List commandList)    { this.commandList = commandList;   }
}
