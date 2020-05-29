package serviceboard.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import notice.model.vo.Notice;
import serviceboard.model.vo.ServiceBoard;
import serviceboard.model.vo.ServicePageData;

public class ServiceDAO {
	
	// 고객센터 전체 가져오기
	public ArrayList<ServiceBoard> selectServiceList(Connection conn, int currentPage, int recordCountPerPage){
		// Statement stmt=null;
		PreparedStatement pstmt=null;
		ResultSet rset=null;
		ArrayList<ServiceBoard> nList=new ArrayList<ServiceBoard>();
		
		String query="SELECT * FROM(SELECT SERVICE_BOARD.*, ROW_NUMBER() OVER(ORDER BY SERVICE_NO DESC) AS NUM FROM SERVICE_BOARD) WHERE NUM BETWEEN ? AND ?";
		
		int start=currentPage*recordCountPerPage-(recordCountPerPage-1);
		int end=currentPage*recordCountPerPage;
		try {
			// stmt=conn.createStatement();
			pstmt=conn.prepareStatement(query);
			pstmt.setInt(1,  start);
			pstmt.setInt(2,  end);
			rset=pstmt.executeQuery();
			while(rset.next()) {
				ServiceBoard service=new ServiceBoard();
				service.setServiceNo(rset.getInt("SERVICE_NO"));
				service.setServiceContent(rset.getString("SERVICE_CONTENT"));
				service.setServiceCategory(rset.getString("SERVICE_CATEGORY"));
				service.setUserId(rset.getString("USER_ID"));
				service.setsRegdate(rset.getDate("S_REGDATE"));
				nList.add(service);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				pstmt.close();
				rset.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return nList;
	}
	
	// 고객센터 자세히 보기
	public ServiceBoard serviceSelect(Connection conn, int serviceNo) {
		PreparedStatement pstmt=null;
		ResultSet rset=null;
		ServiceBoard service=null;
		String query="SELECT * FROM SERVICE_BOARD WHERE SERVICE_NO=?";
		
		try {
			pstmt=conn.prepareStatement(query);
			pstmt.setInt(1, serviceNo);
			rset=pstmt.executeQuery();
			
			if(rset.next()) {
				service=new ServiceBoard();
				service.setServiceNo(rset.getInt("SERVICE_NO"));
				service.setServiceContent(rset.getString("SERVICE_CONTENT"));
				service.setServiceCategory(rset.getString("SERVICE_CATEGORY"));
				service.setUserId(rset.getString("USER_ID"));
				service.setsRegdate(rset.getDate("S_REGDATE"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return service;
	}
	
	public int totalCount(Connection conn) {
		PreparedStatement pstmt=null;
		ResultSet rset=null;
		int recordTotalCount=0;
		
		// 게시글 총 갯수를 알아오는 쿼리
		String query="SELECT COUNT(*) AS TOTALCOUNT FROM SERVICE_BOARD";
		
		try {
			pstmt=conn.prepareStatement(query);
			rset=pstmt.executeQuery();
			if(rset.next()) {
				recordTotalCount=rset.getInt("TOTALCOUNT");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return recordTotalCount;
	}
	
	public String getPageNavi(Connection conn, int currentPage, int recordCountPerPage, int naviCountPerPage) {
		int recordTotalCount=totalCount(conn);
		int pageTotalCount=0; // 전체 페이지의 갯수
		// 전체 게시물 갯수 124개, 10개씩 페이지 만들면 페이지 갯수는 13개
		
		if(recordTotalCount%recordCountPerPage>0) {
			pageTotalCount=recordTotalCount/recordCountPerPage+1;
		}else {
			pageTotalCount=recordTotalCount/recordCountPerPage;
		}
		
		// 현재 페이지를 기준으로 네비게이션을 구해야 하므로 
		// 현재 페이지 정보를 확인해서 0보다는 크고 전체 페이지 수보다는 작은 위치에 있는지 확인(오류방지)
		if(currentPage<1) {
			currentPage=1;
		}else if(currentPage>pageTotalCount){
			currentPage=pageTotalCount;
		}
		
		
		int startNavi=((currentPage-1)/naviCountPerPage)*naviCountPerPage+1;
		// currentPage=8, naviCountPerPage=5
		// ((8-1)/5)*5+1 => 6
		// currentPage=42, naviCountPerPage=5
		// 41 42 43 44 45
		// ((42-1)/5)*5+1 => 41
		int endNavi=startNavi+naviCountPerPage-1;
		
		if(endNavi>pageTotalCount) {
			endNavi=pageTotalCount;
		}
		
		// '<' 모양과 '>' 모양을 준비하기 위해 필요한 변수
		boolean needPrev=true;
		boolean needNext=true;
		if(startNavi==1) {
			needPrev=false;
		}
		if(endNavi==pageTotalCount) {
			needNext=false;
		}
		
		// 모든 준비 끝남
		StringBuilder sb=new StringBuilder();
		if(needPrev) {
			sb.append("<a href='/service?currentPage="+(startNavi-1)+"'><</a>>");
		}
		for(int i=startNavi; i<=endNavi; i++) {
			if(i==currentPage) {
				sb.append("<a href='/service?currentPage="+i+"'><b>"+i+"</b></a>");
			}else {
				sb.append("<a href='/service?currentPage="+i+"'>"+i+"</a>");
			}
		}
		
		if(needNext) {
			sb.append("<a href='service?currentPage="+(endNavi+1)+"'>></a>");
		}
		
		return sb.toString();
	}
	
}
