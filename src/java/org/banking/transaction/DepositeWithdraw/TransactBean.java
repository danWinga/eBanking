/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.banking.transaction.DepositeWithdraw;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import org.banking.transaction.AccCharges;
import org.banking.transaction.ledger.Ledger;
import org.tracking.server.database.DatabaseBean;

/**
 *
 * @author dan
 */
@ManagedBean(name="transactBean")
@SessionScoped
public class TransactBean implements Serializable {
    
            private static final long serialVersionUID = 1L;
            private String accNumberSearch;
            private Transact clientRecordFound;
            private Transact newTransaction;
            private LastTransaction lastTransaction;
            //private LastTransaction lastTransaction;
            private Ledger newLedger;
            private AccCharges accountCharges;
            private AccCharges selectedAccCherges;
            private ArrayList<Transact>clientRecord;
            private ArrayList<LastTransaction> currentTransList;
            private ArrayList<AccCharges>accChargesList;
        
        
            FacesContext facesContext = FacesContext.getCurrentInstance();
            HttpSession httpSession = (HttpSession)facesContext.getExternalContext().getSession(true);
            FacesMessage msg = null;
            
            @PostConstruct
            public void init(){
                    clientRecordFound = new Transact();
                    newTransaction = new Transact();
                    lastTransaction = new LastTransaction();
                    newLedger = new Ledger();
            }
            public String getAccNumberSearch(){return accNumberSearch;}
            public void setAccNumberSearch(String accNumberSearch){this.accNumberSearch = accNumberSearch;}
            
            public Transact  getClientRecordFound(){return clientRecordFound;}
            public void setClientRecordFound(Transact clientRecordFound ){this.clientRecordFound = clientRecordFound;}
            
            public Transact  getNewTransaction(){return newTransaction;}
            public void setNewTransaction(Transact newTransaction ){this.newTransaction = newTransaction;}
            
            public LastTransaction getLastTransaction(){return lastTransaction;}
            public void setLastTransaction(LastTransaction lastTransaction ){this.lastTransaction = lastTransaction;}
            
            public AccCharges  getSelectedAccCherges(){return selectedAccCherges;}
            public void setSelectedAccCherges(AccCharges selectedAccCherges ){this.selectedAccCherges = selectedAccCherges;}
            
            public Ledger getNewLedger(){return newLedger;}
            public void setNewLedger(Ledger newLedger){this.newLedger = newLedger;}
            
            public AccCharges getAccountCharges(){return accountCharges;}
            public void setAccountCharges(AccCharges accountCharges){this.accountCharges = accountCharges;}
            
            
            
            public ArrayList<Transact> getClientRecord() {
                String query = "SELECT members.memberNo,members.title,concat(members.surname,'  ' ,members.otherNames) AS fullName,members.IDNo,"
                        + "members.homeAddr,concat(members.officialCellno,' /',members.officialCellno)  AS contacts,members.email,  fosaAccount.accNo,"
                        + "branches.branchname ,fosaAccount.groupCode,accProductsGroup.groupName, fosaAccount.productCode,accProducts.productName, "
                        + "fosaAccount.currencyCode,fosaAccount.totalBalance,fosaAccount.availableAmount,fosaAccount.isActive,fosaAccount.auditTime "
                        + "  FROM members  JOIN fosaAccount ON (members.memberNo = fosaAccount.memberNo )  JOIN accProductsGroup ON "
                        + "(fosaAccount.groupCode = accProductsGroup.groupCode )JOIN accProducts ON (fosaAccount.productCode = accProducts.productCode ) "
                        + "JOIN branches ON (fosaAccount.branchCode = branches.branchcode ) "
                        + "  WHERE  fosaAccount.accNo  = fosaAccount.accNo  order by fosaAccount.auditTime desc ;";
                clientRecord = new ArrayList<Transact>();
                

                try{
                    DatabaseBean db = new DatabaseBean();
                    Connection con = db.DBconnect();
                    PreparedStatement stmt = con.prepareStatement(query);
                    ResultSet rs=db.preparedState(stmt);
                    while(rs.next()){
                            clientRecord.add(new Transact( rs.getString("memberNo") , rs.getString("title"),rs.getString("fullName"),
                                    rs.getInt("IDNo"),rs.getString("homeAddr"),rs.getString("contacts"),rs.getString("email"),rs.getString("accNo"),
                                    rs.getString("branchname"), rs.getString("groupCode"), rs.getString("groupName"),rs.getString("productCode"),
                                    rs.getString("productName"), rs.getString("currencyCode"), rs.getInt("totalBalance"), rs.getInt("availableAmount"),
                                    rs.getBoolean("isActive"),rs.getDate("auditTime") ));
                    }
                    rs.close();
                    db.cleanup();
                }catch(SQLException e){
                    System.out.println("Error 1: "+ e);
                }catch(Exception e){
                    System.out.println("Error 2: "+ e);
                }
                return clientRecord;
	}
        
