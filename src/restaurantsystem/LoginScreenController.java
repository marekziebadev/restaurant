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
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;

/**
 * FXML Controller class
 *
 * @author kom1
 */
public class LoginScreenController implements Initializable {
    @FXML
    private TextField login;
    @FXML
    private TextField password;
    @FXML
    private Button loginButton;
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
    private void makeLogin(MouseEvent event) {
        if(login.getText().equals(""))
        {
            Image image = new Image("img/x-1152114.svg");
            Notifications notification = Notifications.create().title("Error").text("Pusta nazwa użytkownika")
                    .hideAfter(Duration.seconds(5)).position(Pos.BOTTOM_RIGHT)
                    .graphic(new ImageView(image));
            notification.darkStyle();
            notification.show();
        }
        else if(password.getText().equals(""))
        {
            Image image = new Image("img/x-1152114.svg");
            Notifications notification = Notifications.create().title("Error").text("Brak hasła")
                    .hideAfter(Duration.seconds(5)).position(Pos.BOTTOM_RIGHT)
                    .graphic(new ImageView(image));
            notification.darkStyle();
            notification.show();
        }
        else{
        
        boolean find = false;
        String userPassword = "";
        String userType = "";
        String sqlQuery = "select * from users where username='"+login.getText().trim()+"'";
        Connection connection = DBConnection.getConnection();
        PreparedStatement statement;
        try {
            statement = (PreparedStatement)connection.prepareStatement(sqlQuery);
            ResultSet set = statement.executeQuery();
        while(set.next())
        {
            find = true;
            userPassword = set.getString(3);
            userType = set.getString(6);
            
        }
        if(find)
        {
            if(password.getText().trim().equals(userPassword))
            {
                if(userType.equals("admin"))
                {
                    Stage stage = new Stage();
                    Parent root = null;
                    try {
                        root = FXMLLoader.load(getClass().getResource("adminScreen.fxml"));
                    } catch (IOException ex) {
                        Logger.getLogger(LoginScreenController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    Stage current = (Stage) password.getScene().getWindow();
                    Scene scene = new Scene(root,1366,730);
                    stage.setScene(scene);
                    stage.initStyle(StageStyle.TRANSPARENT);
                    
                    current.hide();
                    stage.show();
                }
                else
                {
                    Stage stage = new Stage();
                    Parent root = null;
                    try {
                        root = FXMLLoader.load(getClass().getResource("userScreen.fxml"));
                    } catch (IOException ex) {
                        Logger.getLogger(LoginScreenController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    Stage current = (Stage) password.getScene().getWindow();
                    Scene scene = new Scene(root,1366,730);
                    stage.setScene(scene);
                    stage.initStyle(StageStyle.TRANSPARENT);
                    
                    current.hide();
                    stage.show();
                }
            }
            else
            {
                 Image image = new Image("img/x-1152114.svg");
                Notifications notification = Notifications.create().title("Error").text("Błędny login lub hasło")
                    .hideAfter(Duration.seconds(5)).position(Pos.BOTTOM_RIGHT)
                    .graphic(new ImageView(image));
            notification.darkStyle();
            notification.show();
            }
        }
        } catch (SQLException ex) {
            Logger.getLogger(LoginScreenController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        }
    }

    @FXML
    private void cancelButton(MouseEvent event) {
        JFXDialogLayout dialogLayout = new JFXDialogLayout();
        dialogLayout.setHeading(new Text("Zamknij"));
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
