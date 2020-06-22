/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.banking.registration.members;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.TreeMap;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.servlet.http.HttpSession;
import org.banking.settings.account.AccountProduct;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.LazyDataModel;
import org.tracking.server.database.DatabaseBean;


/**
 *
 * @author dan
 */


@ManagedBean(name="memberRegistrationBean")
@SessionScoped
public class MemberRegistrationBean implements Serializable{

    private static final long serialVersionUID = 1L;
    
   
    private Member newMember;
    private Member selectedMember;
    private AccountProduct newAccountProduct;
    private Member newBeneficiary;
    private static ArrayList<Member> membersList;
    private ArrayList<Member> nextOfKinList;
    private MemberAccount newMemberAccount;
    private ArrayList<MemberAccount> memberAccountList;
    private ArrayList<Member> filteredMember;
    private LazyDataModel<Member> personalMemberDataModel;
    
    public SelectItem[] productType;
    
    
    FacesContext facesContext = FacesContext.getCurrentInstance();
    HttpSession httpSession = (HttpSession)facesContext.getExternalContext().getSession(true);
    FacesMessage msg = null;
    
    @PostConstruct
    public void init(){
        newMember = new Member();
        newBeneficiary = new Member();
        selectedMember = new Member();
        newAccountProduct = new AccountProduct();
        newMemberAccount = new MemberAccount();
        
    }
    
    public AccountProduct getNewAccountProduct(){
        return newAccountProduct;
    }
    public void setNewAccountProduct(AccountProduct accountProduct){
        this.newAccountProduct = accountProduct;
    }
    
    public LazyDataModel<Member> getPersonalMemberDataModel(){
        return personalMemberDataModel;
    }
    // member selected
    public void onRowSelect(SelectEvent event ){
      msg = new FacesMessage("Member Selected", ((Member) event.getObject()).getMemberNo());
      FacesContext.getCurrentInstance().addMessage(null, msg);
      }
    
    public Member getNewMember(){
        return newMember;
    }
    public void setNewMember(Member newMember){
        this.newMember = newMember;
    }
    
    public Member getSelectedMember(){
        return selectedMember;
    }
    public void setSelectedMember(Member selectedMember){
        this.selectedMember = selectedMember;
    }
    
    public Member getNewBeneficiary(){
        return newBeneficiary;
    }
    public void setNewBeneficiary(Member newBeneficiary){
        this.newBeneficiary = newBeneficiary;
    }
    /***
     * Next Of Kin DataList
     * @return   nextOfKinList
     */
    
    public ArrayList<Member>getNextOfKinList(){
        System.out.println("reading data.........");
        nextOfKinList = new ArrayList<Member>();
        String query = "SELECT * FROM vw_nextOfKin ;";// '"+selectedMember.getMemberNo()+"'
        try{
                         DatabaseBean db = new DatabaseBean();
                         Connection con = db.DBconnect();
                         PreparedStatement stmt = con.prepareStatement(query);
                         stmt.setFetchSize(100);
                         ResultSet rs  = db.preparedState(stmt);
                             while(rs.next()){
                                 
                            /**    Beneficiary nextOfK = new Beneficiary(rs.getInt("benefNo"),rs.getString("accNo"),rs.getString("nextOfKinNo"),
                                rs.getString("memberNo"), rs.getString("surname"),rs.getString("otherNames"),rs.getString("officialCellno"),
                                        rs.getString("homeTelNo"),rs.getString("presentAddr"));
                                        
                                nextOfKinList.add(new Beneficiary(nextOfK)); **/
                                nextOfKinList.add(new Member(rs.getInt("benefNo"),rs.getString("accNo"),rs.getString("nextOfKinNo"),
                                rs.getString("memberNo"), rs.getString("surname"),rs.getString("otherNames"),rs.getString("officialCellno"),
                                        rs.getString("homeTelNo"),rs.getString("presentAddr")));
                                 
                             }
                             rs.close();
                         db.cleanup();
                         
        }catch(SQLException e){
                            msg = new FacesMessage(FacesMessage.SEVERITY_FATAL, "Sql Error"," Please contact admin!");
                            System.out.println("Un assigned Student  error in SQLE !"+e);
        }catch(Exception e ){
                            msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Saving Error"," Fill in the missing fields!");
                            System.out.println(" error in EXception !"+e);
                    }
                            
        
        return nextOfKinList;
    }
    
    
    
