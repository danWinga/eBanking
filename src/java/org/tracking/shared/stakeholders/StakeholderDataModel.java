/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.tracking.shared.stakeholders;

import java.util.ArrayList;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SelectableDataModel;

/**
 *
 * @author root
 */
public class StakeholderDataModel extends LazyDataModel<Stakeholders>  implements SelectableDataModel<Stakeholders>{
    private ArrayList<Stakeholders> stSource;
    
    public StakeholderDataModel (ArrayList<Stakeholders> stSource){
        this.stSource = stSource;
    }
    @Override
    public Stakeholders getRowData(String rowKey){
        for(Stakeholders  holder:stSource){
            if(holder.getStakeholderCode().equals(rowKey))
                return holder;
        }
        return null;
    }
    
    /**
     *
     * @param holder
     * @return
     */
    @Override
    public Object getRowKey(Stakeholders holder){
        return holder.getStakeholderCode();
    }
}
