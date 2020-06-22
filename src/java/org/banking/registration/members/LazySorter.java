/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.banking.registration.members;

/**
 *
 * @author dan
 */
import java.util.Comparator;
import org.primefaces.model.SortOrder;
public class LazySorter implements Comparator<Member> {
 
    private String sortField;
     
    private SortOrder sortOrder;
     
    public LazySorter(String sortField, SortOrder sortOrder) {
        this.sortField = sortField;
        this.sortOrder = sortOrder;
    }
 
    public int compare(Member member1, Member member2) {
        try {
            Object value1 = Member.class.getField(this.sortField).get(member1);
            Object value2 = Member.class.getField(this.sortField).get(member2);
 
            int value = ((Comparable)value1).compareTo(value2);
             
            return SortOrder.ASCENDING.equals(sortOrder) ? value : -1 * value;
        }
        catch(Exception e) {
            throw new RuntimeException();
        }
    }
}