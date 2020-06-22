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
import java.util.Date;
import java.util.Random;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import org.primefaces.context.RequestContext;
import org.tracking.server.database.DatabaseBean;
import org.tracking.shared.persons.Person;
import org.tracking.util.CaseManipulation;

/**
 *
 * @author root
 */
public class NewHolders implements Serializable{
    public class Holders{
    private CaseManipulation cases = new CaseManipulation();
    private String stakeholderCode;
    private int id;
    private String schoolCode;
    private String studentID;
    private String TAC_ID ;
    private String work_ID;
    private String proffessionID;
    private String gender;
    private boolean isGender;
    private Date dateOfBirth;
    private String birthPlace;
    private String bloodGroup;
    private String nationality;
    private String religion;
    private Date created_Date;
    private String applicationNo;
    private String county;
    private String fatherName;
    private String motherName;
    private String gadianName;
    private boolean isEmailEnabled;
    private boolean isCanEmail, isAssigned;
    private boolean  isStudent, isTeacher,isSuperAdmin,isAdmin, isGuardian,isSabodinate_Staff,canLogin ,isBOG;
    private String job_Description, note, other_Name;
    
    private String studentID1, fname1,midname1,lname1;
    private String firstName;
    private String middleName;
    private String lastName;
    private String address1;
    private String address2;
    private String city;
    private String contact1, contact2, email;
         
    RequestContext context = RequestContext.getCurrentInstance();
        FacesMessage msg = null;
    public Holders(){
        firstName   ="";
        middleName   = "";
        lastName     ="";
        address1     = "";
        address2     = "";
        contact1     = "";
        contact2     = "";
        email        = "";
        city         = "";
        this.id = 0;
        this.stakeholderCode = "";
        this.schoolCode = "";
        this.other_Name = "";
        this.gender ="";
        this.dateOfBirth =new Date(); 
        this.birthPlace = "";
        this.bloodGroup = "";
        this.nationality = "";
        this.religion = "";
        this.county = "";
        this.studentID = "";
        this.TAC_ID = ""; 
        this.work_ID = "";
        this.fatherName = "";
        this.motherName = "";
        this.gadianName = "";
        this.proffessionID = "";
        this.isEmailEnabled = false;
        this.isStudent = false;
        this.isTeacher = false;
        this.isSuperAdmin = false;
        this.isAdmin = false;
        this.isBOG = false;
        this.canLogin = false;
        this.job_Description=  "";
        this.note= "";
        this.created_Date = new Date() ;
    }
    public Holders(Person thisPerson ){
        
        this.firstName = thisPerson.getFirstName();
        this.middleName = thisPerson.getMiddleName();
        this.lastName =  thisPerson.getLastName();
        this.contact1 =  thisPerson.getContact1();
        this.contact2 = thisPerson.getContact2();
        this.address1 = thisPerson.getAddress1();
        this.address2 = thisPerson.getAddress2();
        this.email =     thisPerson.getEmail();
        this.city =      thisPerson.getCity();
    }
   
