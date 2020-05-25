package member.model.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import common.ConnectionFactory;
import member.model.dao.MemberDAO;
import member.model.vo.Member;

public class MemberService {

	private ConnectionFactory factory;
	
	public MemberService() {
		factory = ConnectionFactory.getConnection();
	}
	
	// �α��� =====================================================
	public Member selectMember(String userId, String userPwd) {
		return null;
	}
	// ============================================================
	
	
	// ȸ�� ���� ===================================================
	public int insertMember(Member member) {
		return 0;
	}
	// ===========================================================

	
	// ȸ�� ���� =================================================
	public int deleteMember(String userId) {
		return 0;
	}
	// =============================================================

	
	// ȸ�� ��� ===================================================
	public ArrayList<Member> selectMemberList() {
		return null;
	}
	// =============================================================
	
	
	// ȸ�� ���� ���� ===============================================
	public int updateMember(Member member) {
		return 0;
	}
	// ===============================================================

	
	// �� ���� =======================================================
	public Member selectMemberOne(String userId) {
		return null;
	}
	// ================================================================

	
	// ����Ʈ��� ===================================================
	public ArrayList<Member> bestMemberList(String userId, String nickname) {
		return null;
	}
	// ===================================================================

	
	// ����Ʈ ���� =========================================================
	public int chargePoint(int point, String userId) {
		return 0;
	}
	// =====================================================================



	// ����Ʈ ��� ========================================================
	public int usePoint(int point, String userId) {
		return 0;
	}
	// ======================================================================
}
