package requestboard.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import member.model.vo.Member;
import requestboard.model.service.RequestService;

/**
 * Servlet implementation class RequestCommentWriteServlet
 */
@WebServlet("/requestCommentWrite")
public class RequestCommentWriteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public RequestCommentWriteServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 댓글 작성
		// 1.한글 인코딩
		request.setCharacterEncoding("utf-8");
		String content = request.getParameter("comment"); // 댓글 내용
		int requestNo = Integer.parseInt(request.getParameter("requestNo")); // 글번호
		String userId = null; // 작성자 아이디, 정보
		HttpSession session = request.getSession(); // 세션을 가져옴
		if (session != null & (session.getAttribute("member") != null)) {
			// 로그인 했을때
			userId = ((Member) session.getAttribute("member")).getUserId();
		} else {
			// 로그인 안했을때 - 로그인 해달라고 처리해야 할 것 같은데 생각을 더해보자
			userId = "anonymous";
		}
		// 실제로 댓글 작성을 동작하는 부분
		int result = new RequestService().insertRequestComment(requestNo, content, userId);
		if (result > 0) {
			response.sendRedirect("/requestSelect?requestNo=" + requestNo);
		} else {
			response.sendRedirect("/views/requestboard/requestError.html");
			System.out.println("댓글 작성 실패");
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
