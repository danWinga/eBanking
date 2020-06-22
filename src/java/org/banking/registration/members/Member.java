/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.banking.registration.members;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author dan
 */
public class Member  implements Serializable{

    private static final long serialVersionUID = 1L;
    
   
    private String firstName,  lastName,otherNames, gender,memberNo,
            title,phoneNo,cellNo, officeNo,fax,postalAddress,homeAddr,town,careOf,
            email,website,physicalAddress,street,building,blockNo,occupation,
            utilityName,utilityCompany, utilityAccNo,fullName, country, maritalStatus,
            passportNo,staffNo,nationality,branchCode,auditID,surname;
    private Date dateOfIssue,auditTime,withdrawaldate, createDate;   
    private String pinNo, issuingAuthority, placeOfIssue,otherBankCode,otherBankname;
    private int  idNo,shares,incomeEstimate;
    private Date dateOfBirth,expDate;
    private boolean isStaff,isCustomer,isStudent,isBeneficiary,isSignatory;
    
    private String nextOfKinNo ,accNo;
    private int benefNo;
    
    private String  accTypeCode, productCode,currencyCode, incomeCode;
    private int totalBalance,availableAmount;
    private boolean isActive;
    
    public Member(){
        isActive = false;         isSignatory = false;        isBeneficiary= false;
        isCustomer = false;        isStaff = false;           isStudent = false;
        dateOfBirth = new Date();  dateOfIssue = new Date();   expDate = new Date();
        auditTime = new Date();     withdrawaldate = new Date();   createDate = new Date();
        
        firstName ="";         lastName = "";           otherNames = "";        gender ="";
        memberNo = "";          title = "";              phoneNo  = "";         cellNo  = "";
        officeNo = "";          fax = "" ;              postalAddress = "";     homeAddr ="";
        town = "";              careOf = "";            email = "";             website = "";
        physicalAddress = "";   street = "";            building = "";          blockNo = "";
        occupation = "" ;       utilityName = "";       utilityCompany = "";     utilityAccNo ="";
        fullName = "";           country = "" ;          maritalStatus = "";    passportNo = "";
        staffNo = "";           nationality = "";       branchCode = "";        auditID = ""; 
        surname = "";           pinNo  ="";             issuingAuthority ="";   placeOfIssue ="";
        otherBankCode ="";       otherBankname = "";    idNo =0 ;            shares = 0;
        incomeEstimate = 0; 
    }
    
    public String getAccTypeCode(){
        return accTypeCode;
    }
    public void setAccTypeCode(String str){
        this.accTypeCode = str;
    }
    
    public String getProductCode(){
        return productCode;
    }
    public void setProductCode(String str){
        this.productCode = str;
    }
    
    
    public String getCurrencyCode(){
        return currencyCode;
    }
    public void setCurrencyCode(String str){
        this.currencyCode = str;
    }
    public String getIncomeCode(){
        return incomeCode;
    }
    public void setIncomeCode(String str){
        this.incomeCode = str;
    }
    
    public int getTotalBalance(){
        return totalBalance;
    }
    public void setTotalBalance(int str){
        this.totalBalance = str;
    }
    
    public int getAvailableAmount(){
        return availableAmount;
    }
    public void setAvailableAmount(int str){
        this.availableAmount = str;
    }
    
    public boolean getIsActive(){
        return isActive;
    }
    public void setIsActive(boolean str){
        this.isActive = str;
    }
    
    public Member(String memberNo,int idNo, String fullname ){
        this.memberNo = memberNo;
        this.idNo = idNo;
        this.fullName = fullname;
    }

    public Member(int benfi, String accNo, String nextOfKinNo, String memberNo, String surname,
            String othernames,String officialCellno,String homeTelNo ,String presentAddr) {
        
        this.benefNo = benfi; 
        this.nextOfKinNo = nextOfKinNo;
        this.accNo = accNo;
        this.memberNo = memberNo;
        this.surname = surname;
        this.otherNames = othernames;
        this.officeNo = officialCellno;
        this.cellNo = homeTelNo;
        this.homeAddr = presentAddr;
        
    }
    /**
     * 
     * @param memberNo
     * @param title
     * @param surname
     * @param othername
     * @param gender
     * @param cell
     * @param presentAddr
     * @param idno 
     */
    
