package projectboard.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import filetbl.model.vo.File;
import product.model.vo.Product;
import projectboard.model.service.ProjectService;
import projectboard.model.vo.ProjectBoard;

/**
 * Servlet implementation class ProjectModifyServlet
 */
@WebServlet("/projectUpdate")
public class ProjectModifyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ProjectModifyServlet() {
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
		int projectNo = Integer.parseInt(request.getParameter("projectNo"));
		ProjectBoard project = new ProjectService().projectSelect(projectNo);

		Product option1 = new ProjectService().productOption(1, projectNo);
		Product option2 = new ProjectService().productOption(2, projectNo);
		Product option3 = new ProjectService().productOption(3, projectNo);

		ArrayList<File> photoList = new ProjectService().photoSelect(projectNo);

		if (project != null) {
			RequestDispatcher view = request.getRequestDispatcher("/views/projectboard/projectModify.jsp");
			request.setAttribute("content", project);
			request.setAttribute("option1", option1);
			request.setAttribute("option2", option2);
			request.setAttribute("option3", option3);
			// 사이즈가 0이 아닐때만 실행(0일때도 실행하면 심각이라는 콘솔에러남)
			if (photoList.size() != 0) {
				request.setAttribute("image1", photoList.get(0));
				request.setAttribute("image2", photoList.get(1));
				request.setAttribute("image3", photoList.get(2));
			}
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
