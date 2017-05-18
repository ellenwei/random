package sorryclient;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

/*
 * ColorSelector
 * Menu to select Red,Blue,Green,Yellow
 * */
public class ColorSelector extends JPanel {
	
	private static final long serialVersionUID = 1900724217285760485L;
	
	//The options for color selection
	private Color selection;
	private final int numOptions = 4;
	private final JButton[] optionButtons;
	
	private final JButton confirmButton;
	
	private final static String selectColorString = "Select your color";
	
	private final static String[] colorNames = {"Red", "Blue", "Green", "Yellow"};
	private final static Color[] colors = {Color.RED, Color.BLUE, Color.GREEN, Color.YELLOW};
	
	//For spacing to the borders
	private final static Insets spacing = new Insets(20,20,20,20);
	
	public Color getPlayerColor() {
		return selection;
	}
	
	public ColorSelector(ActionListener confirmAction) {
		//set up the button so we can proceed
		confirmButton = new JButton("Confirm");
		confirmButton.addActionListener(confirmAction);
		confirmButton.setEnabled(false);
		
		//The top of the panel, the label
		JLabel selectPlayerLabel = new JLabel(selectColorString);
		selectPlayerLabel.setFont(new Font("",Font.BOLD,36));
		
		JPanel topPanel = new JPanel();
		topPanel.add(selectPlayerLabel);
		
		//The middle of the panel, the color buttons
		JPanel centerPanel = new JPanel(new GridLayout(2,2,20,20));
		Font buttonFont = new Font("",Font.BOLD,22);
		optionButtons = new JButton[numOptions];
		for(int i = 0; i < numOptions; ++i) {
			optionButtons[i] = new JButton(colorNames[i]);
			optionButtons[i].setBackground(colors[i]);
			final int buttonSelection = i;
			optionButtons[i].addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent ae) {
					selection = colors[buttonSelection];
					for(JButton button : optionButtons) button.setEnabled(true);
					optionButtons[buttonSelection].setEnabled(false);
					confirmButton.setEnabled(true);
				}
			});
			optionButtons[i].setFont(buttonFont);
			centerPanel.add(optionButtons[i]);
		}
		centerPanel.setBorder(new EmptyBorder(spacing));
		
		//The bottom of the panel, the confirm button
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
