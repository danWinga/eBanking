/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.banking.settings.admin;

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
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.model.SelectItem;
import javax.servlet.http.HttpSession;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.LazyDataModel;
import org.tracking.server.database.DatabaseBean;

/**
 *
 * @author dan
 */
@ManagedBean(name="dtBranchBean")
@SessionScoped
public class BranchesBean implements Serializable {
    
            private static final long serialVersionUID = 1L;
            private Branch selectedBranch;
            private Branch newBranch;
            private County selectedCounty;
            private County newCounty;
            private Teller selectedTeller;
            private BranchManager selectedBranchManager;
            private BranchManager newBranchManager;
            private ArrayList<County>countysList;
            private ArrayList<Branch> branchList;
            private ArrayList<Teller> tellerList;
            private ArrayList<BranchManager>branchManagerList;
            
            private LazyDataModel<County>  countyDataModel;
            private LazyDataModel<Branch> branchDataModel;
            
            public SelectItem[] manager;
            
            FacesContext facesContext = FacesContext.getCurrentInstance();
            HttpSession httpSession = (HttpSession)facesContext.getExternalContext().getSession(true);
            FacesMessage msg = null;
            
            @PostConstruct
            public void init(){
                selectedBranch = new Branch();
                selectedCounty = new County();
                newCounty = new County();
                newBranch = new Branch();
                newBranchManager = new BranchManager();
                selectedBranchManager =new BranchManager();
            }
            
            public LazyDataModel<County> getCountyDataModel(){
                 return  countyDataModel;
            }
            public LazyDataModel<Branch> getBranchDatamodel(){
                    return branchDataModel;
            }

            public Branch getSelectedBranch(){
                return selectedBranch;
            }
            public void setSelectedBranch(Branch selecBranch){this.selectedBranch = selecBranch;}
            public Branch getNewBranch(){return newBranch;}
            public void setNewBranch(Branch newBranch){this.newBranch = newBranch;}
            
            
            public County getSelectedCounty(){
                return selectedCounty;
            }
            public void setSelectedCounty(County selecCounty){this.selectedCounty = selecCounty;}
            
            public County getNewCounty(){   return newCounty;}
            public void setNewCounty(County newCounty){this.newCounty = newCounty;}
            
            public Teller getSelectedTeller(){return selectedTeller;}
            public void setSelectedTeller(Teller selectedTeller){this.selectedTeller = selectedTeller;}
            
            public BranchManager getNewBranchManager(){return newBranchManager;}
            public void setNewBranchManager(BranchManager newBrh){this.newBranchManager = newBrh;}
            
            public BranchManager getSelectedBranchManager(){return selectedBranchManager;}
            public void setSelectedBranchManager(BranchManager selBrh){this.selectedBranchManager = selBrh;}
            
            /***
             * countyList
             * @return countysList 
             */
            public ArrayList<County> getCountyList(){
                    countysList = new ArrayList<County>();
                    String query = "SELECT countycode, countyname  FROM county ;";
                    try{
                                DatabaseBean db = new DatabaseBean();
                                Connection con = db.DBconnect();
                                PreparedStatement stmt = con.prepareStatement(query);
                                stmt.setFetchSize(100);
                                ResultSet rs  = db.preparedState(stmt);
                                while(rs.next()){
                                  countysList.add(new County(rs.getString("countycode"), rs.getString("countyName")));
                                }
                                rs.close();
                                db.cleanup();
                    }catch(SQLException e){
                                msg = new FacesMessage(FacesMessage.SEVERITY_FATAL, "Sql Error"," Please contact admin!");
                            System.out.println("all County  error in SQLE !"+e);
                    }catch(Exception e ){
                                msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Saving Error"," Fill in the missing fields!");
                                System.out.println(" error in EXception !"+e);
                    }
                    
                  return countysList;
                
            }
            
            public void onRowCountSelect(SelectEvent event ){
            msg = new FacesMessage("County Selected", ((County) event.getObject()).getCountyCode());
           
            
            FacesContext.getCurrentInstance().addMessage(null, msg);
            
            String code = ((County) event.getObject()).getCountyCode();
            selectedCounty.setCountyCode(code);
             System.out.println("new Countycode5 is:"+ selectedCounty.getCountyCode());
            }
            
