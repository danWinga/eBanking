/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.tracking.shared.classes;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.LazyDataModel;
import org.tracking.server.database.DatabaseBean;

/**
 *
 * @author root
 */
@ManagedBean(name="viewClassesBean")
@SessionScoped
public class ViewClassesBean implements Serializable {
    private   ArrayList<SchClass> classesList;
    private SchClass selectedClass;
    private String className;
    private LazyDataModel<SchClass> classModel;
    
    FacesContext facesContext = FacesContext.getCurrentInstance();
    HttpSession httpSession = (HttpSession)facesContext.getExternalContext().getSession(true);
    
    @PostConstruct
    public void init(){
       selectedClass =  new SchClass();
       classModel = new ClsDataModel(getClassesList());
    }
    public LazyDataModel<SchClass> getClsModel(){
        return classModel;
    }
    
    public SchClass getSelectedClass(){
        return selectedClass;
    }
    public void setSelectedClass(SchClass selectedClass ){
        this.selectedClass = selectedClass;
    }
    public String getClassName(){
        return className;
    }
    public void setClassName(String str){
        this.className = str;
    }
    
    public ArrayList<SchClass> getClassesList(){
       classesList = new ArrayList<SchClass>();
       String query = "SELECT * FROM classes";
       try{
           DatabaseBean db = new DatabaseBean();
           Connection con = db.DBconnect();
           PreparedStatement stmt = con.prepareStatement(query);
           ResultSet rs=db.preparedState(stmt);
           while(rs.next()){
                classesList.add(new SchClass (rs.getInt("id"),rs.getString("class_code"),
                       rs.getString("file_Code"),
                       rs.getString("class_name"),
                       rs.getInt("capacity"),
                       rs.getString("details"),rs.getDate("created_Date")));
           }
            rs.close();
             db.cleanup();
           }catch(SQLException e){
                        System.out.println("SQL Exception while getting Class record: "+ e);
           }catch(Exception e){
                        System.out.println("Exception while getting class records: "+ e);
           }
      
       return classesList;
   }
    
    public void editClass(){
        validate();
    }
    public void delete(){
        String msql = "DELETE * FROM classes WHERE class_code="+selectedClass.getClassCode()+"; ";
        try{
                DatabaseBean db = new DatabaseBean();
                db.update(msql);
                db.cleanup();
        }catch(SQLException e){
           System.out.println("SQL Error"+ e);
           FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Sorry  Database error! ", ":Contact Admin"));
        }catch(Exception e){
           System.out.println("Exception error"+e); 
           FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Sorry data Error! ", ":Contact Admin"));
        }
        
    }
    public void validate(){
        if(!(selectedClass.getClassName()== null)   ){
            System.out.println("Updating ..... please wait........");
            checkClassExist();
        }else{
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Sorry! ", ":Filling all records then try again"));
        }
    }
    public void update(){
        // String query = "SELECT class_id ,class_code, class_name FROM  classes"
        String mysql = "UPDATE classes  SET file_Code = '"+selectedClass.getFileCode()+"', class_name = '"+ selectedClass.getClassName()+"',"
                +  " capacity = '"+ selectedClass.getCapacity() +"',"
                + "details = '"+ selectedClass.getDetails()+"' WHERE class_code = '"+selectedClass.getClassCode()+"';";
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
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, " Record  ", selectedClass.getClassName()+":Updated  successfull."));
    }
    /****
     ***  Check if class name exist
    ****/
    public void checkClassExist(){
        int x = 0;
        
        //class_code = '"+newclasses.getClassCode().trim()+"' OR 
         String query = "SELECT * FROM  classes"
                 + " WHERE class_name ='"+selectedClass.getClassName().trim()+"';";
         try{
             DatabaseBean db = new DatabaseBean();
             Connection con = db.DBconnect();
             PreparedStatement stmt = con.prepareStatement(query);
             stmt.setFetchSize(100);
             ResultSet rs = db.preparedState(stmt);
             while(rs.next()){
                 //setClassCode(rs.getString("class_code"));
                 setClassName (rs.getString("class_name"));
                x++;
             }
             rs.close();
             db.cleanup();
         }catch(SQLException e){
             System.out.println("Error validating dublicates: "+ e);
              FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Database Error! ", ":Contact Admin  the try again"));
             
         }catch(Exception e ){
             System.out.println("Error 2: "+ e);
             FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Data Error! ", ":Contact Admin  the try again"));
         }
         System.out.println("Data Validating: code:-"+getClassName());
         if( x<1 || getClassName().equals(selectedClass.getClassName().trim()) ){
               System.out.println("Data is ready for saving please wait .....!");
             
            update();
             
         }else{
             FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Sorry! ", ":Class Name with exist"));
             System.out.println("Sorry!:   "+selectedClass.getClassName() + "Exist");
            
         }
    }
    public void clear(){
        selectedClass.setClassCode("");
        selectedClass.setClassID(0);
        selectedClass.setClassName("");
        
    }
    public void reset() {
        RequestContext.getCurrentInstance().reset("main1:form_edit_class:grid_edit_class");
    }
    
    public void onRowSelect(SelectEvent event ){
      
      FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Class selected! ", ((SchClass) event.getObject()).getClassCode()));
     
   }
    
}
