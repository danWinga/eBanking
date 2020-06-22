/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.tracking.shared.StudentClassAssigment;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
public class AllStudentBean implements Serializable {
        private TreeNode root;
        private TreeNode selectedNode;
        private StudentClass studenetClass = new StudentClass();
            
        FacesContext facesContext = FacesContext.getCurrentInstance();
        HttpSession httpSession = (HttpSession)facesContext.getExternalContext().getSession(true);
        
        public AllStudentBean(){
            showAllstudent();
        }
        
        public TreeNode getRoot(){return root;}
        public void setRoot(TreeNode root){this.root = root;}
    
        public TreeNode getSelectedNode(){return selectedNode;}
        public void setSelectedNode(TreeNode selectedNode){
                this.selectedNode = selectedNode;
        }
        public StudentClass getStudentClass(){
                return studenetClass;
        }
        public void setStudentClass(StudentClass studentClass){
                    this.studenetClass = studentClass;
        }
        
        public TreeNode showAllstudent(){
            
                root = new DefaultTreeNode(new StudentClass("Class","tream","student name", "contacts", "-","-","-","-", "period","academicyear"), null);
                TreeNode node = new DefaultTreeNode();
                String query = "SELECT * FROM vw_studentClassAssign   ORDER BY class_code;";
                
                try{
                            DatabaseBean db = new DatabaseBean();
                            Connection con =  db.DBconnect();
                            PreparedStatement stmt = con.prepareStatement(query);
                            ResultSet rs = db.preparedState(stmt);
                            System.out.println("query 1");
                            while(rs.next()){
                                  int classid  = rs.getInt("id");
                                  String classCode = rs.getString("class_code");
                                  String sql1  = "SELECT class_name from classes WHERE class_code = '"+classCode.trim()+"'  ;";
                                  ResultSet rs1  = db.query(sql1);
                                  System.out.println("sql1:"+ rs1);
                                  String classname = "";
                                  
                                  while(rs1.next()){
                                            classname = rs1.getString("class_name");
                                            }
                                        rs1.close();
                                            
                                  node = new DefaultTreeNode(new StudentClass(rs.getString("class_code"),classname,"-", "-", "-","-","-","-", "-","-"), root);
                                  System.out.println("Class Name:"+ classname); 
                                  // loop for stream
                                  
                                  String sql = "SELECT  * from  vw_studentClassAssign   where class_code ='"+classCode.trim()+"' ;";
                                  ResultSet rs2 = db.query(sql);
                                                
                                  TreeNode node2 = new DefaultTreeNode();
                                  System.out.println("sql");
                                  int strmid  =0;
                                   String  streamcode = "";
                                  while(rs2.next()){
                                            strmid = rs2.getInt("id");
                                            streamcode = rs2.getString("stream_code");
                                            node2 = new DefaultTreeNode(new StudentClass(rs2.getString("stream_code"),rs2.getString("stream_name"),"-", "-", "-","-","-","-", "-","-"), node);
                                            System.out.println("Stream  Name:"+rs2.getString("stream_name"));
                                            System.out.println("rs2 stream_code:"+ streamcode);
                                                      
                                            String sql3 = "SELECT  * from  vw_studentClassAssign   where stream_code ='"+streamcode.trim()+"' ;";
                                            ResultSet rs3= db.query(sql3);
                                            TreeNode node3 = new DefaultTreeNode();
                                            System.out.println("sql3");
                                            int studid = 0;
                                            while(rs3.next()){
                                                        studid = rs3.getInt("id");
                                                        node3 = new DefaultTreeNode(new StudentClass(rs3.getString("assgn_Code"),rs3.getString("student_id"),rs3.getString("first_Name"), rs3.getString("middle_name"), rs3.getString("last_Name"),rs3.getString("gender"),rs3.getString("contact1"),rs3.getString("email"),rs3.getString("acd_Name"),rs3.getString("status")), node2);
                                                         System.out.println("Student Names:"+rs3.getString("first_Name")+" "+ rs3.getString("middle_name"));
                                                       }
                                            
                                            rs3.close();
                                            
                                    }
                                  
                                  rs2.close();
                            }
                            
                            rs.close();
                            db.cleanup();
                            
                }catch(SQLException e){
                    System.out.println(" SQLE error:"+ e);
                }catch(Exception e){
                    System.out.println("Ex error:"+e);
                }
                return root;
        }
        public void onNodeSelect(NodeSelectEvent event) {
        System.out.println("selected "+ studenetClass.getStudentId());
        
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Selected",event.getTreeNode().getData().toString());
        FacesContext.getCurrentInstance().addMessage(null, message);
    }
            
            
    
}
