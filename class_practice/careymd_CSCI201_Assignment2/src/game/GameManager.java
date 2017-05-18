package game;

import java.awt.Color;
import java.io.File;

import javax.swing.JOptionPane;

import game.CardDeck.Card;
import sorryclient.GamePanel;

/*
 * GameManager
 * Actual logic for the game play
 * */
public class GameManager {
	private final static String filePath = "src/game/board";
	private final GameBoard mGameBoard;
	
	//The players (can be idle or not)
	private final static int numPlayers = 4;
	private final Player players[];
	
	//The card deck to be used.
	private final CardDeck cardDeck;
	
	//The actual players playing the game
	private Player livePlayers[];
	private int currentPlaying;
	
	//Main player information
	private final int mainPlayer = 0;
	public Player getMainPlayer(){return livePlayers[mainPlayer];}
	
	//Info for two-step moves
	private Tile swapTile = null;
	private int sevenRemainder = -1;
	
	//The current drawn card, and helper
	private Card card;
	private boolean canDraw = true;
	
	//Winning information
	private boolean gameOver = false;
	public boolean isGameOver() {return gameOver;}
	private String winnerName = null;
	public String getWinner() {return winnerName;}
	
	//GamePanel to update
	private GamePanel mGamePanel;
	
	//Load in the players, board, and card deck used for play
	{
		players = new Player[numPlayers];
		currentPlaying = mainPlayer;
		for(int i = 0; i < numPlayers; ++i) {
			players[i] = new Player(GameHelpers.getColorFromIndex(i));
		}
		mGameBoard = new GameBoard(new File(filePath),players);
		cardDeck = new CardDeck();
	}
	
	//Set up once we know the players color and number selections
	public void setUp(Color playerColor, int numLivePlayers) {
		livePlayers = new Player[numLivePlayers];
		int mainPlayerIndex = GameHelpers.getIndexFromColor(playerColor);
		livePlayers[mainPlayer] = players[mainPlayerIndex];
		switch(numLivePlayers) {
			case 4:
				livePlayers[3] = players[(mainPlayerIndex+3)%numPlayers];
			case 3:
				livePlayers[1] = players[(mainPlayerIndex+1)%numPlayers];
				livePlayers[2] = players[(mainPlayerIndex+2)%numPlayers];
			break;
			case 2:
				livePlayers[1] = players[(mainPlayerIndex+(numPlayers/2))%numPlayers];
			break;
		}
	}
	
	//Returns number of pawns at the given player's start
	public String getPlayerStartCount(int mPlayerNum) {
		int count = 0;
		for(Pawn p: players[mPlayerNum].getPawns()) {
			if(p.getCurrentTile().isStart()) count++;
		}
		return ""+count;
	}
	
	//Return the number of pawns at the given player's home
	public String getPlayerHomeCount(int mPlayerNum) {
		int count = 0;
		for(Pawn p: players[mPlayerNum].getPawns()) {
			if(p.getCurrentTile().isHome()) count++;
		}
		return ""+count;
	}
	
	//Gets a specific tile from the board, used for graphical elements
	public Tile getTile(int x, int y) {
		return mGameBoard.getTile(x, y);
	}
	
	//Draw a card from the deck
	public void drawCard() {
		if(!canDraw) return;
		card = cardDeck.drawCard();
		canDraw = false;
		JOptionPane.showMessageDialog(
			null, 
			card.getName(), 
			"Sorry!", 
			JOptionPane.NO_OPTION
		);
		//If the player cannot make a move, just skip
		if(!canMakeMove()) endTurn();
	}
	
	//Test if the current player can make a move or not.
	private boolean canMakeMove() {
		if(card.getType() == CardDeck.ONE || card.getType() == CardDeck.TWO) return true;
		else if(card.getType() == CardDeck.SORRY) {
			if(livePlayers[currentPlaying].hasPawnsAtStart()) {
				for(Player opponent : livePlayers) {
					if(opponent != livePlayers[currentPlaying]) {
						for(Pawn pawn : opponent.getPawns()) {
							if(!pawn.getCurrentTile().isStart() && !pawn.getCurrentTile().isHome() && !pawn.getCurrentTile().isSafeZone()) return true;
						}
					}
				}
			}
		} else {
			for(Pawn pawn : livePlayers[currentPlaying].getPawns()) {
				Tile currentTile = pawn.getCurrentTile();
				if(!currentTile.isStart() && !currentTile.isHome()) return true;
			}
		}
		return false;
	}
	
