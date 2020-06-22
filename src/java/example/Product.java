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
import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseId;



import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

@ManagedBean(name = "productBean")
@RequestScoped
public class Product implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String productId;
	private String productName;
	private StreamedContent productImage;
	private List<Product> productList;


	public void setProductImage(StreamedContent productImage) {
		this.productImage = productImage;
	}

	public void setProductList(List<Product> productList) {
		this.productList = productList;
	}

	public List<Product> getProductList() throws IOException, SQLException {

		return new ProductImageDAO().getProductDetails();
	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public StreamedContent getProductImage() throws IOException, SQLException {

		FacesContext context = FacesContext.getCurrentInstance();

		if (context.getCurrentPhaseId() == PhaseId.RENDER_RESPONSE) {
			return new DefaultStreamedContent();
		}

		else {

			String id = context.getExternalContext().getRequestParameterMap()
					.get("pid");

			byte[] image = new ProductImageDAO().getProductImage(id);

			return new DefaultStreamedContent(new ByteArrayInputStream(image));

		}
	}

}