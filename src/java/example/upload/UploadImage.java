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
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import org.primefaces.model.UploadedFile;
import org.tracking.server.database.DatabaseBean;

/**
 *
 * @author root
 */
@ManagedBean(name = "uploadImage")
@SessionScoped
public class UploadImage implements Serializable {
 
    private static final long serialVersionUID = 1L;
    private UploadedFile file;
 
    public UploadedFile getFile() {
        return file;
    }
 
    public void setFile(UploadedFile file) {
        this.file = file;
    }
 
    public void upload() {
        System.out.println("sssss");
        //System.out.println("files:" + file.getFileName());
        if (file != null) {
            try {
                //System.out.println(file.getFileName());
                InputStream fin2 = file.getInputstream();
                DatabaseBean db = new DatabaseBean();
                Connection con = db.DBconnect();
                PreparedStatement pre = con.prepareStatement("insert into upload_image (image_name,image) values(?,?)");
                pre.setString(1, file.getFileName().toString());
                pre.setBinaryStream(2, fin2, file.getSize());
                pre.executeUpdate();
                System.out.println("Inserting Successfully!");
                pre.close();
                FacesMessage msg = new FacesMessage("Succesful", file.getFileName() + " is uploaded.");
                FacesContext.getCurrentInstance().addMessage(null, msg);
 
            } catch (Exception e) {
                System.out.println("Exception-File Upload." + e.getMessage());
            }
        }
        else{
        FacesMessage msg = new FacesMessage("Please select image!!");
                FacesContext.getCurrentInstance().addMessage(null, msg);
        }
    }
}