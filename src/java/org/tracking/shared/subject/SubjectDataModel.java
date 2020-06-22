/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.tracking.shared.subject;

import java.util.ArrayList;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SelectableDataModel;

/**
 *
 * @author root
 */
public class SubjectDataModel  extends LazyDataModel<Subject> implements SelectableDataModel<Subject>{
    
    private ArrayList<Subject> subSource;
    
    public SubjectDataModel (ArrayList<Subject> subSource){
       this.subSource = subSource;
        
    }
    @Override
    public Subject getRowData(String rowKey){
        for(Subject subject: subSource){
            if(subject.getSubjectCode().equals(rowKey))
                return subject;
        }
        return null;
    }
    @Override
    public Object getRowKey(Subject subject){
        return subject.getSubjectCode();
    }
}
