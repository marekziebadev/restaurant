/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package restaurantsystem;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;

/**
 * FXML Controller class
 *
 * @author kom1
 */
public class ReservationScreenController implements Initializable {
    @FXML
    private TextField firstName;
    @FXML
    private TextField lastName;
    @FXML
    private TextField phone;
    @FXML
    private TextField email;
    @FXML
    private TextField address;
    @FXML
    private TextField paymentType;
    @FXML
    private TextField peopleAmount;
    @FXML
    private TextField tableNumber;
    @FXML
    private TextField startTime;
    @FXML
    private TextField endTime;
    @FXML
    private TextField price;
    @FXML
    private DatePicker date;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void reserve(MouseEvent event) {
        int res = 0;
        String sql = "INSERT INTO clients (first_name, last_name, phone, email, address, payment_type, people_amount, table_number, date, start_time, end_time, price) VALUES (?,?,?,?,?,?,?,?,?,?,?,?)";
        Connection connection = DBConnection.getConnection();
        try {
            PreparedStatement ps=(PreparedStatement)connection.prepareStatement(sql);
            ps.setString(1, firstName.getText());
            ps.setString(2, lastName.getText());
            ps.setString(3, phone.getText());
            ps.setString(4, email.getText());
            ps.setString(5, address.getText());
            ps.setString(6, paymentType.getText());
            ps.setString(7, peopleAmount.getText());
            ps.setString(8, tableNumber.getText());
            ps.setString(9, date.getValue().toString());
            ps.setString(10, startTime.getText());
            ps.setString(11, endTime.getText());
            ps.setString(12, price.getText());
            
            res = ps.executeUpdate();
            
            
        } catch (SQLException ex) {
            Logger.getLogger(ReservationScreenController.class.getName()).log(Level.SEVERE, null, ex);
        }
        if(res>0)
        {
                updateStatus();
                Image image = new Image("img/x-1152114.svg");
                Notifications notification = Notifications.create().title("Done").text("Dodano rezerwacje")
                    .hideAfter(Duration.seconds(5)).position(Pos.BOTTOM_RIGHT)
                    .graphic(new ImageView(image));
            notification.darkStyle();
            notification.show();
        }
        else
        {
            {
                 Image image = new Image("img/x-1152114.svg");
                Notifications notification = Notifications.create().title("Error").text("Blad dodania rezerwacji.")
                    .hideAfter(Duration.seconds(5)).position(Pos.BOTTOM_RIGHT)
                    .graphic(new ImageView(image));
            notification.darkStyle();
            notification.show();
            }
        }
  
        
    }

    @FXML
    private void cancel(MouseEvent event) {
        Stage home=new Stage();
        Parent root=null;
        
        try {
            root=FXMLLoader.load(getClass().getResource("userScreen.fxml"));
        } catch (IOException ex) {
            Logger.getLogger(AdminScreenController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        Stage current=(Stage)firstName.getScene().getWindow();
        Scene scene=new Scene(root);
        
        home.setScene(scene);
        home.initStyle(StageStyle.TRANSPARENT);
        
        current.hide();
        home.show();
    }


    private void updateStatus() {
        String text = tableNumber.getText().trim();
        String sql = "UPDATE tables SET table_status=? WHERE table_number=?";
        Connection connection = DBConnection.getConnection();
        try {
            PreparedStatement statement = (PreparedStatement)connection.prepareStatement(sql);
            statement.setString(1, "zajety");
            statement.setString(2, text);
            statement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ReservationScreenController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