        public ArrayList<AccCharges> getAccChargesList() {
                String query = "SELECT accSettingsGroup.accgroupCode,accSettingsGroup.productCode,accProducts.productName,accSettingsGroup.accSettingCode,"
                    + "accSettings.settingName,accSettingsGroup.amount   FROM accSettingsGroup  JOIN accProducts ON "
                    + "(accSettingsGroup.productCode = accProducts.productCode )  JOIN accSettings ON (accSettingsGroup.accSettingCode = accSettings.accSettingCode )";
                if(clientRecordFound.getGroupCode() !=null){
                        query += " WHERE  accSettingsGroup.accgroupCode  = accSettingsGroup.accgroupCode ";
                }
                accChargesList = new ArrayList<AccCharges>();
                
                try{
                    DatabaseBean db = new DatabaseBean();
                    Connection con = db.DBconnect();
                    PreparedStatement stmt = con.prepareStatement(query);
                    ResultSet rs=db.preparedState(stmt);
                    while(rs.next()){
                            accChargesList.add(new AccCharges( rs.getString("accgroupCode") , rs.getString("productCode"),rs.getString("productName"),
                                    rs.getString("accSettingCode"), rs.getString("settingName"), rs.getInt("amount")));
                    }
                    rs.close();
                    db.cleanup();
                }catch(SQLException e){
                    System.out.println("Error 1: "+ e);
                }catch(Exception e){
                    System.out.println("Error 2: "+ e);
                }
                return accChargesList;
	}
        /**
         * Product Account Charges per transaction
         * @param prodCode
         * @return 
         */
        
