package notice.model.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

import common.ConnectionFactory;
import notice.model.dao.NoticeDAO;
import notice.model.vo.Notice;
import notice.model.vo.NoticeComment;
import notice.model.vo.PageData;

public class NoticeService {
	
	private ConnectionFactory factory;
	
	public NoticeService() {
		factory=ConnectionFactory.getConnection();
	}
	
	public PageData selectNoticeList(int currentPage){
		// SELECT* FROM NOTICE;
		Connection conn=null;
		int recordCountPerPage=10;
		int naviCountPerPage=5;
		PageData pd=new PageData();
		ArrayList<Notice> noticeList=null;
		
		try {
			conn=factory.createConnection();
			noticeList=new NoticeDAO().selectNoticeList(conn, currentPage, recordCountPerPage);
			pd.setPageList(noticeList);
			pd.setPageNavi(new NoticeDAO().getPageNavi(conn, currentPage, recordCountPerPage, naviCountPerPage));
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		return pd;
	}
<<<<<<< HEAD
<<<<<<< HEAD
	public PageData noticeSearchList() {
=======
<<<<<<< HEAD
	public PageData noticeSearchList() {
=======
	public CommentPageData noticeSearchList(int currentPage,String search) {
		Connection conn =null;
		int recordCountPerPage=10;
		int naviCountPerPage=5;
		CommentPageData pd= new CommentPageData();
>>>>>>> 29a65614bee92ca0220ebfbff8affea36775474c
>>>>>>> 68abd591b980428e768e162a92f858c5cde0f44a
=======
	
	public PageData noticeSearchList(int currentPage, String search) {
		Connection conn=null;
		int recordCountPerPage=10;
		int naviCountPerPage=5;
		PageData pd=new PageData();
>>>>>>> df9685b28cfc014ed2ccf48bdf7c94ace48ebb10
		
		try {
			conn=factory.createConnection();
			pd.setPageList(new NoticeDAO().noticeSearchList(conn, currentPage, recordCountPerPage, search));
			pd.setPageNavi(new NoticeDAO().getSearchPageNavi(conn, currentPage, recordCountPerPage, naviCountPerPage, search));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return pd;
	}

	public int insertNotice(String subject, String content, String userId) {
		Connection conn=null;
		int result=0;
		try {
			conn=factory.createConnection();
			result=new NoticeDAO().insertNotice(conn, subject,content, userId);
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
	
	public int insertNotice(Connection conn, String subject, String content, String userId) {
		PreparedStatement pstmt=null;
		int result=0;
		String query="INSERT INTO NOTICE VALUES(SEQ_NOTICE.NEXTVAL, ?, ?, ?, SYSDATE)";
		
		try {
			pstmt=conn.prepareStatement(query);
			pstmt.setString(1,  subject);
			pstmt.setString(2,  content);
			pstmt.setString(3,  userId);
			result=pstmt.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	public Notice noticeSelect(int noticeNo) {
		Connection conn=null;
		Notice notice=null;
		ArrayList<NoticeComment> cmtList=null;
		
		try {
			conn=factory.createConnection();
			notice=new NoticeDAO().noticeSelect(conn, noticeNo);
			cmtList=new NoticeDAO().noticeComment(conn, noticeNo);
			notice.setComments(cmtList);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return notice;
	}
	
	public int modifyNotice(String subject, String content, int noticeNo) {
		Connection conn=null;
		int result=0;
		try {
			conn=factory.createConnection();
			result=new NoticeDAO().modifyNotice(conn, subject, content, noticeNo);
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
	
	public int deleteNotice(int noticeNo) {
		Connection conn=null;
		int result=0;
		
		try {
			conn=factory.createConnection();
			result=new NoticeDAO().deleteNotice(conn, noticeNo);
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
	
	public int insertNoticeComment(String comment, String userId, int noticeNo) {
		Connection conn=null;
		int result=0;
		try {
			conn=factory.createConnection();
			result=new NoticeDAO().insertNoticeComment(conn, comment, userId, noticeNo);
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
	
	public int modifyNoticeComment(int commentNo, int noticeNo, String content) {
		Connection conn=null;
		int result=0;
		try {
			conn=factory.createConnection();
			result=new NoticeDAO().modifyNoticeComment(conn, commentNo, noticeNo, content);
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
	
	public int deleteNoticeComment(int commentNo) {
		Connection conn=null;
		int result=0;
		
		try {
			conn=factory.createConnection();
			result=new NoticeDAO().deleteNoticeComment(conn, commentNo);
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
















