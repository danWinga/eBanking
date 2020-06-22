/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.tracking.shared.Period;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author root
 */
public class AcademicTerm  implements Serializable{
    private int id,startTrmYr,endTrmYr;
    private String acdCode, termCode,termName, description,status,period ;
    private Date startDate, endDate,createdDate;
    private boolean isActive;
     SimpleDateFormat formatDate;
    public AcademicTerm(){
        
    }
    public AcademicTerm(int id, String acdcode,String termcode, 
            String termname, Date strt, Date enddt, String desc,Date creat, boolean isactive){
        this.id = id;
        this.acdCode = acdcode;
        this.termCode = termcode;
        this.termName = termname ;
        this.startDate = strt;
        this.endDate = enddt;
        this.description = desc;
        this.createdDate = creat;
        this.isActive = isactive;
        
    }
    public AcademicTerm( String acdcode,String termcode, 
            String termname, Date strt, Date enddt,  boolean isactive){
        
        this.acdCode = acdcode;
        this.termCode = termcode;
        this.termName = termname ;
        this.startDate = strt;
        this.endDate = enddt;
        this.isActive = isactive;
        
    }
    public AcademicTerm( String termcode, 
            String termname, Date strt, Date enddt,  boolean isactive){
        
        
        this.termCode = termcode;
        this.termName = termname ;
        this.startDate = strt;
        this.endDate = enddt;
        this.isActive = isactive;
        
    }
    
    public AcademicTerm( String termcode, 
            String termname, String period,  boolean isactive){
        
        this.termCode = termcode;
        this.termName = termname ;
        this.period = period;
        this.isActive = isactive;
        
    }
    
    public int getStartTrmYr(){
      
      return startTrmYr;
  }
    public int getEndTrmYr(){
        return endTrmYr;
    }
    
    /**
     * 
     * @param strDate 
     */
  
    public void setStartYr(Date strDate){
      
        Calendar cal = Calendar.getInstance();
        cal.setTime(strDate);
        int yr = cal.get(Calendar.YEAR);
        this.startTrmYr = yr;
     }
    public void setEndTrmYr(Date endTrmYr){
        Calendar cal = Calendar.getInstance();
        cal.setTime(endTrmYr);
        int yr = cal.get(Calendar.YEAR);
        this.endTrmYr = yr;
        
    }
    public String getPeriod(){return period;}
    public void setPeriod (Date std, Date endd){
       formatDate = new SimpleDateFormat("dd/MM/yyyy");
        String strdt = formatDate.format(std);
        String strend=formatDate.format(endd);
        String strperiod = (strdt +"-"+ strend);
        this.period = strperiod;
                
    }
  
 
    public int getId(){return id;}
    public void setId(int id ){this.id = id;}
    
    public String getAcdCode(){return acdCode;}
    public void setAcdCode(String acdcode){this.acdCode = acdcode;}
    
    public String getTermCode(){return termCode;}
    public void setTermCode(String termcode){this.termCode = termcode;}
    
    public String getTermName(){return termName;}
    public void setTermName(String termname){this.termName = termname;}
    
    public String getDescription(){return description;}
    public void setDescription(String desc){this.description = desc;}
    
    public String getStatus(){return status;}
    public void setStatus(String status){this.status = status;}
    
    public Date getStartDate(){return startDate;}
    public void setStartDate(Date startDate){this.startDate = startDate;}
    
    public Date getEndDate(){return endDate;}
    public void setEndDate(Date endDate){this.endDate = endDate;}
    
    public Date getCreatedDate(){return createdDate;}
    public void setCreatedDate(Date createdDate){this.createdDate = createdDate;}
    
    public boolean getIsActive(){return isActive;}
    public void setIsActive(boolean isActive){this.isActive = isActive;}
    
}
