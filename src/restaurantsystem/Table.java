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
public class Table extends RecursiveTreeObject<Table> {
    
    StringProperty id;
    StringProperty table_number;
    StringProperty table_status;
    StringProperty price;
    public Table()
    {
        super();
    }

    public Table(String id, String table_number, String table_status, String price) {
        this.id = new SimpleStringProperty(id);
        this.table_number = new SimpleStringProperty(table_number);
        this.table_status = new SimpleStringProperty(table_status);
        this.price = new SimpleStringProperty(price);
    }
    
    
}