    public Member(String memberNo, String title, String surname, String othername, String gender,
            String cell,String presentAddr, int idno) {
        
        
        this.title = title;
        this.memberNo = memberNo;
        this.surname = surname;
        this.otherNames = othername;
        this.gender = gender;
        this.cellNo = cell;
        this.idNo = idno;
        this.homeAddr = presentAddr;
        
    }
    //memberNo, title, surname,otherNames, gender,officialCellno,IDNo
    public Member (Member thisMember){
        this.benefNo = thisMember.getBenefNo(); 
        this.nextOfKinNo = thisMember.getNextOfKinNo();
        this.accNo = thisMember.getAccNo();
        this.memberNo = thisMember.getMemberNo();
        this.surname = thisMember.getSurname();
        this.otherNames = thisMember.getOtherNames();
        this.officeNo = thisMember.getOfficeNo();
        this.cellNo = thisMember.getHomeAddr();
        this.homeAddr = thisMember.getHomeAddr();
        
    }
    /**
     * 
     * @param memberNo  
     * @param passport
     * @param dateOfBirth
     * @param maritalStatus
     * @param occupation
     * @param nationality
     * @param officialCellno
     * @param utilityCompany
     * @param utilityAccNo
     * @param IDNo
     * @param email
     * @param homeTelNo
     * @param presentAddr
     * @param careOf
     * @param street
     * @param homeAddr
     * @param currentTown
     * @param incomeEstimate
     * @param issuedBy
     * @param placeOfIssue
     * @param dateOfIssue
     * @param branchCode
     * @param utility
     * @param exprireDate
     * @param PIN
     * @param otherBankCode
     * @param isCustomer
     * @param isStudent
     * @param isBeneficiary
     * @param otherBankname
     * @param auditID
     * @param auditTime
     * @param withdrawaldate
     * @param status
     * @param isSignatory
     * @param isStaff
     * @param createDate
     */
    public Member(String memberNo, String staffNo,String title,String passport,String surname,String otherNames ,String gender,
            Date dateOfBirth,String maritalStatus,String   occupation,String nationality ,String officialCellno,
          String email,String homeTelNo, String presentAddr ,String careOf, String street,String homeAddr,String currentTown,
          int IDNo,String issuedBy,String placeOfIssue,Date dateOfIssue,Date exprireDate,String utility,
          String utilityCompany,String utilityAccNo, String branchCode,String PIN,String otherBankCode,
          String otherBankname, String auditID,Date auditTime,Date withdrawaldate,boolean status,boolean isStaff,
          boolean isCustomer, boolean isStudent, boolean isBeneficiary, boolean isSignatory,Date createDate){
        this.memberNo = memberNo;           this.staffNo = staffNo;                 this.passportNo = passport;
        this.surname = surname;             this.otherNames = otherNames;           this.gender = gender;
        this.dateOfBirth =dateOfBirth;      this.maritalStatus = maritalStatus;     this.occupation = occupation;
        this.nationality = nationality;     this.officeNo = officialCellno;         this.email = email;
        this.cellNo = homeTelNo ;           this.physicalAddress = presentAddr;     this.occupation = occupation;
        this.careOf = careOf;               this.street = street;                   this.homeAddr = homeAddr;
        this.town = currentTown;            this.idNo = IDNo;                       this.issuingAuthority = issuedBy;
        this.placeOfIssue = placeOfIssue;   this.dateOfIssue = dateOfIssue;         this.expDate = exprireDate;
        this.utilityName = utility;         this.utilityCompany = utilityCompany ;  this.utilityAccNo = utilityAccNo;
        this.branchCode = branchCode;         this.pinNo = PIN;                     this.title = title;
        this.otherBankCode = otherBankCode; this.otherBankname = otherBankname;     this.auditID = auditID;
        this.auditTime = auditTime;         this.withdrawaldate = withdrawaldate;   this.isActive = status;
        this.isStaff = isStaff;             this.isCustomer = isCustomer;           this.isStudent = isStudent;
        this.isBeneficiary = isBeneficiary; this.isSignatory = isSignatory;         this.createDate = createDate;
    }
    
