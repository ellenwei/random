package game;

import java.awt.Color;

/*Pawn
 * Data for a pawn that belongs to a player
 * */
public class Pawn {

	//Each pawn has a color
	private final Color mColor;
	
	//The current tile
	private Tile mTile;
	
	//The tile to return to start
	private Tile mStartTile;
	
	public Pawn(Color inColor) {
		mColor = inColor;
	}

	public Color getColor() { return mColor;}

	public void setCurrentTile(Tile inTile) {
		mTile = inTile;
	}
	
	public Tile getCurrentTile() {
		return mTile;
	}

	public void setStartTile(Tile inTile) {
		mStartTile = inTile;
		mTile = inTile;
	}
	
	public void returnToStart() {
		mTile.removePawn();
		mTile = mStartTile;
	}

}
