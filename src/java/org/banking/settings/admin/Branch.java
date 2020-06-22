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
public class Branch implements Serializable{
    
        private static final long serialVersionUID = 1L;
        int  countyId, branchId;
        String countyName,  countyCode , branchCode, branchName,physicaladdress, 
                address, town, officeContact,email, status, fax;
        Date createDate ;   
        
        
        public Branch(){
            countyId = 0;           branchId = 0;           countyCode= "";
            countyName = "";        branchCode ="";         branchName = "";
            physicaladdress ="";    address = "";           town = "";      
            officeContact = "";     email = "";             status ="";
            createDate = new Date();
        }
        
        Branch(int id, String countyCode, String countyName,Date cDate){
                    this.countyId = id;
                    this.countyCode = countyCode;
                    this.countyName = countyName;
                    this.createDate = cDate;
        }
        
        Branch( String countyCode, String countyName){
                   
                    this.countyCode = countyCode;
                    this.countyName = countyName;
                    
        }
        public Branch(int id,String branchcode,String branchname,String countycode ,String countyName, String physicaladdress,
                String address,String town,String officeContact,String fax,String email,String status){
                    this.branchId = id;                 this.branchCode = branchcode;
                    this.branchName = branchname;       this.countyCode  = countycode;
                    this.countyName = countyName;       this.physicaladdress = physicaladdress;
                    this.address = address;             this.town  = town; 
                    this.officeContact = officeContact;  this.fax = fax;
                    this.email = email;                  this.status = status;
            
        }
        
        public Branch(int id,String branchcode,String countycode ,String branchname, String physicaladdress,
                String address,String town,String officeContact,String fax,String email,String status){
                    this.branchId = id;                 this.branchCode = branchcode;
                    this.branchName = branchname;       this.countyCode  = countycode;
                    this.physicaladdress = physicaladdress;
                    this.address = address;             this.town  = town; 
                    this.officeContact = officeContact;  this.fax = fax;
                    this.email = email;                  this.status = status;
            
        }
        
        public int getCountyId(){return countyId;}
        public void setCountyId(int id){this.countyId = id;}
        
        public int getBranchId(){return branchId;}
        public void setBranchId(int id){this.branchId = id;}
        
        public String getCountyCode(){return countyCode;}
        public void setCountyCode(String str){this.countyCode = str;}
        
        public String getBranchCode(){return branchCode;}
        public void setBranchCode(String str){this.branchCode = str;}
        
        public String getCountyName(){return countyName;}
        public void setCountyName(String str){this.countyName = str;}
        
        public String getBranchName(){return branchName;}
        public void setBranchName(String str){this.branchName = str;}
        
        public String getPhysicaladdress(){return physicaladdress;}
        public void setPhysicaladdress(String str){this.physicaladdress = str;}
        
        public String getAddress(){return address;}
        public void setAddress(String str){this.address = str;}
        
        public String getTown(){return town;}
        public void setTown(String str){this.town = str;}
        
        public String getOfficeContact(){return officeContact;}
        public void setOfficeContact(String str){this.officeContact = str;}
        
        public String getStatus(){return status;}
        public void setStatus(String str){this.status = str;}
        
        public String getFax(){return fax;}
        public void setFax(String str){this.fax = str;}
        
        public String getEmail(){return email;}
        public void setEmail(String str){this.email = str;}
        
        public Date getCreateDate(){return createDate;}
        public void setCreateDate(Date str){this.createDate = str;}
    
}
