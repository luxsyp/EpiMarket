package ORM.criteria;

import ORM.dao.XMLConfig;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ACriteriaOP implements ICriteria {
	public ACriteriaOP(String op, String value)
	{
		this.value = value;
		this.op = op;
	}
	
        @Override
	public String	toString(XMLConfig conf)
	{
		String		temp;

		temp = this.op + " \"" + this.value + "\"";
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
        }
        
	private String value;
	private String op;

    @Override
    public String toString(XMLConfig conf, boolean u)
    {
        String		temp;

        temp = this.op + " \"" + this.value + "\"";
        return (temp);
    }
}
