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
 * Servlet implementation class RequestWriteServlet
 */
@WebServlet("/RequestWriteServlet")
public class RequestWriteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public RequestWriteServlet() {
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
		String requestTitle = request.getParameter("requestTitle");
		String requestContent = request.getParameter("requestContent");
		String projectList = request.getParameter("projectList");

		HttpSession session = request.getSession();
		if (session != null && (session.getAttribute("member") != null)) {
			String userId = ((Member) session.getAttribute("member")).getUserId();
			int result = new RequestService().insertRequest(userId, requestTitle, requestContent, projectList);

			if (result > 0) {
				response.sendRedirect("/RequestServlet");
			} else {
				response.sendRedirect("/views/");
				// 값이 1이상이 아니면 오류페이지로 이동

			}

		} else {

			response.sendRedirect("/views/member/loginPage.html");
			// 로그인 하지 않고 글쓰기를 했을 때 로그인 하라는 창이 뜨면서 로그인 페이지로 넘어감
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