    /*******************************************************
     * ********** Personal record   ***********************
     * @return 
     */
    public String getFirstName(){
        return firstName;
    }
    public void setFirstName(String str){
        this.firstName = str;
    }
    
    public String getSurname(){
        return surname;
    }
    public void setSurname(String str){
        this.surname = str;
    }
    
    public String getLastName(){
        return lastName;
    }
    public void setLastName(String str){
        this.lastName = str;
    }
    
    public String getOtherNames(){
        return otherNames;
    }
    public void setOtherNames(String str){
        this.otherNames = str;
    }
    
    public String getGender(){
        return gender;
    }
    public void setGender(String str){
        this.gender = str;
    }
    
    public String getMemberNo(){
        return memberNo;
    }
    public void setMemberNo(String str){
        this.memberNo = str;
    }
    
    public String getTitle(){
        return title;
    }
    public void setTitle(String str){
        this.title = str;
    }
    
    public String getPhoNo(){
        return phoneNo;
    }
    public void setPhoneNo(String str){
        this.phoneNo = str;
    }
    
    public String getCellNo(){
        return cellNo;
    }
    public void setCellNo(String str){
        this.cellNo = str;
    }
    
    public String getFax(){
        return fax;
    }
    public void setFax(String str){
        this.fax = str;
    }
    public String getPostalAddress(){
        return postalAddress;
    }
    public void setPostalAddress(String str){
        this.postalAddress= str;
    }
    public int getIncomeEstimate(){
        return incomeEstimate;
    }
    public void setIncomeEstimate(int str){
        this.incomeEstimate=str;
    }
    public String getTown(){
        return town;
    }
    public void setTown(String str){
        this.town= str;
    }
    public String getNationality(){
        return nationality;
    }
    public void setNationality(String str){
        this.nationality= str;
    }
    public String getCareOf(){
        return careOf;
    }
    public String getEmail(){
        return email;
    }
    public void setEmail(String str){
        this.email= str;
    }
    public String getWebsite(){
        return website;
    }
    public void setWebsite(String str){
        this.website= str;
    }
    public void setCareOf(String str){
        this.careOf= str;
    }
    
    public String getHomeAddr(){
        return homeAddr;
    }
    public void setHomeAddr(String str){
        this.homeAddr= str;
    }
    
    public String getPhysicalAddress(){
        return physicalAddress;
    }
    public void setPhysicalAddress(String str){
        this.physicalAddress= str;
    }
    public String getOccupation(){
        return occupation;
    }
    public void setOccupation(String str){
        this.occupation = str;
    }
    
    public String getStreet(){
        return street;
    }
    public void setStreet(String str){
        this.street= str;
    }
    public String getBuilding(){
        return building;
    }
    public void setBuilding(String str){
        this.building= str;
    }
    
    public String getBlockNo(){
        return blockNo;
    }
    public void setBlockNo(String str){
        this.blockNo= str;
    }
    
    public Date getDateOfBirth(){
        return dateOfBirth;
    }
    public void setDateOfBirth(Date dob){
        this.dateOfBirth = dob;
    }
    
    
    public int getIdNo(){
        return idNo;
    }
    public void setIdNo(int str){
        this.idNo = str;
    }
    // utility company
    public String getUtilityCompany(){
        return utilityCompany;
    }
    public void setUtilityCompany(String str){
        this.utilityCompany= str;
    }
    // utility Name
    public String getUtilityName(){
        return utilityName;
    }
    public void setUtilityName(String str){
        this.utilityName= str;
    }
    // utitity Account Number
    public String getUtilityAccNo(){
        return utilityAccNo;
    }
    public void setUtilityAccNo(String str){
        this.utilityAccNo= str;
    }
    public String getFullName(){
       fullName =  getSurname() + " "+ getOtherNames();
        return fullName;
    }
    public void setFullName(String str){
        this.fullName = str;
    }
    
    public String getCountry(){
        return country;
    }
    public void setCountry(String str){
        this.country= str;
    }
    
    public String getMaritalStatus(){
        return maritalStatus;
    }
    // maritalStatus
    public void setMaritalStatus(String str){
        this.maritalStatus= str;
    }
    // share Holding in percentage
    public int getShares(){
        return shares;
    }
    public void setShares(int str){
        this.shares= str;
    }
    // office Number
    public String getOfficeNo(){
        return officeNo;
    }
    public void setOfficeNo(String str){
        this.officeNo= str;
    }
    
    
    
