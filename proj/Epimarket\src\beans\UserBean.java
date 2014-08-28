package beans;

import ORM.BuisnessFacade;
import ORM.criteria.*;
import ORM.model.AbstractProduct;
import ORM.model.Address;
import ORM.model.Client;
import ORM.model.Order;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Date;

import javax.faces.application.FacesMessage;
import javax.faces.component.html.HtmlDataTable;
import org.apache.commons.beanutils.BeanComparator;
import org.apache.commons.collections.comparators.ReverseComparator;
import services.UserReportWriter;
import utils.FileDownloadHelper;
import utils.KeyRepository;
import utils.WebHelper;
import services.GmailMail;

public class UserBean {
    private Client              user = new Client();
    private CaddieBean          caddieBean;
    private BuisnessFacade      userBO;
    private UserEditBean        userEditBean;
    private KeyRepository       keyRepository;

    private HtmlDataTable       dataTable;
    private String              sortColumn      = "id";
    private int                 rowCount        = 5;
    private boolean             sortAscending;

    private Date		date;
    private boolean             userConnect = false;

    private GmailMail           gmail = new GmailMail();
    
    public List<Client> getAllUser()
    {
        List<Client>    temp;
        
        temp = this.userBO.find("Client");
        Collections.sort(temp, (sortAscending)? new BeanComparator(sortColumn) : new ReverseComparator(new BeanComparator(sortColumn))); 
        return temp;
    }
    
    public String connect()
    {
        List<ICriteria> crits = new ArrayList<ICriteria>(0);
        
        crits.add(new Criteria("login", new CEqual(user.getLogin())));
        crits.add(new Criteria("password", new CEqual(user.getPassword())));
        List<Client> temp = this.userBO.find("Client", new CAnd(crits));
        if (temp != null && temp.size() == 1)
        {
            this.user = temp.get(0);
            this.caddieBean.getCaddie().setClient(user);
            this.userConnect = true;
        }
        return "";
    }
    
    public String   finalizeCaddie()
    {
        if (this.userConnect)
        {
            this.userBO.update(this.user);
            Address     temp = this.user.getBillingAddress();
            
            temp.setId(0);
            this.caddieBean.getCaddie().setAddress(temp);
            this.caddieBean.getCaddie().setClient(this.user);
            return (this.caddieBean.finalizeCaddie());
        }
        return "transition";
    }
    
    public List<Order> getAllOrder()
    {
        ICriteria       crit;
        
        crit = new Criteria("client", new CEqual(Integer.toString(this.user.getId())));
        return this.userBO.find("Order", crit);
    }
    
    public String   doCommand()
    {
        return "doCommand";
    }
    
    
    public String disconnect()
    {
        this.user = new Client();
        this.caddieBean.getCaddie().setClient(null);
        this.userConnect = false;
        return "transition";
    }
    
    public String deleteUser()
    {
        this.userBO.delete((Client)dataTable.getRowData()); 
        return "";
    }
    
    public String searchUser()
    {
        userEditBean.setCurrentUser((Client)dataTable.getRowData()); 
        userEditBean.setStateDisplayValueOnly(true);
        return "userEdit";
    }
    
    public String saveUser()
    {
        this.userBO.save(this.user);
        return "userPage";
    }
    
    public void getPdf()
    {   
        try
        {
            InputStream ProdPDF = new UserReportWriter().generate(this.getAllUser());
            FileDownloadHelper.returnFile(ProdPDF, "application/pdf", "ExportUser.pdf");
        } 
        catch (Throwable t) {t.printStackTrace(); WebHelper.addMessage("generation OK !", "Probleme dans la generation du document !", FacesMessage.SEVERITY_ERROR );}
    }
    
    public void sendMail()
    {        
        List<AbstractProduct> dvdList = this.userBO.find("Dvd");
        List<AbstractProduct> gameList = this.userBO.find("Game");

        Collections.sort(dvdList, new ReverseComparator(new BeanComparator("id"))); 
        Collections.sort(gameList, new ReverseComparator(new BeanComparator("id"))); 

        this.gmail.sendMail(this.getAllUser(), dvdList, gameList);
    }
    
    public Client getUser()                                     { return user;                          }
    public CaddieBean getCaddieBean()                           { return caddieBean;                    }
    public boolean isUserConnect()                              { return userConnect;                   }
    public BuisnessFacade getUserBO()                           { return userBO;                        }
    public HtmlDataTable getDataTable()                         { return dataTable;                     }
    public String getSortColumn()                               { return sortColumn;                    }
    public boolean isSortAscending()                            { return sortAscending;                 }
    public UserEditBean getUserEditBean()                       { return userEditBean;                  }
    public int getRowCount()                                    { return rowCount;                      }
    public Date getDate()                                       { return date;                          }
    public KeyRepository getKeyRepository()                     { return keyRepository;                 }
    public GmailMail getGmail()                                 { return gmail;                         }

    public void setUser(Client user)                            { this.user = user;                     }
    public void setCaddieBean(CaddieBean caddieBean)            { this.caddieBean = caddieBean;         }
    public void setUserConnect(boolean userConnect)             { this.userConnect = userConnect;       }
    public void setUserBO(BuisnessFacade userBO)                { this.userBO = userBO;                 }
    public void setDataTable(HtmlDataTable dataTable)           { this.dataTable = dataTable;           }
    public void setSortColumn(String sortColumn)                { this.sortColumn = sortColumn;         }
    public void setSortAscending(boolean sortAscending)         { this.sortAscending = sortAscending;   }
    public void setUserEditBean(UserEditBean userEditBean)      { this.userEditBean = userEditBean;     }
    public void setRowCount(int rowCount)                       { this.rowCount = rowCount;             }
    public void setDate(Date date)                              { this.date = date;                     }
    public void setKeyRepository(KeyRepository keyRepository)   { this.keyRepository = keyRepository;   }
    public void setGmail(GmailMail gmail)                       { this.gmail = gmail;                   }

}

        