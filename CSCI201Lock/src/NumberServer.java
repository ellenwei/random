import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class NumberServer {
	private ArrayList<Integer> arrayList;
	public NumberServer(int port)
	{
		ServerSocket ss = null;
		arrayList = new ArrayList<Integer>();
		try
		{
			ss = new ServerSocket(port);
			System.out.println("bound to port: " + port);
			while (true)
			{
				Socket s = ss.accept();
				System.out.println("Connection from "+ s.getInetAddress() + ":" + s.getPort());
				NumberThread nt = new NumberThread(s,this);
				nt.start();
			}
		}
		catch(IOException ioe)
		{
			System.out.println("ioe: " + ioe.getMessage());
		}
		finally
		{
			try
			{
				if ( ss != null)
				{
					ss.close();
				}
				
			}catch(IOException ioe)
			{
				System.out.println("ioe closing ss: " + ioe.getMessage());
			}
		}
	}
	public  ArrayList<Integer> addNumberAndGetArrayList(int num)
	{
		addNumber(num);
		return getArrayList();
	}
	private void addNumber(int num)
	{
		arrayList.add(num);
	}
	private ArrayList<Integer> getArrayList()
	{
		return arrayList;
	}
	public static void main(String[] argv)
	{
		new NumberServer(6789);
	}
}