	//End the player turn
	private void endTurn() {
		if(gameOver) return;
		//Update the board graphically
		mGamePanel.redraw();
		canDraw = true;
		
		//Check if the game should end
		boolean allHome = true;
		for(Pawn pawn : livePlayers[currentPlaying].getPawns()) {
			if(!pawn.getCurrentTile().isHome()) allHome = false;
		}
		if(allHome) {
			Color player = livePlayers[currentPlaying].getColor();
			if(player.equals(Color.RED)) winnerName = "Red";
			else if(player.equals(Color.GREEN)) winnerName = "Green";
			else if(player.equals(Color.YELLOW)) winnerName = "Yellow";
			else if(player.equals(Color.BLUE)) winnerName = "Blue";
			gameOver = true;
			mGamePanel.endGame(winnerName);
			return;
		}
		
		//If the card was a two, let the player have another turn
		//Otherwise advance
		if(card.getType() != CardDeck.TWO) {
			currentPlaying++;
			currentPlaying %= livePlayers.length;
		}
		
		//Simulate the other players if they are a robot
		if(currentPlaying != mainPlayer) {
			if(!gameOver)
			simulatePlayer(livePlayers[currentPlaying]);
		}
		
		//Clean up for next player
		clean();
		set();
		card = null;
	}

	//Will draw the card for the robot, and make their moves.
	private void simulatePlayer(Player player) {
		drawCard();
		for(Pawn pawn : player.getPawns()) {
			if(tileClicked(pawn.getCurrentTile(),player)) break;
		} 
		if(sevenRemainder != -1)
			for(Pawn pawn : player.getPawns()) {
				if(tileClicked(pawn.getCurrentTile(),player))break;
			}
		if(card != null) endTurn();
	}
	
