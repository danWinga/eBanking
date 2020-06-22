/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.tracking.shared.classes.streams;

import java.util.ArrayList;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SelectableDataModel;

/**
 *
 * @author root
 */
public class StreamDataModel extends LazyDataModel<Stream> implements SelectableDataModel<Stream> {
    private ArrayList<Stream> streamdata;
    
    public StreamDataModel (ArrayList<Stream> streamdata){
        this.streamdata = streamdata;
    }
    @Override
    public Stream getRowData(String rowKey){
        for(Stream stream: streamdata){
            if(stream.getStreamCode().equals(rowKey))
                return stream;
         
             
        }
       return null;
    }
    @Override
    public Object getRowKey(Stream stream){
        return stream.getStreamCode();
    }
}
