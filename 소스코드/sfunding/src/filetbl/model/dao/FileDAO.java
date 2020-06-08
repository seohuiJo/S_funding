package filetbl.model.dao;

import java.sql.Connection;
import java.util.ArrayList;

import filetbl.model.vo.File;

public class FileDAO {

	public int uploadFile(Connection conn, File filedata) {
		int result = 0;
		return result;
	}

	public ArrayList<File> selectFileList(Connection conn, String userId) {
		return null;
	}

	public int deleteFile(Connection conn, String filePath) {
		int result = 0;
		return result;
	}
}
