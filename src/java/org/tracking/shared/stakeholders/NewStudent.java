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
import java.util.Random;
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
@ManagedBean
@SessionScoped
public class NewStudent implements Serializable {
    private Stakeholders newStakeholder;
    private String studentID1, fname1,midname1,lname1;
    
     FacesContext context = FacesContext.getCurrentInstance();
    HttpSession httpSession = (HttpSession)context.getExternalContext().getSession(true);
        FacesMessage msg = null;
    
    public Stakeholders getNewStakeholder(){
        return newStakeholder;
    }
    public void setNewStakeholder(Stakeholders newStakeholder){
        this.newStakeholder = newStakeholder;
    }
    
    public String getStudentId(){return studentID1;}
    public void setStudentId(String str){
        this.studentID1 = ((str != null)? str.toUpperCase(): "");
    }
    public String getFname(){return fname1;}
    public void setFname(String fname){this.fname1 = fname.toUpperCase();}
    
    public String getMidname(){return midname1;}
    public void setMidname(String midname){this.midname1 = midname.toUpperCase();}
    
    public String getLname(){return lname1;}
    public void setlname(String lname){this.lname1 = lname.toUpperCase();}
    /**
     * generating Student code  code 
     */
    public  void getStakeholderCode(){
        Random newrandom = new Random();
        int random = newrandom.nextInt(1000)+8880;
        System.out.println("newNo:"+ random);
        String randomCode = String.valueOf("sth/"+random);
        newStakeholder.setStakeholderCode(randomCode);
    }
    /**
     * generating class  code 
     */
    public  void getStudentCode(){
        Random newrandom = new Random();
        int random = newrandom.nextInt(1000)+8880;
        System.out.println("newNo:"+ random);
        String randomCode = String.valueOf("std/"+random);
        newStakeholder.setStudentID(randomCode);
    }
    
    public void insert(){
        System.out.println("-----------------------------------");
        System.out.println("--test data:"+ newStakeholder.getAddress1());
        System.out.println("--test data:"+ newStakeholder.getBloodGroup());
        System.out.println("--test data:"+ newStakeholder.getFatherName());
        System.out.println("-----------------------------------");
    }
    /****
     ***  Check if Student exist
    ****/
    public void checkStudentExist(){
        int x = 0;
        getStudentCode();
        getStakeholderCode();
        System.out.println("------------------------------------------:");
        System.out.println("Names :"+ newStakeholder.toString());
        System.out.println("------------------------------------------:");
        //class_code = '"+newclasses.getClassCode().trim()+"' OR 
         String query = "SELECT * FROM  stackholder"
                 + " WHERE student_ID ='"+newStakeholder.getStudentID().trim()+"' OR"
                 + " first_Name ='"+newStakeholder.getFirstName().trim()+"'"
                 + " AND middle_Name ='"+newStakeholder.getMiddleName().trim()+"' "
                 + "AND last_Name ='"+newStakeholder.getLastName().trim()+"' LIMIT 1;";
         try{
             DatabaseBean db = new DatabaseBean();
             Connection con = db.DBconnect();
             PreparedStatement stmt = con.prepareStatement(query);
             stmt.setFetchSize(100);
             ResultSet rs = db.preparedState(stmt);
             while(rs.next()){
                 //setClassCode(rs.getString("class_code"));
                 setStudentId(rs.getString("student_ID"));
                 setFname(rs.getString("first_Name"));
                 setMidname(rs.getString("middle_Name"));
                 setlname(rs.getString("last_Name"));
                x++;
             }
             rs.close();
             db.cleanup();
         }catch(SQLException e){
             System.out.println("Error validating dublicates: "+ e);
              FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Database Error! ", ":Contact Admin  the try again"));
             
         }catch(Exception e ){
             System.out.println("Error 2: "+ e);
             FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Data Error! ", ":Contact Admin  the try again"));
         }
         System.out.println("Data Validating: code:-"+getStudentId());
         if( (x<1) && (!getFname().trim().equalsIgnoreCase(newStakeholder.getFirstName().trim())) &&
                 (!getMidname().trim().equalsIgnoreCase(newStakeholder.getMiddleName().trim())  )&&
                 (!getLname().trim().equalsIgnoreCase(newStakeholder.getLastName().trim())  )) {
            
             System.out.println("Data is ready for saving please wait .....!");
             
             saveData();
             
         }else{
             FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Sorry!( ",newStakeholder.toString()+ "):Student  Name with exist"));
             System.out.println("Sorry!:   "+newStakeholder.toString() + "Exist");
             
         }
    }
    public void saveData(){
        String mysql = "INSERT INTO stackholder (stackholder_Code, school_Code, first_Name, middle_Name,last_Name,other_Name,gender,"
                + "dateOf_Birth,birth_Place,blood_Group,nationality,religion,address1,address2,city,county ,contact1,contact2 ,email,"
                + "student_ID,TAC_ID,work_ID,father_Name,mother_Name,guardian_Name,proffession,isCanEmail,isStudent,isTeacher,"
                + "isSuperAdmin,isAdmin,isGuardian,isSabodinate_Staff,isBOG,canLogin,job_Description,note,created_Date)"
                + " VALUES ('"+ newStakeholder.getStakeholderCode()+"', '"+ newStakeholder.getSchoolCode() +"','"+ newStakeholder.getFirstName() +"',"
                + " '"+ newStakeholder.getMiddleName() +"','"+ newStakeholder.getLastName() +"','"+ newStakeholder.getOtherNames() +"','"+ newStakeholder.getGender() +"',"
                + "'"+ newStakeholder.getDateOfBirth()+"','"+ newStakeholder.getBirthPlace() +"','"+ newStakeholder.getBloodGroup() +"','"+ newStakeholder.getNationality() +"',"
                + "'"+ newStakeholder.getReligion() +"','"+ newStakeholder.getAddress1() +"','"+ newStakeholder.getAddress2() +"','"+ newStakeholder.getCity() +"',"
                + "'"+ newStakeholder.getCounty() +"','"+ newStakeholder.getContact1() +"','"+ newStakeholder.getContact2() +"','"+ newStakeholder.getEmail() +"',"
                + "'"+ newStakeholder.getStudentID() +"','"+ newStakeholder.getTAC_ID() +"','"+ newStakeholder.getFatherName() +"','"+ newStakeholder.getMotherName() +"',"
                + "'"+ newStakeholder.getGadianName() +"','"+ newStakeholder.getProffession_ID() +"', '"+ newStakeholder.getIsCanEmail() +"','"+ newStakeholder.getIsStudent() +"',"
                + "'"+ newStakeholder.getIsTeacher()+ "','"+ newStakeholder.getIsSuperAdmin() +"','"+ newStakeholder.getIsAdmin() +"','"+ newStakeholder.getIsGuardian() +"',"
                + "'"+ newStakeholder.getIsSabodinate_Staff() +"','"+ newStakeholder.getIsBOG() +"','"+ newStakeholder.getCanLogin() +"','"+ newStakeholder.getJobDescription() +"',"
                + "'"+ newStakeholder.getNotes() +"',now());";
        System.out.println("New Student  SQL:"+ mysql);
        try{
            DatabaseBean db = new DatabaseBean();
            db.insert(mysql);
            msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "New Student: ", newStakeholder.toString()  + "Saved!");
            db.cleanup();
        } catch(SQLException e){
             System.out.println("Error 1: "+ e);
             msg = new FacesMessage(FacesMessage.SEVERITY_WARN,"Database Error!", "Check Database then try again!" );
              
        }catch(Exception e ){
             System.out.println("Error 2: "+ e);
        } 
    }
}
