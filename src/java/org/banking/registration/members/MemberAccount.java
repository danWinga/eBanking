/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.banking.registration.members;

import java.io.Serializable;

/**
 *
 * @author dan
 */
public class MemberAccount implements Serializable {
    
    private static final long serialVersionUID = 1L;
    private String accNo, accTypeName, productCode,currencyCode, incomeCode,groupCode,memberNo,branchCode;
    private int totalBalance,availableAmount;
    private boolean isActive;
    
    public MemberAccount(){
         accNo = "";         accTypeName = "";          productCode = "";           currencyCode = "";
         incomeCode = "";    groupCode = "";            memberNo ="";               branchCode ="";
         totalBalance = 0;    availableAmount = 0;      isActive = false;
    }
    public MemberAccount(String accNo, String accTypeName){
        this.accNo = accNo;
        this.accTypeName = accTypeName;
    }
    public String getAccNo(){
        return accNo;
    }
    public void setAccNo(String str){
        this.accNo = str;
    }
    
    public String getAccTypeName(){
        return accTypeName;
    }
    public void setAccTypeName(String str){
        this.accTypeName = str;
    }
    
    public String getProductCode(){
        return productCode;
    }
    public void setProductCode(String str){
        this.productCode = str;
    }
    
    public String getCurrencyCode(){
        return currencyCode;
    }
    public void setCurrencyCode(String str){
        this.currencyCode = str;
    }
    public String getIncomeCode(){
        return incomeCode;
    }
    public void setIncomeCode(String str){
        this.incomeCode = str;
    }
    public String getGroupCode(){
        return groupCode;
    }
    public void setGroupCode(String str){
        this.groupCode = str;
    }
    public String getMemberNo(){
        return memberNo;
    }
    public void setMemberNo(String str){
        this.memberNo = str;
    }
    public String getBranchCode(){
        return branchCode;
    }
    public void setBranchCode(String str){
        this.branchCode = str;
    }
    
    public int getTotalBalance(){
        return totalBalance;
    }
    public void setTotalBalance(int str){
        this.totalBalance = str;
    }
    public int getAvailableAmount(){
        return availableAmount;
    }
    public void setAvailableAmount(int str){
        this.availableAmount = str;
    }
    
     public boolean getIsActive(){
        return isActive;
    }
    public void setAIsActive(boolean  str){
        this.isActive =  str;
    }
    
    public void searchMember(){
        
        
    }
    
    
}
