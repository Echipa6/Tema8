package MainPackage;

import java.io.IOException;
import java.util.Vector;

import javax.swing.JTextArea;

import Usefull.HTTPXML;

class Table {
	private int currentPlayerNumber = -1;
	 JTextArea textArea;
	private boolean available = false;
	private BagTiles bagTiles;
	Vector<Client> players;
	
	int currentPlayer=0;
	private String submittedWord;
	private String wordDefinition;
	
	Dictionary dictionary;
	
	
	public Table()
	{
		try {
			bagTiles=new BagTiles();
			dictionary= new Dictionary();
		} catch (IOException e1) {
			
			e1.printStackTrace();
		}
		
		players=new Vector<Client>();
		
	}
	void addPlayer(Client player)
	{
		players.add(player);
	}
	
	
	
	public Vector<Character> getMissedTiles(int currentNumberTiles)
	{ 
		if(bagTiles.bag.isEmpty())
		{
			this.textArea.append("GAME OVER! The bag is empty.");
			try {
				Thread.sleep(100000);
			} catch (InterruptedException e) {

				e.printStackTrace();
			}
		}

		
		return bagTiles.getTiles(7-currentNumberTiles);
		
	}
	
	private void reloadTail(String word)
	{
////		Player currentPlayer=players.elementAt(currentPlayerNumber);
////		System.out.println("Player"+(currentPlayerNumber+1)+" "+word);
////		this.textArea.append("Player"+(currentPlayerNumber+1)+" "+word+'\n');
////		currentPlayer.gainScore(word.length()*5);
////		currentPlayer.removeMyTiles(word);
//
//		currentPlayer.addMyTiles(getMissedTiles(currentPlayer.getNumberTiles()));
		
	}
	
	
	public void startGame() {
		
		// comunicam server client si ii dam runda clientului respectiv 
		
		players.elementAt(0).getFirstTails(getMissedTiles(0));
		//players.elementAt(1).getFirstTails(getMissedTiles(0));
		
		while(true)
		{
			try {
				String request=players.elementAt(currentPlayer).in.readLine();
				String response="Hello "+request;
				processWord(request);
				
				
//				players.elementAt(0).out.println(response+'\n');
//				System.out.println(response);
//				players.elementAt(0).out.flush();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
	}
	void processWord(String word)
	{
		Client roundPlayer=players.elementAt(currentPlayer);
		if(validate(word))
		{
			System.out.println(players.elementAt(0).myTiles.toString());
			
			
			wordDefinition=null;
			HTTPXML dictionary=new HTTPXML();
			try {
				wordDefinition=dictionary.SearchDefinition(word);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			System.out.println(word+wordDefinition+"\n Cuvantul este bun ");
			players.elementAt(this.currentPlayer).out.println("wordValid");
			roundPlayer.out.flush();
			roundPlayer.removeMyTiles(word);
			roundPlayer.addMyTiles(this.getMissedTiles(roundPlayer.myTiles.size()));
			roundPlayer.out.println(roundPlayer.myTiles.toString());
			roundPlayer.out.flush();
			
			//currentPlayer=(currentPlayer+1)%2;
			
		}
		else
		{
			System.out.println(word+" Cuvantul nu este bun ");
			roundPlayer.out.println("wordInvalid");
			roundPlayer.out.flush();
		}
	}
	
	
	private boolean validate(String wordToValidate) {
		boolean ok=true;
		String auxWord=wordToValidate;
		Vector<Character> auxTiles=new Vector<Character>(players.elementAt(0).myTiles);
		if(dictionary.voc.isPrefix(wordToValidate))
		{
			for(int i=0;i<auxWord.length();i++)
			{
				int index_nr=auxTiles.indexOf(auxWord.charAt(i));
				if(index_nr==-1)
				{
					//System.out.println(auxTiles.toString());
					System.out.println("nu avem litera"+auxWord.charAt(i));
					return false;
				}
				else
				{
					auxTiles.remove(index_nr);
				}

			
			}
			return ok && dictionary.voc.getNode(wordToValidate).isWord();
		}
		return false;
	}
	
	
}


