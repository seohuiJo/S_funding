package serviceboard.model.service;



import java.sql.Connection;
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
	
	
	// Service 글쓰기 =============================================================================
	public int insertService(String serviceTitle, String serviceContent, String userId) {
		return 0;
	}
	// ==============================================================================================


	// Service 목록 ===================================================================
	public ServiceBoard serviceSelect(int serviceNo) {
		return null;
	}


	// Service 검색 ===============================================================================================================
	public ServicePageData serviceSearchList(int currentPage, String search) {
		return null;
	}
	// ==========================================================================================================================


	// Service 수정 =======================================================================
	public int serviceModify(int serviceNo, String serviceTitle, String serviceContent) {
		return 0;
	}
	// ================================================================================


	// Service 삭제 =================================================
	public int deleteService(int serviceNo) {
		return 0;
	}
	// ================================================================




}
