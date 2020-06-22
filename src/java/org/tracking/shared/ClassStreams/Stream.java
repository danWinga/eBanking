/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.tracking.shared.ClassStreams;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author root
 */
public class Stream  implements Serializable{
    private String classCode;
    private String streamName;
    private String details;
    private int capacity, id;
    private String streamCode;
    private Date createdDate;
    
    public Stream (){
        id =0;
        streamCode = "";
        classCode = "";
        streamName  = ""; 
        capacity = 0;
        details = "";
        createdDate =new Date();
    }

    Stream(int id, String strcode, String clcode, String strname, int cap, String dtl, java.sql.Date date) {
        this.id =id;
        this.streamCode = strcode;
        this.classCode = clcode;
        this.streamName  = strname; 
        this.capacity = cap;
        this.details = dtl;
        this.createdDate = date;
    }
    
   
    
    public int getId(){return id;}
    public void setId(int id ){this.id = id;}
    
    public int getCapacity(){return capacity;}
    public void setCapacity(int cap){this.capacity = cap;}
    
    public String getClassCode(){return classCode;}
    public void setClassCode(String  classCode){this.classCode = classCode.toUpperCase();}
    
    public String getStreamCode(){return streamCode;}
    public void setStreamCode(String str){this.streamCode = str.toUpperCase();}
    
    public String getStreamName(){return streamName;}
    public void setStreamName(String str){this.streamName = str.toUpperCase();}
    
    public String getDetails(){return details;}
    public void setDetails(String str){this.details = str;}
    
    public Date getCreatedDate(){return createdDate;}
    public void setCreatedDate(Date createdDate){this.createdDate = createdDate;}
}
