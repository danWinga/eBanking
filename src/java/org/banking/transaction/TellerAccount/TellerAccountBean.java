/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.banking.transaction.TellerAccount;

import com.google.zxing.Result;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.Timestamp;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.servlet.http.HttpSession;
import org.tracking.server.database.DatabaseBean;

/**
 *
 * @author dan
 */
@ManagedBean(name="dtTellerAccountBean")
@SessionScoped
public class TellerAccountBean implements Serializable {
    
        private static final long serialVersionUID = 1L;
        
        private int transState;
        private AssignedTeller selectedTellerUser;
        private TellerTransaction selectedTellerTrans;
        private TellerFloat newTellerFloat;
        private TellerFloat selectedTellerFloat;
        private ArrayList<AssignedTeller> tellerUserList;
        private ArrayList<TellerTransaction> tellerTransList;
        private ArrayList<TellerFloat> floatList;
        public SelectItem[] teller;
        public SelectItem[] branchAcc;
        
            FacesContext facesContext = FacesContext.getCurrentInstance();
            HttpSession httpSession = (HttpSession)facesContext.getExternalContext().getSession(true);
            FacesMessage msg = null;
            
            @PostConstruct
            public void init(){
                selectedTellerUser = new AssignedTeller();
                selectedTellerTrans = new TellerTransaction();
                newTellerFloat = new TellerFloat();
                selectedTellerFloat = new TellerFloat();
                transState = 0;
            }
        public int getTransState(){return transState;}  
        public void setTransState(int state){this.transState = state;}
        
        public AssignedTeller getSelectedTeller(){return selectedTellerUser;}
        public void setSelectedTeller(AssignedTeller user){this.selectedTellerUser = user;}
        
        public TellerTransaction getSelectedTellerTrans(){return selectedTellerTrans;}
        public void setSelectedTellerTrans(TellerTransaction teller){this.selectedTellerTrans = teller;}
        
        public TellerFloat getNewTellerFloat(){return newTellerFloat;}
        public void setNewTellerFloat(TellerFloat teller){this.newTellerFloat = teller;}
        
        public TellerFloat getSelectedTellerFloat(){return selectedTellerFloat;}
        public void setSelectedTellerFloat(TellerFloat teller){this.selectedTellerFloat = teller;}
        
