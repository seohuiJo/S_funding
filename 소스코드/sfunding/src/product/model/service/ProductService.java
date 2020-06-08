package product.model.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import common.ConnectionFactory;
import product.model.vo.Product;

public class ProductService {
	private ConnectionFactory factory;

	public ProductService() {
		factory = ConnectionFactory.getConnection();
	}

	// 상품목록 - 코드 미완
	public ArrayList<Product> selectProductList() {
		ArrayList<Product> productList = null;
		Connection conn = null;
		try {
			conn = factory.createConnection();
			// 실질적 코드 작성 - DAO 호출

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			factory.close(conn);
		}

		return productList;
	}

	// 상품등록 - 코드 미완
	public int insertProduct() {
		int result = 0;
		Connection conn = null;
		try {
			conn = factory.createConnection();
			// 실질적 코드 작성 - DAO 호출

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			factory.close(conn);
		}
		return result;
	}
	
	// 상품 수정 - 코드 미완
	public int modifyProduct() {
		int result = 0;
		Connection conn = null;
		try {
			conn = factory.createConnection();
			// 실질적 코드 작성 - DAO 호출

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			factory.close(conn);
		}
		return result;
	}
	// 상품 삭제 - 코드 미완
	public int deleteProduct() {
		int result = 0;
		Connection conn = null;
		try {
			conn = factory.createConnection();
			// 실질적 코드 작성 - DAO 호출

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			factory.close(conn);
		}
		return result;
	}

}
