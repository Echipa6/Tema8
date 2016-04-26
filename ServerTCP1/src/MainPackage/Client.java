package MainPackage;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Vector;

class Client{
	public Socket socket = null ;
	public String name; 
	public BufferedReader in;
	public PrintWriter out;
	public Vector<Character> myTiles;

	public Client (Socket socket,String name) 
	{
		myTiles= new Vector<Character>();
		this.name=name;
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