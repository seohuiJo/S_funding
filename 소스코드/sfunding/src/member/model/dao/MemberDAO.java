package member.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import common.ConnectionFactory;
import member.model.vo.Member;

public class MemberDAO {

	public ArrayList<Member> selectMemberList(Connection conn, int currentPage, int recordCountPerPage) {
		ArrayList<Member> list = null;
		PreparedStatement pstmt = null;
		// Statement stmt = null;
		ResultSet rset = null;
		String query = "SELECT * FROM(SELECT MEMBER.*, ROW_NUMBER() OVER(ORDER BY USER_ID DESC) AS NUM FROM MEMBER) WHERE NUM BETWEEN ? AND ?";

		int start = currentPage * recordCountPerPage - (recordCountPerPage - 1);
		int end = currentPage * recordCountPerPage;

		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, start);
			pstmt.setInt(2, end);
			rset = pstmt.executeQuery();
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
		} finally {
			ConnectionFactory.close(pstmt);
			ConnectionFactory.close(rset);
		}
		return list;
	}

	// 아이디 중복 체크 함수
	public int idCheck(Connection conn, String userId) {
		int result = 0;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		try {
			String query = "select * from member where user_id=?";
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, userId);
			rset = pstmt.executeQuery();
			if (rset.next()) {
				result = 1;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			ConnectionFactory.close(pstmt);
		}
		return result;
	}

	// 회원 추가
	public int insertMember(Connection conn, Member member) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "insert into member values(?,?,?,?,?,?,?,50000,1,sysdate,?)";

		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, member.getUserId());
			pstmt.setString(2, member.getUserPwd());
			pstmt.setString(3, member.getUserName());
			pstmt.setString(4, member.getPhone());
			pstmt.setString(5, member.getNickname());
			pstmt.setString(6, member.getAddress());
			pstmt.setString(7, member.getEmail());
			/*
			 * pstmt.setInt(8, member.getPoint()); pstmt.setInt(9, member.getEnabled());
			 */
			pstmt.setString(8, member.getInterest());
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			ConnectionFactory.close(pstmt);
		}
		return result;
	}

	public Member selectList(Connection conn, String userId, String userPwd) {
		Member member = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = "select * from member where user_id = ? and user_pwd =?";

		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, userId);
			pstmt.setString(2, userPwd);
			rset = pstmt.executeQuery();
			if (rset.next()) {
				member = new Member();
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
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			ConnectionFactory.close(pstmt);
			ConnectionFactory.close(rset);
		}
		return member;
	}

	// 아이디 보내서 회원 한명 정보 받아옴
	public Member selectOne(Connection conn, String userId) {
		Member member = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = "select * from member where user_id=?";

		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, userId);
			rset = pstmt.executeQuery();
			if (rset.next()) {
				member = new Member();
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
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			ConnectionFactory.close(pstmt);
			ConnectionFactory.close(rset);
		}
		return member;
	}

	public int deleteMember(Connection conn, String userId) {
		int result = 0;
		PreparedStatement pstmt = null;
		String query = "delete from member where user_id =?";

		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, userId);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			ConnectionFactory.close(pstmt);
		}
		return result;
	}

	public int updateMember(Connection conn, Member member) {
		int result = 0;
		PreparedStatement pstmt = null;
		String query = "update member set user_pwd =?, phone =?, address =?, email =?, nickname=? where user_id =?";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, member.getUserPwd());
			pstmt.setString(2, member.getPhone());
			pstmt.setString(3, member.getAddress());
			pstmt.setString(4, member.getEmail());
			pstmt.setString(5, member.getNickname());
			pstmt.setString(6, member.getUserId());

			result = pstmt.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			ConnectionFactory.close(pstmt);
		}
		return result;
	}

	public Member findId(Connection conn, String userName, String email) {
		Member member = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = "select user_id from member where user_name = ? and email= ?";

		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, userName);
			pstmt.setString(2, email);
			rset = pstmt.executeQuery();
			if (rset.next()) {
				member = new Member();
				member.setUserId(rset.getString("user_id"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			ConnectionFactory.close(pstmt);
			ConnectionFactory.close(rset);
		}
		return member;
	}

	public Member findPwd(Connection conn, String userId, String email, String phone) {
		Member member = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = "select user_pwd from member where user_id=? and email=? and phone=?";

		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, userId);
			pstmt.setString(2, email);
			pstmt.setString(3, phone);
			rset = pstmt.executeQuery();
			if (rset.next()) {
				member = new Member();

				member.setUserPwd(rset.getString("user_pwd"));
				// 보여주고 싶은 정보만 넣어주면 된다 비번 찾기니까 비번만 보여주면 된다

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			ConnectionFactory.close(pstmt);
			ConnectionFactory.close(rset);
		}
		return member;
	}

	public int totalCount(Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int recordTotalCount = 0;

		// 게시글 총 갯수를 알아오는 쿼리
		String query = "SELECT COUNT(*) AS TOTALCOUNT FROM MEMBER";

		try {
			pstmt = conn.prepareStatement(query);
			rset = pstmt.executeQuery();
			if (rset.next()) {
				recordTotalCount = rset.getInt("TOTALCOUNT");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			ConnectionFactory.close(pstmt);
			ConnectionFactory.close(rset);
		}

		return recordTotalCount;
	}

	public String getPageNavi(Connection conn, int currentPage, int recordCountPerPage, int naviCountPerPage) {
		int recordTotalCount = totalCount(conn);
		int pageTotalCount = 0; // 전체 페이지 개수; 10개씩 만들면 13개;

		// 페이지 갯수 구하는 식
		if (recordTotalCount % recordCountPerPage > 0) {
			pageTotalCount = recordTotalCount / recordCountPerPage + 1;
		} else {
			pageTotalCount = recordTotalCount / recordCountPerPage;
		}
		// 현재 페이지를 기준으로 네비게이션을 구해야하므로
		// 현재 페이지 정보를 확인해서 0보다는 크고 전체 페이지 수보다는 작은 위치에 있는지 확인
		// (오류방지용)
		if (currentPage < 1) {
			currentPage = 1;
		} else if (currentPage > pageTotalCount) {
			currentPage = pageTotalCount;
		}
		// 예)7을 눌렀을때 시작이 6이 되어야 한다.
		int startNavi = ((currentPage - 1) / naviCountPerPage) * naviCountPerPage + 1;

		int endNavi = startNavi + naviCountPerPage - 1;

		// 오류방지
		if (endNavi > pageTotalCount) {
			endNavi = pageTotalCount;
		}

		// '<' '>'모양
		boolean needPrev = true;
		boolean needNext = true;

		if (startNavi == 1) {
			needPrev = false;
		}
		if (endNavi == pageTotalCount) {
			needNext = false;
		}

		// 준비 끝
		StringBuilder sb = new StringBuilder();
		sb.append("<nav aria-label='Page navigation example'>");
		sb.append("<ul class='pagination justify-content-center'>");
		if (needPrev) {
			sb.append(
					"<li class='page-item'><span class='page-link' style='color: black;'><a href='/memberList?currentPage="
							+ (startNavi - 1) + "'> &lt; </a></span></li>");
		}
		for (int i = startNavi; i <= endNavi; i++) {
			if (i == currentPage) {
				sb.append(
						"<li class='page-item'><span class='page-link' style='color: black;'><a href='/memberList?currentPage="
								+ i + "'><b>" + i + "</b></a></span></li>");

			} else {
				sb.append(
						"<li class='page-item'><span class='page-link' style='color: black;'><a href='/memberList?currentPage="
								+ i + "'>" + i + "</a></span></li>");
			}
		}
		if (needNext) {
			sb.append(
					"<li class='page-item'><span class='page-link' style='color: black;'><a href='/memberList?currentPage="
							+ (endNavi + 1) + "'> &gt; </a></span></li>");
		}
		sb.append("</ul></nav>");

		return sb.toString();
	}

	public int chargePoint(Connection conn, String userId, int point) {
		PreparedStatement pstmt = null;
		int result = 0;

		String query = "update member set point = (point+?) where user_id= ?";

		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, point);
			pstmt.setString(2, userId);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			ConnectionFactory.close(pstmt);
		}

		return result;
	}

	public Member selectCharge(Connection conn, String userId) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Member member = null;
		String query = "SELECT * FROM MEMBER WHERE USER_ID = ?";

		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, userId);
			rset = pstmt.executeQuery();
			if (rset.next()) {
				member = new Member();
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
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			ConnectionFactory.close(pstmt);
			ConnectionFactory.close(rset);
		}
		return member;
	}

	public ArrayList<Member> MemberIdsearchList(Connection conn, int currentPage, int recordCountPerPage, String key) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<Member> list = new ArrayList<Member>();
		int start = currentPage * recordCountPerPage - (recordCountPerPage - 1);
		int end = currentPage * recordCountPerPage;
		String query = "SELECT * FROM (SELECT member.*, ROW_NUMBER() OVER(ORDER BY user_id DESC) AS NUM  from member WHERE user_id LIKE '%"
				+ key + "%') WHERE NUM BETWEEN ? AND ?";

		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, start);
			pstmt.setInt(2, end);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				Member member = new Member();
				member.setUserNo(rset.getInt("num"));
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
		} finally {
			ConnectionFactory.close(pstmt);
			ConnectionFactory.close(rset);
		}

		return list;
	}

	private int searchTotalCount(Connection conn, String search) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int recordTotalCount = 0;
		String query = "Select count(*) as totalcount from member where user_id like '%" + search + "%'";

		try {
			pstmt = conn.prepareStatement(query);
			rset = pstmt.executeQuery();
			if (rset.next()) {
				recordTotalCount = rset.getInt("totalCount");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			ConnectionFactory.close(pstmt);
			ConnectionFactory.close(rset);
		}

		return recordTotalCount;
	}

	public String getSearchPageNavi(Connection conn, int currentPage, int recordCountPerPage, int naviCountPage,
			String key) {
		int recordTotalCount = searchTotalCount(conn, key);
		int pageTotalCount = 0; // 전체 페이지 개수; 10개씩 만들면 13개;

		// 페이지 갯수 구하는 식
		if (recordTotalCount % recordCountPerPage > 0) {
			pageTotalCount = recordTotalCount / recordCountPerPage + 1;
		} else {
			pageTotalCount = recordTotalCount / recordCountPerPage;
		}
		// 현재 페이지를 기준으로 네비게이션을 구해야하므로
		// 현재 페이지 정보를 확인해서 0보다는 크고 전체 페이지 수보다는 작은 위치에 있는지 확인
		// (오류방지용)
		if (currentPage < 1) {
			currentPage = 1;
		} else if (currentPage > pageTotalCount) {
			currentPage = pageTotalCount;
		}
		// 예)7을 눌렀을때 시작이 6이 되어야 한다.
		int startNavi = ((currentPage - 1) / naviCountPage) * naviCountPage + 1;

		int endNavi = startNavi + naviCountPage - 1;

		// 오류방지
		if (endNavi > pageTotalCount) {
			endNavi = pageTotalCount;
		}

		// '<' '>'모양
		boolean needPrev = true;
		boolean needNext = true;

		if (startNavi == 1) {
			needPrev = false;
		}
		if (endNavi == pageTotalCount) {
			needNext = false;
		}

		// 준비 끝
		StringBuilder sb = new StringBuilder();
		sb.append("<nav aria-label='Page navigation example'>");
		sb.append("<ul class='pagination justify-content-center'>");
		if (needPrev) {
			sb.append(
					"<li class='page-item'><span class='page-link' style='color: black;'><a href='/searchMemberId?key="
							+ key + "&currentPage=" + (startNavi - 1) + "'> &lt; </a></span></li>");
		}
		for (int i = startNavi; i <= endNavi; i++) {
			if (i == currentPage) {
				sb.append(
						"<li class='page-item'><span class='page-link' style='color: black;'><a href='/searchMemberId?key="
								+ key + "&currentPage=" + i + "'><b>" + i + "</b></a></span></li>");

			} else {
				sb.append(
						"<li class='page-item'><span class='page-link' style='color: black;'><a href='/searchMemberId?key="
								+ key + "&currentPage=" + i + "'>" + i + "</a></span></li>");
			}
		}
		if (needNext) {
			sb.append(
					"<li class='page-item'><span class='page-link' style='color: black;'><a href='/searchMemberId?key="
							+ key + "&currentPage=" + (endNavi + 1) + "'> &gt; </a></span></li>");
		}
		sb.append("</ul></nav>");
		return sb.toString();
	}

}
