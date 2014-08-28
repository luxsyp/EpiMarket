package ORM.criteria;

import ORM.DBFacade;
import ORM.OrmException;
import ORM.dao.XMLConfig;

import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;

public abstract class ACriteria implements ICriteria {
	public ACriteria()
	{
	}
	public ACriteria(String param, ACriteriaOP op)
	{
		this.param = param;
		this.op = op;
	}
	
    @Override
	public String	toString(XMLConfig conf)
	{
                XMLConfig       tconf = conf;
                String          sub = "";
		String		temp = "";
                int             id;
                String          tparam = this.param;

                while ((id = tparam.indexOf(".")) != -1)
                {
                    sub = tparam.substring(0, id);
                    try {   
                        tconf = DBFacade.getInstance().getXML(tconf.getColumnTable(sub));
                    } catch (OrmException ex) {
                        Logger.getLogger(ACriteria.class.getName()).log(Level.SEVERE, null, ex); 
                    }       
                    if (tconf == null)
                            return "";
                        tparam = tparam.substring(id + 1);
                }
                if (tparam.equals(tconf.getIdAttr()))
                    temp += tconf.getTable() + "." + tconf.getIdCol();
		else
                    temp += tconf.getTable() + "." + tconf.getColumnAttribute(tparam);
                temp += " " + this.op.toString(conf);
                if (tconf != conf)
                    temp += " AND " + tconf.getTable() + "." + tconf.getIdCol() + " = " + conf.getTable() + "." + conf.getColumnAttribute(sub);
		return (temp);
	}
    
    @Override
    public String toString(XMLConfig conf, boolean u)
    {
        XMLConfig       tconf = conf;
                String          sub = "";
		String		temp = "";
                int             id;
                String          tparam = this.param;

                while ((id = tparam.indexOf(".")) != -1)
                {
                    sub = tparam.substring(0, id);
                    try {
                        tconf = DBFacade.getInstance().getXML(tconf.getColumnTable(sub));
                    } catch (OrmException ex) {
                        Logger.getLogger(ACriteria.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    if (tconf == null)
                        return "";
                    tparam = tparam.substring(id + 1);
                }
                if (u)
                {
                    if (tparam.equals(tconf.getIdAttr()))
                        temp += tconf.getTable() + "." + tconf.getIdCol();
                    else
                        temp += tconf.getTable() + "." + tconf.getColumnAttribute(tparam);
                    temp += " " + this.op.toString(conf);
                }
                else
                {
                    if (tconf != conf)
                        temp += tconf.getTable() + "." + tconf.getIdCol() + " = " + conf.getTable() + "." + conf.getColumnAttribute(sub);
                    else
                        temp += "1";
                }
		return (temp);
    }
    @Override
        public String toFrom(XMLConfig conf)
        {
            String          temp = "";
            List<String>    froms = new ArrayList<String>(0);
            Iterator<String>    it;
            int             i = 0;
            
            froms.add(conf.getTable());
            this.toFrom(conf, froms);
            for (it = froms.iterator(); it.hasNext();)
            {
                if (i++ == 0)
                    temp += it.next();
                else
                    temp += " ," + it.next();
            }
            return ("FROM " + temp);
        }
    @Override
        public void toFrom(XMLConfig conf, List<String> from)
        {
            XMLConfig       tconf = conf;
            String          sub = "";
            int             id;
            String          temp = this.param;
            
            while ((id = temp.indexOf(".")) != -1)
            {
                sub = temp.substring(0, id);
                try {
                    tconf = DBFacade.getInstance().getXML(conf.getColumnTable(sub));
                } catch (OrmException ex) {
                    Logger.getLogger(ACriteria.class.getName()).log(Level.SEVERE, null, ex);
                }
                if (tconf == null)
                    break;
                System.out.println(sub + " - " + temp);
                temp = temp.substring(id + 1);
                System.out.println(temp);
            }
            if (tconf != null && Collections.binarySearch(from, tconf.getTable()) == -1)
                from.add(tconf.getTable());
        }
	private String param;
	private ACriteriaOP op;
}
