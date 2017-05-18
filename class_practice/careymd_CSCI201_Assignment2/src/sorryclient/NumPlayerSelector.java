package sorryclient;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.border.EmptyBorder;

/*
 * NumPlayerSelector
 * Menu to select a number of players 2-3
 * */
public class NumPlayerSelector extends JPanel {
	private static final long serialVersionUID = -4510696620583889943L;
	
	//The options for number of player selection
	private int selection = -1;
	private final int numOptions = 3;
	private final JRadioButton[] optionButtons;
	private final ButtonGroup buttonGroup;
	
	private JButton confirmButton;
	
	private final String selectNumPlayerString = "Select the number of players";
	
	//The spacing of the border
	private static final Insets spacing = new Insets(20,20,20,20);
	
	public int getNumberOfPlayers() {
		return selection;
	}
	
	public NumPlayerSelector(ActionListener confirmAction){
		//set up the button so we can proceed
		confirmButton = new JButton("Confirm");
		confirmButton.addActionListener(confirmAction);
		confirmButton.setEnabled(false);
		
		//The top of the panel, the label
		JLabel selectPlayerLabel = new JLabel(selectNumPlayerString);
		selectPlayerLabel.setFont(new Font("",Font.BOLD,36));
		
		JPanel topPanel = new JPanel();
		topPanel.add(selectPlayerLabel);
		
		//The center panel to hold the button panel
		JPanel centerPanel = new JPanel();
		centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
		
		//The button panel to hold the buttons
		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS));
		
		buttonGroup = new ButtonGroup();
		optionButtons = new JRadioButton[numOptions];
		for(int i = 0; i < numOptions; ++i) {
			JPanel numPanel = new JPanel();
			optionButtons[i] = new JRadioButton(""+(i+2));
			optionButtons[i].setOpaque(false);
			final int buttonSelection = i+2;
			optionButtons[i].addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					selection = buttonSelection;
					confirmButton.setEnabled(true);
				}
			});
			buttonGroup.add(optionButtons[i]);
			numPanel.add(optionButtons[i]);
			
			buttonPanel.add(Box.createGlue());
			buttonPanel.add(numPanel);
			buttonPanel.add(Box.createGlue());
		}
		
		centerPanel.add(Box.createGlue());
		centerPanel.add(buttonPanel);
		centerPanel.add(Box.createGlue());
		
		//The bottom panel to hold the confirm button
		JPanel bottomPanel = new JPanel();
		bottomPanel.setLayout(new BoxLayout(bottomPanel,BoxLayout.X_AXIS));
		bottomPanel.setBorder(new EmptyBorder(spacing));
		bottomPanel.add(Box.createGlue());
		bottomPanel.add(confirmButton);
		
		setLayout(new BorderLayout());
		add(topPanel, BorderLayout.NORTH);
		add(centerPanel, BorderLayout.CENTER);
		add(bottomPanel, BorderLayout.SOUTH);
	}
}