    public Holders(int id, String stakecode, String schcode, String fname, String midname , String lname,
            String oname,String gender, Date dateofb,   String place, String bloodGp, String nationality, 
            String relig, String address1, String address2, String city, String county, String cont1,
            String cont2, String email, String studID, String TACID, String workID, String father, 
            String mother,String guardian,String prof, boolean isEmail,boolean isStud, boolean isTeacher,
            boolean isSuperAdmin,boolean isAdmin,boolean isBOG, boolean canLogin, String description, String note, Date created   ){
        
        this.firstName = fname;
        this.middleName = midname;
        this.lastName = lname;
        this.contact1 =cont1;
        this.contact2 = cont2;
        this.address1 = address1;
        this.address2 = address2;
       this. email = email;
        this.city = city;
        
        this.id = id;
        this.stakeholderCode = stakecode;
        this.schoolCode = schcode;
        this.other_Name = oname;
        this.gender =gender;
        this.dateOfBirth = dateofb; 
        this.birthPlace = place;
        this.bloodGroup = bloodGp;
        this.nationality = nationality;
        this.religion = relig;
        this.county = county;
        this.studentID = studID;
        this.TAC_ID = TACID; 
        this.work_ID = workID;
        this.fatherName = father;
        this.motherName = mother;
        this.gadianName = guardian;
        this.proffessionID = prof;
        this.isEmailEnabled = isEmail;
        this.isStudent = isStud;
        this.isTeacher = isTeacher;
        this.isSuperAdmin = isSuperAdmin;
        this.isAdmin = isAdmin;
        this.isBOG = isBOG;
        this.canLogin = canLogin;
        this.job_Description=  description;
        this.note= note;
        this.created_Date = created ;
    }
     public Holders(int id,String studID, String stakecode, String schcode, String fname, String midname , String lname,
            String oname,String gender, Date dateofb,   String place, String bloodGp, String nationality, 
            String relig, String address1, String address2, String city, String county, String cont1,
            String cont2, String email,  String father, String mother,String guardian, String note,boolean isAssgnd, Date created   ){
        
        this.firstName = fname;
        this.middleName = midname;
        this.lastName = lname;
        this.contact1 =cont1;
        this.contact2 = cont2;
        this.address1 = address1;
        this.address2 = address2;
       this. email = email;
        this.city = city;
        this.id = id;
        this.stakeholderCode = stakecode;
        this.schoolCode = schcode;
        this.other_Name = oname;
        this.gender =gender;
        this.dateOfBirth = dateofb; 
        this.birthPlace = place;
        this.bloodGroup = bloodGp;
        this.nationality = nationality;
        this.religion = relig;
        this.county = county;
        this.studentID = studID;
        this.fatherName = father;
        this.motherName = mother;
        this.gadianName = guardian;
        this.note= note;
        this.isAssigned = isAssgnd;
        this.created_Date = created ;
    }
    public Holders (String fn, String mn, String ln,
            String stake, String schCode){
        
        this.firstName = fn;
        this.middleName = mn;
        this.lastName = ln;
        this.stakeholderCode = stake.toUpperCase();
        this.schoolCode =schCode.toUpperCase();
        
    }
    public Holders(String stakecode, String schcode){
        
        this.stakeholderCode = stakecode.toUpperCase();
        this.schoolCode = schcode.toUpperCase();
        
    }
    /**
   *Gets First Name
   *@return Person's first name.
   */
    
    public String getFirstName(){
        return firstName;
    }
    /**
     * Gets Middle Name
     * @return Person's middle name
     */
    public String getMiddleName(){
        return middleName;
    }
    /**
     * Get last Name
     * @return Person's last Name
     */
    public String getLastName(){
        return lastName;
    }
    
    /**
   *Sets new First Name
   *@param str new First Name that is to be replaced over old First Name.
   */
    public void setFirstName(String str ){
        this.firstName = str;
    }
    
    public void setMiddleName(String str){
        
        this.middleName = str;
    }
    
    public void setLastName(String str){
        this.lastName = str;
    }
    /**
   *Returns the person's full name details.
   *@return Complete Person details.
   */
    @Override
    public String toString(){
        
        return getFirstName() +","+ getMiddleName()+ ", "+ getLastName();
    }
    /**
     * @return person's contact details
     */
    public String getAddress1(){
        return address1;
    }
    public void setAddress1(String str){
        
        this.address1 = cases.toSentenceCase(str);
    }
    
    public String getAddress2(){
        return address2;
    }
    public void setAddress2(String str){
        this.address2= str;
    }
    /**
     * 
     * @return contact1
     */
    public String getContact1(){
        return contact1;
    }
    public void setContact1(String str){
        this.contact1= str;
    }
    /**
     * @return contact2
     */
    public String getContact2(){
        return contact2;
    }
    public void setContact2(String str){
        this.contact2= str;
    }
    /**
     * @return email
     */
    public String getEmail(){
        return email;
    }
    public void setEmail(String str){
        this.email= str;
    }
    /**
     * @return city
     */
    public String getCity(){
        return city;
    }
    public void setCity(String str){
       this.city= str;
    }
    
    public boolean getIsAssigned(){
        return isAssigned;
    }
    public void setIsAssigned(boolean isAssigned){
        this.isAssigned = ((isAssigned = true)? isAssigned : false);
    }
    
    public String getStakeholderCode(){
        return stakeholderCode;
    }
    public void setStakeholderCode(String stakeholderCode){
        this.stakeholderCode = stakeholderCode.toUpperCase();
    }
    public String getSchoolCode(){return schoolCode;}
    public void setSchoolCode(String str){this.schoolCode = str.toUpperCase();}
            
    public int getId(){
        return id;
    }
    public void setID(int id){
        this.id = id;
    }
    