        public ArrayList<AssignedTeller> getTellerUserList() { 
                    tellerUserList = new ArrayList<AssignedTeller>();
                    String query = "SELECT stackholder.first_Name,stackholder.middle_Name,"
                            + "stackholder.last_Name,users.user_id FROM stackholder JOIN vw_role ON "
                            + "(stackholder.email = vw_role.user_name) JOIN users ON"
                            + " (stackholder.email = users.user_name) JOIN assign_teller_user ON"
                            + " (users.user_id = assign_teller_user.userId)  ";
                    
                    String sql = "SELECT stackholder.first_Name,stackholder.middle_Name,"
                            + "stackholder.last_Name,users.user_id FROM stackholder JOIN vw_role ON "
                            + "(stackholder.email = vw_role.user_name) JOIN users ON"
                            + " (stackholder.email = users.user_name) JOIN assign_teller_user ON"
                            + " (users.user_id = assign_teller_user.userId) WHERE "
                            + "vw_role.branchcode = '1'"
                            + " AND vw_role.isTeller = true AND users.user_id NOT IN "
                            + " (SELECT assign_teller_user.userId FROM assign_teller_user WHERE created_Date = current_date)  ";
                    sql += ";";
                    
                    String query2 = "SELECT concat( stackholder.first_Name,' ',stackholder.middle_Name, ' ',stackholder.last_Name) As userName,users.user_id"
                            + " FROM stackholder JOIN vw_role ON (stackholder.email = vw_role.user_name)JOIN users ON "
                            + "(stackholder.email = users.user_name) JOIN assign_teller_user ON (users.user_id = assign_teller_user.userId) "
                            + "WHERE vw_role.branchcode = '1' AND vw_role.isTeller = true  AND users.user_id NOT IN (SELECT assign_teller_user.userId "
                            + " FROM assign_teller_user WHERE created_Date = current_date  ) ;";
                    
                   
                   /* if(!httpSession.getAttribute("branchcode").toString().isEmpty()){
                    
                       
                       query += " WHERE vw_role.branchcode = '"+ httpSession.getAttribute("branchcode").toString() +"' "
                               + "AND vw_role.isTeller = true AND users.user_id NOT IN "
                               + "(SELECT  created_Date FROM assign_teller_user WHERE created_Date = current_date)  ";
                        query += ";";

                    }else{
                        query += ";";
                    }*/
                        try{
                                DatabaseBean db = new DatabaseBean();
                                Connection conn = db.DBconnect();
                                PreparedStatement stmt = conn.prepareStatement(query2);
                                stmt.setFetchSize(100);
                                ResultSet rs = db.preparedState(stmt);
                                while(rs.next()){
                                        //tellerUserList.add(new AssignedTeller(rs.getString("first_Name"),
                                           //     rs.getString("middle_Name"),rs.getString("last_Name"),rs.getInt("user_id")));
                                          tellerUserList.add(new AssignedTeller(rs.getString("userName"),
                                              rs.getInt("user_id")));
                                }
                                rs.close();
                                db.cleanup();
                    }catch(SQLException e){
                        System.out.println("SQL Error In Assigning Teller User List: "+ e);
                        FacesMessage msg = new FacesMessage("Sorry! ", "Loading Teller Users  failed");  
                        FacesContext.getCurrentInstance().addMessage(null, msg);
                        
                    }catch(Exception e){
                        System.out.println("Exception Error In Assigning Teller User List: "+ e);
                        FacesMessage msg = new FacesMessage("Sorry! ", "Loading Teller Users  failed");  
                        FacesContext.getCurrentInstance().addMessage(null, msg);
                    }      
                   
                    return tellerUserList; 
                
        }
        public ArrayList<TellerTransaction> getTellerTransList() { 
                    tellerTransList = new ArrayList<TellerTransaction>();
                   
                    
                    String query = "SELECT concat(stackholder.first_Name,' ',stackholder.middle_Name,' ',stackholder.last_Name) "
                            + " AS userName,assign_teller_user.userId,assign_teller_user.teller_id,teller.teller_name," 
                            + "assign_teller_user.initialFloat,assign_teller_user.availableFloat FROM stackholder "
                            + "JOIN users ON (stackholder.email = users.user_name) JOIN assign_teller_user ON"
                            + " (users.user_id = assign_teller_user.userId) JOIN teller ON ( assign_teller_user.teller_id = teller.teller_id)"
                            + "WHERE  assign_teller_user.manager_id = (SELECT manager_id FROM assign_teller_user WHERE userId = '"+httpSession.getAttribute("userId").toString().trim()+"')  ;";
                    
                   
                        try{
                                DatabaseBean db = new DatabaseBean();
                                Connection conn = db.DBconnect();
                                PreparedStatement stmt = conn.prepareStatement(query);
                                stmt.setFetchSize(100);
                                ResultSet rs = db.preparedState(stmt);
                                while(rs.next()){
                                        
                                          tellerTransList.add(new TellerTransaction(rs.getString("userName"),rs.getInt("userId"),rs.getInt("teller_id"), rs.getString("teller_name") , 
                                                            rs.getInt("initialFloat"), rs.getInt("availableFloat")));
                                }
                                rs.close();
                                db.cleanup();
                    }catch(SQLException e){
                        System.out.println("SQL Error In Teller Transaction List: "+ e);
                        FacesMessage msg = new FacesMessage("Sorry! ", "Loading Teller Transaction  failed");  
                        FacesContext.getCurrentInstance().addMessage(null, msg);
                        
                    }catch(Exception e){
                        System.out.println("SQL Error In Teller Transaction List: "+ e);
                        FacesMessage msg = new FacesMessage("Sorry! ", "Loading Teller Transaction  failed");  
                        FacesContext.getCurrentInstance().addMessage(null, msg);
                    }      
                   
                    return tellerTransList; 
                
        }
        public ArrayList<TellerFloat> getFloatList() { 
                    floatList = new ArrayList<TellerFloat>();
                   TellerFloat caTeller = new TellerFloat();
                    
                    String query = "SELECT teller_float.id, teller_float.assign_id, teller_float.branch_manager_id,assign_teller_user.teller_id,teller.teller_name,"
                            + "teller_float.floatAmount,teller_float.isTopup,teller_float.isWithdrawal, date_trunc('seconds', teller_float.created_Date  )AS created_Date ,  branch_manager_acc.acc_id,"
                            + "branch_manager_acc.accNo, branch_manager_acc.totalFloat,branch_manager_acc.subAcc_no, branch_manager_acc.branchcode "
                            + "FROM teller_float JOIN assign_teller_user ON (teller_float.assign_id = assign_teller_user.assign_id) JOIN branch_manager_acc ON"
                            + " ( teller_float.branch_manager_id = branch_manager_acc.accNo) JOIN teller ON ( assign_teller_user.teller_id = teller.teller_id) "
                            + "WHERE  teller.branchcode = branch_manager_acc.branchcode AND branch_manager_acc.accNo = teller_float.branch_manager_id  ;";
                    
                   
                        try{
                                DatabaseBean db = new DatabaseBean();
                                Connection conn = db.DBconnect();
                                PreparedStatement stmt = conn.prepareStatement(query);
                                stmt.setFetchSize(100);
                                ResultSet rs = db.preparedState(stmt);
                                String  newDateString = null;
                                String testd = null;
                                while(rs.next()){
                                    
                                          floatList.add(new TellerFloat(rs.getInt("id") , rs.getInt("assign_id"), rs.getString("branch_manager_id"),
                                                  rs.getInt("teller_id"),rs.getString("teller_name"),rs.getInt("floatAmount"), rs.getBoolean("isTopup"),
                                                  rs.getBoolean("isWithdrawal"),rs.getTimestamp("created_Date") ,rs.getInt("acc_id"), rs.getString("accNo"),
                                                  rs.getInt("totalFloat"), rs.getString("subAcc_no"),rs.getString("branchcode")));
                                }
                                rs.close();
                                db.cleanup();
                    }catch(SQLException e){
                        System.out.println("SQL Error In Teller Float List: "+ e);
                        FacesMessage msg = new FacesMessage("Sorry! ", "Loading Teller Float  failed");  
                        FacesContext.getCurrentInstance().addMessage(null, msg);
                        
                    }catch(Exception e){
                        System.out.println("SQL Error In Teller Float List: "+ e);
                        FacesMessage msg = new FacesMessage("Sorry! ", "Loading Teller Float failed");  
                        FacesContext.getCurrentInstance().addMessage(null, msg);
                    }      
                   
                    return floatList; 
                
        }
        public void checkTransTate(){
            
            if(getTransState() == 1){
                    newTellerFloat.setIsTopup(true);
                    newTellerFloat.setIsWithdrawal(false);
                    System.out.println("topup : "+ newTellerFloat.getIsTopup() );
                    System.out.println("Withdrawal : "+ newTellerFloat.getIsWithdrawal() );
            }else{
                newTellerFloat.setIsWithdrawal(true);
                newTellerFloat.setIsTopup(false);
                System.out.println("Withdrawal : "+ newTellerFloat.getIsWithdrawal() );
                System.out.println("topup : "+ newTellerFloat.getIsTopup() );
            }
        }
        public void manageTellerFloat(){
                 String mysql = "INSERT INTO teller_float (assign_id, branch_manager_id, floatAmount, isTopup, isWithdrawal, created_Date) VALUES "
                         + "("+ newTellerFloat.getAssignId() +", '"+ newTellerFloat.getBranchManagerId() +"', "
                         + "'"+ newTellerFloat.getFloatAmount() +"','"+ newTellerFloat.getIsTopup()+"', '"+newTellerFloat.getIsWithdrawal()+"', now());";  
                  FacesContext context = FacesContext.getCurrentInstance(); 
                 
                 if(newTellerFloat.getAssignId()> 0 && (newTellerFloat.getBranchManagerId() !=null) && getTransState() != 0  ){
                            getOjectFloat();
                            getTellarFloatManager();
                            System.out.println(" Total inBrachFloatAcc :"+ selectedTellerFloat.getTotalFloat());
                            if((newTellerFloat.getIsTopup()== true) && (selectedTellerFloat.getTotalFloat() >= newTellerFloat.getFloatAmount()) ){
                                             
                                    if(dbAction(mysql) > 0){
                                                context.addMessage(null, new FacesMessage("Bravo,","Your Teller Float  was created Successfully"));
                                                upDateCashBranchAccTopup(); 
                                                updateTellerCashTopUp();
                                    } else {
                                                context.addMessage(null, new FacesMessage("Sorry,","System encountered a problem trying to create your Topup teller. Try again later"));
                                            }
                            }else {
                                    
                                        if(dbAction(mysql) > 0 && (selectedTellerFloat.getTotalFloat()>= newTellerFloat.getFloatAmount())){
                                                    context.addMessage(null, new FacesMessage("Bravo,","Your Teller Float  was created Successfully"));
                                                    upDateCashBranchAccWD();
                                                    updateTellerCashWd();
                                        } else {
                                                    context.addMessage(null, new FacesMessage("Sorry,","Your have less Amount than what is requested. Try again later"));
                                               }
                            
                                                   
                                  }
                 }
        }
        /***
         * Top up or withdraw Branch account float 
         * @return  selectedTellerFloat
         */
        public Object getOjectFloat(){
            for( TellerFloat tFloat : getFloatList()){
                 if(tFloat.getAccNo().equals(newTellerFloat.getBranchManagerId()))
                     return selectedTellerFloat = tFloat;
            }
           
            return selectedTellerFloat;
        }
        
