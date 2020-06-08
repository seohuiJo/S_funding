package requestboard.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import requestboard.model.service.RequestService;

/**
 * Servlet implementation class RequestDeleteServlet
 */
@WebServlet("/requestDelete")
public class RequestDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public RequestDeleteServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int requestNo = Integer.parseInt(request.getParameter("requestNo")); // 글번호
		// 현재 요청글에 댓글이 달렸는지 안달렸는지 확인한다 (달렸으면 댓글삭제)

		int cmt_size = new RequestService().requestSelect(requestNo).getComments().size();
		int result_comment = 0;
		if (cmt_size > 0) {
			// 댓글이 있으면 댓글 삭제
			result_comment = new RequestService().deleteRequestCommentAll(requestNo); // 글번호에 해당하는 모든 댓글 삭제
		} else {
			// 댓글 없으면 댓글 삭제 결과 무조건 1
			result_comment = 1;
		}

		int result_board = new RequestService().deleteRequest(requestNo); // 글번호에 해당하는 요청글 삭제
		if (result_comment > 0 && result_board > 0) {
			response.sendRedirect("/RequestServlet");
		} else {
			response.sendRedirect("/views/requestboard/requestError.html");
//			System.out.println("글 삭제 실패");
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
