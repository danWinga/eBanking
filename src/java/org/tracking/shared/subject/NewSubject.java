/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.tracking.shared.subject;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
public class NewSubject implements Serializable{
    private Subject  newSubject = new Subject();
    private Subject  editSubject = new Subject();
    private  boolean isValid;
   
    
    FacesContext facesContext = FacesContext.getCurrentInstance();
    HttpSession httpSession = (HttpSession)facesContext.getExternalContext().getSession(true);
    
    public Subject getNewSubject(){
        return newSubject;
    }
    public void setNewSubject(Subject newsubject){
        this.newSubject = newsubject;
    }
    
    public Subject getEditSubject(){return editSubject;}
    public void setEditSubject(Subject editSubject){
        this.editSubject = editSubject;
    }
    
    
    public void addNew(){
        getIsValid(newSubject.getSubjectName());
        if(isValid== true){
            generateSubjectCode();
            String sql = "INSERT INTO subjects (subj_Code, file_Code, subject_Name, subject_ShortName,details, created_Date)"
                        + " VALUES ( '"+ newSubject.getSubjectCode().trim() +"', '"+ newSubject.getFileCode().trim() +"', "
                + "'"+ newSubject.getSubjectName().trim() +"', '"+ newSubject.getShortName().trim() +"',"
                + "'"+ newSubject.getDetails().trim() +"',now());";
        try{
            DatabaseBean db = new DatabaseBean();
            db.insert(sql);
        }catch (Exception e){
              System.out.println("Exception: "+ e);
              FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Data Error! ", ":Contact Admin  then try again"));
        }
            System.out.println("sql:"+ sql);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "successfull! ", ":New Subject has been created"));
        }
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, 
                    "Dublicate ! ", ":Subject Name Exist!"));
    }
    
    public  void generateSubjectCode(){
        Random newrandom = new Random();
        int random = newrandom.nextInt(1000)+8880;
        System.out.println("newNo:"+ random);
        String randomCode = String.valueOf("subj/"+random);
        newSubject.setSubjectCode(randomCode);
    }
    /***
     * Validating subject Name
     * @param subjname
     * @return 
     */
    public boolean getIsValid(String subjname){
        isValid =false;
        String tempData = "";
        String sql = "SELECT * from subjects where  subject_Name = '"+subjname.trim()+"' ;";
        try{
            DatabaseBean db = new DatabaseBean();
            Connection con  = db.DBconnect();
            PreparedStatement stmt = con.prepareStatement(sql);
            ResultSet rs = db.preparedState(stmt);
            while(rs.next()){
                tempData = rs.getString("subject_Name");
                
            }
            rs.close();
            db.cleanup();
        }catch(SQLException e){
        }catch(Exception e ){
        }
        if(tempData.isEmpty()){
            return isValid = true;
        }
        return isValid;
    }
    public void setIsValid(boolean isValid){this.isValid = isValid;}
    
    
}
