package interviewPractice;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.PriorityQueue;

import javax.swing.text.html.HTMLDocument.Iterator;

public class kthFrequentNumber {	
	static int findKth(int[] arr, int k)
	{
		if (arr.length < k)
		{
			return -1;
		}
		Comparator<Integer> comparator = new numComparator();
		HashMap<Integer, Integer> store = new HashMap<Integer, Integer>();
		PriorityQueue<Integer> sort = new PriorityQueue<Integer>(arr.length, comparator);
		for (int i=0; i<arr.length; i++)
		{
			if (store.containsKey(arr[i]))
			{
				store.put(arr[i], store.get(arr[i])+1);				
			}
			else
			{
				store.put(arr[i],1);			
			}		
		}
		java.util.Iterator<Entry<Integer, Integer>> it = store.entrySet().iterator();
	    while (it.hasNext()) {
	    	HashMap.Entry pair = (HashMap.Entry)it.next();
	    	sort.add((Integer) pair.getValue());
	    }		
		int desCount = 0;
		int count = k;
		while (count !=0)
		{	
			desCount = sort.poll();
			System.out.println(desCount);
			count--;
		}
		java.util.Iterator<Entry<Integer, Integer>> it1 = store.entrySet().iterator();
	    while (it1.hasNext()) {
	    	HashMap.Entry pair = (HashMap.Entry)it1.next();
	    	if (pair.getValue() == (Integer)desCount)
	    	{
	    		return (int) pair.getKey();
	    	}
	    }
		return -1;
	}
	
	static class numComparator implements Comparator<Integer>
	{		
		@Override
		public int compare(Integer x, Integer y) {
			// TODO Auto-generated method stub
			return y-x;
		}		
	}
	
	public static void main(String[] argv)
	{
		int[] arr = {};
		int k = 3;
		System.out.println(findKth(arr,k));
	}

}
