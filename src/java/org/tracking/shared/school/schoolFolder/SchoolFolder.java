/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.tracking.shared.school.schoolFolder;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author root
 */
public class SchoolFolder implements Serializable{
   private int id ;
   private String folder_Code;
   private String school_Code ;
   private String account_Name ;
   private String description ;
   private Date created_Date;
   
   public SchoolFolder(int id , String fc, String sc, String an, String ds, Date cd){
       
       this.id = id;
       this.folder_Code = fc;
       this.school_Code = sc;
       this.account_Name = an;
       this.description = ds;
       this.created_Date = cd;
   }
   public SchoolFolder(){
       id =0;
       folder_Code = "";
       school_Code = "";
       account_Name = "";
       description = "";
       created_Date = new Date();
   }
   public int getID(){return id;}
   public void setID(int id){this.id  = id;}
   
   public String getFolderCode(){return folder_Code;}
   public void setFolderCode(String str){this.folder_Code = str.toUpperCase();}
   
   public String getFolderName(){return account_Name;}
   public void setFolderName(String str){this.account_Name = str.toUpperCase();}
   
   public String getSchoolCode(){return school_Code;}
   public void setSchoolCode(String str){this.school_Code = str.toUpperCase();}
   
   public String getDescription(){return description;}
   public void setDescription(String str){this.description= str.toUpperCase();}
   
   public Date getCreatedDate(){return created_Date;}
   public void setCreatedDate(Date str){this.created_Date = str;}
  
   public String toString (){return getFolderCode() +"-"+getFolderName();}
  
}