            public void onRowBranchSelect(SelectEvent event ){
                    msg = new FacesMessage("Branch Selected", ((Branch) event.getObject()).getBranchCode());

                    FacesContext.getCurrentInstance().addMessage(null, msg);
                    String code = ((Branch) event.getObject()).getBranchCode();
                    System.out.println("new Countycode5 is:"+ selectedCounty.getCountyCode());
            }
    
            
            /***
             * branchList
             * @return 
             */
            
            public ArrayList<Branch> getBranchList() { 
                    String query = "SELECT *  FROM branches ";
                    String code = selectedCounty.getCountyCode();
                    if(!code.isEmpty()){
                    
                       
                       query += " WHERE countycode = '"+ code.trim()  +"'";
                        query += ";";

                    }else{
                        query += ";";
                    
                    }

                            brList(query);
                   
                    return branchList; 
                
             } 
            public void brList(String query){
                    branchList = new ArrayList<Branch>();
                     try{
                                    DatabaseBean db = new DatabaseBean();
                                    Connection con = db.DBconnect();
                                    PreparedStatement stmt = con.prepareStatement(query);
                                    stmt.setFetchSize(100);
                                    ResultSet rs  = db.preparedState(stmt);
                                    while(rs.next()){
                                      branchList.add(new Branch(rs.getInt("id"),rs.getString("branchcode"), rs.getString("countycode"), rs.getString("branchname"),
                                              rs.getString("physicaladdress"),rs.getString("address"),rs.getString("town"),
                                              rs.getString("officeContact"),rs.getString("fax"),rs.getString("email"),rs.getString("status")));
                                    }
                                    rs.close();
                                    db.cleanup();
                                }catch(SQLException e){
                                            msg = new FacesMessage(FacesMessage.SEVERITY_FATAL, "Sql Error"," Please contact admin!");
                                        System.out.println("all Branch Data  error in SQLE !"+e);
                                }catch(Exception e ){
                                            msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Saving Error"," Fill in the missing fields!");
                                            System.out.println(" error in EXception !"+e);
                                }
                
            }
             public ArrayList<Teller> getTellerList(){
                 String query = "SELECT *  FROM teller ";
                    //String query = "SELECT *  FROM branches where countycode = '"+code.trim()+"' ;";// where countycode = '"+selectedCounty.countyCode.trim()+"' 
                       if(selectedBranch.getBranchCode().isEmpty()){
                             query += ";";
                            
                       }else{
                            query += " WHERE branchcode = '"+ selectedBranch.getBranchCode().trim()  +"'";
                            query += ";";
                       }
                         
                    tellerList = new ArrayList<Teller>();
                                            
                     try{
                                    DatabaseBean db = new DatabaseBean();
                                    Connection con = db.DBconnect();
                                    PreparedStatement stmt = con.prepareStatement(query);
                                    stmt.setFetchSize(100);
                                    ResultSet rs  = db.preparedState(stmt);
                                    while(rs.next()){
                                      tellerList.add(new Teller(rs.getInt("teller_id"),rs.getString("teller_name"), 
                                              rs.getString("branchcode"), rs.getBoolean("is_active")));
                                    }
                                    rs.close();
                                    db.cleanup();
                                }catch(SQLException e){
                                            msg = new FacesMessage(FacesMessage.SEVERITY_FATAL, "Sql Error"," Please contact admin!");
                                        System.out.println("all Tellar Data  error in SQLE !"+e);
                                }catch(Exception e ){
                                            msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Saving Error"," Fill in the missing fields!");
                                            System.out.println(" error in EXception !"+e);
                                }
                    
                    
                  return tellerList;
                
            }
            public int dbAction(String mysql){
                        int affected = -1;
                        try{
                                DatabaseBean db = new DatabaseBean();
                                affected = db.insert(mysql);
                                //ResultSet rs = db.query(mysql);
                                //while(rs.next()){
                                //        affected = rs.getInt("order_id");
                                //}
                                db.cleanup();
                        }catch(SQLException e){
                                System.out.println("SQL Error In BranchData: "+ e);
                        }catch(Exception e){
                                System.out.println("Exception Error In BranchData: "+ e);
                        }
                        return affected;
                }
            
