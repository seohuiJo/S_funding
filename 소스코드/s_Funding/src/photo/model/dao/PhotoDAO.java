package photo.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import photo.model.vo.Photo;

public class PhotoDAO {
	public int insertPhoto(Connection conn, Photo p) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "insert into photo values(seq_photo.nextval,?,?,?,?,0,sysdate)";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(4, p.getPhotoWriter());
			pstmt.setString(1, p.getPhotoContent());
			pstmt.setString(2, p.getPhotoFilename());
			pstmt.setString(3, p.getPhotoFilepath());
			result = pstmt.executeUpdate();			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			
		}		
		return result;
	}
	
	public int totalCount(Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int result = 0;
		String query = "select count(*) cnt from photo";
		try {
			pstmt = conn.prepareStatement(query);
			rset = pstmt.executeQuery();
			if(rset.next()) {
				result = rset.getInt("cnt");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			
		}
		return result;
	}

	public ArrayList<Photo> morePhoto(Connection conn, int start, int end) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<Photo> list = new ArrayList<Photo>();
		String query = "select * from (select ROWNUM as rnum, p.* from (select * from photo order by photo_no desc)p) where rnum between ? and ?";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, start);
			pstmt.setInt(2, end);
			rset = pstmt.executeQuery();
			while(rset.next()) {
				Photo p = new Photo();
				p.setPhotoContent(rset.getString("photo_content"));
				p.setPhotoDate(rset.getDate("photo_date"));
				p.setPhotoFilename(rset.getString("photo_filename"));
				p.setPhotoFilepath(rset.getString("photo_filepath"));
				p.setPhotoNo(rset.getInt("photo_no"));
				p.setPhotoReadCount(rset.getInt("photo_readcount"));
				p.setPhotoWriter(rset.getString("photo_writer"));
				list.add(p);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			
		}		
		return list;
	}
}
