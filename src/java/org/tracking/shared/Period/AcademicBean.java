/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.tracking.shared.Period;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
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
public class AcademicBean implements Serializable {
    private static final long serialVersionUID = 1L;
    private AcademicYear newYear= new AcademicYear() ;
    private AcademicYear editYear= new AcademicYear() ;
    private ArrayList<AcademicYear> editlist;
    
    FacesContext facesContext = FacesContext.getCurrentInstance();
    HttpSession httpSession = (HttpSession)facesContext.getExternalContext().getSession(true);
    
    public void init(){
        newYear = new AcademicYear();
        editYear = new AcademicYear();
    }
    
    public AcademicYear getNewYear(){return newYear;}
    public void setNewYear(AcademicYear newYear){this.newYear = newYear;}
    
    public AcademicYear getEditYear(){return editYear;}
    public void setEditYear(AcademicYear editYear){this.editYear = editYear;}
    
    
    /***
     * check active year
     * only one academic year is active at any time
     */
    public void checkActiveYear(){
        String  sql = " select * from  academic_year where is_active  = 'true';";
        int x = 0;
        try{
            DatabaseBean db = new DatabaseBean();
            Connection conn = db.DBconnect();
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = db.preparedState(stmt);
            while(rs.next()){
                
                editYear.setAcdCode(rs.getString("acd_Code"));
                editYear.setIsActive(rs.getBoolean("is_active"));
                x++;
            }
           rs.close();
           db.cleanup();
        }catch(SQLException e){
            System.out.println("SQL Exception while getting Academic year record : "+ e);
        }catch(Exception e){
             System.out.println("Exception while getting Academic year record : "+ e);
        }
        System.out.println("printing x :"+x);
        if(x> 0  && newYear.getIsActive() ==true ){
            System.out.println("clossing records");
            closePeriod();
            System.out.println("inserting newer details");
            addNew();
        }else{
            System.out.println("inserting record afresh");
            addNew();
        }
            
    }
    
    public void closePeriod(){
         String sql = "UPDATE academic_year  SET is_active = 'false'  WHERE acd_Code = '"+editYear.getAcdCode()+"';";
          System.out.println("printing .......................");
          try{
            DatabaseBean db = new DatabaseBean();
            Connection conn = db.DBconnect();
            System.out.println("printing ......................."+ sql);
            db.insert(sql);
            db.cleanup();
                        
        }catch(SQLException e){
            System.out.println("update SQl error "+e);
        }catch(Exception e){
            System.out.println("Update Exception error"+e);
        }
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, " Record  ", editYear.getStartYr()+":Updated  successfull.")); 
    }
    /**
     * addNew
     */
    
    public void addNew(){
        generateCode();
        newYear.setStartYr(newYear.getStryr());
        newYear.setEndYr(newYear.getEndyr());
        System .out.println(" Start year:" + newYear.getStartYr());
        System .out.println(" End year:" + newYear.getEndYr());
        
        String sql = "INSERT INTO academic_year (acd_Code, file_Code,acd_Name,start_year,end_year,is_active,description, created_Date)"
                        + " VALUES ( '"+ newYear.getAcdCode().trim() +"', '"+ newYear.getFileCode().trim() +"','"+ newYear.getAcdName().trim() +"',"
                + " '"+ newYear.getStartYr() +"', '"+ newYear.getEndYr() +"', '"+ newYear.getIsActive() +"', '"+ newYear.getDescription().trim() +"',now());";
        try{
            DatabaseBean db = new DatabaseBean();
            db.insert(sql);
        }catch (Exception e){
              System.out.println("Exception: "+ e);
              FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Data Error! ", ":Contact Admin  then try again"));
        }
       System.out.println("sql:"+ sql);
       FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "successfull! ", ":New year has been created"));
        
    }
    public void edit(){
        editYear.setStartYr(editYear.getStryr());
        editYear.setEndYr(editYear.getEndyr());
        String mysql = "UPDATE academic_year  SET file_Code= '"+editYear.getFileCode().trim()+"', start_year = '"+ editYear.getStartYr()+"',"
                +  " end_year = '"+ editYear.getEndYr() +"',"
                + "is_active = '"+ editYear.getIsActive()+"',description ='"+editYear.getDescription()+"' WHERE acd_Code = '"+editYear.getAcdCode()+"';";
        try{
            DatabaseBean db = new DatabaseBean();
            Connection conn = db.DBconnect();
            db.insert(mysql);
            db.cleanup();
                        
        }catch(SQLException e){
            System.out.println("update SQl error "+e);
        }catch(Exception e){
            System.out.println("Update Exception error"+e);
        }
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, " Record  ", editYear.getStartYr()+":Updated  successfull."));
    }
    
    /**
     * generating class  code 
     */
    public  void generateCode(){
        Random newrandom = new Random();
        int random = newrandom.nextInt(1000)+8880;
        System.out.println("newNo:"+ random);
        String randomCode = String.valueOf("fc/"+random);
       newYear.setAcdCode(randomCode);
    }
    
}
