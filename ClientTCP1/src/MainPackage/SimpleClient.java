package MainPackage;

import java.awt.Color;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;
import java.util.Vector;

import javax.swing.JOptionPane;

public class SimpleClient {

	static PrintWriter out;
	static BufferedReader in;
	static Socket socket;
	public static String name;
	public static int number;
	public static int turnPlayer;
	private static Vector<Character> myTiles;
	public static void setLabel(String name, int nrPlayer)
	{
		GUI.playersLabels.elementAt(nrPlayer).setName(name);
		GUI.playersLabels.elementAt(nrPlayer).setText("<html>"+"<br>__________________<br><font color='blue'>"+name+"<br></font></html>");

	}
	public static void setLabelFirst( String tails)
	{

		GUI.playersLabels.elementAt(3).setText("<html>"+tails+"<br>__________________<br>"+GUI.playersLabels.elementAt(3).getName()+"<br></font></html>");
		GUI.playersLabels.elementAt(3).setForeground(Color.blue);

	}

	public static void setLabelActive(int number)
	{
		if(number==3)
		{
			GUI.playersLabels.elementAt(SimpleClient.number).setText("<html>[X,X,X,X,X,X,X]<br>__________________<br>"+GUI.playersLabels.elementAt(number).getName()+"<br></font></html>");
			GUI.playersLabels.elementAt(SimpleClient.number).setForeground(Color.red);

		}
		else
		{
			GUI.playersLabels.elementAt(number).setText("<html>[X,X,X,X,X,X,X]<br>__________________<br>"+GUI.playersLabels.elementAt(number).getName()+"<br></font></html>");
			GUI.playersLabels.elementAt(number).setForeground(Color.red);
		}


	}
	private static void colorRedLabel() {

		GUI.playersLabels.elementAt(3).setForeground(Color.red);
	}


	public static void main (String[] args) throws IOException {

		String serverAddress = "127.0.0.1"; // The server's IP address
		int PORT = 8102; // The server's port
		socket = new Socket(serverAddress, PORT);
		out =new PrintWriter(socket.getOutputStream(), true);
		in = new BufferedReader (new InputStreamReader(socket.getInputStream()));
		Scanner keyboard = new Scanner(System.in);
		GUI  swingControlDemo = new GUI();
		try {
			// citesc prima asignare de tail-uri
			//

			String response;
			String command;
			turnPlayer = 0;
			while(true)
			{

				System.out.println("enter an value");
				command=in.readLine();
				if(command.contentEquals("inception"))
				{
					inception();

				}
				if(command.contentEquals("meeting"))
				{
					meeting();
				}

				if(command.contentEquals("turn"))
				{
					turnPlayer=in.read();
					System.out.println("incepe runda jucatorului:"+turnPlayer);

					if(turnPlayer==number)
					{
						GUI.submit.setEnabled(true);
						colorRedLabel();
					}
					else
					{
						GUI.submit.setEnabled(false);
						setLabelActive(turnPlayer);
					}

				}
				if(command.contentEquals("endTurn"))
				{
					for(int i=0; i<4;i++)
					{
						GUI.playersLabels.elementAt(i).setForeground(Color.blue);
					}
				}
				if(command.contentEquals("notify"))
				{
					System.out.println("norificare");
					response=in.readLine();
					GUI.textArea.append(response+"\n\n");
				}
				if(command.contentEquals("wordValid"))
				{
					response = in.readLine();
					System.out.println(response);
					setLabelFirst(response);
				}
				else
				{
					System.out.println( "INVALID!");
				}


			}
		} catch (UnknownHostException e) {
			System.err.println("No server listening... " + e);
		}
	}


	private static void meeting() throws IOException 
	{
		while(in.readLine().contains("player"))
		{
			int numberPlayer=in.read();
			String name=in.readLine();

			if(numberPlayer!=number)
			{
				if(numberPlayer==3)
				{
					setLabel(name,number);
				}
				else
				{
					setLabel(name,numberPlayer);
				}

			}
		}


	}


	public static void inception() throws IOException
	{
		GUI.submit.setEnabled(false);
		name="Me";
		String namePlayer = JOptionPane.showInputDialog(GUI.mainFrame, "What's your player name?");
		SimpleClient.out.println(namePlayer);
		name=namePlayer;
		SimpleClient.out.flush();
		GUI.playersLabels.elementAt(3).setName(name);
		number=in.read();
		setLabelFirst(in.readLine());
		
		System.out.println("Eu sunt jucatorul cu numarul: "+number);

	}
}