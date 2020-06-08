package requestboard.model.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import commentboard.model.vo.Comment;
import common.ConnectionFactory;
import requestboard.model.dao.RequestDAO;
import requestboard.model.vo.RequestBoard;
import requestboard.model.vo.RequestPageData;

public class RequestService {
	private ConnectionFactory factory;

	public RequestService() {
		factory = ConnectionFactory.getConnection();
	}

	// 요청글 전체 보기
	public RequestPageData requestSelectList(int currentPage) {
		// SELECT * FROM REQUEST_BOARD
		Connection conn = null;
		int recordCountPerpage = 10;
		int naviCountPerPage = 5;
		RequestPageData pd = new RequestPageData();
		ArrayList<RequestBoard> rList = null;

		try {
			conn = factory.createConnection();
			rList = new RequestDAO().requestSelectList(conn, currentPage, recordCountPerpage);

			pd.setPageList(rList);
			pd.setPageNavi(new RequestDAO().getPageNavi(conn, currentPage, recordCountPerpage, naviCountPerPage));

			// 요청 게시판 + pagenavi 같이 저장
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			factory.close(conn);
		}
		return pd;
	}

	// 요청글 검색 결과 보기
	public RequestPageData requestSearchList(int currentPage, String search) {
		// 검색했을 때 나오는 pagedata
		Connection conn = null;
		int recordCountPerPage = 10;
		int naviCountPerPage = 5;
		RequestPageData pd = new RequestPageData();
		try {
			conn = factory.createConnection();
			pd.setPageList(new RequestDAO().requestSearchList(conn, currentPage, recordCountPerPage, search));
			pd.setPageNavi(new RequestDAO().getSearchPageNavi(conn, currentPage, recordCountPerPage, naviCountPerPage,
					search));

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			factory.close(conn);
		}
		return pd;
	}

	// 요청글 작성
	public int insertRequest(String userId, String requestTitle, String requestContent, String projectList) {
		Connection conn = null;
		int result = 0;
		try {
			conn = factory.createConnection();
			result = new RequestDAO().insertRequest(conn, userId, requestTitle, requestContent, projectList);
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

	// 게시글 삭제 (요청글)
	public int deleteRequest(int requestNo) {
		int result = 0;
		Connection conn = null;
		try {
			conn = factory.createConnection();
			result = new RequestDAO().deleteRequest(conn, requestNo);
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

	// 요청글 삭제할때 해당 댓글 전체 삭제
	public int deleteRequestCommentAll(int requestNo) {
		int result = 0;
		Connection conn = null;
		try {
			conn = factory.createConnection();
			result = new RequestDAO().deleteRequestCommentAll(conn, requestNo);
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

	// 댓글 삭제(요청글)
	public int deleteRequestComment(int commentNo) {
		int result = 0;
		Connection conn = null;
		try {
			conn = factory.createConnection();
			result = new RequestDAO().deleteRequestComment(conn, commentNo);
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

	// 댓글 수정(요청글)
	public int updateRequestComment(int commentNo, int requestNo, String content) {
		int result = 0;
		Connection conn = null;
		try {
			conn = factory.createConnection();
			result = new RequestDAO().updateRequestComment(conn, commentNo, requestNo, content);
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

	// 댓글 추가
	public int insertRequestComment(int requestNo, String content, String userId) {
		int result = 0;
		Connection conn = null;
		try {
			conn = factory.createConnection();
			result = new RequestDAO().insertRequestComment(conn, requestNo, content, userId);
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

	// 게시글 선택
	public RequestBoard requestSelect(int requestNo) {
		RequestBoard requestBoard = null; // 게시글
		ArrayList<Comment> requestCmtList = null; // 댓글
		Connection conn = null;
		try {
			conn = factory.createConnection();
			requestBoard = new RequestDAO().requestSelect(conn, requestNo);
			requestBoard.setGood(new RequestDAO().totalLike(conn, requestNo)); // 공감갯수 불러오기
			requestBoard.setBad(new RequestDAO().totalDislike(conn, requestNo)); // 비공감갯수 불러오기
			requestCmtList = new RequestDAO().requestComment(conn, requestNo);
			// 여기서 set
			requestBoard.setComments(requestCmtList);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			factory.close(conn);
		}
		return requestBoard;
	}

	// 공감 갯수 가져오기
	public int totalLike(int requestNo) {
		int result = 0;
		Connection conn = null;
		try {
			conn = factory.createConnection();
			result = new RequestDAO().totalLike(conn, requestNo);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			factory.close(conn);
		}
		return result;
	}

	// 비공감 갯수 가져오기
	public int totalDislike(int requestNo) {
		int result = 0;
		Connection conn = null;
		try {
			conn = factory.createConnection();
			result = new RequestDAO().totalDislike(conn, requestNo);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			factory.close(conn);
		}
		return result;
	}

	// 글번호,아이디를 받아서 해당 유저 기록확인 - 공감
	public int checkStatusGood(int requestNo, String userId) {
		int result = 0;
		Connection conn = null;
		try {
			conn = factory.createConnection();
			result = new RequestDAO().checkStatusGood(conn, requestNo, userId);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			factory.close(conn);
		}
		return result;
	}

	// 글번호,아이디를 받아서 해당 유저 기록확인 - 비공감
	public int checkStatusBad(int requestNo, String userId) {
		int result = 0;
		Connection conn = null;
		try {
			conn = factory.createConnection();
			result = new RequestDAO().checkStatusBad(conn, requestNo, userId);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			factory.close(conn);
		}
		return result;
	}

	// 공감 추가
	public int addLike(int requestNo, String userId) {
		int result = 0;
		Connection conn = null;
		try {
			conn = factory.createConnection();
			result = new RequestDAO().addLike(conn, requestNo, userId);
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

	// 공감 삭제
	public int cancelLike(int requestNo, String userId) {
		int result = 0;
		Connection conn = null;
		try {
			conn = factory.createConnection();
			result = new RequestDAO().cancelLike(conn, requestNo, userId);
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

	// 비공감 추가
	public int addDislike(int requestNo, String userId) {
		int result = 0;
		Connection conn = null;
		try {
			conn = factory.createConnection();
			result = new RequestDAO().addDislike(conn, requestNo, userId);
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

	// 비공감 삭제
	public int cancelDislike(int requestNo, String userId) {
		int result = 0;
		Connection conn = null;
		try {
			conn = factory.createConnection();
			result = new RequestDAO().cancelDislike(conn, requestNo, userId);
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
}
