package models;
import java.sql.*;
public class ConnectDB {
	

	 public static Connection getConnection() {
	 Connection connection = null;
	 try {
	 Class.forName("com.mysql.cj.jdbc.Driver");
	 connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/bookstoreproject?serverTimezone=Asia/Ho_Chi_Minh", "root", "");
	 } catch (Exception e) {
	 System.err.println(e.getMessage());
	 connection = null;
	 }
	 return connection;
	 }
	
}
