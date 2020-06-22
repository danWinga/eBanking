/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.tracking.shared.schoolAccountFolder;


import java.util.ArrayList;
import java.util.List;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SelectableDataModel;

/**
 *
 * @author root
 */
public class SchoolFolderDataModel extends LazyDataModel<SchoolFolder> implements SelectableDataModel<SchoolFolder> {
    private ArrayList<SchoolFolder> datasource;
    
    
    public SchoolFolderDataModel(ArrayList<SchoolFolder> datasource){
        this.datasource = datasource;
    }
    
    @Override
    public SchoolFolder getRowData(String rowKey) {
        for(SchoolFolder schoolfolder : datasource) {
            if(schoolfolder.getAccCode().equals(rowKey))
                return schoolfolder;
        }
 
        return null;
    }
    @Override
    public Object getRowKey(SchoolFolder schoolfolder){
        return schoolfolder.getAccCode();
    }
    
    /**@Override
    public List<SchoolFolder> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String,Object> filters) {
        List<SchoolFolder> data = new ArrayList<SchoolFolder>();
 
        //filter
        for(SchoolFolder schoolfolder : datasource) {
            boolean match = true;
 
            if (filters != null) {
                for (Iterator<String> it = filters.keySet().iterator(); it.hasNext();) {
                    try {
                        String filterProperty = it.next();
                        Object filterValue = filters.get(filterProperty);
                        String fieldValue = String.valueOf(schoolfolder.getClass().getField(filterProperty).get(schoolfolder));
 
                        if(filterValue == null || fieldValue.startsWith(filterValue.toString())) {
                            match = true;
                    }
                    else {
                            match = false;
                            break;
                        }
                    } catch(Exception e) {
                        match = false;
                    }
                }
            }
 
            if(match) {
                data.add(schoolfolder);
            }
        }
 
        //sort
        if(sortField != null) {
            Collections.sort(data, new LazySorter(sortField, sortOrder));
        }
 
        //rowCount
        int dataSize = data.size();
        this.setRowCount(dataSize);
 
        //paginate
        if(dataSize > pageSize) {
            try {
                return data.subList(first, first + pageSize);
            }
            catch(IndexOutOfBoundsException e) {
                return data.subList(first, first + (dataSize % pageSize));
            }
        }
        else {
            return data;
        }
    }
    */
}