    // passport Number
    public String getPassportNo(){
        return passportNo;
    }
    public void setPassportNo(String str){
        this.passportNo= str;
    }
    // Date of issue
    public Date getDateOfIssue(){
        return dateOfIssue;
    }
    public void setDateOfIssue(Date str){
        this.dateOfIssue= str;
    }
    // expire Date
    public Date getExpDate(){
        return expDate;
    }
    public void setExpDate(Date str){
        this.expDate= str;
    }
     // PIN Number
    public String getPinNo(){
        return pinNo;
    }
    public void setPinNo(String str){
        this.pinNo= str;
    }
    
    public String getStaffNo(){
        return staffNo;
    }
    public void setStafNo(String str){
        this.staffNo= str;
    }
     // Issuing Authority (passport)
    public String getIssuingAuthority(){
        return issuingAuthority;
    }
    public void setIssuingAuthority(String str){
        this.issuingAuthority= str;
    }
    
     // place of Issue (passport)
    public String getPlaceOfIssue(){
        return placeOfIssue;
    }
    public void setPlaceOfIssue(String str){
        this.placeOfIssue= str;
    }
    /***************************
     * ******* corporate Identification
     */
    private boolean  isCertOfRegistration, isCertOfIncorportion;
    
    
    // is Certificate of Registration?
    public boolean getIsCertOfRegistration(){
        return isCertOfRegistration;
    }
    public void setIsCertOfRegistration(boolean cert){
        this.isCertOfRegistration = cert;
    }
    
    // is Certificate of Incorporation?
    public boolean getIsCertOfIncorporation(){
        return isCertOfIncorportion;
    }
    public void setIsCertOfIncorporation(boolean cert){
        this.isCertOfIncorportion = cert;
    }
    public String getBranchCode(){
        return branchCode;
    }
    public void setBranchCode(String str){
        this.branchCode = str;
    }
    public String getOtherBankname(){
        return otherBankname;
    }
    public void setOtherBankname(String str){
        this.otherBankname =str;
    }
    public String getOtherBankcode(){
        return otherBankCode;
    }
    public String getAuditID(){
        return auditID;
    }
    public void setAuditID(String id){
        this.auditID = id;
    }
    
    public Date getAuditTime(){
        return auditTime;
    }
    public void setAuditTime(Date auditTime){
        this.auditTime =auditTime;
    }
    public Date getCreateDate(){
        return createDate;
    }
    public void setCreateDate(Date createDate){
        this.createDate =createDate;
    }
    public Date getWthdrawaldate(){
        return withdrawaldate;
    }
    public void setWithdrawaldate(Date withdrawaldate){
        this.withdrawaldate = withdrawaldate;
    }
    public Boolean getIsStaff (){
        return isStaff;
    }
    public void setIsStaff(Boolean isStaff){
        this.isStaff = isStaff;
    }
    
    public Boolean getIsCustomer (){
        return isCustomer;
    }
    public void setIsCustomer(Boolean isCustomer){
        this.isCustomer = isCustomer;
    }
    
    public Boolean getIsStudent (){
        return isStudent;
    }
    public void setIsStudent(Boolean isStudent){
        this.isStudent = isStudent;
    }
    
    public Boolean getIsBeneficiary (){
        return isBeneficiary;
    }
    public void setIsBeneficiary(Boolean isBeneficiary){
        this.isBeneficiary = isBeneficiary;
    }
    
    public Boolean getIsSignatory (){
        return isSignatory;
    }
    public void setIsSignatory (Boolean isSignatory){
        this.isSignatory = isSignatory;
    }
    
    
    /************************************************************
     * Next Of Kin Data
     */
    
    
    public String  getNextOfKinNo (){
        return nextOfKinNo;
    }
    public void setNextOfKinNo(String str){
        this.nextOfKinNo = str;
    }
    public String  getAccNo (){
        return accNo;
    }
    public void setAccNo(String str){
        this.accNo = str;
    }
   
    
    public int  getBenefNo (){
        return benefNo;
    }
    public void setBenefNo(int str){
        this.benefNo = str;
    }
        
    
    /**********************************************************
     ********************** personal bio data
     */
    
}
