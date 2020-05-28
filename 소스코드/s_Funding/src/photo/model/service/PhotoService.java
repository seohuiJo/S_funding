package photo.model.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import common.ConnectionFactory;
import photo.model.dao.PhotoDAO;
import photo.model.vo.Photo;

public class PhotoService {
	private ConnectionFactory factory;
	
	public PhotoService() {
		factory=ConnectionFactory.getConnection();
	}
	
	public int insertPhoto(Photo photo) {
		Connection conn=null;
		int result=0;
		
		try {
			conn=factory.createConnection();
			result=new PhotoDAO().insertPhoto(conn, photo);
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		if(result>0) {
			factory.commit(conn);
		}else {
			factory.rollback(conn);
		}
		return result;
	}
	
	public int totalCount() {
		Connection conn=null;
		int totalCount=0;
		
		try {
			conn=factory.createConnection();
			totalCount=new PhotoDAO().totalCount(conn);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return totalCount;
	}
	
	public ArrayList<Photo> morePhoto(int start){
		Connection conn=null;
		ArrayList<Photo> list=null;
		
		int length=5; // 한번에 더 가지고 올 사진수
		int end=start+length-1;
		
		try {
			conn=factory.createConnection();
			list=new PhotoDAO().morePhoto(conn, start, end);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
}














