import java.util.LinkedList;
import java.util.List;

public class MenuOrder {
	static class Item{
		String itemName;
		int price;
		
		public Item(String itemName, double price){
			this.itemName = itemName;
			this.price = (int) (price*100);
		}
	}
	
	public static List<List<Item>> menuOrder(List<Item>menu, int target){
		List<List<Item>>result = new LinkedList<>();
		if(menu == null || menu.size() == 0 || target == 0){
			return result;
		}
		backTrack(result, new LinkedList<>(), menu, target, 0);
		return result;
	}
	
	public static void backTrack(List<List<Item>> result, List<Item> list, List<Item> menu, int target, int start){
		if(target<0){
			return;
		}else if(target == 0){
			result.add(new LinkedList<>(list));
		}else{
			for(int i=start; i<menu.size(); i++){
				list.add(menu.get(i));
				backTrack(result, list, menu, target-menu.get(i).price, i);
				list.remove(list.size()-1);
			}
		}
	}
	
	public static void main(String[] argv){
		List<Item> menu = new LinkedList<>();
		menu.add(new Item("apple", 2.45));
		menu.add(new Item("pie", 1.55));
		menu.add(new Item("tea", 2.00));
		menu.add(new Item("coffee", 2.00));
		menu.add(new Item("juice", 4.00));
		menu.add(new Item("chip", 1.00));
		for(List<Item> itemList:menuOrder(menu, 400)){
			for(Item item:itemList){
				System.out.print(item.itemName+" ");
			}
			System.out.println(" ");
		}
	}
}
