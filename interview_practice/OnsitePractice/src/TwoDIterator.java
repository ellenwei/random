import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class TwoDIterator implements Iterator<Integer>{
	private Iterator<List<Integer>> row;
    private Iterator<Integer> column;
    
    public TwoDIterator(List<List<Integer>> vec2d) {
        row = vec2d.iterator();
    }
    
    @Override
    public Integer next() {
        hasNext();
        return column.next();
    }
    
    @Override
    public boolean hasNext() {
    	if((column == null || !column.hasNext()) && row.hasNext()){
            column = row.next().iterator();
        }
        return column!=null && column.hasNext();
    }
    
    @Override
    public void remove(){
    	hasNext();
    	column.remove();
    }
    
    public static void main(String[] argv){
    	List<List<Integer>> all = new LinkedList<>();
    	List<Integer>list1 = new LinkedList<>();
    	list1.add(3);
    	list1.add(2);
    	
    	all.add(list1);
    	List<Integer>list2 = new LinkedList<>();
    	list2.add(5);
    	all.add(list2);
    	List<Integer>list3 = new LinkedList<>();
    	list3.add(0);
    	list3.add(2);
    	list3.add(4);
    	all.add(list3);
    	TwoDIterator iterator = new TwoDIterator(all);
    	System.out.println(iterator.hasNext());
    	System.out.println(iterator.next());
    	System.out.println(iterator.next());
    	System.out.println(iterator.next());
    	//System.out.println(iterator.hasNext());
    	System.out.println(iterator.next());
    	System.out.println(iterator.next());
    	System.out.println(iterator.next());
    	iterator.remove();
    	System.out.println(iterator.hasNext());
    }
}
