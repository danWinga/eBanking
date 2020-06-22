/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.banking.settings.account;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author dan
 */
public class AccSettings implements Serializable{
    
    private static final long serialVersionUID = 1L;
    private int id, amount;
    private String accSetting, settingName,subAcc, accDesc,accgroupCode,productCode, productName;
    private Date auditTime;
    
     public AccSettings(){
         amount = 0;
         accgroupCode = "";
         productCode = "";
         accSetting = "";
         settingName = "";
     }
     

    AccSettings(String accgroupCode, String productCode, String productName, String accSettingCode,
            String settingName, int amount) {
        this.accgroupCode = accgroupCode;
        this.productCode = productCode;
        this.productName = productName;
        this.accSetting = accSettingCode;
        this.settingName = settingName;
        this.amount = amount;
    }
     public int getId(){
         return id;
     }
     public  void setId(int id){this.id = id;}
     
     public int getAmount(){return amount;}
     public void setAmount(int amount){this.amount = amount;}
     
     public String getAccSetting(){return accSetting;}
     public void setAccSetting(String str){this.accSetting = str;}
     
     public String getSettingName(){return settingName;}
     public void setSettingName(String str){this.settingName = str;}
     
     public String getSubAcc(){return subAcc;}
     public void setSubAcc(String str){this.subAcc = str;}
     
     public String getAccDesc(){return accDesc;}
     public void setAccDesc(String str){this.accDesc = str;}
     
     public String getAccgroupCode(){return accgroupCode;}
     public void setAccgroupCode(String str){this.accgroupCode = str;}
     
     public String getProductCode(){return productCode;}
     public void setProductCode(String str){this.productCode = str;}
     
     public String getProductName(){return productName;}
     public void setProductName(String str){this.productName = str;}
     
     public Date getAuditTime(){return auditTime;}
     public void setAuditTime(Date str){this.auditTime = str;}
}
