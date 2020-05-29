package serviceboard.model.service;



import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import common.ConnectionFactory;
import serviceboard.model.dao.ServiceDAO;
import serviceboard.model.vo.ServiceBoard;
import serviceboard.model.vo.ServicePageData;

public class ServiceService {

	private ConnectionFactory factory;
	
	public ServiceService() {
		factory = ConnectionFactory.getConnection();
	}
	
	// 고객센터 모두 출력
	public ServicePageData selectServiceList(int currentPage) {
		// SELECT * FROM SERVICE;
		Connection conn=null;
		int recordCountPerPage=10;
		int naviCountPerPage=5;
		ServicePageData pd=new ServicePageData();
		ArrayList<ServiceBoard> serviceList=null;
		
		try {
			conn=factory.createConnection();
			serviceList=new ServiceDAO().selectServiceList(conn, currentPage, recordCountPerPage);
			pd.setPageList(serviceList);
			pd.setPageNavi(new ServiceDAO().getPageNavi(conn, currentPage, recordCountPerPage, naviCountPerPage));
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		return pd;		
		
	}
	
	// 하나 선택해서 보여주기
	public ServiceBoard serviceSelect(int serviceNo) {
		Connection conn=null;
		ServiceBoard service=null;
		// ArrayList<NoticeComment> cmtList=null;
		
		try {
			conn=factory.createConnection();
			service=new ServiceDAO().serviceSelect(conn, serviceNo);
			// cmtList=new NoticeDAO().noticeComment(conn, noticeNo);
			// notice.setComments(cmtList);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return service;
	}




}
