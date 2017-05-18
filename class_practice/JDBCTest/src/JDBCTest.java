import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBCTest {
	public JDBCTest()
	{
		Connection conn = null; 
		try
		{
			Class.forName("com.mysql.jbdc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost/StudentGrades?user=root&password=");
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery("SELECT studentID, fname, lname FROM Student;");
			while (rs.next())
			{
				int studentID = rs.getInt("studentID");
				String fname = rs.getString("fname");
				String lname = rs.getString("lname");
				System.out.println(studentID + ": " + fname + ": " + lname);
			}
			rs.close();
			st.close();
		}
		catch(ClassNotFoundException cnfe)
		{
			System.out.println("cnfe: " + cnfe.getMessage());
		}
		catch(SQLException sqle)
		{
			System.out.println("sqle: " + sqle.getMessage());
		}
		finally
		{
			try
			{
				if (conn != null)
				{
					conn.close();
				}
			}
			catch( SQLException ce)
			{
				System.out.println("ce: " + ce.getMessage());
			}
			
		}
		
	}
	public static void main(String[] argv)
	{
		JDBCTest test = new JDBCTest();
	}
}
