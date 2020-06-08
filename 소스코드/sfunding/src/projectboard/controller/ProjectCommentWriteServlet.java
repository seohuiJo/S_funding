package projectboard.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import member.model.vo.Member;
import projectboard.model.service.ProjectService;

/**
 * Servlet implementation class ProjectCommentWriteServlet
 */
@WebServlet("/projectCommentWrite")
public class ProjectCommentWriteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ProjectCommentWriteServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 댓글 작성
		// 1. 한글 인코딩
		request.setCharacterEncoding("utf-8");
		String content = request.getParameter("comment"); // 댓글 내용
		int projectNo = Integer.parseInt(request.getParameter("projectNo"));
		System.out.println(projectNo);
		String userId = null; // 작성자 아이디, 정보
		HttpSession session = request.getSession(); // 세션을 가져옴
		if (session != null && (session.getAttribute("member") != null)) {
			// 로그인 했을 때
			userId = ((Member) session.getAttribute("member")).getUserId();
		} else {
			userId = "anonymous";
		}

		// 댓글 작성을 동작하는 부분
		int result = new ProjectService().insertProjectComment(projectNo, content, userId);
		if (result > 0) {
			response.sendRedirect("/projectSelect?projectNo=" + projectNo);
		} else {
			response.sendRedirect("/views/projectboard/projectError.html");
			System.out.println("댓글 작성 실패");
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
