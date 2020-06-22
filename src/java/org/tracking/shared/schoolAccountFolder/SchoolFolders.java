/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.tracking.shared.schoolAccountFolder;

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
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.LazyDataModel;
import org.tracking.server.database.DatabaseBean;

/**
 *
 * @author root
 */
@ManagedBean(name= "schoolFolders")
@ViewScoped
public class SchoolFolders implements  Serializable{
    private static final long serialVersionUID = 1L;
    private ArrayList<SchoolFolder> schoolfolderList;
    private SchoolFolder selectedFolder;
    private SchoolFolder newFolder;
    private boolean isValid;
     
    private LazyDataModel<SchoolFolder> lazyModel;
    
    
    
    /*@PostConstruct
    public void init() {
        lazyModel = new SchoolFolderDataModel(getSchoolfolderList());
    }
    */
    
     FacesContext facesContext = FacesContext.getCurrentInstance();
     HttpSession httpSession = (HttpSession)facesContext.getExternalContext().getSession(true);
     @PostConstruct
     public void init(){
         selectedFolder = new SchoolFolder();
         lazyModel = new SchoolFolderDataModel(getSchoolfolderList());
     }
     
     public LazyDataModel<SchoolFolder> getLazyModel() {
         
        return lazyModel;
     }
     
     
     public  SchoolFolder getSelectedFolder(){
         return selectedFolder;
     }
     public void setSelectedFolder(SchoolFolder selectedFolder){
         System.out.println("Setting selected...");
         this.selectedFolder = selectedFolder;
     }
     public  SchoolFolder getNewFolder(){
         return newFolder;
     }
     public void setNewFolder(SchoolFolder newFolder){
         System.out.println("Setting New Folder...");
         this.newFolder = newFolder;
     }
     
     public void printTest(){ System.out.println("Clicked..."); }
     
     public ArrayList<SchoolFolder> getSchoolfolderList(){
         String query = "SELECT * FROM sch_Accounts";
                if(schoolfolderList == null){
                        schoolfolderList = getList(query);
                }
         return schoolfolderList;
     }
     public ArrayList<SchoolFolder> getList(String query) {
                ArrayList<SchoolFolder> custList = new ArrayList<SchoolFolder>();

                try{
                        DatabaseBean db = new DatabaseBean();
                        Connection con = db.DBconnect();
                        PreparedStatement stmt = con.prepareStatement(query);
                        ResultSet rs=db.preparedState(stmt);
                        while(rs.next()){
                                custList.add(new SchoolFolder(rs.getInt("id"), rs.getString("acc_Code"), rs.getString("school_Code"), rs.getString("account_Name"), rs.getString("description"), 
                                                        rs.getDate("created_Date")));
                        }
                        rs.close();
                        db.cleanup();
                }catch(SQLException e){
                        System.out.println("SQL Exception while getting SchoolFOlder: "+ e);
                }catch(Exception e){
                        System.out.println("Exception while getting SchoolFolder: "+ e);
                }
                return custList;
	}
        public void onRowSelect(SelectEvent event) {
        FacesMessage msg = new FacesMessage("Folder Selected", ((SchoolFolder) event.getObject()).getAccCode());
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
     
    /*********************************************************************************************************************
     * new folder
     */ 
        
     public void saveData(){
        getRandomCode();
        newFolder.setSchoolCode("CH333");
                String sql = "INSERT INTO sch_Accounts ( acc_Code, school_Code, account_Name, description, created_Date)"
                        + " VALUES ( '"+newFolder.getAccCode().trim() +"', '"+ newFolder.getSchoolCode().trim() +"', '"+ newFolder.getAccountName().trim() +"', '"+ newFolder.getDescription().trim() +"',now());";
                //System.out.println(sql);
                try {
                        DatabaseBean db = new DatabaseBean();
                        db.insert(sql);
                }   catch (Exception e){
                        System.out.println("Exception: "+ e);
                        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Data Error! ", ":Contact Admin  then try again"));
                }
                System.out.println("sql:"+ sql);
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "successfull! ", ":New Folder has been created"));
        }
    /**
     * generating   code 
     */
    public  void getRandomCode(){
        Random newrandom = new Random();
        int random = newrandom.nextInt(1000)+8880;
        System.out.println("new folder:"+ random);
        String randomCode = String.valueOf("sc/"+ random);
        newFolder.setAccCode( randomCode);
        System.out.println("New folder code:"+ newFolder.getAccCode());
        
    } 
    /********************************************************************************/
    /*******End of new folder record *********************************************/ 
    
    
    /**************************************************************************
     * ************File validation **********************************************
     * @param filename
     * @return 
     */
    
    public boolean getIsValid(String filename){
        isValid =false;
        String tempData = "";
        String sql = "SELECT * from sch_Accounts where  account_Name == '"+filename.trim()+"' ;";
        try{
            DatabaseBean db = new DatabaseBean();
            Connection con  = db.DBconnect();
            PreparedStatement stmt = con.prepareStatement(sql);
            ResultSet rs = db.preparedState(stmt);
            while(rs.next()){
                tempData = rs.getString("account_Name");
                
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

    /*********************************************************************************************
     * **************************Edit Record ***************************************************
     */
    public void edit(){
         
        getIsValid(selectedFolder.getAccountName());
        System.out.println("isvalid is :"+ isValid);
        if(isValid== true){
           String sql = "UPDATE sch_Accounts    SET account_Name= '"+selectedFolder.getAccountName().trim()+"',"
                +  " description= '"+selectedFolder.getDescription() +"' WHERE acc_Code ='"+selectedFolder.getAccCode().trim()+"';";
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
    /****************************************************************************************************
     * ***********************************delete record *************************************************
     */
    
    public void  delete(){
        String sql = "DELETE FROM sch_Accounts  WHERE acc_Code = '"+selectedFolder.getAccCode()+"';";
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
                    "Folder Record! ",selectedFolder.getAccountName()+ ":Record Deleted successfuly"));
        
    }
     
     
       
        
}
