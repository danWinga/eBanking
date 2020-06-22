/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.banking.loan.setting;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import org.tracking.server.database.DatabaseBean;

/**
 *
 * @author dan
 */
@ManagedBean(name="loanTypeBean")
@SessionScoped
public class LoanTypesBean implements Serializable {
        private static final long serialVersionUID = 1L;  
        private LoanType newLoanType =  new LoanType();
        private LoanType selectedLoan = new LoanType();
        private static ArrayList<LoanType> loanTypeList;
        private List<LoanType> filteredType;

        FacesContext facesContext;
        HttpSession httpSession;

        public LoanTypesBean(){
                 facesContext = FacesContext.getCurrentInstance();
                 httpSession = (HttpSession)facesContext.getExternalContext().getSession(true);
        }
        
        public void lnTypeList(){
                String query = "SELECT loanTypes.id,loanTypes.loanCode,loanTypes.loanName,loanTypes.subAcc,loanTypes.minPaidBridging,loanTypes.minPaidTopUp,"
                        + "loanTypes.isGuarantor,  loanTypes.maxAmount,loanTypes.repaymentMethod,repaymentMethod.methodName,loanTypes.interestRate,loanTypes.gracePeriod,"
                        + "loanTypes.createDate    FROM loanTypes  JOIN repaymentMethod ON (loanTypes.repaymentMethod = repaymentMethod.methodCode )  WHERE  loanTypes.id  = loanTypes.id  "; 
                if(!httpSession.getAttribute("userId").toString().equals("")){
                                       
                        query += "ORDER BY loanCode DESC;";
                        loanTypeList = new ArrayList<LoanType>();
                        try {
                                DatabaseBean db = new DatabaseBean();
                                Connection con = db.DBconnect();
                                PreparedStatement stmt = con.prepareStatement(query);
                                ResultSet rs=db.preparedState(stmt);

                                while(rs.next()){
                                        loanTypeList.add(new LoanType(rs.getInt("id"), rs.getString("loanCode"), 
                                                            rs.getString("loanName"), rs.getString("subAcc"), 
                                                            rs.getDouble("minPaidBridging"), rs.getDouble("minPaidTopUp"), 
                                                            rs.getBoolean("isGuarantor"), rs.getDouble("maxAmount"),rs.getString("repaymentMethod"), 
                                                            rs.getString("methodName"), rs.getInt("interestRate"), 
                                                            rs.getInt("gracePeriod"), rs.getTimestamp("createDate")));
                                }
                                rs.close();
                                db.cleanup();                    
                        }catch(SQLException e){
                                System.out.println("Error1, Reading LoanType List :"+ e);
                        }catch(Exception e){
                                System.out.println("Error2, Reading LoanType List :"+ e);
                        }
                }
        }
        
        public ArrayList<LoanType> getLoanTypeList() { 
                
                if(loanTypeList == null){
                        lnTypeList();
                }
                return loanTypeList; 
        }


        public LoanType getNewLoanType(){return newLoanType;}
        public void setNewLoanType(LoanType newLoanType){this.newLoanType  = newLoanType;}

        public LoanType getSelectedLoan(){return selectedLoan;}
        public void setSelectedLoan(LoanType selectedLoan){this.selectedLoan  = selectedLoan;}
       
        public List<LoanType> getFilteredType() {  
                return filteredType;  
        }  
  
        public void setFilteredType(List<LoanType> filteredType) {  
                this.filteredType = filteredType;  
        }
        public void addLoanType(){
                newLoanType.dbAction("INSERT INTO loanTypes (loanCode,loanName,subAcc,minPaidBridging,minPaidTopUp,isGuarantor,"
                        + "maxAmount,repaymentMethod,interestRate,gracePeriod,createDate )"
                        + "VALUES('"+newLoanType.getLoanCode().trim()+"','"+newLoanType.getLoanName().trim()+"','"+newLoanType.getSubAcc()+"',"
                        + "'"+newLoanType.getMinPaidBridging()+"','"+newLoanType.getMinPaidTopUp()+"','"+newLoanType.getIsGuarantor()+"',"
                        + "'"+newLoanType.getMaxAmount()+"','"+newLoanType+"','"+newLoanType.getRepaymentMethod()+"','"+newLoanType.getInterestRate()+"','"+newLoanType.getGracePeriod()+"',now()) ;");
        }

}