    /**
     * Product Account Charges per transaction
     * @param accgroupcode
     * @param accSettingCode
     * @return
     */
        public Object chargerPerProduct( String accgroupcode, String accSettingCode){
                if( accgroupcode.trim() !=null  && accSettingCode.trim() !=null  ){
                        for(AccCharges accCharge : getAccChargesList()){
                                if((accCharge.getProductCode().equalsIgnoreCase(accgroupcode)&&  accCharge.getAccgroupCode().equalsIgnoreCase(accSettingCode) )){
                                        selectedAccCherges =   accCharge;
                                        System.out.println("general Charges :" + selectedAccCherges.getAccgroupCode());
                                        return selectedAccCherges;
                                }
                        }
                }
                return null;
            
        }
        public Object mincharger( String accgroupcode, String accSettingCode){
                if( accgroupcode.trim() !=null  && accSettingCode.trim() !=null  ){
                        for(AccCharges accCharge : getAccChargesList()){
                                if((accCharge.getProductCode().equalsIgnoreCase(accgroupcode)&&  accCharge.getAccgroupCode().equalsIgnoreCase(accSettingCode) )){
                                        accountCharges =   accCharge;
                                        System.out.println("general Charges :" + accountCharges.getAccgroupCode());
                                        return accountCharges;
                                }
                        }
                }
                return null;
            
        }
        public int  accMinimumBalance(){
                String accSettingcode = "008";
                mincharger(clientRecordFound.getProductCode(),accSettingcode); 
                        clientRecordFound.setMinimumBalance(accountCharges.getAmount()) ;
                return clientRecordFound.getMinimumBalance();   
            
        }
        public void chooseCharge1(){
            System.out.println("transaction test:" + 4000);
            if(newTransaction.getTransactionType() !=null && newTransaction.getTransactionType().equalsIgnoreCase("Deposite")){
                    System.out.println("Deposite:" + 2000);
            }
            if(newTransaction.getTransactionType() !=null && newTransaction.getTransactionType().equalsIgnoreCase("Withdrawal")){
                    System.out.println("Withdrawal:" + 4000);
            }
        }
        public void doLeger(){
            if(newTransaction.getPaymentType() > 0 ){
                    newLedger.setDebited(false);
                    newLedger.setCredited(false);
                    if(newTransaction.getIsDeposite()== true && newTransaction.getPaymentType() ==1 ){
                            newLedger.setDescription("Cash Deposite");
                            newLedger.setDebited(true);
                                                        
                            doLedgerBranchAcc(newLedger.getDescription(),newLedger.getDebited(), newLedger.getCredited());
                            doUpdateTellerFloatDeposite();
                            if(selectedAccCherges.getAmount()> 0 ){
                                    doLedgerInterestAcc(selectedAccCherges.getSettingName(),newLedger.getDebited(), newLedger.getCredited());
                            }
                        
                    }
                    else if(newTransaction.getIsDeposite()== true && newTransaction.getPaymentType() == 2 ){
                            newLedger.setDescription("Cheque Deposite");
                            newLedger.setDebited(true);
                                                        
                            doLedgerBranchAcc(newLedger.getDescription(),newLedger.getDebited(), newLedger.getCredited());
                            if(selectedAccCherges.getAmount()> 0 ){
                                    doLedgerInterestAcc(selectedAccCherges.getSettingName(),newLedger.getDebited(), newLedger.getCredited());
                            }
                    }
                    else if(newTransaction.getIsWithdrawal()== true && newTransaction.getPaymentType() == 1 ){
                            newLedger.setDescription("Cash Withdrawal");
                            newLedger.setCredited(true);
                                                        
                            doLedgerBranchAcc(newLedger.getDescription(),newLedger.getDebited(), newLedger.getCredited());
                            doUpdateTellerFloatWithd();
                            if(selectedAccCherges.getAmount()> 0 ){
                                    doLedgerInterestAcc(selectedAccCherges.getSettingName(),newLedger.getDebited(), newLedger.getCredited());
                            }
                    }
                    else if(newTransaction.getIsWithdrawal()== true && newTransaction.getPaymentType() == 2 ){
                            newLedger.setDescription("Cheque Withdrawal");
                            newLedger.setCredited(true);
                                                        
                            doLedgerBranchAcc(newLedger.getDescription(),newLedger.getDebited(), newLedger.getCredited());
                            if(selectedAccCherges.getAmount()> 0 ){
                                    doLedgerInterestAcc(selectedAccCherges.getSettingName(),newLedger.getDebited(), newLedger.getCredited());
                            }
                    }
            }
        }
        public void chooseCharge(){
            
            System.out.println("chooseCharge:");
            String  accGroupcode ;
            
            String accProdCode = clientRecordFound.getProductCode();
                if(newTransaction.getTransactionType().equalsIgnoreCase("Deposite") && newTransaction.getPaymentType() ==1 ){
                        
                        System.out.println("Cash Deposite:"+ "procode :"+ accProdCode);
                        accGroupcode="006";
                        
                        if(accProdCode  !=null ){
                            System.out.println("Cash Deposite ok:");
                            chargerPerProduct(accProdCode , accGroupcode);
                                selectedAccCherges.getAmount();
                        }
                }
                else if(newTransaction.getTransactionType().equalsIgnoreCase("Deposite") && newTransaction.getPaymentType() ==2 ){
                                accGroupcode ="007";
                                if(accProdCode !=null ){
                                        chargerPerProduct(accProdCode , accGroupcode);
                                            selectedAccCherges.getAmount();
                                }
                } 
                    
                
                else if(newTransaction.getTransactionType().equalsIgnoreCase("Withdrawal") && newTransaction.getPaymentType() ==1){
                        
                                accGroupcode ="006";
                                if(accProdCode!=null ){
                                        chargerPerProduct(accProdCode , accGroupcode);
                                        selectedAccCherges.getAmount();
                                }  
                    
                }
                else if(newTransaction.getTransactionType().equalsIgnoreCase("Withdrawal") && newTransaction.getPaymentType() ==2){
                        
                                accGroupcode ="009";
                                if(accProdCode!=null ){
                                        chargerPerProduct(accProdCode , accGroupcode);
                                        selectedAccCherges.getAmount();
                                }  
                    
                }
        }
        
        
        public void searchClient(){
            
            System.out.println("SearchNumber 1: "+ newTransaction.getAccNumber());
           
                if(!newTransaction.getAccNumber().equals("")&&  newTransaction.getAccNumber() !=null  ){
                        getClientData(newTransaction.getAccNumber());
                        System.out.println("SearchNumber 11: "+ newTransaction.getAccNumber() );
                        accMinimumBalance();
                }
                else if(newTransaction.getAccNumber() == null){
                        msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Sorry !!"," No Record The AccountNumber!");
                        System.out.println("Sorry !! No client Will that Account Number!!");
                }else{
                        System.out.println("Address : "+ clientRecordFound.getAddress() );
                        System.out.println("Contact : "+ clientRecordFound.getContact() );
                        getLastTrans();
                }
            
        } 
        public Object getClientData(String accNo){
                for(Transact clientRec :getClientRecord() ){
                    if( clientRec.getAccNumber().equals(accNo))
                        clientRecordFound = clientRec;
                                System.out.println("Branch : "+ clientRecordFound.getBranchName() );
                                System.out.println("Contact : "+ clientRecordFound.getContact() );
                                System.out.println("prodcode: "+ clientRecordFound.getProductCode());
                                System.out.println("product-group: "+ clientRecordFound.getGroupName());
                        return clientRecordFound ;
                }
            return null;
        }
        public ArrayList<LastTransaction> getCurrentTransList(){
                 String query = "SELECT *  FROM transaction";
                    //String query = "SELECT *  FROM branches where countycode = '"+code.trim()+"' ;";// where countycode = '"+selectedCounty.countyCode.trim()+"' 
                       if(clientRecordFound.getAccNumber() !=null){
                                query += " WHERE accNo = '"+ clientRecordFound.getAccNumber().trim()  +"' order by created_Date desc LIMIT 1";
                                query += ";";
                            
                       }else{
                                //msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Searching Error"," Fill in the search fields!");
                                            
                                System.out.println("Sorry ! No Account found !");
                       }
                         
                                currentTransList = new ArrayList<LastTransaction>();
                                            
                     try{
                                    DatabaseBean db = new DatabaseBean();
                                    Connection con = db.DBconnect();
                                    PreparedStatement stmt = con.prepareStatement(query);
                                    stmt.setFetchSize(100);
                                    ResultSet rs  = db.preparedState(stmt);
                                    while(rs.next()){
                                      currentTransList.add(new LastTransaction(rs.getString("transCode"),rs.getDate("created_Date"),rs.getString("transType"), 
                                               rs.getInt("floatAmount")));
                                    }
                                    rs.close();
                                    db.cleanup();
                                }catch(SQLException e){
                                            //msg = new FacesMessage(FacesMessage.SEVERITY_FATAL, "Sql Error"," Please contact admin!");
                                        System.out.println("lastTransaction   error in SQLE !"+e);
                                }catch(Exception e ){
                                            //msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Reading  Error"," Fill in the missing fields!");
                                            System.out.println(" lastTransaction  error in EXception !"+e);
                                }
                    
                    
                  return currentTransList;
                
        }
        
       
        public Object getLastTrans(){
                if(currentTransList != null){
                        for(LastTransaction  clientRec : getCurrentTransList() ){
                                if(!clientRec.getTransCode().equals("") && clientRec.getAccNo().equals(clientRecordFound.getAccNumber()) ){
                                        lastTransaction = clientRec;

                                    return lastTransaction ;
                                }
                        }
                }
                System.out.println("Sorry ! No Last transaction found");
                return null;
        }
        public void lastTrans(){
                getLastTrans();
                if( lastTransaction.getTransType().equals("")){
                    
                }
        }
       
