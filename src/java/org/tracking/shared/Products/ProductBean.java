/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.tracking.shared.Products;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.LazyDataModel;
import org.tracking.server.database.DatabaseBean;

/**
 *
 * @author dan
 */
@ManagedBean
@SessionScoped
public class ProductBean  implements Serializable{
        private Products selectedProduct;
        private  Products newproduct;
        private ArrayList<Products>productList;
        LazyDataModel<Products>productModel;
        
        FacesContext facesContext = FacesContext.getCurrentInstance();
        HttpSession httpSession = (HttpSession)facesContext.getExternalContext().getSession(true);
        FacesMessage msg = null;
        
        @PostConstruct
        public void init(){
            selectedProduct = new Products();
            newproduct = new Products();
            productModel = new ProductDataModel(getProductList());
        }
        
        
        public ArrayList<Products>getProductList(){
                
                productList = new ArrayList<Products>();
                String sql = "select * from productGroup";
                try{
                        DatabaseBean db = new DatabaseBean();
                        Connection con = db.DBconnect();
                        PreparedStatement stmt = con.prepareStatement(sql);
                        ResultSet rs=db.preparedState(stmt);
                        
                        while(rs.next()){
                                productList.add(new Products(rs.getInt("id"),rs.getString("groupName"),rs.getString("groupType"),
                                rs.getString("status"),rs.getInt("quantity"),rs.getInt("costPrice"),rs.getDate("writeDate"),rs.getDate("createDate")));
                        }
                        rs.close();
                        db.cleanup();
                }catch(SQLException e){
                        System.out.println("SQL Exception while getting units: "+ e);
                }catch(Exception e){
                        System.out.println("Exception while getting units"+e);
                }
                

                return productList;
        }
        
        /***
         * New unit Installation 
         */
        public void clear(){
                newproduct.setId(0);
                newproduct.setStatus("");
                newproduct.setUnitType("");
                newproduct.setGroupName("");
                newproduct.setQuantity(0);
                newproduct.setCostprice(0);
            
        }
        public void addNewProduct(){
                    if(!newproduct.getGroupName().equals("")&& !newproduct.getStatus().equals("") && !newproduct.getUnitType().equals("")){
                        addRecord();
                    }else{
                       msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error"," Fill in all records then try again! ");
                    }
        }
        public void addRecord(){
               String sql = "INSERT INTO productGroup (groupName,groupType, status,quantity,costPrice, writeDate, createDate)"
                + " VALUES ('"+ newproduct.getGroupName()+"', '"+ newproduct.getUnitType()+"','"+ newproduct.getStatus() +"','"+ newproduct.getQuantity() +"',"
                       + "'"+ newproduct.getCostPrice() +"', now(),now());"; 
               System.out.println("new Unit records: "+ sql);
               try{
                        DatabaseBean db = new DatabaseBean();
                        db.insert(sql);
                        msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "New Unit: ",newproduct.getGroupName()+ "Saved!");
                        db.cleanup();
                   
               }catch(SQLException e){
                   System.out.println("error1"+ e);
                   msg = new FacesMessage(FacesMessage.SEVERITY_WARN,"Database Error!", "contact developer!" );
               }catch(Exception e){
                   System.out.println("error 2"+ e);
                   msg = new FacesMessage(FacesMessage.SEVERITY_WARN,"Exception Error!", "check Data!" );
               }
               
               
        }
        public Products getSelectedProduct(){
            return selectedProduct;
        }
        public void setSelectedProduct(Products selectedUnit){
            this.selectedProduct = selectedUnit;
        }
        
        public Products getNewProduct(){
            return newproduct;
        }
        public void setNewProduct(Products newUnit){
            this.newproduct = newUnit;
        }
        
        public LazyDataModel<Products> getProductModel(){
            return productModel;
        }
        
        public void onRowSelect(SelectEvent event){
            msg = new FacesMessage("Product Selected", ((Products) event.getObject()).toString());
        FacesContext.getCurrentInstance().addMessage(null, msg);
        }
        
                
                
    
}
