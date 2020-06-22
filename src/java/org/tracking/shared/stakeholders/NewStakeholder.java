package org.tracking.shared.stakeholders;


import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Random;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import org.primefaces.context.RequestContext;
import org.tracking.server.database.DatabaseBean;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author root
 */
@ManagedBean
@SessionScoped
public class NewStakeholder implements Serializable  {
    private Stakeholders newstakeholder;
    private Stakeholders newTeacher;
    private String stakecode, studentid, teacherid,workid;
    
    
    @PostConstruct
    public void init(){
        newstakeholder = new Stakeholders();
        newTeacher = new Stakeholders();
    }
    public Stakeholders getNewStakeholder(){
        return newstakeholder;
    }
    public void setNewStakeholder(Stakeholders newStakeholder){
        this.newstakeholder = newStakeholder;
    }
    
    public Stakeholders getNewTeacher(){
        return newTeacher;
    }
    public void setNewTeacher(Stakeholders newTeacher){
        this.newTeacher = newTeacher;
    }
     FacesContext context = FacesContext.getCurrentInstance();
     HttpSession httpSession = (HttpSession)context.getExternalContext().getSession(true);
     FacesMessage msg = null;
    
    
   public void insertStudent(){
       newstakeholder.setIsStudent(true);
       newstakeholder.setSchoolCode("CH333");
       chooser();
       System.out.println("data:"+toString());
       System.out.println("date:"+newstakeholder. getDateOfBirth());
       System.out.println("gendar:"+newstakeholder.getGender());
       System.out.println("canlogin:"+newstakeholder.getCanLogin());
       checkStudentExist();
       addStudent();
   }
   public void testMember(){
       
       newstakeholder.setSchoolCode("CH333");
       //chooser2();
       System.out.println("data:"+toString());
       System.out.println("date:"+newstakeholder. getDateOfBirth());
       System.out.println("gendar:"+newstakeholder.getGender());
       System.out.println("canlogin:"+newstakeholder.getCanLogin());
       System.out.println("*******************************************************************************************************:");
       System.out.println("isStudent:"+newstakeholder.getIsStudent());
       System.out.println("isTeacher :"+newstakeholder.getIsTeacher());
       System.out.println("isWorker:"+newstakeholder.getIsSabodinate_Staff());
       System.out.println("IsDOG:"+newstakeholder.getIsBOG());
       System.out.println("*******************************************************************************************************:");
       //checkStudentExist();
       if (newstakeholder.getIsStudent() ==true){
       //addStudent();
       }
   }
   public void insertAnyMember(){
       
       newstakeholder.setSchoolCode("CH333");
       chooser2();
       System.out.println("data:"+toString());
       System.out.println("date:"+newstakeholder. getDateOfBirth());
       System.out.println("gendar:"+newstakeholder.getGender());
       System.out.println("canlogin:"+newstakeholder.getCanLogin());
       System.out.println("*******************************************************************************************************:");
       System.out.println("isStudent:"+newstakeholder.getIsStudent());
       System.out.println("isTeacher :"+newstakeholder.getIsTeacher());
       System.out.println("isWorker:"+newstakeholder.getIsSabodinate_Staff());
       System.out.println("IsDOG:"+newstakeholder.getIsBOG());
       System.out.println("*******************************************************************************************************:");
       checkStudentExist();
       if (newstakeholder.getIsStudent() ==true){
       //addStudent();
       }
   }
   
