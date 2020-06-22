/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.banking.transaction.TellerAccount;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author dan
 */
public class TellerFloat implements Serializable{
    
            private static final long serialVersionUID = 1L;
            private int id, assignId, floatAmount ,accId ,totalFloat, tellerId;
            private String branchManagerId, tellerName, deposite, withdrawal,accNo, subAccNo, branchCode;
            private boolean isTopup, isWithdrawal;
            private Date createdDate;
            private String dateString;
            
            public TellerFloat(){
                 this.id = 0;               this.assignId = 0;          this.floatAmount = 0;
                 this.branchManagerId ="";  this.tellerName = "";       this.deposite = "";
                 this.withdrawal ="";       this.isTopup = false;       this.isWithdrawal = false;
                 this.createdDate = new Date();
                 this.accId = 0;            this.totalFloat =0;              this.accNo ="";
                 this.subAccNo = "";        this.branchCode ="";             this.tellerId = 0;
            }
            
            public TellerFloat(int id, int assignId, String branchManagerId,int tellerId,String tellerName,
                    int floatAmount, boolean isTopup, boolean isWithd,Date cDate,int accId, String accNo,
                    int totalFlat,String subAcc,String branchCode){
                    this.id = id;                           this.assignId = assignId;           this.floatAmount = floatAmount;
                    this.branchManagerId =branchManagerId;  this.tellerName = tellerName;       this.deposite = "";
                    this.withdrawal ="";                    this.isTopup = isTopup;             this.isWithdrawal = isWithd;
                    this.createdDate = cDate;
                    this.accId = accId;                     this.totalFloat = totalFlat;         this.accNo = accNo;
                    this.branchCode = branchCode;           this.tellerId = tellerId;            this.subAccNo = subAcc;
            }
            
            public TellerFloat(int id, int assignId, String branchManagerId,int tellerId,String tellerName,
                    int floatAmount, boolean isTopup, boolean isWithd,String cDate,int accId, String accNo,
                    int totalFlat,String subAcc,String branchCode){
                    this.id = id;                           this.assignId = assignId;           this.floatAmount = floatAmount;
                    this.branchManagerId =branchManagerId;  this.tellerName = tellerName;       this.deposite = "";
                    this.withdrawal ="";                    this.isTopup = isTopup;             this.isWithdrawal = isWithd;
                    this.dateString = cDate;
                    this.accId = accId;                     this.totalFloat = totalFlat;         this.accNo = accNo;
                    this.branchCode = branchCode;           this.tellerId = tellerId;            this.subAccNo = subAcc;
            }
            public int getId(){return id;}
            public void setId(int id){this.id = id;}
            
            public int getAssignId(){return assignId;}
            public void setAssignId(int str){this.assignId = str;}
            
            public int  getFloatAmount(){return floatAmount;}
            public void setFloatAmount(int str){this.floatAmount = str;}
            
            public String  getBranchManagerId(){return branchManagerId;}
            public void setBranchManagerId(String str){this.branchManagerId = str;}
            
            public String  getTellerName(){return tellerName;}
            public void setTellerName(String str){this.tellerName = str;}
            
            public String  getDeposite(){return deposite;}
            public void setDeposite(String str){this.deposite = str;}
            
            public String  getWithdrawal(){return withdrawal;}
            public void setWithdrawal(String str){this.withdrawal = str;}
            
            public boolean  getIsTopup(){return isTopup;}
            public void setIsTopup(boolean str){this.isTopup = str;}
            
            public boolean  getIsWithdrawal(){return isWithdrawal;}
            public void setIsWithdrawal(boolean str){this.isWithdrawal = str;}
            
            public Date getCreatedDate(){return createdDate;}
            public  void setCreatedDate(Date createdDate ){ this.createdDate = createdDate;}
            
            public int getAccId(){return accId;}
            public void setAccId(int id){this.accId = id;}
            
            public int getTotalFloat(){return totalFloat;}
            public void setTotalFloat(int id){this.totalFloat = id;}
            
            public int getTellerId(){return tellerId;}
            public void setTellerId(int id){this.tellerId = id;}
            
            public String  getAccNo(){return accNo;}
            public void setAccNo(String str){this.accNo = str;}
            
            public String  getSubAccNo(){return subAccNo;}
            public void setSubAccNo(String str){this.subAccNo = str;}
            
            public String  getBranchCode(){return branchCode;}
            public void setBranchCode(String str){this.branchCode = str;}
            
            public String getDateString()
            {
                    
                    return dateString;
            }
            public void setDateString(String indate){
                  
                    this.dateString = indate;
            }

    
    
}
