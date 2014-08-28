package beans;

import ORM.BuisnessFacade;
import ORM.model.AbstractProduct;
import ORM.criteria.*;
import java.util.ArrayList;
import java.util.List;

import utils.KeyRepository;
        
public class ProductSearchBean
{
	//Injected references
	private BuisnessFacade 			productBO;
	private KeyRepository 			keyRepository;
        private String                          search = "";
        private int                             idCategory;
        private boolean                         andOr = false;
	
	//Session Associated references
	
	public ProductSearchBean()
	{
		
	}
        public String   search()
        {
            this.idCategory = 0;
            this.andOr = false;
            return "market";
        }
        
        public List getSuggest(String keyword) 
        {
            List srclist = new ArrayList();
            ICriteria crit = new Criteria("name", new CBegin(keyword));
            srclist = productBO.find("AbstractProduct", crit);
  
            return srclist;
        }
        
        public ArrayList      getAllCategory()
        {
            return ((ArrayList)this.productBO.find("Category"));
        }
        
        public List<AbstractProduct> getAllProduct()
	{
            ICriteria       crit;
            List<ICriteria> temp = new ArrayList<ICriteria>(0);
            
            temp.add(new Criteria("name", new CLike(this.search)));
            temp.add(new Criteria("category.id", new CEqual(Integer.toString(this.idCategory))));
            if (this.andOr)
                crit = new CAnd(temp);
            else
                crit = new COr(temp);
            return productBO.find("AbstractProduct", crit);	
        }
        
        public String getSearch()                                                       {return search;}
        public int getIdCategory()                                                      {return idCategory;}
        public boolean getAndOr()                                                       {return andOr;}
	public BuisnessFacade getProductBO()						{return productBO;}
	public KeyRepository getKeyRepository()						{return keyRepository;}

        public void setSearch(String search)                                            {this.search = search;}
        public void setIdCategory(int idCategory)                                       {this.idCategory = idCategory;}
        public void setAndOr(boolean andOr)                                             {this.andOr = andOr;}
	public void setProductBO(BuisnessFacade productBO)				{this.productBO 			= productBO;}
	public void setKeyRepository(KeyRepository keyRepository)			{this.keyRepository 			= keyRepository;}

}
