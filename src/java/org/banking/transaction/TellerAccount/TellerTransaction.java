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
public class TellerTransaction implements Serializable{
            
        private static final long serialVersionUID = 1L;
        private int tellerId,userId, initialFloat,totalFloat, deposite, withdrawal;
        private String tellerName, userName;
        private Date currentDate;
        
        
        public TellerTransaction(){
                    this.tellerId = 0;          this.tellerName = "";
                    this.userId = 0;            this.totalFloat =0;
                    this.initialFloat =0;       this.deposite =0;
                    this.withdrawal = 0;        this.userName = "";
                    this.currentDate = new Date();
            
        }
        public TellerTransaction(String userName,int userId,int tellerId, String tellerName , 
                int initialFloat, int availableFloat ){
                    this.userName = userName; 
                    this.userId = userId;
                    this.tellerId = tellerId;
                    this.tellerName = tellerName;
                    this.initialFloat = initialFloat;
                    this.totalFloat = availableFloat;
        }
        public int getTellerId(){return tellerId;}
        public void setTellarId(int str){this.tellerId = str;}
        
        public int getUserId(){return userId;}
        public void setUserId(int str){this.userId = str;}
        
        public int getInitialFloat(){return initialFloat;}
        public void setInitialFloat(int str){this.initialFloat = str;}
        
        public int getTotalFloat(){return totalFloat;}
        public void setTotalFloat(int str){this.totalFloat = str;}
        
        public int getDeposite(){return deposite;}
        public void setDeposite(int str){this.deposite = str;}
        
        public int getWithdrawal(){return withdrawal;}
        public void setWithdrawal(int str){this.withdrawal = str;}
        
        public String  getUserName(){return userName;}
        public void setUserName(String str){this.userName = str;}
        
        public String  getTellerName(){return tellerName;}
        public void setTellerName(String str){this.tellerName = str;}
        
        public Date getCurrentDate(){return currentDate;}
        public void setCurrentDate(Date cdt){this.currentDate = cdt;}
            
    
}