    /**
     * 
     * @return gender
     */
    public String getGender(){
        return gender;
    }
    public void setGender(String gender){
        this.gender = gender.toUpperCase();
    }
    public boolean getIsGender(){
        return isGender;
    }
    public void setIsGender(boolean isGender){
        this.isGender = ((isGender = true)? isGender : false);
    }
    public boolean getIsCanEmail(){
        return isCanEmail;
    }
    public void setIsCanEmail(boolean isCanEmail){
        this.isCanEmail = ((isCanEmail = true)? isCanEmail : false);
    }
    public boolean getIsStudent(){
        return isStudent;
    }
    public void setIsStudent(boolean isStudent){
        this.isStudent = ((isStudent = true)? isStudent: false);
    }
    public boolean getIsTeacher(){
        return isTeacher;
    }
    public void setIsTeacher(boolean isTeacher){
        this.isTeacher = ((isTeacher = true)? isTeacher: false);
    }
    public boolean getIsSuperAdmin(){
        return isSuperAdmin;
    }
    public void setIsSuperAdmin(boolean isSuperAdmin){
        this.isSuperAdmin = ((isSuperAdmin = true)? isSuperAdmin: false);
    }
    
    public boolean getIsAdmin(){
        return isAdmin;
    }
    public void setIsAdmin(boolean isAdmin){
        this.isAdmin = ((isAdmin = true)? isAdmin: false);
    }
    
    public boolean getIsGuardian(){
        return isGuardian;
    }
    public void setIsGuardian(boolean isGuardian){
        this.isGuardian= ((isGuardian = true)? isGuardian: false);
    }
    
    public boolean getIsSabodinate_Staff(){
        return isSabodinate_Staff;
    }
    public void setIsSabodinate_Staff(boolean isSabodinate_Staff){
        this.isSabodinate_Staff= ((isSabodinate_Staff = true)? isSabodinate_Staff: false);
    }
    
    public boolean getIsBOG(){
        return isBOG;
    }
    public void setIsBOG(boolean isBOG){
        this.isBOG= ((isBOG = true)? isBOG: false);
    }
    public boolean getCanLogin(){
        return canLogin;
    }
    public void setCanLogin(boolean canLogin){
        this.canLogin= ((canLogin = true)? canLogin: false);
    }
     
    
    public Date getDateOfBirth(){
        return dateOfBirth;
        
        
    }
    
    public void setDateOfBirth(Date dateOfBirth){
        this.dateOfBirth = dateOfBirth;
    }
    
    public  String getBirthPlace(){
        return birthPlace;
    }
    public void setBirthPlace(String birthPlace){
        this.birthPlace = ((birthPlace != null)? birthPlace.toUpperCase() :"") ;
    }
    
    public String getStudentID(){
        return studentID;
    }
    public void setStudentID(String studentID){
        this.studentID = ((studentID !=null)? studentID.toUpperCase() : "");
    }
    public String getTAC_ID(){
        return TAC_ID;
    }
    public void setTAC_ID(String tac_id){
        this.TAC_ID = ((tac_id !=null)? tac_id.toUpperCase() : "");
    }
    public String getWork_ID(){
        return work_ID;
    }
    public void setWork_ID(String work_id){
        this.work_ID = ((work_id !=null)? work_id.toUpperCase() : "");
    }
    
    
    public String getProffession_ID(){
        return proffessionID;
    }
    public void setProffession_ID(String pro){
        this.proffessionID = ((pro !=null)? pro.toUpperCase() : "");
    }
    public String getJobDescription(){
        return job_Description;
    }
    public void setJobDescription(String job){
        this.job_Description = ((job !=null)? job.toUpperCase() : "");
    }
    
    public String getOtherNames(){
        return other_Name;
    }
    public void setOtherNames(String oName){
        this.other_Name = ((oName!=null)? oName.toUpperCase() : "");
    }
    public String getNotes(){
        return note;
    }
    public void setNotes(String note){
        this.note = ((note!=null)? note.toUpperCase() : "");
    }
    
    public String getBloodGroup(){
        return bloodGroup;
    }
    public void setBloodGroup(String bloodGroup){
        this.bloodGroup = ((bloodGroup !=null)? bloodGroup.toUpperCase() : "");
    }
    
    public String getNationality(){
        return nationality;
    }
    public void setNationality(String nationality){
        this.nationality = ((nationality !=null)? nationality.toUpperCase() : ""); 
    }
    