   public void insertSabodinate(){
       newstakeholder.setIsSabodinate_Staff(true);
       chooser();
       System.out.println("data:"+newstakeholder.toString());
       System.out.println("date:"+ newstakeholder.getDateOfBirth());
       System.out.println("gendar:"+newstakeholder.getGender());
       System.out.println("canlogin:"+newstakeholder.getCanLogin());
       
   }
   /**
     * generating student Code 
     */
    public  void generateStudentCode(){
        Random newrandom = new Random();
        int random = newrandom.nextInt(1000)+8880;
        System.out.println("newNo:"+ random);
        String randomCode = String.valueOf("std/"+random);
        newstakeholder.setStudentID(randomCode);
    }
    /**
     * generating generate Stakeholder  code 
     */
    public  void generateStakeholderCode(){
        Random newrandom = new Random();
        int random = newrandom.nextInt(1000)+8880;
        System.out.println("newNo:"+ random);
        String randomCode = String.valueOf("sth/"+random);
        newstakeholder.setStakeholderCode(randomCode);
    }
    /**
     * generating generate teacher  code 
     */
    public  void generateTecherCode(){
        Random newrandom = new Random();
        int random = newrandom.nextInt(1000)+8880;
        System.out.println("newNo:"+ random);
        String randomCode = String.valueOf("TAC/"+random);
        newstakeholder.setTAC_ID(randomCode);
    }
    /**
     * generating generate teacher  code 
     */
    public  void generateWorkerCode(){
        Random newrandom = new Random();
        int random = newrandom.nextInt(1000)+8880;
        System.out.println("newNo:"+ random);
        String randomCode = String.valueOf("sab/"+random);
       newstakeholder.setTAC_ID(randomCode);
    }
    public void chooser(){
        if(newstakeholder.getIsStudent() == true){
            System.err.println("generating stakeholder code....");
            generateStakeholderCode();
            System.err.println("generating student code....");
            generateStudentCode();
        }
        else if(newstakeholder.getIsTeacher() == true){
            System.err.println("generating stakeholder code....");
            generateStakeholderCode();
            System.err.println("generating TAC code....");
            generateTecherCode();
        }
        else if(newstakeholder.getIsSabodinate_Staff() == true){
            System.err.println("generating stakeholder code....");
            generateStakeholderCode();
            System.err.println("generating Workers code....");
            generateTecherCode();
        }
        else if((newstakeholder.getIsSabodinate_Staff() == true) && (newstakeholder.getIsTeacher() == true)){
            System.err.println("generating stakeholder code....");
            generateStakeholderCode();
            System.err.println("generating TAC code....");
            generateTecherCode();
            System.err.println("generating workers code....");
            generateWorkerCode();
        }else{
           newstakeholder.setIsSuperAdmin(true);
        }
    }
    public void chooser2(){
        if((newstakeholder.getIsStudent() == true) && (newstakeholder.getIsTeacher()== false) &&
                (newstakeholder.getIsSabodinate_Staff()== false) ){
            System.err.println("generating stakeholder code....");
            generateStakeholderCode();
            System.err.println("generating student code....");
            generateStudentCode();
        }
        else if((newstakeholder.getIsStudent() == false) && (newstakeholder.getIsTeacher()== true) ){
            System.err.println("generating stakeholder code....");
            generateStakeholderCode();
            System.err.println("generating TAC code....");
            generateTecherCode();
        }
        else if((newstakeholder.getIsStudent() == false) && (newstakeholder.getIsTeacher()== false) &&
                (newstakeholder.getIsSabodinate_Staff()== true) ){
            System.err.println("generating stakeholder code....");
            generateStakeholderCode();
            System.err.println("generating Workers code....");
            generateWorkerCode();
        }
        else if((newstakeholder.getIsSabodinate_Staff() == true) && (newstakeholder.getIsTeacher() == true)){
            System.err.println("generating stakeholder code....");
            generateStakeholderCode();
            System.err.println("generating TAC code....");
            generateTecherCode();
            System.err.println("generating workers code....");
            generateWorkerCode();
        }else{
            System.err.println("generating stakeholder code....");
            generateStakeholderCode();
            System.err.println("generating TAC code....");
            generateTecherCode();
            System.err.println("generating workers code....");
            generateWorkerCode();
        }
    }
    /**
     * student data
     */
    
