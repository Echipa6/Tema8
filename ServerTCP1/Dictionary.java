package UsefullClasses;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import net.webeggs.juzzle.vocabulary.LowercaseTrieVocabulary;

public class Dictionary {
	public Map<String, Integer> map;
	public LowercaseTrieVocabulary voc;

	public Dictionary() throws IOException
	{
		map = new HashMap<String, Integer>();
		voc=new LowercaseTrieVocabulary();

		FileReader fr=null;
		fr = new FileReader("word.txt");
		BufferedReader textReader= new BufferedReader(fr);
		String s;
		while ((s=textReader.readLine()) != null)
		{
			if(s.matches("[a-z]+"))
			{
				//System.out.println(s);
				voc.add(s); 
				map.put(s, s.length());
			}
		}
	}

}
