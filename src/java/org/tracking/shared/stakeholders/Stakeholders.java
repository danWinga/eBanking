/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.tracking.shared.stakeholders;

import java.io.Serializable;
import java.util.Date;
import org.tracking.util.CaseManipulation;

/**
 *
 * @author root
 */

public class Stakeholders implements Serializable{
    private CaseManipulation cases  = new CaseManipulation();
    private String stakeholderCode;    private int id;
    private String schoolCode;         private String studentID;
    private String TAC_ID ;            private String work_ID;
    private String proffessionID;      private String gender;
    private boolean isGender;          private Date dateOfBirth;
    private String birthPlace;         private String bloodGroup;
    private String nationality;        private String religion;
    private Date created_Date;         private String applicationNo;
    private String county;             private String fatherName;
    private String motherName;         private String gadianName;
    private boolean isEmailEnabled;    private boolean isCanEmail, isAssigned;
    private boolean  isStudent, isTeacher,isSuperAdmin,isAdmin, isGuardian,isSabodinate_Staff,canLogin ,isBOG;
    private String job_Description, note, other_Name;
    private int idno;
    private String passportno; private String maritalstatus;
    private String stakeTitle ;  private String postalCode;
    private String faxNumber;   private String otherMail;
    private boolean isGenderz;
    
    private String studentID1, fname1,midname1,lname1;
    private String firstName;     private String middleName;
    private String lastName;      private String address1;
    private String address2;      private String city;
    private String contact1, contact2, email;
         
    
    public Stakeholders(){
        firstName   ="";        middleName   = "";        lastName     ="";
        address1     = "";      address2     = "";        contact1     = "";
        contact2     = "";        email        = "";      city         = "";
        id = 0;
        stakeholderCode = "";    schoolCode = "";           other_Name = "";
        gender ="";             dateOfBirth =new Date();    birthPlace = "";
        bloodGroup = "";        nationality = "";           religion = "";
        county = "";            studentID = "";             TAC_ID = ""; 
        work_ID = "";           fatherName = "";            motherName = "";
        gadianName = "";        proffessionID = "";         isEmailEnabled = false;
        isStudent = false;      isTeacher = false;          isSuperAdmin = false;
        isAdmin = false;        isBOG = false;              canLogin = false;
        job_Description=  "";   note= "";                   created_Date = new Date() ;
        isGenderz = false;      idno=0;                     passportno = "";
        maritalstatus = "";     stakeTitle = "";                 postalCode = "";
        otherMail = "";         faxNumber = "";        
        
        
    }
    public Stakeholders(Stakeholders thisPerson ){
        
        this.firstName =thisPerson.getFirstName() ;
        this.middleName = thisPerson.getMiddleName();
        this.lastName = thisPerson.getLastName();
        this.contact1 =thisPerson.getContact1();
        this.contact2 = thisPerson.getContact2();
        this.address1 = thisPerson.getAddress1();
        this.address2 = thisPerson.getAddress2();
        this. email = thisPerson.getEmail();
        this.city = thisPerson.getEmail();
        
        this.id =thisPerson.getId();
        this.stakeholderCode = thisPerson.getStakeholderCode();
        this.schoolCode = thisPerson.getSchoolCode();
        this.other_Name =thisPerson.getOtherNames();
        this.gender =thisPerson.getGender();
        this.dateOfBirth = thisPerson.getDateOfBirth(); 
        this.birthPlace = thisPerson.getBirthPlace();
        this.bloodGroup = thisPerson.getBloodGroup();
        this.nationality = thisPerson.getNationality();
        this.religion = thisPerson.getReligion();
        this.county = thisPerson.getCounty();
        this.studentID = thisPerson.getStudentID();
        this.TAC_ID = thisPerson.getTAC_ID();
        this.work_ID = thisPerson.getWork_ID();
        this.fatherName = thisPerson.getFatherName();
        this.motherName = thisPerson.getMotherName();
        this.gadianName =thisPerson.getGadianName();
        this.proffessionID = thisPerson.getProffession_ID();
        this.isStudent = thisPerson.getIsStudent();
        this.isTeacher = thisPerson.getIsTeacher();
        this.isSuperAdmin = thisPerson.getIsSuperAdmin();
        this.isAdmin = thisPerson.getIsAdmin();
        this.isBOG = thisPerson.getIsBOG();
        this.canLogin = thisPerson.getCanLogin();
        this.job_Description=  thisPerson.getJobDescription();
        this.note= thisPerson.getNotes();
        this.created_Date =thisPerson.getRegistrationDate() ;
        this.idno = thisPerson.getIdno();
        
        this.maritalstatus = thisPerson.getMaritalstatus();
        this.stakeTitle= thisPerson.getStakeTitle();
        this.passportno = thisPerson.getPassportno();
        this.postalCode = thisPerson.getPostalCode();
        this.faxNumber = thisPerson.getFaxNumber();
        this.otherMail = thisPerson.getOtherMail();
    }
    /**
    
     * @param id
     * @param stakecode
     * @param schcode
     * @param fname
     * @param midname
     * @param lname
     * @param oname
     * @param gender
     * @param dateofb
     * @param place
     * @param bloodGp
     * @param nationality
     * @param relig
     * @param address1
     * @param address2
     * @param city
     * @param county
     * @param cont1
     * @param cont2
     * @param email
     * @param studID
     * @param TACID
     * @param workID
     * @param father
     * @param mother
     * @param guardian
     * @param prof
     * @param isEmail
     * @param isStud
     * @param isTeacher
     * @param isSuperAdmin
     * @param isAdmin
     * @param isBOG
     * @param canLogin
     * @param description
     * @param note
     * @param created 
     */
   
