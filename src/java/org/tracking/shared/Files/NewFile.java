/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.tracking.shared.Files;

import java.io.Serializable;
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
public class NewFile  implements Serializable{
    private static final long serialVersionUID = 1L;
    private Files file;

    
    
    FacesContext facesContext = FacesContext.getCurrentInstance();
    HttpSession httpSession = (HttpSession)facesContext.getExternalContext().getSession(true);
    @PostConstruct
    public void init(){
        file = new  Files();
    }
    
    public Files getFile(){
        
        return file;
    }
    public void setFile(Files file){
        this.file = file;
    }
    /**
     * check if file code exist
     */
    public void checkFilecode(){
        generateFileCode();
        String sql = "select * from account_Files where  file_Code = '"+ file.getFileCode()+"' ;";
        
       
    }
    public void test(){
        generateFileCode();
        System.out.println(" fileName: "+ file.getFlname());
        System.out.println("folder code: "+file.getAccCode());
        System.out.println(" filecode: "+ file.getFileCode());
        System.out.println("desc: "+ file.getDescription());
    }
    public void addNew(){
        test();
        generateFileCode();
        String sql = "INSERT INTO account_Files (file_Code, acc_Code, file_Name, description, created_Date)"
                        + " VALUES ( '"+ file.getFileCode().trim() +"', '"+ file.getAccCode().trim() +"', "
                + "'"+ file.getFlname().trim() +"', '"+ file.getDescription().trim() +"',now());";
        try{
            DatabaseBean db = new DatabaseBean();
            db.insert(sql);
        }catch (Exception e){
              System.out.println("Exception: "+ e);
              FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Data Error! ", ":Contact Admin  then try again"));
        }
       System.out.println("sql:"+ sql);
       FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "successfull! ", ":New File has been created"));
        
    }
    
    /**
     * generating class  code 
     */
    public  void generateFileCode(){
        Random newrandom = new Random();
        int random = newrandom.nextInt(1000)+8880;
        System.out.println("newNo:"+ random);
        String randomCode = String.valueOf("fc/"+random);
        file.setFileCode(randomCode);
    }
    
}
