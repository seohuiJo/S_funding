package projectboard.model.dao;

import java.sql.Connection;
import java.sql.Date;
import java.util.ArrayList;

public class ProjectBoardDAO {
	//프로젝트 게시글 작성
	public int writeProject(Connection connection, String userId, String projectTitle, String projectContent, String category, Date startDate, Date endDate) {
		return 0;
	}
	
	// 프로젝트 삭제
	public int deleteProject(Connection connection, int projectNo) {
		return 0;
	}
	
	//프로젝트 검색
	public MemberPageData searchProject(Connection connection, int currentPage, String search) {
		return 0;
	}
	
	// 프로젝트 select
	public MemberPageData selectProject(Connection connection, int currentPage) {
		return 0;
	}
	
	// 추천 프로젝트 list로 가져와서 보기
	public ArrayList<ProjectBoard> recommendProject(Connection connection, String category) {
		return null;
	
	}
}