    /****
     ***  Check if Student exist
    ****/
    public void checkStudentExist(){
        int x = 0;
        
        System.out.println("------------------------------------------:");
        System.out.println("Names :"+ toString());
        System.out.println("------------------------------------------:");
        //class_code = '"+newclasses.getClassCode().trim()+"' OR 
         String query = "SELECT * FROM  stackholder"
                 + " WHERE  first_Name ='"+newstakeholder.getFirstName().trim()+"'"
                 + " AND middle_Name ='"+newstakeholder.getMiddleName().trim()+"' "
                 + "AND last_Name ='"+newstakeholder.getLastName().trim()+"' LIMIT 1;";
         try{
             DatabaseBean db = new DatabaseBean();
             Connection con = db.DBconnect();
             PreparedStatement stmt = con.prepareStatement(query);
             stmt.setFetchSize(100);
             ResultSet rs = db.preparedState(stmt);
             while(rs.next()){
                 //setClassCode(rs.getString("class_code"));
                 newstakeholder.setStudId(rs.getString("student_ID"));
                 newstakeholder.setFname(rs.getString("first_Name"));
                 newstakeholder.setMidname(rs.getString("middle_Name"));
                 newstakeholder.setlname(rs.getString("last_Name"));
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
         System.out.println("Data Validating: db record :-");
         if( x<1){
            
             System.out.println("Data is ready for saving please wait .....!");
             
             saveData();
             
         }else{
             FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Sorry!( ",toString()+ "):Student  Name with exist"));
             System.out.println("Sorry!:   "+toString() + "Exist");
             
         }
    }
    /**
     * end of student data
     */
    
    public void saveData(){
        String mysql = "INSERT INTO stackholder (stackholder_Code, school_Code, first_Name, middle_Name,last_Name,other_Name,gender,"
                + "dateOf_Birth,birth_Place,blood_Group,nationality,religion,address1,address2,city,county ,contact1,contact2 ,email,"
                + "student_ID,TAC_ID,work_ID,father_Name,mother_Name,guardian_Name,proffession,isCanEmail,isStudent,isTeacher,"
                + "isSuperAdmin,isAdmin,isGuardian,isSabodinate_Staff,isBOG,canLogin,job_Description,note,created_Date,idno,passportno,"
                + "maritalstatus,staketitle,postcodeno,faxnumber,passonalemail)"
                + " VALUES ('"+newstakeholder. getStakeholderCode()+"', '"+newstakeholder.getSchoolCode() +"','"+ newstakeholder.getFirstName() +"',"
                + " '"+ newstakeholder.getMiddleName() +"','"+ newstakeholder.getLastName() +"','"+newstakeholder. getOtherNames() +"','"+ newstakeholder.getGender() +"',"
                + "'"+ newstakeholder.getDateOfBirth()+"','"+ newstakeholder.getBirthPlace() +"','"+newstakeholder.getBloodGroup() +"','"+ newstakeholder.getNationality() +"',"
                + "'"+ newstakeholder.getReligion() +"','"+ newstakeholder.getAddress1() +"','"+ newstakeholder.getAddress2() +"','"+ newstakeholder.getCity() +"',"
                + "'"+newstakeholder.getCounty() +"','"+ newstakeholder.getContact1() +"','"+ newstakeholder.getContact2() +"','"+ newstakeholder.getEmail() +"',"
                + "'"+ newstakeholder.getStudentID() +"','"+ newstakeholder.getTAC_ID() +"','"+ newstakeholder.getWork_ID() +"','"+ newstakeholder.getFatherName() +"','"+ newstakeholder.getMotherName() +"',"
                + "'"+ newstakeholder.getGadianName() +"','"+ newstakeholder.getProffession_ID() +"', '"+ newstakeholder.getIsCanEmail() +"','"+ newstakeholder.getIsStudent() +"',"
                + "'"+ newstakeholder.getIsTeacher()+ "','"+ newstakeholder.getIsSuperAdmin() +"','"+ newstakeholder.getIsAdmin() +"','"+ newstakeholder.getIsGuardian() +"',"
                + "'"+ newstakeholder.getIsSabodinate_Staff() +"','"+ newstakeholder.getIsBOG() +"','"+ newstakeholder.getCanLogin() +"','"+ newstakeholder.getJobDescription() +"',"
                + "'"+ newstakeholder.getNotes() +"',now(),'"+ newstakeholder.getIdno() +"','"+ newstakeholder.getPassportno() +"','"+ newstakeholder.getMaritalstatus() +"',"
                + "'"+ newstakeholder.getStakeTitle()+"','"+ newstakeholder.getPostalCode() +"','"+ newstakeholder.getFaxNumber()+"','"+ newstakeholder.getOtherMail() +"');";
        System.out.println("New Record  SQL:"+ mysql);
        try{
            DatabaseBean db = new DatabaseBean();
            db.insert(mysql);
            msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "New Record: ", toString()  + "Saved!");
            db.cleanup();
        } catch(SQLException e){
             System.out.println("Error 1: "+ e);
             msg = new FacesMessage(FacesMessage.SEVERITY_WARN,"Database Error!", "Check Database then try again!" );
              
        }catch(Exception e ){
             System.out.println("Error 2: "+ e);
        } 
        msg = new FacesMessage(FacesMessage.SEVERITY_INFO,"Success !", "Record Saved!" );
    }
    
    /**
     * Add student record to student table
     */
    
    public void  addStudent(){
        String mysql = "INSERT INTO stackholder (stackholder_Code, school_Code, first_Name, middle_Name,last_Name,other_Name,gender,"
                + "dateOf_Birth,birth_Place,blood_Group,nationality,religion,address1,address2,city,county ,contact1,contact2 ,email,"
                + "student_ID,TAC_ID,work_ID,father_Name,mother_Name,guardian_Name,proffession,isCanEmail,isStudent,isTeacher,"
                + "isSuperAdmin,isAdmin,isGuardian,isSabodinate_Staff,isBOG,canLogin,job_Description,note,created_Date,idno,passportno,"
                + "maritalstatus,staketitle,postcodeno,faxnumber,passonalemail)"
                + " VALUES ('"+newstakeholder. getStakeholderCode()+"', '"+newstakeholder.getSchoolCode() +"','"+ newstakeholder.getFirstName() +"',"
                + " '"+ newstakeholder.getMiddleName() +"','"+ newstakeholder.getLastName() +"','"+newstakeholder. getOtherNames() +"','"+ newstakeholder.getGender() +"',"
                + "'"+ newstakeholder.getDateOfBirth()+"','"+ newstakeholder.getBirthPlace() +"','"+newstakeholder.getBloodGroup() +"','"+ newstakeholder.getNationality() +"',"
                + "'"+ newstakeholder.getReligion() +"','"+ newstakeholder.getAddress1() +"','"+ newstakeholder.getAddress2() +"','"+ newstakeholder.getCity() +"',"
                + "'"+newstakeholder.getCounty() +"','"+ newstakeholder.getContact1() +"','"+ newstakeholder.getContact2() +"','"+ newstakeholder.getEmail() +"',"
                + "'"+ newstakeholder.getStudentID() +"','"+ newstakeholder.getTAC_ID() +"','"+ newstakeholder.getWork_ID() +"','"+ newstakeholder.getFatherName() +"','"+ newstakeholder.getMotherName() +"',"
                + "'"+ newstakeholder.getGadianName() +"','"+ newstakeholder.getProffession_ID() +"', '"+ newstakeholder.getIsCanEmail() +"','"+ newstakeholder.getIsStudent() +"',"
                + "'"+ newstakeholder.getIsTeacher()+ "','"+ newstakeholder.getIsSuperAdmin() +"','"+ newstakeholder.getIsAdmin() +"','"+ newstakeholder.getIsGuardian() +"',"
                + "'"+ newstakeholder.getIsSabodinate_Staff() +"','"+ newstakeholder.getIsBOG() +"','"+ newstakeholder.getCanLogin() +"','"+ newstakeholder.getJobDescription() +"',"
                + "'"+ newstakeholder.getNotes() +"',now(),'"+ newstakeholder.getIdno() +"','"+ newstakeholder.getPassportno() +"','"+ newstakeholder.getMaritalstatus() +"',"
                + "'"+ newstakeholder.getStakeTitle()+"','"+ newstakeholder.getPostalCode() +"','"+ newstakeholder.getFaxNumber()+"','"+ newstakeholder.getOtherMail() +"');";
        System.out.println("New Record  SQL:"+ mysql);
        try{
            DatabaseBean db = new DatabaseBean();
            db.insert(mysql);
            msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "New Student Record : ", toString()  + "Saved!");
            db.cleanup();
        } catch(SQLException e){
             System.out.println("Error 1 Student : "+ e);
             msg = new FacesMessage(FacesMessage.SEVERITY_WARN,"Database Error!", "Check Database then try again!" );
              
        }catch(Exception e ){
             System.out.println("Error 2 Student : "+ e);
        } 
        msg = new FacesMessage(FacesMessage.SEVERITY_INFO,"Success !", "Record Saved!" );
    }
    public void updateStudent() {
        RequestContext.getCurrentInstance().execute("main1:main2:studentbean");
    }
    public void updateAdmin() {
        RequestContext.getCurrentInstance().execute("main1:main2:adminbean");
    }
    
    
    /****
     * ************************************************************************
     * Student record
     */
    public void checkTeacherExist(){
                int x = 0;

                System.out.println("------------------------------------------:");
                System.out.println("Names :"+ toString());
                System.out.println("------------------------------------------:");  
                //class_code = '"+newclasses.getClassCode().trim()+"' OR 
                String query = "SELECT * FROM  stackholder"
                 + " WHERE  first_Name ='"+newstakeholder.getFirstName().trim()+"'"
                 + " AND middle_Name ='"+newstakeholder.getMiddleName().trim()+"' "
                 + "AND last_Name ='"+newstakeholder.getLastName().trim()+"' LIMIT 1;";
                try{
                        DatabaseBean db = new DatabaseBean();
                        Connection con = db.DBconnect();
                        PreparedStatement stmt = con.prepareStatement(query);
                        stmt.setFetchSize(100);
                        ResultSet rs = db.preparedState(stmt);
                        while(rs.next()){
                                //setClassCode(rs.getString("class_code"));
                                newstakeholder.setTAC_ID(rs.getString("TAC_ID"));
                                newstakeholder.setFname(rs.getString("first_Name"));
                                newstakeholder.setMidname(rs.getString("middle_Name"));
                                newstakeholder.setlname(rs.getString("last_Name"));
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
                 System.out.println("Data Validating: db record :-");
                   if( x<1){
                       System.out.println("Data is ready for saving please wait .....!");             
                       saveData();
             
                    }else{
                        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Sorry!( ",toString()+ "):Student  Name with exist"));
                        System.out.println("Sorry!:   "+toString() + "Exist");

                    }
    }
    /// ******************** Insertnew teachers record*************************
    public void insertTeacher(){
            newstakeholder. setIsTeacher(true);
            newstakeholder.setSchoolCode("CH333");
             chooser();
             System.out.println("data:"+newstakeholder.toString());
             System.out.println("date:"+ newstakeholder.getDateOfBirth());
             System.out.println("gendar:"+newstakeholder.getGender());
             System.out.println("canlogin:"+newstakeholder.getCanLogin());
             checkTeacherExist();
       
    }
    
    
    /**************************
     * *************************************************END TEACHERS RECORD*****************************************************
     * *************************************************************************************************************************
     */
}
