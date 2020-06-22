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
public class StreamBean implements Serializable{
    private Stream selected;
    private ArrayList<Stream> streamList;
    private LazyDataModel<Stream> streamModel;
    
    FacesContext facesContext = FacesContext.getCurrentInstance();
    HttpSession httpSession = (HttpSession)facesContext.getExternalContext().getSession(true);
    FacesMessage msg = null;
    
    public Stream getSelected(){return selected;}
    public void setSelected(Stream selected){this.selected = selected;}
    
    public LazyDataModel<Stream> getStreamModel(){
        
        return streamModel;
    }
    
    public ArrayList<Stream> getStreamList(){
       streamList = new ArrayList<Stream>();
       String query = "SELECT * FROM account_Files";
       try{
           DatabaseBean db = new DatabaseBean();
           Connection con = db.DBconnect();
           PreparedStatement stmt = con.prepareStatement(query);
           ResultSet rs=db.preparedState(stmt);
           while(rs.next()){
               streamList.add(new Stream(rs.getInt("id"),rs.getString("stream_Code"),rs.getString("class_Code"), 
                       rs.getString("stream_Name"), rs.getInt("capacity"),  rs.getString("details"), rs.getDate("created_Date")));
           }
            rs.close();
             db.cleanup();
           }catch(SQLException e){
                        System.out.println("SQL Exception while getting Class stream: "+ e);
           }catch(Exception e){
                        System.out.println("Exception while getting FClass Stream: "+ e);
           }
      
       return streamList;
   }
    
    /**
     * 
     */
    public void update(){
        
        String mysql = "UPDATE streams  SET class_Code = '"+ selected.getClassCode()+"', stream_Name= '"+ selected.getStreamName()+"', capacity = '"+selected.getCapacity()+"',details = '"+selected.getDetails()+"' WHERE stream_Code = '"+selected.getStreamCode()+"';";
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
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, " Record  ", selected.getStreamName()+":Updated  successfull."));
    }
    public void onRowSelect(SelectEvent event ){
      msg = new FacesMessage("Stream Selected", ((Stream) event.getObject()).getStreamName());
      FacesContext.getCurrentInstance().addMessage(null, msg);
   }
    
}
