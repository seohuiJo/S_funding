package member.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import member.model.service.MemberService;
import member.model.vo.Member;

/**
 * Servlet implementation class MemberModifyServlet
 */
@WebServlet("/mUpdate")
public class MemberModifyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public MemberModifyServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");

		Member member = new Member();
		member.setUserId(request.getParameter("userId"));
		member.setUserPwd(request.getParameter("userPwd"));
		member.setPhone(request.getParameter("phone"));
		member.setAddress(request.getParameter("address"));
		member.setEmail(request.getParameter("email"));
		member.setNickname(request.getParameter("nickname"));
		// member.setUserName(request.getParameter("user_name"));
		// member.setNickname(request.getParameter("nickname"));
		/*
		 * member.setPoint(Integer.parseInt(request.getParameter("point")));
		 * member.setEnabled(Integer.parseInt(request.getParameter("enabled")));
		 */
		// member.setInterest(request.getParameter("interest"));

		int result = new MemberService().updateMember(member);

		if (result > 0) {
			String userId = request.getParameter("userId");
			Member updateMember = new MemberService().selectMemberOne(userId);
			HttpSession session = request.getSession();
			session.setAttribute("member", updateMember);
			response.sendRedirect("/index.jsp");
		} else {
			System.out.println("메롱");
			response.sendRedirect("/views/member/memberError.html");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
