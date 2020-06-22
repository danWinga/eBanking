/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.tracking.shared.subject.subjectToTeacher;

import java.util.List;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SelectableDataModel;
import org.tracking.shared.stakeholders.Stakeholders;

/**
 *
 * @author root
 */
public class SujectTDatamodel extends LazyDataModel<Stakeholders> implements SelectableDataModel<Stakeholders>{
    private List<Stakeholders> subjTsource;
    
    public SujectTDatamodel (List<Stakeholders> subjTsource){
        this.subjTsource = subjTsource;
    }
    
    @Override
    public Stakeholders getRowData(String rowKey){
        for(Stakeholders teacher : subjTsource){
            if(teacher.getStakeholderCode().endsWith(rowKey))
                return teacher;
        }
        return null;
    }
    @Override
    public Object getRowKey(Stakeholders teacher){
        return teacher.getStakeholderCode();
    }
}
