/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.tracking.shared.StudentClassAssigment;

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
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.TreeMap;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.servlet.http.HttpSession;
import org.primefaces.event.TransferEvent;
import org.primefaces.event.UnselectEvent;
import org.primefaces.model.DualListModel;
import org.tracking.server.database.DatabaseBean;
import org.tracking.shared.stakeholders.Stakeholders;

/**
 *
 * @author root
 */
@ManagedBean
@SessionScoped
public class StudentClassBean implements Serializable {

    private static final long serialVersionUID = 1L;
    private DualListModel<StudentClass> studentsList; 
    private ArrayList<StudentClass> unassgnList;
    private List<StudentClass> sourcecont;
    private List<StudentClass> studentTarget;
    private ArrayList<StudentClass> allassgnList;
    private ArrayList<StudentPerStream> studentperStreams;
    private ArrayList<Stakeholders> studentList;
    private StudentClass newStudentClass = new StudentClass();
    private StudentPerStream  studentperstream = new StudentPerStream();
    private boolean isSelected;
    private String classCode;
    public SelectItem[] classes;
    public SelectItem[] classStream;
    
    
    FacesContext context = FacesContext.getCurrentInstance();
    HttpSession httpSession = (HttpSession)context.getExternalContext().getSession(true);
    FacesMessage msg = null;
    
    /*@PostConstruct
    public void init() {
            newStudentClass = new StudentClass();
                
    }*/
    public DualListModel<StudentClass> getStudentsList(){
        sourcecont = getUnassignedList(); //Get data from datatable arraylist
        studentTarget = new ArrayList<StudentClass>();  
        studentsList = new DualListModel<StudentClass>(sourcecont, studentTarget);
        return studentsList;
}
public void setStudentsList(DualListModel<StudentClass> studentsList) { this.studentsList = studentsList; }
   
    public ArrayList<StudentClass> getUnassignedList(){
            System.out.println("reading data.........");
             unassgnList  = new ArrayList<StudentClass>();
             String query = "SELECT * FROM stackholder WHERE isstudent = true   AND stackholder_code   "
                 + " NOT IN (  SELECT stackholder_code FROM   studentclassassign);";//vw_studentclassassign
                try{
                         DatabaseBean db = new DatabaseBean();
                         Connection con = db.DBconnect();
                         PreparedStatement stmt = con.prepareStatement(query);
                         stmt.setFetchSize(100);
                         ResultSet rs  = db.preparedState(stmt);
                             while(rs.next()){
                                        newStudentClass.setHolderCode(rs.getString("stackholder_code"));
                                        System.out.println(" record found rs :"+ rs.getString("first_name")+"--"+rs.getString("last_name"));
                                        unassgnList .add(new StudentClass(rs.getString("stackholder_code"),rs.getString("first_name"),rs.getString("last_name")));
                                        //System.out.println(" record found rs2:"+ rs.getString("first_name")+"--"+rs.getString("last_name"));
                    
                            }
                         rs.close();
                         db.cleanup();
                    }catch(SQLException e){
                            msg = new FacesMessage(FacesMessage.SEVERITY_FATAL, "Sql Error"," Please contact admin!");
                            System.out.println("Un assigned Student  error in SQLE !"+e);
                    }catch(Exception e ){
                            msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Saving Error"," Fill in the missing fields!");
                            System.out.println("Un assigned Students  error in EXception !"+e);
                    }
        
            return unassgnList;
                
    }
    public boolean getIsSelected(){return isSelected;}
    public void setIsselected(boolean isSelected){this.isSelected = isSelected;}
    
    public String getClassCode(){return classCode;}
    public void setClassCode(String classCode){this.classCode = classCode;}
    
    
    
    public StudentClass getNewStudentClass(){
        return newStudentClass;
    }
    public void setNewStudentClass(StudentClass newStudentClass ){
        this.newStudentClass = newStudentClass;
    }
    
    public StudentPerStream getStudentperstream(){
        return studentperstream;
    }
    public void setStudentperstream(StudentPerStream stud){
        this.studentperstream = stud;
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
            builder.append(((StudentClass) item).getHolderCode()).append("<br />");
        }
         
        FacesMessage msg = new FacesMessage();
        msg.setSeverity(FacesMessage.SEVERITY_INFO);
        msg.setSummary("Items Transferred");
        msg.setDetail(builder.toString());
         
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
    
    public void onUnselect(UnselectEvent event) {
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Item Unselected", event.getObject().toString()));
    }
     
