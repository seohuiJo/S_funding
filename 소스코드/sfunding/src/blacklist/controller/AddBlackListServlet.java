package blacklist.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import blacklist.model.service.BlacklistService;
import blacklist.model.vo.BlackList;
import message.model.service.MessageService;

/**
 * Servlet implementation class AddBlackListServlet
 */
@WebServlet("/addBlackList")
public class AddBlackListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AddBlackListServlet() {
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

		BlackList blackList = new BlackList();
		String userId = request.getParameter("userId");
		String reason = request.getParameter("reason");

		blackList.setUserId(userId);
		blackList.setReason(reason);

		System.out.println(userId);
		System.out.println(reason);

		int result = new BlacklistService().insertBlacklist(userId, reason);

		if (result > 0) {
			// 블랙리스트 등록에 성공하면, 알람을 보내준다
			int resultMsg = new MessageService().insertMessageAddBlackList(userId, reason);
			if (resultMsg > 0) {
				response.sendRedirect("/blacklist");
			} else {
				response.sendRedirect("/views/member/memberError.html");
//				System.out.println("블랙리스트 등록 메시지 등록실패");
			}
		} else {
			response.sendRedirect("/views/member/memberError.html");
//			System.out.println("블랙리스트 등록실패");
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
