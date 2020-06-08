package requestboard.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import requestboard.model.service.RequestService;

/**
 * Servlet implementation class RequestCommentModifyServlet
 */
@WebServlet("/modifyRequestComment")
public class RequestCommentModifyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public RequestCommentModifyServlet() {
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
		// View 전송값 저장 - requestNo, commentNo, comment(content)
		String content = request.getParameter("modComment"); // 댓글 내용
		int requestNo = Integer.parseInt(request.getParameter("modRequestNo")); // 요청 글 번호
		int commentNo = Integer.parseInt(request.getParameter("modCommentNo")); // 댓글 번호

		System.out.println(content + ", " + commentNo + ", " + requestNo);
		int result = new RequestService().updateRequestComment(commentNo, requestNo, content);

		if (result > 0) {
			response.sendRedirect("/requestSelect?requestNo=" + requestNo);
		} else {
			response.sendRedirect("/views/requestboard/requestError.html");
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
