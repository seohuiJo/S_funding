package serviceboard.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import common.ConnectionFactory;
import serviceboard.model.vo.ServiceBoard;
import serviceboard.model.vo.ServiceComment;

public class ServiceDAO {

	// 고객센터 전체 가져오기
	public ArrayList<ServiceBoard> selectServiceList(Connection conn, int currentPage, int recordCountPerPage) {
		// Statement stmt=null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<ServiceBoard> nList = new ArrayList<ServiceBoard>();

		String query = "SELECT * FROM(SELECT SERVICE_BOARD.*, ROW_NUMBER() OVER(ORDER BY SERVICE_NO DESC) AS NUM FROM SERVICE_BOARD) WHERE NUM BETWEEN ? AND ?";

		int start = currentPage * recordCountPerPage - (recordCountPerPage - 1);
		int end = currentPage * recordCountPerPage;
		try {
			// stmt=conn.createStatement();
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, start);
			pstmt.setInt(2, end);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				ServiceBoard service = new ServiceBoard();
				service.setServiceNo(rset.getInt("SERVICE_NO"));
				service.setServiceContent(rset.getString("SERVICE_CONTENT"));
				service.setServiceCategory(rset.getString("SERVICE_CATEGORY"));
				service.setUserId(rset.getString("USER_ID"));
				service.setsRegdate(rset.getDate("S_REGDATE"));
				nList.add(service);
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

	// 고객센터 자세히 보기
	public ServiceBoard serviceSelect(Connection conn, int serviceNo) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ServiceBoard service = null;
		String query = "SELECT * FROM SERVICE_BOARD WHERE SERVICE_NO=?";

		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, serviceNo);
			rset = pstmt.executeQuery();

			if (rset.next()) {
				service = new ServiceBoard();
				service.setServiceNo(rset.getInt("SERVICE_NO"));
				service.setServiceContent(rset.getString("SERVICE_CONTENT"));
				service.setServiceCategory(rset.getString("SERVICE_CATEGORY"));
				service.setUserId(rset.getString("USER_ID"));
				service.setsRegdate(rset.getDate("S_REGDATE"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			ConnectionFactory.close(pstmt);
			ConnectionFactory.close(rset);
		}

		return service;
	}

	// searchList 가져오기
	public ArrayList<ServiceBoard> serviceSearchList(Connection conn, int currentPage, int recordCountPerPage,
			String search) {
		ArrayList<ServiceBoard> list = new ArrayList<ServiceBoard>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;

		int start = currentPage * recordCountPerPage - (recordCountPerPage - 1);
		int end = currentPage * recordCountPerPage;

		String query = "SELECT * FROM (SELECT SERVICE_BOARD.*, ROW_NUMBER() OVER(ORDER BY SERVICE_NO DESC) AS NUM FROM SERVICE_BOARD WHERE SERVICE_CONTENT LIKE '%"
				+ search + "%')" + "WHERE NUM BETWEEN ? AND ?";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, start);
			pstmt.setInt(2, end);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				ServiceBoard service = new ServiceBoard();
				service.setServiceNo(rset.getInt("SERVICE_NO"));
				service.setUserId(rset.getString("USER_ID"));
				service.setServiceContent(rset.getString("SERVICE_CONTENT"));
				service.setServiceCategory(rset.getString("SERVICE_CATEGORY"));
				service.setsRegdate(rset.getDate("S_REGDATE"));
				list.add(service);
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

	// search한 게시글 갯수 세기
	public int searchTotalCount(Connection conn, String search) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int recordTotalCount = 0;
		String query = "SELECT COUNT(*) AS TOTALCOUNT FROM SERVICE_BOARD WHERE SERVICE_CATEGORY LIKE '%" + search
				+ "%'";
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
			sb.append(
					"<li class='page-item'><span class='page-link' style='color: black;'><a href='/serviceSearch?search="
							+ search + "&currentPage=" + (startNavi - 1) + "'>&lt;</a></span></li>");
		}
		for (int i = startNavi; i <= endNavi; i++) {
			if (i == currentPage) {
				sb.append(
						"<li class='page-item'><span class='page-link' style='color: black;'><a href='/serviceSearch?search="
								+ search + "&currentPage=" + i + "'><b>" + i + "</b></a></span></li>");
			} else {
				sb.append(
						"<li class='page-item'><span class='page-link' style='color: black;'><a href='/serviceSearch?search="
								+ search + "&currentPage=" + i + "'>" + i + "</a></span></li>");
			}
		}

