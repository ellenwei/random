import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ChatThread extends Thread{
	private Socket s;
	private BufferedReader br;
	private PrintWriter pw;
	private ChatServer cs;
	public ChatThread(Socket s, ChatServer cs)
	{
		this.cs = cs;
		this.s = s;
		try
		{
			br = new BufferedReader (new InputStreamReader(s.getInputStream()));
			pw = new PrintWriter(s.getOutputStream());
		}
		catch(IOException ioe)
		{
			System.out.println("ioe in ChatThread constructor: " + ioe.getMessage());
		}
	}
	public void run()
	{
		try
		{
			while(true)
			{
				String message = br.readLine();
				if (message == null)
				{
					break;
				}
				cs.sendMessageToAllClients(message, this);
				
			}
		}
		catch(IOException ioe)
		{
			System.out.println("ioe in ChatThread.run() " + ioe.getMessage());
		}
	}
	
	public void sendMessage(String message)
	{
		if (pw != null)
		{
			pw.println(message);
			pw.flush();
		}
	}
	
}
