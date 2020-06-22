/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.banking.settings.account;

import java.io.Serializable;

/**
 *
 * @author dan
 */
public class AccountOpenType  implements Serializable{
    
    private static final long serialVersionUID = 1L;
    private String acctypeCode ,accName,subAcc,accDesc;
    private int ttAmount;
    
    public AccountOpenType(){}
    
    public String getAcctypeCode(){
        return acctypeCode;
    }
    public void setAcctypeCode(String str){
        this.acctypeCode = str;
    }
    
    public String getAccName(){
        return accName;
    }
    public void setAccName(String str){
        this.accName = str;
    }
    
    public String getSubAcc(){
        return subAcc;
    }
    public void setSubAcc(String str){
        this.subAcc = str;
    }
    
    public String getAccDesc(){
        return accDesc;
    }
    public void setAccDesc(String str){
        this.accDesc = str;
    }
    
    public int getTtAmount(){
        return ttAmount;
    }
    public void setTtAmount(int str){
        this.ttAmount = str;
    }
}
