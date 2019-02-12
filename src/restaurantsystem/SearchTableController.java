/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package restaurantsystem;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import com.jfoenix.controls.RecursiveTreeItem;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.TreeTableView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author kom1
 */
public class SearchTableController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
    String status = null;
    @FXML
    private TreeTableView<Table> treeView;
    @FXML
    private TextField searchText;
    @FXML
    private CheckBox busy;
    @FXML
    private CheckBox available;
    @FXML
    private StackPane stackpane;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        loadAllTables("SELECT * FROM tables");
    }    
    public void loadAllTables(String sql)
    {
        TreeTableColumn<Table, String> table_id = new TreeTableColumn<>("table_id");
        table_id.setPrefWidth(225);
        table_id.setCellValueFactory((TreeTableColumn.CellDataFeatures<Table, String> param) -> param.getValue().getValue().id);
        
        
        TreeTableColumn<Table, String> table_number = new TreeTableColumn<>("number");
        table_number.setPrefWidth(225);
        table_number.setCellValueFactory((TreeTableColumn.CellDataFeatures<Table, String> param) -> param.getValue().getValue().table_number);
        
        TreeTableColumn<Table, String> table_status = new TreeTableColumn<>("table status");
        table_status.setPrefWidth(225);
        table_status.setCellValueFactory((TreeTableColumn.CellDataFeatures<Table, String> param) -> param.getValue().getValue().table_status);
        
        TreeTableColumn<Table, String> table_price = new TreeTableColumn<>("price");
        table_price.setPrefWidth(225);
        table_price.setCellValueFactory((TreeTableColumn.CellDataFeatures<Table, String> param) -> param.getValue().getValue().price);
        
        ObservableList<Table> tables = FXCollections.observableArrayList();
        Connection connection = DBConnection.getConnection();
        try {
            PreparedStatement ps =(PreparedStatement)connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next())
            {
                tables.add(new Table(rs.getInt(1)+"",rs.getString(2),rs.getString(3),rs.getString(4)));
            }
        } catch (SQLException ex) {
            Logger.getLogger(AvailableTableController.class.getName()).log(Level.SEVERE, null, ex);
        }
        final TreeItem<Table> root = new RecursiveTreeItem<Table>(tables, RecursiveTreeObject::getChildren);
        treeView.getColumns().setAll(table_id,table_number,table_status,table_price);
        treeView.setRoot(root);
        treeView.setShowRoot(false);
        
        
        
    }

    @FXML
    private void searchByTableNumber(MouseEvent event) {
        loadAllTables("SELECT * FROM tables WHERE table_number ='"+searchText.getText().trim()+"'");
    }

    @FXML
    private void searchByStatus(MouseEvent event) {
        loadAllTables(status);
    }

    @FXML
    private void searchBusy(ActionEvent event) {
        available.setSelected(false);
        status="SELECT * FROM tables WHERE table_status = 'zajety' ";
    }

    @FXML
    private void searchAvailable(ActionEvent event) {
        busy.setSelected(false);
        status="SELECT * FROM tables WHERE table_status = 'dostepny' ";
        
    }

    @FXML
    private void close(MouseEvent event) {
        JFXDialogLayout dialogLayout=new JFXDialogLayout();
        dialogLayout.setHeading(new Text("Zamknij"));
        dialogLayout.setBody(new Text("Czy chcesz zamknąć?"));
        
        JFXButton ok=new JFXButton("Ok");
        JFXButton Cancel=new JFXButton("Wróć");
        
        JFXDialog dialog=new JFXDialog(stackpane, dialogLayout, JFXDialog.DialogTransition.CENTER);
        
        ok.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.exit(0);
            }
        });
        
        Cancel.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                dialog.close();
            }
        });
        
        dialogLayout.setActions(ok,Cancel);
        dialog.show();
    }

    @FXML
    private void back(MouseEvent event) {
        Stage home=new Stage();
        Parent root=null;
        
        try {
            root=FXMLLoader.load(getClass().getResource("userScreen.fxml"));
        } catch (IOException ex) {
            Logger.getLogger(AdminScreenController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        Stage current=(Stage)searchText.getScene().getWindow();
        Scene scene=new Scene(root);
        
        home.setScene(scene);
        home.initStyle(StageStyle.TRANSPARENT);
        
        current.hide();
        home.show();
    }
    
}
    
