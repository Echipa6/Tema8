package MainPackage;

import java.io.IOException;
import java.util.Vector;

import javax.swing.JTextArea;

import Usefull.HTTPXML;

class Table {
	public int nrPlayers;
	JTextArea textArea;
	private boolean available = false;
	private BagTiles bagTiles;
	Vector<Client> players;

	Integer currentPlayer;

	Dictionary dictionary;

	public String wordDefinition;

	public Table()
	{
		nrPlayers=0;
		currentPlayer=0;
		try {
			bagTiles=new BagTiles();
			dictionary= new Dictionary();
		} catch (IOException e1) {

			e1.printStackTrace();
		}

		players=new Vector<Client>();

	}
	void addPlayer(Client player)
	{
		nrPlayers++;
		players.add(player);
	}



	public Vector<Character> getMissedTiles(int currentNumberTiles)
	{ 
		if(bagTiles.bag.isEmpty())
		{
			this.textArea.append("GAME OVER! The bag is empty.");
			try {
				Thread.sleep(100000);
			} catch (InterruptedException e) {

				e.printStackTrace();
			}
		}


		return bagTiles.getTiles(7-currentNumberTiles);

	}

	private void reloadTail(String word)
	{
		////		Player currentPlayer=players.elementAt(currentPlayerNumber);
		////		System.out.println("Player"+(currentPlayerNumber+1)+" "+word);
		////		this.textArea.append("Player"+(currentPlayerNumber+1)+" "+word+'\n');
		////		currentPlayer.gainScore(word.length()*5);
		////		currentPlayer.removeMyTiles(word);
		//
		//		currentPlayer.addMyTiles(getMissedTiles(currentPlayer.getNumberTiles()));

	}

	public void inception()
	{
		System.out.println("inception");
		for(Client player : players )
		{
			if(player.type.contentEquals("manual"))
			{
				player.out.println("inception");
				player.out.flush();

				String name = null;
				try {
					name=player.in.readLine();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				if(!name.isEmpty())
				{
					player.name=name;
				}

				System.out.println(player.name);

				player.out.write(player.number);
				System.out.println("am trimis la client numarul:"+ player.number);
				player.out.flush();

				player.getFirstTails(getMissedTiles(0));
			}
			else
			{
				System.out.println("playerul automat");
				player.addMyTiles(getMissedTiles(0));
			}

		}
	}

	public void meeting()
	{
		//for(Client player : players)
		for(int i=0;i<2;i++)
		{
			Client player=players.elementAt(i);

			if(player.type.compareTo("manual")==0);
			{
				System.out.println(player.type.compareTo("manual"));
				System.out.println(player.name+" "+player.type);
				player.out.println("meeting");
				player.out.flush();
				
				for(Client playerSent:players)
				{
					player.out.println("player");
					player.out.flush();

					player.out.write(playerSent.number);
					player.out.flush();

					player.out.println(playerSent.name);
					player.out.flush();


				}
				player.out.println("end");
				player.out.flush();
			}
		}
	}
	public void startGame() {

		// comunicam server client si ii dam runda clientului respectiv 

		inception();
		meeting();	
		while(true)
		{

			try {
				String request=null;
				startRound(currentPlayer.intValue());
				if(players.elementAt(currentPlayer).type.contentEquals("manual"))
				{
					request=players.elementAt(currentPlayer).in.readLine();
				}
				else
				{
					request=players.elementAt(currentPlayer).automatSolver.getWord(players.elementAt(currentPlayer).myTiles);
				}

				processWord(request);

				endRound();


			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}
	private void startRound(int currentPlayer) {

		for(Client player : players )
		{
			if(player.type.contentEquals("manual"))
			{
				player.out.println("turn");
				player.out.flush();


				player.out.write(currentPlayer);
				player.out.flush();
			}

		}

	}
	private void endRound() {

		for(Client player : players )
		{
			if(player.type.contentEquals("manual"))
			{
				player.out.println("endTurn");
				player.out.flush();
			}

		}

	}


	public void notifyAll2(String word)
	{


		HTTPXML dictionary=new HTTPXML();

		wordDefinition=null;
		try {
			wordDefinition=dictionary.SearchDefinition(word);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		//notifyClient.out.print(currentPlayer.toString());
		for(Client  notifyClient:players)
		{ 
			if(notifyClient.type.contentEquals("manual"))

			{
				notifyClient.out.println("notify");
				notifyClient.out.println("["+players.get(currentPlayer).name+"]"+word+"\t"+wordDefinition);
				notifyClient.out.flush();
			}
		}
	}
	void processWord(String word)
	{
		Client roundPlayer=players.elementAt(currentPlayer);
		if(validate(word))
		{
			System.out.println(roundPlayer.myTiles.toString());

			System.out.println(word+" Cuvantul este bun ");
			if(roundPlayer.type.contentEquals("manual"))
			{

				roundPlayer.out.println("wordValid");
				roundPlayer.out.flush();
			}
			roundPlayer.removeMyTiles(word);
			roundPlayer.addMyTiles(this.getMissedTiles(roundPlayer.myTiles.size()));
			if(roundPlayer.type.contentEquals("manual"))
			{
				roundPlayer.out.println(roundPlayer.myTiles.toString());
				roundPlayer.out.flush();
			}
			notifyAll2(word);

			currentPlayer=(currentPlayer+1)%4;


		}
		else
		{
			System.out.println(word+" Cuvantul nu este bun ");
			roundPlayer.out.println("wordInvalid");
			roundPlayer.out.flush();
		}
	}


	private boolean validate(String wordToValidate) {
		boolean ok=true;
		String auxWord=wordToValidate;
		Vector<Character> auxTiles=new Vector<Character>(players.elementAt(currentPlayer).myTiles);
		if(dictionary.voc.isPrefix(wordToValidate))
		{
			for(int i=0;i<auxWord.length();i++)
			{
				int index_nr=auxTiles.indexOf(auxWord.charAt(i));
				if(index_nr==-1)
				{
					//System.out.println(auxTiles.toString());
					System.out.println("nu avem litera"+auxWord.charAt(i));
					return false;
				}
				else
				{
					auxTiles.remove(index_nr);
				}


			}
			return ok && dictionary.voc.getNode(wordToValidate).isWord();
		}
		return false;
	}


}


