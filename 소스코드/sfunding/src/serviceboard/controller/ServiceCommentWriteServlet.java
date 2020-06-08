package serviceboard.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import member.model.vo.Member;
import serviceboard.model.service.ServiceService;

/**
 * Servlet implementation class ServiceCommentWriteServlet
 */
@WebServlet("/serviceCommentWrite")
public class ServiceCommentWriteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ServiceCommentWriteServlet() {
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
		int serviceNo = Integer.parseInt(request.getParameter("serviceNo"));
		String content = request.getParameter("comment");
		HttpSession session = request.getSession();

		String userId = null;
		if (session != null && (session.getAttribute("member") != null)) {
			userId = ((Member) session.getAttribute("member")).getUserId();
		} else {
			response.sendRedirect("/views/login.jsp");
		}

		int result = new ServiceService().insertServiceComment(content, userId, serviceNo);
		if (result > 0) {
			response.sendRedirect("/service");
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
