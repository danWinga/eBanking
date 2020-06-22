/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.banking.settings.admin;

import java.util.ArrayList;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SelectableDataModel;

/**
 *
 * @author dan
 */
public class BranchLazyDataModel extends LazyDataModel<Branch> implements SelectableDataModel<Branch> {
    
            private static final long serialVersionUID = 1L;
            private ArrayList<Branch> sourceData;
            
            public BranchLazyDataModel(ArrayList<Branch>sourceData){
                    this.sourceData = sourceData;
            }
            
            @Override
            public Branch getRowData(String rowKey){
                    for(Branch branch :sourceData){
                       if(branch.getBranchCode().equals(rowKey))
                           return branch;
                    }
                    return null;
            }
            
            @Override
            public Object getRowKey(Branch branch){
                   return branch.getBranchCode();
            }
    
}
