package projectboard.model.service;

import common.ConnectionFactory;
import filetbl.model.vo.File;
import product.model.vo.Product;
import projectboard.model.dao.ProjectBoardDAO;
import projectboard.model.vo.ProjectBoard;
import projectboard.model.vo.ProjectPageData;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import commentboard.model.vo.Comment;

public class ProjectService {
	private ConnectionFactory factory;

	public ProjectService() {
		factory = ConnectionFactory.getConnection();
	}

	// 프로젝트 삽입
	public int insertProject(ProjectBoard project, String userId, String endDate) {
		Connection conn = null;
		int result = 0;
		try {
			conn = factory.createConnection();
			result = new ProjectBoardDAO().insertProject(conn, userId, project, endDate);
			if (result > 0) {
				factory.commit(conn);
			} else {
				factory.rollback(conn);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	// 프로젝트 No 가져오기
	public int selectProjectNo(String userId, String project_title) {
		Connection conn = null;
		int result = 0;

		try {
			conn = factory.createConnection();
			result = new ProjectBoardDAO().selectProjectNo(conn, userId, project_title);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			factory.close(conn);
		}
		return result;
	}

	// product 삽입
	public int insertProduct(Product product) {
		Connection conn = null;
		int result = 0;
		try {
			conn = factory.createConnection();
			result = new ProjectBoardDAO().insertProduct(conn, product);
			if (result > 0) {
				factory.commit(conn);

			} else {
				factory.rollback(conn);

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			factory.close(conn);
		}
		return result;
	}

	// 프로젝트 모두 출력
	public ProjectPageData selectProjectList(int currentPage) {
		// SELECT * FROM SERVICE;
		Connection conn = null;
		int recordCountPerPage = 20;
		int naviCountPerPage = 5;
		ProjectPageData pd = new ProjectPageData();
		ArrayList<ProjectBoard> projectList = null;
		ArrayList<File> fileList = null;

		try {
			conn = factory.createConnection();
			projectList = new ProjectBoardDAO().selectProjectList(conn, currentPage, recordCountPerPage);
			fileList = new ProjectBoardDAO().selectFileList(conn, currentPage, recordCountPerPage);
			pd.setPageList(projectList);
			pd.setPageNavi(new ProjectBoardDAO().getPageNavi(conn, currentPage, recordCountPerPage, naviCountPerPage));
			pd.setFileList(fileList);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			factory.close(conn);
		}

		return pd;

	}

	// 검색 목록 불러오기
	public ProjectPageData projectSearchList(int currentPage, String search) {
		Connection conn = null;
		int recordCountPerPage = 20;
		int naviCountPerPage = 5;
		ProjectPageData pd = new ProjectPageData();

		try {
			conn = factory.createConnection();
			pd.setPageList(new ProjectBoardDAO().projectSearchList(conn, currentPage, recordCountPerPage, search));
			pd.setPageNavi(new ProjectBoardDAO().getSearchPageNavi(conn, currentPage, recordCountPerPage,
					naviCountPerPage, search));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			factory.close(conn);
		}
		return pd;
	}

	// 프로젝트 내용 보기
	public ProjectBoard projectSelect(int projectNo) {
		Connection conn = null;
		ProjectBoard project = null;
		ArrayList<Comment> projectCmtList = null;
		try {
			conn = factory.createConnection();
			project = new ProjectBoardDAO().projectSelect(conn, projectNo);
			projectCmtList = new ProjectBoardDAO().projectComment(conn, projectNo);
			project.setComments(projectCmtList);
			System.out.println("projectNo:::" + projectNo);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			factory.close(conn);
		}
		return project;
	}

	//
	public Product productOption(int optionNo, int projectNo) {
		Connection conn = null;
		Product option = null;

		try {
			conn = factory.createConnection();
			option = new ProjectBoardDAO().productOption(conn, optionNo, projectNo);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			factory.close(conn);
		}
		return option;
	}

	// 프로젝트 게시글 작성
	public int insertPhoto(File file) {
		Connection conn = null;
		int result = 0;

		try {
			conn = factory.createConnection();
			result = new ProjectBoardDAO().insertPhoto(conn, file);
			if (result > 0) {
				factory.commit(conn);
			} else {
				factory.rollback(conn);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			factory.close(conn);
		}

		return result;
	}

	public ArrayList<File> photoSelect(int projectNo) {
		Connection conn = null;
		ArrayList<File> photoList = new ArrayList<File>();

		try {
			conn = factory.createConnection();
			photoList = new ProjectBoardDAO().photoSelect(conn, projectNo);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			factory.close(conn);
		}
		return photoList;
	}

	// 가격 알아오기
	public int getPrice(int projectNo, int optionNo) {
		Connection conn = null;
		int price = 0;

		try {
			conn = factory.createConnection();
			price = new ProjectBoardDAO().getPrice(conn, projectNo, optionNo);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			factory.close(conn);
		}
		return price;
	}

	// 프로젝트 후원하기
	public int supportProject(int projectNo, int optionNo, String userId, int price) {
		Connection conn = null;
		int result1 = -1;
		int result2 = -1;
		int result3 = -1;
		int result4 = -1;

		try {
			conn = factory.createConnection();
			result1 = new ProjectBoardDAO().updateProject(conn, projectNo, price);
			result2 = new ProjectBoardDAO().updateProduct(conn, optionNo, projectNo);
			result3 = new ProjectBoardDAO().updateMember(conn, userId, price);
			result4 = new ProjectBoardDAO().insertSupport(conn, userId, optionNo, projectNo, price);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			factory.close(conn);
		}

		int result = 1;
		if (result1 < 0 || result2 < 0 || result3 < 0 || result4 < 0) {
			result = -1;
		}
		return result;
	}

	// 프로젝트 삭제 하기
	public int projectDelete(int projectNo) {
		Connection conn = null;
		int result = 0;
		try {
			conn = factory.createConnection();
			result = new ProjectBoardDAO().projectDelete(conn, projectNo);
			if (result > 0) {
				factory.commit(conn);
			} else {
				factory.rollback(conn);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	// 프로젝트 상품 옵션 수정하기
	public int modifyProduct(Product product) {
		Connection conn = null;
		int result = 0;
		try {
			conn = factory.createConnection();
			result = new ProjectBoardDAO().modifyProduct(conn, product);
			if (result > 0) {
				factory.commit(conn);
			} else {
				factory.rollback(conn);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	// 프로젝트 수정
	public int ModifyProject(ProjectBoard project, String userId, int projectNo, String endDate) {
		Connection conn = null;
		int result = 0;
		try {
			conn = factory.createConnection();
			result = new ProjectBoardDAO().modifyProject(conn, userId, project, projectNo, endDate);
			if (result > 0) {
				factory.commit(conn);
			} else {
				factory.rollback(conn);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			factory.close(conn);
		}
		return result;
	}

	// 프로젝트 사진 파일 수정
	public int ModifyPhoto(File file) {
		Connection conn = null;
		int result = 0;

		try {
			conn = factory.createConnection();
			result = new ProjectBoardDAO().modifyPhoto(conn, file);
			if (result > 0) {
				factory.commit(conn);
			} else {
				factory.rollback(conn);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			factory.close(conn);
		}
		return result;
	}

	// 댓글 삽입
	public int insertProjectComment(int projectNo, String content, String userId) {
		int result = 0;
		Connection conn = null;
		try {
			conn = factory.createConnection();
			result = new ProjectBoardDAO().insertProjectComment(conn, projectNo, content, userId);
			if (result > 0) {
				factory.commit(conn);
			} else {
				factory.rollback(conn);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			factory.close(conn);
		}
		return result;
	}

	// 댓글 수정
	public int updateProjectComment(int commentNo, int projectNo, String content) {
		int result = 0;
		Connection conn = null;

		try {
			conn = factory.createConnection();
			result = new ProjectBoardDAO().updateProjectComment(conn, commentNo, projectNo, content);
			if (result > 0) {
				factory.commit(conn);
			} else {
				factory.rollback(conn);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			factory.close(conn);
		}
		return result;
	}

	// 댓글 삭제
	public int deleteProjectComment(int commentNo) {
		int result = 0;
		Connection conn = null;
		try {
			conn = factory.createConnection();
			result = new ProjectBoardDAO().deleteProjectComment(conn, commentNo);
			if (result > 0) {
				factory.commit(conn);
			} else {
				factory.rollback(conn);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			factory.close(conn);
		}
		return result;
	}

	// 베스트 창작자
	public ArrayList<ProjectBoard> selectBestCreatorList() {
		Connection conn = null;
		ArrayList<ProjectBoard> list = null;

		try {
			conn = factory.createConnection();
			list = new ProjectBoardDAO().selectBestCreatorList(conn);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			factory.close(conn);
		}
		return list;
	}

	// 프로젝트 리스트 카테고리별 정렬 결과

	// 카테고리 리스트 불러오기 - Art (예술)
	public ProjectPageData selectProjectArtList(int currentPage) {
		// SELECT * FROM SERVICE;
		Connection conn = null;
		int recordCountPerPage = 20;
		int naviCountPerPage = 5;
		ProjectPageData pd = new ProjectPageData();
		ArrayList<ProjectBoard> projectList = null;
		ArrayList<File> fileList = null;
		try {
			conn = factory.createConnection();
			projectList = new ProjectBoardDAO().selectProjectArtList(conn, currentPage, recordCountPerPage);
			fileList = new ProjectBoardDAO().selectFileList(conn, currentPage, recordCountPerPage);
			pd.setPageList(projectList);
			pd.setPageNavi(new ProjectBoardDAO().getPageNavi(conn, currentPage, recordCountPerPage, naviCountPerPage));
			pd.setFileList(fileList);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			factory.close(conn);
		}
		return pd;
	}

	// 카테고리 리스트 불러오기 - Concert (공연)
	public ProjectPageData selectProjectVideotList(int currentPage) {
		// SELECT * FROM SERVICE;
		Connection conn = null;
		int recordCountPerPage = 20;
		int naviCountPerPage = 5;
		ProjectPageData pd = new ProjectPageData();
		ArrayList<ProjectBoard> projectList = null;
		ArrayList<File> fileList = null;
		try {
			conn = factory.createConnection();
			projectList = new ProjectBoardDAO().selectProjectConcertList(conn, currentPage, recordCountPerPage);
			fileList = new ProjectBoardDAO().selectFileList(conn, currentPage, recordCountPerPage);
			pd.setPageList(projectList);
			pd.setPageNavi(new ProjectBoardDAO().getPageNavi(conn, currentPage, recordCountPerPage, naviCountPerPage));
			pd.setFileList(fileList);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			factory.close(conn);
		}
		return pd;
	}

	// 카테고리 리스트 불러오기 - Publishing (출판)
	public ProjectPageData selectProjectPublishingList(int currentPage) {
		// SELECT * FROM SERVICE;
		Connection conn = null;
		int recordCountPerPage = 20;
		int naviCountPerPage = 5;
		ProjectPageData pd = new ProjectPageData();
		ArrayList<ProjectBoard> projectList = null;
		ArrayList<File> fileList = null;
		try {
			conn = factory.createConnection();
			projectList = new ProjectBoardDAO().selectProjectPublishingList(conn, currentPage, recordCountPerPage);
			fileList = new ProjectBoardDAO().selectFileList(conn, currentPage, recordCountPerPage);
			pd.setPageList(projectList);
			pd.setPageNavi(new ProjectBoardDAO().getPageNavi(conn, currentPage, recordCountPerPage, naviCountPerPage));
			pd.setFileList(fileList);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			factory.close(conn);
		}
		return pd;
	}

	// 카테고리 리스트 불러오기 - Game (게임)
	public ProjectPageData selectProjectGameList(int currentPage) {
		// SELECT * FROM SERVICE;
		Connection conn = null;
		int recordCountPerPage = 20;
		int naviCountPerPage = 5;
		ProjectPageData pd = new ProjectPageData();
		ArrayList<ProjectBoard> projectList = null;
		ArrayList<File> fileList = null;
		try {
			conn = factory.createConnection();
			projectList = new ProjectBoardDAO().selectProjectGameList(conn, currentPage, recordCountPerPage);
			fileList = new ProjectBoardDAO().selectFileList(conn, currentPage, recordCountPerPage);
			pd.setPageList(projectList);
			pd.setPageNavi(new ProjectBoardDAO().getPageNavi(conn, currentPage, recordCountPerPage, naviCountPerPage));
			pd.setFileList(fileList);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			factory.close(conn);
		}
		return pd;
	}

	// 카테고리 리스트 불러오기 - Other (기타)
	public ProjectPageData selectProjectOtherList(int currentPage) {
		// SELECT * FROM SERVICE;
		Connection conn = null;
		int recordCountPerPage = 20;
		int naviCountPerPage = 5;
		ProjectPageData pd = new ProjectPageData();
		ArrayList<ProjectBoard> projectList = null;
		ArrayList<File> fileList = null;
		try {
			conn = factory.createConnection();
			projectList = new ProjectBoardDAO().selectProjectOtherList(conn, currentPage, recordCountPerPage);
			fileList = new ProjectBoardDAO().selectFileList(conn, currentPage, recordCountPerPage);
			pd.setPageList(projectList);
			pd.setPageNavi(new ProjectBoardDAO().getPageNavi(conn, currentPage, recordCountPerPage, naviCountPerPage));
			pd.setFileList(fileList);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			factory.close(conn);
		}
		return pd;
	}

	// 추천 프로젝트(인기순, 달성률이 높은 순으로) 가져오기 - 달성률 상위 2개
	// 카테고리별로, 달성률 상위 3개 프로젝트 가져오기 - 매개변수 : 카테고리
	public ProjectPageData recommendCategoryProject(String category) {
		Connection conn = null;
		ProjectPageData pd = new ProjectPageData();
		ArrayList<ProjectBoard> projectList = null;
		ArrayList<File> fileList = null;
		try {
			conn = factory.createConnection();
			projectList = new ProjectBoardDAO().recommendCategoryProject(conn, category);
			fileList = new ArrayList<File>();
			for (int i = 0; i < projectList.size(); i++) {
				File photo = new ProjectBoardDAO().selectRepImage(conn, projectList.get(i).getProjectNo());
				fileList.add(photo);
			}
			pd.setPageList(projectList);
			pd.setFileList(fileList);
//			System.out.println("추천 fileList.get(0).getFilePath():"+fileList.get(0).getFilePath());
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			factory.close(conn);
		}
		return pd;
	}

	// 최신 등록된 프로젝트 리스트 가져오기 - 최신 등록 상위 6개
	public ProjectPageData latestProject() {
		Connection conn = null;
		ProjectPageData pd = new ProjectPageData();
		ArrayList<ProjectBoard> projectList = null;
		ArrayList<File> fileList = null;
		try {
			conn = factory.createConnection();
			projectList = new ProjectBoardDAO().latestProject(conn);
			fileList = new ArrayList<File>();
			// 가져온 프로젝트 갯수만큼 반복하여 해당 프로젝트에 해당하는 파일 정보를 받아온다
			for (int i = 0; i < projectList.size(); i++) {
				File photo = new ProjectBoardDAO().selectRepImage(conn, projectList.get(i).getProjectNo());
				fileList.add(photo);
			}
			pd.setPageList(projectList);
			pd.setFileList(fileList);
//			System.out.println("최신 fileList.get(0).getFilePath():"+fileList.get(0).getFilePath());
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			factory.close(conn);
		}
		return pd;
	}
}
