/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.tracking.shared.subject.subjectToClass;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author root
 */
public class SubjectClass implements Serializable {
        private int id;
        private String subClassCode,classCode,subjCode,subjectName;
        private Date createdDate;
        
        public SubjectClass(){
            id =0;
            subClassCode = "";
            classCode = "";
            subjCode = "";
            subjectName = "";
            createdDate = new Date();
            
        }
        /***
         * 
         * @param subcode
         * @param subjectName 
         */
        public SubjectClass(String subcode,String subjectName){
            this.subjCode = subcode;
            this.subjectName = subjectName;
        }
        
    
        public int getId(){ return id;}
        public void setId(int id ){this.id = id;}
        
        public String getSubjectName(){return subjectName;}
        public void setSubjectName(String subjectName){this.subjectName = subjectName;}
        
        public String getSubClassCode(){return subClassCode;}
        public void setSubClassCode(String subClassCode ){this.subClassCode =subClassCode;}
        
        public String getClassCode(){return classCode;}
        public void setClassCode(String classCode){this.classCode = classCode;}
        
        public String getSubjCode(){return subjCode;}
        public void setSubjCode(String subjCode){this.subjCode = subjCode;}
        
        public Date getCreatedDate(){return createdDate;}
        public void setCreatedDate(Date createdDate){this.createdDate = createdDate;}
        
    
}