    public ArrayList<MemberAccount>getMemberAccountList(){
        System.out.println("reading data.........");
        memberAccountList = new ArrayList<MemberAccount>();
        String query = "SELECT * FROM vw_fosaAccount WHERE memberNo = '"+selectedMember.getMemberNo().trim()+"'  ;";// '"+selectedMember.getMemberNo()+"'
        try{
                         DatabaseBean db = new DatabaseBean();
                         Connection con = db.DBconnect();
                         PreparedStatement stmt = con.prepareStatement(query);
                         stmt.setFetchSize(100);
                         ResultSet rs  = db.preparedState(stmt);
                             while(rs.next()){
                                 
                            
                                memberAccountList.add(new MemberAccount(rs.getString("accNo"),rs.getString("productName")));
                                 
                             }
                             rs.close();
                         db.cleanup();
                         
        }catch(SQLException e){
                            msg = new FacesMessage(FacesMessage.SEVERITY_FATAL, "Sql Error"," Please contact admin!");
                            System.out.println("MemberAcount List  error in SQLE !"+e);
        }catch(Exception e ){
                            msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Saving Error"," Fill in the missing fields!");
                            System.out.println(" error in EXception !"+e);
                    }
                            
        
        return memberAccountList;
    }
    
    public MemberAccount getNewMemberAccount(){
        return newMemberAccount;
    }
    public void setNewMemberAccount(MemberAccount newMemberAcc){
        this.newMemberAccount  = newMemberAcc;
    }
    public void generateMemberCode(){
        Random newrandom = new Random();
        int random = newrandom.nextInt(1000)+330;
        System.out.println("new member:"+ random);
        String randomCode = String.valueOf("MB/"+random);
        newMember.setMemberNo(randomCode);
        
    }
  public void updateMember(){
                        dbAction("UPDATE members SET title = '"+ selectedMember.getTitle().trim() +"', staffNo =  '"+ selectedMember.getStaffNo().trim() +"', passport = '"+ selectedMember.getPassportNo().trim() +"',"
                                + "surname = '"+ selectedMember.getSurname().trim() +"', otherNames = '"+ selectedMember.getOtherNames().trim() +"',gender = '"+ selectedMember.getGender().trim() +"', "
                                + "dateOfBirth = '"+ selectedMember.getDateOfBirth() +"', maritalStatus =  '"+ selectedMember.getMaritalStatus().trim() +"',  occupation = '"+ selectedMember.getOccupation().trim() +"',"
                                + "  nationality= '"+ selectedMember.getNationality().trim() +"',officialCellno =  '"+ selectedMember.getOfficeNo().trim() +"', email =  '"+ selectedMember.getEmail().trim()+"',"
                                + "homeTelNo =  '"+ selectedMember.getCellNo().trim()+"', presentAddr = '"+ selectedMember.getPhysicalAddress().trim() +"', careOf =  '"+ selectedMember.getCareOf().trim() +"',"
                                + " street =  '"+ selectedMember.getStreet().trim()+"', homeAddr = '"+ selectedMember.getHomeAddr().trim() +"', currentTown = '"+ selectedMember.getTown().trim() +"', "
                                + "IDNo = '"+ selectedMember.getIdNo() +"',  issuedBy = '"+ selectedMember.getIssuingAuthority().trim() +"', placeOfIssue = '"+ selectedMember.getPlaceOfIssue().trim() +"', "
                                + "dateOfIssue = '"+ selectedMember.getDateOfIssue()+"', exprireDate =  '"+ selectedMember.getExpDate()+"', utility = '"+ selectedMember.getUtilityName().trim() +"',"
                                + " utilityCompany =  '"+ selectedMember.getUtilityCompany().trim() +"', utilityAccNo = '"+ selectedMember.getUtilityAccNo().trim() +"',  PIN =  '"+ selectedMember.getPinNo().trim() +"',"
                                + "otherBankCode = '"+ selectedMember.getOtherBankcode().trim() +"', otherBankname = '"+ selectedMember.getOtherBankname().trim() +"'   WHERE memberNo = '"+ selectedMember.getMemberNo().trim() +"';");
                }
                public void deleteMember(){
                        dbAction("DELETE FROM members WHERE memberNo = "+ selectedMember.getMemberNo().trim()+";");
                }
    
   
    
