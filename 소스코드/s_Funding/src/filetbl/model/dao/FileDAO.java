package filetbl.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import filetbl.model.vo.File;

public class FileDAO {

	
	// ���� ���ε� ================================================
	public int uploadFile(Connection conn, File filedata) {
		return 0;
	}
	// ===========================================================

	
	// ���� ��� ============================================================
	public ArrayList<File> selectFileList(Connection conn, String userId) {
		return null;
	}
	// =============================================================================


	// ���� ���� =============================================================
	public int deleteFile(Connection conn, String filePath) {
		return 0;
	}
	// ======================================================================
}
