/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package restaurantsystem;

import com.sun.javafx.charts.ChartLayoutAnimator;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

/**
 *
 * @author kom1
 */
public class startScreenController implements Initializable {
    
    private Label label;
    @FXML
    private ImageView image;
    
    private void handleButtonAction(ActionEvent event) {
        System.out.println("You clicked me!");
        label.setText("Hello World!");
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        FadeTransition fadeTransition = new FadeTransition(Duration.millis(5000),image);
        fadeTransition.setFromValue(1.0);
        fadeTransition.setToValue(0);
        fadeTransition.setOnFinished(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                Stage login = new Stage();
                Parent root = null;
                try {
                    root = FXMLLoader.load(getClass().getResource("loginScreen.fxml"));
                } catch (IOException ex) {
                    Logger.getLogger(startScreenController.class.getName()).log(Level.SEVERE, null, ex);
                }
                Stage current = (Stage)image.getScene().getWindow();
                Scene scene = new Scene(root,720,600);
                login.setScene(scene);
                login.initStyle(StageStyle.TRANSPARENT);
                current.hide();
                login.show();
            }
        });
        fadeTransition.play();
    }    
    
}
