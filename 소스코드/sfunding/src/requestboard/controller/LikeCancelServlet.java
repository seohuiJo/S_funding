package requestboard.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;

import member.model.vo.Member;
import requestboard.model.service.RequestService;

/**
 * Servlet implementation class LikeCancelServlet
 */
@WebServlet("/likeCancelServlet")
public class LikeCancelServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LikeCancelServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 공감 누른 사람수 가져옴(기존값 저장해놓기
		int good = Integer.parseInt(request.getParameter("goodOld")); // 기존 전체수 가져옴

		// 1: 요청성공, 0: 요청실패, 2: 로그인 실패, -1: 아무일도 일어나지 않음
		int result = -1; // 아무일도 일어나지 않을때
		int requestNo = Integer.parseInt(request.getParameter("requestNo"));//
		// userId 가져오기
		HttpSession session = request.getSession();
		if (session != null & (session.getAttribute("member") != null)) {
			// 로그인 했을때
			String userId = ((Member) session.getAttribute("member")).getUserId();
			result = new RequestService().cancelLike(requestNo, userId); // 실질적 서비스 요청
//			System.out.println("서블릿/공감 취소 요청 결과/result: " + result + "/userId : " + userId);
			if (result == 1) {
				// 요청에 성공하면, 공감한 사람의 수를 감소시키고 가져온다
				good = new RequestService().totalLike(requestNo);
			}
		} else {
			// 로그인 안했을때
			result = 2;
//			System.out.println("서블릿/공감 취소 요청 로그인 안함/result: " + result);
		}

		JSONObject jsonResult = new JSONObject();
		jsonResult.put("result", result);
		jsonResult.put("good", good);
		response.setContentType("application/json");
		PrintWriter out = response.getWriter();
		out.print(jsonResult);
		out.flush();
		out.close();
//		System.out.println("공감취소/서블릿/요청완료/result : " + result);
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
