/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.tracking.shared.school;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author root
 */
public class School  implements Serializable{
   private int id; 
   private String school_Code;
   private String   school_Name;
   private String location;
   private String address;
   private String contact1;
   private String contact2;
   private String contact_person;
   private String details;
   private Date created_Date;
   
   public School(){
       id = 0;
       school_Code = "";
       school_Name = "";
       location = "";
       address = "";
       contact1= "";
       contact2 ="";
       contact_person = "";
       details ="";
       created_Date =new Date();
   }
   public School(int id, String schCod, String schName,
           String loc, String adr, String con1,String con2,
           String cop,String dt , Date crd){
       this.id = id;
       this.school_Code = schCod.toUpperCase();
       this.school_Name = schName.toUpperCase();
       this.location = loc.toUpperCase();
       this.address = adr.toUpperCase();
       this.contact1 = con1.toUpperCase();
       this.contact2 = con2.toUpperCase();
       this.contact_person =cop.toUpperCase();
       this.details  = dt.toUpperCase();
       this.created_Date = crd;
       
   }
   public School(School thisSchool){
       thisSchool.getID();
       thisSchool.getSchoolCode();
       thisSchool.getSchoolName();
       thisSchool.getLocation();
       thisSchool.getDetails(); 
       thisSchool.getCreatedDate();
       thisSchool.getContant2();
       thisSchool.getContactPerson();
       thisSchool.getContact1();
       thisSchool.getAddress();
   }
   public School(String code, String sname){
       this.school_Code = code;
       this.school_Name = sname;
   }
   public School(String code){
       this.school_Code = code;
   }
   public int getID(){
       return id;
   }
   public void setID(int id){
       this.id = id;
   }
   public String getSchoolCode(){
       return school_Code.toUpperCase();
   }
   public void setSchoolCode(String sch){
       this.school_Code = sch.toUpperCase();
   }
   public String getSchoolName(){
       return school_Name.toUpperCase();
   }
   public void setSchoolName(String str){
       this.school_Name = ((str.toUpperCase())!= null)? str.toUpperCase() :"";
   }
   public String getLocation(){
       return location;
   }
   public void setLocation(String str){
       this.location = str.toUpperCase();
   }
   public String getAddress(){
       return  address;
   }
   public void setAddress(String str){
       this.address = str.toUpperCase();
   }
   public String getContact1(){
       return contact1;
   }
   public void setContant1(String str){
       this.contact1 = str.toUpperCase();
   }
   public String getContant2(){
       return contact2 ;
   }
   public void setContact2(String str){
       this.contact2 = str.toUpperCase();
   }
   public String getContactPerson(){
       return contact_person;
   }
   public void setContactPerson(String str){
       this.contact_person = str.toUpperCase();
   }
   public String getDetails(){
       return details;
   }
   public void setDetails(String str){
       this.details = str.toUpperCase();
   }
   public Date getCreatedDate(){
       return created_Date;
   }
   public void setCreatedDate(Date str){
       this.created_Date = str;
   }
   public String toString(){
       return getSchoolName()+"-In :"+ getLocation();
   }
   
}
