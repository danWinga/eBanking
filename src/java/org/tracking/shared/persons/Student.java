/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.tracking.shared.persons;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author root
 */
public class Student extends Person implements Serializable{
    private String studCode;
    private int studID;
    private String gender;
    private boolean isGender;
    private Date dateOfBirth;
    private String birthPlace;
    private String bloodGroup;
    private String nationality;
    private String religion;
    private Date regDate;
    private String applicationNo;
    private String classCode;
    private String secCode;
    private String county;
    private String fatherName;
    private String motherName;
    private String gadianName;
    private boolean isEmailEnabled;
    
    public Student(){
        super();
    }
    public Student(Person thisPerson ){
        super(thisPerson);
        
    }
    public Student (String fn, String mn, String ln,
            String studCode, String classCode, String secCode){
        super(fn, mn,ln);
        this.studCode = studCode.toUpperCase();
        this.classCode =classCode.toUpperCase();
        this.secCode = secCode.toUpperCase();
    }
    public Student(String studCode, String classCode, String secCode){
        this.studCode = studCode.toUpperCase();
        this.studCode = studCode.toUpperCase();
        this.classCode =classCode.toUpperCase();
        this.secCode = secCode.toUpperCase();
    }
    public Student (String fn, String mn, 
            String ln, String ad1, String ad2, 
            String co1,String co2,String em, String ct ,String studCode,int studID,
            String gender,Date dateOfBirth,String birthPlace,String bloodGroup,
            String  nationality,String religion,Date regDate,String applicationNo,
            String classCode,String secCode, String county , String fatherName,
            String motherName , String gadian, boolean isEmailEnabled){
        super(fn, mn,ln,ad1,ad2,co1,co2,em,ct);
        this.studCode = studCode.toUpperCase();
        this.studID = studID ;
        this.gender = gender.toUpperCase();
        this.dateOfBirth = dateOfBirth;
        this.birthPlace  = birthPlace.toUpperCase();
        this.bloodGroup = bloodGroup;
        this.nationality = nationality.toUpperCase();
        this.religion = religion.toUpperCase();
        this.regDate = regDate; 
        this.applicationNo = applicationNo.toUpperCase();
        this.classCode = classCode.toUpperCase();
        this.secCode = secCode.toUpperCase();
        this.county = county.toUpperCase();
        this.fatherName = fatherName.toUpperCase();
        this.motherName = motherName.toUpperCase();
        this.gadianName = gadian.toUpperCase();
        this.isEmailEnabled  = isEmailEnabled;
    }
    public String geStudentCode(){
        return studCode;
    }
    public void setStudentCode(String sudCode){
        this.studCode = studCode.toUpperCase();
    }
    public int getStudentID(){
        return studID;
    }
    public void setStudentID(int studID){
        this.studID = studID;
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
        return regDate;
    }
    public void setRegistrationDate(Date regDate){
        this.regDate = regDate;
    }
    
    public String getApplicationNo(){
        return applicationNo;
    }
    
    public void setApplicationNo(String applicationNo){
        this.applicationNo = ((applicationNo !=null)? applicationNo : "");
    }
    
    public String getClassCode(){
        return classCode;
    }
    public void setClassCode(String classCode){
        this.classCode = classCode.toUpperCase();
    }
    
    public String getSecCode(){
        return secCode;
    }
    public void setSecCode(String secCode){
        this.secCode = secCode.toUpperCase();
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
   public String toString(){
       return geStudentCode() + ", "+getClassCode() + ", "+ getSecCode() ;
   }
    
}
