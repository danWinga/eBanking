/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.tracking.shared.users;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Random;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import org.primefaces.context.RequestContext;
import org.tracking.server.database.DatabaseBean;
import org.tracking.shared.stakeholders.Stakeholders;

/**
 *
 * @author root
 */
@ManagedBean
@SessionScoped 
public class NewUser  implements Serializable{
    private Users user;
    private Stakeholders stakeholder;
    private String username;
    private String rolecode;
    private ArrayList<Users>  userList;
    
    
    @PostConstruct
    public void init(){
        
        user = new Users();
        stakeholder = new Stakeholders();
    }
    public void NewUser(){
        
    }
     RequestContext context = RequestContext.getCurrentInstance();
        FacesMessage msg = null;
    
    public ArrayList<Users> getUserList(){
        
        String query = "SELECT * FROM users;";
        userList = new ArrayList<Users>();
        System.out.println(" collecting users data>..............................!");
        try{
                    DatabaseBean db = new DatabaseBean();
                    Connection conn = db.DBconnect();
                    PreparedStatement stmt = conn.prepareStatement(query);
                    stmt.setFetchSize(100);
                    ResultSet rs = db.preparedState(stmt);
                    while(rs.next()){
                            userList.add(new Users(rs.getInt("id"), rs.getString("userName"),rs.getString("currentpassword"),
                                    rs.getString("oldPassword"),rs.getDate("passwordChangeDate"),rs.getString("mainPage"),
                                    rs.getString("contentCenter"),rs.getString("contentHeader"),rs.getDate("created_Date")));
                    }         
                     rs.close();
                     db.cleanup();
           
        }catch(SQLException e){
            msg = new FacesMessage(FacesMessage.SEVERITY_FATAL, "Saving Error"," Please contact admin!");
            System.out.println("role  error in SQLE !"+e);
        }catch(Exception e){
            msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Saving Error"," Fill in the missing fields!");
            System.out.println("role  error in EXception !"+e);
        }
      
        System.out.println("role  data collected ........!");
        return userList;
    }    
    public Users getUser(){
       return user; 
    }
    public void setUser(Users thisUser){
        this.user = thisUser;
    }
    
