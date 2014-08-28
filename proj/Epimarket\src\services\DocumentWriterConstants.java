package services;

import java.text.DecimalFormat;

import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;

public interface DocumentWriterConstants 
{

	public static Font              	fontSmall                       	= FontFactory.getFont(FontFactory.HELVETICA, 8);
        public static Font              	fontSmallUnderlined              	= FontFactory.getFont(FontFactory.HELVETICA, 8, Font.UNDERLINE);
	public static Font              	fontSmallBold                   	= FontFactory.getFont(FontFactory.HELVETICA_BOLD, 8);
	public static Font              	fontSmallBoldUnderlined         	= FontFactory.getFont(FontFactory.HELVETICA_BOLD, 8, Font.UNDERLINE);
	public static Font              	fontSmallBoldItalicUnderlined    	= FontFactory.getFont(FontFactory.HELVETICA_BOLDOBLIQUE, 8, Font.UNDERLINE);
	public static Font              	fontSmallBoldItalic    			= FontFactory.getFont(FontFactory.HELVETICA_BOLDOBLIQUE, 8);
	public static Font              	fontSmallItalic    				= FontFactory.getFont(FontFactory.HELVETICA_OBLIQUE, 8);
	public static Font              	fontSmallItalicUnderlined			= FontFactory.getFont(FontFactory.HELVETICA_OBLIQUE, 8, Font.UNDERLINE);
	public static Font              	fontPlain                       	= FontFactory.getFont(FontFactory.HELVETICA, 10);
	public static Font              	fontMedium                      	= FontFactory.getFont(FontFactory.HELVETICA, 12);
	public static Font              	fontBig                         	= FontFactory.getFont(FontFactory.HELVETICA, 20);
	public static Font              	fontPlainBold                   	= FontFactory.getFont(FontFactory.HELVETICA_BOLD, 10);
	public static Font              	fontPlainUnderlined				= FontFactory.getFont(FontFactory.HELVETICA, 10, Font.UNDERLINE);
	public static Font              	fontPlainBoldUnderlined         	= FontFactory.getFont(FontFactory.HELVETICA_BOLD, 10, Font.UNDERLINE);
	public static Font              	fontPlainBoldItalicUnderlined    	= FontFactory.getFont(FontFactory.HELVETICA_BOLDOBLIQUE, 10, Font.UNDERLINE);
	public static Font              	fontMediumBold                 	= FontFactory.getFont(FontFactory.HELVETICA_BOLD, 12);
	public static Font              	fontMediumBoldUnderlined        	= FontFactory.getFont(FontFactory.HELVETICA_BOLD, 12, Font.UNDERLINE);
	public static Font              	fontPlainItalic                 	= FontFactory.getFont(FontFactory.HELVETICA_OBLIQUE, 10);
	public static Font              	fontBoldItalic                  	= FontFactory.getFont(FontFactory.HELVETICA_BOLDOBLIQUE, 10); 
	public static Font              	fontBigBold                     	= FontFactory.getFont(FontFactory.HELVETICA_BOLD, 20);
	public static Font              	fontBigBoldItalic               	= FontFactory.getFont(FontFactory.HELVETICA_BOLDOBLIQUE, 12); 

	public static float             DEFAULT_CELL_MINIMUM_HEIGHT     	= 30;
    
    public static String 				STR_PARAM_DOCUMENT				= "document";
    public static String 				STR_PARAM_WRITER				= "writer";
    public static String 				STR_PARAM_MODE					= "mode";

    //Formatter
    public static DecimalFormat		NUMBER_FORMATTER					= new DecimalFormat("###,###,##0.0000");
    public static DecimalFormat		NUMBER_FORMATTER_SHORT                                  = new DecimalFormat("###,###,##0.00");

    //Modes definition
    public static int               	MODE_PDF    						= 0;             


}
    