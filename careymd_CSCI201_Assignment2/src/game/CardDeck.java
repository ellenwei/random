package game;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/*
 * CardDeck
 * Used for randomly drawing cards for the Sorry! Game
 * */
public class CardDeck {

	private static final int numCardTypes = 11;
	private static final String filePath = "src/game/cards";
	private static final String cardNames[];
	
	private static final int numCards = 44;
	private final List<Card> cards;
	private int index;
	
	private static final Map<Integer,Integer> values;
	
	public static final int ONE = 0;
	public static final int TWO = 1;
	public static final int THREE = 2;
	public static final int FOUR = 3;
	public static final int FIVE = 4;
	public static final int SEVEN = 5;
	public static final int EIGHT = 6;
	public static final int TEN = 7;
	public static final int ELEVEN = 8;
	public static final int TWELVE = 9;
	public static final int SORRY = 10;
	
	static{
		cardNames = new String[numCardTypes];
		//Initialize cards from file
		try {
			Scanner sc = new Scanner(new File(filePath));
			int index = 0;
			while(sc.hasNext()) {
				cardNames[index++] = sc.nextLine();
			}
			sc.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		//Set up easy access for programming constants
		values = new HashMap<Integer,Integer>();
		values.put(ONE, 1);
		values.put(TWO, 2);
		values.put(THREE, 3);
		values.put(FOUR, 4);
		values.put(FIVE, 5);
		values.put(SEVEN, 7);
		values.put(EIGHT, 8);
		values.put(TEN, 10);
		values.put(ELEVEN, 11);
		values.put(TWELVE, 12);
		values.put(SORRY, 0);
	}
	
	{
		//Create a list of 44 cards
		cards = new ArrayList<Card>(44);
		//Add 4 of each 11 types
		for(int i = 0; i < 11; i++) {
			for(int j = 0; j < numCards/numCardTypes; j++) {
				cards.add(new Card(i));
			}
		}
		//Shuffle!
		Collections.shuffle(cards);
	}
	
	public Card drawCard() {
		//Check if a shuffle is needed
		if(index == numCards) {
			index%=numCards;
			Collections.shuffle(cards);
		}
		//Return the next card in the deck
		return cards.get(index++);
	}
	
	class Card {
		//Each card is defined by a name and type
		private final String name;
		private final int type;
		Card(int cardType) {
			type = cardType;
			name = cardNames[cardType];
		}
		
		public String getName() {return name;}
		public int getType() {return type;}
	}
	
	//Used to check the value of a card
	public static int getValue(Card card) {
		return values.get(card.getType());
	}
}
