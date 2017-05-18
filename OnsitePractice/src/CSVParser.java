import java.util.LinkedList;
import java.util.List;

public class CSVParser {
	/**
	 * John,Smith,john.smith@gmail.com,Los Angeles,1
		Jane,Roberts,janer@msn.com,"San Francisco, CA",0
		"Alexandra ""Alex""",Menendez,alex.menendez@gmail.com,Miami,1
		"""Alexandra Alex"""
	
		John|Smith|john.smith@gmail.com|Los Angeles|1
		Jane|Roberts|janer@msn.com|San Francisco, CA|0
		Alexandra "Alex"|Menendez|alex.menendez@gmail.com|Miami|1
		"Alexandra Alex"
	 * @param string
	 * @return
	 */
	
	public static String csvParser1(String string){
		if(string == null || string.length() == 0){
			return "";
		}
		List<String> result = new LinkedList<>();
		StringBuilder builder = new StringBuilder();
		boolean inQuotation = false;
		for(int i=0; i<string.length(); i++){
			char c = string.charAt(i);
			if(inQuotation){
				if(c == '"'){
					if(i == string.length()-1){
						result.add(builder.toString());
						return buildString(result);
					}else if(string.charAt(i+1) == '"'){
						builder.append('"');
						i++;
					}else{
						result.add(builder.toString());
						builder.setLength(0);
						inQuotation = false;
						i++;
					}	
				}else{
					builder.append(c);
				}
			}else{
				if(c == '"'){
					inQuotation = true;
				}else if(c == ','){
					result.add(builder.toString());
					builder.setLength(0);
				}else{
					builder.append(c);
				}
			}
		}
		if(builder.length()>0){
			result.add(builder.toString());
		}
		return buildString(result);
	}
	
	public static String csvParser(String string){
		if(string == null || string.length() == 0){
			return "";
		}
		boolean inQuotation = false;
		List<String>result = new LinkedList<>();
		StringBuilder builder = new StringBuilder();
		for(int i=0; i<string.length(); i++){
			char c= string.charAt(i);
			if(inQuotation){
				if(c == '"'){
					if(i == string.length()-1){
						result.add(builder.toString());
						return buildString(result);
					}else if(string.charAt(i+1) == '"'){
						builder.append('"');
						i++;
					}else{
						result.add(builder.toString());
						builder.setLength(0);
						inQuotation = false;
						i++;
					}
				}else{
					builder.append(c);
				}
				
			}else{
				if(c == '"'){
					inQuotation = true;
				}else if(c == ','){
					result.add(builder.toString());
					builder.setLength(0);
				}else{
					builder.append(c);
				}
			}
		}
		if(builder.length()>0){
			result.add(builder.toString());
		}
		return buildString(result);
	}
	
	public static String buildString(List<String> result){
		if(result.size() == 0){
			return "";
		}
		StringBuilder finalBuilder = new StringBuilder();
		for(String string:result){
			finalBuilder.append(string+"|");
		}
		finalBuilder.deleteCharAt(finalBuilder.length()-1);
		return finalBuilder.toString();
	}
	
	public static void main(String[] argv){
		String csvString1 = "John,Smith,john.smith@gmail.com,Los Angeles,1";
		String csvString2 = "Jane,Roberts,janer@msn.com,\"San Francisco, CA\",0";
		String csvString3 = "\"Alexandra \"\"Alex\"\"\",Menendez,alex.menendez@gmail.com,Miami,1";
		String csvString4 = "\"\"\"Alexandra Alex\"\"\"";
		System.out.println("result of csv1 is: "+ csvParser1(csvString1));
		System.out.println("result of csv1 is: "+ csvParser1(csvString2));
		System.out.println("result of csv1 is: "+ csvParser1(csvString3));
		System.out.println("result of csv1 is: "+ csvParser1(csvString4));
	}
}
