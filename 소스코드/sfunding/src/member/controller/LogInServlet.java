package member.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import member.model.service.MemberService;
import member.model.vo.Member;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/Login")
public class LogInServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LogInServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 1. 전송값에 한글이 있을 경우 인코딩 처리
		request.setCharacterEncoding("utf-8");

		// 2. view 에서 보낸 전송값을 꺼내서 변수에 저장하기
		String userId = request.getParameter("userId");
		String userPwd = request.getParameter("userPwd");

		// 3. 비즈니스 로직을 처리할 서비스 클래스 메소드로
		Member member = new MemberService().selectMember(userId, userPwd);
		// 값을 전달 및 결과 받기

		// 4. 받은 결과에 따라서 성공/실패 페이지 내보내기.
		if (member != null) {
			if (member.getEnabled() == 0) { // 블랙리스트에 있을 시
				RequestDispatcher rd = request.getRequestDispatcher("common/msg.jsp");
				request.setAttribute("loc", "/");
				request.setAttribute("msg", "블랙리스트 회원입니다.");
				rd.forward(request, response);
			} else {
				HttpSession session = request.getSession();
				session.setAttribute("member", member);
				response.sendRedirect("index.jsp");
			}
		} else {
			response.sendRedirect("/views/message/loginfail.html");
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
