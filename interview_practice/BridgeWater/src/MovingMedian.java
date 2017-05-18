import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MovingMedian {
	static List<String> inputs = new ArrayList<String>();
	static int windowSize = 0;
	static double maxChange = 0;
	public static void main(String[] args) throws NumberFormatException, IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    String line = null;
	    windowSize = Integer.parseInt(br.readLine());	
	    maxChange = Double.parseDouble(br.readLine());
	    while((line = br.readLine()) != null){ 
	    	inputs.add(line);
			helper(inputs);
	    }
	}
	
	static void helper(List<String> inputs) {
		if(windowSize<=0 || maxChange<0 || inputs.size() < windowSize+1){
			return;
		}
		
		ArrayList<Double> al = new ArrayList<Double>();
		for(int i=inputs.size()-2; i>=inputs.size()-1-windowSize; i--){
			al.add(Double.parseDouble(inputs.get(i)));
		}
		Collections.sort(al);	
		double median = al.get(al.size()/2);		// even size
		if((al.size() & 1) == 0){			// When it is odd size
			median = al.get(al.size()/2-1);
		}
		
		double cur = Double.parseDouble(inputs.get(inputs.size()-1));
		if(Math.abs(cur-median)*1.0/median > maxChange){
			System.out.println(String.format("%.3f", cur));
		}
	}
}
