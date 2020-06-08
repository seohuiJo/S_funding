package blacklist.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import blacklist.model.vo.BlackList;
import common.ConnectionFactory;

public class BlacklistDAO {

	// 블랙리스트 전체 가져오기
	public ArrayList<BlackList> selectBlackList(Connection conn, int currentPage, int recordCountPerPage) {
		// Statement stmt=null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<BlackList> nList = new ArrayList<BlackList>();

		String query = "SELECT * FROM(SELECT BLACKLIST.*, ROW_NUMBER() OVER(ORDER BY B_REGDATE DESC) AS NUM FROM BLACKLIST) WHERE NUM BETWEEN ? AND ?";

		int start = currentPage * recordCountPerPage - (recordCountPerPage - 1);
		int end = currentPage * recordCountPerPage;
		try {
			// stmt=conn.createStatement();
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, start);
			pstmt.setInt(2, end);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				BlackList blacklist = new BlackList();
				blacklist.setUserId(rset.getString("USER_ID"));
				blacklist.setReason(rset.getString("REASON"));
				blacklist.setbRegdate(rset.getDate("B_REGDATE"));
				nList.add(blacklist);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			ConnectionFactory.close(pstmt);
			ConnectionFactory.close(rset);
		}
		return nList;
	}

	// BLACKLIST 총 수
	public int totalCount(Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int recordTotalCount = 0;

		// 게시글 총 갯수를 알아오는 쿼리
		String query = "SELECT COUNT(*) AS TOTALCOUNT FROM BLACKLIST";

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

	// getPageNAVI
	public String getPageNavi(Connection conn, int currentPage, int recordCountPerPage, int naviCountPerPage) {
		int recordTotalCount = totalCount(conn);
		int pageTotalCount = 0; // 전체 페이지의 갯수
		// 전체 게시물 갯수 124개, 10개씩 페이지 만들면 페이지 갯수는 13개

		if (recordTotalCount % recordCountPerPage > 0) {
			pageTotalCount = recordTotalCount / recordCountPerPage + 1;
		} else {
			pageTotalCount = recordTotalCount / recordCountPerPage;
		}

		// 현재 페이지를 기준으로 네비게이션을 구해야 하므로
		// 현재 페이지 정보를 확인해서 0보다는 크고 전체 페이지 수보다는 작은 위치에 있는지 확인(오류방지)
		if (currentPage < 1) {
			currentPage = 1;
		} else if (currentPage > pageTotalCount) {
			currentPage = pageTotalCount;
		}

		int startNavi = ((currentPage - 1) / naviCountPerPage) * naviCountPerPage + 1;
		// currentPage=8, naviCountPerPage=5
		// ((8-1)/5)*5+1 => 6
		// currentPage=42, naviCountPerPage=5
		// 41 42 43 44 45
		// ((42-1)/5)*5+1 => 41
		int endNavi = startNavi + naviCountPerPage - 1;

		if (endNavi > pageTotalCount) {
			endNavi = pageTotalCount;
		}

		// '<' 모양과 '>' 모양을 준비하기 위해 필요한 변수
		boolean needPrev = true;
		boolean needNext = true;
		if (startNavi == 1) {
			needPrev = false;
		}
		if (endNavi == pageTotalCount) {
			needNext = false;
		}

		// 모든 준비 끝남
		StringBuilder sb = new StringBuilder();
		sb.append("<nav aria-label='Page navigation example'>");
		sb.append("<ul class='pagination justify-content-center'>");
		if (needPrev) {
			sb.append("<li class='page-item'><span class='page-link' style='color: black;'><a href='/blacklist?currentPage=" + (startNavi - 1) + "'>&lt;</a></span></li>");
		}
		for (int i = startNavi; i <= endNavi; i++) {
			if (i == currentPage) {
				sb.append("<li class='page-item'><span class='page-link' style='color: black;'><a href='/blacklist?currentPage=" + i + "'><b>" + i + "</b></a></span></li>");
			} else {
				sb.append("<li class='page-item'><span class='page-link' style='color: black;'><a href='/blacklist?currentPage=" + i + "'>" + i + "</a></span></li>");
			}
		}

		if (needNext) {
			sb.append("<li class='page-item'><span class='page-link' style='color: black;'><a href='blacklist?currentPage=" + (endNavi + 1) + "'>&gt;</a></span></li>");
		}
		sb.append("</ul></nav>");
		return sb.toString();
	}

	// searchList 가져오기
	public ArrayList<BlackList> blacklistSearchList(Connection conn, int currentPage, int recordCountPerPage,
			String search) {
		ArrayList<BlackList> list = new ArrayList<BlackList>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;

		int start = currentPage * recordCountPerPage - (recordCountPerPage - 1);
		int end = currentPage * recordCountPerPage;

		String query = "SELECT * FROM (SELECT BLACKLIST.*, ROW_NUMBER() OVER(ORDER BY B_REGDATE DESC) AS NUM FROM BLACKLIST WHERE USER_ID LIKE '%"
				+ search + "%')" + "WHERE NUM BETWEEN ? AND ?";

		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, start);
			pstmt.setInt(2, end);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				BlackList blacklist = new BlackList();
				blacklist.setUserId(rset.getString("USER_ID"));
				blacklist.setReason(rset.getString("REASON"));
				blacklist.setbRegdate(rset.getDate("B_REGDATE"));
				list.add(blacklist);
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

	// search한 회원 세기
	public int searchTotalCount(Connection conn, String search) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int recordTotalCount = 0;
		String query = "SELECT COUNT(*) AS TOTALCOUNT FROM BLACKLIST WHERE USER_ID LIKE '%" + search + "%'";
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

	// SearchPageNavi 가져오기
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
			sb.append("<li class='page-item'><span class='page-link' style='color: black;'><a href='/blacklistSearch?search=" + search + "&currentPage=" + (startNavi - 1) + "'>&lt;</a></span></li>");
		}
		for (int i = startNavi; i <= endNavi; i++) {
			if (i == currentPage) {
				sb.append(
						"<li class='page-item'><span class='page-link' style='color: black;'><a href='/blacklistSearch?search=" + search + "&currentPage=" + i + "'><b>" + i + "</b></a></span></li>");
			} else {
				sb.append("<li class='page-item'><span class='page-link' style='color: black;'><a href='/blacklistSearch?search=" + search + "&currentPage=" + i + "'>" + i + "</a></span></li>");
			}
		}

		if (needNext) {
			sb.append("<li class='page-item'><span class='page-link' style='color: black;'><a href='/blacklistSearch?search=" + search + "&currentPage=" + (endNavi + 1) + "'>&gt;</a></span></li>");
		}
		sb.append("</ul></nav>");
		return sb.toString();
	}

	// 블랙리스트 삭제
	public int deleteBlacklist(Connection conn, String userId) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "DELETE FROM BLACKLIST WHERE USER_ID=?";
		String query2 = "UPDATE MEMBER SET ENABLED=1 WHERE USER_ID=?";

		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, userId);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			pstmt = conn.prepareStatement(query2);
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

	public int addBlacklist(Connection conn, String userId, String reason) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "insert into blacklist values(?,?,sysdate)";
		String query2 = "UPDATE MEMBER SET ENABLED=0 WHERE USER_ID=?";

		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, userId);
			pstmt.setNString(2, reason);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			pstmt = conn.prepareStatement(query2);
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
}
