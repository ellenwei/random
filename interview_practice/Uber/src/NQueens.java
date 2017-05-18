import java.util.LinkedList;
import java.util.List;

public class NQueens {
	public List<List<String>> solveNQueens(int n) {
		List<List<String>> result = new LinkedList<>();
		char[][]board = new char[n][n];
		helper(result, board, 0);
		return result;
	}
	
	public void helper(List<List<String>> result, char[][] board, int columnIndex){
		if(columnIndex == board.length){
			result.add(buildResult(board));
			return;
		}
		for(int i=0; i<board.length; i++){
				if(isValid(board, i, columnIndex)){
					board[i][columnIndex] = 'Q';	
				}
				helper(result, board, columnIndex+1);
				board[i][columnIndex] ='.';
		}
	}
	
	boolean isValid(char[][]board, int rowIndex, int columnIndex){
		for(int i=0; i<board.length; i++){
			for(int j=0; j<columnIndex; j++){
				if(board[i][j] == 'Q' && (i+j == rowIndex+columnIndex || rowIndex+j == columnIndex+i || i == rowIndex)){
					return false;
				}
			}
		}
		return true;
	}
	
	List<String> buildResult(char[][] board){
		List<String> result = new LinkedList<>();
		for(char[] arr:board){
			String string = new String(arr);
			result.add(string);
		}
		return result;
	}
}
