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
public class MenuItem {
    
    String item_name;
    float item_unit_price;
    int item_qty;

    public String getItem_name() {
        return item_name;
    }

    public int getItem_qty() {
        return item_qty;
    }

    public float getItem_unit_price() {
        return item_unit_price;
    }

    public void setItem_name(String item_name) {
        this.item_name = item_name;
    }

    public void setItem_qty(int item_qty) {
        this.item_qty = item_qty;
    }

    public void setItem_unit_price(float item_unit_price) {
        this.item_unit_price = item_unit_price;
    }
    
    
}
