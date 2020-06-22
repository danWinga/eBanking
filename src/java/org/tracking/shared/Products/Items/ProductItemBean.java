/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.tracking.shared.Products.Items;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import org.tracking.server.database.DatabaseBean;
import org.tracking.shared.Products.Products;

/**
 *
 * @author dan
 */
@ManagedBean
@SessionScoped
public class ProductItemBean implements Serializable {
    private ProductsItem selectedItem ;
    private ProductsItem newProductItem;
    private Products selectedProduct;
    private ArrayList<ProductsItem>productItemList;
    private ArrayList<Products>productList; 
    
        FacesContext facesContext = FacesContext.getCurrentInstance();
        HttpSession httpSession = (HttpSession)facesContext.getExternalContext().getSession(true);
        FacesMessage msg = null;
        
        @PostConstruct
        public void init(){
            selectedItem = new ProductsItem();
        }
    /**
     * product list 
     * @return productList
     * 
     */    
    public ArrayList<Products> getProductList(){
        productList = new ArrayList<Products>();
        String sql = "select * from productgroup order by id";
        try{
                DatabaseBean db = new DatabaseBean();
                Connection con = db.DBconnect();
                PreparedStatement stm = con.prepareStatement(sql);
                ResultSet rs = db.preparedState(stm);
                while(rs.next()){
                            productList.add(new Products(rs.getInt("id"), rs.getString("groupName"), rs.getString("grouptype"), rs.getString("status"),
                            rs.getInt("quantity"), rs.getInt("costprice"), rs.getDate("writedate"),rs.getDate("createdate")));
                }
                rs.close();
                db.cleanup();
        }catch(Exception e){
            System.out.println("error 1"+ e);
        }
        return productList;
    }
        
     // productItem.id,productItem.itemId,productItem.groupid, serialNo,productItem.status, productItem.cabesold,productGroup.groupName   
    public ArrayList<ProductsItem> getProductItemList(){
        productItemList = new ArrayList<ProductsItem>();
        String sql = "select * from vw_productItem order by itemId";
        try{
                DatabaseBean db = new DatabaseBean();
                Connection con = db.DBconnect();
                PreparedStatement stm = con.prepareStatement(sql);
                ResultSet rs = db.preparedState(stm);
                while(rs.next()){
                            productItemList.add(new ProductsItem(rs.getInt("id"),rs.getInt("itemId"),
                            rs.getInt("groupid"), rs.getString("serialNo"), rs.getString("status"),
                            rs.getBoolean("cabesold"), rs.getString("groupName"), rs.getString("notes")));
                }
                rs.close();
                db.cleanup();
        }catch(Exception e){
            System.out.println("error 1"+ e);
        }
        return productItemList;
    }
    
    public void checkRecord(){}
    public void addNewItem(){}
    public void edit(){}
    public void delete(){}
    
    
    public ProductsItem getSelectedItem(){
        return selectedItem;
    }
    public void setSelectedItem(ProductsItem selected){
        this.selectedItem = selected;
    }
    public ProductsItem getNewProductItem(){
        return newProductItem;
    }
    public void setNewProductItem(ProductsItem newItem){
        this.newProductItem = newItem;
    }
    
    public Products getSelectedProduct(){
        
        return selectedProduct;
    }
    public void setSelectedProduct(Products selectProd){
        
        this.selectedProduct = selectProd;
    }
}
