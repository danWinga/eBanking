/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.tracking.shared.classes.streams;

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
import org.primefaces.event.SelectEvent;
import org.primefaces.model.LazyDataModel;
import org.tracking.server.database.DatabaseBean;

/**
 *
 * @author root
 */
@ManagedBean
@SessionScoped
public class StreamBean implements Serializable {
        private Stream newStream ;
        private Stream selected  ;
        LazyDataModel<Stream>streamModel;
        private ArrayList<Stream> streamList;
        private  boolean isValid;
        
        FacesContext facesContext = FacesContext.getCurrentInstance();
        HttpSession httpSession = (HttpSession)facesContext.getExternalContext().getSession(true);
        FacesMessage msg = null;
    @PostConstruct
    public void init(){
        newStream = new  Stream();
        selected  = new  Stream();
        streamModel = new StreamDataModel(getStreamList());
    }  
        
        /***
         * 
         * @return streamList
         */
        public ArrayList<Stream> getStreamList(){
                streamList = new ArrayList<Stream>();
                String query = "SELECT * FROM vw_stream";
                try{
                        DatabaseBean db = new DatabaseBean();
                        Connection con = db.DBconnect();
                        PreparedStatement stmt = con.prepareStatement(query);
                        ResultSet rs=db.preparedState(stmt);
                        
                        while(rs.next()){
                                    streamList.add(new Stream(rs.getInt("id"),rs.getString("stream_code"),rs.getString("stream_name"),
                                            rs.getString("class_code"),rs.getString("class_name"), 
                                       rs.getString("details")));
                         }
                    rs.close();
                    db.cleanup();
                }catch(SQLException e){
                        System.out.println("SQL Exception while getting Streams: "+ e);
                }catch(Exception e){
                        System.out.println("Exception while getting Streams: "+ e);
                }
            return streamList;
        }
            /***
            * Validating stream record before inserting 
            * or editing
            * @param streamname
            * @return 
            */
        public boolean getIsValid(String streamname){
                isValid =false;
                String tempData = "";
                String sql = "SELECT * from streams where  stream_name == '"+streamname.trim()+"' ;";
                try{
                        DatabaseBean db = new DatabaseBean();
                        Connection con  = db.DBconnect();
                        PreparedStatement stmt = con.prepareStatement(sql);
                        ResultSet rs = db.preparedState(stmt);
                        
                        while(rs.next()){
                            tempData = rs.getString("stream_name");

                    }
                rs.close();
                db.cleanup();
                }catch(SQLException e){
                    System.out.println("SQL "+ e);
                }catch(Exception e ){
                    System.out.println("Exception "+e);
                }
                
            if(tempData.isEmpty()){
                return isValid = true;
             }
            return isValid;
        }
        
        /***
         * new stream
         * @add new stream
         */
        public void addNew(){
       //test();
            getIsValid(newStream.getStreamName());
                System.out.println("isvalid is :"+ isValid);
        
                if(isValid== true)
                {
                        generateStreamCode();
                        String sql = "INSERT INTO streams (stream_code, class_code, stream_name, capacity, details,created_Date)"
                        + " VALUES ( '"+ newStream.getStreamCode().trim() +"', '"+ newStream.getClassCode().trim() +"', "
                        + "'"+ newStream.getStreamName().trim() +"', '"+ newStream.getCapacity() +"','"+ newStream.getDetails().trim() +"',now());";
                    try{
                            DatabaseBean db = new DatabaseBean();
                            db.insert(sql);
                    }catch (Exception e){
                        System.out.println("Exception: "+ e);
                         FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Data Error! ", ":Contact Admin  then try again"));
                    }
                    System.out.println("sql:"+ sql);
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "successfull! ", ":New Class Steam has been created"));
                }else{
                     FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Sorry ! ", ": record already exist"));
                }
             
        }
        
        /***
         * Update records
        */
        public void edit(){
         
                getIsValid(selected.getStreamName());
                System.out.println("isvalid is :"+ isValid);
        
                if(isValid== true)
                {
            
                                 String sql = "UPDATE streams    SET stream_name= '"+selected.getStreamName().trim()+"', "
                                    +  "class_code= '"+selected.getClassCode()+"', capacity= '"+selected.getCapacity()+"', "
                                    + "capacity= '"+selected.getDetails()+"' WHERE stream_code ='"+selected.getStreamCode().trim()+"';";
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
                       "Dublicate ! ", ":Stream Name Exist!"));
                    }
        }
        
        /**
         * generating class  code 
        */
        public  void generateStreamCode(){
                Random newrandom = new Random();
                int random = newrandom.nextInt(1000)+8880;
                System.out.println("newNo:"+ random);
                String randomCode = String.valueOf("strm/"+random);
           newStream.setStreamCode(randomCode);
        }
           
        public Stream getNewStream(){return newStream;}
        public void setNewStream(Stream newStream){ this.newStream = newStream;}
           
        public Stream getSelected(){return selected;}
        public void setSelected(Stream selected){this.selected = selected;}
        public void setIsValid(boolean isValid){this.isValid = isValid;} 
        
        public LazyDataModel<Stream> getStreamModel(){
            return streamModel;
        }   
        public void onRowSelect(SelectEvent event ){
            msg = new FacesMessage("Stream Selected", ((Stream) event.getObject()).getStreamCode());
        FacesContext.getCurrentInstance().addMessage(null, msg);
    } 
}
