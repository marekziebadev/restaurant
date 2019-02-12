/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package restaurantsystem;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author kom1
 */
public class UserScreenController implements Initializable {
    @FXML
    private Pane pane_2;
    private Pane pane_3;
    @FXML
    private Pane pane_1;
    @FXML
    private Pane pane_4;
    @FXML
    private Pane pane_5;
    @FXML
    private StackPane stackpane;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void pane_2_exited(MouseEvent event) {
        pane_2.setStyle("-fx-background-color: white; -fx-background-radius: 6px;");
    }

    @FXML
    private void pane_2_hover(MouseEvent event) {
        pane_2.setStyle("-fx-background-color: #377ce8; -fx-background-radius: 6px;");
    }

    private void pane_3_exited(MouseEvent event) {
        pane_3.setStyle("-fx-background-color: white; -fx-background-radius: 6px;");
    }

    private void pane_3_hover(MouseEvent event) {
        pane_3.setStyle("-fx-background-color: #377ce8; -fx-background-radius: 6px;");
    }

    @FXML
    private void pane_1_exited(MouseEvent event) {
        pane_1.setStyle("-fx-background-color: white; -fx-background-radius: 6px;");
    }

    @FXML
    private void pane_1_hover(MouseEvent event) {
        pane_1.setStyle("-fx-background-color: #377ce8; -fx-background-radius: 6px;");
    }

    @FXML
    private void pane_4_exited(MouseEvent event) {
        pane_4.setStyle("-fx-background-color: white; -fx-background-radius: 6px;");
    }

    @FXML
    private void pane_4_hover(MouseEvent event) {
        pane_4.setStyle("-fx-background-color: #377ce8; -fx-background-radius: 6px;");
    }

    @FXML
    private void pane_5_exited(MouseEvent event) {
        pane_5.setStyle("-fx-background-color: white; -fx-background-radius: 6px;");
    }

    @FXML
    private void pane_5_hover(MouseEvent event) {
        pane_5.setStyle("-fx-background-color: #377ce8; -fx-background-radius: 6px;");
    }

    @FXML
    private void employees(MouseEvent event) {
        Stage s = new Stage();
        Parent root = null;
        try {            
            root = FXMLLoader.load(getClass().getResource("searchTable.fxml"));
        } catch (IOException ex) {
            Logger.getLogger(AdminScreenController.class.getName()).log(Level.SEVERE, null, ex);
        }
        Stage current = (Stage)pane_1.getScene().getWindow();
        Scene scene = new Scene(root);
        
        s.setScene(scene);
        s.initStyle(StageStyle.TRANSPARENT);
        
        current.hide();
        s.show();
    }

    private void custom_information(MouseEvent event) {
        Stage s = new Stage();
        Parent root = null;
        try {            
            root = FXMLLoader.load(getClass().getResource("customersScreen.fxml"));
        } catch (IOException ex) {
            Logger.getLogger(AdminScreenController.class.getName()).log(Level.SEVERE, null, ex);
        }
        Stage current = (Stage)pane_1.getScene().getWindow();
        Scene scene = new Scene(root);
        
        s.setScene(scene);
        s.initStyle(StageStyle.TRANSPARENT);
        
        current.hide();
        s.show();
    }

    @FXML
    private void reservation(MouseEvent event) {
        Stage s = new Stage();
        Parent root = null;
        try {            
            root = FXMLLoader.load(getClass().getResource("ReservationScreen.fxml"));
        } catch (IOException ex) {
            Logger.getLogger(AdminScreenController.class.getName()).log(Level.SEVERE, null, ex);
        }
        Stage current = (Stage)pane_1.getScene().getWindow();
        Scene scene = new Scene(root);
        
        s.setScene(scene);
        s.initStyle(StageStyle.TRANSPARENT);
        
        current.hide();
        s.show();
    }

    @FXML
    private void logout(MouseEvent event) {
        JFXDialogLayout dialogLayout = new JFXDialogLayout();
        dialogLayout.setHeading(new Text("Zamknij"));
        dialogLayout.setBody(new Text("Czy chcesz zamknąć aplikacje?"));
        
        JFXButton button = new JFXButton("TAK");
        JFXButton button2 = new JFXButton("NIE");
        JFXDialog dialog = new JFXDialog(stackpane,dialogLayout,JFXDialog.DialogTransition.CENTER);
        button.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                 Stage s = new Stage();
        Parent root = null;
        try {            
            root = FXMLLoader.load(getClass().getResource("loginScreen.fxml"));
        } catch (IOException ex) {
            Logger.getLogger(AdminScreenController.class.getName()).log(Level.SEVERE, null, ex);
        }
        Stage current = (Stage)pane_1.getScene().getWindow();
        Scene scene = new Scene(root);
        
        s.setScene(scene);
        s.initStyle(StageStyle.TRANSPARENT);
        
        current.hide();
        s.show();
            }
        });
        button2.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                dialog.close();
            }
        });
        dialogLayout.setActions(button,button2);
        dialog.show();
    }

    @FXML
    private void exit(MouseEvent event) {
        JFXDialogLayout dialogLayout = new JFXDialogLayout();
        dialogLayout.setHeading(new Text("Wyjście"));
        dialogLayout.setBody(new Text("Czy chcesz zamknąć aplikacje?"));
        
        JFXButton button = new JFXButton("TAK");
        JFXButton button2 = new JFXButton("NIE");
        JFXDialog dialog = new JFXDialog(stackpane,dialogLayout,JFXDialog.DialogTransition.CENTER);
        button.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                 System.exit(0);
            }
        });
        button2.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                dialog.close();
            }
        });
        dialogLayout.setActions(button,button2);
        dialog.show();
    }
    
}
