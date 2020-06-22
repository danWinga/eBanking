/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.tracking.shared.users;

import java.io.Serializable;
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
public class NewUserRole implements Serializable{
    private static final long serialVersionUID = 1L;
    private UserRole newRole = new UserRole();
    
    FacesContext facesContext = FacesContext.getCurrentInstance();
    HttpSession httpSession = (HttpSession)facesContext.getExternalContext().getSession(true);
    public void init(){
        
    }
    
    public UserRole getNewRole(){return newRole;}
    public void setNewRole(UserRole newRole){this.newRole = newRole;}
    
    public void addNew(){
        
        String sql = "INSERT INTO role (role_name, is_active,  created_Date)"
                        + " VALUES ( '"+ newRole.getRolename().trim() +"', '"+ newRole.getIsActive() +"',now());";
        try{
            DatabaseBean db = new DatabaseBean();
            db.insert(sql);
        }catch (Exception e){
              System.out.println("Exception: "+ e);
              FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Data Error! ", ":Contact Admin  then try again"));
        }
       System.out.println("sql:"+ sql);
       FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "successfull! ", ":New Role has been created"));
        
    }
    public void edit(){
        
    }
    
}
