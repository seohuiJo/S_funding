package supportlist.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import member.model.vo.Member;
import supportlist.model.service.SupportService;
import supportlist.model.vo.SupportListPageData;

/**
 * Servlet implementation class SupportListServlet
 */
@WebServlet("/supportList")
public class SupportListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * Default constructor.
	 */
	public SupportListServlet() {
		// TODO Auto-generated constructor stub
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		SupportListPageData supportPageData = null;
		HttpSession session = request.getSession();

		if (session != null && (session.getAttribute("member") != null)) {
			String userId = ((Member) session.getAttribute("member")).getUserId();

			int currentPage = 0;
			if (request.getParameter("currentPage") == null) {
				currentPage = 1;
			} else {
				currentPage = Integer.parseInt(request.getParameter("currentPage"));
			}
			supportPageData = new SupportService().supportList(userId, currentPage);
		}
		RequestDispatcher view = request.getRequestDispatcher("/views/supportlist/supportList.jsp");
		request.setAttribute("SupportPageData", supportPageData);
		view.forward(request, response);

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
