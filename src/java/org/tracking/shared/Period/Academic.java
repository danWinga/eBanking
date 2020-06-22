/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.tracking.shared.Period;

/**
 *
 * @author root
 */
public class Academic {
   private  String period;
   private boolean isActive;
   private String name;
   private String code;
   
   public Academic(){
       period = "";
       code = "";
       name = "";
       isActive = false;
   }
   
   public Academic(String code,String name,String period, boolean isactive){
       this.code = code;
       this.name = name;
       this.period = period;
       this.isActive = isactive;
   }
   
   public String getCode(){return code;}
   public void setCode(String code){this.code = code;}
   
   public String getPeriod(){return period;}
   public void setPeriod(String period){this.period = period;}
   
   public String getName(){return name;}
   public void setName(String name){this.name = name;}
   
   public boolean getIsActive(){return isActive;}
   public void setIsActive(boolean isActive ){this.isActive = isActive;}
}
