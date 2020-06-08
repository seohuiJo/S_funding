package notice.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import common.ConnectionFactory;
import notice.model.vo.Notice;

public class NoticeDAO {

	public Notice noticeSelect(Connection conn, int noticeNo) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Notice notice = null;
		String query = "SELECT * FROM NOTICE WHERE NOTICE_NO = ?";

		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, noticeNo);
			rset = pstmt.executeQuery();
			if (rset.next()) {
				notice = new Notice();
				notice.setNoticeNo(rset.getInt("NOTICE_NO"));
				notice.setUserId(rset.getString("USER_ID"));
				notice.setUserName(rset.getString("USER_NAME"));
				notice.setNoticeContent(rset.getString("NOTICE_CONTENT"));
				notice.setNoticeTitle(rset.getString("NOTICE_TITLE"));
				notice.setnRegdate(rset.getDate("N_REGDATE"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			ConnectionFactory.close(pstmt);
			ConnectionFactory.close(rset);
		}
		return notice;
	}

	public ArrayList<Notice> noticeList(Connection conn, int currentPage, int recordCountPerPage) {
		ArrayList<Notice> list = new ArrayList<Notice>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;

		String query = "select * from(select notice.*, row_number()over(order by notice_no desc) as num from notice) where num between ? and ?";
		int start = currentPage * recordCountPerPage - (recordCountPerPage - 1);
		int end = currentPage * recordCountPerPage;

		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, start);
			pstmt.setInt(2, end);
			rset = pstmt.executeQuery();
			// noticeList = new ArrayList<>();
			while (rset.next()) {
				Notice notice = new Notice();
				notice.setNoticeNo(rset.getInt("NOTICE_NO"));
				notice.setUserId(rset.getString("USER_ID"));
				notice.setUserName(rset.getString("USER_NAME"));
				notice.setNoticeContent(rset.getString("NOTICE_CONTENT"));
				notice.setNoticeTitle(rset.getString("NOTICE_TITLE"));
				notice.setnRegdate(rset.getDate("N_REGDATE"));
				list.add(notice);
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

	public int totalCount(Connection conn) { // 전체 갯수를 구하는 메소드
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int recordTotalCount = 0;
		String query = "SELECT COUNT(*) AS TOTALCOUNT FROM NOTICE";

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
					"<li class='page-item'><span class='page-link' style='color: black;'><a href='/NoticeServlet?currentPage="
							+ (startNavi - 1) + "'> &lt; </a></span></li>");
		}
		for (int i = startNavi; i <= endNavi; i++) {
			if (i == currentPage) {
				sb.append(
						"<li class='page-item'><span class='page-link' style='color: black;'><a href='/NoticeServlet?currentPage="
								+ i + "'><b>" + i + "</b></a></span></li>");

			} else {
				sb.append(
						"<li class='page-item'><span class='page-link' style='color: black;'><a href='/NoticeServlet?currentPage="
								+ i + "'>" + i + "</a></span></li>");
			}
		}
		if (needNext) {
			sb.append(
					"<li class='page-item'><span class='page-link' style='color: black;'><a href='/NoticeServlet?currentPage="
							+ (endNavi + 1) + "'> &gt; </a></span></li>");
		}
		sb.append("</ul></nav>");

		return sb.toString();
	}

	public int insertNotice(Connection conn, String title, String content, String userId, String userName) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "INSERT INTO NOTICE VALUES(NOTICE_SEQ.NEXTVAL, ?,?,?,?,sysdate)";

		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, userId);
			pstmt.setString(2, userName);
			pstmt.setString(3, title);
			pstmt.setString(4, content);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			ConnectionFactory.close(pstmt);
		}
		return result;
	}

	public int modifyNotice(Connection conn, String title, String content, int noticeNo) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "update notice set NOTICE_TITLE  = ? , NOTICE_CONTENT = ? where notice_no = ?";

		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, title);
			pstmt.setString(2, content);
			pstmt.setInt(3, noticeNo);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			ConnectionFactory.close(pstmt);
		}

		return result;
	}

	public int deleteNotice(Connection conn, int noticeNo) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "delete from notice where notice_no = ?";

		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, noticeNo);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			ConnectionFactory.close(pstmt);
		}

		return result;
	}

	public ArrayList<Notice> searchList(Connection conn, int currentPage, int recordCountPerPage, String search) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<Notice> list = new ArrayList<Notice>();

		int start = currentPage * recordCountPerPage - (recordCountPerPage - 1);
		int end = currentPage * recordCountPerPage;
		String query = "select * from(select notice.*, row_number()over(order by notice_no desc)as num from notice where notice_title like'%"
				+ search + "%') where num between ? and ?";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, start);
			pstmt.setInt(2, end);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				Notice notice = new Notice();
				notice.setNoticeNo(rset.getInt("NOTICE_NO"));
				notice.setUserId(rset.getString("USER_ID"));
				notice.setUserName(rset.getString("USER_NAME"));
				notice.setNoticeContent(rset.getString("NOTICE_CONTENT"));
				notice.setNoticeTitle(rset.getString("NOTICE_TITLE"));
				notice.setnRegdate(rset.getDate("N_REGDATE"));
				list.add(notice);
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

	public String getSearchPageNavi(Connection conn, int currentPage, int recordCountPerPage, int naviCountPerPage,
			String search) {
		int recordTotalCount = searchTotalCount(conn, search);

		int pageTotalCount = 0;
		if (recordTotalCount % recordCountPerPage > 0) {
			pageTotalCount = recordTotalCount / recordCountPerPage + 1;
		} else {
			pageTotalCount = recordTotalCount / recordCountPerPage;

		}
		if (currentPage < 1) {
			currentPage = 1;
		} else if (currentPage > pageTotalCount) {
			currentPage = pageTotalCount;
		}
		int startNavi = ((currentPage - 1) / naviCountPerPage) * naviCountPerPage + 1;
		int endNavi = startNavi + naviCountPerPage - 1;

		// 오류 방지
		if (endNavi > pageTotalCount) {
			endNavi = pageTotalCount;
		}

		boolean needPrev = true;
		boolean needNext = true;

		if (startNavi == 1) {
			needPrev = false;
		}
		if (endNavi == pageTotalCount) {
			needNext = false;
		}

		StringBuilder sb = new StringBuilder();
		sb.append("<nav aria-label='Page navigation example'>");
		sb.append("<ul class='pagination justify-content-center'>");
		if (needPrev) {
			sb.append("<li class='page-item'><span class='page-link' style='color: black;'><a href='/search?search="
					+ search + "&currentPage=" + (startNavi - 1) + "'> &lt; </a></span></li>");
		}
		for (int i = startNavi; i <= endNavi; i++) {
			if (i == currentPage) {
				sb.append("<li class='page-item'><span class='page-link' style='color: black;'><a href='/search?search="
						+ search + "&currentPage=" + i + "'><b>" + i + "</b></a></span></li>");

			} else {
				sb.append("<li class='page-item'><span class='page-link' style='color: black;'><a href='/search?search="
						+ search + "&currentPage=" + i + "'>" + i + "</a></span></li>");
			}
		}
		if (needNext) {
			sb.append("<li class='page-item'><span class='page-link' style='color: black;'><a href='/search?search="
					+ search + "&currentPage=" + (endNavi + 1) + "'> &gt; </a></span></li>");
		}
		sb.append("</ul></nav>");

		return sb.toString();
	}

	private int searchTotalCount(Connection conn, String search) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int recordTotalCount = 0;
		String query = "Select count(*) as totalcount from notice where notice_title like '%" + search + "%'";

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

}
