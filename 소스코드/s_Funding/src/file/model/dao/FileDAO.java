package file.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import file.model.vo.File;

public class FileDAO {

	
	// 파일 업로드 ================================================
	public int uploadFile(Connection conn, File filedata) {
		return 0;
	}
	// ===========================================================

	
	// 파일 목록 ============================================================
	public ArrayList<File> selectFileList(Connection conn, String userId) {
		return null;
	}
	// =============================================================================


	// 파일 삭제 =============================================================
	public int deleteFile(Connection conn, String filePath) {
		return 0;
	}
	// ======================================================================
}
