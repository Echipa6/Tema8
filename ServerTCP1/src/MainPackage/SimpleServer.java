package MainPackage;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class SimpleServer {
	// Define the port on which the server is listening
	public static final int PORT = 8101;
	public SimpleServer() throws IOException {
		ServerSocket serverSocket = null ;
		Table tablaJoc1=new Table();
		int nrPlayer=0;
		serverSocket = new ServerSocket(PORT);
		try {
			
			//while (true) {
				System.out.println ("Waiting for a client ...");
				
				Socket socket = serverSocket.accept();
				// Execute the client's request in a new thread
				
				nrPlayer++;
				String name="player"+nrPlayer;
				tablaJoc1.addPlayer(new Client(socket,name));
				System.out.println(tablaJoc1.players.get(0).name);
				tablaJoc1.startGame();
			//}
		} catch (IOException e) {
			System.err. println ("Ooops... " + e);
		} finally {
			serverSocket.close();
		}
	}
	
	public static void main ( String [] args ) throws IOException {
	
		SimpleServer server = new SimpleServer ();
	}
}