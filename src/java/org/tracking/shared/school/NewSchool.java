/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.tracking.shared.school;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.Random;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import org.tracking.server.database.DatabaseBean;

/**
 *
 * @author root
 */
@ManagedBean
@SessionScoped
public class NewSchool implements Serializable {
    private String code;
    private String schname;
    private School newSchool;
    
    FacesContext facesContext = FacesContext.getCurrentInstance();
    HttpSession httpSession = (HttpSession)facesContext.getExternalContext().getSession(true);
    FacesMessage msg = null;
    
    public School getNewSchool(){
        return newSchool;
    }
    public void setNewSchool(School school){
        this.newSchool = school;
    }
    public String getCode(){
        return code;
    }
    public void setCode(String code){
        this.code = code.toUpperCase();
    }
    public String getSchName(){
        return schname;
    }
    public void setSchName(String schn){
        this.schname = schn.toUpperCase();
    }
    public void insert(){
        getRandom();
        saveData();
    }
    public void validate(){
        
    }
    public  void getRandom(){
        Random newrandom = new Random();
        int random = newrandom.nextInt(1000)+8880;
        System.out.println("newNo:"+ random);
        String randomCode = String.valueOf("sch/"+random);
        newSchool.setSchoolCode(randomCode);
    }
    public void saveData(){
        String mysql = "INSERT INTO schools (school_Code, school_Name, location,"
                + " address,contact1,contact2,contact_person,details,created_Date)"
                + " VALUES ('"+ newSchool.getSchoolCode()+"', '"+ newSchool.getSchoolName() +"',  "
                + "'"+ newSchool.getLocation() +"', '"+ newSchool.getAddress() +"',"
                + "'"+newSchool.getContact1()+"','"+newSchool.getContant2()+"','"+newSchool.getContactPerson()+"',"
                + "'"+newSchool.getDetails()+"','"+newSchool.getCreatedDate()+"');";
        System.out.println("New Classes SQL:"+ mysql);
        try{
            DatabaseBean db = new DatabaseBean();
            db.insert(mysql);
            msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "New School: ",newSchool.getSchoolName() + "Saved!");
            db.cleanup();
        } catch(SQLException e){
             System.out.println("Error 1: "+ e);
             msg = new FacesMessage(FacesMessage.SEVERITY_WARN,"Database Error!", "Check Database then try again!" );
              
        }catch(Exception e ){
             System.out.println("Error 2: "+ e);
        } 
    }
    
}
