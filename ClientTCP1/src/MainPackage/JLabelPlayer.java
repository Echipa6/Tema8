package MainPackage;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class JLabelPlayer extends JLabel{
  
	public String nume;
	public String getNume() {
		return nume;
	}

	public void setNume(String nume) {
		this.nume = nume;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	int score;
	
	public JLabelPlayer(String string, ImageIcon imageIcon, int center) {
		
	  super(string,imageIcon,center);
	  
	}

	public JLabelPlayer(String string) {
		super(string);
	}

}