    /**
     *
     * @param id
     * @param stakecode
     * @param schcode
     * @param fname
     * @param midname
     * @param lname
     * @param oname
     * @param gender
     * @param dateofb
     * @param place
     * @param bloodGp
     * @param nationality
     * @param relig
     * @param address1
     * @param address2
     * @param city
     * @param county
     * @param cont1
     * @param cont2
     * @param email
     * @param studID
     * @param TACID
     * @param workID
     * @param father
     * @param mother
     * @param guardian
     * @param prof
     * @param isEmail
     * @param isStud
     * @param isTeacher
     * @param isSuperAdmin
     * @param isAdmin
     * @param isBOG
     * @param canLogin
     * @param description
     * @param note
     * @param created
     * @param idno
     * @param passportno
     * @param maritalstatus
     * @param title
     * @param postalcode
     * @param faxno
     * @param othermail
     */
    public Stakeholders(int id, String stakecode, String schcode, String fname, String midname , String lname,
            String oname,String gender, Date dateofb,   String place, String bloodGp, String nationality, 
            String relig, String address1, String address2, String city, String county, String cont1,
            String cont2, String email, String studID, String TACID, String workID, String father, 
            String mother,String guardian,String prof, boolean isEmail,boolean isStud, boolean isTeacher,
            boolean isSuperAdmin,boolean isAdmin,boolean isBOG, boolean canLogin, String description, String note, Date created,
            int idno,String passportno, String maritalstatus,String title, String postalcode, String faxno, String othermail  ){
        
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
        this.idno = idno;
        this.passportno = passportno;
        this.maritalstatus = maritalstatus;
        this.stakeTitle = title;
        this.postalCode = postalcode;
        this.faxNumber= faxno;
        this.otherMail = othermail;
    }
    /**
     * 
     * @param id
     * @param studID
     * @param stakecode
     * @param schcode
     * @param fname
     * @param midname
     * @param lname
     * @param oname
     * @param gender
     * @param dateofb
     * @param place
     * @param bloodGp
     * @param nationality
     * @param relig
     * @param address1
     * @param address2
     * @param city
     * @param county
     * @param cont1
     * @param cont2
     * @param email
     * @param father
     * @param mother
     * @param guardian
     * @param note
     * @param isAssgnd
     * @param created
     * @param idno
     * @param passportno
     * @param maritalstatus
     * @param title
     * @param faxno
     * @param postalcode
     * @param othermail
     
     */
     public Stakeholders(int id,String studID, String stakecode, String schcode, String fname, String midname , String lname,
            String oname,String gender, Date dateofb,   String place, String bloodGp, String nationality, 
            String relig, String address1, String address2, String city, String county, String cont1,
            String cont2, String email,  String father, String mother,String guardian, String note,boolean isAssgnd, Date created,
            int idno,String passportno, String maritalstatus,String title, String postalcode, String faxno, String othermail){
        
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
        
        this.idno = idno;
        this.passportno = passportno;
        this.maritalstatus = maritalstatus;
        this.stakeTitle = title;
        this.postalCode = postalcode;
        this.faxNumber = faxno;
        this.otherMail = othermail;
    }
     /**
      * 
      * @param id
      * @param stakecode
      * @param schcode
      * @param fname
      * @param midname
      * @param lname
      * @param oname
      * @param gender
      * @param dateofb
      * @param place
      * @param bloodGp
      * @param nationality
      * @param relig
      * @param address1
      * @param address2
      * @param city
      * @param county
      * @param cont1
      * @param cont2
      * @param email
      * @param studid
      * @param tacid
      * @param workid
      * @param father
      * @param mother
      * @param guardian
      * @param proffession
      * @param canemail
      * @param isStudent
      * @param isTeacher
      * @param isSuperAdm
      * @param isadmin
      * @param isBOG
      * @param canLogin
      * @param job
      * @param note
      * @param created 
      */
     public Stakeholders(int id,String stakecode, String schcode, String fname, String midname , String lname,
            String oname,String gender, Date dateofb,   String place, String bloodGp, String nationality, 
            String relig, String address1, String address2, String city, String county, String cont1,
            String cont2, String email, String studid,String tacid, String workid, String father, String mother,String guardian,String proffession,Boolean canemail,
            Boolean isStudent, Boolean isTeacher, Boolean isSuperAdm, Boolean isadmin, Boolean isBOG,Boolean canLogin,String job,String note, Date created   ){
         
         
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
        this.TAC_ID = tacid;
        this.work_ID = workid;
        this.proffessionID = proffession;
        this.isEmailEnabled =canemail;
        this.isStudent = isStudent;
        this.isTeacher = isTeacher;
        this.TAC_ID = tacid;
        this.work_ID = workid;
        this.isSuperAdmin = isSuperAdm;
        this.isAdmin = isadmin;
        this.isBOG = isBOG;
        this.canLogin = canLogin;
        this.job_Description =job;
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
        this.studentID = studid;
        this.fatherName = father;
        this.motherName = mother;
        this.gadianName = guardian;
        this.note= note;
       
        this.created_Date = created ;
     }
     public Stakeholders(String holdercode, String fn,String mn, String ln){
         this.stakeholderCode  = holdercode;
         this.firstName = fn;
         this.middleName = mn;
         this.lastName = ln;
         
     }
    public Stakeholders (String fn, String mn, String ln,
            String stake, String schCode){
        
        this.firstName = fn;
        this.middleName = mn;
        this.lastName = ln;
        this.stakeholderCode = stake.toUpperCase();
        this.schoolCode =schCode.toUpperCase();
        
    }
    public Stakeholders(String stakecode, String schcode){
        this.stakeholderCode = stakecode.toUpperCase();
        this.schoolCode = schcode.toUpperCase();
        
    }
    
