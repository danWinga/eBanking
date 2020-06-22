/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.banking.settings.account;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author dan
 */
public class AccountProduct  implements Serializable{
    
    private static final long serialVersionUID = 1L;
    private int  productId,ttAmount;
    private  String productCode, productName, acctypeCode, acctypeName,groupCode,groupName,accDesc;
    private Date createDate;
    
    
    
    public AccountProduct (){}

    AccountProduct(int id, String productCode, String productName, String accName, String accDesc) {
        this.productId = id;
        this.productCode = productCode;
        this.productName = productName;
        this.acctypeName = accName;
        this.accDesc = accDesc;
        
        //To change body of generated methods, choose Tools | Templates.
    }
    
    public int getProductId(){
        return productId;
    }
    public void setProductId(int str){
        this.productId = str;
    }
    
    public int getTtAmount(){
        return ttAmount;
    }
    public void setTtAmount(int str){
        this.ttAmount = str;
    }
    
    public String  getProductCode(){
        return productCode;
    }
    public void setProductCode(String str){
        this.productCode = str;
    }
    
    public String  getAcctypeName(){
        return acctypeName;
    }
    public void setAcctypeName(String str){
        this.acctypeName = str;
    }
    
    public String getProductName(){
        return productName;
    }
    public void setProductName(String str){
        this.productName = str;
    }
    
    public String getAcctypeCode(){
        return acctypeCode;
    }
    public void setAcctypeCode(String str){
        this.acctypeCode = str;
    }
    
    public String getAccDesc(){
        return accDesc;
    }
    public void setAccDesc(String str){
        this.accDesc =str;
    }
    
    public String getGroupCode(){
        return groupCode;
    }
    public void setGroupCode(String str){
        this.groupCode =str;
    }
    
     public String getGroupName(){
        return groupName;
    }
    public void setGroupName(String str){
        this.groupName =str;
    }
    
    
    public Date getCreateDate(){
        return createDate;
    }
    public void setCreateDate(Date str){
        this.createDate = str;
    }
    
    
    
    
}
