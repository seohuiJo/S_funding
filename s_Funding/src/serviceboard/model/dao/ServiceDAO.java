package serviceboard.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import serviceboard.model.vo.ServiceBoard;
import serviceboard.model.vo.ServicePageData;

public class ServiceDAO {

	
	// Service 글쓰기 ====================================================================================
	public int insertService(Connection conn, String serviceTitle, String serviceContent, String userId) {
		return 0;
	}
	// ===============================================================================================

	
	
	// Service 목록 ================================================================
	public ServiceBoard serviceSelect(Connection conn, int serviceNo) {
		return null;
	}
	// ============================================================================



	// Service 검색 ======================================================
	public ArrayList<ServiceBoard> serviceSearchList(Connection conn, int currentPage, int recordCountPerPage, String search) {
		return null;
	}
	// ======================================================================



	// Service 수정 ======================
	public int serviceModify(Connection conn, int serviceNo, String serviceTitle, String serviceContent) {
		return 0;
	}
	// ==================================



	
	// Service 삭제 ========================================
	public int deleteService(Connection conn, int serviceNo) {
		return 0;
	}
	// =======================================================
	
	
	// PageData ====================================================
	public ServicePageData serviceSelectList(int currentPage) {
		return null;
	}
	
	public ServicePageData serviceSearchList(int currentPage, String search) {
		return null;
	}
}
