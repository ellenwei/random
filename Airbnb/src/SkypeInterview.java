import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

public class SkypeInterview {
	
	// 思路就是先把所有floor加起来，然后看差多少，然后把多少个floor转成ceil
    // 转的时候按照num本身与ceil的距离排序
    public static int[] getNearlyArrayWithSameSum(double[] arr) {
            NumWithDiff[] arrWithDiff = new NumWithDiff[arr.length];
            double sum = 0.0;
            int floorSum = 0;
            for (int i = 0; i < arr.length; i++) {
                    int floor = (int)arr[i];
                    int ceil = floor;
                    if (floor < arr[i]) ceil++; // 查是不是4.0这种floor/ceil都是本身的
                    floorSum += floor;
                    sum += arr[i];
                    arrWithDiff[i] = new NumWithDiff(ceil, ceil - arr[i]); // 把ceil都放在数组里面进行比较
            }
            int target = (int) Math.round(sum);
            int diff = target - floorSum;
            Arrays.sort(arrWithDiff, new Comparator<NumWithDiff>() {
                    public int compare(NumWithDiff n1, NumWithDiff n2) {
                            if (n1.diffWithCeil <= n2.diffWithCeil) return -1;
                            else return 1;
                    }
            });
            int[] res = new int[arr.length];
            int i = 0;
            for (; i < diff; i++) {
                    res[i] = arrWithDiff[i].num; // 这些放ceil
            }
            for (; i < arr.length; i++) {
                    res[i] = arrWithDiff[i].num - 1; // 剩下的只放floor
            }
            return res;
    }
    
    /**
     * [
  "wrt",
  "wrf",
  "er",
  "ett",
  "rftt"
]

"wertf"
     * @param words
     * @return
     */
    public String alienDictionary(String[] words) {
        Map<Character, HashSet<Character>> map = new HashMap<>();
        int[] arr = new int[26];
        for (int i=0;i<words.length; i++){
            char[] temp = words[i].toCharArray();
            for (char c:temp){
                if (!map.containsKey(c)){
                    map.put(c, new HashSet<Character>());
                }
            }
            if (i == 0){
                continue;
            }
            int len = Math.min(words[i-1].length(), words[i].length());
            int p = 0;
            while(p<len && words[i-1].charAt(p) == words[i].charAt(p)){
                p++;
            }
            if (p == len){
                continue;
            }
            char c1 = words[i-1].charAt(p);
            char c2 = words[i].charAt(p);
            if (map.get(c1).add(c2)){
                arr[c2-'a']++;
            }
        }
        Queue<Character> queue = new LinkedList<>();
        for (int i=0; i<arr.length; i++){
            char c = (char)(i+'a');
            if (arr[i]==0 && map.containsKey(c)){
                queue.offer(c);
            }
        }
        StringBuilder builder = new StringBuilder();
        while (!queue.isEmpty()){
            char current = queue.poll();
            builder.append(current);
            for (Object obj:map.get(current)){
                char c = (char)obj;
                arr[c-'a']--;
                if (arr[c-'a'] == 0){
                    queue.offer(c);
                }
            }
        }
        for (int i=0; i<arr.length; i++){
            if (arr[i]>0){
                return "";
            }
        }
        return builder.toString();
    }
    
	
	/**
	 * Test:
	 * 
	  String[] strs = new String[]{
			      "1,28,300.1,SanFrancisco",
			      "4,5,209.1,SanFrancisco",
			      "20,7,208.1,SanFrancisco",
			      "23,8,207.1,SanFrancisco",
			      "16,10,206.1,Oakland",
			      "1,16,205.1,SanFrancisco",
			      "6,29,204.1,SanFrancisco",
			      "7,20,203.1,SanFrancisco",
			      "8,21,202.1,SanFrancisco",
			      "2,18,201.1,SanFrancisco",
			      "2,30,200.1,SanFrancisco",
			      "15,27,109.1,Oakland",
			      "10,13,108.1,Oakland",
			      "11,26,107.1,Oakland",
			      "12,9,106.1,Oakland",
			      "13,1,105.1,Oakland",
			      "22,17,104.1,Oakland",
			      "1,2,103.1,Oakland",
			      "28,24,102.1,Oakland",
			      "18,14,11.1,SanJose",
			      "6,25,10.1,Oakland",
			      "19,15,9.1,SanJose",
			      "3,19,8.1,SanJose",
			      "3,11,7.1,Oakland",
			      "27,12,6.1,Oakland",
			      "1,3,5.1,Oakland",
			      "25,4,4.1,SanJose",
			      "5,6,3.1,SanJose",
			      "29,22,2.1,SanJose",
			      "30,23,1.1,SanJose"
			    };
		List<String> input = new ArrayList<>(Arrays.asList(strs));
		displayPage(input);
	 * 
	 * 
	 * @param input
	 */
	
