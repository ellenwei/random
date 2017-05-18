import java.util.ArrayList;
import java.util.List;

public class FileNode {
	public int previousPathLength;
	public boolean containsImage;
	public List<FileNode> content = new ArrayList<>();
	public int spaceNumber;
	public String line;
	
	
	public FileNode(int previousPathLength, String line){
		containsImage = false;
		this.line = line;
		calculateSpace();
		this.previousPathLength = previousPathLength;
	}
	
	public boolean checkImage(){
		
		return this.line.toLowerCase().contains(".jpeg")||this.line.toLowerCase().contains(".gif")||this.line.toLowerCase().contains(".png");
	}
	
	private void calculateSpace(){
		int count = 0;
		for (char character : this.line.toCharArray()){
			if (character == ' '){
				count++;
			}
		}
		this.spaceNumber = count;
	}
	
	public void addContent(FileNode contentNode){
		this.content.add(contentNode);
	}

}
