package comment.model.dao;

import java.sql.Connection;
import java.util.ArrayList;

import comment.model.vo.Comment;
import comment.model.vo.CommentPageData;

public class CommentDAO {
	//댓글 생성
	public int insertComment(Connection conn, String userId, int projectNo, String content, String boardType) {
		return 0;
	}
	
	//댓글 수정
	public int modifyComment(Connection conn, int commentNo, String content) {
		return 0;
	}
	
	// 댓글 삭제
	public int deleteComment(Connection conn, int commentNo) {
		return 0;
	}
	
	// 댓글 검색
	public CommentPageData searchComment(Connection conn, int currentPage, String search) {
		return null;

	}
	
	// 댓글 한개 보여줌
	public Comment selectComment(Connection conn, int commentNo) {
		return null;

	}
	
	// 댓글 리스트로 가져와서 보여줌
	public CommentPageData selectCommentList(Connection conn, int currentPage, String search) {
		return null;
		
	}
}