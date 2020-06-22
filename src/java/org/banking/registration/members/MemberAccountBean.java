/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.banking.registration.members;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.TreeMap;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.servlet.http.HttpSession;
import org.tracking.server.database.DatabaseBean;

/**
 *
 * @author dan
 */
public class MemberAccountBean implements Serializable{
    
            private static final long serialVersionUID = 1L;
            private MemberAccount newMemberAccount;
            
            public SelectItem[] productType;
    
            FacesContext facesContext = FacesContext.getCurrentInstance();
            HttpSession httpSession = (HttpSession)facesContext.getExternalContext().getSession(true);
            FacesMessage msg = null;

            @PostConstruct
            public void init(){
                        newMemberAccount = new MemberAccount();
            }
            
            public MemberAccount getNewMemberAccount(){
                     return newMemberAccount;
             }
            public void setNewMemberAccount(MemberAccount newMemberAcc){
                     this.newMemberAccount  = newMemberAcc;
            }
            
            /********* Product Types *********
      * **  ComboBox for Product Types e.g
      * * Personal -: individual, joint 
      * * 
     * @param str
      * @return productType
      */
    public void checkProductGroup(){
        System.out.println(" Group selected: "+ newMemberAccount.getGroupCode());
        String prodgp = newMemberAccount.getGroupCode();
        if(!prodgp.equals("") )
            getProductTypes();
        
    }
     public SelectItem[] getProductTypes() {
         
        String  prod ="PC001";
        
        System.out.println("*********account product code1 :"+prod);
                String query = "SELECT  productCode, productName FROM vw_accProducts where acctypeCode = '"+prod.trim()+"' AND groupCode   = '"+ newMemberAccount.getGroupCode().trim()+"'   ;";///where acctypeCode = '"+selectedAccType.getAcctypeCode().trim()+"'
               /* String sql = "SELECT accProductsGroup.groupCode,accProductsGroup.groupName,accProducts.productCode," +
                    "accProducts.productName,accProducts.acctypeCode" +
                    "FROM accProductsGroup" +
                    "INNER JOIN accProducts ON accProducts.groupCode = accProductsGroup.groupCode;";
*/
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
                                tMap.put(rs.getString("productCode").toString(), rs.getString("productName").toString());
                        }
                        rs.close();
                        db.cleanup();
                }catch(SQLException e){
                        System.out.println("product Types  - Combobox SQLException: "+ e);
                }catch(Exception e){
                        System.out.println("product Types - Combobox  Exception: "+ e);
                        System.err.println(e);
                }
                if (list.isEmpty()) {
                        tMap.put("--".toString(), "<<No product Types >>".toString());
                        list.add("---".toString());
                }
                productType = new SelectItem[list.size()];
                int i = 0;

                Set set = tMap.entrySet();
                Iterator ite = set.iterator();
                while(ite.hasNext()) { 
                        Map.Entry me = (Map.Entry)ite.next(); 
                        productType[i++] = new SelectItem(me.getKey().toString(), me.getValue().toString());
                } 
                return productType;
        }
     
        public void createAccNumberz(){
                    Random r1 = new Random();
                    Random r2 = new Random();
                    Random r3 = new Random();
                    int num1 = r1.nextInt((4000 - 9999) + 1) + 4000;
                    int num2 = r2.nextInt((1000 - 9999) + 1) + 1000;
                    int num3 = r3.nextInt((5000 - 9999) + 1) + 5000;
                    String accNumber =String.valueOf(num1 + num2 +num3);
                    System.out.println("accNumber :"+ accNumber);
                    String sql = "SELECT  accNo FROM fosaAccount where accNo = '"+accNumber.trim()+"' ;";
                    dbCheckAcc(sql);
        
        }
        public String dbCheckAcc(String sql){
            System.out.println("sql:"+ sql);
            String accStringNo = "";
            
                        try{
                             DatabaseBean db = new DatabaseBean();
                             Connection con = db.DBconnect();
                             PreparedStatement stmt = con.prepareStatement(sql);
                             ResultSet rs=db.preparedState(stmt);
                             int x = -1;
                             while(rs.next()){
                               accStringNo = rs.getString("accNo");
                             }
                             x++;
                             if(x < 0 ){
                             newMemberAccount.setAccNo(accStringNo);
                             accStringNo = newMemberAccount.getAccNo();
                             }else{
                               accStringNo = "none";  
                             }
                        }catch(SQLException e){
                            System.out.println("SQL Error In AccNumber Check: "+ e);
                        }catch(Exception e){
                            System.out.println("Exception Error In AccNumber Check: "+ e);
                        }
                        return  accStringNo  ;
        }
        public void finalAccNumberCheck(){
            if(newMemberAccount.getAccNo() == null)
                createAccNumberz();
        }
        public void addMemberAcc(){
            System.out.println("Adding Account....");
            finalAccNumberCheck();
            String sql = "INSERT INTO fosaAccount (accNo, memberNo, branchCode,groupCode,productCode,currencyCode,incomeCode ,totalBalance,"
                    + "availableAmount,isActive,auditTime) VALUES ('"+ newMemberAccount.getAccNo().trim() +"', '"+ newMemberAccount.getAccNo().trim() +"',"
                    + " '"+newMemberAccount.getAccNo().trim() +"','"+ newMemberAccount.getGroupCode().trim() +"','"+ newMemberAccount.getProductCode().trim() +"',"
                    + "'"+ newMemberAccount.getCurrencyCode().trim() +"','"+ newMemberAccount.getIncomeCode().trim() +"','"+ newMemberAccount.getTotalBalance() +"',"
                    + " '"+ newMemberAccount.getAvailableAmount() +"','"+ newMemberAccount.getIsActive() +"', now();";         
            System.out.println(sql);
                        try{
                            DatabaseBean db = new DatabaseBean();
                            db.insert(sql);
                            db.cleanup();
                        }catch(SQLException e){
                            System.out.println("SQL Error In FOSA Account: "+ e);
                        }catch(Exception e){
                            System.out.println("Exception Error In FOSA Account: "+ e);
                        }        
        }
    
}
