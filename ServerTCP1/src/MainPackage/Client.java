package MainPackage;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

class Client{
	public Socket socket = null ;
	public String name; 
	public BufferedReader in;
	public PrintWriter out;

	public Client (Socket socket,String name) 
	{
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
	

}