    public void addNewMember(){
        newMember.setAuditID("1");
        newMember.setBranchCode("1");
        generateMemberCode();
           String mysql = "INSERT INTO members (memberNo,title, staffNo, passport,surname,otherNames ,gender,dateOfBirth,maritalStatus,  occupation,nationality ,officialCellno,"
            + "email,homeTelNo,presentAddr ,careOf,street,homeAddr,currentTown,IDNo,issuedBy,placeOfIssue,dateOfIssue,exprireDate,utility,utilityCompany,utilityAccNo,"
            + "incomeEstimate,branchCode,PIN,photo,otherBankCode,otherBankname,auditID,auditTime,withdrawaldate,status,signature,isStaff,isCustomer,isStudent,isBeneficiary,"
            + "isSignatory,createDate)"
                + " VALUES ('"+ newMember.getMemberNo().trim() +"','"+ newMember.getTitle().trim() +"', '"+ newMember.getStaffNo().trim() +"', '"+ newMember.getPassportNo().trim() +"',"
            + "'"+ newMember.getSurname().trim() +"', '"+ newMember.getOtherNames().trim() +"', '"+ newMember.getGender().trim() +"', '"+ newMember.getDateOfBirth() +"',"
            + " '"+ newMember.getMaritalStatus().trim() +"',  '"+ newMember.getOccupation().trim() +"', '"+ newMember.getNationality().trim() +"',  '"+ newMember.getOfficeNo().trim() +"', "
            + " '"+ newMember.getEmail().trim()+"',  '"+ newMember.getCellNo().trim()+"',  '"+ newMember.getPhysicalAddress().trim() +"',  '"+ newMember.getCareOf().trim() +"',  '"+ newMember.getStreet().trim()+"', "
            + " '"+ newMember.getHomeAddr().trim() +"',  '"+ newMember.getTown().trim() +"', '"+ newMember.getIdNo() +"',  '"+ newMember.getIssuingAuthority().trim() +"', "
            + " '"+ newMember.getPlaceOfIssue().trim() +"',  '"+ newMember.getDateOfIssue()+"',  '"+ newMember.getExpDate()+"',  '"+ newMember.getUtilityName() +"',  '"+ newMember.getUtilityCompany().trim() +"',"
            + "  '"+ newMember.getUtilityAccNo().trim() +"',  '"+ newMember.getIncomeEstimate() +"',  '"+ newMember.getBranchCode().trim()+"',  '"+ newMember.getPinNo().trim() +"','',  "
            + "'"+ newMember.getOtherBankcode().trim() +"',  '"+ newMember.getOtherBankname().trim() +"' , '"+ newMember.getAuditID() +"',now(),  '"+ newMember.getWthdrawaldate() +"',  '"+ newMember.getIsActive()+"','', "
            + " '"+ newMember.getIsStaff() +"',  '"+ newMember.getIsCustomer() +"',  '"+ newMember.getIsStudent() +"',  '"+ newMember.getIsBeneficiary() +"',  '"+ newMember.getIsSignatory() +"',now());"; 
     
        System.out.print(mysql);
        try{
            DatabaseBean db = new DatabaseBean();
            db.insert(mysql);
            db.cleanup();
        }catch(SQLException e){
            System.out.println("SQL Error In Member: "+ e);
        }catch(Exception e){
            System.out.println("Exception Error In Memeber: "+ e);
        } 
        
        
    }
    public void dbAction(String sql){
        System.out.println("sql:"+ sql);
                        try{
                            DatabaseBean db = new DatabaseBean();
                            db.insert(sql);
                            db.cleanup();
                        }catch(SQLException e){
                            System.out.println("SQL Error In Facilities: "+ e);
                        }catch(Exception e){
                            System.out.println("Exception Error In Facilities: "+ e);
                        }        
                }
   /* public ArrayList<Member>getMembersList(){
        if (membersList == null )
            getMembazList();
        return membersList;
    }*/
    
