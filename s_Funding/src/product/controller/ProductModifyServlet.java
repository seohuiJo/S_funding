package product.controller;


import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import notice.model.service.NoticeService;
import product.model.service.ProductService;

/**
 * Servlet implementation class ProductModifyServlet
 */
@WebServlet("/productModify")
public class ProductModifyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public ProductModifyServlet() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		//view에서 넘오온 값 변수 저장
		String subject = request.getParameter("subject");
		String content = request.getParameter("content");
		int noticeNo=Integer.parseInt(request.getParameter("noticeNo"));
		int result=new ProductService().modifyProduct(subject,content,noticeNo);
		if(result>0) {//0보다 크면 수정 성공
			response.sendRedirect("/noticeSelect?noticeNo="+noticeNo);
		}else {
			response.sendRedirect("views/notice/noticeError.html");
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
