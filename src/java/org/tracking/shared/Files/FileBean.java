/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.tracking.shared.Files;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.LazyDataModel;
import org.tracking.server.database.DatabaseBean;
import javax.faces.application.FacesMessage;

/**
 *
 * @author root
 */
@ManagedBean(name = "fileBean")
@SessionScoped
public class FileBean  implements Serializable{
    private Files selectedFile;
    private ArrayList<Files> fileList;
    LazyDataModel<Files>fileModel;
    private  boolean isValid;
    
    FacesContext facesContext = FacesContext.getCurrentInstance();
    HttpSession httpSession = (HttpSession)facesContext.getExternalContext().getSession(true);
        @PostConstruct
    public void init(){
        selectedFile = new  Files();
        fileModel = new FileDataModel(getFiletList());
    }    
    FacesMessage msg = null;
    public Files getSelectedFile(){
        return selectedFile;
    }    
    public void setSelectedFile(Files selectedfile){
        this.selectedFile = selectedfile;
    }    
    public LazyDataModel<Files> getFileModel(){
        return fileModel;
    }   
    public void onRowSelect(SelectEvent event ){
       msg = new FacesMessage("file Selected", ((Files) event.getObject()).getFileCode());
       FacesContext.getCurrentInstance().addMessage(null, msg);
    }    
    public ArrayList<Files> getFiletList(){
        fileList = new ArrayList<Files>();
        String query = "SELECT * FROM account_Files";
       try{
           DatabaseBean db = new DatabaseBean();
           Connection con = db.DBconnect();
           PreparedStatement stmt = con.prepareStatement(query);
           ResultSet rs=db.preparedState(stmt);
           while(rs.next()){
               fileList.add(new Files(rs.getInt("id"),rs.getString("file_Code"),rs.getString("acc_Code"), 
                       rs.getString("file_Name"), rs.getString("description"),  rs.getDate("created_Date")));
           }
            rs.close();
             db.cleanup();
           }catch(SQLException e){
                        System.out.println("SQL Exception while getting Files: "+ e);
           }catch(Exception e){
                        System.out.println("Exception while getting Files: "+ e);
           }
       return fileList;
    }
    
    /***
     * Validating files
     * @param filename
     * @return 
     */
    public boolean getIsValid(String filename){
        isValid =false;
        String tempData = "";
        String sql = "SELECT * from account_Files where  file_Name == '"+filename.trim()+"' ;";
        try{
            DatabaseBean db = new DatabaseBean();
            Connection con  = db.DBconnect();
            PreparedStatement stmt = con.prepareStatement(sql);
            ResultSet rs = db.preparedState(stmt);
            while(rs.next()){
                tempData = rs.getString("file_Name");
                
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
    
    /***
     * Update record
     */
    public void edit(){
         
        getIsValid(selectedFile.getFlname());
        System.out.println("isvalid is :"+ isValid);
        if(isValid== true){
           String sql = "UPDATE account_Files    SET file_Name= '"+selectedFile.getFlname().trim()+"', "
                   + "acc_Code = '"+ selectedFile.getAccCode()+"',"
                +  " description= '"+selectedFile.getDescription() +"' WHERE file_code ='"+selectedFile.getFileCode().trim()+"';";
           try{
               DatabaseBean db = new DatabaseBean();
               System.out.println("sql:"+sql);
               db.insert(sql);
           }catch(Exception e){
               System.out.println("Error:" +e);
               FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, 
                    "Fatal Error! ", ":Kindly contact System administrator"));
           }
        }else{
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, 
                    "Dublicate ! ", ":Files Name Exist!"));
        }
    }
    
    /*** 
     * Delete record 
     */
    public void  delete(){
        String sql = "DELETE FROM account_Files  WHERE file_Code = '"+selectedFile.getFileCode()+"';";
        try{
            DatabaseBean db = new DatabaseBean();
            db.update(sql);
            db.cleanup();
        }catch(Exception e ){
            System.out.println("Error:" +e);
               FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, 
                    "Sorry! ", ":The record cannot be Delete"));
        }
        
               FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, 
                    "Subject Record! ", ":Record Deleted successfuly"));
        
    }
}

