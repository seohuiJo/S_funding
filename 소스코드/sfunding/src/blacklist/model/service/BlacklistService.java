package blacklist.model.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import blacklist.model.dao.BlacklistDAO;
import blacklist.model.vo.BlackList;
import blacklist.model.vo.BlackListPageData;
import common.ConnectionFactory;

public class BlacklistService {
	private ConnectionFactory factory;

	public BlacklistService() {
		factory = ConnectionFactory.getConnection();
	}

	// 블랙리스트 모두 출력
	// 고객센터 모두 출력
	public BlackListPageData selectBlackList(int currentPage) {
		// SELECT * FROM BLACKLIST;
		Connection conn = null;
		int recordCountPerPage = 10;
		int naviCountPerPage = 5;
		BlackListPageData pd = new BlackListPageData();
		ArrayList<BlackList> blackList = null;

		try {
			conn = factory.createConnection();
			blackList = new BlacklistDAO().selectBlackList(conn, currentPage, recordCountPerPage);
			pd.setPageList(blackList);
			pd.setPageNavi(new BlacklistDAO().getPageNavi(conn, currentPage, recordCountPerPage, naviCountPerPage));
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			factory.close(conn);
		}

		return pd;

	}

	// 검색 목록 불러오기
	public BlackListPageData blacklistSearchList(int currentPage, String search) {
		Connection conn = null;
		int recordCountPerPage = 10;
		int naviCountPerPage = 5;
		BlackListPageData pd = new BlackListPageData();

		try {
			conn = factory.createConnection();
			pd.setPageList(new BlacklistDAO().blacklistSearchList(conn, currentPage, recordCountPerPage, search));
			pd.setPageNavi(new BlacklistDAO().getSearchPageNavi(conn, currentPage, recordCountPerPage, naviCountPerPage,
					search));

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			factory.close(conn);
		}
		return pd;
	}

	// 글 삭제
	public int deleteBlacklist(String userId) {
		Connection conn = null;
		int result = 0;
		try {
			conn = factory.createConnection();
			result = new BlacklistDAO().deleteBlacklist(conn, userId);
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

	public int insertBlacklist(String userId, String reason) {
		Connection conn = null;
		int result = 0;

		try {
			conn = factory.createConnection();
			result = new BlacklistDAO().addBlacklist(conn, userId, reason);
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
