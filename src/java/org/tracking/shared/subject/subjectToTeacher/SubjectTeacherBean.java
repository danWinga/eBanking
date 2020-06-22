/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.tracking.shared.subject.subjectToTeacher;

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
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.servlet.http.HttpSession;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.DualListModel;
import org.primefaces.model.LazyDataModel;
import org.tracking.server.database.DatabaseBean;
import org.tracking.shared.stakeholders.StakeholderFilterView;
import org.tracking.shared.stakeholders.Stakeholders;
import org.tracking.shared.stakeholders.StakeholdersBean;
import org.tracking.shared.subject.subjectToClass.SubjectClass;

/**
 *
 * @author root
 */
@ManagedBean(name ="subjectTeacherBean")
@SessionScoped
public class SubjectTeacherBean implements Serializable{
    
        private Subjectteacher newSubjectteacher = new Subjectteacher();
        private Subjectteacher selected = new Subjectteacher();
        private  boolean isValid;
        private DualListModel<SubjectClass> subjteacherList; 
        private List<Subjectteacher> sourceStream,sourceSubject;
        private List<Subjectteacher> targetStream,targetSubject;
        private ArrayList<Stakeholders> teachersList;
        private ArrayList<Stakeholders> holdersList;
        private List<Stakeholders> teachers;
        private Stakeholders selectedTeacher = new Stakeholders();
        private Stakeholders selectedStake = new Stakeholders();
        private LazyDataModel<Stakeholders> subjTModel;
        public SelectItem[] subjects;
        public SelectItem[] classes;
        public SelectItem[] classStream;
        public SelectItem[] schoolteachers;
    
        
        FacesContext facesContext = FacesContext.getCurrentInstance();
        HttpSession httpSession = (HttpSession)facesContext.getExternalContext().getSession(true);
        FacesMessage msg = null;
        public SubjectTeacherBean(){
            teachers = getTeachersList();
        }
        public LazyDataModel <Stakeholders>getSubjTModel(){
                subjTModel = new SujectTDatamodel(getTeachers());
                return subjTModel;
        }
        
        public Subjectteacher getNewSubjectteacher(){
            return newSubjectteacher;
        }
        public void setNewSubjectteacher(Subjectteacher newSubjectteacher){
            this.newSubjectteacher = newSubjectteacher;
        }
        
        
        public Subjectteacher getSelected(){
            return selected;
        }
        public void setSelected(Subjectteacher selected){
            this.selected = selected;
        }
        public void setIsValid(boolean isValid){ 
            this.isValid = isValid;
        }
        public List<Stakeholders> getTeachers(){
            return teachers;
        }
        public void setTeachers (List<Stakeholders> teachers ){
            this.teachers = teachers;
        }
        public Stakeholders getSelectedStake(){
            return selectedStake;
        }
        public void setSelectedStake(Stakeholders selectedStake){
            this.selectedStake  = selectedStake;
        }
        
        public Stakeholders getSelectedTeacher(){
            return selectedTeacher;
        }
        public void setSelectedTeacher(Stakeholders selectedTeacher){
            this.selectedTeacher = selectedTeacher;
        }
        
