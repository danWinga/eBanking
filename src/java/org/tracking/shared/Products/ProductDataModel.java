/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.tracking.shared.Products;

import java.util.ArrayList;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SelectableDataModel;

/**
 *
 * @author dan
 */
public class ProductDataModel extends LazyDataModel<Products> implements SelectableDataModel<Products> {
        private ArrayList<Products> productData;
        
        public ProductDataModel (ArrayList<Products> unitdata){
                this.productData = unitdata;
        }
        public Products getRowData(int rowKey){
            for(Products unit: productData){
                if(unit.getId() == rowKey)
                    return unit;
            } 
            return null;
        } 
        
        @Override
        public Object getRowKey(Products unit){
            
            return unit.getId();
        }
    
}
