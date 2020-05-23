package product.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import member.model.vo.Member;
import notice.model.dao.NoticeDAO;
import product.model.vo.Product;

public class ProductDAO {
	public ArrayList<Product>selectProductList(Connection conn) {
		ArrayList<Product> list = null;
		Statement stmt = null;
		ResultSet rset = null;
		String query = "SELECT * FROM MEMBER";

		try {
			stmt = conn.createStatement();
			rset = stmt.executeQuery(query);
			list = new ArrayList<Product>();
			while (rset.next()) {
				Member member = new Member();
				member.setUserId(rset.getString("MEMBER_ID"));
				member.setUserPwd(rset.getString("MEMBER_PWD"));
				member.setUserName(rset.getString("MEMBER_NAME"));
				member.setAge(rset.getInt("AGE"));
				member.setEmail(rset.getString("EMAIL"));
				member.setPhone(rset.getString("PHONE"));
				member.setAddress(rset.getString("ADDRESS"));
				member.setGender(rset.getString("GENDER"));
				member.setHobby(rset.getString("HOBBY"));
				member.setEnrollDate(rset.getDate("ENROLL_DATE"));
				list.add(member);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		  return list;
	}
	public int modifyProduct(Connection conn) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "UPDATE NOTICE SET SUBJECT=?,CONTENTS=?," + "REGDATE=SYSDATE WHERE NOTICENO=?";

		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, subject);
			pstmt.setString(2, content);
			pstmt.setInt(3, noticeNo);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				pstmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return result;
	}
	public int deleteProduct(int noticeNo) {
		Connection conn=null;
		int result=0;
		try {
			conn=factory.createConnection();
			result=new NoticeDAO().deleteNotice(conn,noticeNo);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(result>0) {
			factory.commit(conn);
		}else {
			factory.rollback(conn);
			
		}
		return result;
	}

}
