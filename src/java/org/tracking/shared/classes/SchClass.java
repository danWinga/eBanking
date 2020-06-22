/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.tracking.shared.classes;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author root
 */

public class SchClass implements Serializable{
    private String classCode;
    private String className;
    private String details;
    private int capacity;
    private String fileCode;
    private Date createdDate;
    
        
    public SchClass(){
        classCode ="";
        className ="";
        details = "";
        capacity = 0;
        classID = 0;
        fileCode = "";
        createdDate = new Date();
    }/** 
     * 
     * @param id
     * @param classCode
     * @param className
     * @param details
     * @param capacity 
     */

    /**
     *
     * @param id
     * @param classCode
     * @param filecode
     * @param className
     * @param details
     * @param capacity
     * @param creat
     */
    public SchClass(int  id, String classCode, String filecode,String className,int capacity,
            String details, Date creat ){
        this.classID = id;
        this.classCode = classCode;
        this.className = className;
        this.details = details;
        this.capacity = capacity;
        this.fileCode = filecode;
        this.createdDate = creat;
        
    }
    public SchClass( String classCode,String filecode, String className,
            int capacity,String details ){
        this.classCode = classCode;
        this.className = className;
        this.details = details;
        this.capacity = capacity;
        this.fileCode = filecode;
    }
    /**
     * 
     * @param classCode 
     * @param filecode 
     */
    public SchClass(String classCode,String filecode){
        this.classCode = classCode;
        this.fileCode = filecode;
    }
    /**
     * 
     * @param thisClasses 
     */
    public SchClass(SchClass thisClasses){
        classID = thisClasses.getClassID();
        classCode = thisClasses.getClassCode();
        className = thisClasses.getClassName();
        details =thisClasses.getDetails();
        capacity = thisClasses.getCapacity();
        fileCode = thisClasses.getFileCode();
    }
    
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
     * @return class Name
     */
    public String getClassName(){
        return this.className;
    }
    /**
     * 
     * @param str set new class name 
     */
    public void setClassName(String str){
        this.className =str.toUpperCase();
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
    public String getFileCode(){
        return fileCode;
    }
    public void setFileCode(String str){
        this.fileCode = str;
    }
    public Date getCreatedDate(){
        return createdDate;
    }
    public void setCreatedDate(Date createdData){
        this.createdDate = createdData;
    }
    @Override
    public String toString(){
        return getClassCode() + ","+ getClassName();
    }
    
    
}
