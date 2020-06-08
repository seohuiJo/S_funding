package projectboard.controller;

import java.io.File;
import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import projectboard.model.service.ProjectService;

/**
 * Servlet implementation class ProjectDeleteServlet
 */
@WebServlet("/projectDelete")
public class ProjectDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ProjectDeleteServlet() {
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
		int projectNo = Integer.parseInt(request.getParameter("projectNo"));
		System.out.println(projectNo);

		int result = new ProjectService().projectDelete(projectNo);

		if (result > 0) {
			// 이클랩스 내부 폴더에 있는 이미지 파일을 삭제
			// 경로 가져오기
			String root = request.getSession().getServletContext().getRealPath("/");
			String saveDirectory = root + "upload/photo";
			
			// 파일 경로 가져오기
			String filePath1 = request.getParameter("image1");
			String filePath2 = request.getParameter("image2");
			String filePath3 = request.getParameter("image3");
			
			System.out.println(filePath1 + "," + filePath2 + "," + filePath3);
			
			// 파일 삭제 객체 생성
			File fileDelete1 = new File(saveDirectory + "/" + filePath1);
			File fileDelete2 = new File(saveDirectory + "/" + filePath2);
			File fileDelete3 = new File(saveDirectory + "/" + filePath3);
			System.out.println(saveDirectory + "/" + filePath1);
			
			// 실행되면 삭제하게 조건없이 실행
			fileDelete1.delete();
			fileDelete2.delete();
			fileDelete3.delete();
			
			response.sendRedirect("/project");
		} else {
			// 다음 페이지 설정
			RequestDispatcher rd = request.getRequestDispatcher("common/msg.jsp");
			request.setAttribute("loc", "/");
			request.setAttribute("msg", "프로젝트 삭제 실패");
			rd.forward(request, response);
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
