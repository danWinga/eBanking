/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.tracking.shared.StudentClassAssigment;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author root
 */
public class StudentClass implements Serializable{
    private int id;
    private String assgnCode,holderCode,streamCode,acdCode,status;
    private Date createdDate;
    private String firstname,lastname,streamname,acdname,midname;
    private String studentId, gender, contact1,email,classcode;

    
    public StudentClass(){
        id = 0;
        assgnCode ="";
        holderCode = "";
        streamCode = "";
        acdCode = "";
        status = "";
        studentId = "";
        gender = "";
        contact1= "";
        email = "";
        classcode = "";
        createdDate = new Date();
    }
    /***
     * 
     * @param id
     * @param assgn
     * @param holder
     * @param stream
     * @param adc
     * @param status
     * @param created 
     */
    public StudentClass(int id, String assgn,String holder,String stream,String adc,String  status,Date created){
        this.id = id;
        this.assgnCode = assgn;
        this.holderCode = holder;
        this.streamCode = stream;
        this.acdCode = adc;
        this.status = status;
        this.createdDate = created;
        
    }
    public StudentClass( String studid,String fname,String lname){
       
        this.assgnCode = studid;
        this.firstname = fname;
        this.lastname= lname;
               
    }
    public StudentClass(int id, String assgn,String studentid,String fname, String midname,
            String lname,String gender,String cont1,String email, String streamcode,String streamname,
            String classcode,String acdname,String  status,Date created){
                    this.id = id;
                    this.assgnCode = assgn;
                    this.studentId = studentid;
                    this.firstname = fname;
                    this.midname = midname;
                    this.lastname = lname;
                    this.gender = gender;
                    this.contact1 = cont1;
                    this.email = email;
                    this.streamCode = streamcode;
                    this.streamname = streamname;
                    this.classcode = classcode;
                    this.acdCode = acdname;
                    this.status = status;
                    this.createdDate = created;
        
    }
    public StudentClass(String assgn,String studentid,String fname, String midname,
            String lname,String gender,String cont1,String email, 
            String acdname,String  status){
                    
                    this.assgnCode = assgn;
                    this.studentId = studentid;
                    this.firstname = fname;
                    this.midname = midname;
                    this.lastname = lname;
                    this.gender = gender;
                    this.contact1 = cont1;
                    this.email = email;
                    this.acdCode = acdname;
                    this.status = status;
                   
        
    }
   
    
    public int getId(){return id;}
    public void setId(int id){this.id = id;}
    
    public String getAssgnCode(){return assgnCode;}
    public void setAssgnCode(String assgnCode ){this.assgnCode = assgnCode;}
    
    public String getStudentId(){return studentId;}
    public void setStudentId(String studentId ){this.studentId = studentId;}
    
    public String getGender(){return gender;}
    public void setGender(String gender ){this.gender = gender;}
    
    public String getContact1(){return contact1;}
    public void setContact1(String contact1 ){this.contact1 = contact1;}
    
    public String getEmail(){return email;}
    public void setEmail(String email ){this.email = email;}
    
    public String getClasscode(){return classcode;}
    public void setClasscode(String classcode ){this.classcode = classcode;}
    
    public String getHolderCode(){return holderCode;}
    public void setHolderCode(String holder){this.holderCode = holder;}
    
    public String getStreamCode(){return streamCode;}
    public void setStreamCode(String streamCode){this.streamCode = streamCode;}
    
    public String getAcdCode(){return acdCode;}
    public void setAcdCode(String acdCode){this.acdCode = acdCode;}
    
    public String getFirstname(){return firstname;}
    public void setFirstname(String firstname ){this.firstname = firstname;}
    
    public String getLastname(){return lastname;}
    public void setLastname(String lastname ){this.lastname = lastname;}
    
    public String getStreamname(){return streamname;}
    public void setStreamname(String streamname ){this.streamname = streamname;}
    
    public String getAcdname(){return acdname;}
    public void setAcdname(String acdname ){this.acdname = acdname;}
    
    public String getStatus(){return status;}
    public void setStatus(String status ){this.status = status;}
    
    public Date getCreatedDate(){return createdDate;}
    public void setCreatedDate(Date created){this.createdDate = created;}
    
    public String getMidname(){return midname;}
    public void setMidname(String midname){this.midname = midname;}
    
    @Override
    public String toString(){
        return getAssgnCode() +"-" + getHolderCode()+"- "+getStreamCode() +"-"+ getAcdCode() +"-"+getStatus();
    }
    
}
