/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.tracking.shared.ClassStreams;

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
import org.tracking.server.database.DatabaseBean;

/**
 *
 * @author root
 */
@ManagedBean
@SessionScoped
public class NewStream implements Serializable{
    private Stream newStream;
    private String strName;
    
    FacesContext facesContext = FacesContext.getCurrentInstance();
    HttpSession httpSession = (HttpSession)facesContext.getExternalContext().getSession(true);
    FacesMessage msg = null;
    @PostConstruct
    public void init(){
        newStream = new Stream();
    }
    public String getStrName(){return strName;}
    public void setStrName(String strName){this.strName = strName;}
    
    public Stream getNewStream(){
        return newStream;
    }
    public void setNewStream(Stream newStream){
        this.newStream = newStream;
    }
    
    public  void generateStreamCode(){
        Random newstreamData = new Random();
        int random = newstreamData.nextInt(1000)+8880;
        System.out.println("newNo:"+ random);
        String randomCode = String.valueOf("strm/"+random);
        newStream.setStreamCode(randomCode);
    }
    
    /**
     * check stream name then save data
     */
    public void addnew(){
        int x = 0;
      
         String query = "SELECT * FROM  steams"
                 + " WHERE stream_name ='"+newStream.getStreamName().trim()+"';";
         try{
             DatabaseBean db = new DatabaseBean();
             Connection con = db.DBconnect();
             PreparedStatement stmt = con.prepareStatement(query);
             stmt.setFetchSize(100);
             ResultSet rs = db.preparedState(stmt);
             while(rs.next()){
                 //setClassCode(rs.getString("class_code"));
                 setStrName(rs.getString("stream_Name"));
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
         System.out.println("Data Validating: code:-"+getStrName());
         if( x<1){
            setStrName("");
             System.out.println("Data is ready for saving please wait .....!");
             
             saveData();
             
         }else{
             FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Sorry! ", ":Class Name with exist"));
             System.out.println("Sorry!:   "+newStream.getStreamName() + ":  Exist");
             setStrName("");
         }
    }
    /**
     * saving record
     */
    public void saveData(){
        String mysql = "INSERT INTO streams (stream_Code,class_Code, stream_Name, capacity, details, created_Date)"
                + " VALUES ('"+ newStream.getStreamCode()+"', '"+ newStream.getClassCode()+"','"+ newStream.getStreamName() +"',  "
                + "'"+ newStream.getCapacity() +"', '"+ newStream.getDetails() +"',now());";
        System.out.println("New Classes SQL:"+ mysql);
        try{
            DatabaseBean db = new DatabaseBean();
            db.insert(mysql);
            msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "New Class: ",newStream.getStreamName() + "Saved!");
            db.cleanup();
        } catch(SQLException e){
             System.out.println("Error 1: "+ e);
             msg = new FacesMessage(FacesMessage.SEVERITY_WARN,"Database Error!", "Check Database then try again!" );
              
        }catch(Exception e ){
             System.out.println("Error 2: "+ e);
        } 
    }
    
}