        public boolean getIsValid(String classcode, String subject){
                isValid =false;
                String tempData = "";
                String sql = "SELECT * from vw_subjectClass where  class_code == '"+classcode.trim()+"' AND subj_code == '"+subject.trim()+"' ;";
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
        
        public  void generateAssgnCode(){
                Random newrandom = new Random();
                int random = newrandom.nextInt(1000)+8880;
                System.out.println("newNo:"+ random);
                String randomCode = String.valueOf("sujcl/"+random);
                newSubjectteacher.setAssgnCode(randomCode);
        }
        /***
         * retrieve List of  Teachers 
         * @return teachersList
         */
        public ArrayList<Stakeholders> getTeachersList(){
                teachersList = new ArrayList<Stakeholders>();
                String query = "SELECT * FROM stackholder WHERE isTeacher = true;";
                System.out.println(" collecting Students data>..............................!");
                
                try{
                        DatabaseBean db = new DatabaseBean();
                        Connection conn = db.DBconnect();
                        PreparedStatement stmt = conn.prepareStatement(query);
                        stmt.setFetchSize(100);
                        ResultSet rs = db.preparedState(stmt);
                     
                        while(rs.next()){
                            teachersList.add(new Stakeholders(rs.getString("stackholder_Code"),
                            rs.getString("first_Name"),rs.getString("middle_Name"),rs.getString("last_Name")));
                        }        
                    rs.close();
                    db.cleanup();
            
                }catch(SQLException e){
                    msg = new FacesMessage(FacesMessage.SEVERITY_FATAL, "Record reading  Error"," Please contact admin!");
                    System.out.println("Teachers  error in SQLE !"+e);
                }catch(Exception e){
                    msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Saving Error"," Fill in the missing fields!");
                    System.out.println("Teachers  error in EXception !"+e);
                }
            System.out.println("Teachers  data collected .......ok!");
            
            return teachersList;
        }
        
        /**
         * test record found
         */
        public void recordf(){
            //setTestData( convert.getAsString(facesContext, null, subjects));
            System.out.println("record Stakeholder:"+ getTestData() );
        }
        public void AvailableSubjects(){
            
        }
        /*** 
         * list all available subject allocated to the 
         * selected class
         * @return 
         */
        public SelectItem[] getSubjects() {
                String query = "SELECT subclass_code,subject_name FROM vw_subjectclass where class_code = '"+newSubjectteacher.getClassCode()+"';";

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
                                tMap.put(rs.getString("subclass_code").toString(), rs.getString("subject_name").toString());
                        }
                        rs.close();
                        db.cleanup();
                }catch(SQLException e){
                        System.out.println("subject to teacher  - Combobox SQLException: "+ e);
                }catch(Exception e){
                        System.out.println("subject to teacher - Combobox  Exception: "+ e);
                        System.err.println(e);
                }
                if (list.isEmpty()) {
                        tMap.put("--".toString(), "<<No subject  >>".toString());
                        list.add("---".toString());
                }
                subjects = new SelectItem[list.size()];
                int i = 0;

