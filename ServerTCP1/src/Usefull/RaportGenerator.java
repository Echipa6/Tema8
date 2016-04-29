package Usefull;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class RaportGenerator {
	BufferedWriter bw;
	public RaportGenerator() throws IOException
	{
		bw = new BufferedWriter(new FileWriter("report.html"));
		bw.write("<html>\n\t<head>\n"
				+"\t\t<link rel=\"stylesheet\" type=\"text/css\" href=\"theme.css\">"
				+ "\n\t\t<title>GameReport</title>\n\t</head>\n<body>\n");
		
	}
	public void finishRaport() throws IOException
	{
		
		bw.append("\n</body>\n</html>");
		bw.close();
		
	}
	
	public void addWord(String player, String word, String definition)
	{
		try {
			openDiv();
			createHTag(player, word);
			creaeDefPag(definition);
			closeDiv();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public void addScore(String topScore)
	{
		try {
			openDiv();
			bw.append("\t <h5>Game Top: </h5>");
			creaeDefPag(topScore);
			closeDiv();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void openDiv() throws IOException
	{
		bw.append("<div> \n");		
	}
	public void closeDiv() throws IOException
	{
		bw.append("\n"+"</div> \n <p> \n </p> \n");		
	}
	private void createHTag(String player, String word) throws IOException 
	{
		
		bw.append("\t <h5>"+player+" played: "+word+" scoring "+ word.length()*5+" points </h5>");
		
	}
	
	private void creaeDefPag(String definition) throws IOException 
	{
		bw.append("\n"+"\t <p>"+definition+"</p>");
		
	}

	
	


}
