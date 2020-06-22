/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.tracking.navigation;

import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 *
 * @author admin
 */
@ManagedBean(name = "navigation")
@SessionScoped
public class NavigationBean implements Serializable {
   
    private String pageName = "/product/productList";//"/login/login"..."/admin/login"
    private String mainPage = "defaultpage";
    private String urlselected = "/login/login";
    private String user = "not logged in";
    private String  centerUrl;
    private String sourceCode = "";
  
    
    private String trackingMenuPage = "";
    private String hrMenuPage = "";
    private String payrollMenuPage = "  ";
    
    
    private String templateLoader ="/WEB-INF/logintemplate";
    
        FacesContext facesContext = FacesContext.getCurrentInstance();
        HttpSession httpSession = (HttpSession)facesContext.getExternalContext().getSession(true);
        FacesContext context; 
        HttpServletRequest request; 
        //String sessionusername = httpSession.getAttribute("username").toString();

    public void doNav() {
        String str = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("test");
        this.pageName = str;
    }
    /*
     * user  centerpanel initials
     */
    public String getCenterUrl(){
        if(getUrlselected().equals("licence") ){
            centerUrl="/business/main";
          }
        
        else if (getUrlselected().equals("parking")){
            centerUrl = "/parking/parking";
        }
        else if (getUrlselected().equals("admin")){
            centerUrl = "/dashboard/welcomePrimefaces";
        }
        else if (getUrlselected().equals("market")){
            centerUrl = "/marketrevenue/marketrevenue";
        }
        else if (getUrlselected().equals("property")){
            centerUrl = "/property/newproperty";
        }
        else if (getUrlselected().equals("advertisement")){
            centerUrl = "/advertisement/main";
            
        }
        System.out.println("center url "+ centerUrl);
        return centerUrl;
    }
    public void setCenterUrl(String centerUrl){  this.centerUrl = centerUrl;}
    /*
     * get the correct centerpanel url
     */
   
    public String getUrlselected(){
            if((httpSession.getAttribute("mainPage")!=null)&&  httpSession.getAttribute("loggedin") == "yes"){
                urlselected= httpSession.getAttribute("mainPage").toString();
                System.out.println("urlselected is :" + urlselected+ "\t logged in :" + getUser() );
                //update(); 

               return urlselected.toLowerCase().trim();

            }else {
                //update(); 
                return urlselected = "/login/login";
            }
     
    }
    public String getUser(){
        if(httpSession.getAttribute("username")!= null){
          user=  httpSession.getAttribute("username").toString();
          return user;
         }else {
            return user;
        }       
    }
    /*
    public void update() {
        RequestContext.getCurrentInstance().update("main1");
         RequestContext.getCurrentInstance().execute("main1");
        RequestContext context = RequestContext.getCurrentInstance();
           context.update("main1");
    }
    */
    public void setUser(String user){this.user= user.trim();}
    public void setUrlselected(String urlselected){this.urlselected = urlselected.trim();}
    public String getMainPage(){ return mainPage;   }
    public void setMainPage(String mainPage){this.mainPage = mainPage;}

    public String getPageName() { return pageName; }
    public void setPageName(String pageName) { this.pageName = pageName; }
    
    public String getTemplateLoader(){return templateLoader;}
    public void setTemplateLoader(String temp){this.templateLoader = temp.trim();}
    
    public String getSourceCode(){
        return sourceCode;
    }
    public void setSourceCode(String sourcecode){
        this.sourceCode = sourcecode.trim();
    }
    
    /**
     * *************left Menu page content ************************
     * @return trackingMenuPage 
     */
    public String getTrackingMenuPage(){
        return trackingMenuPage;
    }
    
    public void setTrackingMenuPage(String trackingMenuPage){
        this.trackingMenuPage = trackingMenuPage;
    }
   
    
    public String getHrMenuPage(){
        return hrMenuPage;
    }
    
    public void setHrMenuPage(String hrMenuPage){
        this.hrMenuPage = hrMenuPage;
    }
    
    public String getPayrollMenuPage(){
        return payrollMenuPage;
    }
    
    public void setPayrollMenuPage(String payrollMenuPage){
        this.payrollMenuPage = payrollMenuPage;
    }
    
    
            
            
}