    public String getReligion(){
        return religion;
    }
    public void setReligion(String religion){
        this.religion = ((religion !=null)? religion .toUpperCase(): "");
    }
    
    public Date getRegistrationDate(){
        return created_Date;
    }
    public void setRegistrationDate(Date regDate){
        this.created_Date = regDate;
    }
    
    public String getApplicationNo(){
        return applicationNo;
    }
    
    public void setApplicationNo(String applicationNo){
        this.applicationNo = ((applicationNo !=null)? applicationNo : "");
    }
        
    
    
    
   public String getCounty(){
       return county;
   } 
   public void setCounty(String county){
       this.county = county.toUpperCase();
   }
   
   public String getFatherName(){
       return fatherName;
   }
   
   public void setFatherName(String fatherName){
       this.fatherName = fatherName.toUpperCase();
   }
   
   public String getMotherName() {
       return motherName;
   }
   public void setMotherName(String motherName){
       this.motherName = motherName.toUpperCase();
   }
   
   public String getGadianName(){
       return gadianName;
   }
   public void setGadianName(String gadianName){
       this.gadianName = gadianName.toUpperCase();
   }
   
   public boolean isEmailEnabled(){
       return isEmailEnabled;
   }
   
   public void setIsEmailEnabled(boolean isEmailEnabled){
       this.isEmailEnabled = ((isEmailEnabled =true )? isEmailEnabled: false);
   }
   
   /***
    * db validation 
     * @return 
    */
   public String getStudId(){return studentID1;}
    public void setStudId(String str){
        this.studentID1 = ((str != null)? str.toUpperCase(): "");
    }
    public String getFname(){return fname1;}
    public void setFname(String fname){this.fname1 = fname.toUpperCase();}
    
    public String getMidname(){return midname1;}
    public void setMidname(String midname){this.midname1 = midname.toUpperCase();}
    
