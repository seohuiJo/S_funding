package requestboard.model.dao;

import common.ConnectionFactory;
import requestboard.model.vo.RequestBoard;
import requestboard.model.vo.RequestPageData;

public class RequestDAO {
	public RequestPageData requestSelectList(Connection conn, int currentPage) {
		
	}
	
	public RequestPageData requestSearchList(Connection conn, int currentPage, String search) {
		
	}
	
	public int insertRequest(Connection conn, String userId, String requestTitle, String requestContent) {
		
	}
	
	public RequestBoard requestSelect(Connection conn, int requestNo) {
		
	}
	
	public int deleteRequest(Connection conn, int requestNo) {
		
	}
	
	public int addLike(Connection conn, int requestNo) {
		
	}
	
	public int addDislike(Connection conn, int requestNo) {
		
	}
}
