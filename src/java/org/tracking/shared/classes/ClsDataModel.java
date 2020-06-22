/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.tracking.shared.classes;

import java.util.ArrayList;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SelectableDataModel;

/**
 *
 * @author root
 */
public class ClsDataModel extends LazyDataModel implements SelectableDataModel {
    private ArrayList<SchClass> classDataSource;
    
    public ClsDataModel(ArrayList<SchClass> classDataSource){
        this.classDataSource = classDataSource;
    }
    @Override
    public SchClass getRowData(String rowKey){
        for(SchClass classrec : classDataSource){
                if(classrec.getClassCode().equals(rowKey))
                    return classrec;
                }
        return null;
    }
    
    public Object getRowKey(SchClass classrec){
        return classrec.getClassCode();
    }
}
