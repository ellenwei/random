import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class NetworkingServer {
	
	public NetworkingServer(int port)
	{
		try
		{
			ServerSocket ss = new ServerSocket(port);
			System.out.println("Waiting for connection...");
			Socket s = ss.accept();
			System.out.println(s.getInetAddress()+ " connected.");
			BufferedReader br = new BufferedReader(new InputStreamReader(s.getInputStream()));
			PrintWriter pw = new PrintWriter(s.getOutputStream());
			
			String line = br.readLine();
			System.out.println("Client said: " + line);
			pw.println("Hello World");
			pw.flush();
			
			pw.close();
			br.close();
			s.close();
			ss.close();
		}
		catch (IOException ioe)
		{
			System.out.println("ioe: " + ioe.getMessage());
		}
	}
	public static void main(String[] argv)
	{
		new NetworkingServer(6789);
	}
}
