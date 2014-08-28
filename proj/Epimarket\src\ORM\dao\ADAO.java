package ORM.dao;

import ORM.OrmException;
import java.io.File;
import java.util.List;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
import org.xml.sax.helpers.DefaultHandler;

import ORM.DBFacade;
import ORM.criteria.ICriteria;
import java.util.ArrayList;

public abstract class ADAO extends DefaultHandler implements IDAO
{	
	public ADAO(String file)
	{
            SAXParserFactory factory= SAXParserFactory.newInstance();
		
            try 
            {
                    SAXParser saxParser	= factory.newSAXParser();

                    saxParser.getXMLReader().setErrorHandler(this);
                    saxParser.getXMLReader().setEntityResolver(this);
                    saxParser.getXMLReader().setDTDHandler(this);

                    saxParser.parse(new File("C:\\Users\\locque_d\\Desktop\\Epimarket\\" + file), this);
                    DBFacade.getInstance().initXML(this.config);
            }
            catch (Throwable t)	{System.out.println("createSaxParser: " + t.toString()); t.printStackTrace();}
            }
            @Override
            public int save(Object o)
            {
                try {
                    return (DBFacade.getInstance().save(o, this.config));
                    // TODO Auto-generated method stub
                } catch (OrmException ex) {
                return (-1);
                }
	}

	@Override
	public List<Object> getAll(ICriteria crit) {
            try {
                return (DBFacade.getInstance().getAll(crit, this.config));
            } catch (OrmException ex) {
                return (new ArrayList<Object>(0));
            }
	}

	@Override
	public void update(Object o) {
            try {
                DBFacade.getInstance().update(o, this.config);
                // TODO Auto-generated method stub
            } catch (OrmException ex) {
            }
	}

	@Override
	public void delete(Object o) {
            try {
                DBFacade.getInstance().delete(o, this.config);
                // TODO Auto-generated method stub
            } catch (OrmException ex) {
            }
	}

	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//get XML config
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	
	
	
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//SAX parsing methods
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	public void startElement(String namespaceURI, String sName, String qName, Attributes attrs) throws SAXException
	{
		if (qName.equalsIgnoreCase("class"))
			startClass(attrs);
		else
		{
			if (qName.equalsIgnoreCase("id"))
				this.config.addId(startPropertyEntry(attrs));
			else if (qName.equalsIgnoreCase("property"))
				this.config.addProperty(startPropertyEntry(attrs));
			else if (qName.equalsIgnoreCase("column"))
				this.config.addColumn(startPropertyEntry(attrs));
			else if (qName.equalsIgnoreCase("abstract"))
				this.config.addAbstract(startPropertyEntry(attrs));
			else if (qName.equalsIgnoreCase("pos"))
				this.config.addPos(startPropertyEntry(attrs));
		}
	}

	public void endElement(String namespaceURI, String sName, String qName) throws SAXException   	{}	
	public void characters(char buf[], int offset, int len) throws SAXException						{}
	public void startDocument() throws SAXException													{}	
	public void endDocument() throws SAXException													{}

	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//Sub-parsing methods : create, chain, and feed objects 
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	public Entry startPropertyEntry(Attributes attrs)
	{
		String entryId			= attrs.getValue("name");
        String entryType		= attrs.getValue("type");
        String entryDesignation	= attrs.getValue("designation");
        Entry e = new Entry(entryId, entryDesignation, entryType);
        
        if (attrs.getValue("mult") != null && attrs.getValue("mult").equals("true"))
        	e.setMult(true);
        return (e);
	}
	
	public void startClass(Attributes attrs)
	{
		this.config = new XMLConfig(attrs.getValue("name"), attrs.getValue("table"));
	}

	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//Error handler methods
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	public void error(SAXParseException exception)					{System.out.println("error: " 		+ exception.toString());}
	public void fatalError(SAXParseException exception)				{System.out.println("fatalError: " 	+ exception.toString());}
	public void warning(SAXParseException exception)				{System.out.println("warning: " 	+ exception.toString());}

	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//Entity handler methods
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	public InputSource resolveEntity(String publicId, String systemId) throws SAXException		{return null;}

	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//DTD handler methods
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	public void notationDecl(String name, String publicId, String systemId)	
	{System.out.println("notationDecl: name: " + name + ", publicId: " + publicId + ", systemId: " + systemId);}
	
	public void unparsedEntityDecl(String name, String publicId, String systemId, String notationName) throws SAXException
	{System.out.println("unparsedEntityDecl: name: " + name + ", publicId: " + publicId + ", systemId: " + systemId + ", notationName: " + notationName);}

	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//Gestion des Structures 
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	
	private XMLConfig	config = null;
}
