package product.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import member.model.vo.Member;
import notice.model.dao.NoticeDAO;
import product.model.vo.Product;

public class ProductDAO {

	public ArrayList<Product> selectProductList(Connection conn) {
		ArrayList<Product> productList = null;
		PreparedStatement pstmt = null;
		String query = "";

		return productList;
	}

	public int deleteProduct(Connection conn) {
		int result = 0;
		PreparedStatement pstmt = null;
		String query = "";

		return result;
	}

	public int insertProduct(Connection conn) {
		int result = 0;
		PreparedStatement pstmt = null;
		String query = "";

		return result;
	}

}
