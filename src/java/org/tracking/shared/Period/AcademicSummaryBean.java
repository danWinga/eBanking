/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.tracking.shared.Period;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import org.primefaces.event.NodeSelectEvent;
import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;
import org.tracking.server.database.DatabaseBean;

/**
 *
 * @author root
 */
@ManagedBean
@SessionScoped
public class AcademicSummaryBean implements Serializable {
    private TreeNode root;
    private TreeNode selectedNode;
    private AcademicYear selectedYear ;
    public AcademicTerm selectedTerm ;
    private Academic selectedAcademic ;
    private String accCode, strprd;
     SimpleDateFormat formatDate;
    
    
    
    FacesContext facesContext = FacesContext.getCurrentInstance();
    HttpSession httpSession = (HttpSession)facesContext.getExternalContext().getSession(true);
    
    
    public AcademicSummaryBean(){
       
        createDocuments();
        selectedAcademic= new Academic(null,null,null,true); 
    }
    
    public TreeNode getRoot(){return root;}
    public void setRoot(TreeNode root){this.root = root;}
    
    public TreeNode getSelectedNode(){return selectedNode;}
    public void setSelectedNode(TreeNode selectedNode){
    this.selectedNode = selectedNode;}
    
    public String getAccCode(){return accCode;}
    public void setAccCode(String acccode){this.accCode = acccode;}
    
    public AcademicYear getSelectedYear(){return selectedYear;}
    public void setSelectedYear(AcademicYear selectedYear){
        this.selectedYear = selectedYear;
    }
    public AcademicTerm getSelectedTerm(){return selectedTerm;}
    public void setSelectedTerm(AcademicTerm selectedTerm){this.selectedTerm = selectedTerm;}
    
    public Academic getSelectedAcademic(){return selectedAcademic;}
    public void setSelectedAcademic(Academic selectedAcademic) {
        this.selectedAcademic = selectedAcademic;
    }
    
    public String getStrprd(Date st1,Date st2){
        formatDate = new SimpleDateFormat("dd/MM/yyyy");
        String strdt = formatDate.format(st1);
        String strend=formatDate.format(st2);
        String strperiod = (strdt +"-"+ strend);
        return strprd = strperiod;
    }
    
    
    
    public TreeNode createDocuments(){
        
        root = new DefaultTreeNode(new Academic("Files", "Academic Name", "Period",false ), null);
        TreeNode[] node = new DefaultTreeNode[50000];
        String query = "SELECT  * from  academic_year ;";
        String strPeriod = "";
        try{
            DatabaseBean db = new DatabaseBean();
            Connection con =  db.DBconnect();
            PreparedStatement stmt = con.prepareStatement(query);
            ResultSet rs1 = db.preparedState(stmt);
            while(rs1.next()){
                int acadyrid = rs1.getInt("id");
                String code = rs1.getString("acd_Code");
                strPeriod = (rs1.getString("acd_Code") +"-"+ rs1.getString("acd_Code"));
                node[acadyrid] = new DefaultTreeNode(new Academic(rs1.getString("acd_Code"),rs1.getString("acd_Name"), strPeriod, rs1.getBoolean("is_active")), root);
                System.out.println("term period:"+strPeriod); 
                String sql = "SELECT  * from  academic_term  where acd_code ='"+code.trim()+"' ;";
                ResultSet rs2 = db.query(sql);
                TreeNode[] node2 = new DefaultTreeNode[50000];
                    while(rs2.next()){
				int actid = rs2.getInt("id");
                                String trmPeriod = rs2.getDate("start_Date")+" - "+rs2.getDate("start_Date") ;
				node2[actid] = new DefaultTreeNode(new Academic(rs2.getString("term_Code"),rs2.getString("term_Name"), trmPeriod,rs2.getBoolean("is_Active")), node[acadyrid]);
                               System.out.println("term period:"+trmPeriod);                                  
			}
                    rs2.close();
            }
            rs1.close();
            db.cleanup();
        }catch(SQLException e){
            System.out.println("SQLE"+ e);
        }catch(Exception e ){
            System.out.println("Excception"+ e);
        }
        return root;
    }
    
    public void displaySelectedSingle() {
        if(selectedNode != null) {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Selected",((Academic) selectedNode.getData()).getName());
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
    }
    public void onNodeSelect(NodeSelectEvent event) {
        System.out.println("selected "+ selectedAcademic.getName());
        
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Selected",event.getTreeNode().getData().toString());
        FacesContext.getCurrentInstance().addMessage(null, message);
    }
     
   
}
