/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package example;

/**
 *
 * @author dan
 */


import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.context.FacesContext;



import org.primefaces.model.DefaultStreamedContent;

public class ProductImageDAO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public byte[] getProductImage(String productId) throws IOException,
			SQLException {
		Connection con = null;
		PreparedStatement stmt = null;
		byte[] productImage = null;

		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/test", "root", "*****");
		} catch (Exception e) {
			System.out.println(e);
			System.exit(0);
		}

		stmt = con.prepareStatement("select * from product where productId=?");
		stmt.setString(1, productId);
		ResultSet rs = stmt.executeQuery();

		while (rs.next()) {
			productImage = rs.getBytes("productImage");
		}

		rs.close();
		con.close();

		return productImage;
	}

public List getProductDetails() throws IOException, SQLException {
 List<Product> productList = new ArrayList<Product>();

 Connection con = null;
 Statement stmt = null;

 try {
 Class.forName("com.mysql.jdbc.Driver");
 con = DriverManager.getConnection(
 "jdbc:mysql://localhost:3306/test", "root", "******");
 } catch (Exception e) {
 System.out.println(e);
 System.exit(0);
 }

 stmt = con.createStatement();
 ResultSet rs = stmt.executeQuery("select * from product");

 Product product;

 while (rs.next()) {

 product = new Product();

 String productId = rs.getString("productId");

 product.setProductId(productId.trim());
 product.setProductName(rs.getString("productName"));
 productList.add(product);
 }

 rs.close();
 con.close();
 return productList;
 }

}
