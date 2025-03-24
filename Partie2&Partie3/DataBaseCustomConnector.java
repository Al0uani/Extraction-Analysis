import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
public class DataBaseCustomConnector {
	public String Url;
	public String User;
	public String Password;
	public Connection con;
	public DataBaseCustomConnector() {
		this.Url = "";this.User = ""; this.Password ="";this.con = null;
	}
	public DataBaseCustomConnector(String Url,String User, String Password) {
		this.Url = Url; this.User = User; this.Password = Password;this.con = null;
	}
	
	private Connection ConnectManual() {
		try {
            return DriverManager.getConnection(this.Url, this.User, this.Password);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
	}
	
	public void Connect() {
		Connection co = ConnectManual();
		if(co != null) {this.con=co; System.out.println("DataBase Connected");}
		else {System.out.println("DataBase Not connected");}
	}
	
	public void Disconnect() {
		try {
			this.con.close();
			this.con = null;
			System.out.println("DataBase Disconnected");
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	public void SimpleQuery(String Query) {
		if(this.con != null) {
			try(Statement stmt = this.con.createStatement()){
				stmt.executeUpdate(Query);
				System.out.println("Query was Executed sucessfully\n");
			} catch (SQLException e) {
				e.printStackTrace();
			}	
		}
		else {throw new ArithmeticException("\nDataBase Not Connected - Please Connect()\n");}
	}
	public ResultSet SimpleQueryWithResult(String Query) {
	    if (this.con != null) {
	        try {
	            Statement stmt = this.con.createStatement();
	            ResultSet res = stmt.executeQuery(Query);
	            System.out.println("Query was executed successfully\n");
	            return res;  
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }    
	    } else {
	        throw new ArithmeticException("\nDatabase not connected - Please connect()\n");
	    }
	    return null; 
	}


	public void PreparedQuery(String Query, Object... parameters) {
	    if (this.con != null) {
	        try (PreparedStatement prepstmt = this.con.prepareStatement(Query)) {
	            for (int i = 0; i < parameters.length; i++) {
	                prepstmt.setObject(i + 1, parameters[i]); // Fix: Start from index 1
	            }
	            prepstmt.executeUpdate();
	           
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    } else {
	        throw new ArithmeticException("\nDataBase Not Connected - Please Connect()\n");
	    }
	}

	






}//this is for BaseCustomConnector
