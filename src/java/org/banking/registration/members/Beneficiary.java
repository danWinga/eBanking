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
public class Beneficiary  implements Serializable{
    
    private static final long serialVersionUID = 5873756507904435786L;
    
     private String firstNamekin,  lastNamekin,otherNameskin, genderkin,memberNokin,
            titlekin,phoneNokin,cellNokin, officeNokin,faxkin,postalAddresskin,homeAddrkin,townkin,careOfkin,
            emailkin,websitekin,physicalAddresskin,streetkin,buildingkin,blockNokin,occupationkin,
            utilityNamekin,utilityCompanykin, utilityAccNokin,fullNamekin, countrykin, maritalStatuskin,
            passportNokin,staffNokin,nationalitykin,branchCodekin,auditIDkin,surnamekin;
    private Date dateOfIssuekin,auditTimekin,withdrawaldatekin, createDatekin;   
    private String pinNokin, issuingAuthoritykin, placeOfIssuekin,otherBankCodekin,otherBanknamekin;
    private int  idNokin,shareskin,incomeEstimatekin;
    private Date dateOfBirthkin,expDatekin;
    private boolean isStaffkin,isCustomerkin,isStudentkin;
    
    
    public Beneficiary(){}
    public Beneficiary(String memberNo,int idNo, String fullname ){
        this.memberNokin = memberNo;
        this.idNokin = idNo;
        this.fullNamekin = fullname;
    }

    public Beneficiary(int benfi, String accNo, String nextOfKinNo, String memberNo, String surname,
            String othernames,String officialCellno,String homeTelNo ,String presentAddr) {
        
        this.benefNo = benfi; 
        this.nextOfKinNo = nextOfKinNo;
        this.accNo = accNo;
        this.memberNokin = memberNo;
        this.surnamekin = surname;
        this.otherNameskin = othernames;
        this.officeNokin = officialCellno;
        this.cellNokin = homeTelNo;
        this.homeAddrkin = presentAddr;
        
    }
    public Beneficiary (Beneficiary thisMember){
        this.benefNo = thisMember.getBenefNo(); 
        this.nextOfKinNo = thisMember.getNextOfKinNo();
        this.accNo = thisMember.getAccNo();
        this.memberNokin = thisMember.getMemberNokin();
        this.surnamekin = thisMember.getSurnamekin();
        this.otherNameskin = thisMember.getOtherNameskin();
        this.officeNokin = thisMember.getOfficeNokin();
        this.cellNokin = thisMember.getHomeAddrkin();
        this.homeAddrkin = thisMember.getHomeAddrkin();
        
    }
    
    /*******************************************************
     * ********** Personal record   ***********************
     * @return 
     */
    public String getFisrtNamekin(){
        return firstNamekin;
    }
    public void setFirstName(String str){
        this.firstNamekin = str;
    }
    
    public String getSurnamekin(){
        return surnamekin;
    }
    public void setSurnamekin(String str){
        this.surnamekin = str;
    }
    
    public String getLastNamekin(){
        return lastNamekin;
    }
    public void setLastNamekin(String str){
        this.lastNamekin = str;
    }
    
    public String getOtherNameskin(){
        return otherNameskin;
    }
    public void setOtherNameskin(String str){
        this.otherNameskin = str;
    }
    
    public String getGenderkin(){
        return genderkin;
    }
    public void setGenderkin(String str){
        this.genderkin = str;
    }
    
    public String getMemberNokin(){
        return memberNokin;
    }
    public void setMemberNokin(String str){
        this.memberNokin = str;
    }
    
    public String getTitlekin(){
        return titlekin;
    }
    public void setTitlekin(String str){
        this.titlekin = str;
    }
    
    public String getPhoNokin(){
        return phoneNokin;
    }
    public void setPhoneNokin(String str){
        this.phoneNokin = str;
    }
    
    public String getCellNokin(){
        return cellNokin;
    }
    public void setCellNokin(String str){
        this.cellNokin = str;
    }
    
    public String getFaxkin(){
        return faxkin;
    }
    public void setFaxkin(String str){
        this.faxkin = str;
    }
    public String getPostalAddresskin(){
        return postalAddresskin;
    }
    public void setPostalAddresskin(String str){
        this.postalAddresskin= str;
    }
    public int getIncomeEstimatekin(){
        return incomeEstimatekin;
    }
    public void setIncomeEstimatekin(int str){
        this.incomeEstimatekin = str;
    }
    public String getTownkin(){
        return townkin;
    }
    public void setTown(String str){
        this.townkin= str;
    }
    public String getNationalitykin(){
        return nationalitykin;
    }
    public void setNationalitykin(String str){
        this.nationalitykin= str;
    }
    public String getCareOfkin(){
        return careOfkin;
    }
    public String getEmailkin(){
        return emailkin;
    }
    public void setEmailkin(String str){
        this.emailkin= str;
    }
    public String getWebsitekin(){
        return websitekin;
    }
    public void setWebsitekin(String str){
        this.websitekin= str;
    }
    public void setCareOfkin(String str){
        this.careOfkin= str;
    }
    
    public String getHomeAddrkin(){
        return homeAddrkin;
    }
    public void setHomeAddrkin(String str){
        this.homeAddrkin= str;
    }
    
    public String getPhysicalAddresskin(){
        return physicalAddresskin;
    }
    public void setPhysicalAddresskin(String str){
        this.physicalAddresskin= str;
    }
    public String getOccupationkin(){
        return occupationkin;
    }
    public void setOccupationkin(String str){
        this.occupationkin = str;
    }
    
    public String getStreetkin(){
        return streetkin;
    }
    public void setStreetkin(String str){
        this.streetkin= str;
    }
    public String getBuildingkin(){
        return buildingkin;
    }
    public void setBuildingkin(String str){
        this.buildingkin= str;
    }
    
