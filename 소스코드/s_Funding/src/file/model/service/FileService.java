package file.model.service;
import file.model.dao.FileDAO;
import file.model.vo.FileData;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import common.ConnectionFactory;

public class FileService {
	
	private ConnectionFactory factory;
	
	public FileService() {
		factory = ConnectionFactory.getConnection();
	}
	
	public int uploadFile(FileData fileData) {
		Connection conn=null;
		int result=0;
		
		try {
			conn=factory.createConnection();
			result=new FileDAO().uploadFile(conn, fileData);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(result>0) {
			factory.commit(conn);
		}else {
			factory.rollback(conn);
		}
		
		return result;
	}
	
	public ArrayList<FileData> selectFileList(String userId){
		Connection conn=null;
		ArrayList<FileData> list=new ArrayList<FileData>();
		try {
			conn=factory.createConnection();
			list=new FileDAO().selectFileList(conn, userId);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return list;
	}
	
	public int deleteFile(String filePath) {
		Connection conn=null;
		int result=0;
		
		try {
			conn=factory.createConnection();
			result=new FileDAO().deleteFile(conn, filePath);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(result>0) {
			factory.commit(conn);
		}else {
			factory.rollback(conn);
		}
		
		return result;
	}

}













