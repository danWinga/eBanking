/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.tracking.shared.Products;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author dan
 */
public class Products implements Serializable {
        private String groupName,groupType,status;
        private int quantity, id, costPrice;
        private Date  writeDate, createDate;
        
        public Products(){
            id =0;
            quantity = 0;
            costPrice = 0;
            groupType = "";
            status = "";
            writeDate = new Date();
            createDate = new Date();
        }
        
        public Products(int id,String groupName,String groupType,String status, int qty,int price,Date writeDate, Date creaDate  ){
                this.id = id;
                this.quantity = qty;
                this.groupType = groupType;
                this.groupName = groupName;
                this.costPrice = price;
                this.status = status;
                this.writeDate = writeDate;
                this.createDate = creaDate;

        }
        public Products(int id,String groupName,String groupType,String status ){
                
                this.id = id;
                this.groupType = groupType;
                this.groupName = groupName;
                this.status = status;
                
               

        }
        
        public String getGroupName(){
            return groupName;
        }
        public void setGroupName(String groupName){
            this.groupName = groupName;
        }
        
        public String getUnitType(){
            return groupType;
        }
        public void setUnitType(String unitType){
            this.groupType = unitType;
        }
        public String getStatus(){
            return status;
        }
        public void setStatus(String status){
            this.status = status;
        }
        public Date getWriteDate(){
            return writeDate;
        }
        public void setWriteDate(Date writeDate){
            this.writeDate = writeDate;
        }
        public Date getCreateDate(){
            return createDate;
        }
        public void setCreateDate(Date createDate){
            this.createDate = createDate;
        }
        
        public int getId(){
            return id;
        }
        public void setId(int id){
            this.id  = id;
        }
        public int getQuantity(){
            return quantity;
        }
        public void setQuantity(int qty){
            this.quantity = qty;
        }
        
        public int getCostPrice(){
            return costPrice;
        }
        public void setCostprice(int price){
            this.costPrice = price;
        }
        
        @Override
    public String toString(){
        return getGroupName()+ getStatus();
    }
}
