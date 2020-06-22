/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.tracking.shared.schoolAccountFolder;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author root
 */
public class SchoolFolder implements Serializable {
    private int id ;
    private String accCode ;
    private String schoolCode ;
    private String accountName;
    private String description ;
    private Date createdDate;
    
    
    
    public SchoolFolder(){
       id = 0;
       accCode = "";
       accountName = "";
       schoolCode = "";
       description = "";
       createdDate = new Date();
    }
    public SchoolFolder(int id, String accn,String schcode,String accname,  String desc, Date createdd){ 
        this.id = id;
        this.accCode = accn;
        this.accountName = accname;
        this.schoolCode = schcode;
        this.description = desc;
        this.createdDate = createdd;
    }
    
    
    
    public void setId (int id){ this.id = id; }
    public int getId (){return id;}
    
    public void setAccCode (String accCode){ this.accCode = accCode.toUpperCase(); }
    public String getAccCode(){return accCode;}
    
    public void setSchoolCode(String schoolcode){this.schoolCode = schoolcode.toUpperCase();}
    public String getSchoolCode (){return schoolCode.toUpperCase();}
    
    public String getDescription(){return description;}
    public void setDescription(String desc){this.description = desc;}
    
    public Date getCreatedDate(){return createdDate;}
    public void setCreatedDate(Date createddate){this.createdDate = createddate;}
    
    public String getAccountName(){return accountName;}
    public void setAccountName(String accountName){this.accountName = accountName.toUpperCase();}
 }