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
public class County  implements Serializable{

    private static final long serialVersionUID = 7718263919885226233L;
    private int id;         private String countyCode, countyName;
    private Date createdDate;
    
    public County(){}
    public County(int id,String code, String cname,Date ctd){
        this.countyCode = code;
        this.countyName = cname;
        this.createdDate = ctd;
        this.id = id;
    }
    
    public String getCountyCode(){
        return countyCode;
    }
    public void setCountyCode(String countyCode){
        this.countyCode = countyCode;
    }
    
    public int getId(){
        return id;
    }
    public void setId(int id){
        this.id = id;
    }
    public String getCountyName(){
        return countyName;
    }
    public void setCountyName(String str){
        this.countyName = str;
    }
    public Date getCreatedDate(){
        return createdDate;
    }
    public void setCreatedDate(Date ctd){
        this.createdDate = ctd;
    }
}
