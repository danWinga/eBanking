/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.tracking.shared.users;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author root
 */
public class Users implements Serializable{
    
    private int id;
    private String userName ;
    private String currentpassword;
    private String oldPassword;
    private Date passwordChangeDate;
    private String mainPage;
    private String contentCenter;
    private String contentHeader;
    private Date createdDate;
    private String usercode;
    
    public Users(){
        
        userName            = "" ;
        id                  = 0;
        currentpassword     ="";
        oldPassword         ="";
        passwordChangeDate  =new Date();
        mainPage            ="" ;
        contentCenter       ="";
        contentHeader       ="";
        createdDate        =new Date();
    }
    /**
     * 
     * @param id
     * @param username
     * @param currntPass
     * @param oldpass
     * @param passchange
     * @param mainpage
     * @param contentCenter
     * @param contheader
     * @param createdDate
     */
    public Users(int id, String username,  String currntPass, String oldpass, Date passchange,
            String mainpage,String contentCenter, String contheader ,Date createdDate ){
        this.id = id;
        this.userName = username;
        this.currentpassword = currntPass;
        this.oldPassword = oldpass;
        this.passwordChangeDate = passchange;
        this.mainPage = mainpage;
        this.createdDate = createdDate;
        this.contentCenter = contentCenter;
        this.contentHeader = contheader;
    } 
    public Users(int id,String user,
            String currntPass ){
        this.id = id;
        this.userName = user;
        this.currentpassword = currntPass;
    }
     public Users( String user,  String currntPass ){
        
        
        this.userName = user;
        this.currentpassword = currntPass;
        
    }
     public Users(String user,String oldPass, String newPass ){
       
        this.userName = user;
        this.currentpassword = newPass;
        this.oldPassword =oldPass;
        
    }
    
    public Users(Users thisUser){
        this.id =thisUser.getId();
        this.userName = thisUser.getUserName();
        this.currentpassword = thisUser.getCurrentpassword();
        this.oldPassword = thisUser.getOldpassword();
        this.passwordChangeDate = thisUser.getPasswordChangeDate();
        this.mainPage = thisUser.getMainPage();
        this.contentCenter = thisUser.getContentCenter();
        this.contentHeader = thisUser.getContentHeader();
        this.createdDate = thisUser.getCreatedDate();
    }
    
    
    public String getUserName(){
        return userName ;
    }
    
    public void setUserName(String str){
        this.userName= str ;
    }
    
    public String getCurrentpassword(){
        return currentpassword ;
    }
    public void setCurrentpassword(String str){
        this.currentpassword = str ;
    }
    public String getOldpassword(){
        return oldPassword ;
    }
    public void setOldpassword(String str){
        this.oldPassword = str;
    }
    
    public Date getPasswordChangeDate(){
        return passwordChangeDate;
    }
    public void setPasswordChangeDate(Date str){
        this.passwordChangeDate = str;
    }
    public String getMainPage(){
        return mainPage ;
    }
    public void setMainPage(String str){
        this.mainPage = ((str !=null)? str.toLowerCase() :"");
    }
    public String getContentCenter(){
        return contentCenter ;
    }
    public void setContentCenter(String str){
        this.contentCenter = ((str !=null)? str :"");
    }
    public String getContentHeader(){
        return contentHeader ;
    }
    public void setContentHeader(String str){
        this.contentHeader = ((str !=null)? str:"");
    }
    public Date getCreatedDate(){
        return createdDate;
    }
    public void setCreatedDate(Date str){
        this.createdDate = str;
    }
    
    public int getId(){
        return id;
    }
    public void setId(int id){
        this.id = id;
    }
    
    public String getUserCode(){
        return usercode;
    }
    public void setUserCode(String usercode){
        this.usercode = usercode.toUpperCase();
    }
    
    @Override
    public String toString(){
        return getUserName()+ ","+ getCurrentpassword();
    }
    
    
            
}