            public void newCounty(){
                       
                        String mysql = "INSERT INTO county (countyCode, countyName) VALUES ("+ newCounty.getCountyCode()+", "+ newCounty+"');";
                         
                                FacesContext context = FacesContext.getCurrentInstance(); 
                                if(dbAction(mysql) > 0){
                                        context.addMessage(null, new FacesMessage("Bravo,","New County was created Successfully"));
                                } else {
                                        context.addMessage(null, new FacesMessage("Sorry,","System encountered a problem trying to create new County. Try again later"));
                                }
                                
                        
            }
            public void editCounty(ActionEvent actionevent){
                        String sql = "UPDATE county SET countyName = '"+ selectedCounty.countyName.trim() +"';";
                        dbAction(sql);
            }
            
            public void deleteCounty(ActionEvent actionevent){
                        String sql = "DELETE * from county WHERE countyCode = '"+ selectedCounty.countyCode.trim() +"';";
                        FacesContext context = FacesContext.getCurrentInstance(); 
                        if(dbAction(sql)>0){
                                context.addMessage(null, new FacesMessage("Bravo,"," County was deleted Successfully"));
                          } else {
                                        context.addMessage(null, new FacesMessage("Sorry,","System encountered a problem trying to delete County. Try again later"));
                          }
                         
            }
            
            public void addNewBranch(){
                       
                        String mysql = "INSERT INTO branches (branchcode, countycode,branchname,physicaladdress,address,town,officeContact,fax,email,status,createDate)"
                                + " VALUES ('"+ newBranch.getBranchCode()+"', '"+ selectedCounty.getCountyCode()+"', '"+ newBranch.getBranchName()+"',"
                                + " '"+ newBranch.getPhysicaladdress()+"', '"+ newBranch.getAddress()+"', '"+ newBranch.getTown()+"',"
                                + " '"+ newBranch.getOfficeContact()+"', '"+ newBranch.getFax()+"', '"+ newBranch.getEmail()+"', '"+ newBranch.getStatus()+"',now());";
                           
                                FacesContext context = FacesContext.getCurrentInstance(); 
                                if((!newBranch.getBranchCode().equals(""))  || (!newBranch.getBranchName().equals("")) || (!newBranch.getPhysicaladdress().equals(""))|| (!selectedBranch.getCountyCode().equals(""))
                                        || (!newBranch.getOfficeContact().equals("")) ){
                                           
                                           System.out.println(mysql);
                                           if(dbAction(mysql) > 0){
                                                 context.addMessage(null, new FacesMessage("Bravo,","New Branch was created Successfully"));
                                        } else {
                                                context.addMessage(null, new FacesMessage("Sorry,","System encountered a problem trying to create new Brach. Try again later"));
                                        } 
                                }else{
                                      
                                       context.addMessage(null, new FacesMessage("Sorry,","Fill in the missing field . Then try again")); 
                                }
                                
                                
                        
            }
            
            public void editBranch(ActionEvent actionevent){
                        String sql = "UPDATE branches SET branchcode = '"+ selectedBranch.getBranchCode().trim() +"', countycode = '"+ selectedCounty.getCountyCode().trim() +"', branchname = '"+ selectedBranch.getBranchName().trim() +"',"
                                + "physicaladdress = '"+ selectedBranch.getPhysicaladdress().trim() +"', address='"+ selectedBranch.getAddress().trim() +"',"
                                + " town = '"+ selectedBranch.getTown().trim() +"', officeContact = '"+ selectedBranch.getOfficeContact().trim() +"',"
                                + " fax = '"+ selectedBranch.getFax().trim() +"', email = '"+ selectedBranch.getEmail().trim() +"', status = '"+ selectedBranch.getStatus().trim() +"' where id = '"+ selectedBranch.getBranchId() +"'  ;";
                        dbAction(sql);
                        
                          
            }
            
