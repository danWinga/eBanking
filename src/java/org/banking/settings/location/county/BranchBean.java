/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.banking.settings.location.county;

import java.io.Serializable;
import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import org.tracking.server.database.DatabaseBean;

/**
 *
 * @author dan
 */
@ManagedBean(name="branchBean")
@SessionScoped
public class BranchBean implements Serializable{
    
    private static final long serialVersionUID = -8877838528790724147L;
    private Branch newBranch, selected;
    
    FacesContext facesContext = FacesContext.getCurrentInstance();
    HttpSession httpSession = (HttpSession)facesContext.getExternalContext().getSession(true);
    FacesMessage msg = null; 
    
    @PostConstruct
    public void init(){
        newBranch = new Branch();
    }
    
    public Branch getSelected(){
        return selected;
    }
    public void setSelected(Branch selected){
        this.selected = selected;
    }
    
    public Branch getNewBranch(){
        return newBranch;
    }
    public void setNewRanch(Branch newBranch){
        this.newBranch = newBranch;
    }
    /**********
     * save new County details
     */
    
    public void saveData(){
        
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
	   
	   Calendar currenttime = Calendar.getInstance();
           Date sqldate = new Date((currenttime.getTime()).getTime()); 
           
                String sql = "INSERT INTO branches ( branchcode, countycode,branchname,physicaladdress,address,town,officeContact,fax,email,createDate)"
                        + " VALUES ('"+ newBranch.getBranchCode().trim() +"', '"+ newBranch.getCountyCode().trim() +"', '"+ newBranch.getBranchName().trim() +"', "
                        + "'"+ newBranch.getPhysicalAddress().trim() +"','"+ newBranch.getAddress().trim() +"','"+ newBranch.getTown().trim() +"',"
                        + " '"+ newBranch.getOfficeContact().trim() +"', '"+ newBranch.getFaxno().trim() +"', "
                        + "'"+ newBranch.getEmail().trim() +"', '"+sqldate  +"');";
                System.out.println(sql);
                try {
                        DatabaseBean db = new DatabaseBean();
                        db.insert(sql);
                }   catch (Exception e){
                        System.out.println("Exception: "+ e);
                }
        }
    
}
