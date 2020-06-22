/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.tracking.shared.school.schoolFiles;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author root
 */
public class SchoolFiles implements  Serializable {
   private int id ;
   private String fileCode;
   private String folderCode;
   private String fileName;
   private String description;
   private Date createdDate;
   
   public SchoolFiles(){
       
   }
   public SchoolFiles(int id, String str1, String str2,String str3,String str4, Date dat ){
       this.id = id;
       this.fileCode = str1;
       this.fileName = str2;
       this.folderCode = str3;
       this.description =str4;
       this.createdDate = dat;
   }
  
   public int getID(){return id;}
   public void setID(int id){this.id  = id;}
     
   public String getFileCode(){return fileCode;}
   public void setFileCode(String str){this.fileCode = str.toUpperCase();}
   
   public String getFileName(){return fileName;}
   public void setFileName(String str){this.fileName = str.toUpperCase();}
   
   public String getFolderCode(){return folderCode;}
   public void setFolderCode(String str){this.folderCode = str.toUpperCase();}
   
   public String getDescription(){return description;}
   public void setDescription(String str){this.description= str.toUpperCase();}
   
   public Date getCreatedDate(){return createdDate;}
   public void setCreatedDate(Date str){this.createdDate = str;}
  
   public String toString (){return getFileCode() +"-"+getFileName();}
}