	//The logic for a tile being clicked
	public boolean tileClicked(Tile tile, Player player) {
		//Check basic impossibilities
		if(card == null) return false;
		if(tile == null) return false;
		if(!livePlayers[currentPlaying].equals(player)) return false;
		
		//If we are dealing with a sorry card
		if(card.getType() == CardDeck.SORRY) {
			//For the robots, find another pawn and replace it
			if(currentPlaying != mainPlayer) {
				Pawn playerPawn = player.getAvailablePawn();
				Pawn opponentPawn = null;
				for(Player opponent : livePlayers) {
					if(opponent != livePlayers[currentPlaying]) {
						for(Pawn pawn : opponent.getPawns()) {
							if(!pawn.getCurrentTile().isStart() && !pawn.getCurrentTile().isHome() && !pawn.getCurrentTile().isSafeZone()){
								opponentPawn = pawn;
								break;
							}
						}
						if(opponentPawn != null) break;
					}
				}
				Tile toSet = opponentPawn.getCurrentTile();
				players[GameHelpers.getIndexFromColor(opponentPawn.getColor())].returnPawn(opponentPawn);
				toSet.setPawn(playerPawn);
				endTurn();
				return true;
			} else {
				//If human, check if the selection of the opponent is valid
				if(tile.isOccupied()) {
					if(!tile.isOccupiedByColor(player.getColor()) && !tile.isSafeZone()) {
						Pawn playerPawn = player.getAvailablePawn();
						Pawn opponentPawn = tile.getPawn();
						players[GameHelpers.getIndexFromColor(opponentPawn.getColor())].returnPawn(opponentPawn);
						tile.setPawn(playerPawn);
						endTurn();
						return true;
					}
				}
				return false;
			}
		}
		
		//If we are making a swap with 11
		if(swapTile != null) {
			//Swap the pawns
			if(card.getType() == CardDeck.ELEVEN) {
				if(tile.isOccupied()) {
					if(!tile.isOccupiedByColor(swapTile.getPawnColor())) {
						Pawn playerPawn = swapTile.getPawn();
						Pawn opponentPawn = tile.getPawn();
						tile.setPawn(playerPawn);
						swapTile.setPawn(opponentPawn);
						swapTile = null;
					}
				}
			}
			if(swapTile == null) {
				endTurn();
				return true;
			} else return false;
		}
		
		//Get conditions that must be true
		boolean start = tile.isStart() && tile.isColor(player.getColor()) && player.hasPawnsAtStart() && (card.getType() == CardDeck.ONE || card.getType() == CardDeck.TWO);
		boolean owned = tile.isOccupiedByColor(player.getColor());
		
		if(!start && !owned) return false;
		
		//Check to see if we have a valid pawn to work with
		Pawn pawn;
		if(!start) pawn = tile.getPawn();
		else pawn = player.getAvailablePawn();
		if(pawn == null) return false;
		
		//If the card is a seven, and we have remaining moves, use the remainder on the pawn
		if(card.getType() == CardDeck.SEVEN && sevenRemainder != -1) {
			Tile endTile = travel(tile, pawn, sevenRemainder);
			if(endTile == null) return false;
			endTile = slide(endTile,pawn);
			if(endTile.isOccupiedByColor(pawn.getColor())) {
				if(endTile.getPawn() != pawn) return false;
			}
			if(endTile.isOccupied()) players[GameHelpers.getIndexFromColor(endTile.getPawnColor())].returnPawn(endTile.getPawn());
			tile.removePawn();
			if(!endTile.isHome()){
				endTile.setPawn(pawn);
			} else pawn.setCurrentTile(endTile);
			sevenRemainder = -1;
			endTurn();
			return true;
		}//If a normal forward move, simply move the pawn forward
		else if(card.getType() == CardDeck.ONE || card.getType() == CardDeck.TWO ||
			card.getType() == CardDeck.THREE || card.getType() == CardDeck.FIVE ||
			card.getType() == CardDeck.EIGHT || card.getType() == CardDeck.TWELVE ) {
			Tile endTile = travel(tile, pawn, CardDeck.getValue(card));
			if(endTile == null) return false;
			Tile beforeTile = endTile;
			endTile = slide(endTile,pawn);
			if(endTile.isOccupiedByColor(pawn.getColor())) {
				if(endTile.getPawn() != pawn) return false;
			}
			if(endTile.isOccupied()) players[GameHelpers.getIndexFromColor(endTile.getPawnColor())].returnPawn(endTile.getPawn());
			tile.removePawn();
			if(!endTile.isHome()){
				endTile.setPawn(pawn);
			} else pawn.setCurrentTile(endTile);
			if(beforeTile != endTile) slideRemove(beforeTile, pawn.getColor());
		}//If the card is four, do as before, but negate the travel amount
		else if (card.getType() == CardDeck.FOUR){
			Tile endTile = travel(tile, pawn, -CardDeck.getValue(card));
			if(endTile == null) return false;
			Tile beforeTile = endTile;
			endTile = slide(endTile,pawn);
			if(endTile.isOccupiedByColor(pawn.getColor())) {
				if(endTile.getPawn() != pawn) return false;
			}
			if(endTile.isOccupied()) players[GameHelpers.getIndexFromColor(endTile.getPawnColor())].returnPawn(endTile.getPawn());
			tile.removePawn();
			if(!endTile.isHome()){
				endTile.setPawn(pawn);
			} else pawn.setCurrentTile(endTile);
			if(beforeTile != endTile) slideRemove(beforeTile, pawn.getColor());
		}//Card seven
		else if (card.getType() == CardDeck.SEVEN){
			//assume you can move all 1-7
			boolean moves[] = {false,true,true,true,true,true,true,true};
			//check if you can make the move normally
			for(int i = 0; i <= 7; i++) {
				int main = 7 - i;
				int secondary = i;
				Tile endTileMain = travel(tile,pawn, main);
				if(endTileMain == null) {
					moves[main] = false;
					continue;
				}
				endTileMain = slide(endTileMain,pawn);
				if(endTileMain.isOccupiedByColor(pawn.getColor())) {
					if(endTileMain.getPawn() != pawn) {
						moves[main] = false;
					}
				}
				if(main == 7) continue;//if 7, we don'e need a compliment
				
				//Prove that the compliment to each valid move is also a valid move
				boolean hasFriend = false;
				for(Pawn other : livePlayers[currentPlaying].getPawns()) {
					if(other == pawn) continue;
					Tile endTileSecondary = other.getCurrentTile();
					if(endTileSecondary.isHome()) continue;
					if(endTileSecondary.isStart()) continue;
					
					//Check if the move would be blocked
					boolean blocked = false;
					for(int j = 0; j < secondary; j++) {
						endTileSecondary = endTileSecondary.getNext(other.getColor());
						if(endTileSecondary.isOccupiedByColor(other.getColor())) {
							if(endTileSecondary != tile) blocked = true;
						}
						if(endTileSecondary == endTileMain) blocked = true;
					}
					if(blocked) continue;
					
					endTileSecondary = slide(endTileSecondary,other);
					if(endTileSecondary.isOccupiedByColor(other.getColor())) {
						if(endTileSecondary != tile) continue;
					}
					if(endTileSecondary == endTileMain) continue;
					hasFriend = true;
					break;
				}
				//The final verdict if the initial move has a valid compliment
				moves[main] = moves[main] && hasFriend;
			}
			//count our options
			int options = 0;
			for(boolean move : moves) if(move) options++;
			//If no options, the move is not valid, otherwise offer to move
			if(options == 0) return false;
			else {
				String availableMoves[] = new String[options];
				int counter = 0;
				for(int i = 0; i < moves.length; i++) {
					if(moves[i])availableMoves[counter++] = ""+i;
				}
				int n;
				//Allow the human player to pick
				if(currentPlaying == mainPlayer) {
				String selection = (String)JOptionPane.showInputDialog(
						null,
						"What would you like to do?",
					    "???",
				        JOptionPane.QUESTION_MESSAGE,
				        null,
				        availableMoves,
				        availableMoves[0]);
						n = Integer.valueOf(selection);
				} else n = Integer.valueOf(availableMoves[0]); //Robot will just use first valid move
				
				//if moving 7, we won't move another pawn, otherwise, keep track of the remainder
				//Note: we proved earlier that this remainder will be valid for at least one pawn later
				if(n == 7) sevenRemainder = -1;
				else sevenRemainder = 7 - n;
				Tile endTile = travel(tile, pawn, n);
				if(endTile == null) return false;
				Tile beforeTile = endTile;
				endTile = slide(endTile,pawn);
				if(endTile.isOccupiedByColor(pawn.getColor())) {
					if(endTile.getPawn() != pawn) return false;
				}
				if(endTile.isOccupied()) players[GameHelpers.getIndexFromColor(endTile.getPawnColor())].returnPawn(endTile.getPawn());
				tile.removePawn();
				if(!endTile.isHome()){
					endTile.setPawn(pawn);
				} else pawn.setCurrentTile(endTile);
				if(beforeTile != endTile) slideRemove(beforeTile, pawn.getColor());
				if(sevenRemainder != -1) return true;
			}
		}
		else if (card.getType() == CardDeck.TEN){
			//Check to see if the resulting forward 10 move is valid
			boolean canMoveForward = true;
			Tile endTileForward = travel(tile, pawn, CardDeck.getValue(card));
			Tile beforeTileForward = endTileForward;
			if(endTileForward == null) canMoveForward = false;
			if(canMoveForward) {
				endTileForward = slide(endTileForward,pawn);
				if(endTileForward.isOccupiedByColor(pawn.getColor())) {
					if(endTileForward.getPawn() != pawn) canMoveForward = false;
				}
			}
			//Check to see if the resulting backward 1 move is valid
			boolean canMoveBackwards = true;
			Tile endTileBackward = travel(tile, pawn, -1);
			Tile beforeTileBackward = endTileBackward;
			if(endTileBackward == null) canMoveBackwards = false;
			if(canMoveBackwards) {
				endTileBackward = slide(endTileBackward,pawn);
				if(endTileBackward.isOccupiedByColor(pawn.getColor())) {
					if(endTileBackward.getPawn() != pawn) canMoveBackwards = false;
				}
			}
			if(currentPlaying != mainPlayer) {
				//Robots will move forward if possible
				if(canMoveForward) {
					if(endTileForward.isOccupied()) players[GameHelpers.getIndexFromColor(endTileForward.getPawnColor())].returnPawn(endTileForward.getPawn());
					tile.removePawn();
					if(!endTileForward.isHome()){
						endTileForward.setPawn(pawn);
					}else pawn.setCurrentTile(endTileForward);
					if(beforeTileForward != endTileForward) slideRemove(beforeTileForward, pawn.getColor());
				} else {
					if(endTileBackward.isOccupied()) players[GameHelpers.getIndexFromColor(endTileBackward.getPawnColor())].returnPawn(endTileBackward.getPawn());
					tile.removePawn();
					if(!endTileBackward.isHome()){
						endTileBackward.setPawn(pawn);
					}else pawn.setCurrentTile(endTileBackward);
					if(beforeTileBackward != endTileBackward) slideRemove(beforeTileBackward, pawn.getColor());
				}
			} else {
				int n = -1;
				//Humans will be able to choose if they can
				if(canMoveForward && canMoveBackwards) {
					Object[] options = {"Forward 10","Backward 1"};
					n = JOptionPane.showOptionDialog(null,
					    "What would you like to do?",
					    "???",
					    JOptionPane.YES_NO_OPTION,
					    JOptionPane.QUESTION_MESSAGE,
					    null,
					    options,
					    options[1]);
				}
				else if(canMoveForward) n = 0;
				else if(canMoveBackwards) n = -1;
				//Human selected forward (or is forced to)
				if(n == 0) {
					if(endTileForward.isOccupied()) players[GameHelpers.getIndexFromColor(endTileForward.getPawnColor())].returnPawn(endTileForward.getPawn());
					tile.removePawn();
					if(!endTileForward.isHome()){
						endTileForward.setPawn(pawn);
					}else pawn.setCurrentTile(endTileForward);
					if(beforeTileForward != endTileForward) slideRemove(beforeTileForward, pawn.getColor());
				} else { //Human selected backward (or is forced to)
					if(endTileBackward.isOccupied()) players[GameHelpers.getIndexFromColor(endTileBackward.getPawnColor())].returnPawn(endTileBackward.getPawn());
					tile.removePawn();
					if(!endTileBackward.isHome()){
						endTileBackward.setPawn(pawn);
					}else pawn.setCurrentTile(endTileBackward);
					if(beforeTileBackward != endTileBackward) slideRemove(beforeTileBackward, pawn.getColor());
				}
			}
		}
		else if (card.getType() == CardDeck.ELEVEN) {
			//Check to see if the pawn can move forward 11
			boolean canMoveForward = true;
			Tile endTile = travel(tile, pawn, CardDeck.getValue(card));
			Tile beforeTile = endTile;
			if(endTile == null) canMoveForward = false;
			if(canMoveForward) {
				endTile = slide(endTile,pawn);
				if(endTile.isOccupiedByColor(pawn.getColor())) {
					if(endTile.getPawn() != pawn) canMoveForward = false;
				}
			}
			//Check to see if the pawn can swap with another
			boolean canSwap = false;
			for(Player playing : livePlayers) {
				if(playing != livePlayers[currentPlaying]) {
					for(Pawn opponentPawn : playing.getPawns()) {
						Tile t = opponentPawn.getCurrentTile();
						if(!t.isHome() && !t.isStart() && !t.isSafeZone()) {
							canSwap = true;
							break;
						}
					}
					if(canSwap) break;
				}
			}
			if(canMoveForward && !canSwap) {
				//If the pawn can move forward, but can't swap just move it forward
				if(endTile.isOccupied()) players[GameHelpers.getIndexFromColor(endTile.getPawnColor())].returnPawn(endTile.getPawn());
				tile.removePawn();
				if(!endTile.isHome()){
					endTile.setPawn(pawn);
				} else pawn.setCurrentTile(endTile);
				if(beforeTile != endTile) slideRemove(beforeTile, pawn.getColor());
			} else {
				//If it is a robot, just move forward
				if(currentPlaying != mainPlayer) {
					if(canMoveForward) {
						if(endTile.isOccupied()) players[GameHelpers.getIndexFromColor(endTile.getPawnColor())].returnPawn(endTile.getPawn());
						tile.removePawn();
						if(!endTile.isHome()){
							endTile.setPawn(pawn);
						} else pawn.setCurrentTile(endTile);
						if(beforeTile != endTile) slideRemove(beforeTile, pawn.getColor());
					} else {
						//Remember: The robot will attempt with all pawns,
						//Eventually, the robot will move forward
						return false;
					}
				} else {
					//If the player can choose, let them.
					Object[] options = {"Move Forward","Swap"};
					int n = JOptionPane.showOptionDialog(null,
					    "What would you like to do?",
					    "???",
					    JOptionPane.YES_NO_OPTION,
					    JOptionPane.QUESTION_MESSAGE,
					    null,
					    options,
					    options[1]);
					if(n == 1) {//Chose to swap, remember this tile for later
						swapTile = tile;
						return true;
					} else {
						//Chose to move, simply move forward 11
						if(endTile.isOccupied()) players[GameHelpers.getIndexFromColor(endTile.getPawnColor())].returnPawn(endTile.getPawn());
						tile.removePawn();
						if(!endTile.isHome()){
							endTile.setPawn(pawn);
						} else pawn.setCurrentTile(endTile);
						if(beforeTile != endTile) slideRemove(beforeTile, pawn.getColor());
					}
				}
			}
		}
		//Move was successful if this is reached.
		endTurn();
		return true;
	}
	
