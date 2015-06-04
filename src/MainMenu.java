import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;

public class MainMenu extends JFrame {

	private static final long serialVersionUID = 1L;
	
	int screenWidth = 275;
	int screenHeight = 160;
	
	int buttonWidth = 100;
	int buttonHeight = 40;
	
	JButton play, quit;
	JCheckBox twoPlayers;

	public MainMenu() {
		addButtons();
		addActions();
		
		getContentPane().setLayout(null);
		
		play.setBounds((screenWidth - buttonWidth) / 2, 5, buttonWidth, buttonHeight); // Positions the buttons
		quit.setBounds((screenWidth - buttonWidth) / 2, 50, buttonWidth, buttonHeight);
		twoPlayers.setBounds((screenWidth - buttonWidth) / 2, 95, buttonWidth, buttonHeight);
		
		// Adding buttons to the JFrame ContentPane
		getContentPane().add(play);
		getContentPane().add(quit);
		getContentPane().add(twoPlayers);
		
		// JFrame stuff
		pack();
		setVisible(true);
		setLocationRelativeTo(null);
		setSize(screenWidth, screenHeight);
		setTitle("Pong Menu");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
	}
	
	private void addButtons(){
		play = new JButton("Play");
		quit = new JButton("Quit");
		twoPlayers = new JCheckBox("2 Players?");
	}

	private void addActions(){

		play.addActionListener(new ActionListener() { // Take Play button, add new actionlistener
					public void actionPerformed(ActionEvent e) { // Turn the action performed into a variable for usage
						dispose();

						Game game = new Game();

						if (twoPlayers.isSelected()) {
							game.ai.isTwoPlayer = true;
						} else {
							game.ai.isTwoPlayer = false;
						}

						game.start();
					}
				});// Play button

		quit.addActionListener(new ActionListener() { // Take Quit button, add new actionlistener
					public void actionPerformed(ActionEvent e) { // Turn the action performed into a variable for usage
						System.exit(0); // Shut down the program
					}
				});// Quit button
	}
}
