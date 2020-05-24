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
	
	// 로그인 =====================================================
	public Member selectMember(String userId, String userPwd) {
		return null;
	}
	// ============================================================
	
	
	// 회원 가입 ===================================================
	public int insertMember(Member member) {
		return 0;
	}
	// ===========================================================

	
	// 회원 삭제 =================================================
	public int deleteMember(String userId) {
		return 0;
	}
	// =============================================================

	
	// 회원 목록 ===================================================
	public ArrayList<Member> selectMemberList() {
		return null;
	}
	// =============================================================
	
	
	// 회원 정보 수정 ===============================================
	public int updateMember(Member member) {
		return 0;
	}
	// ===============================================================

	
	// 내 정보 =======================================================
	public Member selectMemberOne(String userId) {
		return null;
	}
	// ================================================================

	
	// 베스트멤버 ===================================================
	public ArrayList<Member> bestMemberList(String userId, String nickname) {
		return null;
	}
	// ===================================================================

	
	// 포인트 충전 =========================================================
	public Member chargePoint(int point, String userId) {
		return null;
	}
	// =====================================================================



	// 포인트 사용 ========================================================
	public Member usePoint(int point, String userId) {
		return null;
	}
	// ======================================================================
}
