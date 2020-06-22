            /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.banking.transaction.DepositeWithdraw;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author dan
 */
public class Transact implements Serializable{
    
            private static final long serialVersionUID = 1L;
            private int  tellerId,userId,idNo,paymentType, totalFloat,availableFloat,transAmount,
                    totalWithdrawal,accAvailableAmount, minimumBalance;
            private int accCurrentBal;
            private String transCode, accNumber,accName, branchName,fullName,contact,transactionType;
            private boolean isDeposite, isWithdrawal, isActive;
            private Date createdDate;
            private String title, memberNo, branchCode, groupCode,groupName, currencyCode;
            
            private  int bankCode, chequeNo;
            
            private String chequeDrawer, bankName,productCode,productName;
            private String address, email;
            
            public Transact(){
                    createdDate = new Date();
                    transactionType = "";
                    transAmount = 0;
                    totalFloat = 0;
                    accNumber = "";
                    address = "";
                    email = "";

            }
            
            public Transact(String memberNo , String title,String fullName,int IDNo,String address, String contact, String email,String accNo, String branchName, String groupCode, 
                    String groupName ,String productCode,String productName, String currencyCode,int totalBalance, int availableAmount, boolean isActive, String transCode,
                    String transtype,int paymentType,int floatAmount, boolean isDeposite, boolean isWithd, Date createdDate ){
                        this.memberNo = memberNo;                   this.title = title;                 this.fullName = fullName;
                        this.idNo = IDNo;                           this.accNumber = accNo;             this.branchName =branchName;
                        this.groupCode =groupCode;                  this.groupName = groupName;         this.currencyCode = currencyCode;
                        this.transAmount = floatAmount;            this.isDeposite = isDeposite;       this.isActive = isActive;
                        this.transCode = transCode;                 this.transactionType = transtype;   this.accCurrentBal= totalBalance;
                        this.accAvailableAmount= availableAmount;   this.address = address;             this.accAvailableAmount= availableAmount;  
            }
            
            public Transact(String memberNo , String title,String fullName,int IDNo,String address, String contact, String email,String accNo, String branchName, String groupCode, 
                    String groupName ,String productCode,String productName, String currencyCode,int totalBalance, int availableAmount, boolean isActive, Date createdDate ){
                        this.memberNo = memberNo;                   this.title = title;                 this.fullName = fullName;
                        this.idNo = IDNo;                           this.accNumber = accNo;             this.branchName = branchName;
                        this.groupCode =groupCode;                  this.groupName = groupName;         this.currencyCode = currencyCode;
                        this.isActive = isActive;                   this.address = address;             this.contact = contact;
                        this.email= email;                          this.productName = productName;     this.productCode =productCode;
                        this.totalFloat= totalBalance;   this.accAvailableAmount= availableAmount;  
            }
            public Transact(String transCode ,Date transDate, String transType ,int amount ){
                        this.transCode = transCode ;                this.transactionType = transType ;  
                        this.createdDate= transDate;
                         
            }
            public Transact(Transact thisTransact){
                    thisTransact. getTransCode();
                    
            }
            /**
            public LastTransaction(String transCode,Date transDate,String transType, int amount  ){
                    this.transCode = transCode;
                    this.lastTransDate = transDate;
                    this.transType = transType;
                    this.accLastTranAmount = amount;
            }**/
            
            public int getBankCode(){return bankCode;}
            public void setBankCode(int bankCode ){this.bankCode = bankCode;}
            
            public int getChequeNo(){return chequeNo;}
            public void setChequeNo(int chequeNo ){this.chequeNo = chequeNo;}
            
            public String getChequeDrawer(){return chequeDrawer;}
            public void setChequeDrawer(String chequeDrawer ){this.chequeDrawer = chequeDrawer;}
            
            public String getBankName(){return bankName;}
            public void setBankName(String bankName ){this.bankName = bankName;}
            
            public String getProductCode(){return productCode;}
            public void setProductCode(String productCode ){this.productCode = productCode;}
            
            public String getProductName(){return productName;}
            public void setProductName(String productName ){this.productName = productName;}
            
