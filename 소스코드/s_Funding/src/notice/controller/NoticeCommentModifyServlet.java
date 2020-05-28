package notice.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import notice.model.service.NoticeService;

/**
 * Servlet implementation class NoticeCommentModifyServlet
 */
@WebServlet("/modifyComment")
public class NoticeCommentModifyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NoticeCommentModifyServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 한글 인코딩 처리
		request.setCharacterEncoding("utf-8");
		// View에서 보낸 전송값 변수 저장
		// noticeNo, commentNo, comment 
		String content=request.getParameter("modComment");
		int noticeNo=Integer.parseInt(request.getParameter("modNoticeNo"));
		int commentNo=Integer.parseInt(request.getParameter("modCommentNo"));
		
		System.out.println(content+", "+noticeNo+", "+commentNo);
		
		int result=new NoticeService().modifyNoticeComment(commentNo, noticeNo, content);
		
		if(result>0) {
			response.sendRedirect("/noticeSelect?noticeNo="+noticeNo);
		}else {
			response.sendRedirect("/views/notice/noticeError.html");
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
