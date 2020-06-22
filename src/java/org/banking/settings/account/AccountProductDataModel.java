/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.banking.settings.account;

import java.util.ArrayList;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SelectableDataModel;

/**
 *
 * @author dan
 */
public class AccountProductDataModel extends LazyDataModel<AccountProduct> implements SelectableDataModel<AccountProduct> {
    
    private static final long serialVersionUID = 1L;
    private ArrayList<AccountProduct> datasource;
    
    
    public AccountProductDataModel(ArrayList<AccountProduct> datasource){
        this.datasource = datasource;
    }
    
    @Override
    public AccountProduct getRowData(String rowKey){
        for(AccountProduct accProd : datasource){
            if(accProd.getProductCode().equals(rowKey))
                return accProd;
        }
        return null;
    }
    
    @Override
    public Object getRowKey(AccountProduct accProd){
        return accProd.getProductCode();
    }
}
