/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.tracking.login;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.primefaces.context.RequestContext;
import org.tracking.server.database.DatabaseBean;
import org.tracking.shared.users.Users;

/**
 *
 * @author root
 */
@ManagedBean
@SessionScoped
public class LoginBean implements Serializable{
    private Users selectedLogin;
    private ArrayList<Users>loginList;
    private String user, password ;
    private String  fullName,firstName,middleName,lastName,branchCode;
    public boolean isloggedin;
    private int roleId, userId;

    public LoginBean() {
        
        facesContext = FacesContext.getCurrentInstance();
        httpSession = (HttpSession)facesContext.getExternalContext().getSession(true);
        context = FacesContext.getCurrentInstance();
        request = (HttpServletRequest) context.getExternalContext().getRequest(); 
    }
    
    @PostConstruct
    
    public void init(){
      this.selectedLogin = new Users ();  
    }

    
     FacesContext facesContext;
     HttpSession httpSession;
        
     FacesContext context; 
     HttpServletRequest request; 
    
   
    FacesMessage msg = null;
    public Users getSelectedLogin(){
        return selectedLogin;
    }
    
    public void  setSelectedLogin(Users selectedLogin){
        this.selectedLogin = selectedLogin;
    }
    
        public String getUser(){return user;}
        public void setUser(String user){this.user = user.trim();}

        public String  getPassword(){return password;}
        public void setPassword(String str){this.password = str.trim();}

        public String getFullName(){ 
            fullName = getFirstName()+ ""+ getMiddleName()+""+ getLastName();
                 return fullName ;}
        //public void setFullName(String str){this.fullName = str;}

        public String getFirstName(){ return firstName;}
        public void setFirstName(String str){this.firstName = str;}
         
        public String getMiddleName(){ return middleName;}
        public void setMiddleName(String str){this.middleName = str;}
         
        public String getLastName(){ return lastName;}
        public void setLastName(String str){this.lastName = str;}
        
        public String getBranchCode(){ return branchCode;}
        public void setBranchCode(String str){this.branchCode = str;}
        
        public int getRoleId(){return roleId;}
        public void setRoleId(int id){this.roleId = id;}
        
        
    
        public boolean getIsloggedin(){

                if(httpSession.getAttribute("loggedin") !=null){
                        this.isloggedin = "yes".equals(httpSession.getAttribute("loggedin").toString())? true : false;
                }else{
                    this.isloggedin = false;
                }

                return isloggedin;
        }
        public void setIsloggedin(boolean isloggedin){
            this.isloggedin = isloggedin ;}

        public void checkUser(){

            int x =0;
            if(getUser() != null){
                 String query ="SELECT * FROM users WHERE user_name = '"+getUser()+"'"
                        + " AND currentpassword= '"+getPassword()+"'  LIMIT 1;";
                try{
                    DatabaseBean db = new DatabaseBean();
                    Connection conn = db.DBconnect();
                    PreparedStatement stmt = conn.prepareStatement(query);
                    ResultSet  rs = db.preparedState(stmt);
                    while(rs.next()){
                        selectedLogin.setId(rs.getInt("user_id"));
                        selectedLogin.setUserName(rs.getString("user_name"));
                        selectedLogin.setCurrentpassword(rs.getString("currentpassword"));
                        selectedLogin.setMainPage(rs.getString("mainPage"));

                        x++;
                    }
                        rs.close();
                        db.cleanup();

            }catch(SQLException e){
                System.out.println("error  1 sql:"+e);
            }catch(Exception e){
                System.out.println("error  2 exception:"+e);
            }
            if(x>0){
                    isloggedin= true;
                                httpSession.setAttribute("loggedin", "yes");
                    setIsloggedin(true);
                    startUserSession();
                    httpSession.setAttribute("userId", selectedLogin.getId());
                    httpSession.setAttribute("username", selectedLogin.getUserName());
                    httpSession.setAttribute("mainPage", selectedLogin.getMainPage());
                    httpSession.setAttribute("fullname", getFullName());
                    httpSession.setAttribute("roleid", getRoleId());
                    httpSession.setAttribute("branchcode", getBranchCode());
                    System.out.println("wait... loading modules..!");
                    System.out.println("Loggedin ..!"+selectedLogin.getUserName() );
                    System.out.println("main page  ..!"+selectedLogin.getMainPage());
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Login successful! ", ":welcome "+ selectedLogin.getUserName()));    
                    RequestContext.getCurrentInstance().update(":main1");
            }
            else{
                    isloggedin = false;                
                    httpSession.setAttribute("loggedin", "no");

                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Login Error", "Invalid credentials"));
                    System.out.println("error ! wrong username or password");
                }
             }
            //FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Sorry", "Invalid credentials"));

        }
        public void startUserSession(){
                String query = "SELECT stackholder.first_Name,stackholder.middle_Name,"
                        + "stackholder.last_Name, vw_role.role_id,vw_role.branchcode FROM stackholder JOIN vw_role ON "
                        + "(stackholder.email = vw_role.user_name) WHERE stackholder.email = '"+ selectedLogin.getUserName().trim()+"'";
                try{
                        DatabaseBean db = new DatabaseBean();
                        Connection conn = db.DBconnect();
                        PreparedStatement stmt = conn.prepareStatement(query);
                        ResultSet rs = db.preparedState(stmt);
                        while(rs.next()){
                            setFirstName(rs.getString("first_Name"));
                            setMiddleName(rs.getString("middle_Name"));
                            setLastName(rs.getString("last_Name"));
                            setRoleId(rs.getInt("role_id"));
                            setBranchCode(rs.getString("branchcode"));
                        }
                        rs.close();
                        db.cleanup();
                }catch(SQLException e){
                    
                }catch(Exception e){
                    
                }

        }

        public void logout() {
            System.out.println("System is logging out!........");
                    httpSession.setAttribute("loggedin", "no");
                    selectedLogin.setMainPage("/login/login");
                    selectedLogin.setUserName("");
                    HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
                    if (session != null) {
                            session.invalidate();
                            RequestContext.getCurrentInstance().update(":main1");
                            selectedLogin.setUserName("");
                            selectedLogin.setCurrentpassword("");
                    }
                    RequestContext.getCurrentInstance().update(":main1");


            }
        public void updatecontex() {
            System.out.println(".....context executed......");
            RequestContext.getCurrentInstance().update(":main1:loadpagecontent");
            // RequestContext.getCurrentInstance().execute("main1");
            //RequestContext context = RequestContext.getCurrentInstance();
               //context.update("main1");

        }
}
