/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.tracking.shared.subject.subjectToClass;

import java.io.Serializable;
import java.sql.BatchUpdateException;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Random;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import org.primefaces.event.TransferEvent;
import org.primefaces.model.DualListModel;
import org.tracking.server.database.DatabaseBean;

/**
 *
 * @author root
 */
@ManagedBean
@SessionScoped
public class SubjectClassBean implements Serializable {
        private SubjectClass newSubclass = new SubjectClass();
        private SubjectClass selected  = new SubjectClass();
        private  boolean isValid;
        private DualListModel<SubjectClass> subjclassList; 
        private ArrayList<SubjectClass>  unasgnedSubjList;
        private List<SubjectClass> sourcecont;
        private List<SubjectClass> targetcont;
        
        FacesContext facesContext = FacesContext.getCurrentInstance();
        HttpSession httpSession = (HttpSession)facesContext.getExternalContext().getSession(true);
        FacesMessage msg = null;
        /***
         * 
         * @param classcode
         * @param subject
         * @return isValid
         */
        public boolean getIsValid(String classcode, String subject){
                isValid =false;
                String tempData = "";
                String sql = "SELECT * from subject_class where  class_code == '"+classcode.trim()+"' AND subj_code == '"+subject.trim()+"' ;";
                try{
                        DatabaseBean db = new DatabaseBean();
                        Connection con  = db.DBconnect();
                        PreparedStatement stmt = con.prepareStatement(sql);
                        ResultSet rs = db.preparedState(stmt);
                        
                        while(rs.next()){
                            tempData = rs.getString("subj_code");

                    }
                rs.close();
                db.cleanup();
                }catch(SQLException e){
                    System.out.println("SQL "+ e);
                }catch(Exception e ){
                    System.out.println("Exception "+e);
                }
                
            if(tempData.isEmpty()){
                return isValid = true;
             }
            return isValid;
        }
        
        public  void generateSubclassCode(){
                Random newrandom = new Random();
                int random = newrandom.nextInt(1000)+8880;
                System.out.println("newNo:"+ random);
                String randomCode = String.valueOf("sujcl/"+random);
                newSubclass.setSubClassCode(randomCode);
        }
        /***
         * refresh un assigned Subject list for each  class selected
         * 
         */
        public void prepareList(){
            System.out.println(" preparing the list........");
            getUnasgnedSubjList();
            System.out.println(" ....... the list is prepared");
        }
        
        public ArrayList<SubjectClass> getUnasgnedSubjList(){
                System.out.println("reading data.........");
                unasgnedSubjList  = new ArrayList<SubjectClass>();
                String query = "SELECT * FROM subjects  where subj_code "
                 + " NOT IN (  SELECT subj_code FROM   subject_class where class_code = '"+newSubclass.getClassCode()+"' );";
                try{
                         DatabaseBean db = new DatabaseBean();
                         Connection con = db.DBconnect();
                         PreparedStatement stmt = con.prepareStatement(query);
                         stmt.setFetchSize(100);
                         ResultSet rs  = db.preparedState(stmt);
                             while(rs.next()){
                                        //newSubclass.setSubjCode(rs.getString("subj_code"));
                                        System.out.println(" record found rs :"+ rs.getString("subj_code")+"--"+rs.getString("subject_name"));
                                        unasgnedSubjList .add(new SubjectClass(rs.getString("subj_code"),rs.getString("subject_name")));
                                        
                    
                            }
                         rs.close();
                         db.cleanup();
                    }catch(SQLException e){
                            msg = new FacesMessage(FacesMessage.SEVERITY_FATAL, "Sql Error"," Please contact admin!");
                            System.out.println("unassigned Subject to class  error in SQLE !"+e);
                    }catch(Exception e ){
                            msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Saving Error"," Fill in the missing fields!");
                            System.out.println("unassigned Subject  error in EXception !"+e);
                    }
        
                return unasgnedSubjList;
                
        }
        public DualListModel<SubjectClass> getSubjclassList(){
                sourcecont = getUnasgnedSubjList(); 
                targetcont = new ArrayList<SubjectClass>();  
                subjclassList = new DualListModel<SubjectClass>(sourcecont, targetcont);
                return subjclassList;
        }
        public void onTransfer(TransferEvent event) {
                StringBuilder builder = new StringBuilder();
                for(Object item : event.getItems()) {
                    builder.append((item).toString()).append("<br />");
                }
         
            FacesMessage msg = new FacesMessage();
            msg.setSeverity(FacesMessage.SEVERITY_INFO);
            msg.setSummary("Items Transferred");
            msg.setDetail(builder.toString());
            System.out.println("Items Transferred:"+builder.toString());
         
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }
        public void onTransfer1(TransferEvent event) {
            StringBuilder builder = new StringBuilder();
             for(Object item : event.getItems()) {
                builder.append(((SubjectClass) item).getSubjCode()).append("<br />");
             }
         
            FacesMessage msg = new FacesMessage();
            msg.setSeverity(FacesMessage.SEVERITY_INFO);
            msg.setSummary("Items Transferred");
            msg.setDetail(builder.toString());
         
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }
        
        public void addSubjectsToClass(){
            System.out.println(" selected class:"+ newSubclass.getClassCode());
            if(!newSubclass.getClassCode().isEmpty()){
                DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
	   
                Calendar currenttime = Calendar.getInstance();
                Date sqldate = new Date((currenttime.getTime()).getTime()); 
            
           
                String sql = "INSERT INTO subject_class ( subclass_code, class_code,subj_code,created_date)"
                + " VALUES (?, ?, ?,?)"; 
                try{
                    DatabaseBean db = new DatabaseBean();
                    Connection con = db.DBconnect();
                    con.setAutoCommit(false);
                    DatabaseMetaData dbmData = con.getMetaData();
                    if(dbmData.supportsBatchUpdates()){
                        
                         PreparedStatement stmt = con.prepareStatement(sql);
                                for(Object subject :subjclassList.getTarget() ){
                                    
                                        if(!subject.toString().isEmpty()){
                                            System.out.println("doing batch data......");
                                            generateSubclassCode();
                                            newSubclass.setSubjCode(subject.toString());
                                            stmt.setString(1, newSubclass.getSubClassCode());
                                            stmt.setString(2, newSubclass.getClassCode());
                                            stmt.setString(3, newSubclass.getSubjCode());
                                            stmt.setDate(4,sqldate);
                                            stmt.addBatch();
                                         
                                        }else{
                                             System.out.println("batch data empty......"); 
                                        }
                                   
                                    System.out.println("sql stmt : "+stmt);
                                }
                            int []c1 = stmt.executeBatch();
                            
                            con.commit();
                            db.cleanup();
                    }
                }catch(BatchUpdateException e){
                        System.out.println("Error 1: "+ e.getNextException());
                }catch(Exception e){
                        System.out.println("Error 2: "+ e);
                }
            }else{
                System.out.println("Kindly select a class to add subject to , then try again!");
                msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Sorry !"," Kindly select a class to add subject to , then try again!");
            }        
        
        }
        public void setSubjclassList(DualListModel<SubjectClass> subjclassList) { this.subjclassList = subjclassList; }
        
    
        public SubjectClass getNewSubclass(){return newSubclass;}
        public void setNewSubclass(SubjectClass newSubclass){this.newSubclass = newSubclass;}
        
        public SubjectClass getSelected(){return selected;}
        public void setSelected(SubjectClass selected){this.selected = selected;}
    
}