    public String getLname(){return lname1;}
    public void setlname(String lname){this.lname1 = lname.toUpperCase();}
   
   
   /**
    * end of db validation
    */
   public void insertStudent(){
       setIsStudent(true);
       setSchoolCode("CH333");
       chooser();
       System.out.println("data:"+toString());
       System.out.println("date:"+ getDateOfBirth());
       System.out.println("gendar:"+getGender());
       System.out.println("canlogin:"+getCanLogin());
       checkStudentExist();
       addStudent();
   }
   public void insertTeacher(){
       setIsTeacher(true);
       chooser();
       System.out.println("data:"+toString());
       System.out.println("date:"+ getDateOfBirth());
       System.out.println("gendar:"+getGender());
       System.out.println("canlogin:"+getCanLogin());
       //checkStudentExist();
       
   }
   public void insertSabodinate(){
       setIsSabodinate_Staff(true);
       chooser();
       System.out.println("data:"+toString());
       System.out.println("date:"+ getDateOfBirth());
       System.out.println("gendar:"+getGender());
       System.out.println("canlogin:"+getCanLogin());
       
   }
   /**
     * generating student Code 
     */
    public  void generateStudentCode(){
        Random newrandom = new Random();
        int random = newrandom.nextInt(1000)+8880;
        System.out.println("newNo:"+ random);
        String randomCode = String.valueOf("std/"+random);
        setStudentID(randomCode);
    }
    /**
     * generating generate Stakeholder  code 
     */
    public  void generateStakeholderCode(){
        Random newrandom = new Random();
        int random = newrandom.nextInt(1000)+8880;
        System.out.println("newNo:"+ random);
        String randomCode = String.valueOf("sth/"+random);
        setStakeholderCode(randomCode);
    }
    /**
     * generating generate teacher  code 
     */
    public  void generateTecherCode(){
        Random newrandom = new Random();
        int random = newrandom.nextInt(1000)+8880;
        System.out.println("newNo:"+ random);
        String randomCode = String.valueOf("TAC/"+random);
        setTAC_ID(randomCode);
    }
    /**
     * generating generate teacher  code 
     */
    public  void generateWorkerCode(){
        Random newrandom = new Random();
        int random = newrandom.nextInt(1000)+8880;
        System.out.println("newNo:"+ random);
        String randomCode = String.valueOf("sab/"+random);
        setTAC_ID(randomCode);
    }
    public void chooser(){
        if(getIsStudent() == true){
            System.err.println("generating stakeholder code....");
            generateStakeholderCode();
            System.err.println("generating student code....");
            generateStudentCode();
        }
        else if(getIsTeacher() == true){
            System.err.println("generating stakeholder code....");
            generateStakeholderCode();
            System.err.println("generating TAC code....");
            generateTecherCode();
        }
        else if(getIsSabodinate_Staff() == true){
            System.err.println("generating stakeholder code....");
            generateStakeholderCode();
            System.err.println("generating Workers code....");
            generateTecherCode();
        }
        else if((getIsSabodinate_Staff() == true) && (getIsTeacher() == true)){
            System.err.println("generating stakeholder code....");
            generateStakeholderCode();
            System.err.println("generating TAC code....");
            generateTecherCode();
            System.err.println("generating workers code....");
            generateWorkerCode();
        }else{
           setIsSuperAdmin(true);
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
                 + " WHERE  first_Name ='"+getFirstName().trim()+"'"
                 + " AND middle_Name ='"+getMiddleName().trim()+"' "
                 + "AND last_Name ='"+getLastName().trim()+"' LIMIT 1;";
         try{
             DatabaseBean db = new DatabaseBean();
             Connection con = db.DBconnect();
             PreparedStatement stmt = con.prepareStatement(query);
             stmt.setFetchSize(100);
             ResultSet rs = db.preparedState(stmt);
             while(rs.next()){
                 //setClassCode(rs.getString("class_code"));
                 setStudId(rs.getString("student_ID"));
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
                + "isSuperAdmin,isAdmin,isGuardian,isSabodinate_Staff,isBOG,canLogin,job_Description,note,created_Date, idno,passportno,"
                + "  maritalstatus, title,  postalcode,  faxno, othermail)"
                + " VALUES ('"+ getStakeholderCode()+"', '"+ getSchoolCode() +"','"+ getFirstName() +"',"
                + " '"+ getMiddleName() +"','"+ getLastName() +"','"+ getOtherNames() +"','"+ getGender() +"',"
                + "'"+ getDateOfBirth()+"','"+ getBirthPlace() +"','"+ getBloodGroup() +"','"+ getNationality() +"',"
                + "'"+ getReligion() +"','"+ getAddress1() +"','"+ getAddress2() +"','"+ getCity() +"',"
                + "'"+ getCounty() +"','"+ getContact1() +"','"+ getContact2() +"','"+ getEmail() +"',"
                + "'"+ getStudentID() +"','"+ getTAC_ID() +"','"+ getWork_ID() +"','"+ getFatherName() +"','"+ getMotherName() +"',"
                + "'"+ getGadianName() +"','"+ getProffession_ID() +"', '"+ getIsCanEmail() +"','"+ getIsStudent() +"',"
                + "'"+ getIsTeacher()+ "','"+ getIsSuperAdmin() +"','"+ getIsAdmin() +"','"+ getIsGuardian() +"',"
                + "'"+ getIsSabodinate_Staff() +"','"+ getIsBOG() +"','"+ getCanLogin() +"','"+ getJobDescription() +"',"
                + "'"+ getNotes() +"',now());";
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
        String mysql = "INSERT INTO student (student_ID,stackholder_Code, school_Code, first_Name, middle_Name,last_Name,other_Name,gender,"
                + "dateOf_Birth,birth_Place,blood_Group,nationality,religion,address1,address2,city,county ,contact1,contact2 ,email,"
                + "father_Name,mother_Name,guardian_Name,note,isAssigned,created_Date)"
                + " VALUES ('"+ getStudentID()+"','"+ getStakeholderCode()+"', '"+ getSchoolCode() +"','"+ getFirstName() +"',"
                + " '"+ getMiddleName() +"','"+ getLastName() +"','"+ getOtherNames() +"','"+ getGender() +"',"
                + "'"+ getDateOfBirth()+"','"+ getBirthPlace() +"','"+ getBloodGroup() +"','"+ getNationality() +"',"
                + "'"+ getReligion() +"','"+ getAddress1() +"','"+ getAddress2() +"','"+ getCity() +"',"
                + "'"+ getCounty() +"','"+ getContact1() +"','"+ getContact2() +"','"+ getEmail() +"',"
                + "'"+ getFatherName() +"','"+ getMotherName() +"','"+ getGadianName() +"','"+ getNotes() +"','"+ getIsAssigned()+"',now());";
        System.out.println("New student  SQL:"+ mysql);
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
    }
    
}
