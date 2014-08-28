package ORM.dao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

public class XMLConfig {
	public XMLConfig(String name, String table)
	{
		this.name				= name;
		this.table 				= table;
		this.collectionElt		= new Vector<Entry>();
		this.abs				= new Vector<Entry>();
	}
	
	public String			getName()
	{
		return (this.name);
	}
	
	public String			getTable()
	{
		return (this.table);
	}
	
	public String			getIdCol()
	{
		return (this.id.getColumn().getId());
	}
	
	public String			getIdAttr()
	{
		return (this.id.getId());
	}
	
	
	public List<String> getAllAttr()
	{
		List<String>	attrs = new ArrayList<String>(0);
		Iterator<?> iterator = this.collectionElt.iterator();
		while (iterator.hasNext())
        {
        	Entry elt					= (Entry) iterator.next();
        	
        	attrs.add(elt.getId());
        }
		return attrs;
	}
	
	public boolean			getAttrMult(String attr)
	{
		Iterator<?> iterator = this.collectionElt.iterator();
		while (iterator.hasNext())
        {
        	Entry elt					= (Entry) iterator.next();
        	
        	if (attr.equals(elt.getId()))
        	{
        		return (elt.getMult());
        	}
        }
		return (false);
	}
	public String			getColumnAttribute(String attr)
	{
		Iterator<?> iterator = this.collectionElt.iterator();
		while (iterator.hasNext())
        {
        	Entry elt					= (Entry) iterator.next();
        	
        	if (attr.equals(elt.getId()))
        	{
        		return (elt.getColumn().getId());
        	}
        }
		return (attr);
	}
	
	
	public String			getColumnTable(String attr)
	{
		Iterator<?> iterator = this.collectionElt.iterator();
		while (iterator.hasNext())
        {
        	Entry elt					= (Entry) iterator.next();
        	
        	if (attr.equals(elt.getId()))
        	{
        		return (elt.getColumn().getType());
        	}
        }
		return (attr);
	}
	
	public String			getTypeAttr(String attr)
	{
		Iterator<?> iterator = this.collectionElt.iterator();
		while (iterator.hasNext())
        {
        	Entry elt					= (Entry) iterator.next();
        	
        	if (attr.equals(elt.getId()))
        		return (elt.getType());
        }
		return (attr);
	}
	
	public String			getDesignationAttr(String attr)
	{
		Iterator<?> iterator = this.collectionElt.iterator();
		while (iterator.hasNext())
        {
        	Entry elt					= (Entry) iterator.next();
        	
        	if (attr.equals(elt.getId()))
        		return (elt.getDesignation());
        }
		return (attr);
	}
	
	public List<String>			getAllAbstract()
	{
		List<String>	abst = new ArrayList<String>(0);
		Iterator<?> iterator = this.abs.iterator();
		while (iterator.hasNext())
        {
        	Entry elt					= (Entry) iterator.next();
        	
        	abst.add(elt.getId());
        }
		return abst;
	}
	
	public String				getAbstractCol(String abs)
	{
		Iterator<?> iterator = this.abs.iterator();
		while (iterator.hasNext())
        {
        	Entry elt					= (Entry) iterator.next();
        	
        	if (abs.equals(elt.getId()))
        		return (elt.getColumn().getId());
        }
		return (abs);
	}
	
	public List<String>			getPos()
	{
		List<String>	abst = new ArrayList<String>(0);
		Iterator<?> iterator = this.pos.iterator();
		while (iterator.hasNext())
        {
        	Entry elt					= (Entry) iterator.next();
        	
        	abst.add(elt.getId());
        }
		return abst;
	}
	
	public String			getAbstractPos(String pos)
	{
		Iterator<?> iterator = this.pos.iterator();
		while (iterator.hasNext())
        {
        	Entry elt					= (Entry) iterator.next();

        	if (pos.equals(elt.getId()))
        		return (elt.getDesignation());
        }
		return (pos);
	}
	
	///////////////////////////////////////////
	///ADD
	///////////////////////////////////////////
	public void addId(Entry elt)
	{
		this.id = elt;
		this.currentEntry = this.id;
	}
	
	public void addProperty(Entry elt)
	{
		this.collectionElt.add(elt);
		this.currentEntry = elt;
	}
	
	public void addColumn(Entry elt)
	{
		this.currentEntry.setTable(elt);
	}
	
	public void	addAbstract(Entry abs)
	{
		this.abs.add(abs);
		this.currentEntry = abs;
	}
	
	public void	addPos(Entry pos)
	{
		this.pos.add(pos);
	}
	
	private Collection<Entry>	pos = new ArrayList<Entry>(0);
	private Collection<Entry>	collectionElt;
	private Collection<Entry>	abs = new ArrayList<Entry>(0);
	private Entry				id = null;
	
	private Entry				currentEntry = null;

	private String				table;
	protected String			name;
}