        public SelectItem[] getManager() {
           
         
                String query = "SELECT (concat( stackholder.first_Name,' ',stackholder.middle_Name,' ',stackholder.last_Name) As managername"
                        + " ,vw_role.role_id As roleid FROM stackholder  JOIN vw_role ON (stackholder.email = vw_role.user_name) WHERE  vw_role.isManager = true  ;";
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
                                tMap.put(rs.getString("roleid").toString(), rs.getString("managername").toString());
                        }
                        rs.close();
                        db.cleanup();
                }catch(SQLException e){
                        System.out.println("Namager Name  - Combobox SQLException: "+ e);
                }catch(Exception e){
                        System.out.println("Namagers - Combobox  Exception: "+ e);
                        System.err.println(e);
                }
                if (list.isEmpty()) {
                        tMap.put("--".toString(), "<<No manager >>".toString());
                        list.add("---".toString());
                }
                manager = new SelectItem[list.size()];
                int i = 0;

                Set set = tMap.entrySet();
                Iterator ite = set.iterator();
                while(ite.hasNext()) { 
                        Map.Entry me = (Map.Entry)ite.next(); 
                        manager[i++] = new SelectItem(me.getKey().toString(), me.getValue().toString());
                } 
                return manager;
        }
        
        public void assignBranchManager(){
                
                       
                        String mysql = "INSERT INTO branch_manager (manager_id, branchcode,is_active,created_Date)"
                                + " VALUES ('"+ selectedBranchManager.getManagerId()+"', '"+ selectedBranch.getBranchCode()+"', true,now());";
                           
                                FacesContext context = FacesContext.getCurrentInstance(); 
                                if((selectedBranchManager.getManagerId() != 0)  || (!selectedBranch.getBranchCode().equals("")) ){
                                       if(checkIfManagerExist()< 1){
                                            System.out.println(mysql);
                                           if(dbAction(mysql) > 0){
                                                 context.addMessage(null, new FacesMessage("Bravo,","New Branch Manager was created Successfully"));
                                            } else {
                                                    context.addMessage(null, new FacesMessage("Sorry,","System encountered a problem trying to create new Brach. Try again later"));
                                            }
                                       }
                                }else{
                                      
                                       context.addMessage(null, new FacesMessage("Sorry,","Fill in the missing field . Then try again")); 
                                }
                                
                                
                        
            }
        public int checkIfManagerExist(){
                
                String query = "SELECT *  FROM branch_manager WHERE manager_id =  '"+ selectedBranchManager.getManagerId()+"'"
                        + " AND branchcode = '"+ selectedBranch.getBranchCode()+"'  ";
                int x = 0;
                try{
                        DatabaseBean db = new DatabaseBean();
                        Connection con = db.DBconnect();
                        PreparedStatement stmt = con.prepareStatement(query);
                        ResultSet rs=db.preparedState(stmt);
                        
                        while(rs.next()){
                           x++;
                            
                        }
                        rs.close();
                        db.cleanup();
                }catch(SQLException e){
                        System.out.println("Namager Name  - Combobox SQLException: "+ e);
                }catch(Exception e){
                        System.out.println("Namagers - Combobox  Exception: "+ e);
                        System.err.println(e);
                }
                return x;
        }
        
        public ArrayList<BranchManager> getBranchManagerList(){
                 String query = "SELECT concat( stackholder.first_Name,'  ',stackholder.middle_Name,'  ',stackholder.last_Name) As fullnames, "
                         + " concat(stackholder.contact1,' / ',stackholder.contact2)AS contacts   "
                         + "FROM stackholder  JOIN vw_role ON (stackholder.email = vw_role.user_name)  JOIN branch_manager ON"
                         + " (vw_role.role_id = branch_manager.manager_id  )  ";
                    
                       if(selectedBranch.getBranchCode().isEmpty()){
                             query += ";";
                            
                       }else{
                            query += " WHERE  vw_role.role_id  = branch_manager.manager_id AND branch_manager.branchcode = '"+selectedBranch.getBranchCode().trim()+"'";
                            query += ";";
                       }
                         
                            branchManagerList = new ArrayList<BranchManager>();
                                            
                     try{
                                    DatabaseBean db = new DatabaseBean();
                                    Connection con = db.DBconnect();
                                    PreparedStatement stmt = con.prepareStatement(query);
                                    stmt.setFetchSize(100);
                                    ResultSet rs  = db.preparedState(stmt);
                                    while(rs.next()){
                                     /*branchManagerList.add(new BranchManager(rs.getString("first_Name"),rs.getString("middle_Name"), 
                                            rs.getString("last_Name"), rs.getString("contacts")));*/
                                     branchManagerList.add(new BranchManager(rs.getString("fullnames"), rs.getString("contacts")));
                                     
                                    }
                                    rs.close();
                                    db.cleanup();
                                }catch(SQLException e){
                                            msg = new FacesMessage(FacesMessage.SEVERITY_FATAL, "Sql Error"," Please contact admin!");
                                        System.out.println("Branch Managers List  error in SQLE !"+e);
                                }catch(Exception e ){
                                            msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Saving Error"," Fill in the missing fields!");
                                            System.out.println(" error in EXception !"+e);
                                }
                    
                    
                  return branchManagerList;
                
            }
        
    
}
