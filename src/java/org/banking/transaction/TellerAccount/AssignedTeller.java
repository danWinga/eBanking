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
public class AssignedTeller implements Serializable  {

    private static final long serialVersionUID = 1L;
         int assignId  , tellerId ,managerId ,userId;
         int initialFloat,availableFloat;
         String  status, fullName,firstName,middleName,lastName;
         Date createdDate;
         
         public AssignedTeller(){
             this.firstName = "";       this.middleName = "";
             this.lastName = "";        this.userId = 0;
             this.initialFloat = 0;     this.tellerId = 0;
             this.managerId =0;         this.assignId =0;
             this.availableFloat = 0;    this.createdDate = new Date();
         }
         public AssignedTeller(String fname,String mname,String lname, int userid){
             this.firstName  =fname;
             this.middleName = mname;
             this.lastName = lname;
             this.userId = userid;
         }
         
         public AssignedTeller(String fname, int userid){
             this.fullName  = fname;
             this.userId =  userid;
         }
         
         
         public int getAssignId(){ return assignId;}
         public void setAssignId(int assId){this.assignId = assId;}
         
         public int getTellerId(){ return tellerId;}
         public void setTellerId(int tellerId){this.tellerId = tellerId;}
         
         public int getManagerId(){ return managerId;}
         public void setManagerId(int managerId){this.managerId = managerId;}
         
         public int getUserId(){ return userId;}
         public void setUserId(int userId){this.userId = userId;}
         
         public int getInitialFloat(){ return initialFloat;}
         public void setInitialFloat(int initialFloat){this.initialFloat = initialFloat;}
         
         public int getAvailableFloat(){ return availableFloat;}
         public void setAvailableFloat(int availableFloat){this.availableFloat = availableFloat;}
         
         public String getStatus(){ return status;}
         public void setStatus(String status){this.status = status;}
         
         public String getFullName(){ 
             return fullName ;}
         public void setFullName(String username){this.fullName = username;}
         
         public String getFirstName(){ return firstName;}
         public void setFirstName(String str){this.firstName = str;}
         
         public String getMiddleName(){ return middleName;}
         public void setMiddleName(String str){this.middleName = str;}
         
         public String getLastName(){ return lastName;}
         public void setLastName(String str){this.lastName = str;}
         
         public Date getCreatedDate(){ return createdDate;}
         public void setCreatedDate(Date createdDate){this.createdDate = createdDate;}
    
    
}
