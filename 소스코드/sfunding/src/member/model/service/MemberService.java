package member.model.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import common.ConnectionFactory;
import member.model.dao.MemberDAO;
import member.model.vo.Member;
import member.model.vo.MemberPageData;

public class MemberService {

	private ConnectionFactory factory;

	public MemberService() {
		factory = ConnectionFactory.getConnection();
	}

	// 아이디 중복 체크 함수
	public int idCheck(String userId) {
		Connection conn = null;
		int result = 0;

		try {
			conn = factory.createConnection();
			result = new MemberDAO().idCheck(conn, userId);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			factory.close(conn);
		}
		return result;

	}

	public int insertMember(Member member) {
		Connection conn = null;

		int result = 0;

		try {
			conn = factory.createConnection();
			result = new MemberDAO().insertMember(conn, member);
			if (result > 0) {
				factory.commit(conn);

			} else {
				factory.rollback(conn);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			factory.close(conn);
		}

		return result;

	}

	public Member selectMember(String userId, String userPwd) {
		Connection conn = null;
		Member member = null;
		try {
			conn = factory.createConnection();
			member = new MemberDAO().selectList(conn, userId, userPwd);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			factory.close(conn);
		}
		return member;

	}

	public Member selectMemberOne(String userId) {
		Connection conn = null;
		Member member = null;

		try {
			conn = factory.createConnection();
			member = new MemberDAO().selectOne(conn, userId);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			factory.close(conn);
		}
		return member;

	}

	public int deleteMember(String userId) {
		Connection conn = null;
		int result = 0;

		try {
			conn = factory.createConnection();
			result = new MemberDAO().deleteMember(conn, userId);
			if (result > 0) {
				factory.commit(conn);
			} else {
				factory.rollback(conn);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			factory.close(conn);
		}
		return result;
	}

	public int updateMember(Member member) {
		Connection conn = null;
		int result = 0;

		try {
			conn = factory.createConnection();
			result = new MemberDAO().updateMember(conn, member);
			if (result > 0) {
				factory.commit(conn);
			} else {
				factory.rollback(conn);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			factory.close(conn);
		}
		return result;

	}

	public Member findId(String userName, String email) {
		Connection conn = null;
		Member member = null;

		try {
			conn = factory.createConnection();
			member = new MemberDAO().findId(conn, userName, email);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			factory.close(conn);
		}
		return member;

	}

	public Member findPwd(String userId, String email, String phone) {
		Connection conn = null;
		Member member = null;

		try {
			conn = factory.createConnection();
			member = new MemberDAO().findPwd(conn, userId, email, phone);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			factory.close(conn);
		}
		return member;

	}

	public MemberPageData selectMemberList(int currentPage) {
		// SELECT * FROM MEMBER;
		Connection conn = null;
		int recordCountPerPage = 10;
		int naviCountPerPage = 5;
		MemberPageData pd = new MemberPageData();
		ArrayList<Member> memberList = null;

		try {
			conn = factory.createConnection();
			memberList = new MemberDAO().selectMemberList(conn, currentPage, recordCountPerPage);
			pd.setPageList(memberList);
			pd.setPageNavi(new MemberDAO().getPageNavi(conn, currentPage, recordCountPerPage, naviCountPerPage));
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			factory.close(conn);
		}

		return pd;

	}

	public int chargePoint(String userId, int point) {
		Connection conn = null;
		int result = 0;

		try {
			conn = factory.createConnection();
			result = new MemberDAO().chargePoint(conn, userId, point);
			if (result > 0) {
				factory.commit(conn);

			} else {
				factory.rollback(conn);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			factory.close(conn);
		}
		return result;
	}

	public Member selectCharge(String userId) {
		Connection conn = null;
		Member member = null;

		try {
			conn = factory.createConnection();
			member = new MemberDAO().selectCharge(conn, userId);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			factory.close(conn);
		}
		return member;
	}

	public MemberPageData MemberIdsearchList(int currentPage, String key) {
		Connection conn = null;
		int recordCountPerPage = 10;
		int naviCountPage = 5;
		MemberPageData pd = new MemberPageData();
		try {
			conn = factory.createConnection();
			pd.setPageList(new MemberDAO().MemberIdsearchList(conn, currentPage, recordCountPerPage, key));
			pd.setPageNavi(
					new MemberDAO().getSearchPageNavi(conn, currentPage, recordCountPerPage, naviCountPage, key));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			factory.close(conn);
		}
		return pd;
	}

}