    public ArrayList<Member>getMembersList(){
                String query = "SELECT * FROM members;";
                membersList = new ArrayList<Member>();
                System.out.println("Selecting all Members.....to memberList");
                try{
                        DatabaseBean db = new DatabaseBean();
                        Connection con = db.DBconnect();
                        PreparedStatement stmt = con.prepareStatement(query);
                        ResultSet rs=db.preparedState(stmt);
                        while(rs.next()){
                                    
                            
                            membersList.add(new Member(rs.getString("memberNo"), rs.getString("staffNo"),rs.getString("title"), rs.getString("passport"),rs.getString("surname"),rs.getString("otherNames"),
                                    rs.getString("gender"),rs.getDate("dateOfBirth"),rs.getString("maritalStatus"), rs.getString("occupation"),
                                    rs.getString("nationality") ,rs.getString("officialCellno"),rs.getString("email"),rs.getString("homeTelNo"),
                                    rs.getString("presentAddr"),rs.getString("careOf"),rs.getString("street"),rs.getString("homeAddr"),
                                    rs.getString("currentTown"),rs.getInt("IDNo"),rs.getString("issuedBy"),rs.getString("placeOfIssue"),
                                    rs.getDate("dateOfIssue"),rs.getDate("exprireDate"),rs.getString("utility"),rs.getString("utilityCompany"),
                                    rs.getString("utilityAccNo"), rs.getString("branchCode"),rs.getString("PIN"),
                                    rs.getString("otherBankCode"),rs.getString("otherBankname"),rs.getString("auditID"),rs.getDate("auditTime"),
                                    rs.getDate("withdrawaldate"),rs.getBoolean("status"),rs.getBoolean("isStaff"),rs.getBoolean("isCustomer"), 
                                    rs.getBoolean("isStudent"),rs.getBoolean("isBeneficiary"),rs.getBoolean("isSignatory"),rs.getDate("createDate")));
                        }
                        rs.close();
                        db.cleanup();
                }catch(SQLException e){
                        System.out.println("Error 1: "+ e);
                }catch(Exception e){
                        System.out.println("Error 2: "+ e);
                }

        
        return membersList;
    }
    
