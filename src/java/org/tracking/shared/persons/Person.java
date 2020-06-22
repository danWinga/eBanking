/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.tracking.shared.persons;

import java.io.Serializable;

/**
 *
 * @author root
 */
public class Person implements Serializable{
    
    private String firstName;
    private String middleName;
    private String lastName;
    private String address1;
    private String address2;
    private String city;
    private String contact1, contact2, email;
    
    /**
   *Creates a person object with blank first, last and middle names.
   */
    
    public Person()
    {
        firstName   ="";
        middleName   = "";
        lastName     ="";
        address1     = "";
        address2     = "";
        contact1     = "";
        contact2     = "";
        email        = "";
        city         = "";
    }
    public Person (String fn, String mn, 
            String ln, String ad1, String ad2, 
            String co1,String co2,String em, String ct ){
        this.firstName = fn;
        this.middleName = mn;
        this.lastName = ln;
        this.contact1 =co1;
        this.contact2 = co2;
        this.address1 = ad1;
        this.address2 = ad2;
       this. email = em;
        this.city = ct;
        
    }
    /**
   *Creates new Person object from another Person
   *@param thisPerson Person object.
   */
    public Person(Person thisPerson){
        this.firstName = thisPerson.getFirstName();
        this.middleName = thisPerson.getMiddleName();
        this.lastName =  thisPerson.getLastName();
        this.contact1 =  thisPerson.getContact1();
        this.contact2 = thisPerson.getContact2();
        this.address1 = thisPerson.getAddress1();
        this.address2 = thisPerson.getAddress2();
        this.email =     thisPerson.getEmail();
        this.city =      thisPerson.getCity();
    }
    public Person(String fn, String mn, String ln){
        this.firstName = fn;
        this.middleName = mn;
        this.lastName = ln;
    }
    
    /**
   *Gets First Name
   *@return Person's first name.
   */
    
    public String getFirstName(){
        return firstName;
    }
    /**
     * Gets Middle Name
     * @return Person's middle name
     */
    public String getMiddleName(){
        return middleName;
    }
    /**
     * Get last Name
     * @return Person's last Name
     */
    public String getLastName(){
        return lastName;
    }
    
    /**
   *Sets new First Name
   *@param str new First Name that is to be replaced over old First Name.
   */
    public void setFirstName(String str ){
        this.firstName = str;
    }
    
    public void setMiddleName(String str){
        this.middleName = str;
    }
    
    public void setLastName(String str){
        this.lastName = str;
    }
    /**
   *Returns the person's full name details.
   *@return Complete Person details.
   */
    @Override
    public String toString(){
        
        return getFirstName() +","+ getMiddleName()+ ", "+ getLastName();
    }
    /**
     * @return person's contact details
     */
    public String getAddress1(){
        return address1;
    }
    public void setAddress1(String str){
        this.address1= str;
    }
    
    public String getAddress2(){
        return address2;
    }
    public void setAddress2(String str){
        this.address2= str;
    }
    /**
     * 
     * @return contact1
     */
    public String getContact1(){
        return contact1;
    }
    public void setContact1(String str){
        this.contact1= str;
    }
    /**
     * @return contact2
     */
    public String getContact2(){
        return contact2;
    }
    public void setContact2(String str){
        this.contact2= str;
    }
    /**
     * @return email
     */
    public String getEmail(){
        return email;
    }
    public void setEmail(String str){
        this.email= str;
    }
    /**
     * @return city
     */
    public String getCity(){
        return city;
    }
    public void setCity(String str){
       this.city= str;
    }

      
}


