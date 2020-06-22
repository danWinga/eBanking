/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.tracking.shared.Period;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 *
 * @author root
 */
public class AcademicYear implements Serializable{
   
  private int id;
  private String acdCode , acdName,fileCode, description;
  private int startYr, endYr;
  private Date createdDate,stryr,endyr;
  private boolean isActive;
  public String period;
  
  public AcademicYear(){
      
  }
  
  public AcademicYear(int id,String acd_Code, String file_Code,String acd_Name,int start_year,
          int end_year,boolean is_active,String description, Date created_Date ){
      this.id = id;
      this.acdCode = acd_Code;
      this.fileCode = file_Code;
      this.acdName  = acd_Name;
      this.startYr = start_year;
      this.endYr = end_year;
      this.isActive = is_active;
      this.description = description;
      this.createdDate =created_Date;
  }
  
  public AcademicYear(String acd_Code,String acd_Name,int start_year,
          int end_year,boolean is_active){
      
      this.acdCode = acd_Code;
      this.acdName  = acd_Name;
      this.startYr = start_year;
      this.endYr = end_year;
      this.isActive = is_active;
      
  }
  public AcademicYear(String acd_Code,String acd_Name,String period,boolean is_active){
      
      this.acdCode = acd_Code;
      this.acdName  = acd_Name;
      this.period  = period;
      this.isActive = is_active;
      
  }
  public AcademicYear (String acd_Code,boolean is_active){
      this.acdCode = acd_Code;
      this.isActive = is_active;
  }
  
  public String getPeriod(){return period;}
  public void setPeriod(int st, int end){
      String strSt = String.valueOf(st) +"-"+ String.valueOf(end);
      this.period = strSt;
  }
  
  public int getId(){return id;}
  public void setId(int id ){this.id = id;}
  
  public String getAcdCode(){return acdCode;}
  public void setAcdCode(String  acdCode){this.acdCode = acdCode.toUpperCase(Locale.ENGLISH);}
  
  public String getAcdName(){return acdName;}
  public void setAcdName(String acdName){this.acdName = acdName;}
  
  public String getFileCode(){return fileCode;}
  public void setFileCode(String fileCode){this.fileCode = fileCode;}
  
  public String getDescription(){return description;}
  public void setDescription(String description){this.description = description;}
  
  /***
   * 
   * @return 
   */
  public int getStartYr(){
      
      return startYr;
  }
  /**
   * 
   * @param strDate 
   */
  public void setStartYr(Date strDate){
      
      Calendar cal = Calendar.getInstance();
        cal.setTime(strDate);
        int yr = cal.get(Calendar.YEAR);
      this.startYr = yr;
  }
  
  /**
   * 
   * @return 
   */
  public int getEndYr(){
      
    return endYr;
  }
  /**
   * 
   * @param endDate 
   */
  public void setEndYr(Date endDate){
      Calendar cal = Calendar.getInstance();
        cal.setTime(endDate);
        int yr = cal.get(Calendar.YEAR);
      this.endYr = yr;
  }
 
  public Date getCreatedDate(){return createdDate;}
  public void setCreatedDate(Date createdDate ){this.createdDate = createdDate;}
  
  public Date getStryr (){return stryr; }
  public void setStryr(Date stryr){this.stryr = stryr;}
  
  public Date getEndyr (){return endyr; }
  public void setEndyr(Date endyr){this.endyr = endyr;}
  
  public boolean getIsActive(){return isActive;}
  public void setIsActive(boolean isActive){this.isActive = isActive;}
  
}
