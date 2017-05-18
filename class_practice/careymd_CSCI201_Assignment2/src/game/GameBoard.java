package game;

import java.awt.Color;
import java.awt.Point;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/*
 * GameBoard
 * The Sorry! Game board
 * */
public class GameBoard {

	//Size of the board and number of players
	private final static int boardSize = 16;
	private final static int numPlayers = 4;
	
	//The tiles that belong on the board
	private final Tile tiles[][];
	
	//Easy access for the home and start tiles
	private final Tile homes[];
	private final Tile starts[];
	
	{//constructor
		homes = new Tile[numPlayers];
		starts = new Tile[numPlayers];
		tiles = new Tile[boardSize][boardSize];
	}
	
	public GameBoard(File configFile, Player[] players){
		Scanner sc = null;
		//Read in the board
		try {
			sc = new Scanner(configFile);
		} catch (FileNotFoundException e) {}
		
		//These are used to link each tile to one another
		Point previous[][] = new Point[boardSize][boardSize];
		Point next[][] = new Point[boardSize][boardSize];
		Point nextSameColor[][] = new Point[boardSize][boardSize];
		
		//Read in each tile's information from the file and create it
		while(sc.hasNext()) {
			Color c = GameHelpers.getColorFromString(sc.next());
			sc.next();
			int slide = sc.nextInt();
			sc.next();
			int start = sc.nextInt();
			sc.next();
			int home = sc.nextInt();
			sc.next();
			int x = sc.nextInt();
			int y = sc.nextInt();
			sc.next();
			previous[x][y] = new Point(sc.nextInt(),sc.nextInt());
			sc.next();
			next[x][y] = new Point(sc.nextInt(),sc.nextInt());
			nextSameColor[x][y] = new Point(sc.nextInt(),sc.nextInt());
			tiles[x][y] = new Tile(c,slide!=0,start!=0,home!=0,x,y);
			if(home!=0) homes[GameHelpers.getIndexFromColor(c)] = tiles[x][y];
			if(start!=0) starts[GameHelpers.getIndexFromColor(c)] = tiles[x][y];
		}
		sc.close();
		
		//Link all the tiles based on their connection information
		for(int y = 0; y < boardSize; ++y) {
			for(int x = 0; x < boardSize; ++x) {
				if(tiles[x][y] == null) continue;
				int X = next[x][y].x;
				int Y = next[x][y].y;
				tiles[x][y].setNext(tiles[X][Y]);
				X = nextSameColor[x][y].x;
				Y = nextSameColor[x][y].y;
				tiles[x][y].setNextSameColor(tiles[X][Y]);
				X = previous[x][y].x;
				Y = previous[x][y].y;
				tiles[x][y].setPrevious(tiles[X][Y]);
			}
		}
		for(Player player : players) {
			for(Pawn pawn : player.getPawns()) {
				pawn.setStartTile(starts[GameHelpers.getIndexFromColor(player.getColor())]);
			}
		}
	}

	//Get a given tile at a location
	public Tile getTile(int x, int y) {
		return tiles[x][y];
	}
	
	//Get the start tile for a given color
	public Tile getStart(Color color) {
		return starts[GameHelpers.getIndexFromColor(color)];
	}

	//Refresh the tiles
	public void clearTiles() {
		for(Tile t[] : tiles) {
			for(Tile tile : t) {
				if(tile != null)
				tile.removePawn();
			}
		}
	}
	
}
