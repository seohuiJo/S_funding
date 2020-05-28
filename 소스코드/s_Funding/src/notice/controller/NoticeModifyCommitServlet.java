package notice.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import notice.model.service.NoticeService;

/**
 * Servlet implementation class NoticeModifyCommitServlet
 */
@WebServlet("/noticeModifyCommit")
public class NoticeModifyCommitServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NoticeModifyCommitServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 한글 인코딩 처리
		request.setCharacterEncoding("utf-8");
		
		// View에서 넘어온 값 변수 저장
		String subject=request.getParameter("subject");
		String content=request.getParameter("content");
		
		int noticeNo=Integer.parseInt(request.getParameter("noticeNo"));
		int result=new NoticeService().modifyNotice(subject, content, noticeNo);
		
		if(result>0) {
			response.sendRedirect("/noticeSelect?noticeNo="+noticeNo);
		}else {
			response.sendRedirect("/vies/notice/noticeError.html");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
