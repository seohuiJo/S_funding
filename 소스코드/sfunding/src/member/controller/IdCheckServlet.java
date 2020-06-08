package member.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import member.model.service.MemberService;

/**
 * Servlet implementation class IdCheckSerlet
 */
@WebServlet("/IdCheckServlet")
public class IdCheckServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public IdCheckServlet() {
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

		// 2. view에서 보낸 전송값을 꺼내어 변수 저장
		String userId = request.getParameter("userId");

		// 3. 비즈니스 로직을 처리할 서비스 클래스 메소드로 값을 전달 및 결과 받기
		int result = new MemberService().idCheck(userId);
		response.setContentType("text/html;charset=utf-8");
		if (result == 1) {

			// request.setAttribute("result", result);

			PrintWriter out = response.getWriter();

			out.println(
					"<script>alert('해당 아이디는 이미 사용중입니다.'); window.close(); document.getElementById('userId').value = '' ;</script>");

			System.out.println("성공");
			// request.getRequestDispatcher("idCheck.jsp").forward(request, response);
		} else {
			System.out.println("실패");
			PrintWriter out = response.getWriter();

			out.println("<script>alert('해당 아이디는 사용가능합니다.'); window.close();</script>");

			// response.sendRedirect("/views/member/memberError.html");
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
