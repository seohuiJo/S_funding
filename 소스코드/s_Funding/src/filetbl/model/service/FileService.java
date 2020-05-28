package filetbl.model.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import common.ConnectionFactory;
import filetbl.model.dao.FileDAO;
import filetbl.model.vo.File;

public class FileService {
	private ConnectionFactory factory;

	// ���� ���ε� =====================================================
	public int uploadFile(File filedata) {
		return 0;
	}
	// =================================================================
	

	// ���� ��� ======================================================
	public ArrayList<File> selectFileList(String userId) {
		return null;
	}
	// ===============================================================

	
	// ���� ���� ==========================================================
	public int deleteFile(String filePath) {
		return 0;
	}
	// =====================================================================
}
