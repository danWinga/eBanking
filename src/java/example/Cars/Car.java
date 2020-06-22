/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package example.Cars;

/**
 *
 * @author root
 */

public class Car {
private String vin,id;
private String brand;
private int year,price;
private String color;
private boolean soldState;
public Car() {
}
public Car(String vin, String brand, int year, String color) {
    this.vin = vin;
    this.brand = brand;
    this.year = year;
    this.color = color;
}
public Car (String id,String brand, int year,String color,int price,boolean state){
    this.id = id;
    this.brand = brand;
    this.year = year;
    this.color = color;
}
public String getId(){
    return id;
}
public void setId(String id){
    this.id= id;
}
public int getPrice(){
    return price;
}
public void setPrice(int price){
    this.price = price;
}
public boolean getSoldState(){
    return soldState;
}
public void setSoldState(boolean soldState){
    this.soldState = soldState;
}
public String getVin() {
    return vin;
}
public void setVin(String vin) {
    this.vin = vin;
}
public String getBrand() {
return brand;
}
public void setBrand(String brand) {
this.brand = brand;
}
public int getYear() {
return year;
}
public void setYear(int year) {
this.year = year;
}
public String getColor() {
return color;
}
public void setColor(String color) {
this.color = color;
}
@Override
public int hashCode() {
int hash = 7;
hash = 97 * hash + (this.vin != null ? this.vin.hashCode() : 0);
return hash;
}
@Override
public boolean equals(Object obj) {
if (obj == null) {
return false;
}
if (getClass() != obj.getClass()) {
return false;
}
final Car other = (Car) obj;
if ((this.vin == null) ? (other.vin != null) : !this.vin.equals(other.vin)) {
return false;
}
return true;
}
}