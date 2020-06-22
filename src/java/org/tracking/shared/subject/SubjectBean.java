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
import java.util.ArrayList;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.LazyDataModel;
import org.tracking.server.database.DatabaseBean;

/**
 *
 * @author root
 */
@ManagedBean
@SessionScoped
public class SubjectBean  implements Serializable {
    private ArrayList<Subject> subjectList;
    private Subject selectedSubject;
    private LazyDataModel<Subject> subModel;
    private  boolean isValid;
    
    FacesContext facesContext = FacesContext.getCurrentInstance();
     HttpSession httpSession = (HttpSession)facesContext.getExternalContext().getSession(true);
     
   public LazyDataModel <Subject>getSubModel(){
       subModel = new SubjectDataModel(getSubjectList());
       return subModel;
   }
   public ArrayList<Subject> getSubjectList(){
       subjectList = new ArrayList<Subject>();
       String query = "SELECT * FROM subjects";
       try{
           DatabaseBean db = new DatabaseBean();
           Connection con = db.DBconnect();
           PreparedStatement stmt = con.prepareStatement(query);
           ResultSet rs=db.preparedState(stmt);
           while(rs.next()){
               subjectList.add(new Subject(rs.getInt("id"),rs.getString("subj_Code"),rs.getString("file_Code"), 
                       rs.getString("subject_Name"), rs.getString("subject_ShortName"), rs.getString("details"), rs.getDate("created_Date")));
           }
            rs.close();
             db.cleanup();
           }catch(SQLException e){
                        System.out.println("SQL Exception while getting Subject: "+ e);
           }catch(Exception e){
                        System.out.println("Exception while getting Subject: "+ e);
           }
      
       return subjectList;
   }
   /***
     * Update record
     */
    public void edit(){
         
        getIsValid(selectedSubject.getSubjectName());
        System.out.println("isvalid is :"+ isValid);
        if(isValid== true){
           String sql = "UPDATE subjects  SET subject_Name= '"+selectedSubject.getSubjectName().trim()+"', "
                   + "subject_ShortName = '"+ selectedSubject.getShortName()+"',"
                +  " details = '"+selectedSubject.getDetails() +"';";
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
                    "Dublicate ! ", ":Subject Name Exist!"));
        }
        
        
    }
    /*** 
     * Delete record 
     */
    public void  delete(){
        String sql = "DELETE FROM subjects  WHERE subj_Code = '"+selectedSubject.getSubjectCode()+"';";
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
                    "Subject Record! ", ":Record Deleted successfuly"));
        
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
    
   
   public Subject getSelectedSubject(){
       return selectedSubject;
   }
   public void setSelectedSubject(Subject selectedSubject){
       this.selectedSubject = selectedSubject;
   }
   public void onRowSelect(SelectEvent event ){
       FacesMessage msg = new FacesMessage("Folder Selected", ((Subject) event.getObject()).getSubjectCode());
        FacesContext.getCurrentInstance().addMessage(null, msg);
   }
   
   
}
