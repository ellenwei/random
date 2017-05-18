package game;

import java.awt.Color;
import java.util.HashMap;
import java.util.Map;

/*
 * GameHelpers
 * Various methods for translation
 * */
public class GameHelpers {
	private static final Color colors[] = {Color.RED, Color.BLUE, Color.YELLOW, Color.GREEN};
	
	private static final Map<String,Color> colorLookup = new HashMap<String,Color>();
	private static final Map<Color,String> slideLabelLookup = new HashMap<Color,String>();
	private static final Map<Color,Integer> colorIndexLookup = new HashMap<Color,Integer>();
	
	static {
		colorLookup.put("-", Color.BLACK);
		colorLookup.put("r", colors[0]);
		colorLookup.put("b", colors[1]);
		colorLookup.put("y", colors[2]);
		colorLookup.put("g", colors[3]);
		
		slideLabelLookup.put(colors[0], "<");
		slideLabelLookup.put(colors[1], "^");
		slideLabelLookup.put(colors[2], ">");
		slideLabelLookup.put(colors[3], "v");
		
		colorIndexLookup.put(colors[0], 0);
		colorIndexLookup.put(colors[1], 1);
		colorIndexLookup.put(colors[2], 2);
		colorIndexLookup.put(colors[3], 3);
	}

	public static Color getColorFromIndex(int index) {
		return colors[index];
	}
	
	public static Integer getIndexFromColor(Color color) {
		return colorIndexLookup.get(color);
	}
	
	public static Color getColorFromString(String color) {
		return colorLookup.get(color);
	}
	
	public static String getSlideLabelFromColor(Color color) {
		return slideLabelLookup.get(color);
	}

}
