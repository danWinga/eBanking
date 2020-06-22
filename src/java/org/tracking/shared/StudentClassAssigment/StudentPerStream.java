/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.tracking.shared.StudentClassAssigment;

import java.io.Serializable;

/**
 *
 * @author root
 */
public class StudentPerStream  implements Serializable{
    private String streams;
    private int boys,  girls, totalstudent;
    /**
     * 
     */
    public  StudentPerStream (){
        streams = "";
        boys = 0;
        girls = 0;
        totalstudent = 0;
    }
    /**
     * 
     * @param streams
     * @param boys
     * @param girls
     * @param total 
     */
    public  StudentPerStream (String streams, int boys, int girls, int total){
        this.streams = streams;
        this.boys = boys;
        this.girls = girls;
        this.totalstudent = total;
    }
    
    public String getStreams(){return streams;}
    public void setStreams(String streams){this.streams = streams;}
    
    public int getBoys(){return boys;}
    public void setBoys(int boys){this.boys = boys;}
    
    public int getGirls(){return girls;}
    public void setGirls(int girls){this.girls = girls;}
    
    public int getTotalstudent(){return totalstudent;}
    public void setTotalstudent(int totalstudent){this.totalstudent = totalstudent;}
    
    @Override
    public String toString(){
        return getStreams()+" "+ getBoys()+" "+getGirls()+" "+ getTotalstudent();
    }
}