	public static void displayPage(List<String> input){
		if (input == null || input.size() == 0){
			return;
		}
		Set<String> check = new HashSet<String>();
		Iterator<String> iterator = input.iterator();
		int pageCount = 1;
		System.out.println("Page: "+pageCount);
		while (iterator.hasNext()){
			String entry = iterator.next();
			String hostId = entry.split(",")[0];
			if (!check.contains(hostId)){
				System.out.println(entry);
				check.add(hostId);
				iterator.remove();
			}
			if (check.size() == 12 || !iterator.hasNext()){
				check.clear();
				iterator = input.iterator();
				if (!input.isEmpty()){
					pageCount++;
					System.out.println("Page: "+pageCount);				
				}
			}
		}
	}
	
	
	/**
	 * Test:
	 * 
	 * MenuItem item1 = new MenuItem("lol", 233);
		MenuItem item2 = new MenuItem("lok", 233);
		MenuItem item3 = new MenuItem("qwe", 200);
		MenuItem item4 = new MenuItem("lolr", 100);
		MenuItem item5 = new MenuItem("sxd", 67);
		List<MenuItem> testMenu = new LinkedList<>();
		testMenu.add(item1);
		testMenu.add(item2);
		testMenu.add(item3);
		testMenu.add(item4);
		testMenu.add(item5);
		List<List<MenuItem>> finalChoice = orderMenu(testMenu, 300);
		for (int i=0; i<finalChoice.size(); i++){
			System.out.print("Choice is: ");
			for (int j=0; j<finalChoice.get(i).size(); j++){
				System.out.print(finalChoice.get(i).get(j).dishName+" ");
			}
			System.out.println("");
		}
	 * 
	 * @param menu
	 * @param money
	 * @return
	 */
	public static List<List<MenuItem>> orderMenu(List<MenuItem> menu, double money){
		List<List<MenuItem>> result = new ArrayList<>();
		backtrack(result, new ArrayList<MenuItem>(), menu, money,0);
		return result;	
	}
	
	public static void backtrack(List<List<MenuItem>> result, List<MenuItem> list, List<MenuItem> menu, double money, int start){
		if (money<0){
			return;
		}else if(money==0){
			result.add(new ArrayList<>(list));
		}else{
			for (int i=start; i<menu.size(); i++){
				list.add(menu.get(i));
				backtrack(result, list, menu, money-menu.get(i).price, i);
				list.remove(list.size()-1);
			}
		}
		
	}
	
	
	/**
	 * Test:
	 * String badEncStr = "kljJj324hijks_";
	    Integer resulta = decodeFind(badEncStr);
	     
	    System.out.println(resulta);
	    
	 * @param badEncString
	 * @return
	 */
	public static Integer decodeFind(String badEncString) {
	    if (badEncString == null || badEncString.length() == 0) {
	      return -1;
	    }     
	    StringBuffer sb = new StringBuffer();   
	    return decodeFindHelper(0, sb, badEncString);
	  }
	   
	  private static Integer decodeFindHelper(int start, StringBuffer curr, String badEncString) {
	    if (start == badEncString.length()) {
	      String testEncStr = curr.toString();
	      Integer result = decode(testEncStr);	       
	      if (result != null) {
	        return result;
	      } else {
	        return null;
	      }
	    }	     
	    char c = badEncString.charAt(start);
	    if (!Character.isLetter(c)) {
	      curr.append(c);
	      Integer result = decodeFindHelper(start + 1, curr, badEncString);
	      if (result != null) {
	        return result;
	      }
	      curr.deleteCharAt(curr.length() - 1);
	    } else {
	      // To lower case
	      char lower = Character.toLowerCase(c);
	      curr.append(lower);
	      Integer result = decodeFindHelper(start + 1, curr, badEncString);
	      if (result != null) {
	        return result;
	      }
	      curr.deleteCharAt(curr.length() - 1);
	       
	      // To upper case
	      char upper = Character.toUpperCase(c);
	      curr.append(upper);
	      result = decodeFindHelper(start + 1, curr, badEncString);
	      if (result != null) {
	        return result;
	      }
	      curr.deleteCharAt(curr.length() - 1);
	    }	     
	    return null;
	  }
	   
	  public static Integer decode(String testEncStr) {
	    String truth = "kljJJ324hijkS_";     
	    if (testEncStr.equals(truth)) {
	      return 848662;
	    } else {
	      return null;
	    }
	  }
	  
	  
	  /**
	   * Test:
	   * 
	   * String csvString1 = "John,Smith,john.smith@gmail.com,Los Angeles,1";
		String csvString2 = "Jane,Roberts,janer@msn.com,\"San Francisco, CA\",0";
		String csvString3 = "\"Alexandra \"\"Alex\"\"\",Menendez,alex.menendez@gmail.com,Miami,1";
		String csvString4 = "\"\"\"Alexandra Alex\"\"\"";
		
		John,Smith,john.smith@gmail.com,Los Angeles,1
		Jane,Roberts,janer@msn.com,"San Francisco, CA",0
		"Alexandra ""Alex""",Menendez,alex.menendez@gmail.com,Miami,1
		"""Alexandra Alex"""
	
		John|Smith|john.smith@gmail.com|Los Angeles|1
		Jane|Roberts|janer@msn.com|San Francisco, CA|0
		Alexandra "Alex"|Menendez|alex.menendez@gmail.com|Miami|1
		"Alexandra Alex"
	
		System.out.println(CSVReader(csvString1));
		System.out.println(CSVReader(csvString2));
		System.out.println(CSVReader(csvString3));
		System.out.println(CSVReader(csvString4));
	   * 
	   * @param string
	   * @return
	   */
	  
		public static String CSVReader(String string){
			List<String> result = new ArrayList<>();
			if (string == null || string.length() == 0){
				return "";
			}
			boolean inQuotation = false;
			StringBuilder builder = new StringBuilder();
			for (int i=0; i<string.length(); i++){
				char current = string.charAt(i);
				if(inQuotation){
					if(current == '"'){
						if(i == string.length()-1){
							result.add(builder.toString());
							return printString(result);				
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
						builder.append(current);
					}
				}else{
					if(current == '"'){
						inQuotation = true;
					}else if (current == ','){
						result.add(builder.toString());
						builder.setLength(0);
					}else{
						builder.append(current);
					}
				}
			}
			if (builder.length()>0){
				result.add(builder.toString());
			}
			return printString(result);
		}
		
		public static String printString(List<String> result){
			StringBuilder builder = new StringBuilder();
			for (int i=0; i<result.size(); i++){
				builder.append(result.get(i));
				if (i+1 < result.size()){
					builder.append('|');
				}
			}
			return builder.toString();
		}
}
