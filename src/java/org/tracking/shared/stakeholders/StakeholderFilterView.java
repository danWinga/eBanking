/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.tracking.shared.stakeholders;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import org.primefaces.context.RequestContext;
import org.tracking.server.database.DatabaseBean;

/**
 *
 * @author root
 */
@ManagedBean(name="dtStakeholderFilterView")
//@ViewScoped
@SessionScoped
public class StakeholderFilterView implements Serializable {
    private ArrayList<Stakeholders> stakeholders;
     private ArrayList<Stakeholders> stakeholderList;
     private ArrayList<Stakeholders> filteredStakeholders;
     private Stakeholders selectedStakeholder = new Stakeholders();
     
     
   @PostConstruct
    public void init() {
            
        stakeholders = getStakeholderList();
        System.out.println(" printing init "+selectedStakeholder.getFirstName() );
    }
    
    public ArrayList<Stakeholders> getStakeholders() {
        return stakeholders;
    }
    public ArrayList<Stakeholders>getFilteredStakeholders() {
        return filteredStakeholders;
    }
    public void setFilteredStakeholders(ArrayList<Stakeholders> filteredStakeholders ){
        this.filteredStakeholders = filteredStakeholders;
        
    }
    
    public Stakeholders getSelectedStakeholder(){
        return selectedStakeholder;
    }
    public void setSelectedStakeholder(Stakeholders selectedStakeholder){
        this.selectedStakeholder = selectedStakeholder;
    }
    
    FacesContext context = FacesContext.getCurrentInstance();
    HttpSession httpSession = (HttpSession)context.getExternalContext().getSession(true);
    FacesMessage msg = null;
    
    public ArrayList<Stakeholders>  getStakeholderList(){
            String query = "SELECT * FROM stackholder;";
               stakeholderList = new ArrayList<Stakeholders>();
              System.out.println(" collecting Stakeholder data>..............................!");
        try{
            DatabaseBean db = new DatabaseBean();
            Connection conn = db.DBconnect();
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setFetchSize(100);
            ResultSet rs = db.preparedState(stmt);
            while(rs.next()){
                stakeholderList.add(new Stakeholders(rs.getInt("id"),rs.getString("stackholder_Code"),rs.getString("school_Code"),
                        rs.getString("first_Name"),rs.getString("middle_Name"),rs.getString("last_Name"),rs.getString("other_Name"),
                        rs.getString("gender"),rs.getDate("dateOf_Birth"),rs.getString("birth_Place"),rs.getString("blood_Group"),
                        rs.getString("nationality"),rs.getString("religion"),rs.getString("address1"),rs.getString("address2"),
                        rs.getString("city"),rs.getString("county"),rs.getString("contact1"),rs.getString("contact2"),
                        rs.getString("email"),rs.getString("student_ID"),rs.getString("TAC_ID"),rs.getString("work_ID"),rs.getString("father_Name"),
                        rs.getString("mother_Name"),rs.getString("guardian_Name"),rs.getString("proffession"),rs.getBoolean("isCanEmail"),
                        rs.getBoolean("isStudent"),rs.getBoolean("isTeacher"),rs.getBoolean("isSuperAdmin"),rs.getBoolean("isAdmin"),rs.getBoolean("isBOG"),
                        rs.getBoolean("canLogin"),rs.getString("job_Description"),rs.getString("note"),rs.getDate("created_Date")));
            }         
             rs.close();
             db.cleanup();
           
        }catch(SQLException e){
            msg = new FacesMessage(FacesMessage.SEVERITY_FATAL, "Saving Error"," Please contact admin!");
            System.out.println("Stakeholder  error in SQLE !"+e);
        }catch(Exception e){
            msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Saving Error"," Fill in the missing fields!");
            System.out.println("Stakeholder  error in EXception !"+e);
        }
      
        System.out.println("Stakeholder  data collected ........!"+ selectedStakeholder.getFirstName());
        return stakeholderList;
    }
   
    
}
