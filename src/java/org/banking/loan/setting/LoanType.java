/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.banking.loan.setting;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.Date;
import org.tracking.server.database.DatabaseBean;

/**
 *
 * @author dan
 */

public class LoanType implements Serializable {
        
        private int id,interestRate, gracePeriod ;
        private String loanCode ,loanName,subAcc,repaymentMethod,methodName;
        private double minPaidBridging, minPaidTopUp, maxAmount;
        private boolean isGuarantor;  
        private Date createDate ;
        
        public LoanType(){}
        
        public LoanType(int id,String loanCode,String loanName,String subAcc,double minBrid,
                double minTopUp, boolean isGuarantor,double maxAmount,String repaymentMethod,String methodName,
                int interestRate, int gracePeriod, Date createDate ){
                    this.id = id;                   this.loanCode = loanCode;       
                    this.loanName = loanName;       this.minPaidTopUp = minTopUp;   
                    this.subAcc = subAcc;           this.minPaidBridging = minBrid; 
                    this.isGuarantor = isGuarantor; this.maxAmount = maxAmount;
                    this.repaymentMethod = repaymentMethod;
                    this.interestRate = interestRate;
                    this.gracePeriod = gracePeriod; this.createDate = createDate;
                
        }
        
        public int getId(){return id;}
        public void setId(int id){this.id = id;}
        
        public int getInterestRate(){return interestRate;}
        public void setInterestRate(int interestRate ){this.interestRate = interestRate;}
        
        public int getGracePeriod(){return gracePeriod;}
        public void setGracePeriod(int gracePeriod){this.gracePeriod = gracePeriod;}
        
        public String getLoanCode(){return loanCode;}
        public void setLoanCode(String loanCode){this.loanCode = loanCode;}
        
        public String getLoanName(){return loanName;}
        public void setLoanName(String loanName){this.loanName = loanName;}
        
        public String getSubAcc(){return subAcc;}
        public void setSubAcc(String subAcc){this.subAcc = subAcc;}
        
        public String getRepaymentMethod(){return repaymentMethod;}
        public void setRepaymentMethod(String repaymentMethod){this.repaymentMethod = repaymentMethod;}
        
        public String getMethodName(){return methodName;}
        public void setMethodName(String methodName){this.methodName = methodName;}
        
        public double getMinPaidBridging(){return minPaidBridging;}
        public void setMinPaidBridging(double minPaidBridging){this.minPaidBridging = minPaidBridging;}
        
        public double getMinPaidTopUp(){return minPaidTopUp;}
        public void setMinPaidTopUp(double minPaidTopUp){this.minPaidTopUp = minPaidTopUp;}
        
        public double getMaxAmount(){return maxAmount;}
        public void setMaxAmount(double maxAmount){this.maxAmount = maxAmount;}
        
        public boolean getIsGuarantor(){return isGuarantor;}
        public void setIsGuarantor(boolean isGuarantor){this.isGuarantor = isGuarantor;}
        
        public Date getCreateDate(){return createDate;}
        public void setCreateDate(Date createDate){this.createDate = createDate;}
        public void upDateLoanType(){
               dbAction("UPDATE loanTypes SET loanName ='"+loanName+"',subAcc ='"+subAcc+"',minPaidBridging='"+minPaidBridging+"',minPaidTopUp='"+minPaidTopUp+"',"
                       + "isGuarantor='"+isGuarantor+"',maxAmount= '"+maxAmount+"',repaymentMethod='"+repaymentMethod+"', interestRate='"+interestRate+"',"
                       + "gracePeriod='"+gracePeriod+"', createDate= now() WHERE id ="+id+" ; ");
        }
        public void deleteLoanType(){
               dbAction("DELETE loanTypes WHERE id = "+id+"; ");
        }
        
        public void dbAction(String sql){
                        try{
                            DatabaseBean db = new DatabaseBean();
                            db.insert(sql);
                            db.cleanup();
                        }catch(SQLException e){
                            System.out.println("SQL Error In LoanType: "+ e);
                        }catch(Exception e){
                            System.out.println("Exception Error In LoanType: "+ e);
                        }        
        }
    
}
