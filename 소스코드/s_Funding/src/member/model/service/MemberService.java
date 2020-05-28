package member.model.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import common.ConnectionFactory;
import member.model.dao.MemberDAO;
import member.model.vo.Member;

public class MemberService {

	private ConnectionFactory factory;
	
	public MemberService() {
		factory = ConnectionFactory.getConnection();
	}
	
	public ArrayList<Member> selectMemberList() {
	      Connection conn = null;
	      ArrayList<Member> list = null;

	      try {
	         conn = factory.createConnection();
	         list = new MemberDAO().selectMemberList(conn);
	      } catch (SQLException e) {
	         // TODO Auto-generated catch block
	         e.printStackTrace();
	      }
	      return list;
	   }
	
	public int insertMember(Member member) {
		Connection conn = null;
		
		int result = 0;

		try {
			conn = factory.createConnection();
			result = new MemberDAO().insertMember(conn, member);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if (result > 0) {
			factory.commit(conn);

		} else {
			factory.rollback(conn);

		}
		return result;

	}

}
