/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.tracking.shared.stakeholders;

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
import javax.servlet.http.HttpSession;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.LazyDataModel;
import org.tracking.server.database.DatabaseBean;

/**
 *
 * @author root
 */
@ManagedBean(name="stakeholdersBean")
@SessionScoped
public class StakeholdersBean implements Serializable{
    private ArrayList<Stakeholders> stakeholderList;
    private ArrayList<Stakeholders> studentList;
    private ArrayList<Stakeholders> teachertList;
    private ArrayList<Stakeholders> filteredStakeholders;
    private Stakeholders selectedStakeholder;
    private Stakeholders selectedTeacher;
    private Stakeholders newStudent;
    private Stakeholders newTeacher;
    private LazyDataModel<Stakeholders> studModel;
    private LazyDataModel<Stakeholders> teacherModel;
    private boolean isValid;
    
    
    FacesContext context = FacesContext.getCurrentInstance();
    HttpSession httpSession = (HttpSession)context.getExternalContext().getSession(true);
    FacesMessage msg = null;
    
    @PostConstruct
    public void init(){
        selectedStakeholder = new Stakeholders();
        selectedTeacher = new Stakeholders();
        stakeholderList = getStakeholderList();
        newStudent = new Stakeholders();
        newTeacher = new Stakeholders();   
        
    }
    
    
    
    public LazyDataModel<Stakeholders>getStudModel(){
        studModel = new StakeholderDataModel(getStudentrecord());
        return studModel;
    }
    
    public LazyDataModel<Stakeholders>getTeacherModel(){
        teacherModel = new StakeholderDataModel(getTeachertList());
        return teacherModel;
    }
    
    public Stakeholders getSelectedStakeholder(){
        return selectedStakeholder;
    }
    public void setSelectedStakeholder(Stakeholders stake){
        this.selectedStakeholder = stake;
    }
    
    public Stakeholders getSelectedTeacher(){
        return selectedTeacher;
    }
    public void setSelectedTeacher(Stakeholders stake){
        this.selectedTeacher = stake;
    }
    public ArrayList<Stakeholders>getFilteredStakeholders() {
        return filteredStakeholders;
    }
    public void setFilteredStakeholders(ArrayList<Stakeholders> filteredStakeholders ){
        this.filteredStakeholders = filteredStakeholders;
        
    }
    /******
     * Reading all members of the school record 
     * @return @all stakeholder records
     */
    
    public ArrayList<Stakeholders>  getStakeholderList(){
        
              String query = "SELECT * FROM stackholder;";
               stakeholderList = new ArrayList<Stakeholders>();
              System.out.println(" collecting Stakeholder data>..............................!");
        try{
            DatabaseBean db = new DatabaseBean();
            Connection conn = db.DBconnect();
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setFetchSize(100);
            ResultSet rs = db.preparedState(stmt);
            while(rs.next()){
                stakeholderList.add(new Stakeholders(rs.getInt("id"),rs.getString("stackholder_Code"),rs.getString("school_Code"),
                        rs.getString("first_Name"),rs.getString("middle_Name"),rs.getString("last_Name"),rs.getString("other_Name"),
                        rs.getString("gender"),rs.getDate("dateOf_Birth"),rs.getString("birth_Place"),rs.getString("blood_Group"),
                        rs.getString("nationality"),rs.getString("religion"),rs.getString("address1"),rs.getString("address2"),
                        rs.getString("city"),rs.getString("county"),rs.getString("contact1"),rs.getString("contact2"),
                        rs.getString("email"),rs.getString("student_ID"),rs.getString("TAC_ID"),rs.getString("work_ID"),rs.getString("father_Name"),
                        rs.getString("mother_Name"),rs.getString("guardian_Name"),rs.getString("proffession"),rs.getBoolean("isCanEmail"),
                        rs.getBoolean("isStudent"),rs.getBoolean("isTeacher"),rs.getBoolean("isSuperAdmin"),rs.getBoolean("isAdmin"),rs.getBoolean("isBOG"),
                        rs.getBoolean("canLogin"),rs.getString("job_Description"),rs.getString("note"),rs.getDate("created_Date"),
                        rs.getInt("idno"),rs.getString("passportno") ,rs.getString("maritalstatus") ,rs.getString("staketitle") , rs.getString("postcodeno") , 
                        rs.getString("faxnumber") ,rs.getString("passonalemail")));
            }         
             rs.close();
             db.cleanup();
           
        }catch(SQLException e){
            msg = new FacesMessage(FacesMessage.SEVERITY_FATAL, "Saving Error"," Please contact admin!");
            System.out.println("Stakeholder  error in SQLE !"+e);
        }catch(Exception e){
            msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Saving Error"," Fill in the missing fields!");
            System.out.println("Stakeholder  error in EXception !"+e);
        }
      
        System.out.println("Stakeholder  data collected ........!");
        return stakeholderList;
    }
    
