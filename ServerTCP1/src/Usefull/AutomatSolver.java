package Usefull;


import java.io.IOException;
import java.util.Vector;

import MainPackage.Dictionary;

public class AutomatSolver {
	Vector<String> findedWords;
	private Vector<Character> tiles;
	private Dictionary dictionary;
	public AutomatSolver()
	{
		try {
			dictionary=new Dictionary();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		findedWords=new Vector<String>();
	}
	public String getWord(Vector<Character> tiles) {
		this.tiles=tiles;
		
		findedWords=new Vector<String>();

		//circular shifting;
		for(int i=0;i<this.tiles.size()-1;i++)
		{
			for(int j=0;j<this.tiles.size();j++)
			{

				
				tryToFindWord();
				Character aux=tiles.elementAt(0);
				tiles.remove(0);
				tiles.add(tiles.size()-i, aux);
			}
			
		}
		
		return selectBigestWord();
	}
	
	public String selectBigestWord()
	{
		String word="";
			
		for(String word1 :findedWords)
		{
			if(word1.length()>word.length())
			{
				word=word1;
			}
		}
			
		return word;
	}
	
	public String tryToFindWord()
	{
		StringBuilder constructedWord;
		Vector<Integer> added=new Vector<Integer>();
		
		Integer used=new Integer(1);
		Integer free=new Integer(0);
		for(int i=0; i<tiles.size();i++)
		{
			added.add(free);
		}
		
		for(int index=0;index<tiles.size();index++)
		{
			constructedWord=new StringBuilder("");
			
			//System.out.println(tiles.get(index).toString());
			if(dictionary.voc.isPrefix(tiles.get(index).toString()))
			{
				constructedWord.append(tiles.get(index).toString());
				added.setElementAt(used, index);
				
				if(dictionary.voc.getNode(constructedWord.toString()).isWord())
				{
					findedWords.add(constructedWord.toString());
				}
				
				for(int cursor=0;cursor<tiles.size();cursor++)
				{
					if(added.get(cursor)==0)
					{
						StringBuilder newWord= new StringBuilder("");
						newWord=constructedWord;
						newWord.append(tiles.get(cursor).toString());
						if(dictionary.voc.isPrefix(newWord.toString()))
						{
							if(dictionary.voc.getNode(newWord.toString()).isWord())
							{
								findedWords.add(newWord.toString());
							}
							added.setElementAt(used, cursor);
							constructedWord=newWord;
							
						}
					}
				}
				
				for(int i=0; i<added.size();i++)
				{
					added.set(i, 0);
				}
			}
			
			
		}
		
		return null;
		
	}


}
