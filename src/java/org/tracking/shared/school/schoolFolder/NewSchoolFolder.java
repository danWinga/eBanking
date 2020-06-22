/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.tracking.shared.school.schoolFolder;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import org.primefaces.context.RequestContext;
import org.tracking.server.database.DatabaseBean;

/**
 *
 * @author root
 */
@ManagedBean
@SessionScoped
public class NewSchoolFolder implements Serializable {
    private String folderCode;
    private String folderName;
    private SchoolFolder schoolFolder;
    
    
    RequestContext context = RequestContext.getCurrentInstance();
        FacesMessage msg = null;
    
    public  SchoolFolder getNewSchoolFolder(){
        return schoolFolder;
    }
    public void setNewSchoolFolder(SchoolFolder newSchoolFolder){
        this.schoolFolder = newSchoolFolder;
    }
    public String getFolderCode(){
        return folderCode;
    }
    public void setFolderCode(String folderCode){
        this.folderCode = folderCode;
    }
    
    public String getFolderName(){
        return folderName;
    }
    public void setFolderName(String folderName){
        this.folderName = folderName;
    }
    public void getSchoolCode(){
        String schoolcode = "CH333";
        String sql ="SELECT * FROM schools WHERE school_Code = '"+schoolcode.trim()+"'";
        try{
            DatabaseBean db = new DatabaseBean();
            Connection conn = db.DBconnect();
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setFetchSize(100);
            ResultSet rs = db.preparedState(stmt);
            while (rs.next()){
                schoolFolder.setSchoolCode(rs.getString("school_Code"));
                schoolFolder.setFolderName(rs.getString("school_Name"));
                
            }
            rs.close();
            db.cleanup();
            
        }catch(SQLException e){
            System.out.println("Error validating dublicates: "+ e);
              FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Database Error! ", ":Contact Admin  the try again"));
        }catch(Exception e){
            System.out.println("Error 2: "+ e);
             FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Data Error! ", ":Contact Admin  the try again"));
        }
    }
    /**
     * check if folder exist to avoid replication 
     */
    public void checkExistence(){
        int x = 0;
        String query="SELECT * FROM  sch_Accounts WHERE acc_Code = '"+schoolFolder.getFolderCode().trim()+"'";
        try{
            DatabaseBean db = new DatabaseBean();
            Connection conn = db.DBconnect();
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setFetchSize(100);
            ResultSet rs = db.preparedState(stmt);
            while (rs.next()){
                setFolderCode("acc_Code");
                setFolderName("account_Name");
                x++;
            }
            rs.close();
            db.cleanup();
        }catch(SQLException e){
            System.out.println("Error 1: "+ e);
             msg = new FacesMessage(FacesMessage.SEVERITY_FATAL,"Database Error!", "Check Database then try again!" );
        }catch(Exception e){
             System.out.println("Error 2: "+ e);
             msg = new FacesMessage(FacesMessage.SEVERITY_FATAL,"Database Error!", "Check Database then try again!" );
        }
        if(x<1){
            save();
        }
        System.out.println("Error Folder name with : "+ schoolFolder.getFolderName()+ "_ Exists");
         msg = new FacesMessage(FacesMessage.SEVERITY_WARN," Sorry!", schoolFolder.getFolderName()+" :Exist!" );
    }
    public void insert(){
        
    }
    public void save(){
        String mysql = "INSERT INTO sch_Accounts(acc_Code, school_Code, account_Name, description,created_Date)"
                + " VALUES ('"+ schoolFolder.getFolderCode().trim()+"', '"+ schoolFolder.getFolderName().trim() +"',  "
                + "'"+ schoolFolder.getDescription().trim() +"', now();";
        System.out.println("New School Department/ folder SQL:"+ mysql);
        try{
            DatabaseBean db = new DatabaseBean();
            db.insert(mysql);
            msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "New Department Name: ",schoolFolder.getFolderName() + "Saved!");
            db.cleanup();
        } catch(SQLException e){
             System.out.println("Error 1: "+ e);
             msg = new FacesMessage(FacesMessage.SEVERITY_WARN,"Database Error!", "Check Database then try again!" );
              
        }catch(Exception e ){
             System.out.println("Error 2: "+ e);
        }
        
    }
    public void update(){
        
    }
    public void refresh(){
        
    }
    
}
