package Usefull;

import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

public class HTTPXML
{
    private  static String urlString;
    private static String key;
	

	public HTTPXML()
	{
		
		urlString = "http://www.dictionaryapi.com/api/v1/references/collegiate/xml/";
		key="66d82680-455c-4c80-b932-f627e55c60e4";
		
		
	}
    public String SearchDefinition(String word) throws Exception
    {
    	urlString=urlString+word+"?key="+key;
        URL url= new URL(urlString);
        URLConnection connection = url.openConnection();

        Document doc = parseXML(connection.getInputStream());
        NodeList descNodes = doc.getElementsByTagName("dt");

        for(int i=0; i<descNodes.getLength();i++)
        {
           return  descNodes.item(i).getTextContent();
        }
        return null;
    }

    private Document parseXML(InputStream stream)
    throws Exception
    {
        DocumentBuilderFactory objDocumentBuilderFactory = null;
        DocumentBuilder objDocumentBuilder = null;
        Document doc = null;
        try
        {
            objDocumentBuilderFactory = DocumentBuilderFactory.newInstance();
            objDocumentBuilder = objDocumentBuilderFactory.newDocumentBuilder();

            doc = objDocumentBuilder.parse(stream);
        }
        catch(Exception ex)
        {
            throw ex;
        }       

        return doc;
    }
}