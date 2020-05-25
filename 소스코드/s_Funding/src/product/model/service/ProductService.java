package product.model.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import notice.model.dao.NoticeDAO;
import product.model.dao.ProductDAO;
import product.model.vo.Product;
import supportlist.model.dao.SupportDAO;




public class ProductService {
	public ArrayList<Product> selectProductList() {
		Connection conn = null;
		ArrayList<Product> list = null;

		try {
			conn = factory.createConnection();
			list = new ProductDAO().selectProductList(conn);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	public int modifyProduct(String subject,String content,int noticeNo) {
		Connection conn =null;
		int result=0;
		try {
			conn=factory.createConnection();
			result=new ProductDAO().modifyProduct(conn);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(result>0) {
			factory.commit(conn);
		}else {
			factory.rollback(conn);
		}
		return result;
	}

}
