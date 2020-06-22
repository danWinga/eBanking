/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.tracking.shared.subject.subjectToTeacher;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author root
 */
public class Subjectteacher implements Serializable {
        private int id;
        private String assgnCode,holderCode,subclassCode,streamCode,termCode;
        private String subjectName,stremmName,subjectCode,classCode;
        private Date created;
        
        public Subjectteacher(){
            id = 0;
            assgnCode = "";
            holderCode = "";
            subclassCode = "" ;
            streamCode = "";
            termCode = "";
            created = new Date();
        }
        public Subjectteacher(String subjectcode, String subjectName ){
            this.subjectCode = subjectcode;
            this.subjectName = subjectName;
        }
        public Subjectteacher(int id,String assgnCode,String holderCode,String subclassCode,String streamCode,
                String termCode, Date created  ){
            this.id = id;
            this.assgnCode = assgnCode;
            this.holderCode = holderCode;
            this.subclassCode = subclassCode ;
            this.streamCode = streamCode;
            this.termCode = termCode;
            this.created = created;
        }
        
        public int getId(){return id;}
        public void setId(int id ){this.id = id;};
        
        public String getAssgnCode(){return assgnCode;}
        public void setAssgnCode(String assgnCode){this.assgnCode  = assgnCode;}
        
        public String getHolderCode(){return holderCode;}
        public void setHolderCode(String holderCode){this.holderCode = holderCode;}
        
        public String getSubclassCode(){return subclassCode;}
        public void setSubclassCode(String subclassCode){this.subclassCode  =subclassCode;}
        
        public String getStreamCode(){return streamCode;}
        public void setStreamCode(String streamCode){this.streamCode = streamCode;}
        
        public String getTermCode(){return termCode;}
        public void setTermCode(String termCode){this.termCode = termCode;}
        
        public Date getCreated(){return created;}
        public void setCreated(Date created){this.created = created;}
        
        public String getSubjectName(){return subjectName;}
        public void setSubjectName(String subjectName){this.subjectName = subjectName;}
        
        public String getStremmName(){return stremmName;}
        public void setStremmName(String stremmName){this.stremmName = stremmName;}
        
        public String getSubjectCode(){return subjectCode;}
        public void setSubjectCode(String subjectCode){this.subjectCode = subjectCode;}
        
        public String getClassCode(){return classCode;}
        public void setlassCode(String classCode ){this.classCode = classCode;}
}