    public int getIdno(){return idno;}
    public void setIdno(int idno){this.idno = idno;}
    
    
    public String getPassportno(){return passportno;}
    
    public void setPassportno(String str){
        this.passportno = cases.toSentenceCase(str);
    }
    
    
    public String getMaritalstatus(){return maritalstatus;}
    public void setMaritalstatus(String maritalstatus ){
        this.maritalstatus =cases.toSentenceCase(maritalstatus);
    }
    
    public String getStakeTitle(){return stakeTitle;}
    public void setStakeTitle(String str){
        this.stakeTitle =cases.toSentenceCase(str) ;
    }
    
    
    
    
    public String getPostalCode(){return postalCode; }
    
    public void setPostalCode(String str ){
        this.postalCode =cases.toSentenceCase(str);
    }
    
    
    // passportno,maritalstatus,title, postalcode, faxno, othermail;
    
    public String getFaxNumber(){return faxNumber; }
    public void setFaxNumber(String str ){
        this.faxNumber =cases.toUpperCase(str);
    }
    
    public String getOtherMail(){return otherMail; }
    public void setOtherMail(String str ){
        this.otherMail =cases.toLowerCase(str);
    }
    
       
    /**
   *Gets First Name
   *@return Person's first name.
   */
    
    public String getFirstName(){return firstName; }
    
