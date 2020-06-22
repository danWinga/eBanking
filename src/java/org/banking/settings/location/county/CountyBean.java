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
@ManagedBean(name="countyBean")
@SessionScoped
public class CountyBean implements Serializable{

    private static final long serialVersionUID = 2615233453630484917L;
    private  County selected , newCounty; 
            
    FacesContext facesContext = FacesContext.getCurrentInstance();
    HttpSession httpSession = (HttpSession)facesContext.getExternalContext().getSession(true);
    FacesMessage msg = null;   
    
    @PostConstruct
    public void init(){
        newCounty = new County();
    }
    
    public County getSelected(){
        return selected;
    }
    public void setSelected(County selected){
        this.selected = selected;
    }
    
    public County getNewCounty(){
        return newCounty;
    }
    public void setNewCounty(County newcounty){
        this.newCounty = newcounty;
    }
    
    /**********
     * save new County details
     */
    
    public void saveData(){
        
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
	   
	   Calendar currenttime = Calendar.getInstance();
           Date sqldate = new Date((currenttime.getTime()).getTime()); 
           
                String sql = "INSERT INTO county ( countyCode, countyName,createDate)"
                        + " VALUES ('"+ newCounty.getCountyCode().trim() +"', '"+ newCounty.getCountyName().trim() +"', '"+sqldate  +"');";
                System.out.println(sql);
                try {
                        DatabaseBean db = new DatabaseBean();
                        db.insert(sql);
                }   catch (Exception e){
                        System.out.println("Exception: "+ e);
                }
        }
}
