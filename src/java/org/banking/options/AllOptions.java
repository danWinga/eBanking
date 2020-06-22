/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.banking.options;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.servlet.http.HttpSession;
import org.tracking.server.database.DatabaseBean;

/**
 *
 * @author dan
 */
@ManagedBean(name = "dtOptions")
@SessionScoped
public class AllOptions implements Serializable {
    
    private static final long serialVersionUID = 1L;
    public SelectItem[] incomeLevel; // range of income ie. 0-20,000
    public SelectItem[] currency; // Kshs., $, 
    public SelectItem[] county;
     
     FacesContext facesContext = FacesContext.getCurrentInstance();
     HttpSession httpSession = (HttpSession)facesContext.getExternalContext().getSession(true);
    
     
     
     /***** Range of income **********
      * @return 
      * 
      */
     public SelectItem[] getIncomeLevel() {
                String query = "SELECT incomeCode, rangeName FROM incomeLevel;";

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
                                tMap.put(rs.getString("incomeCode").toString(), rs.getString("rangeName").toString());
                        }
                        rs.close();
                        db.cleanup();
                }catch(SQLException e){
                        System.out.println("IncomeLevel  - Combobox SQLException: "+ e);
                }catch(Exception e){
                        System.out.println("IncomeLevel - Combobox  Exception: "+ e);
                        System.err.println(e);
                }
                if (list.isEmpty()) {
                        tMap.put("--".toString(), "<<No income Level >>".toString());
                        list.add("---".toString());
                }
                incomeLevel = new SelectItem[list.size()];
                int i = 0;

                Set set = tMap.entrySet();
                Iterator ite = set.iterator();
                while(ite.hasNext()) { 
                        Map.Entry me = (Map.Entry)ite.next(); 
                       incomeLevel[i++] = new SelectItem(me.getKey().toString(), me.getValue().toString());
                } 
                return incomeLevel;
        }
        
     
        /***** currency **********
      * @return 
      * 
      */
     public SelectItem[] getCurrency() {
                String query = "SELECT code, currency FROM accurrency;";

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
                                tMap.put(rs.getString("code").toString(), rs.getString("currency").toString());
                        }
                        rs.close();
                        db.cleanup();
                }catch(SQLException e){
                        System.out.println("currency  - Combobox SQLException: "+ e);
                }catch(Exception e){
                        System.out.println("currency - Combobox  Exception: "+ e);
                        System.err.println(e);
                }
                if (list.isEmpty()) {
                        tMap.put("--".toString(), "<<No currecy >>".toString());
                        list.add("---".toString());
                }
                currency = new SelectItem[list.size()];
                int i = 0;

                Set set = tMap.entrySet();
                Iterator ite = set.iterator();
                while(ite.hasNext()) { 
                        Map.Entry me = (Map.Entry)ite.next(); 
                       currency[i++] = new SelectItem(me.getKey().toString(), me.getValue().toString());
                } 
                return currency;
        }
     
      
     
     /******* County *************
      * @return
      * 
      */
     public SelectItem[] getCounty() {
                String query = "SELECT countycode, countyName FROM county;";

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
                                tMap.put(rs.getString("countycode").toString(), rs.getString("countyName").toString());
                        }
                        rs.close();
                        db.cleanup();
                }catch(SQLException e){
                        System.out.println("County  - Combobox SQLException: "+ e);
                }catch(Exception e){
                        System.out.println("county - Combobox  Exception: "+ e);
                        System.err.println(e);
                }
                if (list.isEmpty()) {
                        tMap.put("--".toString(), "<<No County >>".toString());
                        list.add("---".toString());
                }
                county = new SelectItem[list.size()];
                int i = 0;

                Set set = tMap.entrySet();
                Iterator ite = set.iterator();
                while(ite.hasNext()) { 
                        Map.Entry me = (Map.Entry)ite.next(); 
                       county[i++] = new SelectItem(me.getKey().toString(), me.getValue().toString());
                } 
                return county;
        }
     
}
