package member.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import member.model.vo.Member;

public class MemberDAO {
	
	// �α��� =================================================================
	public Member selectList(Connection conn, String userId, String userPwd) {
		return null;
	}
	// ===================================================================
	
	
	
	// ȸ�� ���� ===============================================
	public int insertMember(Connection conn, Member member) {
		return 0;
	}
	// ======================================================


	// ȸ�� ���� ==============================================
	public int deleteMember(Connection conn, String userId) {
		return 0;
	}
	// =========================================================


	// ȸ�� ��� ===============================================
	public ArrayList<Member> selectMemberList(Connection conn) {
		return null;
	}
	// ===========================================================


	// ȸ�� ���� ���� ============================================
	public int updateMember(Connection conn, Member member) {
		return 0;
	}
	// ============================================================



	// �� ���� ====================================================
	public Member selectOne(Connection conn, String userId) {
		return null;
	}
	// ==============================================================



	// ����Ʈ��� =========================================================
	public ArrayList<Member> bestMemberList(Connection conn, String userId, String nickname) {
		return null;
	}
	// =============================================================================



	// ����Ʈ ���� ================================================================
	public int chargePoint(Connection conn, int point, String userId) {
		return 0;
	}
	// ==========================================================================



	// ����Ʈ ��� ==============================================================
	public int usePoint(Connection conn, int point, String userId) {
		return 0;
	}
	// ========================================================================

}
