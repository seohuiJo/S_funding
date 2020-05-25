package supportlist.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import member.model.vo.Member;
import supportlist.model.vo.SupportList;

public class SupportDAO {
	public ArrayList<SupportList>selectSupportList(Connection conn) {
		ArrayList<SupportList> list = null;
		Statement stmt = null;
		ResultSet rset = null;
		String query = "SELECT * FROM SUPPORTLIST";

		try {
			stmt = conn.createStatement();
			rset = stmt.executeQuery(query);
			list = new ArrayList<SupportList>();
			while (rset.next()) {
				SupportList member = new SupportList();
				
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		  return list;
	}
	public SupportList selectOne(Connection conn, String userId) {
		SupportList member = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = "SELECT * FROM MEMBER WHERE MEMBER_ID=?";

		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, userId);
			rset = pstmt.executeQuery();
			if (rset.next()) {
				member = new SupportList();
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
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return member;
	}

}
