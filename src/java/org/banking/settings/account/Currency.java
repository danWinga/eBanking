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
public class Currency implements Serializable {
    
    private static final long serialVersionUID = 1L;
    private int id;
    private String currencyName, range;
    
    
    
    public Currency(){}
    
    public String getCurrencyName(){
        return currencyName;
    }
    public void setCurrencyName(String str){
        this.currencyName = str;
    }
    
    public int getId(){
        return id;
    }
    
    public void setId(int id){
        this.id = id;
    }
    public String getRange(){
        return range;
    }
    
    public void setRange(String str){
        this.range = str;
    }
}
