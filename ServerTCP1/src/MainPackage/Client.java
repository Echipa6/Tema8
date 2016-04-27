package MainPackage;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Vector;

import Usefull.AutomatSolver;

class Client{
	public String type;
	public Socket socket = null ;
	public BufferedReader in;
	public PrintWriter out;
	private AutomatSolver automatSolver;
	public String name;
	public int number;
	public Vector<Character> myTiles;

	public Client (Socket socket,int nr, String type ) 
	{
		this.type=type;
		if (type.contentEquals("automat"))
		{
			automatSolver=new AutomatSolver();
			this.name="Pc"+nr;
		}
		else
		{
			this.name="Player"+nr;
			this.number=nr;
			this.socket = socket ;
		}
		
		myTiles= new Vector<Character>();
		this.name="Player"+nr;
		this.number=nr;
		this.socket = socket ;

		try {
			this.in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			this.out = new PrintWriter(socket.getOutputStream());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

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
	public void getFirstTails(Vector <Character> randomTail)
	{
		addMyTiles(randomTail);
		out.println(myTiles.toString());
		out.flush();

	}


	public Vector<Character> getMyTiles() {
		return myTiles;
	}

	public void setMyTiles(Vector<Character> myTiles) {
		this.myTiles = myTiles;
	}


}