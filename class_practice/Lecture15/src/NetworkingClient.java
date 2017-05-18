import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class NetworkingClient {
	
	public NetworkingClient (String hostname, int port)
	{
		try
		{
			Socket s = new Socket(hostname, port);
			BufferedReader br = new BufferedReader(new InputStreamReader(s.getInputStream()));
			PrintWriter pw = new PrintWriter(s.getOutputStream());
			
			pw.println("Hello World!");
			pw.flush();
			String line = br.readLine();
			System.out.println("Server said: " + line);
			br.close();
			pw.close();
			s.close();
		}
		catch (IOException ioe)
		{
			System.out.println("ioe: " + ioe.getMessage());
		}
	}
	
	public static void main(String[] argv)
	{
		new NetworkingClient("localhost", 6789);
	}
}
