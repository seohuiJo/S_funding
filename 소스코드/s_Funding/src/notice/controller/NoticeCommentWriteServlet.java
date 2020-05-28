package notice.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import member.model.vo.Member;
import notice.model.service.NoticeService;

/**
 * Servlet implementation class NoticeCommentWriteServlet
 */
@WebServlet("/noticeCommentWrite")
public class NoticeCommentWriteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NoticeCommentWriteServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 한글 인코딩 처리
		request.setCharacterEncoding("utf-8");
		int noticeNo=Integer.parseInt(request.getParameter("noticeNo"));
		String content=request.getParameter("comment");
		HttpSession session=request.getSession();
		
		String userId;
		if(session!=null&&(session.getAttribute("member")!=null)) {
			userId=((Member)session.getAttribute("member")).getUserId();
		}else {
			userId="anonymous";
		}
		
		int result=new NoticeService().insertNoticeComment(content, userId, noticeNo);
		if(result>0) {
			response.sendRedirect("/notice");
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
