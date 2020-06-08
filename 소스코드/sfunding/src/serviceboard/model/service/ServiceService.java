package serviceboard.model.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import common.ConnectionFactory;
import serviceboard.model.dao.ServiceDAO;
import serviceboard.model.vo.ServiceBoard;
import serviceboard.model.vo.ServicePageData;
import serviceboard.model.vo.ServiceCommentPage;
import serviceboard.model.vo.ServiceComment;

public class ServiceService {

	private ConnectionFactory factory;

	public ServiceService() {
		factory = ConnectionFactory.getConnection();
	}

	// 고객센터 모두 출력
	public ServicePageData selectServiceList(int currentPage) {
		// SELECT * FROM SERVICE;
		Connection conn = null;
		int recordCountPerPage = 10;
		int naviCountPerPage = 5;
		ServicePageData pd = new ServicePageData();
		ArrayList<ServiceBoard> serviceList = null;

		try {
			conn = factory.createConnection();
			serviceList = new ServiceDAO().selectServiceList(conn, currentPage, recordCountPerPage);
			pd.setPageList(serviceList);
			pd.setPageNavi(new ServiceDAO().getPageNavi(conn, currentPage, recordCountPerPage, naviCountPerPage));
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			factory.close(conn);
		}
		return pd;

	}

	// 하나 선택해서 보여주기
	public ServiceBoard serviceSelect(int serviceNo) {
		Connection conn = null;
		ServiceBoard service = null;

		try {
			conn = factory.createConnection();
			service = new ServiceDAO().serviceSelect(conn, serviceNo);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			factory.close(conn);
		}

		return service;
	}

	// 검색 목록 불러오기
	public ServicePageData serviceSearchList(int currentPage, String search) {
		Connection conn = null;
		int recordCountPerPage = 10;
		int naviCountPerPage = 5;
		ServicePageData pd = new ServicePageData();

		try {
			conn = factory.createConnection();
			pd.setPageList(new ServiceDAO().serviceSearchList(conn, currentPage, recordCountPerPage, search));
			pd.setPageNavi(new ServiceDAO().getSearchPageNavi(conn, currentPage, recordCountPerPage, naviCountPerPage,
					search));

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			factory.close(conn);
		}
		return pd;
	}

	// 댓글 pd 가져오기
	public ServiceCommentPage selectCommentList(int serviceNo, int currentPage) {
		Connection conn = null;
		int recordCountPerPage = 10;
		int naviCountPerPage = 5;
		ServiceCommentPage pd = new ServiceCommentPage();
		ArrayList<ServiceComment> commentList = null;

		try {
			conn = factory.createConnection();
			commentList = new ServiceDAO().serviceCommentList(conn, serviceNo, currentPage, recordCountPerPage);
			pd.setPageList(commentList);
			pd.setPageNavi(new ServiceDAO().getPageNavi(conn, currentPage, recordCountPerPage, naviCountPerPage));
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			factory.close(conn);
		}
		return pd;
	}

	// 고객센터 글 작성
	public int insertService(String serviceCategory, String serviceContent, String userId) {
		Connection conn = null;
		int result = 0;

		try {
			conn = factory.createConnection();
			result = new ServiceDAO().insertService(conn, serviceCategory, serviceContent, userId);
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

	// 글 수정
	public int serviceModify(int serviceNo, String serviceContent, String serviceCategory) {
		Connection conn = null;
		int result = 0;

		try {
			conn = factory.createConnection();
			result = new ServiceDAO().serviceModify(conn, serviceNo, serviceContent, serviceCategory);
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

	// 글 삭제
	public int deleteService(int serviceNo) {
		Connection conn = null;
		int result = 0;
		try {
			conn = factory.createConnection();
			result = new ServiceDAO().deleteService(conn, serviceNo);
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

	// 코멘트 달기
	public int insertServiceComment(String comment, String userId, int serviceNo) {
		Connection conn = null;
		int result = 0;
		try {
			conn = factory.createConnection();
			result = new ServiceDAO().insertServiceComment(conn, comment, userId, serviceNo);
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

	// 코멘트 수정
	public int modifyServiceComment(int commentNo, String content) {
		Connection conn = null;
		int result = 0;
		try {
			conn = factory.createConnection();
			result = new ServiceDAO().modifyServiceComment(conn, commentNo, content);
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

	// 코멘트 삭제
	public int deleteServiceComment(int commentNo) {
		Connection conn = null;
		int result = 0;

		try {
			conn = factory.createConnection();
			result = new ServiceDAO().deleteServiceComment(conn, commentNo);
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
