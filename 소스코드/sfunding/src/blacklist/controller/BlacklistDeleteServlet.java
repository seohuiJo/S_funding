package blacklist.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import blacklist.model.service.BlacklistService;
import message.model.service.MessageService;

/**
 * Servlet implementation class BlacklistDeleteServlet
 */
@WebServlet("/blacklistDelete")
public class BlacklistDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public BlacklistDeleteServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String userId = request.getParameter("userId");
		int result = new BlacklistService().deleteBlacklist(userId);

		if (result > 0) {
			int resultMsg = new MessageService().insertMessageRemoveBlackList(userId);
			if (resultMsg > 0) {
				response.sendRedirect("/blacklist");
			} else {
				System.out.println("블랙리스트 해제 메시지 등록실패");
			}
		} else {
			System.out.println("블랙리스트 해제 실패");
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
