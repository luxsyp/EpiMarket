package ORM.criteria;

import ORM.dao.XMLConfig;
import java.util.ArrayList;
import java.util.List;
import java.util.Iterator;

public abstract class ACriteriaCond implements ICriteria {
	public ACriteriaCond()
	{
	}
	public ACriteriaCond(String op, List<ICriteria> crits)
	{
		this.op = op;
		this.crits = crits;
	}
    @Override
	public String	toString(XMLConfig conf)
	{
		String		temp = "";
                int             i = 0;
		
                for (Iterator<ICriteria> it = this.crits.iterator(); it.hasNext();)
                {
                    if (i++ != 0)
                        temp += " " + this.op + " ";
                    temp += it.next().toString(conf, true);
                        
                }
                temp = "(" + temp + ")";
                temp += " AND ";
                i = 0;
                for (Iterator<ICriteria> it = this.crits.iterator(); it.hasNext();)
                {
                    if (i++ != 0)
                        temp += " AND ";
                    temp += it.next().toString(conf, false);
                        
                }
		return ("(" + temp + ")");
	}
    
    @Override
	public String	toString(XMLConfig conf, boolean u)
	{
		String		temp = "";
                int             i = 0;
		
                for (Iterator<ICriteria> it = this.crits.iterator(); it.hasNext();)
                {
                    if (i++ != 0)
                        temp += " " + this.op + " ";
                    temp += it.next().toString(conf);
                        
                }
		return ("(" + temp + ")");
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
            String		temp = "";
            int             i = 0;

            for (Iterator<ICriteria> it = this.crits.iterator(); it.hasNext();)
                it.next().toFrom(conf, from);
        }
        
        private List<ICriteria> crits;
	private String op;
}
