package notice.model.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import notice.model.dao.NoticeDAO;
import notice.model.vo.Notice;
import notice.model.vo.NoticeComment;
import notice.model.vo.PageData;

public class NoticeService {
	/* dd */
	
	public int deleteNotice() {
		
	}
	public Notice noticeSelect() {
		
	}
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
		
	}
	
	
	public int insertNotice() {
		
	}
	public int modifyNotice() {
		

}
