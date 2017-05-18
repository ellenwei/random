import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class NumberClient extends Thread{
	private int num;
	private String hostName;
	private int port;
	private static Lock lock = new ReentrantLock();
	public NumberClient(int num, String hostName, int port)
	{
		this.num = num;
		this.hostName = hostName;
		this.port = port;
	}
	private void printArrayList(ArrayList<Integer> list)
	{
		lock.lock();
		try{
		System.out.print(num + ":");
		for (int i=0; i<list.size(); i++)
		{
			System.out.print(list.get(i));
			if (i<list.size()-1)
				System.out.print(",");
		}
		System.out.println();
		}finally
		{
			lock.unlock();
		}
	}
	public void run()
	{
		Socket s = null;
		PrintWriter pw = null;
		ObjectInputStream ois = null;
		try
		{
			s = new Socket(hostName, port);
			pw = new PrintWriter(s.getOutputStream());
			ois = new ObjectInputStream(s.getInputStream());
			pw.println(this.num);
			pw.flush();
			ArrayList<Integer> list = (ArrayList<Integer>)ois.readObject();		
			printArrayList(list);
		}
		catch(ClassNotFoundException cnfe)
		{
			System.out.println("cnfe: " + cnfe.getMessage());
		}
		catch(IOException ioe)
		{
			System.out.println("ioe: " + ioe.getMessage());
		}finally
		{
			try
			{
				if (s != null)
				{
					s.close();
				}
			}catch(IOException ioe)
			{
				System.out.println("ioe closing streams: " + ioe.getMessage());
			}
		}
	}
	public static void main(String[] argv)
	{
		for (int i=0; i<100; i++)
		{
			NumberClient nc = new NumberClient(i, "localhost", 6789);
			nc.start();
		}
	}
	
}
