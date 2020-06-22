/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.banking.settings.admin;

import java.util.List;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SelectableDataModel;

/**
 *
 * @author dan
 */
public class CountyDataModel extends LazyDataModel<County> implements SelectableDataModel<County> {
    
            private static final long serialVersionUID = 1L;
            private List<County> sourceData;
            
            public CountyDataModel(List<County> sourceData){
                    this.sourceData  = sourceData;
            }
            
            @Override
            public County getRowData(String rowKey){
                for(County county : sourceData){
                    if(county.getCountyCode().equals(rowKey))
                        return county;
                }
                return null;
            }
            
            @Override
            public Object getRowKey(County county){
                    return county.getCountyCode();
            }
            
    
}
