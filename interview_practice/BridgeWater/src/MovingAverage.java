import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;

public class MovingAverage {
	static List<String> inputs = new LinkedList<String>();
	static int windowSize = 0;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    String line = null;
	    windowSize = Integer.parseInt(br.readLine());	    
	    while((line = br.readLine()) != null){ 
	    	inputs.add(line);
			process(inputs);
	    }
	}
	
	public static void process(List<String> inputs) {
		if(windowSize<=0 || inputs.size() < windowSize){
			return;
		}
		
		double sum = 0;
		for(int i=inputs.size()-1; i>=inputs.size()-windowSize; i--){
			sum += Double.parseDouble(inputs.get(i));
		}
		double average = sum*1.0 / windowSize;
		System.out.println(String.format("%.3f", average));
	}
	
}
