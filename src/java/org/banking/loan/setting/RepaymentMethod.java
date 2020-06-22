/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.banking.loan.setting;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.Date;
import org.tracking.server.database.DatabaseBean;

/**
 *
 * @author dan
 */
public class RepaymentMethod implements Serializable{
        private int id;
        private String  methodCode,methodName;
        private Date createdDate;
        
        public int getId(){return id;}
        public void setId(int id){this.id = id;}
        
        public String getMethodCode(){return methodCode ;}
        public void setMethodCode(String methodCode){this.methodCode = methodCode;}
        
        public String getMethodName(){return methodName ;}
        public void setMethodName(String methodName){this.methodName = methodName;}
        
        public Date getCreatedDate(){return createdDate;}
        public void setCreatedDate(Date createdDate){this.createdDate = createdDate;}
        
        public void dbAction(String sql){
                        try{
                            DatabaseBean db = new DatabaseBean();
                            db.insert(sql);
                            db.cleanup();
                        }catch(SQLException e){
                            System.out.println("SQL Error In Loan Repayment Method: "+ e);
                        }catch(Exception e){
                            System.out.println("Exception Error In Loan Repayment method: "+ e);
                        }        
        }
        public void upDate(){
                dbAction("UPDATE repaymentMethod SET methodName ='"+methodName+"'  WHERE id ="+id+" ; ");
        }
        
        public void delete(){
               dbAction("DELETE repaymentMethod WHERE id = "+id+"; ");
        }
    
}
