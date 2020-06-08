package notice.model.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import common.ConnectionFactory;
import notice.model.dao.NoticeDAO;
import notice.model.vo.Notice;
import notice.model.vo.NoticePageData;

public class NoticeService {
	private ConnectionFactory factory;

	public NoticeService() {
		factory = ConnectionFactory.getConnection();
	}

	public NoticePageData selectNoticeList(int currentPage) {
		ArrayList<Notice> list = null;
		int recordCountPerPage = 10;
		int naviCountPerPage = 5;
		NoticePageData Noticepd = new NoticePageData();
		Connection conn = null;
		try {
			conn = factory.createConnection();
			list = new NoticeDAO().noticeList(conn, currentPage, recordCountPerPage);
			Noticepd.setPageList(list);
			Noticepd.setPageNavi(new NoticeDAO().getPageNavi(conn, currentPage, recordCountPerPage, naviCountPerPage));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			factory.close(conn);
		}

		return Noticepd;
	}

	public Notice noticeSelect(int noticeNo) {
		Connection conn = null;
		Notice notice = null;
		try {
			conn = factory.createConnection();
			notice = new NoticeDAO().noticeSelect(conn, noticeNo);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			factory.close(conn);
		}

		return notice;
	}

	public int insertNotice(String title, String content, String userId, String userName) {
		Connection conn = null;
		int result = 0;

		try {
			conn = factory.createConnection();
			result = new NoticeDAO().insertNotice(conn, title, content, userId, userName);
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

	public int ModifyNotice(String title, String content, int noticeNo) {
		Connection conn = null;
		int result = 0;

		try {
			conn = factory.createConnection();
			result = new NoticeDAO().modifyNotice(conn, title, content, noticeNo);
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

	public int deleteNotice(int noticeNo) {
		int result = 0;
		Connection conn = null;

		try {
			conn = factory.createConnection();
			result = new NoticeDAO().deleteNotice(conn, noticeNo);
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

	public NoticePageData noticeSearchList(int currentPage, String search) {
		Connection conn = null;
		int recordCountPerPage = 10;
		int naviCountPage = 5;
		NoticePageData pageData = new NoticePageData();
		try {
			conn = factory.createConnection();
			pageData.setPageList(new NoticeDAO().searchList(conn, currentPage, recordCountPerPage, search));
			pageData.setPageNavi(
					new NoticeDAO().getSearchPageNavi(conn, currentPage, recordCountPerPage, naviCountPage, search));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			factory.close(conn);
		}
		return pageData;
	}
}
