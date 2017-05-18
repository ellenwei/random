package icecream;

class IceCream
	{
		public int value;
		public int price;
		
		IceCream(int value, int price)
		{
			this.value = value;
			this.price = price;
		}
	}


public class maxIcecream {

	public static int buyIceCream(int budget, IceCream ic[], int n )
	{
		int[] result = new int[budget+1];
		for (int i=0; i<=budget; i++)
		{
			result[i] = 0;
		}
		for (int i = 0; i<n; i++)
		{
			for (int j = budget; j>= 0; j--)
			{
				if (j>=ic[i].price)
				{
					result[j] = Math.max(result[j], result[j-ic[i].price]+ic[i].value);
				}
				//result[j] = Math.max(result[j], result[j-ic[i].price]+ic[i].value);
			}
		}
		for (int i=0; i< budget; i++)
		{
			System.out.println("index: " + i + " value " + result[i]);
		}
		if (result[budget] < 0)
		{
			return -1;
		}
		else 
			return result[budget];
		
	}
	
	public static void main(String[] argv)
	{
		IceCream[] ic = new IceCream[4];
		IceCream ice1 = new IceCream(5,4);
		ic[0] = ice1;
		IceCream ice2 = new IceCream(8,7);
		ic[1] = ice2;
		IceCream ice3 = new IceCream(4,3);
		ic[2] = ice3;
		IceCream ice4 = new IceCream(10,14);
		ic[3] = ice4;
		int result = buyIceCream(14,ic,4);
		System.out.println(result);
		
	}
}
