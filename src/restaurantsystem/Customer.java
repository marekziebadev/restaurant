/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package restaurantsystem;

import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author kom1
 */
public class Customer extends RecursiveTreeObject<Customer>{
    
    StringProperty id;
    StringProperty first_name;
    StringProperty last_name;
    StringProperty phone;
    StringProperty email;
    StringProperty address;
    StringProperty payment_type;
    StringProperty people_amount;
    StringProperty table_number;
    StringProperty date;
    StringProperty start_time;
    StringProperty end_time;
    StringProperty price;
    
    public Customer()
    {
       super();
    }

    public Customer(String id, String first_name, String last_name, String phone, String email, String address, String payment_type, String people_amount, String table_number, String date, String start_time, String end_time, String price) {
        this.id = new SimpleStringProperty(id);
        this.first_name = new SimpleStringProperty(first_name);
        this.last_name = new SimpleStringProperty(last_name);
        this.phone = new SimpleStringProperty(phone);
        this.email = new SimpleStringProperty(email);
        this.address = new SimpleStringProperty(address);
        this.payment_type = new SimpleStringProperty(payment_type);
        this.people_amount = new SimpleStringProperty(people_amount);
        this.table_number = new SimpleStringProperty(table_number);
        this.date = new SimpleStringProperty(date);
        this.start_time = new SimpleStringProperty(start_time);
        this.end_time = new SimpleStringProperty(end_time);
        this.price = new SimpleStringProperty(price);
    }

    
}