                Set set = tMap.entrySet();
                Iterator ite = set.iterator();
                while(ite.hasNext()) { 
                        Map.Entry me = (Map.Entry)ite.next(); 
                        subjects[i++] = new SelectItem(me.getKey().toString(), me.getValue().toString());
                } 
                return subjects;
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
       * Getting all class streams of a class
       * @return  classStream
       */
      public SelectItem[] getClassStream() {
                String query = "SELECT stream_code, stream_name FROM streams WHERE class_code = '"+newSubjectteacher.getClassCode()+"';";

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
        /**
         * get SubclassCode for any selected Subject
         */
        public void geSubcode(){
          newSubjectteacher.setSubclassCode("");
          String query = "SELECT * FROM subject_class WHERE subj_code = '"+newSubjectteacher.getSubjectCode()+"' LIMIT 1;";
                System.out.println(" collecting Subject Code>..............................!");
                
                try{
                        DatabaseBean db = new DatabaseBean();
                        Connection conn = db.DBconnect();
                        PreparedStatement stmt = conn.prepareStatement(query);
                        stmt.setFetchSize(100);
                        ResultSet rs = db.preparedState(stmt);
                     
                        while(rs.next()){
                            newSubjectteacher.setSubclassCode(rs.getString("subclass_code"));
                        }        
                    rs.close();
                    db.cleanup();
            
                }catch(SQLException e){
                    msg = new FacesMessage(FacesMessage.SEVERITY_FATAL, "Record reading  Error"," Please contact admin!");
                    System.out.println("Subclass code  error in SQLE !"+e);
                }catch(Exception e){
                    msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Saving Error"," Fill in the missing fields!");
                    System.out.println("Subclass code  error in EXception !"+e);
                }
          
      }
      /***
       * Getting all class streams of a class
       * @return  classStream
       */
      public SelectItem[] getSchoolTeachers() {
                String query = "SELECT * FROM stackholder WHERE isTeacher = true;";

                List list = new ArrayList();
                TreeMap <String, String>tMap = new TreeMap<String, String>();
                String teachersName = "";
                try{
                        DatabaseBean db = new DatabaseBean();
                        Connection con = db.DBconnect();
                        PreparedStatement stmt = con.prepareStatement(query);
                        ResultSet rs=db.preparedState(stmt);
                        int z = 0;
                        while(rs.next()){
                                list.add(z++);
                                teachersName = rs.getString("first_Name")+ " "+ rs.getString("middle_Name")+ " "+ rs.getString("last_Name"); 
                                tMap.put(rs.getString("stackholder_Code").toString(), teachersName);
                        }
                        rs.close();
                        db.cleanup();
                }catch(SQLException e){
                        System.out.println("Teachers code  - Combobox SQLException: "+ e);
                }catch(Exception e){
                        System.out.println("teachers code  - Combobox  Exception: "+ e);
                        System.err.println(e);
                }
                if (list.isEmpty()) {
                        tMap.put("--".toString(), "<<No Teacher  >>".toString());
                        list.add("---".toString());
                }
                schoolteachers = new SelectItem[list.size()];
                int i = 0;

                Set set = tMap.entrySet();
                Iterator ite = set.iterator();
                while(ite.hasNext()) { 
                        Map.Entry me = (Map.Entry)ite.next(); 
                        schoolteachers[i++] = new SelectItem(me.getKey().toString(), me.getValue().toString());
                } 
                return schoolteachers;
        }
      /***********************************************************************************************************/
      
      public void searchTeacherWithId(){
          System.out.println(" selected Teacher Code is:" + selectedTeacher.getStakeholderCode());
          selectedHolder(selectedTeacher.getStakeholderCode());
          System.out.println(" Teacher details:" + selectedStake.getStakeholderCode()+"\t Fname:"+selectedStake.getFirstName()+"\t LName: "+selectedStake.getLastName());
          
      }
      
     
      //@ManagedProperty("#{dtStakeholderFilterView}") 
      @ManagedProperty("#{stakeholdersBean}")
      private StakeholdersBean holder;
      
      @PostConstruct
        public void init() {
            holdersList = holder.getTeachertList();
        }
      
      public ArrayList<Stakeholders> getHolderList(){
          return holdersList;
      }
      
      public void setHolder(StakeholdersBean holder){
          this.holder = holder;
      }
      
      public Object selectedHolder(String str){
          for(Stakeholders tc : holdersList){
              if(tc.getStakeholderCode().equals(str))
                  return selectedStake= tc;
          }
          return null;
      }
      
      /***
       * Get current academic Term
       * @return newSubjectteacher.getTermCode()
       */
      public String getAcademicTerm(){
          newSubjectteacher.setTermCode("");
          String query = "SELECT term_code, term_name FROM academic_term where is_active = true LIMIT 1;";
          try{
                DatabaseBean db = new DatabaseBean();
                Connection conn = db.DBconnect();
                PreparedStatement stmt = conn.prepareStatement(query);
                stmt.setFetchSize(100);
                ResultSet rs = db.preparedState(stmt);
                while(rs.next()){
                    newSubjectteacher.setTermCode(rs.getString("term_code"));
                }
              
          }catch(SQLException e){
              System.out.println("Sql exception error"+e);
          }catch(Exception e){
              System.out.println("Exception error "+e);
          }
          
          return newSubjectteacher.getTermCode();
          
      }
      public void onRowSelect(SelectEvent event ){
            FacesMessage msg = new FacesMessage("Teacher Selected", ((Stakeholders) event.getObject()).getStakeholderCode());
             FacesContext.getCurrentInstance().addMessage(null, msg);
      }
      
      private String testData;
      public String getTestData(){return testData;}
      public void setTestData(String testData){this.testData = testData;}
      
      @ManagedProperty("#{teacherConverter}")
      private TeacherConverter convert;
      
      
      public  TeacherConverter getConvert(){
          return convert;
      }
      
      public void setConvert(TeacherConverter  convert){
          this.convert = convert;
      }
      
    
    
}
