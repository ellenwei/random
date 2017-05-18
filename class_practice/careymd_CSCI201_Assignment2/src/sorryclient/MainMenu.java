package sorryclient;

import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

/*
 * MainMenu
 * Menu to hold the start button
 * */
public class MainMenu extends JPanel{
	private static final long serialVersionUID = 3609831945869059312L;
	
	private final JButton start;
	
	private final String menuTitleString = "Sorry!";
	
	public MainMenu(ActionListener startAction){
		start = new JButton("Start");
		start.addActionListener(startAction);
		
		JLabel titleLabel = new JLabel(menuTitleString);
		titleLabel.setFont(new Font("",Font.BOLD,72));
		
		setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();

		gbc.gridy = 1;
		add(titleLabel,gbc);
		gbc.gridy = 2;
		add(start,gbc);
	}
	
}
