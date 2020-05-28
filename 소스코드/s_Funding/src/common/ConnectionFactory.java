package common;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import sun.security.jca.GetInstance.Instance;

public class ConnectionFactory {
	public ConnectionFactory() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public static ConnectionFactory instance;
	
	public static ConnectionFactory getConnection() {
			if(instance ==null) {
				instance = new ConnectionFactory();
			}
			return instance;
	}
	
	public Connection createConnection() throws SQLException {
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String user = "student";
		String password = "student";
		return DriverManager.getConnection(url,user,password);
		
		
	}
	
	public static void close(Connection conn) {
		try {
			if(conn!=null && !conn.isClosed()) {
				conn.close();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void commit(Connection conn) {
		
			try {
				if (conn!= null && !conn.isClosed()) {
				conn.commit();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	
	public void rollback(Connection conn) {
		
		try {
			if (conn!= null && !conn.isClosed()) {
			conn.rollback();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	

}
