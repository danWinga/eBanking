/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.tracking.shared.subject.subjectToTeacher;

import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;
import org.tracking.shared.stakeholders.Stakeholders;

/**
 *
 * @author root
 */
@FacesConverter("teacherConverter")

public class TeacherConverter implements Converter {
    private List<Stakeholders> teachersource;
    private Stakeholders selectedTeacher = new Stakeholders() ;
    public String valueFound;
    
    @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String value) {
        if(value != null && value.trim().length() > 0) {
            try {
                SubjectTeacherBean service = (SubjectTeacherBean) fc.getExternalContext().getApplicationMap().get("subjectTeacherBean");
                for(Stakeholders teacher : service.getTeachers()){
                    if(teacher.getStakeholderCode().equals(value))
                        selectedTeacher = teacher;
                }
                return selectedTeacher;
            } catch(NumberFormatException e) {
                throw new ConverterException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Conversion Error", "Not a valid Teacher."));
            }
        }
        else {
            return null;
        }
    }
    
    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object object) {
        System.out.println("testing as string");
        if(object != null) {
             System.out.println(" string in :" + String.valueOf(((Stakeholders) object).getStakeholderCode()));
            return String.valueOf(((Stakeholders) object).getStakeholderCode());
        }
        else {
            return null;
        }
    }
    public Stakeholders getSelectedTeacher(){
        return selectedTeacher;
    }
    
    public void setSelectedTeacher(Stakeholders selected){
        this.selectedTeacher = selected;
    }
    public String getValueFound(){return valueFound;}
    public void setValueFound(String valueFound){this.valueFound = valueFound;}
    public void recordf(){
            System.out.println("record inside :" +getValueFound());
    }

    
    
}