    public String getMiddleName(){ return middleName; }
   
    public String getLastName(){  return lastName;  }
    
    
    
    public void setFirstName(String str ){
        this.firstName =cases.toSentenceCase(str);
    }
    
    public void setMiddleName(String str){
        this.middleName = cases.toSentenceCase(str);
    }
    
    public void setLastName(String str){
        this.lastName = cases.toSentenceCase(str);
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
    public String getAddress1(){ return address1; }
    public void setAddress1(String str){
        this.address1= cases.toSentenceCase(str);
    }
    
    public String getAddress2(){ return address2; }
    public void setAddress2(String str){
        this.address2 = cases.toSentenceCase(str);
    }
    /**
     * 
     * @return contact1
     */
    public String getContact1(){ return contact1;    }
    public void setContact1(String str){
        this.contact1= cases.toSentenceCase(str);
    }
    /**
     * @return contact2
     */
    public String getContact2(){ return contact2;   }
    public void setContact2(String str){
        this.contact2= cases.toSentenceCase(str);
    }
    /**
     * @return email
     */
    public String getEmail(){ return email; }
    public void setEmail(String str){
        this.email= cases.toLowerCase(str);
    }
    /**
     * @return city
     */
    public String getCity(){ return city; }
    public void setCity(String str){
       this.city= cases.toSentenceCase(str);
    }
    
    public boolean getIsAssigned(){  return isAssigned;  }
    public void setIsAssigned(boolean isAssigned){
        this.isAssigned = ((isAssigned = true)? isAssigned : false);
    }
    
    public String getStakeholderCode(){ return stakeholderCode; }
    public void setStakeholderCode(String str){
        this.stakeholderCode = cases.toUpperCase(str);
    }
    public String getSchoolCode(){return schoolCode;}
    public void setSchoolCode(String str){
        this.schoolCode = cases.toUpperCase(str);
    }
            
    public int getId(){     return id;  }
    public void setID(int id){
        this.id = id;
    }
    
    /**
     * 
     * @return gender
     */
    public String getGender(){ return gender; }
    public void setGender(String str){
        this.gender = cases.toSentenceCase(str);
    }
    public boolean getIsGender(){ return isGender; }
    public void setIsGender(boolean isGender){
        this.isGender = isGender ;
    }
    public boolean getIsCanEmail(){ return isCanEmail;}
    public void setIsCanEmail(boolean isCanEmail){
        this.isCanEmail = isCanEmail ;
    }
    public boolean getIsStudent(){return isStudent; }
    public void setIsStudent(boolean isStudent){
        this.isStudent =  isStudent;
    }
    public boolean getIsTeacher(){  return isTeacher; }
    public void setIsTeacher(boolean isTeacher){
        this.isTeacher = isTeacher ;
    }
    public boolean getIsSuperAdmin(){ return isSuperAdmin; }
    public void setIsSuperAdmin(boolean isSuperAdmin){
        this.isSuperAdmin = isSuperAdmin;
    }
    
    public boolean getIsAdmin(){ return isAdmin; }
    public void setIsAdmin(boolean isAdmin){
        this.isAdmin = isAdmin;
    }
    
    public boolean getIsGuardian(){ return isGuardian;  }
    public void setIsGuardian(boolean isGuardian){
        this.isGuardian=  isGuardian;
    }
    
    public boolean getIsSabodinate_Staff(){
        return isSabodinate_Staff;
    }
    public void setIsSabodinate_Staff(boolean isSabodinate_Staff){
        this.isSabodinate_Staff=  isSabodinate_Staff;
    }
    
    public boolean getIsBOG(){   return isBOG; }
    public void setIsBOG(boolean isBOG){
        this.isBOG= isBOG;
    }
    public boolean getCanLogin(){  return canLogin;  }
    public void setCanLogin(boolean canLogin){
        this.canLogin= canLogin;
    }
     
    
    public Date getDateOfBirth(){ return dateOfBirth; }
    
    public void setDateOfBirth(Date dateOfBirth){
        this.dateOfBirth = dateOfBirth;
    }
    
    public  String getBirthPlace(){ return birthPlace; }
    public void setBirthPlace(String str){
        this.birthPlace =  cases.toSentenceCase(str)  ;
    }
    
    public String getStudentID(){  return studentID; }
    public void setStudentID(String str){
        this.studentID = cases.toUpperCase(str);
    }
    public String getTAC_ID(){  return TAC_ID; }
    public void setTAC_ID(String str){
        this.TAC_ID = cases.toUpperCase(str);
    }
    public String getWork_ID(){   return work_ID; }
    public void setWork_ID(String str){
        this.work_ID =  cases.toUpperCase(str);
    }
    
    
    public String getProffession_ID(){  return proffessionID;  }
    public void setProffession_ID(String str){
        this.proffessionID = cases.toUpperCase(str);
    }
    public String getJobDescription(){ return job_Description; }
    public void setJobDescription(String str){
        this.job_Description = cases.toSentenceCase(str);
    }
    
    
    public String getOtherNames(){ return other_Name; }
    public void setOtherNames(String str){
        this.other_Name = cases.toSentenceCase(str);
    }
    public String getNotes(){  return note; }
    public void setNotes(String str){
        this.note = cases.toSentenceCase(str);
    }
    
    public String getBloodGroup(){ return bloodGroup; }
    public void setBloodGroup(String str){
        this.bloodGroup = cases.toUpperCase(str);
    }
    
    public String getNationality(){ return nationality; }
    public void setNationality(String str){
        this.nationality = cases.toSentenceCase(str); 
    }
    
    public String getReligion(){ return religion;}
    public void setReligion(String str){
        this.religion = cases.toSentenceCase(str);
    }
    
    public Date getRegistrationDate(){return created_Date; }
    public void setRegistrationDate(Date regDate){
        this.created_Date = regDate;
    }
    
    public String getApplicationNo(){ return applicationNo; }
    
    public void setApplicationNo(String str){
        this.applicationNo = cases.toUpperCase(str);
    }
       
    
   public String getCounty(){  return county; } 
   public void setCounty(String str){
       this.county = cases.toSentenceCase(str);
   }
   
   public String getFatherName(){  return fatherName; }
   
   public void setFatherName(String str){
       this.fatherName = cases.toSentenceCase(str);
   }
   
   public String getMotherName() { return motherName; }
   public void setMotherName(String str){
       this.motherName = cases.toSentenceCase(str);
   }
   
   public String getGadianName(){ return gadianName; }
   public void setGadianName(String str){
       this.gadianName = cases.toSentenceCase(str);
   }
   
   public boolean isEmailEnabled(){ return isEmailEnabled; }
   
   public void setIsEmailEnabled(boolean isEmailEnabled){
       this.isEmailEnabled = isEmailEnabled;
   }
   
   /***
    * db validation 
     * @return 
    */
   public String getStudId(){return studentID1;}
    public void setStudId(String str){
        this.studentID1 = cases.toUpperCase(str);
    }
    public String getFname(){return fname1;}
    public void setFname(String str){this.fname1 = cases.toSentenceCase(str);}
    
    public String getMidname(){return midname1;}
    public void setMidname(String str){this.midname1 = cases.toSentenceCase(str);}
    
    public String getLname(){return lname1;}
    public void setlname(String str){this.lname1 = cases.toSentenceCase(str);}
   
    public boolean getIsGendarz(){
       
        return isGenderz;
    }
    public void setIsGendarz(boolean isGenderz){
        this.isGenderz = isGenderz;
        
    }
    public void formateGender(String gender){
        
         if(gender.equalsIgnoreCase( "Male")){
            this.isGenderz = true;
        }
        else if(gender.equalsIgnoreCase("Female")){
           this. isGenderz = false;
         }
        else{
            this.isGenderz = false;    
            }
    }
    public String setSelectedGender(boolean isgenderz){
        
        if(getIsGendarz() == true){
            setGender("Male");
        }
        
        else{
            setGender("Female");
        }
        return getGender();
    }
}
