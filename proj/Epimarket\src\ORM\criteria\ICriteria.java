package ORM.criteria;

import ORM.dao.XMLConfig;
import java.util.List;

public interface ICriteria {
	public String toString(XMLConfig conf);
        public String toString(XMLConfig conf, boolean u);
        public String toFrom(XMLConfig conf);
        public void toFrom(XMLConfig conf, List<String> from);
}
