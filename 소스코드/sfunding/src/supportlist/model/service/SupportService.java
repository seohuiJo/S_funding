package supportlist.model.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import common.ConnectionFactory;
import supportlist.model.dao.SupportDAO;
import supportlist.model.vo.SupportList;
import supportlist.model.vo.SupportListPageData;

public class SupportService {
	private ConnectionFactory factory;

	public SupportService() {
		factory = ConnectionFactory.getConnection();
	}

	public SupportListPageData supportList(String userId, int currentPage) {

		// TODO Auto-generated method stub
		ArrayList<SupportList> list = null;
		int recordcountPerPage = 5;
		int naviCountPerPage = 5;
		SupportListPageData supportPd = new SupportListPageData();
		Connection conn = null;

		try {
			conn = factory.createConnection();
			list = new SupportDAO().supportList(conn, userId, currentPage, recordcountPerPage);
			supportPd.setPageList(list);
			supportPd.setPageNavi(
					new SupportDAO().getPageNavi(conn, userId, currentPage, recordcountPerPage, naviCountPerPage));

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			factory.close(conn);
		}
		return supportPd;
	}

	// 베스트 후원자
	public ArrayList<SupportList> supportBestList() {
		// TODO Auto-generated method stub
		Connection conn = null;
		ArrayList<SupportList> list = null;
		try {
			conn = factory.createConnection();
			list = new SupportDAO().supportbestlist(conn);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			factory.close(conn);
		}
		return list;
	}

}
