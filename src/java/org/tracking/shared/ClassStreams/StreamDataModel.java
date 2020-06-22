/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.tracking.shared.ClassStreams;

import java.util.ArrayList;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SelectableDataModel;

/**
 *
 * @author root
 */
public class StreamDataModel extends LazyDataModel implements SelectableDataModel {
    private ArrayList<Stream> streamData;
    
    public StreamDataModel(ArrayList<Stream> streamData){
       this.streamData = streamData; 
    }
    @Override
    public Stream getRowData(String rowKey){
        for(Stream stream : streamData){
            if(stream.getStreamCode().equals(rowKey))
                return stream;
        }
        return null;
    }
    
    public Object getRowKey(Stream stream){
        return stream.getStreamCode();
    }
    
}
