package csci201client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class ChatClient extends Thread {
	private BufferedReader br;
	private PrintWriter pw;
	public ChatClient(String hostname, int port)
	{
		try
		{
			Socket s = new Socket(hostname, port);
			br = new BufferedReader(new InputStreamReader(s.getInputStream()));
			pw = new PrintWriter(s.getOutputStream());
			this.start();
			Scanner scan = new Scanner(System.in);
			while (true)
			{
				String line = scan.nextLine();
				//pw.println(line);
				pw.println("Ellen: " + line);
				pw.flush();
			}
			
		}
		catch(IOException ioe)
		{
			System.out.println("ioe in ChatClient constructor: " + ioe.getMessage());
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
			System.out.println(message);
		}
		}
		catch(IOException ioe)
		{
			System.out.println("ioe in ChatClient.run() " + ioe.getMessage());
		}
	}
	public static void main(String[] argv)
	{
		new ChatClient ("localhost", 6789);
	}
}
