package serviceboard.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import serviceboard.model.service.ServiceService;

/**
 * Servlet implementation class ServiceDeleteCommentServlet
 */
@WebServlet("/serviceDeleteComment")
public class ServiceDeleteCommentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ServiceDeleteCommentServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int serviceNo = Integer.parseInt(request.getParameter("serviceNo"));
		int commentNo = Integer.parseInt(request.getParameter("commentNo"));
		int result = new ServiceService().deleteServiceComment(commentNo);

		if (result > 0) {
			response.sendRedirect("/serviceSelect?serviceNo=" + serviceNo);
		} else {
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
