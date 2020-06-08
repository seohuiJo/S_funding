package requestboard.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import commentboard.model.vo.Comment;
import common.ConnectionFactory;
import requestboard.model.vo.RequestBoard;

public class RequestDAO {
	// 요청 글 전체 보기
	public ArrayList<RequestBoard> requestSelectList(Connection conn, int currentPage, int recordCountPerPage) {
		ArrayList<RequestBoard> rList = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = "SELECT * FROM (SELECT REQUEST_BOARD.*, ROW_NUMBER() OVER(ORDER BY REQUEST_NO DESC) AS NUM FROM REQUEST_BOARD) WHERE NUM BETWEEN ? AND ?";

		int start = currentPage * recordCountPerPage - (recordCountPerPage - 1);
		int end = currentPage * recordCountPerPage;
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, start);
			pstmt.setInt(2, end);
			rset = pstmt.executeQuery();
			rList = new ArrayList<RequestBoard>();
			while (rset.next()) {
				RequestBoard rBoard = new RequestBoard();
				rBoard.setRequestNo(rset.getInt("REQUEST_NO"));
				rBoard.setUserId(rset.getString("USER_ID"));
				rBoard.setRequestTitle(rset.getString("REQUEST_TITLE"));
				rBoard.setRequestContent(rset.getString("REQUEST_CONTENT"));
				// 새로바꾼거, status 테이블에서 공감, 비공감 데이터 불러오기
				rBoard.setGood(this.totalLike(conn, rset.getInt("REQUEST_NO")));
				rBoard.setBad(this.totalDislike(conn, rset.getInt("REQUEST_NO")));
				rBoard.setrRegdate(rset.getDate("R_REGDATE"));
				rBoard.setProjectList(rset.getString("PROJECT_LIST"));
				rList.add(rBoard);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			ConnectionFactory.close(pstmt);
			ConnectionFactory.close(rset);
		}
		return rList;
	}

	// 글 전체 갯수 리턴
	public int totalCount(Connection conn) {
		// 전체 갯수를 나눠주는 메소드
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int recordTotalCount = 0;
		String query = "SELECT COUNT(*) AS TOTALCOUNT FROM REQUEST_BOARD";
		// 게시글 총 갯수를 알아오는 쿼리

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

	// 페이지 네비게이션
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
					"<li class='page-item'><span class='page-link' style='color: black;'><a href='/RequestServlet?currentPage="
							+ (startNavi - 1) + "'> &lt; </a></span></li>");
		}
		for (int i = startNavi; i <= endNavi; i++) {
			if (i == currentPage) {
				sb.append(
						"<li class='page-item'><span class='page-link' style='color: black;'><a href='/RequestServlet?currentPage="
								+ i + "'><b>" + i + "</b></a></span></li>");

			} else {
				sb.append(
						"<li class='page-item'><span class='page-link' style='color: black;'><a href='/RequestServlet?currentPage="
								+ i + "'>" + i + "</a></span></li>");
			}
		}
		if (needNext) {
			sb.append(
					"<li class='page-item'><span class='page-link' style='color: black;'><a href='/RequestServlet?currentPage="
							+ (endNavi + 1) + "'> &gt; </a></span></li>");
		}
		sb.append("</ul></nav>");
		return sb.toString();
	}

	// 검색 결과 글 목록 가져오기
	public ArrayList<RequestBoard> requestSearchList(Connection conn, int currentPage, int recordCountPerPage,
			String search) {
		ArrayList<RequestBoard> rList = new ArrayList<RequestBoard>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;

		int start = currentPage * recordCountPerPage - (recordCountPerPage - 1);
		int end = currentPage * recordCountPerPage;
		String query = "SELECT * FROM (SELECT REQUEST_BOARD.*, ROW_NUMBER() OVER(ORDER BY REQUEST_NO DESC) AS NUM FROM REQUEST_BOARD WHERE REQUEST_TITLE LIKE '%"
				+ search + "%') WHERE NUM BETWEEN ? AND ?";

		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, start);
			pstmt.setInt(2, end);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				RequestBoard rBoard = new RequestBoard();
				rBoard.setRequestNo(rset.getInt("REQUEST_NO"));
				rBoard.setUserId(rset.getString("USER_ID"));
				rBoard.setRequestTitle(rset.getString("REQUEST_TITLE"));
				rBoard.setRequestContent(rset.getString("REQUEST_CONTENT"));
				// 새로바꾼거, status 테이블에서 공감, 비공감 데이터 불러오기
				rBoard.setGood(this.totalLike(conn, rset.getInt("REQUEST_NO")));
				rBoard.setBad(this.totalDislike(conn, rset.getInt("REQUEST_NO")));
				rBoard.setrRegdate(rset.getDate("R_REGDATE"));
				rBoard.setProjectList(rset.getString("PROJECT_LIST"));
				rList.add(rBoard);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			ConnectionFactory.close(pstmt);
			ConnectionFactory.close(rset);
		}
		return rList;
	}

	// 검색 결과 글 갯수 리턴
	public int searchTotalCount(Connection conn, String search) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int recordTotalCount = 0;

		String query = "SELECT COUNT(*) AS TOTALCOUNT FROM REQUEST_BOARD WHERE REQUEST_CONTENT LIKE '%" + search + "%'";

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

	// 검색결과 페이지 네비
	public String getSearchPageNavi(Connection conn, int currentPage, int recordCountPerPage, int naviCountPerPage,
			String search) {
		int recordTotalCount = searchTotalCount(conn, search);
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
					"<li class='page-item'><span class='page-link' style='color: black;'><a href='/RequestSearchServlet?search="
							+ search + "&currentPage=" + (startNavi - 1) + "'> &lt; </a></span></li>");
		}
		for (int i = startNavi; i <= endNavi; i++) {
			if (i == currentPage) {
				sb.append(
						"<li class='page-item'><span class='page-link' style='color: black;'><a href='/RequestSearchServlet?search="
								+ search + "&currentPage=" + i + "'><b>" + i + "</b></a></span></li>");

			} else {
				sb.append(
						"<li class='page-item'><span class='page-link' style='color: black;'><a href='/RequestSearchServlet?search="
								+ search + "&currentPage=" + i + "'>" + i + "</a></span></li>");
			}
		}
		if (needNext) {
			sb.append(
					"<li class='page-item'><span class='page-link' style='color: black;'><a href='/RequestSearchServlet?search="
							+ search + "&currentPage=" + (endNavi + 1) + "'> &gt; </a></span></li>");
		}
		sb.append("</ul></nav>");

		return sb.toString();
	}

	// 요청 글 작성
	public int insertRequest(Connection conn, String userId, String requestTitle, String requestContent,
			String projectList) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "INSERT INTO REQUEST_BOARD VALUES (REQUEST_SEQ.NEXTVAL,?,?,?,0,0,SYSDATE,?)";
		// PROJECT_LIST 값은 서브쿼리를 이용해서 가져온다.
		// project_list는 나중에처리
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, userId);
			pstmt.setString(2, requestTitle);
			pstmt.setString(3, requestContent);
			pstmt.setString(4, projectList);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			ConnectionFactory.close(pstmt);
		}
		return result;
	}

	// 요청글 상세보기 (글 선택)
	public RequestBoard requestSelect(Connection conn, int requestNo) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		RequestBoard rBoard = null;
		String query = "SELECT * FROM REQUEST_BOARD WHERE REQUEST_NO=?";

		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, requestNo);
			rset = pstmt.executeQuery();
			if (rset.next()) {
				rBoard = new RequestBoard();
				rBoard.setRequestNo(rset.getInt("REQUEST_NO"));
				rBoard.setUserId(rset.getString("USER_ID"));
				rBoard.setRequestTitle(rset.getString("REQUEST_TITLE"));
				rBoard.setRequestContent(rset.getString("REQUEST_CONTENT"));
//				rBoard.setGood(rset.getInt("GOOD"));
//				rBoard.setBad(rset.getInt("BAD"));
				rBoard.setrRegdate(rset.getDate("R_REGDATE"));
				rBoard.setProjectList(rset.getString("PROJECT_LIST"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			ConnectionFactory.close(pstmt);
			ConnectionFactory.close(rset);
		}
		return rBoard;
	}

	// 요청글 삭제
	public int deleteRequest(Connection conn, int requestNo) {
		int result = 0;
		PreparedStatement pstmt = null;
		String query = "delete from request_board where request_no =?";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, requestNo);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			ConnectionFactory.close(pstmt);
		}
		return result;
	}

	// 요청글 삭제할때 해당 요청글에 있던 댓글 모두 삭제
	public int deleteRequestCommentAll(Connection conn, int requestNo) {
		int result = 0;
		PreparedStatement pstmt = null;
		String query = "delete from board_comment where request_no=?";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, requestNo);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			ConnectionFactory.close(pstmt);
		}
		return result;
	}

	// 댓글 목록 불러오기 - 요청글
	public ArrayList<Comment> requestComment(Connection conn, int requestNo) {
		ArrayList<Comment> requestCmtList = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = "select * from board_comment where request_no = ? order by comment_no";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, requestNo);
			rset = pstmt.executeQuery();
			requestCmtList = new ArrayList<Comment>();
			while (rset.next()) {
				Comment cmt = new Comment();
				cmt.setCommentNo(rset.getInt("comment_no"));
				cmt.setContent(rset.getString("content"));
				cmt.setUserId(rset.getString("user_id"));
				cmt.setBoardType("request");
				cmt.setcRegdate(rset.getDate("c_regdate"));
				cmt.setRequestNo(rset.getInt("request_no"));
				requestCmtList.add(cmt);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			ConnectionFactory.close(pstmt);
			ConnectionFactory.close(rset);
		}
		return requestCmtList;
	}

	// 댓글 작성 - 요청글
	public int insertRequestComment(Connection conn, int requestNo, String content, String userId) {
		int result = 0;
		PreparedStatement pstmt = null;
		String query = "insert into board_comment values(comment_seq.nextval,?,?,'REQUEST',sysdate, null, ?, null)";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, content);
			pstmt.setString(2, userId);
			pstmt.setInt(3, requestNo);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			ConnectionFactory.close(pstmt);
		}
		return result;
	}

	// 댓글 수정 - 요청글
	public int updateRequestComment(Connection conn, int commentNo, int requestNo, String content) {
		int result = 0;
		PreparedStatement pstmt = null;
		// String query = "update board_comment set content = ? where comment_no =? and
		// request_no = ?";
		String query = "update board_comment set content = ? where comment_no =?";

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

	// 댓글 삭제 - 요청글
	public int deleteRequestComment(Connection conn, int commentNo) {
		int result = 0;
		PreparedStatement pstmt = null;
		String query = "delete from board_comment where comment_no =?";

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

	// 공감 전체 갯수 세기
	public int totalLike(Connection conn, int requestNo) {
		int result = 0;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = "select count(*) as cnt from request_status where request_no= ? and status='good'";

		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, requestNo);
			rset = pstmt.executeQuery();
			if (rset.next()) {
				result = rset.getInt("cnt");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			ConnectionFactory.close(pstmt);
			ConnectionFactory.close(rset);
		}
		return result;
	}

	// 비공감 전체 갯수 세기
	public int totalDislike(Connection conn, int requestNo) {
		int result = 0;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = "select count(*) as cnt from request_status where request_no=? and status='bad'";

		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, requestNo);
			rset = pstmt.executeQuery();
			if (rset.next()) {
				result = rset.getInt("cnt");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			ConnectionFactory.close(pstmt);
			ConnectionFactory.close(rset);
		}
		return result;
	}

	// 글번호,아이디를 받아서 해당 유저 기록확인 - 공감
	public int checkStatusGood(Connection conn, int requestNo, String userId) {
		int result = 0;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = "select * from request_status where request_no = ? and user_id = ? and status='good'";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, requestNo);
			pstmt.setString(2, userId);
			rset = pstmt.executeQuery();
			if (rset.next()) {
				result = 1;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	// 글번호,아이디를 받아서 해당 유저 기록확인 - 비공감
	public int checkStatusBad(Connection conn, int requestNo, String userId) {
		int result = 0;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = "select * from request_status where request_no = ? and user_id = ? and status='bad'";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, requestNo);
			pstmt.setString(2, userId);
			rset = pstmt.executeQuery();
			if (rset.next()) {
				result = 1;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	// 공감 추가
	public int addLike(Connection conn, int requestNo, String userId) {
		int result = 0;
		PreparedStatement pstmt = null;
		String query = "insert into request_status values(request_status_seq.nextval, ?, ?,'good')";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, requestNo);
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

	// 공감 취소
	public int cancelLike(Connection conn, int requestNo, String userId) {
		int result = 0;
		PreparedStatement pstmt = null;
		String query = "delete from request_status where request_no = ? and user_id = ? and status = 'good'";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, requestNo);
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

	// 비공감 추가
	public int addDislike(Connection conn, int requestNo, String userId) {
		int result = 0;
		PreparedStatement pstmt = null;
		String query = "insert into request_status values(request_status_seq.nextval, ?, ?,'bad')";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, requestNo);
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

	// 비공감 취소
	public int cancelDislike(Connection conn, int requestNo, String userId) {
		int result = 0;
		PreparedStatement pstmt = null;
		String query = "delete from request_status where request_no = ? and user_id = ? and status = 'bad'";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, requestNo);
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
}
