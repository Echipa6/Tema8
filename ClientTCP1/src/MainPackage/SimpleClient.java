package MainPackage;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;
import java.util.Vector;

public class SimpleClient {
	
	static PrintWriter out;
	static BufferedReader in;
	
	private static Vector<Character> myTiles;
	public static void setLabelActive( String tails )
	{
		
		GUI.labelPlayer4.setText("<html>"+tails+"<br>__________________<br><font color='red'>Player <br></font></html>");

	}
	
	
	public static void main (String[] args) throws IOException {
		
		String serverAddress = "127.0.0.1"; // The server's IP address
		int PORT = 8102; // The server's port
		Socket socket = new Socket(serverAddress, PORT);
		out =new PrintWriter(socket.getOutputStream(), true);
		in = new BufferedReader (new InputStreamReader(socket.getInputStream()));
		Scanner keyboard = new Scanner(System.in);
		 GUI  swingControlDemo = new GUI(); 
		try {
			// citesc prima asignare de tail-uri
			setLabelActive(in.readLine());
			
			String response;
			String command;
			while(true)
			{
				
				System.out.println("enter an value");
				// Send a request to the server
				//String request = keyboard.next();
				//out.println(request);
				// Wait the response from the server ("Hello World!")
				//response = in.readLine();
				command=in.readLine();
				if(command.contentEquals("notifyme"))
				{
					System.out.println("norificare");
					response=in.readLine();
					GUI.textArea.append("[Me]"+response+"\n");
					
				}
				if(command.contentEquals("notify"))
				{
					System.out.println("norificare");
					response=in.readLine();
					GUI.textArea.append("[Player1]"+response+"\n");
				}
				if(command.contentEquals("wordValid"))
				{
					System.out.println(" Cuvant valid! Felicitari");
					response = in.readLine();
					System.out.println(" Activare");
					System.out.println(response);
					setLabelActive(response);
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
}