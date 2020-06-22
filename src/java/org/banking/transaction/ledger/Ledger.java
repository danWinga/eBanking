/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.banking.transaction.ledger;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.Date;
import org.tracking.server.database.DatabaseBean;

/**
 *
 * @author dan
 */
public class Ledger implements Serializable {
        private int id,amount;
        private String transactCode, branchCode,description,affectedAcc;
        private boolean debited,credited;
        private Date createdDate;
        
        public Ledger(){}
        
        public Ledger(String transactCode,String branchCode, String description,boolean debited,
                boolean credited,String affectedAcc,int amount,Date createdDate){
                this.transactCode =transactCode;
                this.branchCode = branchCode;
                this.description =description;
                this.debited = debited;
                this.credited = credited;
                this.affectedAcc = affectedAcc;
                this.amount = amount;
                this.createdDate = createdDate;
        }

        public int getId(int id){return id;}
        public void setId(int id){this.id = id;}

        public int getAmount(){return amount;}
        public void setAmount(int amount){this.amount = amount;}
        
        public String getTransactCode(){return transactCode;}
        public void setTransactCode(String transactCode){this.transactCode = transactCode;}
        
        public String getBranchCode(){return branchCode;}
        public void setBranchCode(String branchCode){this.branchCode = branchCode;} 
        
        public String getDescription(){return description;}
        public void setDescription(String description){this.description = description;}
        
        public String getAffectedAcc(){return affectedAcc;}
        public void setAffectedAcc(String affectedAcc){this.affectedAcc = affectedAcc;} 
        
        public boolean getDebited(){return debited;}
        public void setDebited(boolean debited){this.debited = debited;}
        
        public boolean getCredited(){return credited;}
        public void setCredited(boolean credited){this.credited = credited;} 
        
        public Date getCreatedDate(){return createdDate;}
        public void setCreatedDate(Date createdDate){this.createdDate = createdDate;}
        
        public int dbAction(String mysql){
                        int affected = -1;
                        try{
                                DatabaseBean db = new DatabaseBean();
                                affected = db.insert(mysql);
                                db.cleanup();
                        }catch(SQLException e){
                                System.out.println("SQL Error In Ledger: "+ e);
                        }catch(Exception e){
                                System.out.println("Exception Error In Ledger: "+ e);
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