            public String getAddress(){return address;}
            public void setAddress(String address ){this.address = address;}
            
            public String getContact(){return contact;}
            public void setContact(String contact ){this.contact = contact;}
            
            public String getEmail(){return email;}
            public void setEmail(String email ){this.email = email;}
            
            public int getTellerId(){return tellerId;}
            public void setTellerId(int tellerId ){this.tellerId = tellerId;}
            
            public String getMemberNo(){return memberNo;}
            public void setMemberNo(String memberNo){this.memberNo = memberNo;}
            
            public String getTitle(){return title;}
            public void setTitle(String title){this.title = title;}
            
            public String getbranchCode(){return branchCode;}
            public void setBranchCode(String branchCode){this.branchCode = branchCode;}
            
            public String getGroupCode(){return groupCode;}
            public void setGroupCode(String groupCode){this.groupCode = groupCode;}
            
            public String getGroupName(){return groupName;}
            public void setGroupName(String groupName){this.groupName = groupName;}
            
            public String getCurrencyCode(){return currencyCode;}
            public void setCurrencyCode(String currencyCode){this.currencyCode = currencyCode;}
            
            public String getTransCode(){return transCode;}
            public void setTransCode(String transCode ){this.transCode = transCode;}
            
            public int getUserId(){return userId;}
            public void setUserId(int userId ){this.userId = userId;}
            
            public int getPaymentType(){return paymentType;}
            public void setPaymentType(int paymentType ){this.paymentType = paymentType;}
            
            public Boolean getIsDeposite(){return isDeposite;}
            public void setIsDeposite(boolean isDeposite ){this.isDeposite = isDeposite;}
            
            public Boolean getIsActive(){return isActive;}
            public void setIsActive(boolean isActive ){this.isActive = isActive;}
            
            public Boolean getIsWithdrawal(){return isWithdrawal;}
            public void setIsWithdrawal(boolean isWithdrawal ){this.isWithdrawal = isWithdrawal;}
            
            public Date getCeatedDate(){return createdDate;}
            public void setCreatedDate(Date createdDate ){this.createdDate = createdDate;}
            
            public String getTransactionType(){return transactionType;}
            public void setTransactionType(String transactionType ){this.transactionType =transactionType;}
            
            public int getIdNo(){return idNo;}
            public void setIdNo(int idno ){this.idNo = idno;}
            
            public int getTotalFloat(){return totalFloat;}
            public void setTotalFloat(int totalFloat ){this.totalFloat = totalFloat;}
            
            public int getAvailableFloat(){return availableFloat;}
            public void setAvailableFloat(int availableFloat ){this.availableFloat = availableFloat;}
            
            public int getTransAmount(){return transAmount;}
            public void setTransAmount(int transAmount ){this.transAmount = transAmount;}
            
            public int getTotalWithdrawal(){return totalWithdrawal;}
            public void setTotalWithdrawal(int totalWithdrawal ){this.totalWithdrawal = totalWithdrawal;}
            
            
            
            public int getAccCurrentBal(){return accCurrentBal;}
            public void setAccCurrentBal(int accCurrentBal ){this.accCurrentBal = accCurrentBal;}
            
            public int getAccAvailableAmount(){return accAvailableAmount;}
            public void setAccAvailableAmount(int accAvailableAmount ){this.accAvailableAmount = accAvailableAmount;}
            
            
            
            public String getAccNumber(){return accNumber;}
            public void setAccNumber(String  accNumber ){this.accNumber = accNumber;}
            
            public String getAccName(){return accName;}
            public void setAccName(String  accName ){this.accName = accName;}
            
            public String getBranchName(){return branchName;}
            public void setBranchName(String branchName){this.branchName = branchName;}
            
            public String getFullName(){return fullName;}
            public void setFullName(String fullName){this.fullName = fullName;}
            
            public void allValues(){
                System.out.println("all redord serach");
            }
            public int getMinimumBalance(){return minimumBalance;}
            public void setMinimumBalance(int minimumBalance){this.minimumBalance = minimumBalance;}
    
}
