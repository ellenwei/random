package game;

import java.awt.Color;
import java.util.Stack;

/*
 * Player
 * Each player manages four pawns
 * */
public final class Player {
	private final int numPawns = 4;
	private final Pawn mPawns[];
	
	//The start tile hold
	private final Stack<Pawn> startPawns;
	
	private final Color mColor;
	
	{ //Initialize
		mPawns = new Pawn[numPawns];
		startPawns = new Stack<Pawn>();
	}
	
	Player(Color inColor) {
		//Set the color, and create the number of pawns
		mColor = inColor;
		for(int i = 0; i < numPawns; ++i) {
			Pawn p = new Pawn(mColor);
			mPawns[i] = p;
			startPawns.add(mPawns[i]);
		}
	}

	public Color getColor() { return mColor; }

	//Get a pawn that is idle in the start hold
	public Pawn getAvailablePawn() { 
		if(startPawns.isEmpty())return null;
		return startPawns.pop();
	}
	
	//Put a pawn back in the start hold
	public void returnPawn(Pawn pawn) { 
		startPawns.push(pawn);
		pawn.returnToStart();
	}

	public boolean hasPawnsAtStart() {
		return !startPawns.isEmpty();
	}

	//Return the list of all pawns the player controls
	public Pawn[] getPawns() {
		return mPawns;
	}

	//Clear the start pawns
	public void resetStartPawns() {
		startPawns.removeAllElements();
	}
	
}
