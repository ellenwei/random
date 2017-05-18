package interview;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Vector;


class Heap
{
	private ArrayList<Integer> list;
	Heap()
	{
		list = new ArrayList<Integer>();
	}
	public void insert(int num)
	{
		//System.out.println("The number is: " + num);
		if (list.isEmpty())
		{
			//System.out.println("When list is empty: " + num);
			list.add(0, num);
			//print();
		}
		else
		{
			list.add(num);
			int last = list.size()-1;
			int parent = (last-1)/2;
			if (num < list.get(parent))
			{
				//System.out.println("When num < parent: The number is: " + num + "parent: " + list.get(parent));
				
				//print();
				return;
			}
			while ( list.get(parent) < num )
			{
				//System.out.println("num larger than parent");
				swap((last-1)/2, last);
				last = parent;
				//System.out.println("The check one is at: " + last);
				if (last == 0)
				{
					//print();
					break;
				}
				parent = (last-1)/2;
				//System.out.println("The parent one is at: " + parent);
				//print();
			}
		}
	}
	public void remove()
	{
		if (list.size() == 0)
		{
			return;
		}
		list.set(0, list.get(list.size()-1));
		list.remove(list.size()-1);
		int parent = 0;
		int childL = parent*2+1;
		int childR = parent*2+2;
		int parentV = list.get(parent);
		while (childL < list.size() || childR < list.size())
		{
			int left = list.get(childL);
			int right = list.get(childR);
			if (left > right)
			{
				swap(childL, parent);
				parent = childL;
				childL = parent*2+1;
				childR = parent*2+2;
				parentV = list.get(parent);
				System.out.print("When left larger than right");
				print();
			}
			else
			{
				swap(childR, parent);
				parent = childR;
				childL = parent*2+1;
				childR = parent*2+2;
				parentV = list.get(parent);
				System.out.print("When right larger than left");
				print();
			}
		}
					
	}
	public void swap(int parent, int child)
	{
		int temp = list.get(child);
		int parentV = list.get(parent);
		list.set(child,parentV);
		list.set(parent, temp);		
		//print();
	}
	public void print()
	{
		for (int i=0; i<list.size(); i++)
		{
			System.out.println(list.get(i));
		}
		
	}
}
 class Fruit implements Comparable<Fruit>
{
	public String name;
	public int num;
	Fruit (String name, int num)
	{
		this.name = name;
		this.num = num;
	}
	public String getName()
	{
		return name;
	}
	public int getQuantity()
	{
		return num;
	}
	@Override
	public int compareTo(Fruit o) {
		// TODO Auto-generated method stub
		
		return this.getQuantity() - o.getQuantity();
	}
	
	public static Comparator<Fruit> fruitNameComparator = new Comparator<Fruit>(){
		public int compare(Fruit fruit1, Fruit fruit2)
		{
			String fruitN1 = fruit1.getName().toLowerCase();
			String fruitN2 = fruit2.getName().toLowerCase();
			return fruitN1.compareTo(fruitN2);
		}
	};
	
}


public class practice {
	
	void mergeSort(Vector<Integer> list)
	{
		Vector<Integer> other = new Vector<Integer>();
		other.addAll(0,list);
		msort(other, list, 0, list.size());
		
	}
	void msort(Vector<Integer> myList, Vector<Integer> output, int start, int end)
	{
		if (start > end)
		{
			return;
		}
		else
		{
			int mid = (start+end)/2;
			msort(myList, output, 0, mid);
			msort(myList, output, mid+1, end);
			helper(myList, output, start, mid, mid, end);
		}
	}
	void helper(Vector<Integer> my, Vector<Integer> output, int s1, int e1, int s2,int e2)
	{
		int pointer1 = s1;
		int pointer2 = s2;
		while (pointer1<e1 && pointer2<e2)
		{
			if (my.get(pointer1) < my.get(pointer2))
			{
				output.addElement(my.get(pointer1));
				pointer1++;
			}
			else if (my.get(pointer2)>my.get(pointer1))
			{
				output.addElement(my.get(pointer2));
				pointer2++;
			}
		}
		while (pointer1<=e1)
		{
			
		}
	}
	public static void main(String[] argv)
	{
		/*
		Fruit[] fruit = new Fruit[4];
			fruit[0] = new Fruit("apple", 8);
			fruit[1] = new Fruit("KIwi", 4);
			fruit[2] = new Fruit("banana", 2);
			fruit[3] = new Fruit("Orange", 7);
			PriorityQueue<Fruit> p = new PriorityQueue<Fruit>(4, Fruit.fruitNameComparator);
			p.add(fruit[0]);
			p.add(fruit[1]);
			p.add(fruit[2]);
			p.add(fruit[3]);
			System.out.println(p.peek().getName());
			System.out.println(p.poll().getName());
			System.out.println(p.poll().getName());
			System.out.println(p.poll().getName());
			System.out.println(p.poll().getName());
			//System.out.println(p.element().getName());
			//System.out.println(p.element().getName());
		Arrays.sort(fruit, Fruit.fruitNameComparator);
		
		for (Fruit temp: fruit)
		{
			System.out.println(temp.getName());
		}
		*/
		Heap heap = new Heap();
		heap.insert(3);
		heap.insert(2);
		heap.insert(2);
		heap.insert(1);
		heap.insert(3);
		heap.insert(4);
		heap.insert(5);
		heap.print();
		heap.remove();
		System.out.println("After remove");
		heap.print();
		
	}

}
