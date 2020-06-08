package projectboard.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import commentboard.model.vo.Comment;
import common.ConnectionFactory;
import filetbl.model.vo.File;
import product.model.vo.Product;
import projectboard.model.vo.ProjectBoard;

public class ProjectBoardDAO {

	// 프로젝트 삽입
	public int insertProject(Connection conn, String userId, ProjectBoard project, String endDate) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int result = 0;
		String query = "insert into project_board values(project_seq.nextval,?,?,?,?,sysdate,?,0,0,?,sysdate)";

		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, userId);
			pstmt.setString(2, project.getProjectTitle());
			pstmt.setString(3, project.getProjectContent());
			pstmt.setString(4, project.getCategory());
			pstmt.setString(5, endDate);
			pstmt.setInt(6, project.getTargetMoney());
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			ConnectionFactory.close(pstmt);
			ConnectionFactory.close(rset);
		}
		return result;
	}

	//
	public int selectProjectNo(Connection conn, String userId, String project_title) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int result = 0;
		String query = "select project_no from project_board where user_id=? and project_title = ?";

		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, userId);
			pstmt.setString(2, project_title);

			rset = pstmt.executeQuery();
			if (rset.next()) {
				result = rset.getInt("project_no");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			ConnectionFactory.close(pstmt);
			ConnectionFactory.close(rset);
		}
		return result;
	}

	// product 삽입
	public int insertProduct(Connection conn, Product product) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "insert into product values(?,?,?,?,0,?)";

		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, product.getOptionNo());
			pstmt.setInt(2, product.getProjectNo());
			pstmt.setString(3, product.getProductName());
			pstmt.setString(4, product.getOption());
			pstmt.setInt(5, product.getPrice());
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			ConnectionFactory.close(pstmt);
		}
		return result;
	}

	// 프로젝트 게시글 작성
	public int insertPhoto(Connection conn, File file) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "INSERT INTO FILETBL VALUES(FILE_SEQ.NEXTVAL, ?,?,?,?)";

		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, file.getFileName());
			pstmt.setString(2, file.getFilePath());
			pstmt.setString(3, file.getUserId());
			pstmt.setInt(4, file.getProjectNo());
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			ConnectionFactory.close(pstmt);
		}

		return result;
	}

	// 프로젝트 리스트불러오기
	public ArrayList<ProjectBoard> selectProjectList(Connection conn, int currentPage, int recordCountPerPage) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<ProjectBoard> nList = new ArrayList<ProjectBoard>();

		String query = "SELECT * FROM(SELECT PROJECT_BOARD.*, ROW_NUMBER() OVER(ORDER BY PROJECT_NO DESC) AS NUM FROM PROJECT_BOARD) WHERE NUM BETWEEN ? AND ?";

		int start = currentPage * recordCountPerPage - (recordCountPerPage - 1);
		int end = currentPage * recordCountPerPage;
		try {
			// stmt=conn.createStatement();
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, start);
			pstmt.setInt(2, end);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				ProjectBoard project = new ProjectBoard();
