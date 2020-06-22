/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.banking.settings.admin;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author dan
 */
public class Role implements Serializable {
    
        private static final long serialVersionUID = 1L;
    
        private int roleId,subId, userId  ;
        String roleName, branchcode;
        boolean isActive, isAdmin , isManager, isTeller, isRoot;
        Date createdDate;
        public Role(){
                roleId = 0;         subId = 0;              roleName = "";
                userId =0;          branchcode ="";         isActive =false;
                isAdmin = false;    isManager = false;      isTeller= false; 
                createdDate = new Date();
        }
        
        public int getRoleId(){return roleId;}
        public void setRoleId(int str){this.roleId = str;}
        
        public int getSubId(){return subId;}
        public void setSubId(int str){this.subId = str;}
        
        public int getUserId(){return userId;}
        public void setUserId(int str){this.userId = str;}
        
        public String getRoleName(){return roleName;}
        public void setRoleName(String str){this.roleName= str;}
        
        public String  getBranchcode(){return branchcode;}
        public void setBranchcode(String str){this.branchcode = str;}
        
        public boolean getIsAdmin(){return isAdmin ;}
        public void setisAdmin(boolean str){this.isAdmin = str;}
        
        public boolean getIsActive(){return isActive ;}
        public void setisActive(boolean str){this.isActive = str;}
        
        public boolean getIsTeller(){return isTeller ;}
        public void setisTeller(boolean str){this.isTeller = str;}
        
        public boolean getIsManager(){return isManager ;}
        public void setisManager(boolean str){this.isManager = str;}
        
        public Date getCreatedDate(){return createdDate ;}
        public void setCreatedDate(Date str){this.createdDate = str;}
   
}
