/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.banking.settings.location.county;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author dan
 */
public class Branch implements Serializable {
    
    private static final long serialVersionUID = -1714448615083738535L;
    private int id;
    private String countyCode, branchCode, branchName, physicalAddress, address,
            email,officeContact,faxno, status, town;
    private Date createdDate;
    
    public Branch(){}
    
    public  int getId(){
        return id;
    }
    
    public String getCountyCode(){
        return countyCode;
    }
    public void setCountryCode(String str){
        this.countyCode = str;
    }
    
    public String getBranchCode(){
        return branchCode;
    }
    public void setBranchCode(String str){
        this.branchCode = str;
    }
    
    public String getBranchName(){
        return branchName;
    }
    public void setBranchName(String str){
        this.branchName = str;
    }
    public String getPhysicalAddress(){
        return physicalAddress;
    }
    public void setPhysicalAddress(String str){
        this.physicalAddress = str;
    }
    
    public String getAddress(){
        return address;
    }
    public void setAddress(String str){
        this.address = str;
    }
    
    public String getEmail(){
        return email;
    }
    public void setEmail(String str){
        this.email = str;
    }
    
    public String getOfficeContact(){
        return officeContact;
    }
    public void setOfficeContact(String str){
        this.officeContact = str;
    }
    public String getFaxno(){
        return faxno;
    }
    public void setFaxno(String str){
        this.faxno = str;
    }
    public String getStatus(){
        return status;
    }
    public void setStatus(String str){
        this.status = str;
    }
    public String getTown(){
        return town;
    }
    public void setTown(String str){
        this.town = str;
    }
    public Date getCreatedDate(){
        return createdDate;
    }
    public void setCreatedDate(Date str){
        this.createdDate = str;
    }
}
