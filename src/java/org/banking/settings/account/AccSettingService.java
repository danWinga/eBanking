/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.banking.settings.account;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import org.tracking.server.database.DatabaseBean;

/**
 *
 * @author dan
 */
@ManagedBean(name = "accSettingService")
@SessionScoped
public class AccSettingService {
        private String prodcode;
        private static ArrayList<AccSettings> accSList;
        FacesContext facesContext;
        HttpSession httpSession;
        FacesMessage msg = null;
        
        public  AccSettingService(){
                facesContext = FacesContext.getCurrentInstance();
                httpSession = (HttpSession)facesContext.getExternalContext().getSession(true);
                
        }
    
       
    
        public ArrayList<AccSettings>getAccSList(){
            
                System.out.println("reading data.........");
                String query = "SELECT * FROM vw_accSettingsGroup ";
                if( ( prodcode !=null) ){
                            query += " WHERE productcode = '"+ prodcode.trim() +"'";
                }

                        query += ";";

                             AccSetList(query);
                    

                return accSList;
        }
    
        public void AccSetList(String query){
                        accSList = new ArrayList<AccSettings>();
                        try {
                                DatabaseBean db = new DatabaseBean();
                                Connection con = db.DBconnect();
                                PreparedStatement stmt = con.prepareStatement(query);
                                stmt.setFetchSize(100);
                                ResultSet rs=db.preparedState(stmt);

                                while(rs.next()){
                                       accSList.add(new AccSettings(rs.getString("accgroupCode"),
                                               rs.getString("productCode"),rs.getString("productName"), 
                                               rs.getString("accSettingCode"),rs.getString("settingName"),
                                               rs.getInt("amount")));
                                }
                                rs.close();
                                db.cleanup();                    
                        }catch(SQLException e){
                                System.out.println("Error :"+ e);
                                msg = new FacesMessage(FacesMessage.SEVERITY_FATAL, "Sql Error"," Please contact admin!");
                                System.out.println("Product Account list  error in SQLE !"+e);
                        }catch(Exception e){
                                msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Saving Error"," Fill in the missing fields!");
                                System.out.println(" error in EXception !"+e);

                        }
            }
            public String getProdCode(){ return prodcode;  }
            public void setProdCode(String prodcode){this.prodcode = prodcode;}
        
    
}
