/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.tracking.shared.schoolAccountFolder;

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
@ManagedBean(name = "newSchoolFolder")
@SessionScoped
public class NewSchoolFolder  implements Serializable {
    
    private SchoolFolder newfolder = new  SchoolFolder();
    
    FacesContext facesContext = FacesContext.getCurrentInstance();
     HttpSession httpSession = (HttpSession)facesContext.getExternalContext().getSession(true);
     
    
          
    public SchoolFolder getNewfolder(){
        return newfolder;
    }
    public void setNewfolder(SchoolFolder newfolder){
        this.newfolder = newfolder;
    }
    
    public void saveData(){
        getRandomCode();
       newfolder.setSchoolCode("CH333");
                String sql = "INSERT INTO sch_Accounts ( acc_Code, school_Code, account_Name, description, created_Date)"
                        + " VALUES ( '"+newfolder.getAccCode().trim() +"', '"+ newfolder.getSchoolCode().trim() +"', '"+ newfolder.getAccountName().trim() +"', '"+ newfolder.getDescription().trim() +"',now());";
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
        newfolder.setAccCode( randomCode);
        System.out.println("New folder code:"+ newfolder.getAccCode());
        
    }
}
