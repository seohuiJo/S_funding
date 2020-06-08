package projectboard.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import projectboard.model.service.ProjectService;

/**
 * Servlet implementation class ProjectCommentModifyServlet
 */
@WebServlet("/projectCommentModify")
public class ProjectCommentModifyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ProjectCommentModifyServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 한글 인코딩 처리
		request.setCharacterEncoding("utf-8");
		// view 전송값 저장 - projectNo, commentNo, comment(content)
		String content = request.getParameter("modComment");
		System.out.println("request.getparameter,modprojectNO : "+request.getParameter("modProjectNo"));
		int projectNo = Integer.parseInt(request.getParameter("modProjectNo"));
		int commentNo = Integer.parseInt(request.getParameter("modCommentNo"));

		System.out.println(content + ", " + projectNo + ", " + commentNo);
		int result = new ProjectService().updateProjectComment(commentNo, projectNo, content);
		if (result > 0) {
			response.sendRedirect("/projectSelect?projectNo=" + projectNo);

		} else {
			response.sendRedirect("/views/projectboard/projectError.html");
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
