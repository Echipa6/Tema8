package MainPackage;

import java.awt.Color;
import java.util.Vector;

import javax.swing.JLabel;

class Player extends Thread {
	private Table table;
	private int number; 
	Solver solver;

	private int score;
	private JLabel labelPlayer;
	private Vector<Character> myTiles;

	public Player(Table b, int nr, JLabel label) {
		table = b;
		number=nr;
		score=0;
		this.labelPlayer=label;
		labelPlayer.setText("<html>__________________<br>Player"+number+": <br> score:"+score+"</html>");

		setMyTiles(new Vector<Character>());

		if (nr!=4)
		{
			solver = new AutomatSolver();
		}
		else
		{	System.out.println("initiere manual solver");
			solver= new ManualSolver();
		}
	}
	public void setLabelActive()
	{
		labelPlayer.setText("<html>"+this.getMyTiles().toString()+"<br>__________________<br><font color='red'>Player"+number+": <br> score:"+score+"</font></html>");
	}

	public void endTurn()
	{
		labelPlayer.setText("<html>"+this.getMyTiles().toString()+"<br>__________________<br><font color='blue'>Player"+number+": <br> score:"+score+"</font></html>");
	}
	public void run() {
		int value = 0;
		for (int i = 0; i < 300; i++)
		{	
			value = table.get(this.number-1);

		}
	}
	public Vector<Character> getMyTiles() {
		return myTiles;
	}
	public void setMyTiles(Vector<Character> myTiles) {
		this.myTiles = myTiles;
	}
	public int getNumberTiles() {
		return this.myTiles.size();
	}

	public void addMyTiles(Vector<Character> randomTiles) {
		while(!randomTiles.isEmpty())
		{
			myTiles.add(randomTiles.remove(randomTiles.size()-1));
		}

	}
	public void removeMyTiles(String word) {


		for(int i=0;i<word.length();i++)
		{
			int index_nr=myTiles.indexOf(word.charAt(i));
			myTiles.remove(index_nr);

		}

	}
	public void gainScore(int i) {
		this.score+=i;

	}
}
