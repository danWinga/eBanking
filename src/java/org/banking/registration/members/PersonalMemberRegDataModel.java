/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.banking.registration.members;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.Map;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SelectableDataModel;
import org.primefaces.model.SortOrder;

/**
 *
 * @author dan
 */
public class PersonalMemberRegDataModel extends LazyDataModel<Member> implements SelectableDataModel<Member> {
    
    private static final long serialVersionUID = 1L;
    private ArrayList<Member> datasource;
    
    public PersonalMemberRegDataModel(ArrayList<Member> datasource){
        this.datasource = datasource;
    } 
    @Override
    public Member getRowData(String rowKey){
        for(Member aMember : datasource){
            if(aMember.getMemberNo().equals(rowKey))
                return aMember;
        }
        return null;
    }
    
    @Override
    public Object getRowKey(Member aMember){
        return aMember.getMemberNo();
    }
    @Override
    public ArrayList<Member> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String,Object> filters) {
        ArrayList<Member> data = new ArrayList<Member>();
 
        //filter
        for(Member member : datasource) {
            boolean match = true;
 
            if (filters != null) {
                for (Iterator<String> it = filters.keySet().iterator(); it.hasNext();) {
                    try {
                        String filterProperty = it.next();
                        Object filterValue = filters.get(filterProperty);
                        String fieldValue = String.valueOf(member.getClass().getField(filterProperty).get(member));
 
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
                data.add(member);
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
                return (ArrayList<Member>) data.subList(first, first + pageSize);
            }
            catch(IndexOutOfBoundsException e) {
                return (ArrayList<Member>) data.subList(first, first + (dataSize % pageSize));
            }
        }
        else {
            return data;
        }
    }
    
}
