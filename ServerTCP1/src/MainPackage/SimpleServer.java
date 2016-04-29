package MainPackage;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class SimpleServer {
	// Define the port on which the server is listening
	public static final int PORT = 8102;
	public SimpleServer() throws IOException {
		ServerSocket serverSocket = null ;


		serverSocket = new ServerSocket(PORT);
		try {
			while(true)
			{
				Table tablaJoc1=new Table();
				System.out.println ("Waiting for a client ...");

				Socket socket = serverSocket.accept();			

				tablaJoc1.addPlayer(new Client(socket,tablaJoc1.nrPlayers,"manual"));
				socket= serverSocket.accept();

				tablaJoc1.addPlayer(new Client(socket,tablaJoc1.nrPlayers,"manual"));


				tablaJoc1.addPlayer(new Client(null,tablaJoc1.nrPlayers,"automat"));
				tablaJoc1.addPlayer(new Client(null,tablaJoc1.nrPlayers,"automat"));

				tablaJoc1.start();
			}

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