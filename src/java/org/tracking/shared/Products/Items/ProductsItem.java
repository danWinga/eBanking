/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.tracking.shared.Products.Items;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author dan
 */
public class ProductsItem implements Serializable {
        private String status,notes,serialNo, groupName;
        private int quantity, id,itemId, groupid,costPrice,sellingPrice;
        private Date  writeDate, createDate;
        private boolean caBeSold ;
        
        public ProductsItem(){  
            id =0;
            quantity = 0;
            costPrice = 0;
            itemId = 0;
            status = "";
            serialNo = "";
            caBeSold = true;
            groupid = 0;
            sellingPrice = 0;
            costPrice = 0;
            notes = "";
            writeDate = new Date();
            createDate = new Date();
        }
        
        public ProductsItem(int id,int itemId,int groupId,String serialNo,String status, int costPrice,int sell,boolean cabesold,
                String note,Date writeDate, Date creaDate  ){
                this.id = id;
                this.itemId = itemId;
                this.groupid = groupId;
                this.serialNo = serialNo;
                this.costPrice = costPrice;
                this.sellingPrice = sell;
                this.caBeSold = cabesold;
                this.notes = note;
                this.status = status;
                this.writeDate = writeDate;
                this.createDate = creaDate;

        }
        // productItem.id,productItem.itemId,productItem.groupid, serialNo,productItem.status, productItem.cabesold,productGroup.groupName
        public ProductsItem(int id,int itemId,int groupId,String serialNo,String status, boolean cabesold,String gpname,String note ){
                this.id = id;
                this.itemId = itemId;
                this.groupid = groupId;
                this.serialNo = serialNo;
                this.caBeSold = cabesold;
                this.notes = note;
                this.status = status;
                this.groupName = gpname;
                

        }
        public ProductsItem(int id,int itemId,int groupId,String status, int costPrice,int sell ){
                
                this.id = id;
                this.itemId = itemId;
                this.groupid = groupId;
                this.costPrice = costPrice;
                this.sellingPrice = sell;
                this.status = status;
        
        }
        public int groupId(){return groupid;}
        public void setGroupId(int id){this.groupid = id;}
        
        public int getSellingPrice(){return sellingPrice;}
        public void setSellingPrice(int sell){this.sellingPrice = sell;}
        
        public String getNotes(){return notes;}
        public void setNotes(String notes){this.notes = notes;}
        
        public boolean getCabeSold(){return caBeSold;}
        public void setCabeSold(boolean cabsold){this.caBeSold = cabsold;}
        
        public int getItemId(){return itemId;}
        public void setItemId(int itemid){this.itemId =itemid;}
        
        public String getSerialNo(){return serialNo;}
        public void setSerialNo(String serialNo){this.serialNo = serialNo;}
        
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
                
        public int getCostPrice(){
            return costPrice;
        }
        public void setCostprice(int price){
            this.costPrice = price;
        }
        public String getGroupName(){
            return groupName;
        }
        public void setGroupName(String groupname){
            this.groupName = groupname;
        }
        
        @Override
    public String toString(){
        return getItemId()+ getStatus();
    }
}
