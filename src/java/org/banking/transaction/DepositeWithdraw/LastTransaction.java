/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.banking.transaction.DepositeWithdraw;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author dan
 */
public class LastTransaction implements Serializable {
    
        private int accLastTranAmount;
        private  String transType ,transCode,accNo;
        private Date lastTransDate;
        
        public LastTransaction(){
                accLastTranAmount = 0;
                transType = "";
                lastTransDate = new Date();
                transCode = "";
                accNo = "";
        }
        public LastTransaction(String transCode,Date transDate,String transType, int amount  ){
                this.transCode = transCode;
                this.lastTransDate = transDate;
                this.transType = transType;
                this.accLastTranAmount = amount;
        }
        
        public int getAccLastTranAmount(){return accLastTranAmount;}
        public void setAccLastTranAmount(int accLastTranAmount ){this.accLastTranAmount = accLastTranAmount;}
        
        public String getTransType(){return transType;}
        public void setTransType(String  transType ){this.transType = transType;}
        
        public String getAccNo(){return accNo;}
        public void setAccNo(String  accNo ){this.accNo= accNo;}
        
        public String getTransCode(){return transCode;}
        public void setTransCode(String transCode){this.transCode = transCode;}
        
        public Date getLastTransDate(){return lastTransDate;}
        public void setLastTransDate(Date lstDate){this.lastTransDate = lstDate;}
}
