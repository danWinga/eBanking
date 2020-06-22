/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.tracking.shared.subject;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import org.tracking.server.database.DatabaseBean;

/**
 *
 * @author root
 */
public class Subject implements Serializable {
    private String subCode;
    private String subName;
    private int subID;
    private String details;
    private String fileCode;
    private String shortName;
    private  Date createdDate;
   
    
    public Subject(){
        subCode = "";
        subName = "";
        subID = 0;
        details ="";
        
    }
    /**
     * 
     * @param subCode
     * @param subName
     * @param details 
     */
    public Subject(String subCode, String  subName, String details){
        this .subCode = subCode.toUpperCase();
        this.subName = subName.toUpperCase();
        this.details = details;
        
    }
    public Subject(int subId,String subCode,String filecode, String  subName, String shortname, String details, Date created){
        this .subCode = subCode.toUpperCase();
        this.subName = subName.toUpperCase();
        this.details = details;
        this.subID = subId;
        this.fileCode = filecode;
        this.shortName = shortname;
        this.createdDate = created;
        
    }
    /**
     * 
     * @param thisSubject 
     */
    public Subject (Subject thisSubject){
        subCode = thisSubject.getSubjectCode();
        subName = thisSubject.getSubjectName();
        details = thisSubject.getDetails();
        
    }
    
    /**
     * 
     * @return subCode
     */
    public String getSubjectCode(){
        return subCode;
    }
    /**
     * 
     * @param subCode 
     */
    public void setSubjectCode(String subCode){
        this.subCode = subCode.toUpperCase();
    }
    public String getSubjectName(){
        return  subName;
    }
    public void setSubjectName(String subName){
        this.subName = subName.toUpperCase();
    }
    
    public String getDetails(){
        return details;
    }
    public void setDetails(String details){
        this.details = details;
    }
    public int getSubID(){
        return subID;
    }
    public void setSubID(int subId){
        subID =subId;
    }
    public String getFileCode(){
        return fileCode;
    }
    public void setFileCode(String fileCode){
        this.fileCode = fileCode;
    }
    public String getShortName(){
        return shortName;
    }
    public void setShortName(String shortName){
        this.shortName = shortName;
    }
    public Date getCreatedDate(){
        return createdDate;
    }
    public void setCreatedDate(Date createdDate){
        this.createdDate = createdDate;
    }
    public void dbFileName(){
       String sql = "SELECT file_Code, file_Name FROM account_Files ;";
        try{
            DatabaseBean db = new DatabaseBean();
            Connection con = db.DBconnect();
            PreparedStatement stmt = con.prepareStatement(sql);
            ResultSet rs = db.preparedState(stmt);
            while(rs.next()){
                setFileCode(rs.getString(""));
            }
        }catch(SQLException e){
            System.out.println("SQLE"+ e);
        }catch(Exception e ){
            System.out.println("Excception"+ e);
        }
        
    }
    
}
