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
public class County implements Serializable{
    
            private static final long serialVersionUID = 1L;
            int countyId;
            String countyCode, countyName; 
            Date createdDate;
            
            public int getCountyId(){return countyId;}
            public void setCountyId(int id){this.countyId = id;}
            
            public String getCountyCode(){return countyCode;}
            public void setCountyCode(String str){this.countyCode = str;}
            
            public String getCountyName(){return countyName;}
            public void setCountyName(String str){this.countyName = str;}
            
            public Date getCreatedDate(){return createdDate;}
            public void setCreatedDate(Date str){this.createdDate = str;}
            
            public County(){
                countyCode = "";
                countyId = 0;
                countyName = "";
                createdDate = new Date();
            }
            public County(String countyCode,  String countyName){
                        this.countyCode = countyCode;
                        this.countyName = countyName;
            }
    
}