    public void getMembazList() {
                String query = "SELECT * FROM members;";
                membersList = new ArrayList<Member>();
                System.out.println("Selecting all Members.....to memberList");
                try{
                        DatabaseBean db = new DatabaseBean();
                        Connection con = db.DBconnect();
                        PreparedStatement stmt = con.prepareStatement(query);
                        ResultSet rs=db.preparedState(stmt);
                        while(rs.next()){
                                    
                            
                            membersList.add(new Member(rs.getString("memberNo"), rs.getString("staffNo"),rs.getString("title"), rs.getString("passport"),rs.getString("surname"),rs.getString("otherNames"),
                                    rs.getString("gender"),rs.getDate("dateOfBirth"),rs.getString("maritalStatus"), rs.getString("occupation"),
                                    rs.getString("nationality") ,rs.getString("officialCellno"),rs.getString("email"),rs.getString("homeTelNo"),
                                    rs.getString("presentAddr"),rs.getString("careOf"),rs.getString("street"),rs.getString("homeAddr"),
                                    rs.getString("currentTown"),rs.getInt("IDNo"),rs.getString("issuedBy"),rs.getString("placeOfIssue"),
                                    rs.getDate("dateOfIssue"),rs.getDate("exprireDate"),rs.getString("utility"),rs.getString("utilityCompany"),
                                    rs.getString("utilityAccNo"), rs.getString("branchCode"),rs.getString("PIN"),
                                    rs.getString("otherBankCode"),rs.getString("otherBankname"),rs.getString("auditID"),rs.getDate("auditTime"),
                                    rs.getDate("withdrawaldate"),rs.getBoolean("status"),rs.getBoolean("isStaff"),rs.getBoolean("isCustomer"), 
                                    rs.getBoolean("isStudent"),rs.getBoolean("isBeneficiary"),rs.getBoolean("isSignatory"),rs.getDate("createDate")));
                        }
                        rs.close();
                        db.cleanup();
                }catch(SQLException e){
                        System.out.println("Error 1: "+ e);
                }catch(Exception e){
                        System.out.println("Error 2: "+ e);
                }

                //return marketerList;
	}
    public ArrayList<Member> getFilteredMember(){return  filteredMember;}
    public void setFilteredMember(ArrayList<Member> filteredMember){
        this.filteredMember = filteredMember;
    }
    public void searchMember(){
        
        
    }
    
    
    /********* Product Types *********
      * **  ComboBox for Product Types e.g
      * * Personal -: individual, joint 
      * * 
      * @return productType
      */
    public void checkProductGroup(){
        System.out.println(" Group selected: "+ newMemberAccount.getGroupCode());
        String prodgp = newMemberAccount.getGroupCode();
        if(!prodgp.equals("") )
            getProductTypes();
        
    }
     public SelectItem[] getProductTypes() {
         
        String  prod ="PC001";
        
        System.out.println("*********account product code1 :"+prod);
                String query = "SELECT  productCode, productName FROM vw_accProducts where acctypeCode = '"+prod.trim()+"' AND groupCode   = '"+ newMemberAccount.getGroupCode().trim()+"'   ;";///where acctypeCode = '"+selectedAccType.getAcctypeCode().trim()+"'
               /* String sql = "SELECT accProductsGroup.groupCode,accProductsGroup.groupName,accProducts.productCode," +
                    "accProducts.productName,accProducts.acctypeCode" +
                    "FROM accProductsGroup" +
                    "INNER JOIN accProducts ON accProducts.groupCode = accProductsGroup.groupCode;";
*/
                                    List list = new ArrayList();
                TreeMap <String, String>tMap = new TreeMap<String, String>();
                try{
                        DatabaseBean db = new DatabaseBean();
                        Connection con = db.DBconnect();
                        PreparedStatement stmt = con.prepareStatement(query);
                        ResultSet rs=db.preparedState(stmt);
                        int z = 0;
                        while(rs.next()){
                                list.add(z++);
                                tMap.put(rs.getString("productCode").toString(), rs.getString("productName").toString());
                        }
                        rs.close();
                        db.cleanup();
                }catch(SQLException e){
                        System.out.println("product Types  - Combobox SQLException: "+ e);
                }catch(Exception e){
                        System.out.println("product Types - Combobox  Exception: "+ e);
                        System.err.println(e);
                }
                if (list.isEmpty()) {
                        tMap.put("--".toString(), "<<No product Types >>".toString());
                        list.add("---".toString());
                }
                productType = new SelectItem[list.size()];
                int i = 0;

                Set set = tMap.entrySet();
                Iterator ite = set.iterator();
                while(ite.hasNext()) { 
                        Map.Entry me = (Map.Entry)ite.next(); 
                        productType[i++] = new SelectItem(me.getKey().toString(), me.getValue().toString());
                } 
                return productType;
        }
     
