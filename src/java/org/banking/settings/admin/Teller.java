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
public class Teller implements Serializable {

    private static final long serialVersionUID = 1L;
    private int id;
    private String  tellerName , branchCode;
    private boolean isActive;
    private Date createdDate ;
    
    public Teller(){
        id = 0;                  this.tellerName = "";       this.branchCode = "";
        this.isActive = false;   this.createdDate = new Date();     
    }
    public Teller(int id, String tellerName, String branchCode,boolean isActive){
        this.id = id;                   this.tellerName = tellerName;
        this.branchCode= branchCode;    this.isActive = isActive;
    }
    
    public int getId(){return id;}
    public void setId(int str){this.id = str;}
    
    public String getTellerName(){return tellerName;}
    public void setTellerName(String str){this.tellerName = str;}
    
    public String getBranchCode(){return branchCode;}
    public void setBranchCode(String str){this.branchCode = str;}
    
    public boolean getIsActive(){return isActive;}
    public void setIsActive(boolean isActive){this.isActive = isActive;}
    
    public Date getCreatedDate(){return createdDate;}
    public void setCreatedDate(Date str){this.createdDate = str;}
    
}