		if (needNext) {
			sb.append(
					"<li class='page-item'><span class='page-link' style='color: black;'><a href='/serviceSearch?search="
							+ search + "&currentPage=" + (endNavi + 1) + "'> &gt;</a></span></li>");
		}
		sb.append("</ul></nav>");
		return sb.toString();
	}

	// service 게시글의 해당 댓글 가져오기
	public ArrayList<ServiceComment> serviceCommentList(Connection conn, int serviceNo, int currentPage,
			int recordCountPerPage) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<ServiceComment> list = new ArrayList<ServiceComment>();
		String query = "SELECT * FROM(SELECT BOARD_COMMENT.*, ROW_NUMBER() OVER(ORDER BY COMMENT_NO DESC) AS NUM FROM BOARD_COMMENT WHERE SERVICE_NO=?) WHERE NUM BETWEEN ? AND ?";

		int start = currentPage * recordCountPerPage - (recordCountPerPage - 1);
		int end = currentPage * recordCountPerPage;
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, serviceNo);
			pstmt.setInt(2, start);
			pstmt.setInt(3, end);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				ServiceComment comment = new ServiceComment();
				comment.setCommentNo(rset.getInt("comment_no"));
				comment.setContent(rset.getString("content"));
				comment.setUserId(rset.getString("user_id"));
				comment.setServiceNo(rset.getInt("service_no"));
				comment.setcRegdate(rset.getDate("c_regdate"));
				list.add(comment);
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

	// Service 게시글 총 갯수 세기
	public int totalCount(Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int recordTotalCount = 0;

		// 게시글 총 갯수를 알아오는 쿼리
		String query = "SELECT COUNT(*) AS TOTALCOUNT FROM SERVICE_BOARD";

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

	// ServicePageNavi 가져오기
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
			sb.append(
					"<li class='page-item'><span class='page-link' style='color: black;'><a href='/service?currentPage="
							+ (startNavi - 1) + "'>&lt;</a>></span></li>");
		}
		for (int i = startNavi; i <= endNavi; i++) {
			if (i == currentPage) {
				sb.append(
						"<li class='page-item'><span class='page-link' style='color: black;'><a href='/service?currentPage="
								+ i + "'><b>" + i + "</b></a></span></li>");
			} else {
				sb.append(
						"<li class='page-item'><span class='page-link' style='color: black;'><a href='/service?currentPage="
								+ i + "'>" + i + "</a></span></li>");
			}
		}

		if (needNext) {
			sb.append(
					"<li class='page-item'><span class='page-link' style='color: black;'><a href='service?currentPage="
							+ (endNavi + 1) + "'>&gt;</a></span></li>");
		}
		sb.append("</ul></nav>");
		return sb.toString();
	}

	// Service 댓글 총 갯수
	public int totalCommentCount(Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int recordTotalCount = 0;

		// 게시글 총 갯수를 알아오는 쿼리
		String query = "SELECT COUNT(*) AS TOTALCOUNT FROM BOARD_COMMENT WHERE SERVICE_NO IS NOT NULL";

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

	// ServiceCommentPageNavi 가져오기
	public String getCommentPageNavi(Connection conn, int currentPage, int recordCountPerPage, int naviCountPerPage) {
		int recordTotalCount = totalCommentCount(conn);
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
		if (needPrev) {
			sb.append("<a href='/serviceSelect?currentPage=" + (startNavi - 1) + "'><</a>>");
		}
		for (int i = startNavi; i <= endNavi; i++) {
			if (i == currentPage) {
				sb.append("<a href='/serviceSelect?currentPage=" + i + "'><b>" + i + "</b></a>");
			} else {
				sb.append("<a href='/serviceSelect?currentPage=" + i + "'>" + i + "</a>");
			}
		}

		if (needNext) {
			sb.append("<a href='service?currentPage=" + (endNavi + 1) + "'>></a>");
		}

		return sb.toString();
	}

	// 고객센터 글 작성
	public int insertService(Connection conn, String serviceCategory, String serviceContent, String userId) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "INSERT INTO SERVICE_BOARD VALUES(SERVICE_SEQ.NEXTVAL,?,?,?,SYSDATE)";

		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, userId);
			pstmt.setString(2, serviceContent);
			pstmt.setString(3, serviceCategory);
			System.out.println(userId + serviceContent + serviceCategory);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			ConnectionFactory.close(pstmt);
		}
		return result;
	}

	// 고객센터 글 수정
	public int serviceModify(Connection conn, int serviceNo, String serviceContent, String serviceCategory) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "UPDATE SERVICE_BOARD SET SERVICE_CONTENT = ?, SERVICE_CATEGORY = ?  WHERE SERVICE_NO = ?";

		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, serviceContent);
			pstmt.setString(2, serviceCategory);
			pstmt.setInt(3, serviceNo);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			ConnectionFactory.close(pstmt);
		}
		return result;
	}

	public int deleteService(Connection conn, int serviceNo) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "DELETE FROM SERVICE_BOARD WHERE SERVICE_NO=?";

		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, serviceNo);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			ConnectionFactory.close(pstmt);
		}
		return result;
	}

	// 코멘트 달기
	public int insertServiceComment(Connection conn, String comment, String userId, int serviceNo) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "INSERT INTO BOARD_COMMENT VALUES(COMMENT_SEQ.NEXTVAL,?,?,'SERVICE',SYSDATE,NULL,NULL,?) ";

		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, comment);
			pstmt.setString(2, userId);
			pstmt.setInt(3, serviceNo);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			ConnectionFactory.close(pstmt);
		}
		return result;
	}

	// 코멘트 수정
	public int modifyServiceComment(Connection conn, int commentNo, String content) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "UPDATE BOARD_COMMENT SET CONTENT = ?, C_REGDATE=SYSDATE WHERE COMMENT_NO=?";

		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, content);
			pstmt.setInt(2, commentNo);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			ConnectionFactory.close(pstmt);
		}

		return result;
	}

	// 코멘트 삭제
	public int deleteServiceComment(Connection conn, int commentNo) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "DELETE FROM BOARD_COMMENT WHERE COMMENT_NO=?";

		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, commentNo);
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
