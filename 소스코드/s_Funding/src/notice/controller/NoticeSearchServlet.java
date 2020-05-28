package notice.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import notice.model.service.NoticeService;
import notice.model.vo.PageData;

/**
 * Servlet implementation class NoticeSearchServlet
 */
@WebServlet("/noticeSearch")
public class NoticeSearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NoticeSearchServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
<<<<<<< HEAD
		
<<<<<<< HEAD
=======
<<<<<<< HEAD
=======
				
>>>>>>> 29a65614bee92ca0220ebfbff8affea36775474c
>>>>>>> 68abd591b980428e768e162a92f858c5cde0f44a
=======
		// 1. 전송값에 한글이 있을 경우 인코딩 처리
		request.setCharacterEncoding("utf-8");
		// 2. View에서 보낸 전송값을 꺼내어 변수 저장
		String search=request.getParameter("search");
		int currentPage=0;
		if(request.getParameter("currentPage")==null) {
			currentPage=1;
		}else {
			currentPage=Integer.parseInt(request.getParameter("currentPage"));
		}
		PageData pd=new NoticeService().noticeSearchList(currentPage, search);
		RequestDispatcher view=request.getRequestDispatcher("/views/notice/noticeSearch.jsp");
		request.setAttribute("pageData", pd);
		request.setAttribute("search", search);
		view.forward(request, response);
>>>>>>> df9685b28cfc014ed2ccf48bdf7c94ace48ebb10
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
