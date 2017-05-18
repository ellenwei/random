import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;

public class NumberThread extends Thread{
	private Socket s;
	private NumberServer ns;
	public NumberThread(Socket s, NumberServer ns)
	{
		this.s = s;
		this.ns = ns;
	}
	public void run()
	{
		BufferedReader br = null;
		ObjectOutputStream oos = null;
		try
		{
			br = new BufferedReader(new InputStreamReader(s.getInputStream()));
			oos = new ObjectOutputStream(s.getOutputStream());
			int num = Integer.parseInt(br.readLine());
			synchronized(ns){
			ArrayList<Integer> list = ns.addNumberAndGetArrayList(num);
			oos.writeObject(list);
			oos.flush();
			}
		}catch(IOException ioe)
		{
			System.out.println("ioe in NumberThread.run() " + ioe.getMessage());
		}finally
		{
			try
			{
				if (br != null)
				{
					br.close();
				}
				if (oos != null)
				{
					oos.close();
				}
				if (s != null)
				{
					s.close();
				}
			}catch(IOException ioe)
			{
				System.out.println("ioe: " + ioe.getMessage());
			}
		}
	}
}
