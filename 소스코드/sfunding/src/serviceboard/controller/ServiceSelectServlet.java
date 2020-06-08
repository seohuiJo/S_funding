package serviceboard.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import serviceboard.model.service.ServiceService;
import serviceboard.model.vo.ServiceBoard;
import serviceboard.model.vo.ServiceCommentPage;

/**
 * Servlet implementation class ServiceSelectServlet
 */
@WebServlet("/serviceSelect")
public class ServiceSelectServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ServiceSelectServlet() {
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
		int serviceNo = Integer.parseInt(request.getParameter("serviceNo"));
		ServiceBoard service = new ServiceService().serviceSelect(serviceNo);

		// 댓글
		int currentPage = 0;
		// href="/notice?currentPage=1"
		if (request.getParameter("currentPage") == null) {
			currentPage = 1;
		} else {
			currentPage = Integer.parseInt(request.getParameter("currentPage"));
		}

		if (service != null) {
			RequestDispatcher view = request.getRequestDispatcher("/views/service/serviceContent.jsp");
			ServiceCommentPage pd = new ServiceService().selectCommentList(serviceNo, currentPage);
			request.setAttribute("service", service);
			request.setAttribute("pageData", pd);
			request.setAttribute("size", pd.getPageList().size());
			request.setAttribute("currentPage", currentPage);
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