        /****
         * Updating teller float
         * @return  selectedTellerTrans
         */
        public Object getTellarFloatManager(){
            for( TellerTransaction tellerTrans : getTellerTransList()){
                 if(tellerTrans.getTellerId()== newTellerFloat.getAssignId())
                     return selectedTellerTrans = tellerTrans;
            }
           
            return selectedTellerTrans;
        }
        /*******************************************************************************************************
         * 
         */
        /***
         * update teller cash :either top up >
         * OR withdrawal by branch manager
         */
        public void updateTellerCashTopUp(){
                System.out.println(" Total inTeller(topup) Float1 :"+ selectedTellerTrans.getInitialFloat());
                
                int newInAmount = 0;
                int newAvAmount = 0;
                
                           newInAmount =  selectedTellerTrans.getInitialFloat() + newTellerFloat.getFloatAmount();
                           newAvAmount = selectedTellerTrans.getTotalFloat() + newTellerFloat.getFloatAmount();
                           String mysql = "UPDATE assign_teller_user SET initialFloat = '"+ newInAmount +"', availableFloat = '"+newAvAmount +"' WHERE teller_id = '"+ selectedTellerFloat.getTellerId() +"';";
                           dbUpdate(mysql); 
                           System.out.println(" Total AvailableTellerFloat on topup :"+ newInAmount);
        }
        
