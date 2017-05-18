import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

public class UniquePath {
	
	static String removeDD(String date){
		//System.out.println("string to format is "+date);
        Date initDate = null;
        try{
            initDate = new SimpleDateFormat("yyyy-MM-dd").parse(date);
        }catch (ParseException e){
            e.printStackTrace();
        }
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM");
        String parsedDate = formatter.format(initDate);
        return parsedDate;
    }
	
	public static void main(String[] args) throws ParseException{
		String[] input = {"2015-08,2016-04",
				"      ",
"2015-08-15,clicks,635",
"2016-03-24,app_installs,683",
"2015-04-05,favorites,763",
"2016-01-22,favorites,788",
"2015-12-26,clicks,525",
"2016-06-03,retweets,101",
"2015-12-02,app_installs,982",
"2016-09-17,app_installs,770",
"2015-11-07,impressions,245",
"2016-10-16,impressions,567"};
		TreeMap<String, TreeMap<String, Integer> >treeMap = new TreeMap<>(Collections.reverseOrder());
		String line = null;
		//Scanner scanner = new Scanner(System.in);
		line = input[0];
		String start = line.split(",")[0];
		String end = line.split(",")[1];
		//System.out.println(start+","+end);
		//line = scanner.nextLine();
		for(int i=2; i<input.length; i++){
			line = input[i];
			String[] input1 = line.split(",");
			String date = removeDD(input1[0]);	
			String type = input1[1];
			int number = Integer.parseInt(input1[2]);
			if(date.compareTo(start) >=0 && end.compareTo(date)>0){
				//System.out.println("date: "+date);
				if(treeMap.containsKey(date)){
					treeMap.get(date).put(type, number);
				}else{
					//System.out.println("update the new key");
					TreeMap<String, Integer> temp = new TreeMap<String, Integer>();
					temp.put(type, number);
					treeMap.put(date, temp);
				}
			}		
		}
		//System.out.println(treeMap.size());
		for(Map.Entry<String, TreeMap<String, Integer> > entry: treeMap.entrySet()){
			TreeMap<String, Integer> temp = entry.getValue();
			StringBuilder builder = new StringBuilder();
			builder.append(entry.getKey()+",");
			
			for(Map.Entry<String, Integer> entryTemp:temp.entrySet()){	
				builder.append(" "+entryTemp.getKey()+",");
				builder.append(" "+entryTemp.getValue()+",");			
			}	
			builder.deleteCharAt(builder.length()-1);
			System.out.println(builder.toString());
		}
	}
}
