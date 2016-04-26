package MainPackage;

import java.io.IOException;
import java.util.Vector;

import javax.swing.JTextArea;

class Table {
	private int currentPlayerNumber = -1;
	 JTextArea textArea;
	private boolean available = false;
	private BagTiles bagTiles;
	Vector<Client> players;
	
	
	
	
	public Table()
	{
		try {
			bagTiles=new BagTiles();
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
				// TODO Auto-generated catch block
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
		
		
		while(true)
		{
			try {
				String request=players.elementAt(0).in.readLine();
				String response="Hello "+request;
				
				players.elementAt(0).out.println(response+'\n');
				System.out.println(response);
				players.elementAt(0).out.flush();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	
	
}


