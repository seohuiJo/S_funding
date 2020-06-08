package serviceboard.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import serviceboard.model.service.ServiceService;

/**
 * Servlet implementation class ServiceModifyCommentServlet
 */
@WebServlet("/serviceModifyComment")
public class ServiceModifyCommentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ServiceModifyCommentServlet() {
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
		// View에서 보낸 전송값 변수 저장
		// noticeNo, commentNo, comment
		String content = request.getParameter("modComment");
		int commentNo = Integer.parseInt(request.getParameter("modCommentNo"));
		int serviceNo = Integer.parseInt(request.getParameter("modServiceNo"));

		int result = new ServiceService().modifyServiceComment(commentNo, content);

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
