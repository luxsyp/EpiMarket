package services;

import java.awt.Color;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import com.lowagie.text.Chapter;
import com.lowagie.text.Chunk;
import com.lowagie.text.DocWriter;
import com.lowagie.text.Document;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.HeaderFooter;
import com.lowagie.text.Image;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.Rectangle;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;


/**
 * @author Dim
 */
public class AbstractDocumentWriter implements DocumentWriterConstants
{
    //Document entities
    public Document                 	document;
    public DocWriter                	writer;

    //Determinate current objects for common writer class
    public Object                       currentContainerObject;    
    public HeaderFooter 		currentHeader, currentFooter;
    public String                       currentStrHeader;
    public int                          currentChapterIndex;
    
    
    //Current fonts used
    public Font                         currentFontPlain;
    public Font                         currentFontMedium;
    public Font                         currentFontPlainUnderlined;
    public Font 			currentFontBold;

    public int                          summaryPageOffset 	= 0;
    public int                          nbrPageCount 		= 0;
    
    
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//Getters & Setters
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    
    public void setWriter(DocWriter writer)                	{this.writer            			= writer;}
    public void setDocument(Document document)             	{this.document          			= document;}
    
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //DOCUMENT
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    
    public void startDocument(OutputStream stream) throws Exception {startDocument(stream, MODE_PDF);}
    public void startDocument(OutputStream stream, Rectangle rect) throws Exception {startDocument(stream, MODE_PDF, rect);}
    
   	public void startDocument(String strFileName) throws Exception
	{
		document		= new Document();
		writer      		= (DocWriter) PdfWriter.getInstance(document, new FileOutputStream(strFileName));
	}
    
    public void startDocument(OutputStream stream, int mode) throws Exception
    {
        document        = new Document();
        document.setMargins(71, 71, 71, 71);
        
        writer          = (DocWriter) PdfWriter.getInstance(document, stream);
    }

    public void startDocument(OutputStream stream, int mode, Rectangle rect) throws Exception
    {
        document        = new Document(rect);

        
        writer          = (DocWriter) PdfWriter.getInstance(document, stream);
    }
    
  	public static void endDocument(Document document)
   	{
   	    try {document.close();}
   	    catch (Exception e) {System.out.println("ProspectusWriter: endDocument: " + e.toString());}
   	}

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //HEADER & FOOTER
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    
    public void createAndAddHeader(String strHeader, Document document)
    {
        currentHeader		 				= new HeaderFooter(new Phrase(strHeader, fontPlain), true);
        
        document.setHeader(currentHeader);
    }

    public void createAndAddFooter(Document document)
    {
        currentFooter		 				= new HeaderFooter(new Phrase("Page"), new Phrase("."));
        
        document.setFooter(currentFooter);
    }
    
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //PARAGRAPH
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	public static Paragraph getBlankLine()		{return new Paragraph("");}
	
    
	public static void addNewParagraph(String content, Font font, Object target) throws Exception 
    {
	    Paragraph p	= (font == null)? new Paragraph(content) : new Paragraph(content, font);
	    
	    p.setAlignment(Element.ALIGN_JUSTIFIED);
	    
        if (target instanceof Document)
            ((Document) target).add(p);
        else if (target instanceof Chapter)
            ((Chapter) target).add(p);
    }

	public static void addNewParagraph(String content, Font font, Object target, Integer alignment) throws Exception 
    {
	    Paragraph p	= (font == null)? new Paragraph(content) : new Paragraph(content, font);
	    
	    p.setAlignment((alignment == null)? Element.ALIGN_JUSTIFIED : alignment.intValue());
	    
        if (target instanceof Document)
            ((Document) target).add(p);
        else if (target instanceof Chapter)
            ((Chapter) target).add(p);
    }

	public static void manageNewPage(Object container, Document document, HeaderFooter header) 
	{
	    try
	    {
		    if (container instanceof Document)
		        document.newPage();
	        else if (container instanceof Chapter)
	        {   
	            	document.setHeader(header);
	            	document.add((Chapter) container);
	        }
	    }
	    catch (Exception e) {System.out.println("AbstractDocumentWriter: manageNewPage: " + e.toString());}		    
	}
	
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //PDF TABLE
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	public static PdfPTable getNewDefaultPTable()			{try {return (PdfPTable) setTableProperties(new PdfPTable(2), null);}		catch (Exception e) {System.out.println("AbstractDocumentWriter: getNewDefaultPTable: " + e.toString()); return null;}}  
	public static PdfPTable getNewDefaultPTable(int nbrCols)	{try {return (PdfPTable) setTableProperties(new PdfPTable(nbrCols), null);}	catch (Exception e) {System.out.println("AbstractDocumentWriter: getNewDefaultPTable: " + e.toString()); return null;}}

	public static PdfPTable createSimplePdfPTable(String content, Color color, Font font)	{try {return setTableProperties(getNewDefaultPTable(1), content, null, color, font, null);}																																				catch (Exception e) {System.out.println("AbstractDocumentWriter: createSimplePdfPTable: " + e.toString()); return null;}}
	public static PdfPTable createSimplePdfPTable(String content, Color color, Font font, float tableWidth)						{try {return setTableProperties(getNewDefaultPTable(1), content, new Float(tableWidth), color, font, null);}																			catch (Exception e) {System.out.println("AbstractDocumentWriter: createSimplePdfPTable: " + e.toString()); return null;}}
	public static PdfPTable createSimplePdfPTable(String content, Color color, Font font, float tableWidth, int alignment)		{try {return setTableProperties(getNewDefaultPTable(1), content, new Float(tableWidth), color, font, new Integer(alignment));}	catch (Exception e) {System.out.println("AbstractDocumentWriter: createSimplePdfPTable: " + e.toString()); return null;}}
	