        public void createAccNumberz(){
                     System.out.println("Datatest Ok! -----------");
                     Random r1 = new Random();
                   Random r2 = new Random();
                   Random r3 = new Random();
                    int num1 = r1.nextInt((999) ) + 999;
                    int num2 = r2.nextInt(( 999) ) + 999;
                    int num3 = r3.nextInt(( 100) ) + 100; 
                    String accNumber =String.valueOf(num1 + num2 +num3);
                    System.out.println("accNumber :"+ accNumber);
                    String sql = "SELECT  accNo FROM fosaAccount where accNo = '"+accNumber.trim()+"' ;";
                    dbCheckAcc(sql);
        
        }
        public void testThis(){
                    System.out.println("Datatest Ok! -----------");
                    Random r1 = new Random();
                   Random r2 = new Random();
                   Random r3 = new Random();
                    int num1 = r1.nextInt((999) ) + 999;
                    int num2 = r2.nextInt(( 999) ) + 999;
                    int num3 = r3.nextInt(( 100) ) + 100;
                    System.out.println("my Numbers" +num1+ num2+num3);
                     String numberz  = String.valueOf(+num1+""+ num2+""+num3);
                    
                    newMemberAccount.setAccNo(numberz);
                    newMemberAccount.setBranchCode(selectedMember.getBranchCode());
                    newMemberAccount.setMemberNo( selectedMember.getMemberNo());
                    String sql = "INSERT INTO fosaAccount (accNo, memberNo, branchCode,groupCode,productCode,currencyCode,incomeCode ,totalBalance,"
                    + "availableAmount,isActive,auditTime) VALUES ('"+ newMemberAccount.getAccNo().trim() +"', '"+ newMemberAccount.getMemberNo().trim() +"',"
                    + " '"+ newMemberAccount.getBranchCode().trim() +"','"+ newMemberAccount.getGroupCode().trim() +"','"+ newMemberAccount.getProductCode().trim() +"',"
                    + "'"+ newMemberAccount.getCurrencyCode().trim() +"','"+ newMemberAccount.getIncomeCode().trim() +"','"+ newMemberAccount.getTotalBalance() +"',"
                    + " '"+ newMemberAccount.getAvailableAmount() +"','"+ newMemberAccount.getIsActive() +"', now());";
                    System.out.println(sql);
                        try{
                            DatabaseBean db = new DatabaseBean();
                            db.insert(sql);
                            db.cleanup();
                        }catch(SQLException e){
                            System.out.println("SQL Error In FOSA Account: "+ e);
                        }catch(Exception e){
                            System.out.println("Exception Error In FOSA Account: "+ e);
                        }  
        }
        public String dbCheckAcc(String sql){
            System.out.println("sql:"+ sql);
            String accStringNo = "";
            
                        try{
                             DatabaseBean db = new DatabaseBean();
                             Connection con = db.DBconnect();
                             PreparedStatement stmt = con.prepareStatement(sql);
                             ResultSet rs=db.preparedState(stmt);
                             int x = -1;
                             while(rs.next()){
                               accStringNo = rs.getString("accNo");
                             }
                             x++;
                             if(x < 0 ){
                             newMemberAccount.setAccNo(accStringNo);
                             accStringNo = newMemberAccount.getAccNo();
                             }else{
                               accStringNo = "none";  
                             }
                        }catch(SQLException e){
                            System.out.println("SQL Error In AccNumber Check: "+ e);
                        }catch(Exception e){
                            System.out.println("Exception Error In AccNumber Check: "+ e);
                        }
                        return  accStringNo  ;
        }
        public void finalAccNumberCheck(String str1 , String str2){
            if(newMemberAccount.getAccNo() == null){
                createAccNumberz();
            }else{
                System.out.println("accountNumber is :" + newMemberAccount.getAccNo() );
                newMemberAccount.setBranchCode(str1);
                newMemberAccount.setMemberNo(str2);
            }
        }
        public void   getAddMemberAcc(){
            System.out.println("Adding ");
            finalAccNumberCheck(selectedMember.getBranchCode(), selectedMember.getMemberNo());
             
            String sql = "INSERT INTO fosaAccount (accNo, memberNo, branchCode,groupCode,productCode,currencyCode,incomeCode ,totalBalance,"
                    + "availableAmount,isActive,auditTime) VALUES ('"+ newMemberAccount.getAccNo().trim() +"', '"+ newMemberAccount.getMemberNo().trim() +"',"
                    + " '"+ newMemberAccount.getBranchCode().trim() +"','"+ newMemberAccount.getGroupCode().trim() +"','"+ newMemberAccount.getProductCode().trim() +"',"
                    + "'"+ newMemberAccount.getCurrencyCode().trim() +"','"+ newMemberAccount.getIncomeCode().trim() +"','"+ newMemberAccount.getTotalBalance() +"',"
                    + " '"+ newMemberAccount.getAvailableAmount() +"','"+ newMemberAccount.getIsActive() +"', now();";         
            System.out.println(sql);
                        try{
                            DatabaseBean db = new DatabaseBean();
                            db.insert(sql);
                            db.cleanup();
                        }catch(SQLException e){
                            System.out.println("SQL Error In FOSA Account: "+ e);
                        }catch(Exception e){
                            System.out.println("Exception Error In FOSA Account: "+ e);
                        }  
                        
        }
            
        
        
}
