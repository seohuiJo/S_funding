package member.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import member.model.vo.Member;

public class MemberDAO {
	
	// 전체 멤버 가져오기
	public ArrayList<Member> selectMemberList(Connection conn) {
		  ArrayList<Member> list = null;
	      Statement stmt = null;
	      ResultSet rset = null;
	      String query = "select * from member";
	      
	      try {
	         stmt=conn.createStatement();
	         rset = stmt.executeQuery(query);
	         list = new ArrayList<Member>();
	          while(rset.next()) {
	                  Member member = new Member();
	               member.setUserId(rset.getString("user_id"));
	               list.add(member);
	          }

	      } catch (SQLException e) {
	         // TODO Auto-generated catch block
	         e.printStackTrace();
	      }
	      
	      return list;
	}

}
