package sorryclient;

import java.awt.Color;
import java.awt.Component;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Stack;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import game.GameHelpers;
import game.GameManager;
import game.Tile;

/*
 * GamePanel
 * Panel to hold the graphical game
 * */
public class GamePanel extends JPanel {
	private static final long serialVersionUID = 1593344194657112518L;
	
	//A grid to hold all the tiles in the game
	private final static int boardSize = 16;
	private final TilePanel[][] tileGrid;
	
	//The card button for the game
	private final JButton cardButton;
	private final JLabel cardLabel;
	
	//The game manager that runs the actual logic
	private final GameManager mGameManager;
	
	//The way to exit the game menu
	private final ActionListener mQuitAction;
	
	{
		//Create and set-up the card button
		cardButton = new JButton();
		cardButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				mGameManager.drawCard();
				redraw();
			}
		});
		cardLabel = new JLabel("Cards:");
	}
	
	public GamePanel(ActionListener inQuitAction, GameManager inGameManager){
		mQuitAction = inQuitAction;
		mGameManager = inGameManager;
		
		//Create the GUI to be a grid for all the tiles
		setLayout(new GridLayout(boardSize,boardSize));
		tileGrid = new TilePanel[boardSize][boardSize];
		
		//Create each grid square
		//Either give it a Start/Home label panel, or a TilePanel
		for(int y = 0; y < boardSize; ++y) {
			for(int x = 0; x < boardSize; ++x) {
				if(x == 4 && y == 2) {tileGrid[x][y] = new StartLabelPanel(GameHelpers.getIndexFromColor(Color.YELLOW));}
				else if (x == 2 && y == 7) {tileGrid[x][y] = new HomeLabelPanel(GameHelpers.getIndexFromColor(Color.YELLOW));}
				else if(x == 13 && y == 4) {tileGrid[x][y] = new StartLabelPanel(GameHelpers.getIndexFromColor(Color.GREEN));}
				else if(x == 8 && y == 2) {tileGrid[x][y] = new HomeLabelPanel(GameHelpers.getIndexFromColor(Color.GREEN));}
				else if(x == 11 && y == 13) {tileGrid[x][y] = new StartLabelPanel(GameHelpers.getIndexFromColor(Color.RED));}
				else if(x == 13 && y == 8) {tileGrid[x][y] = new HomeLabelPanel(GameHelpers.getIndexFromColor(Color.RED));}
				else if(x == 2 && y == 11) {tileGrid[x][y] = new StartLabelPanel(GameHelpers.getIndexFromColor(Color.BLUE));}
				else if(x == 7 && y == 13) {tileGrid[x][y] = new HomeLabelPanel(GameHelpers.getIndexFromColor(Color.BLUE));}
				else {tileGrid[x][y] = new TilePanel(mGameManager.getTile(x,y));}
				add(tileGrid[x][y]);
			}
		}
		
		//Set in the card
		TilePanel cardLabelTile = tileGrid[boardSize/2-1][boardSize/2-1];
		cardLabelTile.setLayout(new GridLayout(1,1));
		cardLabelTile.add(cardLabel);
		
		TilePanel cardButtonTile = tileGrid[boardSize/2][boardSize/2-1];
		cardButtonTile.setLayout(new GridLayout(1,1));
		cardButtonTile.add(cardButton);
		
		//This is used to make sure the GameManager can redraw the GUI
		inGameManager.setGamePanel(this);
		
		redraw();
	}
	
	public void redraw() {
		for(TilePanel row[] : tileGrid) {
			for(TilePanel tp : row) {
				tp.update();
			}
		}
		revalidate();
		repaint();
	}

	//Each tile is a square in the grid, it can be null to hold a blank square
	class TilePanel extends JPanel {
		private static final long serialVersionUID = -9071191204545371340L;
		
		private final Tile mTile;
		private final Stack<Component> components;
		
		private JPanel pawn = new JPanel();
		private boolean pawnDisplayed = false;
		
		TilePanel(Tile tile) {
			mTile = tile;
			//Used to keep track what component should be displayed
			components = new Stack<Component>();
			
			//If we are a meaningful tile in the game
			if(mTile != null) {
				setBorder(new LineBorder(mTile.getColor()));
				//Set any special looks based on the tiles properties
				if(mTile.doesSlide()) components.push(new JLabel(GameHelpers.getSlideLabelFromColor(mTile.getColor())));
				if(mTile.isStart()) components.push(new JLabel("Start"));
				if(mTile.isHome()) components.push(new JLabel("Home"));
				
				//If the tile is clicked by the user...
				addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent me) {
						//Update this in the action manager
						mGameManager.tileClicked(mTile,mGameManager.getMainPlayer());
					}
				});
			}
		}
		
		//Update the tile based on its properties
		protected void update() {
			if(mTile == null) return;
			if(mTile.isOccupied() && !pawnDisplayed) {
				pawnDisplayed = true;
				components.push(pawn);
			}
			if(mTile.isOccupied() && pawnDisplayed) {
				pawn.setBackground(mTile.getPawnColor());
			}
			if(!mTile.isOccupied() && pawnDisplayed) {
				pawnDisplayed = false;
				components.pop();
			}
			removeAll();
			if(!components.isEmpty())add(components.peek());
		}
	}
	
	//Used for the start counter display
	class StartLabelPanel extends TilePanel{
		private static final long serialVersionUID = -9166979703140637366L;
		private final int mPlayerNum;
		JLabel mLabel;
		{
			mLabel = new JLabel();
			add(mLabel);
		}
		StartLabelPanel(int numPlayer) {
			super(null);
			mPlayerNum = numPlayer;
		}
		@Override
		protected void update() {
			mLabel.setText(mGameManager.getPlayerStartCount(mPlayerNum));
		}
	}
	
	//Used for the home counter display
	class HomeLabelPanel extends TilePanel{
		private static final long serialVersionUID = -9166979703540637366L;
		private final int mPlayerNum;
		JLabel mLabel;
		{
			mLabel = new JLabel();
			add(mLabel);
		}
		HomeLabelPanel(int numPlayer) {
			super(null);
			mPlayerNum = numPlayer;
		}
		@Override
		protected void update() {
			mLabel.setText(mGameManager.getPlayerHomeCount(mPlayerNum));
		}
	}

	public void endGame(String winnerName) {
		JOptionPane.showMessageDialog(
				null, 
				mGameManager.getWinner() + " player won!", 
				"Sorry!", 
				JOptionPane.NO_OPTION
			);
		//Quit out if over
		JButton exit = new JButton("");
		exit.addActionListener(mQuitAction);
		exit.doClick();
	}
}
