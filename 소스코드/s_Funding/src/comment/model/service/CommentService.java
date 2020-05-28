package comment.model.service;

import common.ConnectionFactory;
import java.util.ArrayList;

import comment.model.vo.Comment;
import comment.model.vo.CommentPageData;

public class CommentService {
	private ConnectionFactory factory;
	
	public CommentService() {
		factory=ConnectionFactory.getConnection();
	}
	
	//댓글 생성
	public int insertComment(String userId, int projectNo, String content, String boardType) {
		return 0;
	}
	
	//댓글 수정
	public int modifyComment(int commentNo, String content) {
		return 0;
	}
	
	// 댓글 삭제
	public int deleteComment(int commentNo) {
		return 0;
	}
	
	// 댓글 검색
	public CommentPageData searchComment(int currentPage, String search) {
		return null;
	}
	
	// 댓글 한개 보여줌
	public Comment selectComment(int commentNo) {
		return null;
	}
	
	// 댓글 리스트로 가져와서 보여줌
	public CommentPageData selectCommentList(int currentPage, String search) {
		return null;
		
	}
	
}
