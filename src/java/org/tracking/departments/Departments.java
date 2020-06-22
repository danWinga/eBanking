/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.tracking.departments;

import java.io.Serializable;

/**
 *
 * @author root
 */
public class Departments implements Serializable{
   private String depCode;
   private String depName;
   private String details;
   private int depID;
   
   public Departments(){
       depCode = "";
       depName = "";
       details = "";
       depID = 0;
   }
   public Departments(int id,String code,String name, String detail ){
       depCode = code;
       depName = name;
       details = detail;
       depID = id;
   }
   public Departments(Departments thisDep){
       depCode = thisDep.getDepartmentCode();
       depName = thisDep.getDepartmentName();
       details = thisDep.getDetails();
       depID =thisDep.getDepID();
   }
   public Departments(String code,String name){
       depCode = code;
       depName = name;
       details = "";
       depID = 0;
   }
    public Departments(String code){
       depCode = code;
       depName = "";
       details = "";
       depID = 0;
   }
   
   public String getDepartmentCode(){
       return depCode;
   }
   public void setDepartmentCode(String str){
       depCode = str.toUpperCase();
   }
   
   public String getDepartmentName(){
       return depName;
   }
   
   public void setDepartmentName(String depName){
       this.depName = depName;
   }
   
   public String getDetails(){
       return details;
   }
   
   public void setDetails(String  dpt){
       this.details = dpt;
   }
   public int getDepID(){
       return depID;
   }
   public void setDepID(int id){
       this.depID = id;
   }
    
}
