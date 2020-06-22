/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.tracking.shared.classes.streams;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author root
 */

public class Stream implements Serializable{
    private String classCode,classname;
    private String streamName;
    private String details;
    private int capacity,id ;
    private String streamCode;
    private Date createdDate;
    
        
    public Stream(){
        id = 0;
        classCode ="";
        streamName ="";
        details = "";
        capacity = 0;
        classID = 0;
        streamCode = "";
        createdDate = new Date();
    }
    
    public Stream(int  id,String streamCode, String streamName,String classCode, String classname,
            String details){
        this.classID = id;
        this.classCode = classCode;
        this.streamName = streamName;
        this.details = details;
        this.streamCode = streamCode;
        this.classname = classname;
        
    }

    /**
     *
     * @param id
     * @param classCode
     * @param streamCode
     * @param streamName
     * @param details
     * @param capacity
     * @param creat
     */
    public Stream(int  id,String streamCode, String classCode, String streamName,int capacity,
            String details, Date creat ){
        this.classID = id;
        this.classCode = classCode;
        this.streamName = streamName;
        this.details = details;
        this.capacity = capacity;
        this.streamCode = streamCode;
        this.createdDate = creat;
        
    }
    public Stream( String classCode,String streamCode, String streamName,
            int capacity,String details ){
        this.classCode = classCode;
        this.streamName = streamName;
        this.details = details;
        this.capacity = capacity;
        this.streamCode = streamCode;
    }
    /**
     * 
     * @param classCode 
     * @param streamCode 
     */
    public Stream(String classCode,String streamCode){
        this.classCode = classCode;
        this.streamCode = streamCode;
    }
    /**
     * 
     * @param thisStream 
     */
    public Stream(Stream thisStream){
        classID = thisStream.getClassID();
        classCode = thisStream.getClassCode();
        streamName = thisStream.getStreamName();
        details = thisStream.getDetails();
        capacity = thisStream.getCapacity();
        streamCode= thisStream.getStreamCode();
    }
    public int getId(){return id;}
    public void setId(int id){this.id= id;}
    
    /**
     * 
     * @return classCode
     */
    public String getClassCode(){
        return this.classCode;
    }
    /**
     * 
     * @param str set new ClassCode
     */
    public void setClassCode(String str){
        this.classCode = str.toUpperCase();
    }
    /**
     * 
     * @return stream Name
     */
    public String getStreamName(){
        return streamName;
    }
    /**
     * 
     * @param str set new stream name 
     */
    public void setStreamName(String str){
        this.streamName =str.toUpperCase();
    }
    public String getDetails(){
        return this.details;
    }
    public void setDetails(String str){
        this.details = str.toUpperCase();
    }
    /**
     * 
     * @return capacity
     */
    
    public int getCapacity(){
        return this.capacity;
    }
    /**
     * 
     * @param capacity 
     */
    public void setCapacity(int capacity){
        this.capacity = capacity;
        
    }
    private int classID;
    /**
     * 
     * @return  classID
     */
    
    public int getClassID(){
        return classID;
    }
    public void setClassID(int classID){
        this.classID = classID;
    }
    public String getClassname(){return classname;}
    public void setClassname(String classname){this.classname = classname;}
    public String getStreamCode(){
        return streamCode;
    }
    public void setStreamCode(String str){
        this.streamCode = str.toUpperCase();
    }
    public Date getCreatedDate(){
        return createdDate;
    }
    public void setCreatedDate(Date createdData){
        this.createdDate = createdData;
    }
    @Override
    public String toString(){
        return getClassCode() + ","+ getStreamName();
    }
    
    
}
