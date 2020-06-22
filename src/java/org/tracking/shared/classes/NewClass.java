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
import java.util.Random;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import org.primefaces.context.RequestContext;
import org.tracking.server.database.DatabaseBean;


/**
 *
 * @author root
 */
@ManagedBean
@SessionScoped
public class NewClass implements Serializable {
    private SchClass newclasses;
    private String classCode ;
    private String className ;
    
    FacesContext context = FacesContext.getCurrentInstance();
    HttpSession httpSession = (HttpSession)context.getExternalContext().getSession(true);
     FacesMessage msg = null;
    
     @PostConstruct
     public void init(){
         newclasses = new SchClass();
     }
    public SchClass getNewClasses(){
        return newclasses;
    }
    public void setNewlasses(SchClass classes){
        this.newclasses= classes ;
    }
    public String getClassCode(){
        return classCode;
    }
    public void setClassCode(String str){
        this.classCode = str;
    }
    public String getClassName(){
        return className;
    }
    public void setClassName(String str){
        this.className = str;
    }
    public void saveData(){
        String mysql = "INSERT INTO classes (class_code,file_Code, class_name, capacity, details, created_Date)"
                + " VALUES ('"+ newclasses.getClassCode()+"', '"+ newclasses.getFileCode()+"','"+ newclasses.getClassName() +"',  "
                + "'"+ newclasses.getCapacity() +"', '"+ newclasses.getDetails() +"',now());";
        System.out.println("New Classes SQL:"+ mysql);
        try{
            DatabaseBean db = new DatabaseBean();
            db.insert(mysql);
            msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "New Class: ",newclasses.getClassName() + "Saved!");
            db.cleanup();
        } catch(SQLException e){
             System.out.println("Error 1: "+ e);
             msg = new FacesMessage(FacesMessage.SEVERITY_WARN,"Database Error!", "Check Database then try again!" );
              
        }catch(Exception e ){
             System.out.println("Error 2: "+ e);
        } 
    }
    public void test(){
        System.out.println("class_code :"+newclasses.getClassCode()+ "\t class_Name "+newclasses.getClassName()+
                "\t class_Name "+newclasses.getCapacity()+ "\t class_Name "+newclasses.getDetails() );
    }
    public void insertRecord(){
        getRandom();
        System.out.println("classname:"+ newclasses.getClassName());
        boolean recordIn = false;
        if(!(newclasses.getClassCode()).isEmpty() && !(newclasses.getClassName()).isEmpty()){
            recordIn = true;
            checkClassExist();
            //msg = new FacesMessage(FacesMessage.SEVERITY_INFO,"New Class",newclasses.getClassName()+ "Saved");
            
        }
        else{
            recordIn= false;
            msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Saving Error"," Fill in the missing informations!");
        }
        
    }
    /****
     ***  Check if class exist
    ****/
    public void checkClassExist(){
        int x = 0;
        
        //class_code = '"+newclasses.getClassCode().trim()+"' OR 
         String query = "SELECT * FROM  classes"
                 + " WHERE class_name ='"+newclasses.getClassName().trim()+"';";
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
         if( x<1){
             setClassName("");
             System.out.println("Data is ready for saving please wait .....!");
             
             saveData();
             
         }else{
             FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Sorry! ", ":Class Name with exist"));
             System.out.println("Sorry!:   "+newclasses.getClassName() + "Exist");
             setClassName("");
         }
    }
    
    /**
     * generating class  code 
     */
    public  void getRandom(){
        Random newrandom = new Random();
        int random = newrandom.nextInt(1000)+8880;
        System.out.println("newNo:"+ random);
        String randomCode = String.valueOf("cl/"+random);
        newclasses.setClassCode(randomCode);
    }
    
}
