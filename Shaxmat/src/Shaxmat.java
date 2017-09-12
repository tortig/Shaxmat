import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Shaxmat extends JFrame implements ActionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	int a, b, c, d;               //  cordinats of figures
	
	JPanel panel1 = new JPanel();
	JPanel panel2 = new JPanel();
	JPanel panel3 = new JPanel();
	JPanel panel4 = new JPanel();
	JPanel panel5 = new JPanel();
	
    
    Icon icon1 = new ImageIcon("1.jpg");	// black field
    Icon icon2 = new ImageIcon("2.jpg");	// white field
    Icon icon3 = new ImageIcon("3.jpg");	// Rook on black field
    Icon icon4 = new ImageIcon("4.jpg");	// Rook on white field
    Icon icon5 = new ImageIcon("5.jpg");	// Soldier on black field
    Icon icon6 = new ImageIcon("6.jpg");	// Soldier on white field
    
    JLabel label1[] = new  JLabel[8];		// vertical numbers
    JLabel label2[] = new  JLabel[8];		// horizontal numbers
    JLabel label[][] = new JLabel[8][8];	// play desk
    
    JLabel label3 = new  JLabel("   Rook---");
	JTextField text1= new JTextField(5);	// a cordinat 
	JTextField text2= new JTextField(5); 	// b cordinat
	
	JLabel label4 = new  JLabel("      Soldier---");
	JTextField text3= new JTextField(5);	// c cordinat
	JTextField text4= new JTextField(5);	// d cordinat
	
	JButton buttonInsert = new JButton("Insert");	// insert button
	JTextField text5= new JTextField(20);			// game status messege
	JButton buttonHit = new JButton("Hit");			// hit button
	JButton buttonReplay = new JButton("Replay");	// replay button
	
	
	public Shaxmat(){
		
		setLayout(new FlowLayout());
		
		panel4.setLayout(new GridLayout());	// panel for inputing cordinats
		panel4.add(label3);
		panel4.add(text1);
		panel4.add(text2);
		panel4.add(label4);
		panel4.add(text3); 
		panel4.add(text4);
		
		panel3.setLayout(new GridLayout(1, 8, 23, 0));	// horizontal numeration
		
		for(int i = 0; i < 8; i++){
			label2[i] =  new JLabel();
			panel3.add(label2[i]);
			label2[i].setText("       " + (i + 1) + "");	
		}
		
		panel2.setLayout(new GridLayout(8, 1, 5, 35));	// vertical numeration
		
		for(int i = 0; i < 8; i++){
			label1[i] =  new JLabel();
			panel2.add(label1[i]);
			label1[i].setText("    " + (8 - i) + "      ");		
		}
		
		panel1.setLayout(new GridLayout(8, 8, 0, 0));	// play desk panel
		
		for(int i = 0; i < 8; i++){
			for(int j = 0; j < 8; j++){
				label[i][j] = new JLabel();
				panel1.add(label[i][j]);
			if((i + j) % 2 == 0)
					label[i][j].setIcon(icon1);
				else
					label[i][j].setIcon(icon2);
			}
		}
		
		panel5.setLayout(new GridLayout(4,1,5,5));
		panel5.add(buttonInsert);
		panel5.add(text5);
		panel5.add(buttonHit);
		panel5.add(buttonReplay);
		add(panel2);
		add(panel1);
		add(panel3);
		add(panel4);
		add(panel5);
		buttonInsert.addActionListener(this);
		buttonHit.addActionListener(this);
		buttonReplay.addActionListener(this);
		setSize(480, 700);
		setVisible(true);
		setTitle("Chess Game ");
		setResizable(false);
		setLocation(400, 30);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource() == buttonInsert){
			
			a = Integer.parseInt(text1.getText());
			b = Integer.parseInt(text2.getText());
			c = Integer.parseInt(text3.getText());
			d = Integer.parseInt(text4.getText());
		
			if(a == c || b == d )			// Rook can hit Soldier
				text5.setText("Rook@ harvacum e Soldierin ");
			
			if(a == c + 1 && (b == d + 1 || b == d - 1) )		// Soldier can hit Rook
				text5.setText("Soldier@ harvacum e Rookin ");
			
			if(a < 0 || a > 8 || b < 0 || b > 8 || c < 0 || c > 8 || d < 0 || d > 8){	// wrong cordinats
				text5.setText("Shaxmati taxtak@ 8x8 chapsi e ");
				buttonInsert.setEnabled(false);
				buttonHit.setEnabled(false);
			}
		
			if((a + b) % 2 == 0)		// check if field white
				label[8 - a][b - 1].setIcon(icon4);
		
			else
				label[8 - a][b - 1].setIcon(icon3);
		
			if((c + d) % 2 == 0)		// check if field white
				label[8 - c][d - 1].setIcon(icon6);
		
			else
				label[8 - c][d - 1].setIcon(icon5);
		
			if(a != c && b != d && a != c + 1){		// figures don't hit each other
				text5.setText("Vtang chka ");
				buttonHit.setEnabled(false);
			}
		
			if((a == c && b == d) && (a + b) % 2 == 0 ){	// if put figures in one white field
				text5.setText("Tarber texer texadreq Figurner@");
				label[8-a][b-1].setIcon(icon2);
				buttonHit.setEnabled(false);
			}
		
			if((a == c && b == d) && (a + b) % 2 != 0 ){	//// if put figures in one black field
				text5.setText("Tarber texer texadreq Figurner@");
				label[8-a][b-1].setIcon(icon1);
				buttonHit.setEnabled(false);
			}
		
		buttonInsert.setEnabled(false);
		
	}
		
		
		
		if(e.getSource() == buttonHit){
			
			if((a == c || b == d) && (a + b) % 2 == 0 && (c + d) % 2 == 0){		// white Rook hit Soldier on white field
				label[8 - c][d - 1].setIcon(icon4);
		        label[8 - a][b - 1].setIcon(icon2);
		    }
			
			if((a == c || b == d) && (a + b) % 2 == 0 && (c + d) % 2 != 0){		// white Rook hit Soldier on black field
				label[8 - c][d - 1].setIcon(icon3);
				label[8 - a][b - 1].setIcon(icon2);
			}
		
			if((a == c || b == d) && (a + b) % 2 != 0 && (c + d) % 2 == 0){		// black Rook hit Soldier on white field
				label[8 - c][d - 1].setIcon(icon4);
				label[8 - a][b - 1].setIcon(icon1);
			}
			
			if((a == c || b == d) && (a + b) % 2 != 0 && (c + d) % 2 != 0){		// black Rook hit Soldier on black field
				label[8 - c][d - 1].setIcon(icon3);
				label[8 - a][b - 1].setIcon(icon1);
			}
			
			if((a == c + 1 && (b == d + 1 || b == d - 1) ) && (c + d) % 2 == 0){	// white Soldier hit white Rook
				label[8 - c][d - 1].setIcon(icon2);
				label[8 - a][b - 1].setIcon(icon6);
			}
			
			if((a == c + 1 && (b == d + 1 || b == d - 1) ) && (c + d) % 2 != 0){	// black Soldier hit black Rook
				label[8 - c][d - 1].setIcon(icon1);
				label[8 - a][b - 1].setIcon(icon5);
			}
			
		buttonHit.setEnabled(false);
		
	}
		
		
		if(e.getSource() == buttonReplay){
			
			for(int i = 0; i < 8; i++){			// create new play field
				for(int j = 0; j < 8; j++){	
					if((i + j) % 2 == 0)
						label[i][j].setIcon(icon1);
					else
						label[i][j].setIcon(icon2);
				}
			}
			
			text1.setText("");
			text2.setText("");
			text3.setText("");
			text4.setText("");
			text5.setText("");
			buttonInsert.setEnabled(true);
			buttonHit.setEnabled(true);
			
		}
			
	}

}