    public String getBlockNokin(){
        return blockNokin;
    }
    public void setBlockNo(String str){
        this.blockNokin= str;
    }
    
    public Date getDateOfBirthkin(){
        return dateOfBirthkin;
    }
    public void setDateOfBirthkin(Date dob){
        this.dateOfBirthkin = dob;
    }
    
    
    public Integer getIdNokin(){
        return idNokin;
    }
    public void setIdNokin(int str){
        this.idNokin = str;
    }
    // utility company
    public String getUtilityCompanykin(){
        return utilityCompanykin;
    }
    public void setUtilityCompanykin(String str){
        this.utilityCompanykin= str;
    }
    // utility Name
    public String getUtilityNamekin(){
        return utilityNamekin;
    }
    public void setUtilityNamekin(String str){
        this.utilityNamekin= str;
    }
    // utitity Account Number
    public String getUtilityAccNokin(){
        return utilityAccNokin;
    }
    public void setUtilityAccNokin(String str){
        this.utilityAccNokin= str;
    }
    public String getFullNamekin(){
       fullNamekin =  getSurnamekin() + " "+ getOtherNameskin();
        return fullNamekin;
    }
    public void setFullNamekin(String str){
        this.fullNamekin = str;
    }
    
    public String getCountrykin(){
        return countrykin;
    }
    public void setCountrykin(String str){
        this.countrykin= str;
    }
    
    public String getMaritalSatuskin(){
        return maritalStatuskin;
    }
    // maritalStatus
    public void setMaritalStatuskin(String str){
        this.maritalStatuskin= str;
    }
    // share Holding in percentage
    public int getShareskin(){
        return shareskin;
    }
    public void setShareskin(int str){
        this.shareskin= str;
    }
    // office Number
    public String getOfficeNokin(){
        return officeNokin;
    }
    public void setOfficeNokin(String str){
        this.officeNokin= str;
    }
    
    
    
    // passport Number
    public String getPassportNokin(){
        return passportNokin;
    }
    public void setPassportNokin(String str){
        this.passportNokin= str;
    }
    // Date of issue
    public Date getDateOfIssuekin(){
        return dateOfIssuekin;
    }
    public void setDateOfIssuekin(Date str){
        this.dateOfIssuekin= str;
    }
    // expire Date
    public Date getExpDatekin(){
        return expDatekin;
    }
    public void setExpDatekin(Date str){
        this.expDatekin= str;
    }
     // PIN Number
    public String getPinNokin(){
        return pinNokin;
    }
    public void setPinNokin(String str){
        this.pinNokin= str;
    }
    
    public String getStaffNokin(){
        return staffNokin;
    }
    public void setStafNokin(String str){
        this.staffNokin= str;
    }
     // Issuing Authority (passport)
    public String getIssuingAuthoritykin(){
        return issuingAuthoritykin;
    }
    public void setIssuingAuthoritykin(String str){
        this.issuingAuthoritykin= str;
    }
    
     // place of Issue (passport)
    public String getPlaceOfIssuekin(){
        return placeOfIssuekin;
    }
    public void setPlaceOfIssuekin(String str){
        this.placeOfIssuekin= str;
    }
    /***************************
     * ******* corporate Identification
     */
    private boolean  isCertOfRegistrationkin, isCertOfIncorportionkin;
    
    
    // is Certificate of Registration?
    public boolean getIsCertOfRegistrationkin(){
        return isCertOfRegistrationkin;
    }
    public void setIsCertOfRegistration(boolean certkin){
        this.isCertOfRegistrationkin = certkin;
    }
    
    // is Certificate of Incorporation?
    public boolean getIsCertOfIncorporationkin(){
        return isCertOfIncorportionkin;
    }
    public void setIsCertOfIncorporationkin(boolean cert){
        this.isCertOfIncorportionkin = cert;
    }
    public String getBranchCodekin(){
        return branchCodekin;
    }
    public void setBranchCodekin(String str){
        this.branchCodekin = str;
    }
    public String getOtherBanknamekin(){
        return otherBanknamekin;
    }
    public void setOtherBanknamekin(String str){
        this.otherBanknamekin =str;
    }
    public String getOtherBankcodekin(){
        return otherBankCodekin;
    }
    public String getAuditIDkin(){
        return auditIDkin;
    }
    public void setAuditIDkin(String id){
        this.auditIDkin = id;
    }
    
    public Date getAuditTimekin(){
        return auditTimekin;
    }
    public void setAuditTimekin(Date auditTime){
        this.auditTimekin =auditTime;
    }
    public Date getCreateDatekin(){
        return createDatekin;
    }
    public void setCreateDatekin(Date createDate){
        this.createDatekin =createDate;
    }
    public Date getWthdrawaldatekin(){
        return withdrawaldatekin;
    }
    public void setWithdrawaldatekin(Date withdrawaldate){
        this.withdrawaldatekin = withdrawaldate;
    }
    public Boolean getIsStaffkin (){
        return isStaffkin;
    }
    public void setIsStaffkin(Boolean isStaff){
        this.isStaffkin = isStaff;
    }
    
    public Boolean getIsCustomerkin (){
        return isCustomerkin;
    }
    public void setIsCustomerkin(Boolean isCustomer){
        this.isCustomerkin = isCustomer;
    }
    
    public Boolean getIsStudentkin (){
        return isStudentkin;
    }
    public void setIsStudentkin(Boolean isStudent){
        this.isStudentkin = isStudent;
    }
    
    
    /************************************************************
     * Next Of Kin Data
     */
    private String nextOfKinNo ,accNo;
    private int benefNo;
    
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

    
    