        /***
         * update teller cash :either top up >
         * OR withdrawal by branch manager
         */
        public void updateTellerCashWd(){
                System.out.println(" Total inTeller(wd) Float1 :"+ selectedTellerTrans.getInitialFloat());
                
                int newInAmount = 0;
                int newAvAmount = 0;
                
                           //newInAmount =  selectedTellerTrans.getInitialFloat() + newTellerFloat.getFloatAmount();
                           newAvAmount = selectedTellerTrans.getTotalFloat() - newTellerFloat.getFloatAmount();
                           String mysql = "UPDATE assign_teller_user SET  availableFloat = '"+newAvAmount +"' WHERE teller_id = '"+ selectedTellerFloat.getTellerId() +"';";
                           dbUpdate(mysql); 
                           System.out.println(" Total AvailableTellerFloat on Withd :"+ newInAmount);
        }
        /***********************************************************************8
         * 
         */
        /***
         * withdraw cash from teller to Branch account
         */
        public void upDateCashBranchAccTopup(){
                
                //System.out.println(" Total Float1 :"+ selectedTellerFloat.getTotalFloat());
                
                int newAmount = 0;
                
                           newAmount =  selectedTellerFloat.getTotalFloat() - newTellerFloat.getFloatAmount();
                           String mysql = "UPDATE branch_manager_acc SET totalFloat = '"+ newAmount +"' WHERE accNo = '"+ selectedTellerFloat.getAccNo().trim() +"';";
                           dbUpdate(mysql); 
                           System.out.println(" Total Float on topup :"+ newAmount);
         
        }
        public void upDateCashBranchAccWD(){
                
                //System.out.println(" Total Float1 :"+ selectedTellerFloat.getTotalFloat());
                
                int newAmount = 0;
                            newAmount =  selectedTellerFloat.getTotalFloat() + newTellerFloat.getFloatAmount();
                            String mysql = "UPDATE branch_manager_acc SET totalFloat = '"+ newAmount +"' WHERE accNo = '"+ selectedTellerFloat.getAccNo().trim() +"';";
                            dbUpdate(mysql);
                            System.out.println(" Total Float3 on withdrawal :"+ newAmount);
                

        }
        public int dbAction(String mysql){
                        int affected = -1;
                        try{
                                DatabaseBean db = new DatabaseBean();
                                affected = db.insert(mysql);
                                
                                db.cleanup();
                        }catch(SQLException e){
                                System.out.println("SQL Error In FloatAccount  : "+ e);
                        }catch(Exception e){
                                System.out.println("Exception Error In FloatAccount: "+ e);
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
        
        public SelectItem[] getTeller() {
           
                String query = "SELECT assign_teller_user.assign_id,teller.teller_name "
                        + "FROM assign_teller_user  JOIN teller ON "
                        + "( assign_teller_user.teller_id = teller.teller_id) "
                        + "WHERE  teller.branchcode = '"+ httpSession.getAttribute("branchcode").toString().trim()+ "' ;";
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
                                tMap.put(rs.getString("assign_id").toString(), rs.getString("teller_name").toString());
                        }
                        rs.close();
                        db.cleanup();
                }catch(SQLException e){
                        System.out.println("Branch Teller  - Combobox SQLException: "+ e);
                }catch(Exception e){
                        System.out.println("Branch Teller - Combobox  Exception: "+ e);
                        System.err.println(e);
                }
                if (list.isEmpty()) {
                        tMap.put("--".toString(), "<<No Teller >>".toString());
                        list.add("---".toString());
                }
                teller = new SelectItem[list.size()];
                int i = 0;

                Set set = tMap.entrySet();
                Iterator ite = set.iterator();
                while(ite.hasNext()) { 
                        Map.Entry me = (Map.Entry)ite.next(); 
                        teller[i++] = new SelectItem(me.getKey().toString(), me.getValue().toString());
                } 
                return teller;
        }
        
