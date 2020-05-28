package member.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import member.model.vo.Member;

public class MemberDAO {

	
	public ArrayList<Member> selectMemberList(Connection conn) {
		ArrayList<Member> list = null;
		Statement stmt = null;
		ResultSet rset = null;
		String query = "select * from member";

		try {
			stmt = conn.createStatement();
			rset = stmt.executeQuery(query);
			list = new ArrayList<Member>();
			while (rset.next()) {
				Member member = new Member();
				member.setUserId(rset.getString("user_id"));
				member.setUserPwd(rset.getString("user_pwd"));
				member.setUserName(rset.getString("user_name"));
				member.setPhone(rset.getString("phone"));
				member.setNickname(rset.getString("nickname"));
				member.setAddress(rset.getString("address"));
				member.setEmail(rset.getString("email"));
				member.setPoint(rset.getInt("point"));
				member.setEnabled(rset.getInt("enabled"));
				member.setuRegdate(rset.getDate("u_regdate"));
				member.setInterest(rset.getString("interest"));

				list.add(member);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return list;
	}
	
	public int insertMember(Connection conn, Member member) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int result = 0;
		String query ="insert into member values(?,?,?,?,?,?,?,50000,1,sysdate,?)";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, member.getUserId());
			pstmt.setString(2, member.getUserPwd());
			pstmt.setString(3, member.getUserName());
			pstmt.setString(4, member.getPhone());
			pstmt.setString(5, member.getNickname());
			pstmt.setString(6, member.getAddress());
			pstmt.setString(7, member.getEmail());
			/*pstmt.setInt(8, member.getPoint());
			pstmt.setInt(9, member.getEnabled());*/
			pstmt.setString(8, member.getInterest());
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
	         try {
	             pstmt.close();
	          } catch (SQLException e) {
	             // TODO Auto-generated catch block
	             e.printStackTrace();
	          }
	       }return result;
		
		
		
		
		
		
	}

}
