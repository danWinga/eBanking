/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.tracking.shared.comboboxes;

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
 * @author root
 */
@ManagedBean(name = "comboBoxes")
@SessionScoped
public class ComboBoxes  implements Serializable{
     public SelectItem[] filesCode;
     public SelectItem[] filesCodeAcademic;
     public SelectItem[] folderCodes;
     public SelectItem[] classes;
     public SelectItem[] classStream;
     public SelectItem[] academicTerms;
     
     FacesContext facesContext = FacesContext.getCurrentInstance();
     HttpSession httpSession = (HttpSession)facesContext.getExternalContext().getSession(true);
     
     /***
      * generate Folder codes in a combo box
      * @return  SchoolFodercode
      */
     
     public SelectItem[] getFoldercodes() {
                String query = "SELECT acc_Code, account_Name FROM sch_Accounts;";

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
                                tMap.put(rs.getString("acc_Code").toString(), rs.getString("account_Name").toString());
                        }
                        rs.close();
                        db.cleanup();
                }catch(SQLException e){
                        System.out.println("folder code  - Combobox SQLException: "+ e);
                }catch(Exception e){
                        System.out.println("folder code - Combobox  Exception: "+ e);
                        System.err.println(e);
                }
                if (list.isEmpty()) {
                        tMap.put("--".toString(), "<<No Folders >>".toString());
                        list.add("---".toString());
                }
                folderCodes = new SelectItem[list.size()];
                int i = 0;

                Set set = tMap.entrySet();
                Iterator ite = set.iterator();
                while(ite.hasNext()) { 
                        Map.Entry me = (Map.Entry)ite.next(); 
                        folderCodes[i++] = new SelectItem(me.getKey().toString(), me.getValue().toString());
                } 
                return folderCodes;
        }
     
     /***
      * Academic Term in combo box
      * @return academicTerms
      */
     public SelectItem[] getAcademicterms(){
            String query = "SELECT term_code, term_name FROM academic_term;";
         return academicTerms;
         
     }
     /***
      * generate file codes in a combo box
      * @return  filesCodes
      */
     
     public SelectItem[] getFilescode() {
                String query = "SELECT file_Code, file_Name FROM account_Files;";

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
                                tMap.put(rs.getString("file_Code").toString(), rs.getString("file_Name").toString());
                        }
                        rs.close();
                        db.cleanup();
                }catch(SQLException e){
                        System.out.println("Files code  - Combobox SQLException: "+ e);
                }catch(Exception e){
                        System.out.println("Files code - Combobox SQLException: "+ e);
                        System.err.println(e);
                }
                if (list.isEmpty()) {
                        tMap.put("--".toString(), "<<No Files>>".toString());
                        list.add("---".toString());
                }
                filesCode = new SelectItem[list.size()];
                int i = 0;

                Set set = tMap.entrySet();
                Iterator ite = set.iterator();
                while(ite.hasNext()) { 
                        Map.Entry me = (Map.Entry)ite.next(); 
                        filesCode[i++] = new SelectItem(me.getKey().toString(), me.getValue().toString());
                } 
                return filesCode;
        }
     /***
      * generate file codes in a combo box
      * @return  filesCodes Academic year
      */
     
     public SelectItem[] getFilescodeAcademic() {
                String query = "SELECT file_Code, file_Name FROM account_Files where acc_code = 'SC/9435';";

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
                                tMap.put(rs.getString("file_Code").toString(), rs.getString("file_Name").toString());
                        }
                        rs.close();
                        db.cleanup();
                }catch(SQLException e){
                        System.out.println("Files code  - Combobox SQLException: "+ e);
                }catch(Exception e){
                        System.out.println("Files code - Combobox SQLException: "+ e);
                        System.err.println(e);
                }
                if (list.isEmpty()) {
                        tMap.put("--".toString(), "<<No Acaademic Year>>".toString());
                        list.add("---".toString());
                }
                filesCodeAcademic = new SelectItem[list.size()];
                int i = 0;

                Set set = tMap.entrySet();
                Iterator ite = set.iterator();
                while(ite.hasNext()) { 
                        Map.Entry me = (Map.Entry)ite.next(); 
                        filesCodeAcademic[i++] = new SelectItem(me.getKey().toString(), me.getValue().toString());
                } 
                return filesCodeAcademic;
        }
     /***
      * selecting classes
      */
      public SelectItem[] getClasses() {
                String query = "SELECT class_code, class_name FROM classes;";

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
                                tMap.put(rs.getString("class_code").toString(), rs.getString("class_name").toString());
                        }
                        rs.close();
                        db.cleanup();
                }catch(SQLException e){
                        System.out.println("class code  - Combobox SQLException: "+ e);
                }catch(Exception e){
                        System.out.println("class code - Combobox  Exception: "+ e);
                        System.err.println(e);
                }
                if (list.isEmpty()) {
                        tMap.put("--".toString(), "<<No Class >>".toString());
                        list.add("---".toString());
                }
                classes = new SelectItem[list.size()];
                int i = 0;

                Set set = tMap.entrySet();
                Iterator ite = set.iterator();
                while(ite.hasNext()) { 
                        Map.Entry me = (Map.Entry)ite.next(); 
                        classes[i++] = new SelectItem(me.getKey().toString(), me.getValue().toString());
                } 
                return classes;
        }
      /***
       * 
       * @return  classStream
       */
      public SelectItem[] getClassStream() {
                String query = "SELECT stream_code, stream_name FROM streams;";

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
                                tMap.put(rs.getString("stream_code").toString(), rs.getString("stream_name").toString());
                        }
                        rs.close();
                        db.cleanup();
                }catch(SQLException e){
                        System.out.println("stream code  - Combobox SQLException: "+ e);
                }catch(Exception e){
                        System.out.println("stream code - Combobox  Exception: "+ e);
                        System.err.println(e);
                }
                if (list.isEmpty()) {
                        tMap.put("--".toString(), "<<No stream >>".toString());
                        list.add("---".toString());
                }
                classStream = new SelectItem[list.size()];
                int i = 0;

                Set set = tMap.entrySet();
                Iterator ite = set.iterator();
                while(ite.hasNext()) { 
                        Map.Entry me = (Map.Entry)ite.next(); 
                        classStream[i++] = new SelectItem(me.getKey().toString(), me.getValue().toString());
                } 
                return classStream;
        }
}