        public int  checkIfteller(){
                
                String sessionRoleID = httpSession.getAttribute("userId").toString();
                if(sessionRoleID !=null ){
                    System.out.println(":session Not Null :" +sessionRoleID);
                        String query = "SELECT teller_id FROM assign_teller_user ";
                        query += " WHERE  userId  = '"+ sessionRoleID.trim() +"'";
                        try{
                                DatabaseBean db = new DatabaseBean();
                                Connection con = db.DBconnect();
                                PreparedStatement stmt = con.prepareStatement(query);
                                ResultSet rs=db.preparedState(stmt);
                                while(rs.next()){
                                        newTransaction.setTellerId(rs.getInt("teller_id"));
                                        //System.out.println(":tellerid foundxxx :" +newTransaction.getTellerId());
                                }
                                rs.close();
                                db.cleanup();
                        }catch(SQLException e){
                            System.out.println("Error 1: "+ e);
                        }catch(Exception e){
                            System.out.println("Error 2: "+ e);
                        } 
                        
                        System.out.println(":tellerid found1 :" +newTransaction.getTellerId());
                }       
                
                System.out.println(":tellerid found2 :" +newTransaction.getTellerId());
                        
           return  newTransaction.getTellerId();
           
        }
        public void checkTransactionType(){
                newTransaction.setIsDeposite(false);
                newTransaction.setIsWithdrawal(false);
                System.out.println("mode!, Transaction Type:");
                        if(newTransaction.getTransactionType().equalsIgnoreCase("Deposite")){
                               newTransaction.setIsDeposite(true); 
                               chooseCharge();
                               
                        }else if(newTransaction.getTransactionType().equalsIgnoreCase("Withdrawal")){
                               newTransaction.setIsWithdrawal(true);
                               chooseCharge();
                        }else{
                                System.out.println("Sorry !, No selection Made:");
                        }
                
        }
        public String newGenTransCode(){
                String transCode = "";
                int result =0;
                    String query = "SELECT transCode FROM transaction ";
                            query += "order by created_Date desc limit 1 ";
                try{
                        DatabaseBean db = new DatabaseBean();
                        Connection con = db.DBconnect();
                        PreparedStatement stmt = con.prepareStatement(query);
                        ResultSet rs=db.preparedState(stmt);
                        while(rs.next()){
                                transCode =rs.getString("transCode");

                        }
                        rs.close();
                        db.cleanup();
                }catch(SQLException e){
                    System.out.println("Error 1: "+ e);
                }catch(Exception e){
                    System.out.println("Error 2: "+ e);
                }
                
                if(! transCode.equals("") ){
                        result = Integer.parseInt(transCode);
                        int newCode  =   result+1;
                        
                        
                        if(result <10){
                          
                                newTransaction.setTransCode("000"+newCode);
                        }
                        else if(result >9 && result < 100){
                                 newTransaction.setTransCode("00"+newCode);
                        }
                        else if(result >99 && result < 1000){
                                 newTransaction.setTransCode("0"+newCode);
                        }
                        else if(result >1000 ){
                                 newTransaction.setTransCode(String.valueOf(newCode));
                        }
                    
                }
                else{
                        newTransaction.setTransCode("000"+1);
                }
                System.out.println(":Transactioncode found :" +newTransaction.getTransCode() );
                        
              return  newTransaction.getTransCode() ;  
        }
        public void totalAM(){
            System.out.println(":TransactionAmount  :" +newTransaction.getTransAmount() );
            int allowedAmount = (( clientRecordFound.getTotalFloat()- newTransaction.getTransAmount())- selectedAccCherges.getAmount());
            if(newTransaction.getTransactionType().equalsIgnoreCase("Withdrawal") && allowedAmount < clientRecordFound.getMinimumBalance() ){
                     FacesContext context = FacesContext.getCurrentInstance();
                     //context.addMessage(null, new FacesMessage.SEVERITY_WARN("Sorry,","System encountered a problem trying to "+newTransaction.getTransactionType()+". Try again later"));
                     FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Warning!", "You can only withdraw Kshs."+ clientRecordFound.getAccAvailableAmount()+" amount  ."));
            }
        }
        
