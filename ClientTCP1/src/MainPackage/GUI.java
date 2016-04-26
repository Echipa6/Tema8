package MainPackage;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;


public class GUI {
	public static int width=900;
	public static int height=600;
	
	static JLabel statusLabel;

	public static JFrame mainFrame;
	private static JTextArea textArea;
	private static JLabel labelPlayer1;
	private static JLabel labelPlayer2;
	private static JLabel labelPlayer3;
	private static JLabel labelPlayer4;
	
	private static JTextField  wordToSubmit;
	private static JButton submit;
	GUI(){

		mainFrame = new JFrame("Word Game");
		mainFrame.setSize(width,height);
		mainFrame.setResizable(false);
		mainFrame.setLayout(new BorderLayout());


		mainFrame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent windowEvent){
				System.exit(0);
			}        
		});

		
	   
	   
		ImageIcon imageIcon = new ImageIcon("PlayerLeft.png");
		labelPlayer1=new JLabel("Player1",imageIcon,JLabel.CENTER);
		labelPlayer1.setHorizontalAlignment(JLabel.CENTER);

		
		
		imageIcon = new ImageIcon("PlayerTop.png");
		labelPlayer2=new JLabel("Player2",imageIcon,JLabel.CENTER);
		labelPlayer2.setHorizontalAlignment(JLabel.CENTER);

		
		imageIcon = new ImageIcon("PlayerRight.png");
		labelPlayer3=new JLabel("Player3",imageIcon,JLabel.CENTER);
		labelPlayer3.setHorizontalAlignment(JLabel.CENTER);

		
		JPanel playerBottomPanel =new JPanel();
		
		labelPlayer4=new JLabel("Player4");
		labelPlayer4.setHorizontalAlignment(JLabel.CENTER);
		
		wordToSubmit= new JTextField(10);
		wordToSubmit.setVisible(true);
		submit=new JButton("submit");
			
		playerBottomPanel.add(wordToSubmit);
		playerBottomPanel.add(submit);
		
		playerBottomPanel.add(labelPlayer4);
		
		
		
		JPanel middlePanel=new JPanel();
		middlePanel.setPreferredSize(new Dimension(300,300));
		textArea= new JTextArea();
		JScrollPane scroll = new JScrollPane(textArea);
		scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);



		middlePanel.add(scroll);
		scroll.setPreferredSize(scroll.getParent().getPreferredSize());
		scroll.setBorder(new TitledBorder(new EtchedBorder(), "GameTable"));
		mainFrame.add(middlePanel, BorderLayout.CENTER);
		middlePanel.setPreferredSize(middlePanel.getParent().getPreferredSize());
		
		statusLabel = new JLabel("",JLabel.RIGHT);
		
		JPanel northPlayer= new JPanel();
		northPlayer.add(labelPlayer2);
		northPlayer.add(statusLabel,BorderLayout.EAST);

		//mainFrame.add(statusLabel,BorderLayout.NORTH);
		mainFrame.add(labelPlayer1, BorderLayout.WEST);
		mainFrame.add(northPlayer, BorderLayout.NORTH);
		mainFrame.add(labelPlayer3, BorderLayout.EAST);
		
		mainFrame.add(playerBottomPanel, BorderLayout.SOUTH);
		
		submit.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent evt) {
		        String word=wordToSubmit.getText();
		        //((ManualSolver)c4.solver).setWordToValidate(word.toUpperCase());
		        SimpleClient.out.println(word);
		        
		       
		        wordToSubmit.setText("");
		    }
		}
		);
		
		mainFrame.setVisible(true); 
	}

//	GUI()
//	{
//
//	mainFrame = new JFrame("Java Swing Examples");
//	mainFrame.setSize(500,400);
//	mainFrame.setLayout(new BorderLayout());
//	mainFrame.addWindowListener(new WindowAdapter() {
//		public void windowClosing(WindowEvent windowEvent){
//			System.exit(0);
//		}        
//	});
//	Button startButton = new Button("Start");
//	 startButton.addActionListener(new ActionListener() {
//         public void actionPerformed(ActionEvent e) {
//        	 
//          		  System.out.println("mutare invalida");
//          		SimpleClient.out.println("mutare invalida");
//          	  }
//         });
//	mainFrame.add(startButton,BorderLayout.CENTER);
//    mainFrame.add(new Button("Connect"),BorderLayout.NORTH);
//    mainFrame.setVisible(true); 
//	}
	
	public static void main(String[] args){
	      GUI  swingControlDemo = new GUI();      
	       
	   }

}
