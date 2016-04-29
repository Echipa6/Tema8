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
	private static String myTiles;
	private static Vector<Integer> score;
	public static void setLabel(String name, int nrPlayer)
	{
		if(nrPlayer!=number)
		{
			GUI.playersLabels.elementAt(nrPlayer).setName(name);
			GUI.playersLabels.elementAt(nrPlayer).setText("<html>"+"<br>__________________<br>Score: "+score.get(nrPlayer) +"<br><font color='blue'>"+name+"<br></font></html>");
		}
	}
	public static void setLabelFirst( String tails)
	{
		myTiles=tails;
		GUI.playersLabels.elementAt(number).setText("<html>"+tails+"<br>__________________<br>Score: "+score.get(number)+"<br>"+GUI.playersLabels.elementAt(number).getName()+"<br></html>");
		GUI.playersLabels.elementAt(number).setForeground(Color.blue);

	}

	public static void setLabelActive(int number)
	{
		GUI.playersLabels.elementAt(number).setText("<html>[X,X,X,X,X,X,X]<br>__________________<br>Score: "+score.get(number)+"<br>"+GUI.playersLabels.elementAt(number).getName()+"<br></html>");
		GUI.playersLabels.elementAt(number).setForeground(Color.red);


	}
	private static void colorRedLabel() {

		GUI.playersLabels.elementAt(number).setForeground(Color.red);
	}


	public static void main (String[] args) throws IOException {

		String serverAddress = "127.0.0.1"; // The server's IP address
		int PORT = 8102; // The server's port
		socket = new Socket(serverAddress, PORT);
		out =new PrintWriter(socket.getOutputStream(), true);
		in = new BufferedReader (new InputStreamReader(socket.getInputStream()));
		Scanner keyboard = new Scanner(System.in);
		GUI  swingControlDemo = new GUI();
		score= new Vector<Integer>();
		
		score.add(0);
		score.add(0);
		score.add(0);
		score.add(0);
		
		try {
		
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
						if(i==number)
						{
							GUI.playersLabels.elementAt(number).setText("<html>"+myTiles+"<br>__________________<br>Score: "+score.get(number)+"<br>"+GUI.playersLabels.elementAt(number).getName()+"<br></html>");
						}
						else
						{
							GUI.playersLabels.elementAt(i).setText("<html>[X,X,X,X,X,X,X]<br>__________________<br>Score: "+score.get(i)+"<br>"+GUI.playersLabels.elementAt(i).getName()+"<br></html>");
						}
						GUI.playersLabels.elementAt(i).setForeground(Color.blue);
					}
				}
				if(command.contentEquals("notify"))
				{
					System.out.println("notificare");
					response=in.readLine();
					GUI.textArea.append(response+"\n\n");
					int aux=in.read();
					score.set(turnPlayer, aux+score.get(turnPlayer));
				}
				if(command.contentEquals("EmptyBag"))
				{

					GUI.textArea.append("GAME OVER"+"\n\n");

				}
				
				if(command.contentEquals("0exit"))
				{
					response="Celalalt jucator a iesit din joc. ne pare rau dar nu mai puteti continua jocul.";
					System.out.println(response);
					GUI.textArea.append(response+"\n\n");
				}
				if(command.contentEquals("wordValid"))
				{
					response = in.readLine();
					System.out.println(response);
					setLabelFirst(response);
				}
				if(command.contentEquals("wordInvalid"))
				{
					GUI.textArea.append("Word Invalid!\n");

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

			
			setLabel(name,numberPlayer);
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
		number=in.read();
		GUI.shiftLabels(number);
		GUI.playersLabels.elementAt(number).setName(name);
		
		setLabelFirst(in.readLine());

		System.out.println("Eu sunt jucatorul cu numarul: "+number);

	}
}