import java.util.LinkedList;
import java.util.List;

public class SpiralMatrix {
	public List<Integer> spiralOrder(int[][] matrix) {
		List<Integer> result = new LinkedList<>();
        if(matrix == null || matrix.length == 0 || matrix[0].length == 0){
            return result;
        }
		int rowMin = 0, rowMax = matrix.length-1, colMin = 0, colMax = matrix[0].length-1;
		while(rowMin<=rowMax && colMin<=colMax){
			for(int i=colMin; i<=colMax; i++){
				result.add(matrix[rowMin][i]);
			}
			rowMin++;
			for(int i=rowMin; i<=rowMax; i++){
				result.add(matrix[i][colMax]);
			}
			colMax--;
			if(rowMin>rowMax || colMax<colMin){
				return result;
			}
			for(int i=colMax; i>=colMin; i--){
				result.add(matrix[rowMax][i]);
			}
			rowMax--;
			for(int i=rowMax; i>=rowMin; i--){
				result.add(matrix[i][colMin]);
			}
			colMin++;
		}
		return result;
	}
}