        public void doNewTransaction(){
           
                System.out.println(":TransactionAmount  :" +newTransaction.getTransAmount() );
                if(checkIfteller() > 0 && newGenTransCode() !=null &&  ( httpSession.getAttribute("userId").toString() !=null)&& (clientRecordFound.getAccNumber() !=null) && (newTransaction.getTransAmount()> 0)){
                     if((( clientRecordFound.getTotalFloat()- newTransaction.getTransAmount())- selectedAccCherges.getAmount())>= clientRecordFound.getMinimumBalance() ){
                        
                        String mysql = "INSERT INTO transaction (transCode,teller_id, userId,transType,accNo,paymentType,floatAmount,isDeposite,isWithdrawal,created_Date)"
                                + " VALUES ('"+newTransaction.getTransCode()+"','"+newTransaction.getTellerId()+"' ,'"+ httpSession.getAttribute("userId").toString()+"','"+newTransaction.getTransactionType()+"','"+ clientRecordFound.getAccNumber()+"',"
                                + "'"+newTransaction.getPaymentType()+"','"+newTransaction.getTransAmount()+"','"+newTransaction.getIsDeposite()+"','"+newTransaction.getIsWithdrawal()+"',now());";
                        System.out.println(":sql  :" +mysql );
                        FacesContext context = FacesContext.getCurrentInstance(); 
                               if(dbAction(mysql) > 0){
                                        upDateFosaMemberAcc();
                                        System.out.println(":updating FosaAcc...  :" );
                                        doLeger();
                                         System.out.println(":updating Ledgers...  :" );
                                         getLastTrans();
                                          System.out.println(":updating LastTransaction...  :" );
                                        context.addMessage(null, new FacesMessage("Bravo,","New "+""+newTransaction.getTransactionType()+" "+" was created Successfully"));
                                } else {
                                                context.addMessage(null, new FacesMessage("Sorry,","System encountered a problem trying to "+newTransaction.getTransactionType()+". Try again later"));
                                        }
                     }
                }
                //System.out.println(":SQL falidation failed  :" );
                                
                        
        }
        public void upDateFosaMemberAcc(){
                System.out.println("...updating Member Account...");
                if(newTransaction.getIsDeposite() == true){
                        String mysql = "UPDATE fosaAccount SET totalBalance = '"+( clientRecordFound.getTotalFloat()+newTransaction.getTransAmount()) +"', "
                                + "availableAmount = '"+(( clientRecordFound.getTotalFloat()+newTransaction.getTransAmount())- clientRecordFound.getMinimumBalance()) +"',"
                                + " auditTime = now() WHERE accNo = '"+clientRecordFound.getAccNumber() +"';";
                                dbUpdate(mysql);
                }
                else if(newTransaction.getIsWithdrawal() == true){
                         String mysql = "UPDATE fosaAccount SET totalBalance = '"+( clientRecordFound.getTotalFloat() - newTransaction.getTransAmount()) +"',"
                                 + " availableAmount = '"+(( clientRecordFound.getTotalFloat()- newTransaction.getTransAmount())- selectedAccCherges.getAmount()- clientRecordFound.getMinimumBalance()) +"', "
                                 + "auditTime = now() WHERE accNo = '"+clientRecordFound.getAccNumber() +"';";
                                dbUpdate(mysql);
                }
                
                        
        }
        public void doLedgerBranchAcc(String desc,boolean dr, boolean cr){
            String subAffect ="0001";
            String sessionBranchCode = httpSession.getAttribute("branchcode").toString();
                if(newTransaction.getTransCode() !=null && sessionBranchCode !=null &&
                        newTransaction.getTransactionType() !=null && newTransaction.getPaymentType()> 0 ){}
                        String query = "INSERT INTO ledge_book (transactCode,branchCode,description,debited,credited,affectedAcc,amount,created_Date)"
                                + "VALUES('"+newTransaction.getTransCode().trim()+"','"+sessionBranchCode+"','"+desc.trim()+"','"+dr+"', '"+cr+"', "
                                + "'"+subAffect.trim()+"','"+newTransaction.getTransAmount()+"', now())  ";
                        //(SELECT accNo FROM branch_manager_acc WHERE branchcode ='"+sessionBranchCode+"' LIMIT 1)
                        dbAction( query);
        }
        public void doLedgerInterestAcc(String desc,boolean dr, boolean cr){
            String subAffect ="0002";
            String sessionBranchCode = httpSession.getAttribute("branchcode").toString();
                if(newTransaction.getTransCode() !=null && sessionBranchCode !=null &&
                        newTransaction.getTransactionType() !=null && newTransaction.getPaymentType()> 0 ){}
                        String query = "INSERT INTO ledge_book (transactCode,branchCode,description,debited,credited,affectedAcc,amount,created_Date)"
                                + "VALUES('"+newTransaction.getTransCode().trim()+"','"+sessionBranchCode+"','"+desc.trim()+"','"+dr+"', '"+cr+"', "
                                + "'"+subAffect.trim()+"','"+selectedAccCherges.getAmount()+"', now())  ";
                        //(SELECT accNo FROM branch_manager_acc WHERE branchcode ='"+sessionBranchCode+"' LIMIT 1)
                        dbAction( query);
        }
        public void doUpdateTellerFloatDeposite(){
                   System .out.println("Updating Teller Float");
                        String mysql = "UPDATE assign_teller_user SET availableFloat = (select availableFloat from assign_teller_user where  teller_id "
                                + " ='"+newTransaction.getTellerId()+"' order  by created_Date desc limit 1 )+ '"+ newTransaction.getTransAmount() +"';";
                                dbUpdate(mysql);
        }
        public void doUpdateTellerFloatWithd(){
                   System .out.println("Updating Teller Float");
                        String mysql = "UPDATE assign_teller_user SET availableFloat = (select availableFloat from assign_teller_user where  teller_id "
                                + " ='"+newTransaction.getTellerId()+"' order  by created_Date desc limit 1 )-'"+ newTransaction.getTransAmount() +"';";
                                dbUpdate(mysql);
        }
       
        public int dbAction(String mysql){
                        int affected = -1;
                        try{
                                DatabaseBean db = new DatabaseBean();
                                affected = db.insert(mysql);
                                
                                db.cleanup();
                        }catch(SQLException e){
                                System.out.println("SQL Error In Transaction-payments: "+ e);
                        }catch(Exception e){
                                System.out.println("Exception Error In Transaction- payments: "+ e);
                        }
                        return affected;
        }
        public int dbUpdate(String mysql){
                        int affected = -1;
                        try{
                                DatabaseBean db = new DatabaseBean();
                                affected = db.insert(mysql);
                                db.cleanup();
                        }catch(SQLException e){
                                System.out.println("SQL Error In dbUpdate: "+ e);
                        }catch(Exception e){
                                System.out.println("Exception Error In dbUpdate: "+ e);
                        }
                        return affected;
        }
    
}
