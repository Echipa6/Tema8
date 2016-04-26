package MainPackage;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class SimpleClient {
	
	static PrintWriter out;
	static BufferedReader in;
	
	
	public static void main (String[] args) throws IOException {
		
		String serverAddress = "192.168.0.103"; // The server's IP address
		int PORT = 8100; // The server's port
		Socket socket = new Socket(serverAddress, PORT);
		out =new PrintWriter(socket.getOutputStream(), true);
		in = new BufferedReader (new InputStreamReader(socket.getInputStream()));
		Scanner keyboard = new Scanner(System.in);
		 GUI  swingControlDemo = new GUI(); 
		try {
			while(true)
			{
				
				System.out.println("enter an value");
				// Send a request to the server
				//String request = keyboard.next();
				//out.println(request);
				// Wait the response from the server ("Hello World!")
				String response = in.readLine ();
				System.out.println(response);
				
			}
		} catch (UnknownHostException e) {
			System.err.println("No server listening... " + e);
		}
	}
}