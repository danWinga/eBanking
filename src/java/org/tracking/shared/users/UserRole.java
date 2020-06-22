/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.tracking.shared.users;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author root
 */
public class UserRole implements Serializable {
  
  private int roleid;
  private String rolename,details;
  private boolean isActive;
  private Date createdDate;
  
  public UserRole(){
      
  }
  public UserRole(int id, String nme, boolean isActive, String det,Date Createddate){
      
      this.roleid = id;
      this.rolename = nme;
      this.isActive = isActive;
      this.details = det;
      this.createdDate = Createddate;
  }
  
  
  public int getRoleId(){return roleid;}
  public void setRoleId(int roleid ){this.roleid = roleid;}
  
  public String getRolename(){return rolename;}
  public void setRolename(String rolename){this.rolename = rolename;}
  
  public String getDetails(){return details;}
  public void setGetDetails(String details){this.details = details;}
  
  public boolean getIsActive(){return isActive;}
  public void setIsActive(boolean isActive){this.isActive = isActive;}
  
  public Date getCreatedDate(){return createdDate;}
  public void setCreatedDate(Date createdDate){this.createdDate = createdDate;}
}