        public SelectItem[] getBranchAcc() {
           
                String query = "SELECT branch_manager_acc.accNo,subAccount.accName  "
                        + "FROM branch_manager_acc JOIN subAccount ON "
                        + "( branch_manager_acc.subAcc_no = subAccount.subAccNo)"
                        + "WHERE  branch_manager_acc.branchcode = '"+ httpSession.getAttribute("branchcode").toString().trim()+ "' ;";
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
                                tMap.put(rs.getString("accNo").toString(), rs.getString("accName").toString());
                        }
                        rs.close();
                        db.cleanup();
                }catch(SQLException e){
                        System.out.println("Branch Account  - Combobox SQLException: "+ e);
                }catch(Exception e){
                        System.out.println("Branch Account- Combobox  Exception: "+ e);
                        System.err.println(e);
                }
                if (list.isEmpty()) {
                        tMap.put("--".toString(), "<<No Account >>".toString());
                        list.add("---".toString());
                }
                branchAcc = new SelectItem[list.size()];
                int i = 0;

                Set set = tMap.entrySet();
                Iterator ite = set.iterator();
                while(ite.hasNext()) { 
                        Map.Entry me = (Map.Entry)ite.next(); 
                        branchAcc[i++] = new SelectItem(me.getKey().toString(), me.getValue().toString());
                } 
                return branchAcc;
        }
  
}
