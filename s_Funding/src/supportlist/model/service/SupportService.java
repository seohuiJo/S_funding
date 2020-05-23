package supportlist.model.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import member.model.dao.MemberDAO;
import member.model.vo.Member;
import supportlist.model.dao.SupportDAO;
import supportlist.model.vo.SupportList;



public class SupportService {
	public ArrayList<SupportList> selectSupportList() {
		Connection conn = null;
		ArrayList<SupportList> list = null;

		try {
			conn = factory.createConnection();
			list = new SupportDAO().selectSupportList(conn);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	public SupportList selectMemberOne(String userId) {
		Connection conn = null;
		SupportList member = null;

		try {
			conn = factory.createConnection();
			member = new SupportDAO().selectOne(conn, userId);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return member;
	}

}
