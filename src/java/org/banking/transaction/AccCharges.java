/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.banking.transaction;

import java.io.Serializable;

/**
 *
 * @author dan
 */
public class AccCharges implements Serializable  {
        private String  accgroupCode, productCode,productName;
        private String accSettingCode , settingName;
        private int amount;
        
        public AccCharges (){}
        public AccCharges(String  accgroupCode, String productCode, String productName,
                String   accSettingCode, String settingName, int amount  ){
                this.accSettingCode = accSettingCode;
                this.accgroupCode = accgroupCode;
                this.productCode = productCode;
                this.productName = productName;
                this.settingName = settingName;
                this.amount = amount;
                
        }
        
        public String getAccgroupCode(){return accgroupCode;}
        public void setAccgroupCode(String accgroupCode){this.accgroupCode =  accgroupCode;}
        
        public String getProductCode(){return productCode;}
        public void setProductCode(String productCode){this.productCode =  productCode;}
        
        public String getProductName(){return productName;}
        public void setProductName(String productName){this.productName = productName;}
        
        public String getAccSettingCode(){return accSettingCode;}
        public void setAccSettingCode(String accSettingCode){this.accSettingCode = accSettingCode;}
        
        public String getSettingName(){return settingName;}
        public void setSettingName(String settingName){this.settingName = settingName;}
        
        public int getAmount(){return amount;}
        public void setAmount(int amount){this.amount = amount;}
                
        
    
}
