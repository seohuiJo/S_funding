package requestboard.model.service;

import common.ConnectionFactory;
import requestboard.model.vo.RequestBoard;
import requestboard.model.vo.RequestPageData;

public class RequestService {
	private ConnectionFactory factory;
	
	public RequestService() {
		factory=ConnectionFactory.getConnection();
	}
	
	public RequestPageData requestSelectList(int currentPage) {
	}
	
	public RequestPageData requestSearchList(int currentPage, String search) {
		
	}
	
	public int insertRequest(String userId, String requestTitle, String requestContent) {
		
	}
	
	public RequestBoard requestSelect(int requestNo) {
		
	}
	
	public int deleteRequest(int requestNo) {
		
	}
	
	public int addLike(int requestNo) {
		
	}
	
	public int addDislike(int requestNo) {
		
	}
}