    public Stakeholders getStakeholder(){
        return stakeholder;
    }
    public void setStakeholder(Stakeholders stakeholder){
        this.stakeholder = stakeholder;
    }
    public String getUserName(){
        return username;
    }
    public void setUserName(String username){
        this.username = username.trim();
    }
    public String getRolecode(){
        return rolecode;
    }
    public void setRolecode(String rolecode){
        this.rolecode = rolecode.toUpperCase();
    }
    public void saveData(){
        //role.setOldpassword(role.get);
       test();
      System.out.println("........... records check ....................................... ");
            System.out.println("checking records again ....... ");
            System.out.println("Names Are:"+ stakeholder.getFirstName()+" "+ stakeholder.getMiddleName());
            System.out.println("Role code:"+ getRolecode()+" \t  Stakehoders code:"+ stakeholder.getStakeholderCode());
            System.out.println("User name:"+ user.getUserName()+" \t  password:"+ user.getCurrentpassword() );
            System.out.println("Main Page:"+ user.getMainPage() );
            System.out.println("Oldpassword!:   "+user.getOldpassword());
            System.out.println("Stakeholders code!:   "+stakeholder.getStakeholderCode());
            System.out.println("record check complete....... ");
            System.out.println(".................................................. ");
        String mysql = "INSERT INTO role (role_Code, stackholder_Code, userName,currentpassword,"
                + "oldPassword,passwordChangeDate,mainPage,contentCenter,contentHeader,created_Date)"
                + " VALUES ('"+ getRolecode()+"', '"+ stakeholder.getStakeholderCode() +"',  "
                + "'"+ user.getUserName() +"', '"+ user.getCurrentpassword() +"', '"+ user.getOldpassword() +"',"
                + " '"+ user.getPasswordChangeDate() +"', '"+ user.getMainPage() +"', '"+ user.getContentCenter() +"',"
                + " '"+ user.getContentHeader() +"', now());";
        
        
        System.out.println("New Role SQL:"+ mysql);
        try{
            DatabaseBean db = new DatabaseBean();
            db.insert(mysql);
            msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "New Role: ",user.getUserName() + "Saved!");
            db.cleanup();
        } catch(SQLException e){
             System.out.println("Error 1: "+ e);
             msg = new FacesMessage(FacesMessage.SEVERITY_WARN,"Database Error!", "Check Database then try again!" );
              FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Data Error! ", ":Contact Admin  the try again"));
        }catch(Exception e ){
             System.out.println("Error 2: "+ e);
        } 
    }
    
    
    
    /****
     ***  Check if class exist
    ****/
    public void checkUserNameExist(){
        int x = 0;
        
        
         String query = "SELECT * FROM  role"
                 + " WHERE userName ='"+user.getUserName().trim()+"' LIMIT 1;";
         try{
             DatabaseBean db = new DatabaseBean();
             Connection con = db.DBconnect();
             PreparedStatement stmt = con.prepareStatement(query);
             stmt.setFetchSize(100);
             ResultSet rs = db.preparedState(stmt);
             while(rs.next()){
                 
                 setUserName(rs.getString("userName"));
                 
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
         System.out.println("Data Validating: code:-"+getUserName());
         if( x<1 ){
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "UserName! ", ":User name accepted"));
             System.out.println("Good!:   "+user.getUserName() + "Accepted");
                     user.setOldpassword(user.getCurrentpassword());
             System.out.println(" saving Data  please wait .....!");
             
             //saveData();
            
             test();
             
         }else{
             FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Sorry! ", ":User Name  exist Try another name"));
             System.out.println("Sorry!:   "+user.getUserName() + "Exist");
            
         }
    }
    
    
    public void searchCode(){
        int x = 0;
        
         System.out.println(" searching  ......" + user.getUserCode());
        
           // setUserCode("STD/9014");//STD/9025
        String query ="SELECT  * from stackholder where student_ID ='"+ user.getUserCode().trim()+"'"
                + " OR TAC_ID ='"+ user.getUserCode().trim()+"'"
                + " OR work_ID ='"+ user.getUserCode().trim()+"'  LIMIT 1;";
       
        try{
            DatabaseBean db = new DatabaseBean();
            Connection conn = db.DBconnect();
            PreparedStatement stmt = conn.prepareStatement(query);
            ResultSet rs = db.preparedState(stmt);
            while (rs.next()){
                stakeholder.setStakeholderCode(rs.getString("stackholder_Code"));
                stakeholder.setFirstName(rs.getString("first_Name"));
                stakeholder.setMiddleName(rs.getString("middle_Name"));
                stakeholder.setIsAdmin(rs.getBoolean("isAdmin"));
                stakeholder.setIsStudent(rs.getBoolean("isStudent"));
                stakeholder.setIsTeacher(rs.getBoolean("isTeacher"));
                stakeholder.setIsSabodinate_Staff(rs.getBoolean("isSabodinate_Staff"));
                stakeholder.setIsBOG(rs.getBoolean("isBOG"));
                stakeholder.setIsGuardian(rs.getBoolean("isGuardian"));
                
                x++;
            }
            rs.close();
            db.cleanup();
        }catch(SQLException e ){
            System.out.println("error 1"+ e);
        }catch(Exception e){
            System.out.println("error 2"+ e);
        }
        if(x>0){
            System.out.println(".................................................. ");
            System.out.println("checking stakeholder's code id....... ");
            System.out.println("Names Are:"+ stakeholder.getFirstName()+" "+ stakeholder.getMiddleName());
            //role.getRandom();
            System.out.println("Stakeholders code!:   "+stakeholder.getStakeholderCode());
            System.out.println("code check complete....... ");
            System.out.println(".................................................. ");
        }else {
           stakeholder.setFirstName("");
           stakeholder.setMiddleName("");
           FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Sorry! ", ":Identification code entered doesn't exist"));
             System.out.println("Sorry!:   "+user.getUserCode()+ "Identification code entered doesn't exist");
        }
      
        
    }
    
    public void findHomepage(){
        if ((stakeholder.getIsStudent()== true)&& (stakeholder.getIsAdmin() == false)&& (stakeholder.getIsTeacher() == false)&& 
                (stakeholder.getIsSabodinate_Staff() == false)&& (stakeholder.getIsBOG() == false)){
            user.setMainPage("/student/student");
            
        }
        else if(((stakeholder.getIsStudent()== false)&& (stakeholder.getIsAdmin() == false)&& (stakeholder.getIsTeacher() == true)&& 
                (stakeholder.getIsSabodinate_Staff() == false)&& (stakeholder.getIsBOG() == false))|| (((stakeholder.getIsStudent()== true)&&
                (stakeholder.getIsAdmin() == false)&& (stakeholder.getIsTeacher() == true)&& 
                (stakeholder.getIsSabodinate_Staff() == false)&& (stakeholder.getIsBOG() == false)))){
           user.setMainPage("/teacher/teacher"); 
        }
        else if(((stakeholder.getIsStudent()== false)&& (stakeholder.getIsAdmin() == false)&& (stakeholder.getIsTeacher() == false)&& 
                (stakeholder.getIsSabodinate_Staff() == true)&& (stakeholder.getIsBOG() == true))|| (((stakeholder.getIsStudent()== false)&&
                (stakeholder.getIsAdmin() == false)&& (stakeholder.getIsTeacher() == false)&& 
                (stakeholder.getIsSabodinate_Staff() == false)&& (stakeholder.getIsBOG() == false)))){
            user.setMainPage("/worker/worker"); 
        }
        else if(((stakeholder.getIsStudent()== false)&& (stakeholder.getIsAdmin() == false)&& (stakeholder.getIsTeacher() == false)&& 
                (stakeholder.getIsSabodinate_Staff() == false)&& (stakeholder.getIsBOG() == true))){
            user.setMainPage("/bog/bog"); 
        }
        
        else if (((stakeholder.getIsAdmin() == true) && (stakeholder.getIsStudent() == false) &&
                (stakeholder.getIsTeacher() == false)&& (stakeholder.getIsSabodinate_Staff() == true))){
             user.setMainPage("/admin/admin"); 
        }
        else if ( ((stakeholder.getIsAdmin() == true)&& (stakeholder.getIsStudent() == false)&& 
                (stakeholder.getIsTeacher() == true)&& (stakeholder.getIsSabodinate_Staff() == true))){
             user.setMainPage("/admin/admin"); 
        }
        else if ( ((stakeholder.getIsAdmin() == true)&& (stakeholder.getIsStudent() == false) &&
                (stakeholder.getIsTeacher() == false)&& (stakeholder.getIsSabodinate_Staff() == false)&& 
                (stakeholder.getIsBOG() == true))){
             user.setMainPage("/admin/admin"); 
        }
        else{
           user.setMainPage("/login/login"); 
        }
            
        }
    public void test(){
        System.out.println(".................................................. ");
        System.out.println("*******Settings ************* ");
        System.out.println("isStudent :"+ stakeholder.getIsStudent()+" \t isTeacher "+ stakeholder.getIsTeacher());
        System.out.println("isBOG :"+ stakeholder.getIsBOG()+" \t isworker "+ stakeholder.getIsSabodinate_Staff());
        System.out.println("isAdmin :"+ stakeholder.getIsAdmin()+" \t isGuardian "+ stakeholder.getIsGuardian());
        findHomepage();
        System.out.println("...........Testing records....................................... ");
            System.out.println("checking records....... ");
            System.out.println("Names Are:"+ stakeholder.getFirstName()+" "+ stakeholder.getMiddleName());
            System.out.println("Role code:"+ getRolecode()+" \t  Stakehoders code:"+ stakeholder.getStakeholderCode());
            System.out.println("User name:"+ user.getUserName()+" \t  password:"+ user.getCurrentpassword() );
            System.out.println("Main Page:"+ user.getMainPage() );
            System.out.println("Stakeholders code!:   "+stakeholder.getStakeholderCode());
            System.out.println("Stakeholders code!:   "+stakeholder.getStakeholderCode());
            System.out.println("record check complete....... ");
            System.out.println(".................................................. ");
    }
        
    
}