	public static PdfPTable setTableProperties(PdfPTable t, String content, Float tableWidth, Color color, Font font, Integer alignment)
	{
		PdfPCell c4     = (font != null)? new PdfPCell(new Phrase(new Chunk(content, font))) : new PdfPCell(new Phrase(content));
		
		if (color != null)
		    c4.setBackgroundColor(color);
		
		t.addCell(c4);
		
		if (tableWidth != null)
		    t.setWidthPercentage(tableWidth.floatValue());
	    
		if (alignment != null)
		    t.getDefaultCell().setVerticalAlignment(alignment.intValue());
	    
	    return t;
	}
	
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//GLOBAL TABLE MANAGMENT (PDF + RTF)
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	public static Object setTableProperties(Object table, Integer alignment)
	{
	    if (table instanceof PdfPTable)
	    {
	        ((PdfPTable) table).getDefaultCell().setPadding(3);
	        ((PdfPTable) table).getDefaultCell().setMinimumHeight(DEFAULT_CELL_MINIMUM_HEIGHT);
	        ((PdfPTable) table).getDefaultCell().setVerticalAlignment(Element.ALIGN_MIDDLE);
	        ((PdfPTable) table).setWidthPercentage(100); //t.setWidthPercentage(95);
	    }
	    
	    return table;
	}
	
	public static void addTableToContainer(Object table, Object container)
	{
	    try
	    {
		    	if (container instanceof Document)
		    	    ((Document) container).add((Element) table);
		    	else if (container instanceof Chapter)
		    	    ((Chapter) container).add(table);		    	    
	    }
	    catch (IllegalArgumentException e) 	{System.out.println("AbstractDocumentWriter: addTableToContainer: " + e.toString() + ", table instanceof " + table.getClass().toString()  + ", container instanceof " + container.getClass().toString()); e.printStackTrace();}
	    catch (Exception e) 				{System.out.println("AbstractDocumentWriter: addTableToContainer: " + e.toString());}		    		
	}
    
	public static Object createTableForWriter(Object writer, int nbrColumns)
	{
	    Object tableResult 	= null;
	    
	    if (writer instanceof PdfWriter)
	    {
	        tableResult 		= getNewDefaultPTable(nbrColumns);
	    }	    
	    
	    return tableResult;
	}
	
	public static void addCellToTable(Object table, String content, Object font, Integer alignment)
	{
	    if (table instanceof PdfPTable)
	    {
	        PdfPCell c	= (font != null)? new PdfPCell(new Phrase(new Chunk(content, (com.lowagie.text.Font) font))) : new PdfPCell(new Phrase(new Chunk(content)));
        
	        if (alignment != null)
	            c.setHorizontalAlignment(alignment.intValue());
            
	        ((PdfPTable) table).addCell(c);
	    }	    
	}

    public static void addCellToTable(Object table, String content, Object font, Integer alignment, int borderWidth)
    {
        if (table instanceof PdfPTable)
        {
            PdfPCell c  = (font != null)? new PdfPCell(new Phrase(new Chunk(content, (com.lowagie.text.Font) font))) : new PdfPCell(new Phrase(new Chunk(content)));
        
            if (alignment != null)
                c.setHorizontalAlignment(alignment.intValue());
            
            c.setBorder(borderWidth);
            
            ((PdfPTable) table).addCell(c);
        }       
    }

    
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//IMAGES
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	public static void addImageToContainer(Image image, Object container)
	{
	    try
	    {
		    	if (container instanceof Document)
		    	    ((Document) container).add(image);
		    	else if (container instanceof Chapter)
		    	    ((Chapter) container).add(image);		    	
	    }
	    catch (Exception e) {System.out.println("AbstractDocumentWriter: addImageToContainer: " + e.toString());}		    		
	}

	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//DATE 
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	public static String getStrDate(Date date)
	{
        if (date  == null)
            return "XX/XX/XXXX";
    
	        GregorianCalendar cal       	= new GregorianCalendar();
	        cal.setTime(date);
	        
	        int month 					= cal.get(Calendar.MONTH) + 1;
	        int day						= cal.get(Calendar.DAY_OF_MONTH);
	        
	        return "" + ((day < 10)? "0" + day : "" + day) + "/" + ((month < 10)? "0" + month : "" + month) + "/" + cal.get(Calendar.YEAR);
	}

	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//STRING
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	public static boolean isConsistent(String str)
	{
	    return ((str != null) && (str.trim().length() != 0));
	}

	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//FILE READING
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	public static byte[] getByteStream(String fileName)
	{
		try {return getBytesFromFile(new File(fileName));}    
	        catch (Exception e) {System.out.println("\nNdWriter: getByteStream: " + e.toString()); return null;}
	}
	    
	// Returns the contents of the file in a byte array.
	public static byte[] getBytesFromFile(File file) throws IOException 
	{
        InputStream is = new FileInputStream(file);
	    
        // Get the size of the file
        long length = file.length();
	    
        // You cannot create an array using a long type, It needs to be an int type.
        // Before converting to an int type, check to ensure that file is not larger than Integer.MAX_VALUE.
        if (length > Integer.MAX_VALUE) 
               System.out.println("NdWriter: getBytesFromFile: length > Integer.MAX_VALUE !");
	    
        // Create the byte array to hold the data
        byte[] bytes = new byte[(int)length];
	    
        // Read in the bytes
        int offset = 0;
        int numRead = 0;
        
        while ((offset < bytes.length) && (numRead=is.read(bytes, offset, bytes.length-offset)) >= 0) 
            offset += numRead;
        
        // Ensure all the bytes have been read in
        if (offset < bytes.length) 
            throw new IOException("Could not completely read file " + file.getName());
        
        // Close the input stream and return bytes
        is.close();
        return bytes;
	}

}
