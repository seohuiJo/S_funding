package ajax.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

/**
 * Servlet implementation class AjaxTest6Servlet
 */
@WebServlet("/ajaxTest6")
public class AjaxTest6Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AjaxTest6Servlet() {
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
		int userNum=Integer.parseInt(request.getParameter("userNum"));
		JSONArray userArr=new JSONArray();
		if(userNum>=1 && userNum<=5) {
			User user=userList.get(userNum-1);
			JSONObject result=new JSONObject();
			result.put("userNo", user.getUserNo());
			result.put("userName", URLEncoder.encode(user.getUserName(), "utf-8"));
			result.put("userAddr", URLEncoder.encode(user.getUserAddr(),"utf-8")); // result는 JSON 객체
			userArr.add(result); // JSON 객체를 JSONArray에 추가
		}else {
			for(User user: userList) {
				JSONObject result=new JSONObject();
				result.put("userNo", user.getUserNo());
				result.put("userName", user.getUserName());
				result.put("userAddr", user.getUserAddr());
				userArr.add(result);
			}
		}
		
		response.setContentType("application/json");
		PrintWriter out=response.getWriter();
		out.print(userArr);
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
