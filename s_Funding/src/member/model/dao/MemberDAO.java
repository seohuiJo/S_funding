package member.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import member.model.vo.Member;

public class MemberDAO {
	
	// 로그인 =================================================================
	public Member selectList(Connection conn, String userId, String userPwd) {
		return null;
	}
	// ===================================================================
	
	
	
	// 회원 가입 ===============================================
	public int insertMember(Connection conn, Member member) {
		return 0;
	}
	// ======================================================


	// 회원 삭제 ==============================================
	public int deleteMember(Connection conn, String userId) {
		return 0;
	}
	// =========================================================


	// 회원 목록 ===============================================
	public ArrayList<Member> selectMemberList(Connection conn) {
		return null;
	}
	// ===========================================================


	// 회원 정보 수정 ============================================
	public int updateMember(Connection conn, Member member) {
		return 0;
	}
	// ============================================================



	// 내 정보 ====================================================
	public Member selectOne(Connection conn, String userId) {
		return null;
	}
	// ==============================================================



	// 베스트멤버 =========================================================
	public ArrayList<Member> bestMemberList(Connection conn, String userId, String nickname) {
		return null;
	}
	// =============================================================================



	// 포인트 충전 ================================================================
	public Member chargePoint(Connection conn, int point, String userId) {
		return null;
	}
	// ==========================================================================



	// 포인트 사용 ==============================================================
	public Member usePoint(Connection conn, int point, String userId) {
		return null;
	}
	// ========================================================================

}
