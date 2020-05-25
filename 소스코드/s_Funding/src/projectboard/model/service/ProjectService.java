package projectboard.model.service;

import common.ConnectionFactory;

import java.sql.Date;
import java.util.ArrayList;

public class ProjectService {
	private ConnectionFactory factory;
	
	public ProjectService() {
		factory=ConnectionFactory.getConnection();
	}
	
	//프로젝트 게시글 작성
	public int writeProject(String userId, String projectTitle, String projectContent, String category, Date startDate, Date endDate) {
		return 0;
	}
	
	// 프로젝트 삭제
	public int deleteProject(int projectNo) {
		return 0;
	}
	
	//프로젝트 검색
	public ProjectPageData searchProject(int currentPage, String search) {
		return 0;
	}
	
	// 프로젝트 select
	public ProjectPageData selectProject(int currentPage) {
		return 0;
	}
	
	// 추천 프로젝트 list로 가져와서 보기
	public ArrayList<ProjectBoard> recommendProject(String category) {
		return null;
	
	}
}
