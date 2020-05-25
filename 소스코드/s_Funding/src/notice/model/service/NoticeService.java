package notice.model.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import notice.model.dao.NoticeDAO;
import notice.model.vo.Notice;
import notice.model.vo.NoticeComment;
import notice.model.vo.PageData;

public class NoticeService {
	
	public int deleteNotice(int noticeNo) {
		Connection conn=null;
		int result=0;
		
		return result;
	}
	public Notice noticeSelect(int noticeNo) {
		Connection conn=null;
		Notice notice=null;
		ArrayList<NoticeComment> cmList=null;
		
		return cmList;
	}
	public PageData noticeSearchList(int currentPage,String search) {
		Connection conn =null;
		
		return pd;
	}
	
	
	public int insertNotice(String subject,String content,String userId) {
		Connection conn=null;
		int result=0;
		
		return result;
	}
	public int modifyNotice(String subject,String content,int noticeNo) {
		Connection conn =null;
		int result=0;
		
		return result;
	}

}