    /****
     * 
     * @return teachers list 
     */
    
    public ArrayList<Stakeholders> getTeachertList(){
         teachertList = new ArrayList<Stakeholders>();
        for(Stakeholders holder :getStakeholderList()){
                if(holder.getIsTeacher()== true){
                     teachertList.add(new Stakeholders(holder));
                }
        }
        return  teachertList;
    }
    /*****
     * 
     * @return student list 
     */
    
    public ArrayList<Stakeholders> getStudentrecord(){
        studentList= new ArrayList<Stakeholders>();
        for(Stakeholders holder :getStakeholderList()){
           
           if(holder.getIsStudent()== true){
              selectedStakeholder.formateGender(selectedStakeholder.getGender());
                 studentList.add(new Stakeholders(holder));
           }
                 
        }
        return studentList;
    }
    
    
    public void save() {
        addMessage("Success", "Data saved");
        System.out.println("Selected Name:"+selectedStakeholder.getFirstName());
    }
    public void checkEmpty(){
        if(selectedStakeholder.getOtherMail()== null){
            selectedStakeholder.setOtherMail("N/A");
            System.out.println("selected othermails   .... ok ");
        }
        if(selectedStakeholder.getPostalCode()==null){
            selectedStakeholder.setPostalCode("N/A");
            System.out.println("selected postalcode   .... ok ");
        }
         if(selectedStakeholder.getPassportno()== null){
             selectedStakeholder.setPassportno("N/A");
             System.out.println("selected passport   .... ok ");
        }
        if(selectedStakeholder.getStakeTitle()== null){
            selectedStakeholder.setStakeTitle("N/A");
            System.out.println("title   .... ok ");
        }
         if(selectedStakeholder.getFaxNumber()==null){
            selectedStakeholder.setFaxNumber("N/A");
            System.out.println("selected faxno   .... ok ");
        }
         if(selectedStakeholder.getMaritalstatus()==null){
            selectedStakeholder.setMaritalstatus("N/A");
            System.out.println("selected marital status   .... ok ");
        }
    }
    /*****
     * update records of a stakeholder
     */
     
