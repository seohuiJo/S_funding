package member.model.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import common.ConnectionFactory;
import member.model.dao.MemberDao;
import member.model.vo.Member;

public class MemberService {

	private ConnectionFactory factory;

	public MemberService() {
		factory = ConnectionFactory.getConnection();
	}

	public Member selectMember(String userId, String userPwd) {
		Connection conn = null;
		Member member = null;
		try {
			conn = factory.createConnection();
			member = new MemberDao().selectList(conn, userId, userPwd);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return member;

	}

	public Member selectMemberOne(String userId) {
		Connection conn = null;
		Member member = null;

		try {
			conn = factory.createConnection();
			member = new MemberDao().selectOne(conn, userId);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return member;

	}

	public ArrayList<Member> selectMemberList() {
		Connection conn = null;
		ArrayList<Member> list = null;

		try {
			conn = factory.createConnection();
			list = new MemberDao().selectMemberList(conn);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

	public int insertMember(Member member) {
		Connection conn = null;
		;
		int result = 0;

		try {
			conn = factory.createConnection();
			result = new MemberDao().insertMember(conn, member);
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

	public int deleteMember(String userId) {
		Connection conn = null;
		int result = 0;

		try {
			conn = factory.createConnection();
			result = new MemberDao().deleteMember(conn, userId);
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

	public int updateMember(Member member) {
		Connection conn = null;
		int result = 0;

		try {
			conn = factory.createConnection();
			result = new MemberDao().updateMember(conn, member);

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
