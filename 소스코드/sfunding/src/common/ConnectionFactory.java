package common;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ConnectionFactory {
	// 드라이버 로드
	public ConnectionFactory() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	// 싱글톤
	public static ConnectionFactory instance;
	
	public static ConnectionFactory getConnection() {
			if(instance ==null) {
				instance = new ConnectionFactory();
			}
			return instance;
	}
	
	// 커넥트
	public Connection createConnection() throws SQLException {
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String user = "sfunding";
		String password = "sfunding";
		return DriverManager.getConnection(url,user,password);
	}
	
	// connection close
	public void close(Connection conn) {
		try {
			if(conn!=null && !conn.isClosed()) {
				conn.close();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	// commit
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
	
	// rollback
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
	
	// stmt close
		public static void close(Statement stmt) {
			try {
				if (stmt != null && !stmt.isClosed()) {
					stmt.close();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		// rset close
		public static void close(ResultSet rset) {
			try {
				if (rset != null && !rset.isClosed()) {
					rset.close();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
}
