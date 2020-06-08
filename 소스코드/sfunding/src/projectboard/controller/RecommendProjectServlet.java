package projectboard.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import member.model.service.MemberService;
import member.model.vo.Member;
import projectboard.model.service.ProjectService;
import projectboard.model.vo.ProjectPageData;

/**
 * Servlet implementation class RecommentProjectServlet
 */
@WebServlet("/recommendProject")
public class RecommendProjectServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public RecommendProjectServlet() {
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
		// 추천 프로젝트 정보 변수
		ProjectPageData pd = null;
		// 현재 유저정보를 가져옴
		HttpSession session = request.getSession();
		if (session != null & (session.getAttribute("member") != null)) {
			// 로그인 해야만 디비에서 데이터 불러옴
			String userId = ((Member) session.getAttribute("member")).getUserId(); // userId
			// 해당 유저의 관심 카테고리 정보를 가져온다
			String category = new MemberService().selectMemberOne(userId).getInterest();
			pd = new ProjectService().recommendCategoryProject(category);
		}
		// 리스트가 비어있지 않으면
		if (pd != null) {
			RequestDispatcher view = request.getRequestDispatcher("/views/projectboard/projectRecommend.jsp");
			request.setAttribute("recommendPageData", pd);
			view.forward(request, response);
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
