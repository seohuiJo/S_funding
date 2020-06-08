package projectboard.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import projectboard.model.service.ProjectService;
import projectboard.model.vo.ProjectPageData;

/**
 * Servlet implementation class LatestProjectServlet
 */
@WebServlet("/latestProject")
public class LatestProjectServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LatestProjectServlet() {
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
		// 최신 프로젝트 정보 변수
		ProjectPageData pd = new ProjectService().latestProject();
//		System.out.println("서블릿에서 파일경로 :"+pd.getFileList().get(0).getFilePath());

		if (pd != null) {
			RequestDispatcher view = request.getRequestDispatcher("/views/projectboard/projectLatest.jsp");
			request.setAttribute("latestPageData", pd);
			view.forward(request, response);
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
