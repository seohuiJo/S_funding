package serviceboard.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import serviceboard.model.vo.ServiceBoard;
import serviceboard.model.vo.ServicePageData;

public class ServiceDAO {

	
	// Service �۾��� ====================================================================================
	public int insertService(Connection conn, String serviceTitle, String serviceContent, String userId) {
		return 0;
	}
	// ===============================================================================================

	
	
	// Service ��� ================================================================
	public ServiceBoard serviceSelect(Connection conn, int serviceNo) {
		return null;
	}
	// ============================================================================



	// Service �˻� ======================================================
	public ArrayList<ServiceBoard> serviceSearchList(Connection conn, int currentPage, int recordCountPerPage, String search) {
		return null;
	}
	// ======================================================================



	// Service ���� ======================
	public int serviceModify(Connection conn, int serviceNo, String serviceTitle, String serviceContent) {
		return 0;
	}
	// ==================================



	
	// Service ���� ========================================
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
