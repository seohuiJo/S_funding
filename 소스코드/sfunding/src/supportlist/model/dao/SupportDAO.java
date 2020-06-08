package supportlist.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import common.ConnectionFactory;
import supportlist.model.vo.SupportList;

public class SupportDAO {

	public ArrayList<SupportList> supportList(Connection conn, String userId, int currentPage, int recordcountPerPage) {
		ArrayList<SupportList> list = new ArrayList<SupportList>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = "SELECT * FROM (SELECT PROJECT_TITLE, C.USER_ID, START_DATE, END_DATE, PRICE, SUM_MONEY, TARGET_MONEY, ROW_NUMBER() OVER (ORDER BY PRICE desc)SP_NUM	FROM SUPPORTLIST A left JOIN PRODUCT B ON A.OPTION_NO = B.OPTION_NO AND A.PROJECT_NO=B.PROJECT_NO left JOIN PROJECT_BOARD C ON B.PROJECT_NO=C.PROJECT_NO WHERE A.USER_ID= ? ) WHERE SP_NUM BETWEEN ? AND ?";
		int start = currentPage * recordcountPerPage - (recordcountPerPage - 1);
		int end = currentPage * recordcountPerPage;

		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, userId);
			pstmt.setInt(2, start);
			pstmt.setInt(3, end);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				SupportList supportList = new SupportList();
				supportList.setProjectContent(rset.getString("PROJECT_TITLE"));
				supportList.setSelluserId(rset.getString("USER_ID"));
				supportList.setStartDate(rset.getDate("START_DATE"));
				supportList.setEndDate(rset.getDate("END_DATE"));
				supportList.setPrice(rset.getInt("PRICE"));
				supportList.setSumMoney(rset.getInt("SUM_MONEY"));
				supportList.setTargetMoney(rset.getInt("TARGET_MONEY"));
				list.add(supportList);
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

	public String getPageNavi(Connection conn, String userId, int currentPage, int recordcountPerPage,
			int naviCountPerPage) {
		int recordTotalCount = totalCount(conn, userId);
		int pageTotalCount = 0; // 전체 페이지 개수; 10개씩 만들면 13개;

		// 페이지 갯수 구하는 식
		if (recordTotalCount % recordcountPerPage > 0) {
			pageTotalCount = recordTotalCount / recordcountPerPage + 1;
		} else {
			pageTotalCount = recordTotalCount / recordcountPerPage;
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
					"<li class='page-item'><span class='page-link' style='color: black;'><a href='/SupportListServlet?currentPage="
							+ (startNavi - 1) + "'> &lt; </a></span></li>");
		}
		for (int i = startNavi; i <= endNavi; i++) {
			if (i == currentPage) {
				sb.append(
						"<li class='page-item'><span class='page-link' style='color: black;'><a href='/SupportListServlet?currentPage="
								+ i + "'><b>" + i + "</b></a></span></li>");

			} else {
				sb.append(
						"<li class='page-item'><span class='page-link' style='color: black;'><a href='/SupportListServlet?currentPage="
								+ i + "'>" + i + "</a></span></li>");
			}
		}
		if (needNext) {
			sb.append(
					"<li class='page-item'><span class='page-link' style='color: black;'><a href='/SupportListServlet?currentPage="
							+ (endNavi + 1) + "'> &gt; </a></span></li>");
		}
		sb.append("</ul></nav>");

		return sb.toString();
	}

	private int totalCount(Connection conn, String userId) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;

		int recordTotalCount = 0;
		String query = "SELECT COUNT(*) AS TOTALCOUNT\r\n" + "FROM SUPPORTLIST A\r\n" + "left JOIN PRODUCT B\r\n"
				+ "ON A.OPTION_NO = B.OPTION_NO \r\n" + "left JOIN PROJECT_BOARD C \r\n"
				+ "ON B.PROJECT_NO=C.PROJECT_NO\r\n" + "WHERE A.USER_ID=?";

		try {

			pstmt = conn.prepareStatement(query);
			pstmt.setNString(1, userId);
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

	// 베스트 후원자 
	public ArrayList<SupportList> supportbestlist(Connection conn) {
		ArrayList<SupportList> list = null;
		// TODO Auto-generated method stub
		Statement stmt = null;
		ResultSet rset = null;
		String query = "SELECT * FROM (SELECT USER_ID FROM SUPPORTLIST GROUP BY USER_ID ORDER BY COUNT(USER_ID) DESC)WHERE ROWNUM <= 3";
		try {
			stmt = conn.createStatement();
			rset = stmt.executeQuery(query);
			list = new ArrayList<SupportList>();
			while (rset.next()) {
				SupportList supportlist = new SupportList();
				supportlist.setUserId(rset.getString("user_Id"));
				list.add(supportlist);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			ConnectionFactory.close(stmt);
			ConnectionFactory.close(rset);
		}
		return list;
	}

}
