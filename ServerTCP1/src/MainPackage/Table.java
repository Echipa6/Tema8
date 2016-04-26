package MainPackage;

import java.io.IOException;
import java.util.Vector;

import javax.swing.JTextArea;

class Table {
	private int currentPlayerNumber = -1;
	 JTextArea textArea;
	private boolean available = false;
	private BagTiles bagTiles;
	Vector<Player> players;
	
	
	public Table( JTextArea textArea)
	{
		this.textArea=textArea;
		try {
			bagTiles=new BagTiles();
		} catch (IOException e1) {
			
			e1.printStackTrace();
		}
		players= new Vector<Player>();
	}
	void addPlayer(Player player)
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
		Player currentPlayer=players.elementAt(currentPlayerNumber);
		System.out.println("Player"+(currentPlayerNumber+1)+" "+word);
		this.textArea.append("Player"+(currentPlayerNumber+1)+" "+word+'\n');
		currentPlayer.gainScore(word.length()*5);
		currentPlayer.removeMyTiles(word);

		currentPlayer.addMyTiles(getMissedTiles(currentPlayer.getNumberTiles()));
		
	}
	private void playRound()
	{
		
		Player currentPlayer=players.elementAt(currentPlayerNumber);
		
		currentPlayer.addMyTiles(getMissedTiles(currentPlayer.getNumberTiles()));
		currentPlayer.setLabelActive();
		
		String s="";
		int i=0;
		while(s.isEmpty())
		{
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			s=currentPlayer.solver.getWord(currentPlayer.getMyTiles());
			//System.out.println(i++);
			
		}
		if(currentPlayerNumber==3){
			((ManualSolver)currentPlayer.solver).wordToValidate="";
		}
		reloadTail(s);
		
		
		currentPlayer.endTurn();
	}
	
	public synchronized int get(int consNumber) {
		while (!(available && currentPlayerNumber==consNumber)) {
			try {
				wait();
				// Asteapta arbitrul sa puna o valoare
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		available = false;
		//System.out.println(	"Consumatorul "+consNumber+" primit:\t" + currentPlayerNumber);
		playRound();
		
		
		notifyAll();
		return currentPlayerNumber;
	}
	public synchronized void put(int number) {
		while (available) {
			try {
				wait();
				// Asteapta jucatorul sa preia valoarea
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		this.currentPlayerNumber = number;
		available = true;
		//System.out.println("Producatorul a pus:\t" + number);
		notifyAll();
	}
}


