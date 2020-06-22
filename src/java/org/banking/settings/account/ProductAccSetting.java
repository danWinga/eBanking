/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.banking.settings.account;

import java.io.Serializable;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import org.tracking.server.database.DatabaseBean;

/**
 *
 * @author dan
 */
public class ProductAccSetting implements Serializable {
    
    private static final long serialVersionUID = 1L;
    private int id, atmwithdrawal,counterWithD, chequeProcessing, accService, minBalance, uid;
    private String accSettingCode, productCode;
    
    public ProductAccSetting(){
        
    }
    public int getId(){
        return id;
    }
    public void setId(int id){
        this.id = id;
    }
    
    public int getAtmwithdrawal(){
        return atmwithdrawal;
    }
    public void setAtmwithdrawal(int str){
        this.atmwithdrawal= str;
    }
    
    public int getCounterWithD(){
        return counterWithD;
    }
    public void setCounterWithD(int str){
        this.counterWithD= str;
    }
    
    public int getMinBalance(){
        return minBalance;
    }
    public void setMinBalance(int str){
        this.minBalance= str;
    }
    
    public int getChequeProcessing(){
        return chequeProcessing;
    }
    public void setChequeProcessing(int str){
        this.chequeProcessing= str;
    }
    
    public int getAccService(){
        return accService;
    }
    public void setAccService(int str){
        this.accService= str;
    }
    
    public String getAccSettingCode(){
        return accSettingCode;
    }
    public void setAccSettingCode(String str){
        this.accSettingCode= str;
    }
    
    public String getProductCode(){
        return productCode;
    }
    public void setProductCode(String str){
        this.productCode= str;
    }
    
    public int getUid(){return uid;}
    public void setUid(int uid){this.uid = uid;}
    
    
    public void addNewAccSettings (String sql){ 
        System.out.println("*****Adding new accSettings*****");
         
          try{
            DatabaseBean db = new DatabaseBean();
            db.insert(sql);
            System.out.println("*****sql*****"+ sql);
        }catch (Exception e){
              System.out.println("Exception: "+ e);
              FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Data Error! ", ":Contact Admin  then try again"));
        }
       System.out.println("sql:"+ sql);
       FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "successfull! ", ":New File has been created"));
        
    }
}
