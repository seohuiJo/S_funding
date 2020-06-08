package message.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import common.ConnectionFactory;
import message.model.vo.Message;

public class MessageDAO {
	//
	public int notifyMessage(Connection conn, String userId, String nickname, String messageContent) {
		return 0;
	}

	// 아이디를 보내서 해당 아이디가 받은 알람 전체 선택
	public ArrayList<Message> messageSelectList(Connection conn, int currentPage, int recordCountPerPage,
			String userId) {
		ArrayList<Message> msgList = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = "select * from (select message.*, row_number() over(order by message_no desc) as num from message where user_id = ?) where num between ? and ?";

		int start = currentPage * recordCountPerPage - (recordCountPerPage - 1);
		int end = currentPage * recordCountPerPage;
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, userId);
			pstmt.setInt(2, start);
			pstmt.setInt(3, end);
			rset = pstmt.executeQuery();
			msgList = new ArrayList<Message>();
			while (rset.next()) {
				Message msg = new Message();
				msg.setMessageNo(rset.getInt("message_no"));
				msg.setUserId(rset.getString("user_id"));
				msg.setMessageContent(rset.getString("message_content"));
				msg.setmRegdate(rset.getDate("m_regdate"));
				msg.setNickname(rset.getString("nickname"));
				msgList.add(msg);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			ConnectionFactory.close(pstmt);
			ConnectionFactory.close(rset);
		}
		return msgList;
	}

	// 알림 전체 갯수 리턴(아이디에 해당하는)
	public int totalCount(Connection conn, String userId) {
		// 전체 갯수를 나눠주는 메소드
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int recordTotalCount = 0;
		String query = "SELECT COUNT(*) AS TOTALCOUNT FROM MESSAGE WHERE USER_ID = ?";
		// 게시글 총 갯수를 알아오는 쿼리

		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, userId);
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

	// 페이지 네비게이션
	public String getPageNavi(Connection conn, int currentPage, int recordCountPerPage, int naviCountPerPage,
			String userId) {
		int recordTotalCount = totalCount(conn, userId);
		int pageTotalCount = 0; // 전체 페이지 개수; 10개씩 만들면 13개;

		// 페이지 갯수 구하는 식
		if (recordTotalCount % recordCountPerPage > 0) {
			pageTotalCount = recordTotalCount / recordCountPerPage + 1;
		} else {
			pageTotalCount = recordTotalCount / recordCountPerPage;
		}
		// 현재 페이지를 기준으로 네비게이션을 구해야하므로
		// 현재 페이지 정보를 확인해서 0보다는 크고 전체 페이지 수보다는 작은 위치에 있는지 확인
		// (오류방지용)
		if (currentPage < 1) {
			currentPage = 1;
		} else if (currentPage > pageTotalCount) {
			currentPage = pageTotalCount;
		}
		// 예)7을 눌렀을때 시작이 6이 되어야 한다.
		int startNavi = ((currentPage - 1) / naviCountPerPage) * naviCountPerPage + 1;

		int endNavi = startNavi + naviCountPerPage - 1;

		// 오류방지
		if (endNavi > pageTotalCount) {
			endNavi = pageTotalCount;
		}

		// '<' '>'모양
		boolean needPrev = true;
		boolean needNext = true;

		if (startNavi == 1) {
			needPrev = false;
		}
		if (endNavi == pageTotalCount) {
			needNext = false;
		}

		// 준비 끝
		StringBuilder sb = new StringBuilder();
		sb.append("<nav aria-label='Page navigation example'>");
		sb.append("<ul class='pagination justify-content-center'>");
		if (needPrev) {
			sb.append(
					"<li class='page-item'><span class='page-link' style='color: black;'><a href='/messageList?currentPage="
							+ (startNavi - 1) + "'> &lt; </a></span></li>");
		}
		for (int i = startNavi; i <= endNavi; i++) {
			if (i == currentPage) {
				sb.append(
						"<li class='page-item'><span class='page-link' style='color: black;'><a href='/messageList?currentPage="
								+ i + "'><b>" + i + "</b></a></span></li>");

			} else {
				sb.append(
						"<li class='page-item'><span class='page-link' style='color: black;'><a href='/messageList?currentPage="
								+ i + "'>" + i + "</a></span></li>");
			}
		}
		if (needNext) {
			sb.append(
					"<li class='page-item'><span class='page-link' style='color: black;'><a href='/messageList?currentPage="
							+ (endNavi + 1) + "'> &gt; </a></span></li>");
		}
		sb.append("</ul></nav>");

		return sb.toString();
	}

	// 알람 삭제
	public int deleteMessage(Connection conn, int messageNo) {
		int result = 0;
		PreparedStatement pstmt = null;
		String query = "delete from message where message_no = ?";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, messageNo);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			ConnectionFactory.close(pstmt);
		}
		return result;
	}

	// 유저 아이디를 보내서 닉네임을 리턴하는 함수
	public String selectUserIdToNickname(Connection conn, String userId) {
		String nickname = "";
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = "select nickname from member where user_id = ?";

		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, userId);
			rset = pstmt.executeQuery();
			if (rset.next()) {
				nickname = rset.getString("nickname");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			ConnectionFactory.close(pstmt);
			ConnectionFactory.close(rset);
		}
		return nickname;
	}

	// 알림 보내기 - 블랙리스트 추가
	public int insertMessageAddBlackList(Connection conn, String userId, String reason) {
		int result = 0;
		PreparedStatement pstmt = null;
		String query = "insert into message values(message_seq.nextval, ?, ?,sysdate,?)";
		String message_content = userId + "님, " + reason + " 사유로 인해 블랙리스트 회원으로 변경되셨습니다.";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, userId);
			pstmt.setString(2, message_content);
			pstmt.setString(3, this.selectUserIdToNickname(conn, userId));
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			ConnectionFactory.close(pstmt);
		}
		return result;
	}

	// 알림 보내기 - 블랙리스트 해제
	public int insertMessageRemoveBlackList(Connection conn, String userId) {
		int result = 0;
		PreparedStatement pstmt = null;
		String query = "insert into message values(message_seq.nextval, ?, ?,sysdate,?)";
		String message_content = userId + "님, 블랙리스트 회원에서 해제되셨습니다.";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, userId);
			pstmt.setString(2, message_content);
			pstmt.setString(3, this.selectUserIdToNickname(conn, userId));
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			ConnectionFactory.close(pstmt);
		}
		return result;
	}

	// 알림 보내기 - 펀딩하기 눌렀을때('~님, ~프로젝트에 후원하셨습니다')
	public int insertMessageFundingThanks(Connection conn, String userId, String projectTitle) {
		int result = 0;
		PreparedStatement pstmt = null;
		String query = "insert into message values(message_seq.nextval, ?, ?,sysdate,?)";
		String message_content = "당신은 멋진 후원자! '" + this.selectUserIdToNickname(conn, userId) + "' 님 감사합니다~ "
				+ projectTitle + " 프로젝트에 펀딩하셨습니다.! *^^*";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, userId);
			pstmt.setString(2, message_content);
			pstmt.setString(3, this.selectUserIdToNickname(conn, userId));
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			ConnectionFactory.close(pstmt);
		}
		return result;
	}

	// 알림 보내기 - 결제 예정 알림('~프로젝트는 마감일 ~일에, ~원 포인트 차감예정입니다.')
	public int insertMessagePointRemind(Connection conn, String userId, String projectTitle, String endDate,
			int price) {
		int result = 0;
		PreparedStatement pstmt = null;
		String query = "insert into message values(message_seq.nextval, ?, ?,sysdate,?)";
		String priceFormat = String.format("%,d", price); // 충전포인트 콤마 포멧 - 10,000
		String message_content = userId + "님, < " + projectTitle + " > 프로젝트에 후원하셔서, '" + priceFormat + "' 포인트 차감 되었습니다."
				+ "감사합니다. (해당프로젝트 펀딩 마감일은 '"+endDate+"' 입니다.)";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, userId);
			pstmt.setString(2, message_content);
			pstmt.setString(3, this.selectUserIdToNickname(conn, userId));
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			ConnectionFactory.close(pstmt);
		}
		return result;
	}

	// 알림 보내기 - 펀딩 프로젝트가 성공률 100% 도달했을때
	public int insertMessageFundingSuccess(Connection conn, String userId) {
		int result = 0;
		PreparedStatement pstmt = null;
		String query = "insert into message values(message_seq.nextval, ?, ?,sysdate,?)";
		String message_content = "후원하신 프로젝트가 펀딩에 성공하였습니다. !!";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, userId);
			pstmt.setString(2, message_content);
			pstmt.setString(3, this.selectUserIdToNickname(conn, userId));
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			ConnectionFactory.close(pstmt);
		}
		return result;
	}

	// 알림 보내기 - 펀딩 실패
	public int insertMessageFundingFail(Connection conn, String userId) {
		int result = 0;
		PreparedStatement pstmt = null;
		String query = "insert into message values(message_seq.nextval, ?, ?,sysdate,?)";
		String message_content = "후원하신 프로젝트가 펀딩에 실패하였습니다.";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, userId);
			pstmt.setString(2, message_content);
			pstmt.setString(3, this.selectUserIdToNickname(conn, userId));
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			ConnectionFactory.close(pstmt);
		}
		return result;
	}

	// 알림 보내기 - 포인트 충전
	public int insertMessageAddPoint(Connection conn, String userId, int point, int totalPoint) {
		int result = 0;
		PreparedStatement pstmt = null;
		String query = "insert into message values(message_seq.nextval, ?, ?,sysdate,?)";
		Date currentDate = new Date(); // 현재 날짜와 시간
		SimpleDateFormat date = new SimpleDateFormat("yyyy년 MM월 dd일"); // 날짜 포멧
		SimpleDateFormat time = new SimpleDateFormat("a hh:mm"); // 시간 포멧
		String today = date.format(currentDate) + ", " + time.format(currentDate); // 오늘 날짜 + 시간
		String pointFormat = String.format("%,d", point); // 충전포인트 콤마 포멧 - 10,000
		String totalPointFormat = String.format("%,d", totalPoint); // 토탈포인트 콤마 포멧 - 30,000
		String message_content = "' " + pointFormat + " ' 포인트가 충전되었습니다. /현재 잔여 포인트 : " + totalPointFormat + " ( "
				+ today + " 기준)";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, userId);
			pstmt.setString(2, message_content);
			pstmt.setString(3, this.selectUserIdToNickname(conn, userId));
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			ConnectionFactory.close(pstmt);
		}
		return result;
	}

	// 알림 보내기 - 포인트 차감
	public int insertMessageDiscountPoint(Connection conn, String userId, int point) {
		int result = 0;
		PreparedStatement pstmt = null;
		String query = "insert into message values(message_seq.nextval, ?, ?,sysdate,?)";
		String message_content = point + " 포인트 차감예정입니다.";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, userId);
			pstmt.setString(2, message_content);
			pstmt.setString(3, this.selectUserIdToNickname(conn, userId));
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			ConnectionFactory.close(pstmt);
		}
		return result;
	}
}
