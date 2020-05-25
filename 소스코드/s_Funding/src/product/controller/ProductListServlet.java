package product.controller;


import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import member.controller.RequestDispatcher;
import member.controller.SupportService;
import member.model.vo.Member;
import product.model.service.ProductService;
import product.model.vo.Product;

/**
 * Servlet implementation class ProductListServlet
 */
@WebServlet("/productList")
public class ProductListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public ProductListServlet() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ArrayList<Product> list=new ProductService().selectMemberList();
		if(!list.isEmpty()) {
			RequestDispatcher view=request.getRequestDispatcher("/views/member/memberList.jsp");
			request.setAttribute("list",list);
			view.forward(request,response);
		}else {
			response.sendRedirect("");
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
