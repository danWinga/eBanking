/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.tracking.shared.Files;

import java.util.ArrayList;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SelectableDataModel;

/**
 *
 * @author root
 */
public class FileDataModel extends LazyDataModel<Files> implements SelectableDataModel<Files> {
    private ArrayList<Files> filedata;
    
    public FileDataModel (ArrayList<Files> filedata){
        this.filedata = filedata;
    }
    @Override
    public Files getRowData(String rowKey){
        for(Files file: filedata){
            if(file.getFileCode().equals(rowKey))
                return file;
         
             
        }
       return null;
    }
    @Override
    public Object getRowKey(Files file){
        return file.getFileCode();
    }
}
