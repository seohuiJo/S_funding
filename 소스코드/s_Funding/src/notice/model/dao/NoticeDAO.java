package notice.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import notice.model.vo.Notice;

public class NoticeDAO {
	
	public int deleteNotice(Connection conn) {
		
		
	}
	public Notice noticeSelect(Connection conn) {
		
		
	}
	public ArrayList<Notice> noticeSearchList(Connection conn) {
		
		
	}
	public int insertNotice(Connection conn) {
		
		
	}
	public int modifyNotice(Connection conn) {
		
	}
	public int deleteNotice(Connection conn, int noticeNo) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "";
		
		return result;
	}
	public Notice noticeSelect(Connection conn, int noticeNo) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Notice notice = null;
		String query = "";

		
		return notice;
	}
	public ArrayList<Notice> noticeSearchList(Connection conn, int currentPage, int recordCountPerPage, String search) {
		ArrayList<Notice> list = new ArrayList<Notice>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;

		
		return list;
	}
	public int insertNotice(Connection conn, String subject, String content, String userId) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "";
		

		
		return result;
	}
	public int modifyNotice(Connection conn, String subject, String content, int noticeNo) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "UPDATE NOTICE SET SUBJECT=?,CONTENTS=?," + "REGDATE=SYSDATE WHERE NOTICENO=?";

		
		
	}

}
