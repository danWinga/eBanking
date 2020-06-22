/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.banking.settings.admin;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author dan
 */
public class BranchManager implements Serializable{
    
        private static final long serialVersionUID = 1L;
        private int id , managerId;
        private  String branchcode,firstName,fullName, middleName,lastName,contact, email;
        private Date createdDate;
        
        public BranchManager(){
            
        }
        
        public BranchManager(String fname ,String mname, String lname, String contact){
                    this.firstName = fname;         this.middleName = mname;
                    this.lastName = lname;          this.contact = contact;
            
        }
        
        public BranchManager(String fname , String contact){
                    this.fullName = fname; 
                    this.contact = contact;
            
        }
        
        
        public int getId(){return id;}
        public void setId(int id ){this.id = id;}
        
        public int getManagerId(){return managerId;}
        public void setManagerId(int id ){this.managerId = id;}
        
        public String  getBranchcode(){return branchcode;}
        public void setBranchcode(String str ){this.branchcode = str;}
        
        public String  getFirstName(){return firstName;}
        public void setFirstName(String str ){this.firstName = str;}
        
        public String  getFullName(){return fullName;}
        public void setFullName(String str ){this.fullName = str;}
        
        public String  getLastName(){return lastName;}
        public void setLastName(String str ){this.lastName = str;}
        
        public String  getMiddleName(){return middleName;}
        public void setMiddleName(String str ){this.middleName = str;}
        
        public String  getContact(){return contact;}
        public void setContact(String str ){this.contact = str;}
        
        public String  getEmail(){return email;}
        public void setEmail(String str ){this.email = str;}
        
        public Date getCreatedDate(){return createdDate;}
        public void setCreatedDate(Date cDate ){this.createdDate = cDate;}
   
}
