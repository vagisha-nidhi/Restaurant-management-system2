/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javadatabasetest;

/**
 *
 * @author vagisha
 */
public class Employee {
    
    int id;
    int phone_no;
    int pincode;
    String category;
    String name;
    String area;

    public void setArea(String area) {
        this.area = area;
    }

    public String getArea() {
        return area;
    }
    
    

    public String getCategory() {
        return category;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getPhone_no() {
        return phone_no;
    }

    public int getPincode() {
        return pincode;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPhone_no(int phone_no) {
        this.phone_no = phone_no;
    }

    public void setPincode(int pincode) {
        this.pincode = pincode;
    }
    
    
    
}
