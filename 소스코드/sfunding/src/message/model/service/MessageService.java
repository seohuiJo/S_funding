package message.model.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import common.ConnectionFactory;
import message.model.dao.MessageDAO;
import message.model.vo.Message;
import message.model.vo.MessagePageData;

public class MessageService {
	private ConnectionFactory factory;

	public MessageService() {
		factory = ConnectionFactory.getConnection();
	}

	public int notifyMessage(String userId, String nickname, String messageContent) {
		return 0;

	}

	// 아이디를 보내서 해당 아이디가 받은 알람 전체 선택
	public MessagePageData messageSelectList(int currentPage, String userId) {
		Connection conn = null;
		MessagePageData pd = new MessagePageData();
		ArrayList<Message> msgList = null;
		int recordCountPerPage = 10;
		int naviCountPerPage = 5;
		try {
			conn = factory.createConnection();
			msgList = new MessageDAO().messageSelectList(conn, currentPage, recordCountPerPage, userId);

			pd.setPageList(msgList);
			pd.setPageNavi(
					new MessageDAO().getPageNavi(conn, currentPage, recordCountPerPage, naviCountPerPage, userId));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			factory.close(conn);
		}
		return pd;
	}

	// 알림 삭제
	public int deleteMessage(int messageNo) {
		int result = 0;
		Connection conn = null;
		try {
			conn = factory.createConnection();
			result = new MessageDAO().deleteMessage(conn, messageNo);
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

	// 알림 보내기 - 블랙리스트 추가
	public int insertMessageAddBlackList(String userId, String reason) {
		int result = 0;
		Connection conn = null;

		try {
			conn = factory.createConnection();
			result = new MessageDAO().insertMessageAddBlackList(conn, userId, reason);
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

	// 알림 보내기 - 블랙리스트 해제
	public int insertMessageRemoveBlackList(String userId) {
		int result = 0;
		Connection conn = null;
		try {
			conn = factory.createConnection();
			result = new MessageDAO().insertMessageRemoveBlackList(conn, userId);
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

	// 알림 보내기 - 펀딩하기 눌렀을때('~님, ~프로젝트에 후원하셨습니다')
	public int insertMessageFundingThanks(String userId, String projectTitle) {
		int result = 0;
		Connection conn = null;
		try {
			conn = factory.createConnection();
			result = new MessageDAO().insertMessageFundingThanks(conn, userId, projectTitle);
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

	// 알림 보내기 - 결제 예정 알림('~프로젝트는 마감일 ~일에, ~원 포인트 차감예정입니다.')
	public int insertMessagePointRemind(String userId, String projectTitle, String endDate, int price) {
		int result = 0;
		Connection conn = null;
		try {
			conn = factory.createConnection();
			result = new MessageDAO().insertMessagePointRemind(conn, userId, projectTitle, endDate, price);
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

	// 알림 보내기 - 펀딩 성공
	public int insertMessageFundingSuccess(String userId) {
		int result = 0;
		Connection conn = null;
		try {
			conn = factory.createConnection();
			result = new MessageDAO().insertMessageFundingSuccess(conn, userId);
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

	// 알림 보내기 - 펀딩 실패
	public int insertMessageFundingFail(String userId) {
		int result = 0;
		Connection conn = null;

		try {
			conn = factory.createConnection();
			result = new MessageDAO().insertMessageFundingFail(conn, userId);
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

	// 알림 보내기 - 포인트 충전
	public int insertMessageAddPoint(String userId, int point, int totalPoint) {
		int result = 0;
		Connection conn = null;

		try {
			conn = factory.createConnection();
			result = new MessageDAO().insertMessageAddPoint(conn, userId, point, totalPoint);
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

	// 알림 보내기 - 포인트 사용
	public int insertMessageDiscountPoint(String userId, int point) {
		int result = 0;
		Connection conn = null;
		try {
			conn = factory.createConnection();
			result = new MessageDAO().insertMessageDiscountPoint(conn, userId, point);
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
}