//				File image = new File();
				project.setProjectNo(rset.getInt("PROJECT_NO"));
				project.setUserId(rset.getString("USER_ID"));
				project.setProjectTitle(rset.getString("PROJECT_TITLE"));
				project.setProjectContent(rset.getString("PROJECT_CONTENT"));
				project.setCategory(rset.getString("CATEGORY"));
				project.setStartDate(rset.getDate("START_DATE"));
				project.setEndDate(rset.getDate("END_DATe"));
				project.setSponsorCount(rset.getInt("SPONSOR_COUNT"));
				project.setSumMoney(rset.getInt("SUM_MONEY"));
				project.setTargetMoney(rset.getInt("TARGET_MONEY"));
				float rate = ((float) project.getSumMoney() / project.getTargetMoney()) * 100;
				rate = (float) (Math.round(rate * 100) / 100.0);
				project.setRate(rate);
				project.setpRegdate(rset.getDate("P_REGDATE"));
				project.setImage(selectRepImage(conn, project.getProjectNo()));
				nList.add(project);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			ConnectionFactory.close(pstmt);
			ConnectionFactory.close(rset);
		}
		return nList;
	}

	// 대표사진 1개 불러오기
	public File selectRepImage(Connection conn, int projectNo) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		File file = new File();

		String query = "SELECT * FROM(SELECT FILETBL.*, ROW_NUMBER() OVER(ORDER BY FILE_NO) AS NUM FROM FILETBL) WHERE PROJECT_NO = ? AND MOD(NUM,3)=1";

		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, projectNo);
			rset = pstmt.executeQuery();
			if (rset.next()) {
				file.setFileNo(rset.getInt("FILE_NO"));
				file.setFileName(rset.getString("FILENAME"));
				file.setFilePath(rset.getString("FILEPATH"));
				file.setUserId(rset.getString("USER_ID"));
				file.setProjectNo(rset.getInt("PROJECT_NO"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			ConnectionFactory.close(pstmt);
			ConnectionFactory.close(rset);
		}
		return file;
	}

	// fileList 가져오기
	public ArrayList<File> selectFileList(Connection conn, int currentPage, int recordCountPerPage) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<File> nList = new ArrayList<File>();

		String query = "SELECT * FROM(SELECT FILETBL.*, ROW_NUMBER() OVER(ORDER BY FILE_NO) AS NUM FROM FILETBL) WHERE NUM BETWEEN ? AND ?";

		int start = currentPage * recordCountPerPage - (recordCountPerPage - 1);
		int end = currentPage * recordCountPerPage;
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, start);
			pstmt.setInt(2, end);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				File file = new File();
				file.setFileNo(rset.getInt("FILE_NO"));
				file.setFileName(rset.getString("FILENAME"));
				file.setFilePath(rset.getString("FILEPATH"));
				file.setUserId(rset.getString("USER_ID"));
				file.setProjectNo(rset.getInt("PROJECT_NO"));
				nList.add(file);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			ConnectionFactory.close(pstmt);
			ConnectionFactory.close(rset);
		}
		return nList;
	}

	// Project 게시글 총 갯수 세기
	public int totalCount(Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int recordTotalCount = 0;

		// 게시글 총 갯수를 알아오는 쿼리
		String query = "SELECT COUNT(*) AS TOTALCOUNT FROM PROJECT_BOARD";

		try {
			pstmt = conn.prepareStatement(query);
			rset = pstmt.executeQuery();
			if (rset.next()) {
				recordTotalCount = rset.getInt("TOTALCOUNT");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			ConnectionFactory.close(pstmt);
			ConnectionFactory.close(rset);
		}

		return recordTotalCount;
	}

	// ServicePageNavi 가져오기
	public String getPageNavi(Connection conn, int currentPage, int recordCountPerPage, int naviCountPerPage) {
		int recordTotalCount = totalCount(conn);
		int pageTotalCount = 0; // 전체 페이지의 갯수
		// 전체 게시물 갯수 124개, 10개씩 페이지 만들면 페이지 갯수는 13개

		if (recordTotalCount % recordCountPerPage > 0) {
			pageTotalCount = recordTotalCount / recordCountPerPage + 1;
		} else {
			pageTotalCount = recordTotalCount / recordCountPerPage;
		}

		// 현재 페이지를 기준으로 네비게이션을 구해야 하므로
		// 현재 페이지 정보를 확인해서 0보다는 크고 전체 페이지 수보다는 작은 위치에 있는지 확인(오류방지)
		if (currentPage < 1) {
			currentPage = 1;
		} else if (currentPage > pageTotalCount) {
			currentPage = pageTotalCount;
		}

		int startNavi = ((currentPage - 1) / naviCountPerPage) * naviCountPerPage + 1;
		// currentPage=8, naviCountPerPage=5
		// ((8-1)/5)*5+1 => 6
		// currentPage=42, naviCountPerPage=5
		// 41 42 43 44 45
		// ((42-1)/5)*5+1 => 41
		int endNavi = startNavi + naviCountPerPage - 1;

		if (endNavi > pageTotalCount) {
			endNavi = pageTotalCount;
		}

		// '<' 모양과 '>' 모양을 준비하기 위해 필요한 변수
		boolean needPrev = true;
		boolean needNext = true;
		if (startNavi == 1) {
			needPrev = false;
		}
		if (endNavi == pageTotalCount) {
			needNext = false;
		}

		// 모든 준비 끝남
		StringBuilder sb = new StringBuilder();
		sb.append("<nav aria-label='Page navigation example'>");
		sb.append("<ul class='pagination justify-content-center'>");
		if (needPrev) {
			sb.append(
					"<li class='page-item'><span class='page-link' style='color: black;'><a href='/project?currentPage="
							+ (startNavi - 1) + "'>&lt; </a></span></li>");
		}
		for (int i = startNavi; i <= endNavi; i++) {
			if (i == currentPage) {
				sb.append(
						"<li class='page-item'><span class='page-link' style='color: black;'><a href='/project?currentPage="
								+ i + "'><b>" + i + "</b></a></span></li>");
			} else {
				sb.append(
						"<li class='page-item'><span class='page-link' style='color: black;'><a href='/project?currentPage="
								+ i + "'>" + i + "</a></span></li>");
			}
		}
		if (needNext) {
			sb.append(
					"<li class='page-item'><span class='page-link' style='color: black;'><a href='project?currentPage="
							+ (endNavi + 1) + "'> &gt;</a></span></li>");
		}
		sb.append("</ul></nav>");
		return sb.toString();
	}

	// searchList 가져오기
	public ArrayList<ProjectBoard> projectSearchList(Connection conn, int currentPage, int recordCountPerPage,
			String search) {
		ArrayList<ProjectBoard> list = new ArrayList<ProjectBoard>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;

		int start = currentPage * recordCountPerPage - (recordCountPerPage - 1);
		int end = currentPage * recordCountPerPage;

		String query = "SELECT * FROM (SELECT PROJECT_BOARD.*, ROW_NUMBER() OVER(ORDER BY PROJECT_NO DESC) AS NUM FROM PROJECT_BOARD WHERE PROJECT_TITLE LIKE '%"
				+ search + "%')" + "WHERE NUM BETWEEN ? AND ?";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, start);
			pstmt.setInt(2, end);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				ProjectBoard project = new ProjectBoard();
				project.setProjectNo(rset.getInt("PROJECT_NO"));
				project.setUserId(rset.getString("USER_ID"));
				project.setProjectTitle(rset.getString("PROJECT_TITLE"));
				project.setProjectContent(rset.getString("PROJECT_CONTENT"));
				project.setCategory(rset.getString("CATEGORY"));
				project.setStartDate(rset.getDate("START_DATE"));
				project.setEndDate(rset.getDate("END_DATe"));
				project.setSponsorCount(rset.getInt("SPONSOR_COUNT"));
				project.setSumMoney(rset.getInt("SUM_MONEY"));
				project.setTargetMoney(rset.getInt("TARGET_MONEY"));
				float rate = ((float) project.getSumMoney() / project.getTargetMoney()) * 100;
				rate = (float) (Math.round(rate * 100) / 100.0);
				project.setRate(rate);
				project.setpRegdate(rset.getDate("P_REGDATE"));
				project.setImage(selectRepImage(conn, project.getProjectNo()));
				list.add(project);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			ConnectionFactory.close(pstmt);
			ConnectionFactory.close(rset);
		}

		return list;
	}

	// search한 게시글 갯수 세기
	public int searchTotalCount(Connection conn, String search) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int recordTotalCount = 0;
		String query = "SELECT COUNT(*) AS TOTALCOUNT FROM PROJECT_BOARD WHERE PROJECT_TITLE LIKE '%" + search + "%'";
		try {
			pstmt = conn.prepareStatement(query);
			rset = pstmt.executeQuery();
			if (rset.next()) {
				recordTotalCount = rset.getInt("TOTALCOUNT");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			ConnectionFactory.close(pstmt);
			ConnectionFactory.close(rset);
		}

		return recordTotalCount;
	}

	// SearchPageNavi 가져오기
	public String getSearchPageNavi(Connection conn, int currentPage, int recordCountPerPage, int naviCountPerPage,
			String search) {
		int recordTotalCount = searchTotalCount(conn, search);
		int pageTotalCount = 0;

		if (recordTotalCount % recordCountPerPage > 0) {
			pageTotalCount = recordTotalCount / recordCountPerPage + 1;
		} else {
			pageTotalCount = recordTotalCount / recordCountPerPage;
		}

		if (currentPage < 1) {
			currentPage = 1;
		} else if (currentPage > pageTotalCount) {
			currentPage = pageTotalCount;
		}

		int startNavi = ((currentPage - 1) / naviCountPerPage) * naviCountPerPage + 1;
		int endNavi = startNavi + naviCountPerPage - 1;

		if (endNavi > pageTotalCount) {
			endNavi = pageTotalCount;
		}

		boolean needPrev = true;
		boolean needNext = true;
		if (startNavi == 1) {
			needPrev = false;
		}
		if (endNavi == pageTotalCount) {
			needNext = false;
		}
		StringBuilder sb = new StringBuilder();
		sb.append("<nav aria-label='Page navigation example'>");
		sb.append("<ul class='pagination justify-content-center'>");
		if (needPrev) {
			sb.append(
					"<li class='page-item'><span class='page-link' style='color: black;'><a href='/projectSearch?search="
							+ search + "&currentPage=" + (startNavi - 1) + "'> &lt;</a></span></li>");
		}
		for (int i = startNavi; i <= endNavi; i++) {
			if (i == currentPage) {
				sb.append(
						"<li class='page-item'><span class='page-link' style='color: black;'><a href='/projectSearch?search="
								+ search + "&currentPage=" + i + "'><b>" + i + "</b></a></span></li>");
			} else {
				sb.append(
						"<li class='page-item'><span class='page-link' style='color: black;'><a href='/projectSearch?search="
								+ search + "&currentPage=" + i + "'>" + i + "</a></span></li>");
			}
		}
		if (needNext) {
			sb.append(
					"<li class='page-item'><span class='page-link' style='color: black;'><a href='/projectSearch?search="
							+ search + "&currentPage=" + (endNavi + 1) + "'>&gt;</a></span></li>");
		}
		sb.append("</ul></nav>");
		return sb.toString();
	}

	public ProjectBoard projectSelect(Connection conn, int projectNo) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ProjectBoard project = null;
		String query = "SELECT * FROM PROJECT_BOARD WHERE PROJECT_NO = ?";

		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, projectNo);
			rset = pstmt.executeQuery();

			if (rset.next()) {
				project = new ProjectBoard();
				project.setProjectNo(rset.getInt("PROJECT_NO"));
				project.setUserId(rset.getString("USER_ID"));
				project.setProjectTitle(rset.getString("PROJECT_TITLE"));
				project.setProjectContent(rset.getString("PROJECT_CONTENT"));
				project.setCategory(rset.getString("CATEGORY"));
				project.setStartDate(rset.getDate("START_DATE"));
				project.setEndDate(rset.getDate("END_DATE"));
				project.setSponsorCount(rset.getInt("SPONSOR_COUNT"));
				project.setSumMoney(rset.getInt("SUM_MONEY"));
				project.setTargetMoney(rset.getInt("TARGET_MONEY"));
				project.setpRegdate(rset.getDate("P_REGDATE"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return project;
	}

	// option 가져오기
	public Product productOption(Connection conn, int optionNo, int projectNo) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Product option = null;
		String query = "SELECT * FROM PRODUCT WHERE OPTION_NO = ? AND PROJECT_NO = ?";

		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, optionNo);
			pstmt.setInt(2, projectNo);
			rset = pstmt.executeQuery();

			if (rset.next()) {
				option = new Product();
				option.setOptionNo(rset.getInt("OPTION_NO"));
				option.setProjectNo(rset.getInt("PROJECT_NO"));
				option.setProductName(rset.getString("PRODUCT_NAME"));
				option.setOption(rset.getString("PRODUCT_OPTION"));
				option.setSponsorCount(rset.getInt("SPONSOR_COUNT"));
				option.setPrice(rset.getInt("PRICE"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			ConnectionFactory.close(pstmt);
			ConnectionFactory.close(rset);
		}
		return option;
	}

	// photo 가져오기
	public ArrayList<File> photoSelect(Connection conn, int projectNo) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<File> photoList = new ArrayList<File>();
		String query = "SELECT * FROM FILETBL WHERE PROJECT_NO=? ORDER BY FILE_NO";

		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, projectNo);
			rset = pstmt.executeQuery();

			while (rset.next()) {
				File photo = new File();
				photo.setFileNo(rset.getInt("FILE_NO"));
				photo.setFileName(rset.getString("FILENAME"));
				photo.setFilePath(rset.getString("FILEPATH"));
				photo.setUserId(rset.getString("USER_ID"));
				photo.setProjectNo(rset.getInt("PROJECT_NO"));
				photoList.add(photo);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			ConnectionFactory.close(pstmt);
			ConnectionFactory.close(rset);
		}
		return photoList;
	}

	// 가격 불러오기
	public int getPrice(Connection conn, int projectNo, int optionNo) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int price = 0;
		String query = "SELECT PRICE FROM PRODUCT WHERE OPTION_NO=? AND PROJECT_NO=?";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, optionNo);
			pstmt.setInt(2, projectNo);
			rset = pstmt.executeQuery();
			if (rset.next()) {
				price = rset.getInt("PRICE");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			ConnectionFactory.close(pstmt);
			ConnectionFactory.close(rset);
		}

		return price;
	}

	// 후원 후 project update
	public int updateProject(Connection conn, int projectNo, int price) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "UPDATE PROJECT_BOARD SET SPONSOR_COUNT=SPONSOR_COUNT+1, SUM_MONEY=SUM_MONEY+? WHERE PROJECT_NO=?";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, price);
			pstmt.setInt(2, projectNo);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			ConnectionFactory.close(pstmt);
		}

		return result;
	}

	// 후원 후 product update
	public int updateProduct(Connection conn, int optionNo, int projectNo) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "UPDATE PRODUCT SET SPONSOR_COUNT=SPONSOR_COUNT+1 WHERE OPTION_NO=? AND PROJECT_NO=?";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, optionNo);
			pstmt.setInt(2, projectNo);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			ConnectionFactory.close(pstmt);
		}

		return result;
	}

	// 후원 후 member update
	public int updateMember(Connection conn, String userId, int price) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "UPDATE MEMBER SET POINT=POINT-? WHERE USER_ID=?";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, price);
			pstmt.setString(2, userId);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			ConnectionFactory.close(pstmt);
		}

		return result;
	}

	// 후원자 목록 테이블에 추가
	public int insertSupport(Connection conn, String userId, int optionNo, int projectNo, int price) {
		PreparedStatement pstmt = null;
		int result = 0;
		System.out.println(userId + " " + optionNo + " " + projectNo + " " + price);
		String query = "INSERT INTO SUPPORTLIST VALUES(?,?,?,?,SYSDATE)";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, userId);
			pstmt.setInt(2, optionNo);
			pstmt.setInt(3, projectNo);
			pstmt.setInt(4, price);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			ConnectionFactory.close(pstmt);
		}
		return result;
	}

	// 프로젝트 삭제
	public int projectDelete(Connection conn, int projectNo) {
		PreparedStatement pstmt = null;
		int result = 0;
		int resultFiletbl = 0;
		int resultProduct = 0;
		int resultComment = 0;
		int resultProject = 0;
		// on delete cascade 를 사용할려했으나 너무 어려워서
		// 순차적으로 삭제하는 쿼리
		String query1 = "delete from filetbl where project_no = ?";
		String query2 = "delete from product where project_no= ?";
		String query3 = "delete from board_comment where project_no = ?";
		String query4 = "delete from project_board where project_no = ?";

		try {
			pstmt = conn.prepareStatement(query1);
			pstmt.setInt(1, projectNo);
			resultFiletbl = pstmt.executeUpdate(); // 파일 테이블 삭제
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			pstmt = conn.prepareStatement(query2);
			pstmt.setInt(1, projectNo);
			resultProduct = pstmt.executeUpdate(); // 상품 테이블 삭제
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int cmt_size = this.projectComment(conn, projectNo).size();
		if (cmt_size > 0) {
			// 댓글이 하나라도 있으면 댓글 삭제
			try {
				pstmt = conn.prepareStatement(query3);
				pstmt.setInt(1, projectNo);
				resultComment = pstmt.executeUpdate(); // 댓글 삭제
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			// 댓글이 하나도 없으면 그냥 성공 처리
			resultComment = 1;
		}

		try {
			pstmt = conn.prepareStatement(query4);
			pstmt.setInt(1, projectNo);
			resultProject = pstmt.executeUpdate(); // 프로젝트 삭제
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			ConnectionFactory.close(pstmt);
		}
		if (resultFiletbl > 0 && resultProduct > 0 && resultComment > 0 && resultProject > 0) {
			// 셋 다 삭제에 성공하면 1을리턴 (셋다 성공했을시에 서비스에서 commit)
			result = 1;
		}
		return result;
	}

	// 프로젝트 수정
	public int modifyProject(Connection conn, String userId, ProjectBoard project, int projectNo, String endDate) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "update project_board set project_title=?, project_content = ?, end_date = ? where project_no = ?";

		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, project.getProjectTitle());
			pstmt.setString(2, project.getProjectContent());
			pstmt.setString(3, endDate);
			pstmt.setInt(4, projectNo);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			ConnectionFactory.close(pstmt);
		}
		return result;
	}

	// 프로젝트 상품 옵션 수정
	public int modifyProduct(Connection conn, Product product) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "update product set product_name = ?, product_option = ?, price = ? where project_no = ? and option_no = ?";

		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, product.getProductName());
			pstmt.setString(2, product.getOption());
			pstmt.setInt(3, product.getPrice());
			pstmt.setInt(4, product.getProjectNo());
			pstmt.setInt(5, product.getOptionNo());
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			ConnectionFactory.close(pstmt);
		}
		return result;
	}

	// 사진 수정하기
	public int modifyPhoto(Connection conn, File file) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "update filetbl set filename = ?, filepath = ? where project_no = ? and file_no=?";

		System.out.println("query : " + query);
		System.out.println(file.toString());

		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, file.getFileName());
			pstmt.setString(2, file.getFilePath());
			pstmt.setInt(3, file.getProjectNo());
			pstmt.setInt(4, file.getFileNo());

			result = pstmt.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			ConnectionFactory.close(pstmt);
		}
		return result;
	}

	// 댓글 목록 불러오기 - 프로젝트
	public ArrayList<Comment> projectComment(Connection conn, int projectNo) {
		ArrayList<Comment> projectCmtList = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = "select * from board_comment where project_no=? order by comment_no";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, projectNo);
			rset = pstmt.executeQuery();
			projectCmtList = new ArrayList<Comment>();
			while (rset.next()) {
				Comment cmt = new Comment();
				cmt.setCommentNo(rset.getInt("COMMENT_NO"));
				cmt.setContent(rset.getString("CONTENT"));
				cmt.setUserId(rset.getString("USER_ID"));
				cmt.setBoardType(rset.getString("BOARD_TYPE"));
				cmt.setcRegdate(rset.getDate("C_REGDATE"));
				cmt.setProjectNo(rset.getInt("PROJECT_NO"));
				projectCmtList.add(cmt);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			ConnectionFactory.close(pstmt);
			ConnectionFactory.close(rset);
		}
		return projectCmtList;
	}

	// 댓글 달기 - 프로젝트
	public int insertProjectComment(Connection conn, int projectNo, String content, String userId) {
		int result = 0;
		PreparedStatement pstmt = null;
		String query = "insert into board_comment values (comment_seq.nextval, ?, ?, 'PROJECT', SYSDATE, ?, null, null )";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, content);
			pstmt.setString(2, userId);
			pstmt.setInt(3, projectNo);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			ConnectionFactory.close(pstmt);
		}
		return result;
	}

	// 댓글 수정 - 프로젝트
	public int updateProjectComment(Connection conn, int commentNo, int projectNo, String content) {
		int result = 0;
		PreparedStatement pstmt = null;
		String query = "update board_comment set content=? where comment_no=?";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, content);
			pstmt.setInt(2, commentNo);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			ConnectionFactory.close(pstmt);
		}
		return result;

	}

	// 댓글 삭제
	public int deleteProjectComment(Connection conn, int commentNo) {
		int result = 0;
		PreparedStatement pstmt = null;
		String query = "delete from board_comment where comment_no=?";

		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, commentNo);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			ConnectionFactory.close(pstmt);
		}
		return result;
	}

	// 베스트 창작자
	public ArrayList<ProjectBoard> selectBestCreatorList(Connection conn) {
		ArrayList<ProjectBoard> list = null;
		Statement stmt = null;
		ResultSet rset = null;
		String query = "select * from (select user_id from project_board group by user_id order by count(user_id) desc) where ROWNUM <= 3";

		try {
			stmt = conn.createStatement();
			rset = stmt.executeQuery(query);
			list = new ArrayList<ProjectBoard>();
			while (rset.next()) {
				ProjectBoard project = new ProjectBoard();
				project.setUserId(rset.getString("user_id"));

				list.add(project);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			ConnectionFactory.close(stmt);
			ConnectionFactory.close(rset);
		}
		return list;
	}

	// 프로젝트 리스트 카테고리별 정렬 결과

	// 카테고리 리스트 불러오기 - Art (예술)
	public ArrayList<ProjectBoard> selectProjectArtList(Connection conn, int currentPage, int recordCountPerPage) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<ProjectBoard> nList = new ArrayList<ProjectBoard>();

		String query = "SELECT * FROM PROJECT_BOARD WHERE CATEGORY LIKE 'ART'";

		try {
			// stmt=conn.createStatement();
			pstmt = conn.prepareStatement(query);

			rset = pstmt.executeQuery();
			while (rset.next()) {
				ProjectBoard project = new ProjectBoard();
//				File image = new File();
				project.setProjectNo(rset.getInt("PROJECT_NO"));
				project.setUserId(rset.getString("USER_ID"));
				project.setProjectTitle(rset.getString("PROJECT_TITLE"));
				project.setProjectContent(rset.getString("PROJECT_CONTENT"));
				project.setStartDate(rset.getDate("START_DATE"));
				project.setEndDate(rset.getDate("END_DATe"));
				project.setSponsorCount(rset.getInt("SPONSOR_COUNT"));
				project.setSumMoney(rset.getInt("SUM_MONEY"));
				project.setTargetMoney(rset.getInt("TARGET_MONEY"));
				project.setpRegdate(rset.getDate("P_REGDATE"));
				float rate = ((float) project.getSumMoney() / project.getTargetMoney()) * 100;
				rate = (float) (Math.round(rate * 100) / 100.0);
				project.setRate(rate);
				project.setImage(selectRepImage(conn, project.getProjectNo()));
				nList.add(project);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			ConnectionFactory.close(pstmt);
			ConnectionFactory.close(rset);
		}
		return nList;
	}

	// 카테고리 리스트 불러오기 - Concert (공연)
	public ArrayList<ProjectBoard> selectProjectConcertList(Connection conn, int currentPage, int recordCountPerPage) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<ProjectBoard> nList = new ArrayList<ProjectBoard>();

		String query = "SELECT * FROM PROJECT_BOARD WHERE CATEGORY LIKE 'VIDEO'";

		try {
			pstmt = conn.prepareStatement(query);

			rset = pstmt.executeQuery();
			while (rset.next()) {
				ProjectBoard project = new ProjectBoard();
//				File image = new File();
				project.setProjectNo(rset.getInt("PROJECT_NO"));
				project.setUserId(rset.getString("USER_ID"));
				project.setProjectTitle(rset.getString("PROJECT_TITLE"));
				project.setProjectContent(rset.getString("PROJECT_CONTENT"));
				project.setStartDate(rset.getDate("START_DATE"));
				project.setEndDate(rset.getDate("END_DATe"));
				project.setSponsorCount(rset.getInt("SPONSOR_COUNT"));
				project.setSumMoney(rset.getInt("SUM_MONEY"));
				project.setTargetMoney(rset.getInt("TARGET_MONEY"));
				project.setpRegdate(rset.getDate("P_REGDATE"));
				float rate = ((float) project.getSumMoney() / project.getTargetMoney()) * 100;
				rate = (float) (Math.round(rate * 100) / 100.0);
				project.setRate(rate);
				project.setImage(selectRepImage(conn, project.getProjectNo()));
				nList.add(project);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			ConnectionFactory.close(pstmt);
			ConnectionFactory.close(rset);
		}
		return nList;
	}

	// 카테고리 리스트 불러오기 - Publishing (출판)
	public ArrayList<ProjectBoard> selectProjectPublishingList(Connection conn, int currentPage,
			int recordCountPerPage) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<ProjectBoard> nList = new ArrayList<ProjectBoard>();

		String query = "SELECT * FROM PROJECT_BOARD WHERE CATEGORY LIKE 'PUBLISHING'";

		try {
			pstmt = conn.prepareStatement(query);

			rset = pstmt.executeQuery();
			while (rset.next()) {
				ProjectBoard project = new ProjectBoard();
//				File image = new File();
				project.setProjectNo(rset.getInt("PROJECT_NO"));
				project.setUserId(rset.getString("USER_ID"));
				project.setProjectTitle(rset.getString("PROJECT_TITLE"));
				project.setProjectContent(rset.getString("PROJECT_CONTENT"));
				project.setStartDate(rset.getDate("START_DATE"));
				project.setEndDate(rset.getDate("END_DATe"));
				project.setSponsorCount(rset.getInt("SPONSOR_COUNT"));
				project.setSumMoney(rset.getInt("SUM_MONEY"));
				project.setTargetMoney(rset.getInt("TARGET_MONEY"));
				project.setpRegdate(rset.getDate("P_REGDATE"));
				float rate = ((float) project.getSumMoney() / project.getTargetMoney()) * 100;
				rate = (float) (Math.round(rate * 100) / 100.0);
				project.setRate(rate);
				project.setImage(selectRepImage(conn, project.getProjectNo()));
				nList.add(project);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			ConnectionFactory.close(pstmt);
			ConnectionFactory.close(rset);
		}
		return nList;
	}

	// 카테고리 리스트 불러오기 - Game (게임)
	public ArrayList<ProjectBoard> selectProjectGameList(Connection conn, int currentPage, int recordCountPerPage) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<ProjectBoard> nList = new ArrayList<ProjectBoard>();

		String query = "SELECT * FROM PROJECT_BOARD WHERE CATEGORY LIKE 'GAME'";

		try {
			pstmt = conn.prepareStatement(query);

			rset = pstmt.executeQuery();
			while (rset.next()) {
				ProjectBoard project = new ProjectBoard();
//				File image = new File();
				project.setProjectNo(rset.getInt("PROJECT_NO"));
				project.setUserId(rset.getString("USER_ID"));
				project.setProjectTitle(rset.getString("PROJECT_TITLE"));
				project.setProjectContent(rset.getString("PROJECT_CONTENT"));
				project.setStartDate(rset.getDate("START_DATE"));
				project.setEndDate(rset.getDate("END_DATe"));
				project.setSponsorCount(rset.getInt("SPONSOR_COUNT"));
				project.setSumMoney(rset.getInt("SUM_MONEY"));
				project.setTargetMoney(rset.getInt("TARGET_MONEY"));
				project.setpRegdate(rset.getDate("P_REGDATE"));
				float rate = ((float) project.getSumMoney() / project.getTargetMoney()) * 100;
				rate = (float) (Math.round(rate * 100) / 100.0);
				project.setRate(rate);
				project.setImage(selectRepImage(conn, project.getProjectNo()));
				nList.add(project);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			ConnectionFactory.close(pstmt);
			ConnectionFactory.close(rset);
		}
		return nList;
	}

	// 카테고리 리스트 불러오기 - Other (기타)
	public ArrayList<ProjectBoard> selectProjectOtherList(Connection conn, int currentPage, int recordCountPerPage) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<ProjectBoard> nList = new ArrayList<ProjectBoard>();

		String query = "SELECT * FROM PROJECT_BOARD WHERE CATEGORY LIKE 'OTHER'";

		try {
			pstmt = conn.prepareStatement(query);

			rset = pstmt.executeQuery();
			while (rset.next()) {
				ProjectBoard project = new ProjectBoard();
//				File image = new File();
				project.setProjectNo(rset.getInt("PROJECT_NO"));
				project.setUserId(rset.getString("USER_ID"));
				project.setProjectTitle(rset.getString("PROJECT_TITLE"));
				project.setProjectContent(rset.getString("PROJECT_CONTENT"));
				project.setStartDate(rset.getDate("START_DATE"));
				project.setEndDate(rset.getDate("END_DATe"));
				project.setSponsorCount(rset.getInt("SPONSOR_COUNT"));
				project.setSumMoney(rset.getInt("SUM_MONEY"));
				project.setTargetMoney(rset.getInt("TARGET_MONEY"));
				project.setpRegdate(rset.getDate("P_REGDATE"));
				float rate = ((float) project.getSumMoney() / project.getTargetMoney()) * 100;
				rate = (float) (Math.round(rate * 100) / 100.0);
				project.setRate(rate);
				project.setRate(rate);
				project.setImage(selectRepImage(conn, project.getProjectNo()));
				nList.add(project);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			ConnectionFactory.close(pstmt);
			ConnectionFactory.close(rset);
		}
		return nList;
	}

	// 추천 프로젝트(인기순, 달성률이 높은 순으로) 가져오기 - 달성률 상위 2개

	// 카테고리별로, 달성률 상위 3개 프로젝트 가져오기 - 매개변수 : 카테고리
	public ArrayList<ProjectBoard> recommendCategoryProject(Connection conn, String category) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<ProjectBoard> list = null;
		String sql = "SELECT * FROM (SELECT PROJECT_NO, PROJECT_TITLE, PROJECT_CONTENT, CATEGORY, TARGET_MONEY, SUM_MONEY, SPONSOR_COUNT, (SUM_MONEY/TARGET_MONEY)*100 AS RATE, ROW_NUMBER()OVER (PARTITION BY CATEGORY ORDER BY (SUM_MONEY/TARGET_MONEY) DESC) AS RNK FROM PROJECT_BOARD) WHERE RNK <= 3 and category=?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, category);
			rset = pstmt.executeQuery();
			list = new ArrayList<ProjectBoard>();
			while (rset.next()) {
				ProjectBoard project = new ProjectBoard();
				project.setProjectNo(rset.getInt("PROJECT_NO"));
				project.setProjectTitle(rset.getString("PROJECT_TITLE"));
				if (rset.getString("PROJECT_CONTENT").length() > 20) {
					// 프로젝트 설명이 20자보다 크면, 20글자로 줄여서 세팅
					project.setProjectContent(((rset.getString("PROJECT_CONTENT")).substring(0, 20)).concat(" ..."));
//					System.out.println("글자 자르기 결과 : "+project.getProjectContent());
				} else {
					// 20글자보다 작으면 그대로 출력
					project.setProjectContent(rset.getString("PROJECT_CONTENT"));
//					System.out.println("글자 안 자르기 결과 : "+project.getProjectContent());
				}
				project.setCategory(rset.getString("CATEGORY"));
				project.setTargetMoney(rset.getInt("TARGET_MONEY"));
				project.setSumMoney(rset.getInt("SUM_MONEY"));
				project.setSponsorCount(rset.getInt("SPONSOR_COUNT"));
				project.setRate(rset.getFloat("RATE"));
				project.setRanking(rset.getInt("RNK"));
				float rate = ((float) project.getSumMoney() / project.getTargetMoney()) * 100;
				rate = (float) (Math.round(rate * 100) / 100.0);
				project.setRate(rate);
				project.setImage(selectRepImage(conn, project.getProjectNo())); // 대표 이미지경로
				list.add(project);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			ConnectionFactory.close(pstmt);
			ConnectionFactory.close(rset);
		}
		return list;
	}

	// 최신 등록된 프로젝트 리스트 가져오기 - 최신 등록 상위 6개
	public ArrayList<ProjectBoard> latestProject(Connection conn) {
		ArrayList<ProjectBoard> list = null;
		Statement stmt = null;
		ResultSet rset = null;
		String query = "select * from (select project_no, user_id, project_title, project_content,category, start_date, end_date, sponsor_count, sum_money, target_money, p_regdate, row_number() over(order by start_date desc) as latest from project_board) where latest <= 6";

		try {
			stmt = conn.createStatement();
			rset = stmt.executeQuery(query);
			list = new ArrayList<ProjectBoard>();
			while (rset.next()) {
				ProjectBoard project = new ProjectBoard();
				project.setProjectNo(rset.getInt("PROJECT_NO"));
				project.setUserId(rset.getString("USER_ID"));
				project.setProjectTitle(rset.getString("PROJECT_TITLE"));
				if (rset.getString("PROJECT_CONTENT").length() > 20) {
					// 프로젝트 설명이 20자보다 크면, 20글자로 줄여서 세팅
					project.setProjectContent(((rset.getString("PROJECT_CONTENT")).substring(0, 20)).concat(" ..."));
				} else {
					// 20글자보다 작으면 그대로 출력
					project.setProjectContent(rset.getString("PROJECT_CONTENT"));
				}
				project.setCategory(rset.getString("CATEGORY"));
				project.setStartDate(rset.getDate("START_DATE"));
				project.setEndDate(rset.getDate("END_DATE"));
				project.setSponsorCount(rset.getInt("SPONSOR_COUNT"));
				project.setSumMoney(rset.getInt("SUM_MONEY"));
				project.setTargetMoney(rset.getInt("TARGET_MONEY"));
				float rate = ((float) project.getSumMoney() / project.getTargetMoney()) * 100;
				rate = (float) (Math.round(rate * 100) / 100.0);
//				System.out.println("dao rate : " + rate);
				project.setRate(rate);
				project.setImage(selectRepImage(conn, project.getProjectNo())); // 대표 이미지 경로
				list.add(project);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			ConnectionFactory.close(stmt);
			ConnectionFactory.close(rset);
		}
		return list;
	}

}
