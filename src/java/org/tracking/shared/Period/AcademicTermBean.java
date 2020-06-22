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
import javax.annotation.PostConstruct;
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
public class AcademicTermBean implements Serializable{
    private AcademicTerm newTerm = new AcademicTerm() ;
    private AcademicTerm editTerm =  new AcademicTerm();
    private ArrayList<AcademicTerm> termList;
    private String activeYear;
    private int strtYr,endYr;
    private boolean isValidDate;
    
    
    
    FacesContext facesContext = FacesContext.getCurrentInstance();
    HttpSession httpSession = (HttpSession)facesContext.getExternalContext().getSession(true);
    
    public AcademicTerm getNewTerm(){return newTerm;}
    public  void  setNewTerm (AcademicTerm newterm){
        this.newTerm = newterm;
    }
    public AcademicTerm getEditTerm(){return editTerm;}
    public  void  setEditTerm (AcademicTerm editTerm){
        this.editTerm = editTerm;
    }
    public int getStrtYr(){return strtYr;}
    public void setStrtYr(int strtyr ){this.strtYr = strtyr;}
    
    public int getEndYr(){return endYr;}
    public void setEndYr(int endyr){this.endYr = endyr;}
    
    public boolean getIsValidDate(){return isValidDate;}
    public void setIsValidDate(boolean isValidDate){this.isValidDate = isValidDate;}
    
    public String getActiveYear(){
        activeYear = "";
        String sql ="SELECT * FROM academic_year where is_active = 'true' ;";
        try{
            DatabaseBean db = new DatabaseBean();
            Connection con = db.DBconnect();
            PreparedStatement stmt = con.prepareStatement(sql);
            ResultSet rs = db.preparedState(stmt);
            while(rs.next()){
                activeYear= rs.getString("acd_code");
                setStrtYr(rs.getInt("start_year"));
                setEndYr(rs.getInt("end_year"));
            }
            rs.close();
            db.cleanup();
            
        }catch(SQLException e){
        }catch(Exception e){
        }
        return activeYear;
    }
   
    
    /**
     * generating class  code 
     */
    public  void generateCode(){
        Random newrandom = new Random();
        int random = newrandom.nextInt(1000)+8880;
        System.out.println("newNo:"+ random);
        String randomCode = String.valueOf("ad/"+random);
       newTerm.setTermCode(randomCode);
       System.out.println(" term code ::"+ newTerm.getTermCode());
    }
    
    /***
     *   add new term
     */
    public void addNew(){
        getActiveYear();
        System.out.println("Academic active year:"+ getActiveYear());
         System.out.println("Academic year start date:"+ getStrtYr());
         System.out.println("Academic year enddate:"+ getEndYr()) ;
         System.out.println("Academic Term Start Date  :"+ newTerm.getStartDate() + "end Date "+ newTerm.getEndDate() ) ;
         System.out.println("Academic Term Start year  :"+ newTerm.getStartDate().getYear() + "end year "+ newTerm.getEndDate().getYear() ) ;
         
         validateDates();
        if(getIsValidDate()== true){
            generateCode();
            newTerm.setAcdCode(activeYear);
            System .out.println(" account code:" + newTerm.getAcdCode());
            System .out.println(" Start Date:" + newTerm.getStartDate());
            System .out.println(" End Date:" + newTerm.getEndDate());
        
            String sql = "INSERT INTO academic_term (term_Code, acd_Code,term_Name,start_Date,end_Date,description, "
                    + "created_Date,is_Active)"
                        + " VALUES ( '"+ newTerm.getTermCode().trim() +"', '"+ newTerm.getAcdCode().trim() +"',"
                    + "'"+ newTerm.getTermName().trim() +"',"
                + " '"+ newTerm.getStartDate() +"', '"+ newTerm.getEndDate() +"',  '"+ newTerm.getDescription().trim() +"',"
                    + "now(),'"+ newTerm.getIsActive() +"');";
        try{
            DatabaseBean db = new DatabaseBean();
            System.out.println(sql);
            db.insert(sql);
        }catch (Exception e){
              System.out.println("Exception: "+ e);
              FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, 
                      "Data Error! ", ":Contact Admin  then try again"));
        }
            System.out.println("sql:"+ sql);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
                    "successfull! ", ":New Academic Term has been created"));
        }else{
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, 
                    "Date Error! ", ":Check Date Formate , then try again"));
        }
        
    }
          
    /***
     * edit academic term
     */
        
    public void edit(){
         getActiveYear();
        validateDates();
        if(getIsValidDate()== true){
           String sql = "UPDATE academic_term  SET term_Name= '"+editTerm.getTermName().trim()+"', "
                   + "start_Date = '"+ editTerm.getStartDate()+"',"
                +  " end_Date = '"+ editTerm.getEndDate() +"',"
                + "status = '"+ editTerm.getStatus()+"',"
                   + "description ='"+editTerm.getDescription()+"' WHERE term_Code = '"+editTerm.getTermCode()+"';";
           try{
               DatabaseBean db = new DatabaseBean();
               db.insert(sql);
           }catch(Exception e){
               System.out.println("Error:" +e);
               FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, 
                    "Fatal Error! ", ":Kindly contact System administrator"));
           }
        }else{
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, 
                    "Date Error! ", ":Check Date Format , then try again"));
        }
        
        
    }
    /****
     * delete academic term
     * remember only empty term can be delete
     */
    
    public void delete(){
        String sql = "DELETE FROM academic_term  WHERE term_Code = '"+editTerm.getTermCode()+"';";
        try{
            DatabaseBean db = new DatabaseBean();
            db.update(sql);
            db.cleanup();
        }catch(Exception e ){
            System.out.println("Error:" +e);
               FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, 
                    "Fatal Error! ", ":Kindly contact System administrator"));
        }
        
               FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, 
                    "Academic Term! ", ":Record Deleted successfuly"));
    }
    
    /***
     * Check the  start date 
     * is less than the end date
     * academic year &&
     * check if term period is between academic year
     */
    public void validateDates( ){
        setIsValidDate(false);
        newTerm.setStartYr(newTerm.getStartDate());
        newTerm.setEndTrmYr(newTerm.getEndDate());
        if(newTerm.getEndDate().before(newTerm.getStartDate())){
            System.out.println(" Sorry! End date is less than Start Date");
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Date Error! ", 
                    ":End date is less than End Date"));
        }
        else if(newTerm.getEndDate().equals(newTerm.getStartDate())){
            System.out.println(" Sory! Wrong dates selected");
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Date Error! ", 
                    ":Wrong dates selected"));
        }
        else if((newTerm.getStartDate().before(newTerm.getEndDate())) && ((newTerm.getStartTrmYr())>= getStrtYr()) 
                && (newTerm.getEndTrmYr())<= getEndYr()){
            setIsValidDate(true);
            System.out.println(" Ok!date format is ok");
            
        }else{
            System.out.println(" Sorry!bad Date format");
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Date Error! ", 
                    ":Bad Date format"));
        }
        
    }
    
}
