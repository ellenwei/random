import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Vector;

public class ChatServer {
	
	private Vector<ChatThread> ctVector;
	
	public ChatServer(int port) throws IOException
	{
		ctVector = new Vector<ChatThread>();
		try
		{
			ServerSocket ss = new ServerSocket(port);
			System.out.println("Bound to port " + port);
			while (true)
			{
				Socket s = ss.accept();
				System.out.println("Connected: " + s.getInetAddress());
				//ChatThread ct = new ChatThread(s, this);
				//ctVector.add(ct);
				//ct.start();
				PrintWriter pw = null;
				try {
					pw = new PrintWriter(s.getOutputStream());
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				BufferedReader br = null;
				pw.println("HTTP/1.0 200 OK");
				pw.println("Content-Type: text/html");
				pw.println("Server: Bot");
				pw.println();
				pw.println("<!DOCTYPE html>");
				
				try {
					String line = null;
					br = new BufferedReader(new FileReader("scores.txt"));
					pw.write("<html><body>");
					pw.println("<h style = \"font-size:40px;\">Sorry!Top Score List</h>");
					pw.println("<table style=\"border: 1px solid black;\">");
					while ((line = br.readLine()) != null) {
						String[] splited = line.split("\\s+");
						pw.println("<tr>");
						pw.println("<td>");
						pw.println("<h>"+splited[0]+"</h>");
						pw.println("</td>");
						//pw.println("\n");
						pw.println("<td>");
						pw.println("<h>"+splited[1]+"</h>");
						pw.println("</td>");
						pw.println("</tr>");
					}		
					pw.write("</table></body></html>");
					pw.flush();
				} finally {
					try {
						if (br != null)
							br.close();
					} catch (IOException ex) {
						ex.printStackTrace();
					}
				}
				pw.close();
			}
		}
		catch(IOException ioe)
		{
			System.out.println("ioe in char server constructor:"+ ioe.getMessage());
		}
	}
	public void sendMessageToAllClients( String message, ChatThread sendingMessage)
	{
		for (ChatThread ct : ctVector)
		{
			if (sendingMessage != ct)
			{
				ct.sendMessage(message);
			}
		}
	}
	public static void main (String[] argv)
	{
		try {
			new ChatServer(2002);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
