
public class TextJustification {
	
	public static void textJustification1(String[] words, int maxWidth){
		int index = 0;
		while(index<words.length){
			int last = index+1;
			int currentLength = words[index].length();
			while(last<words.length){
				if(currentLength+words[last].length()+1>maxWidth){
					break;
				}
				currentLength = currentLength+words[last].length()+1;
				last++;
			}
			int gap = last-index-1;
			StringBuilder builder = new StringBuilder();
			if(gap == 0 || last == words.length){
				for(int i=index; i<last; i++){
					builder.append(words[i]+" ");
				}
				builder.deleteCharAt(builder.length()-1);
				for(int i=last; i<maxWidth; i++){
					builder.append(" ");
				}
			}else{
				int count = (maxWidth-currentLength)/gap;
				int extra = (maxWidth-currentLength)%gap;
				for(int i=index; i<last; i++){
					builder.append(words[i]);
					if(i<last-1){
						int addOne = 0;
						if(i-index<extra){
							addOne = 1;
						}
						for(int j=0; j<=count+addOne; j++){
							builder.append(" ");
						}
					}
				}
			}
			System.out.println(builder.toString());
			index = last;
		}
	}
	
	public static void textJustification(String[] words, int maxWidth){
		int index = 0;
		while(index<words.length){
			int last = index+1;
			int currentLength = words[index].length();
			while(last<words.length){
				if(currentLength+words[last].length()+1>maxWidth){
					break;
				}
				currentLength = currentLength+words[last].length()+1;
				last++;
			}
			StringBuilder builder = new StringBuilder();
			int gap = last-index-1;
			if(gap == 0 || last == words.length){
				for(int i=index; i<last; i++){
					builder.append(words[i]+ " ");
				}
				builder.deleteCharAt(builder.length()-1);
				for(int i=last; i<maxWidth; i++){
					builder.append(" ");
				}
			}else{
				int count = (maxWidth-currentLength)/gap;
				int extra = (maxWidth-currentLength)%gap;
				for(int i=index; i<last; i++){
					builder.append(words[i]);
					if(i<last-1){
						int addOne = 0;
						if(i-index<extra){
							addOne = 1;
						}
						for(int j=0; j<count+addOne; j++){
							builder.append(" ");
						}
					}
				}
			}
			System.out.println(builder.toString());
			index = last;
		}
	}
	
	public static void main(String[] argv){
		String[] input = {"This", "is", "an", "example", "of", "text", "justification."};
		textJustification1(input, 16);
	}
}
