package beans;

import ORM.BuisnessFacade;
import ORM.model.AbstractProduct;
import java.io.InputStream;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import javax.faces.application.FacesMessage;
import org.apache.commons.beanutils.BeanComparator;
import org.apache.commons.collections.comparators.ReverseComparator;
import utils.KeyRepository;
import org.apache.myfaces.component.html.ext.HtmlDataTable;
import org.apache.myfaces.custom.datalist.HtmlDataList;
import services.ProductReportWriter;
import utils.FileDownloadHelper;
import utils.WebHelper;


public class ProductBean
{
	//Injected references
	private BuisnessFacade 			productBO;
	private ProductEditBean 		productEditBean;
        private ProductBuyBean                  productBuyBean;
        private ProductSearchBean               productSearchBean;
	private KeyRepository 			keyRepository;
	
	//Page variables
	private HtmlDataTable 			dataTable;
        private HtmlDataList			productTable;
	private String 				sortColumn = "id";
        private int                             rowCount = 9;
	private boolean 			sortAscending;
	
	//temp
	private Date					date;
	
	public ProductBean()
	{
		
	}
	
	/**
	 * 
	 * @return
	 */
	public List<AbstractProduct> getAllProduct()
	{
            List<AbstractProduct>   temp;
            
            temp = this.productSearchBean.getAllProduct();
            Collections.sort(temp, (sortAscending)? new BeanComparator(sortColumn) : new ReverseComparator(new BeanComparator(sortColumn))); 
            return temp;
        }
	
        
        public String deleteProduct()
	{
            this.productBO.delete((AbstractProduct)dataTable.getRowData());
            return "";
	}
        
        
	public String searchProduct()
	{
            productEditBean.setCurrentProduct((AbstractProduct)dataTable.getRowData()); 
            productEditBean.setStateDisplayValueOnly(true);
            return "productEdit";
	}
        
        public String viewProduct()
	{
            productBuyBean.setCurrentProduct((AbstractProduct)productTable.getRowData()); 
            return "visu";
	}

        public void getPdf()
        {   
            try
            {
                InputStream ProdPDF = new ProductReportWriter().generate(this.getAllProduct());
                FileDownloadHelper.returnFile(ProdPDF, "application/pdf", "ExportProduct.pdf");
            } 
            catch (Throwable t) {t.printStackTrace(); WebHelper.addMessage("generation OK !", "Probleme dans la generation du document !", FacesMessage.SEVERITY_ERROR );}
        }
        
	public BuisnessFacade getProductBO()                        {return productBO;}
	public HtmlDataTable getDataTable()                         {return dataTable;}
        public HtmlDataList getProductTable()                       {return productTable;}
	public String getSortColumn()                               {return sortColumn;}
	public boolean isSortAscending()                            {return sortAscending;}
	public ProductEditBean getProductEditBean()                 {return productEditBean;}
        public ProductBuyBean getProductBuyBean()                   {return productBuyBean;}
        public ProductSearchBean getProductSearchBean()             {return productSearchBean;}
	public int getRowCount()                                    {return rowCount;}
	public Date getDate()                                       {return date;}
	public KeyRepository getKeyRepository()                     {return keyRepository;}

	public void setProductBO(BuisnessFacade productBO)              {this.productBO 		= productBO;}
	public void setDataTable(HtmlDataTable dataTable)               {this.dataTable 		= dataTable;}
        public void setProductTable(HtmlDataList productTable)         {this.productTable 		= productTable;}
	public void setSortColumn(String sortColumn)			{this.sortColumn 		= sortColumn;}
	public void setSortAscending(boolean sortAscending)		{this.sortAscending 	= sortAscending;}
	public void setProductEditBean(ProductEditBean productEditBean)	{this.productEditBean 	= productEditBean;}
        public void setProductBuyBean(ProductBuyBean productBuyBean)	{this.productBuyBean 	= productBuyBean;}
        public void setProductSearchBean(ProductSearchBean productSearchBean)	{this.productSearchBean 	= productSearchBean;}
	public void setRowCount(int rowCount)				{this.rowCount 			= rowCount;}
	public void setDate(Date date)					{this.date 				= date;}
	public void setKeyRepository(KeyRepository keyRepository)	{this.keyRepository 	= keyRepository;}
	 
}
