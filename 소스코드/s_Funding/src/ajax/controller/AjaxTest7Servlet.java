package ajax.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.StringTokenizer;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;

/**
 * Servlet implementation class AjaxTest7Servlet
 */
@WebServlet("/ajaxTest7")
public class AjaxTest7Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AjaxTest7Servlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ArrayList<User> userList=new ArrayList<User>();
		userList.add(new User(1,"일용자","서울"));
		userList.add(new User(2,"이용자","경기"));
		userList.add(new User(3,"삼용자","강원"));
		userList.add(new User(4,"사용자","전남"));
		userList.add(new User(5,"오용자","경북"));
		
		String userNums=request.getParameter("userNum");
		StringTokenizer token=new StringTokenizer(userNums, ",");
		ArrayList<Integer> userNum=new ArrayList<Integer>();
		while(token.hasMoreTokens()) {
			userNum.add(Integer.parseInt(token.nextToken())-1);
		}
		JSONObject map=new JSONObject();
		for(int i=0;i<userNum.size();i++) {
			User user=userList.get(userNum.get(i));
			JSONObject result=new JSONObject();
			result.put("userNo", user.getUserNo());
			result.put("userName", URLEncoder.encode(user.getUserName(),"utf-8"));
			result.put("userAddr", URLEncoder.encode(user.getUserAddr(),"utf-8"));
			map.put(URLEncoder.encode(user.getUserName(),"utf-8"), result);
		}
		response.setContentType("application/json");
		PrintWriter out=response.getWriter();
		out.print(map);
		out.flush();
		out.close();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
