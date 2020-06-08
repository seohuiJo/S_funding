package projectboard.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import projectboard.model.service.ProjectService;

/**
 * Servlet implementation class ProjectCommentDeleteServlet
 */
@WebServlet("/projectCommentDelete")
public class ProjectCommentDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ProjectCommentDeleteServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int commentNo = Integer.parseInt(request.getParameter("commentNo"));
		int projectNo = Integer.parseInt(request.getParameter("projectNo"));
		int result = new ProjectService().deleteProjectComment(commentNo);

		if (result > 0) {
			response.sendRedirect("/projectSelect?projectNo=" + projectNo);

		} else {
			response.sendRedirect("/views/projectboard/projectError.html");
			System.out.println("댓글 삭제 실패");
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