	//Clears the slide of all non-color colored pawns
	private void slideRemove(Tile temp, Color color) {
		if(temp.doesSlide() && !temp.getPrevious().doesSlide()) {
			while(temp.getNext(color).doesSlide()) {
				temp = temp.getNext(color);
				if(temp.isOccupied())
				if(!temp.getPawnColor().equals(color)) {
					players[GameHelpers.getIndexFromColor(temp.getPawnColor())].returnPawn(temp.getPawn());
					temp.removePawn();
				}
			}
		}
	}

	//Movement logic to move pawn
	public Tile travel(Tile start, Pawn pawn, int numSpaces) {
		boolean backwards = numSpaces < 0;
		numSpaces = Math.abs(numSpaces);
		for(int i = 0; i < numSpaces; i++) {
			if(backwards) start = start.getPrevious();
			else start = start.getNext(pawn.getColor());
			if(start.isOccupiedByColor(pawn.getColor())) return null;
		}
		return start;
	}
	
	//Movement logic to slide pawn (does not remove)
	public Tile slide(Tile temp, Pawn pawn) {
		if(temp.doesSlide() && !temp.getPrevious().doesSlide()) {
			while(temp.getNext(pawn.getColor()).doesSlide()) {
				temp = temp.getNext(pawn.getColor());
			}
			temp = temp.getNext(pawn.getColor());
		}
		return temp;
	}
	
	//clears the tiles of the game board
	private void clean() {
		mGameBoard.clearTiles();
	}
	
	//Sets the game baord
	private void set() {
		for(Player player : players) {
			player.resetStartPawns();
			for(Pawn pawn : player.getPawns()) {
				Tile toSet = pawn.getCurrentTile();
				if(!toSet.isHome() && !toSet.isStart())
					pawn.getCurrentTile().setPawn(pawn);
				else if(toSet.isStart()) {
					player.returnPawn(pawn);
				}
			}
		}
	}

	//Set the render target
	public void setGamePanel(GamePanel inGamePanel) {
		mGamePanel = inGamePanel;
	}
	
}
