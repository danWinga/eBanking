/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.tracking.shared.Files;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author root
 */
public class Files  implements Serializable{
    private int id;
    private String fileCode;
    private String accCode; 
    private String flname;
    private String description;
    private Date createdDate;
    
    public Files(){
        id =0;
        fileCode = "";
        accCode = "";
        flname = "";
        description = "";
        createdDate = new Date();
    }
    
    public Files(int id, String filecode, String accCode, String filename,String desc, Date created){
        this.id = id;
        this.fileCode = filecode;
        this.accCode = accCode;
        this.flname = filename ;
        this.description = desc;
        this.createdDate = created;
    }
     public Files( String filecode, String accCode, String filename,String desc, Date created){
        
        this.fileCode = filecode;
        this.accCode = accCode;
        this.flname = filename ;
        this.description = desc;
        this.createdDate = created;
    }
    public int getId(){return id;}
    public void setId(int id ){this.id= id;}
    
    public  String getFileCode(){return fileCode;}
    public void setFileCode(String filecode){this.fileCode = filecode.toUpperCase();} 
    
    public String getFlname(){return flname;}
    public void setFlname(String filename){this.flname =filename;}
    
    public String getAccCode(){return accCode;}
    public void setAccCode(String acc){this.accCode = acc;}
    
    public String getDescription(){return description;}
    public void setDescription(String desc){this.description = desc;}
    
    public Date getCreatedDate(){return createdDate;}
    public void setCreatedDate(Date createdDate){this.createdDate = createdDate;}
    
    
}
