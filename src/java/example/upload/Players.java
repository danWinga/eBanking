/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package example.upload;

import java.io.InputStream;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;
import org.tracking.server.database.DatabaseBean;
import org.tracking.shared.stakeholders.Stakeholders;

/**
 *
 * @author root
 */
@ManagedBean(name="players")
@SessionScoped
public class Players implements Serializable{
	private static final long serialVersionUID = 1L;        
        private UploadedFile file;
        //public Stakeholders selectedHolder;
        
        FacesContext facesContext = FacesContext.getCurrentInstance();
        HttpSession httpSession = (HttpSession)facesContext.getExternalContext().getSession(true);
        
        public Players(){
                
        }
        

        public void profilePic(FileUploadEvent event){
               //String sql = "UPDATE team_players SET image = ? WHERE team_player_id = ?";
                String sql = "insert into team_players (image,details) values(?,?)";
                handleFileUpload(event, sql);
        }
        
        public void handleFileUpload(FileUploadEvent event, String sql) {  
                FacesContext context = FacesContext.getCurrentInstance(); 
                try {
                        InputStream fin2 =  event.getFile().getInputstream(); 
                        DatabaseBean db = new DatabaseBean();
                        Connection con = db.DBconnect();
                        PreparedStatement pre = con.prepareStatement(sql);
                        pre.setString(2, event.getFile().getFileName());
                        pre.setBinaryStream(1, fin2, (int) event.getFile().getSize());
                        System.out.println("sql pre:" + pre);
                        pre.executeUpdate();
                        
                        pre.close();
                        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Bravo...", "Photo uploaded successfully..."));
                }catch (SQLException sqlex){
                        System.out.println("Image Display SQL Exception: "+ sqlex);
                } catch (Exception e) {
                        //System.out.println("Exception-File Upload: " + e);
                } 
        }
}
