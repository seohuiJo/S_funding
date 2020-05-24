package file.model.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import common.ConnectionFactory;
import file.model.dao.FileDAO;
import file.model.vo.File;

public class FileService {
	private ConnectionFactory factory;

	// 파일 업로드 =====================================================
	public int uploadFile(File filedata) {
		return 0;
	}
	// =================================================================
	

	// 파일 목록 ======================================================
	public ArrayList<File> selectFileList(String userId) {
		return null;
	}
	// ===============================================================

	
	// 파일 삭제 ==========================================================
	public int deleteFile(String filePath) {
		return 0;
	}
	// =====================================================================
}
