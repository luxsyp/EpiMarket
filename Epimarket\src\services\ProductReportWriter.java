package services;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.List;

import com.lowagie.text.Element;
import com.lowagie.text.PageSize;
import com.lowagie.text.pdf.PdfPTable;

import ORM.model.AbstractProduct;


public class ProductReportWriter  extends AbstractDocumentWriter
{
    private String                          FILE_PATH                   = "./";


    public ProductReportWriter()        {}

    
    //for Web side
    public InputStream generate(List<AbstractProduct> listProduct) throws Exception
    {
        ByteArrayOutputStream buffer            = new ByteArrayOutputStream(1024);
        
        startDocument(buffer, PageSize.A4.rotate());
        
        document.open();
        publish(listProduct);
        //document.newPage();
        
        endDocument(document);
        buffer.close();
        
        return new ByteArrayInputStream(buffer.toByteArray());
    }
    
    public void publish(List<AbstractProduct> listProduct)
    {
        try
        {
            addNewParagraph("\r\n\r\nExport des produits", fontBigBold, document, new Integer(Element.ALIGN_CENTER));           

            addNewParagraph("\r\n\r\nListe:", fontMedium, document, new Integer(Element.ALIGN_LEFT));           

            PdfPTable t0     = getNewDefaultPTable(4);

            t0.setTotalWidth(new float[] {60, 10, 20, 10});

            for (AbstractProduct p : listProduct)
            {
            	addCellToTable(t0, "Product: "      + p.getName(),                  fontPlainBold,  new Integer(Element.ALIGN_LEFT), 0);
            	addCellToTable(t0, "Categorie: "    + p.getCategory().getName(),    fontPlain,      new Integer(Element.ALIGN_LEFT), 0);
            	addCellToTable(t0, "Designation: "  + p.getDesignation(),           fontPlain,      new Integer(Element.ALIGN_LEFT), 0);
            	addCellToTable(t0, "prix: "         + p.getPrice(),                 fontPlain,      new Integer(Element.ALIGN_LEFT), 0);
            }

            addTableToContainer(t0, document);
            addNewParagraph("\r\n", fontPlain, document);
        }
        catch (Exception e) {e.printStackTrace();}        
    }

}