    public void onReorder() {
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "List Reordered", null));
    }
    
    /**
     * generating class  code 
     */
    public  void generateAssgnCode(){
        Random newrandom = new Random();
        int random = newrandom.nextInt(1000)+8880;
        System.out.println("newNo:"+ random);
        String randomCode = String.valueOf("assgn/"+random);
        newStudentClass.setAssgnCode(randomCode);
    }
    /***
     * check  current academic year
     * for assigning new student to classes
     */
    public void getCurrentYear(){
        
        String sql = "SELECT acd_code FROM  academic_year WHERE is_active = true LIMIT 1  ;";
        try{
            DatabaseBean db = new DatabaseBean();
            Connection con = db.DBconnect();
            PreparedStatement stmt = con.prepareStatement(sql);
            ResultSet rs = db.preparedState(stmt);
            while(rs.next()){
                newStudentClass.setAcdCode(rs.getString("acd_code"));
            }
            rs.close();
            db.cleanup();
        }catch(SQLException e ){
            System.out.println(" SQL error "+e);
        }catch(Exception e ){
            System.out.println("Exception error" +e);
        }
        
    }
    
    /***
     * generate student available per class of each stream
     */
    public ArrayList<StudentPerStream> getStudentperStreams(){
        String sql = "SELECT * vw_studentClassAssign WHERE class_code ='"+ getClassCode().trim()+"';";
           studentperStreams = new ArrayList<StudentPerStream>();
        try{
            DatabaseBean db = new DatabaseBean();
            Connection con = db.DBconnect();
            PreparedStatement stmt = con.prepareStatement(sql);
            ResultSet rs1 = db.preparedState(stmt);
            while(rs1.next()){
                String streamcode = rs1.getString("stream_code");
                String streamname = rs1.getString("stream_name");
                String query = "SELECT * vw_studentClassAssign WHERE stream_code ='"+ streamcode.trim()+"';";
                ResultSet rs2= db.query(query);
                int boys  = 0;
                int girls= 0;
                int total  = 0; 
                while(rs2.next()){
                     
                    if((rs2.getString("gender").equalsIgnoreCase("male"))  ){
                        boys++;
                        studentperstream.setBoys(boys);
                    }
                    else if((rs2.getString("gender").equalsIgnoreCase("female"))){
                        girls++;
                        studentperstream.setGirls(girls);
                    }else{
                        boys  =0;
                        girls = 0;
                    }
                    total =boys+girls;
                    studentperstream.setTotalstudent(total);
                    studentperStreams.add(new StudentPerStream(streamname, studentperstream.getBoys(), studentperstream.getGirls(), studentperstream.getTotalstudent()));
                }
            }
        }catch(SQLException e){
            System.out.println("SQL error"+ e);
        }catch(Exception e){
            System.out.println("Exception error"+ e);
        }
        
        return studentperStreams;
    }
    /***
     * Combo box to select classes and class streams
     * ****************************************************************************************8
     */
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
                String query = "SELECT stream_code, stream_name FROM streams WHERE class_code = '"+newStudentClass.getClasscode()+"';";

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
    /******
     * end combo box
     * **********************************************************************************
     */
      /****
       * student can only be added to current academic year
       */
      public void getCurrentYr(){
          String sql ="SELECT acd_code FROM academic_year WHERE is_active= true;";
          try{
                    DatabaseBean db = new DatabaseBean();
                    Connection con = db.DBconnect();
                    PreparedStatement stmt = con.prepareStatement(sql);
                    ResultSet rs = db.preparedState(stmt);
              
                    while(rs.next()){
                            newStudentClass.setAcdCode(rs.getString("acd_code"));
                    }
                    rs.close();
                    db.cleanup();
          }catch(SQLException e){
              System.out.println("SQL current year error:" +e);
          }catch(Exception e ){
              System.out.println("EX current year error:" +e);
          }
             
          
      }
      
      public void clstreams(){
          System.out.println("selected classcode :"+ newStudentClass.getClasscode());
          getClassStream();
         System.out.println("dual list target "+ studentsList.getTarget());
         for(Object stud :studentsList.getTarget() ){
             if(!stud.toString().isEmpty()){
                System.out.println("dual Object list target2 "+ stud);
                newStudentClass.setHolderCode(stud.toString());
             }
             System.out.println("dual holder values list target3 :"+ newStudentClass.getHolderCode());
            // dtClassStream();
             
         }
      }
      public void dtClassStream(){
          if(newStudentClass.getStreamCode().isEmpty()){
              newStudentClass.setStreamCode(newStudentClass.getClasscode());
              setClassCode(newStudentClass.getClasscode());
          }else{
              setClassCode(newStudentClass.getClasscode());
          }
          System.out.println("streamcode " + getClassCode());
      }
    /***
     * inserting records to db
     */
    
    public void addStudents(){
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
	   
	   Calendar currenttime = Calendar.getInstance();
           Date sqldate = new Date((currenttime.getTime()).getTime());
           
           getCurrentYr();
           
            newStudentClass.setStatus("onProgress");
            String sql = "INSERT INTO studentClassAssign ( assgn_code, stackholder_code,stream_code,acd_code,status,created_date)"
                + " VALUES (?, ?, ?,?,?,?)"; 
            try{
                    DatabaseBean db = new DatabaseBean();
                    Connection con = db.DBconnect();
                    con.setAutoCommit(false);
                    DatabaseMetaData dbmData = con.getMetaData();
                    if(dbmData.supportsBatchUpdates()){
                         PreparedStatement stmt = con.prepareStatement(sql);
                            for(Object student :studentsList.getTarget() ){
                                    if(!student.toString().isEmpty()){
                                       System.out.println("doing batch data......");
                                        generateAssgnCode();
                                         newStudentClass.setHolderCode(student.toString());
                                         stmt.setString(1, newStudentClass.getAssgnCode());
                                         stmt.setString(2, newStudentClass.getHolderCode());
                                         stmt.setString(3, newStudentClass.getStreamCode());
                                         stmt.setString(4, newStudentClass.getAcdCode());
                                         stmt.setString(5, newStudentClass.getStatus());
                                         stmt.setDate(6,sqldate);
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
        
    }
}
