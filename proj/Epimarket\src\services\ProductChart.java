package services;

import ORM.criteria.CLike;
import ORM.criteria.Criteria;
import ORM.criteria.ICriteria;
import ORM.model.AbstractProduct;
import ORM.model.Category;
import beans.ProductBean;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.beanutils.BeanComparator;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.time.Month;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;


public class ProductChart 
{
	//Injected references
	private ProductBean     productBean;	
	
	
	public ProductChart()
	{
		
	}

	public DefaultPieDataset getPieDataset() 
	{
                List prodList = new ArrayList();                
		List categlist = ((ArrayList)this.productBean.getProductBO().find("Category"));
                DefaultPieDataset pieDataSet = new DefaultPieDataset();

                prodList = productBean.getAllProduct();
                
                Iterator i = categlist.iterator();
                while (i.hasNext())
                {
                    String categName = ((Category)i.next()).getName();
                    ICriteria crit = new Criteria("category.name", new CLike(categName));
                    prodList = ((ArrayList)this.productBean.getProductBO().find("AbstractProduct", crit));
                    pieDataSet.setValue(categName, prodList.size());
                }
		return pieDataSet;
	}

	public XYDataset getFirstXYDataset() {
                List<AbstractProduct> dvdList = this.productBean.getProductBO().find("Dvd");
                Collections.sort(dvdList, new BeanComparator("price")); 
                
		XYSeries series = new XYSeries("Dvd Price");                
		series.setDescription("Dvd Price");

                int count = 0;
                Iterator i = dvdList.iterator();
                while (i.hasNext())                
                {
                    AbstractProduct tmp = (AbstractProduct) i.next();
                    ++count;
                    series.add(count, tmp.getPrice());
                }
		return new XYSeriesCollection(series);
	}

        public XYDataset getSecondXYDataset() {
                List<AbstractProduct> gameList = this.productBean.getProductBO().find("Game");
                Collections.sort(gameList, new BeanComparator("price")); 
                           
		XYSeries series = new XYSeries("Game Price");                
		series.setDescription("Game Price");
                
                int count = 0;
                Iterator i = gameList.iterator();
                while (i.hasNext())                
                {
                    AbstractProduct tmp = (AbstractProduct) i.next();
                    ++count;
                    series.add(count, tmp.getPrice());
                }
		return new XYSeriesCollection(series);
	}

        
    public ProductBean getProductBean()                 { return productBean;               }
    public void setProductBean(ProductBean productBean) { this.productBean = productBean;   }               
}