    public void update() {
        System.out.println("system is updating stakeholders records .... ");
         checkEmpty();
        System.out.println("selected gender  .... "+selectedStakeholder.getIsGendarz());
        selectedStakeholder.setSelectedGender(selectedStakeholder.getIsGendarz());
        System.out.println("gender record  .... "+selectedStakeholder.getGender());
        System.out.println("Stakeholder code  .... "+selectedStakeholder.getStakeholderCode());
        System.out.println("date of birth  .... "+selectedStakeholder.getDateOfBirth());
        System.out.println("idno  .... "+selectedStakeholder.getIdno());
        System.out.println("otheremail  .... "+selectedStakeholder.getOtherMail());
        System.out.println("postalcode  .... "+selectedStakeholder.getPostalCode());
        System.out.println("title  .... "+selectedStakeholder.getStakeTitle());
        System.out.println("passportno  .... "+selectedStakeholder.getPassportno());
        System.out.println("fax number  .... "+selectedStakeholder.getFaxNumber());
        
        
        String sql = "UPDATE stackholder    SET first_name = '"+selectedStakeholder.getFirstName().trim()+"', "
                   + "middle_name = '"+ selectedStakeholder.getMiddleName().trim()+"',"
                   +  " last_name = '"+selectedStakeholder.getLastName().trim()+"',other_name = '"+selectedStakeholder.getOtherNames().trim()+"',"
                   + "dateof_birth= '"+selectedStakeholder.getDateOfBirth()+"',birth_place= '"+selectedStakeholder.getBirthPlace().trim()+"',"
                   + "blood_group= '"+selectedStakeholder.getBloodGroup().trim()+"',nationality= '"+selectedStakeholder.getNationality().trim()+"',"
                   + "religion= '"+selectedStakeholder.getReligion().trim()+"',address1= '"+selectedStakeholder.getAddress1().trim()+"',"
                   + "address2= '"+selectedStakeholder.getAddress1().trim()+"',city= '"+selectedStakeholder.getCity().trim()+"',"
                   + "county= '"+selectedStakeholder.getCounty().trim()+"', contact1= '"+selectedStakeholder.getContact1().trim()+"',"
                   + "contact2= '"+selectedStakeholder.getContact2().trim()+"',email= '"+selectedStakeholder.getEmail().trim()+"',"
                   + "father_name= '"+selectedStakeholder.getFatherName().trim()+"',mother_name= '"+selectedStakeholder.getMotherName().trim()+"',"
                   + "guardian_name= '"+selectedStakeholder.getGadianName().trim()+"',proffession= '"+selectedStakeholder.getProffession_ID().trim()+"',"
                   + "iscanemail= '"+selectedStakeholder.getIsCanEmail()+"',canlogin= '"+selectedStakeholder.getCanLogin()+"',"
                   + "job_description= '"+selectedStakeholder.getJobDescription().trim()+"',note= '"+selectedStakeholder.getNotes().trim()+"', "
                   + "maritalstatus= '"+selectedStakeholder.getMaritalstatus().trim()+"',passonalemail= '"+selectedStakeholder.getOtherMail().trim()+"',"
                   + "postcodeno= '"+selectedStakeholder.getPostalCode().trim()+"',staketitle= '"+selectedStakeholder.getStakeTitle().trim()+"',"
                   + "gender = '"+selectedStakeholder.getGender().trim()+"', faxnumber='"+selectedStakeholder.getFaxNumber().trim()+"' WHERE stackholder_code ='"+selectedStakeholder.getStakeholderCode().trim()+"';";
          
//, faxnumber='"+selectedStakeholder.getFaxno().trim()+"';
        //+ "postcodeno= '"+selectedStakeholder.getPostalCode().trim()+"',staketitle= '"+selectedStakeholder.getStakeTitle().trim()+"',"
        
        
        System.out.println("sqlnot connected  sql .... ");
           try{
               DatabaseBean db = new DatabaseBean();
               System.out.println("sql:"+sql);
               db.insert(sql);
               FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, 
                    "Success! ", ":Record  updated successfuly"));
           }catch(Exception e){
               System.out.println("Error:" +e);
               FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, 
                    "Fatal Error! ", ":Kindly contact System administrator"));
           }
           
        addMessage("Success", "Data updated");
        
    }
     
    public void delete() {
        addMessage("Success", "Data deleted");
    }
    
    public void addMessage(String summary, String detail) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary, detail);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }
    /**
     * update con
     */
   public void onRowSelect(SelectEvent event ){
       FacesMessage msg = new FacesMessage("Folder Selected", ((Stakeholders) event.getObject()).getStakeholderCode());
        //FacesContext.getCurrentInstance().addMessage(null, msg);
   }
   
   
   /**************************************************************************
     * ************record validation **********************************************
     * @param idno
     * @return 
     */
    
    public boolean getIsValid(int idno){
        isValid =false;
        int tempData = 0;
        String sql = "SELECT idno from stackeholder where  stackholder_code == '"+selectedStakeholder.getStakeholderCode()+"' ;";
        try{
            DatabaseBean db = new DatabaseBean();
            Connection con  = db.DBconnect();
            PreparedStatement stmt = con.prepareStatement(sql);
            ResultSet rs = db.preparedState(stmt);
            while(rs.next()){
                tempData = rs.getInt("idno");
                
            }
            rs.close();
            db.cleanup();
        }catch(SQLException e){
        }catch(Exception e ){
        }
        if(tempData >0){
            return isValid = true;
        }
        return isValid;
    }
    public void setIsValid(boolean isValid){this.isValid = isValid;}
    
    
    
    
}
