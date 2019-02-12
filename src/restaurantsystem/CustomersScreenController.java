/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package restaurantsystem;

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
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.TreeTableView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author kom1
 */
public class CustomersScreenController implements Initializable {
    @FXML
    private TreeTableView<Customer> treeView;
    @FXML
    private Button search;
    @FXML
    private Button back;
    @FXML
    private TextField searchText;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        loadAllCurstomers("SELECT * FROM clients");
    }
    
    public void loadAllCurstomers(String sql)
    {
        TreeTableColumn<Customer, String> table_id = new TreeTableColumn<>("id");
        table_id.setPrefWidth(225);
        table_id.setCellValueFactory((TreeTableColumn.CellDataFeatures<Customer, String> param) -> param.getValue().getValue().id);
        
        
        TreeTableColumn<Customer, String> first_name = new TreeTableColumn<>("Imie");
        first_name.setPrefWidth(225);
        first_name.setCellValueFactory((TreeTableColumn.CellDataFeatures<Customer, String> param) -> param.getValue().getValue().first_name);
        
        TreeTableColumn<Customer, String> last_name = new TreeTableColumn<>("Nazwisko");
        last_name.setPrefWidth(225);
        last_name.setCellValueFactory((TreeTableColumn.CellDataFeatures<Customer, String> param) -> param.getValue().getValue().last_name);        
        
        TreeTableColumn<Customer, String> phone = new TreeTableColumn<>("Telefon");
        phone.setPrefWidth(225);
        phone.setCellValueFactory((TreeTableColumn.CellDataFeatures<Customer, String> param) -> param.getValue().getValue().phone);
        
        TreeTableColumn<Customer, String> email = new TreeTableColumn<>("Email");
        email.setPrefWidth(225);
        email.setCellValueFactory((TreeTableColumn.CellDataFeatures<Customer, String> param) -> param.getValue().getValue().email);
        
        TreeTableColumn<Customer, String> address = new TreeTableColumn<>("Adres");
        address.setPrefWidth(225);
        address.setCellValueFactory((TreeTableColumn.CellDataFeatures<Customer, String> param) -> param.getValue().getValue().address);
        
        TreeTableColumn<Customer, String> payment_type = new TreeTableColumn<>("Płatność");
        payment_type.setPrefWidth(225);
        payment_type.setCellValueFactory((TreeTableColumn.CellDataFeatures<Customer, String> param) -> param.getValue().getValue().payment_type);
        
        TreeTableColumn<Customer, String> people_amount = new TreeTableColumn<>("Ilość ludzi");
        people_amount.setPrefWidth(225);
        people_amount.setCellValueFactory((TreeTableColumn.CellDataFeatures<Customer, String> param) -> param.getValue().getValue().people_amount);
        
        TreeTableColumn<Customer, String> table_number = new TreeTableColumn<>("Numer stolika");
        table_number.setPrefWidth(225);
        table_number.setCellValueFactory((TreeTableColumn.CellDataFeatures<Customer, String> param) -> param.getValue().getValue().table_number);
        
        TreeTableColumn<Customer, String> date = new TreeTableColumn<>("Data");
        date.setPrefWidth(225);
        date.setCellValueFactory((TreeTableColumn.CellDataFeatures<Customer, String> param) -> param.getValue().getValue().date);
        
        TreeTableColumn<Customer, String> start_time = new TreeTableColumn<>("Początek rezerwacji");
        start_time.setPrefWidth(225);
        start_time.setCellValueFactory((TreeTableColumn.CellDataFeatures<Customer, String> param) -> param.getValue().getValue().start_time);
        
        TreeTableColumn<Customer, String> end_time = new TreeTableColumn<>("Koniec rezerwacji");
        end_time.setPrefWidth(225);
        end_time.setCellValueFactory((TreeTableColumn.CellDataFeatures<Customer, String> param) -> param.getValue().getValue().end_time);
        
        TreeTableColumn<Customer, String> price = new TreeTableColumn<>("Cena");
        price.setPrefWidth(225);
        price.setCellValueFactory((TreeTableColumn.CellDataFeatures<Customer, String> param) -> param.getValue().getValue().price);
        
        
        ObservableList<Customer> customers = FXCollections.observableArrayList();
        Connection connection = DBConnection.getConnection();
        try {
            PreparedStatement ps =(PreparedStatement)connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next())
            {
                customers.add(new Customer(rs.getInt(1)+"",rs.getString(2),rs.getString(3),rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9), rs.getString(10), rs.getString(11), rs.getString(12), rs.getString(13)));
            }
        } catch (SQLException ex) {
            Logger.getLogger(AvailableTableController.class.getName()).log(Level.SEVERE, null, ex);
        }
        final TreeItem<Customer> root = new RecursiveTreeItem<Customer>(customers, RecursiveTreeObject::getChildren);
        treeView.getColumns().setAll(table_id,first_name, last_name, phone, email, address, payment_type, people_amount, table_number, date,start_time, end_time,price);
        treeView.setRoot(root);
        treeView.setShowRoot(false);
        
        
        
    }

    @FXML
    private void searchByTableNumber(MouseEvent event) {
        loadAllCurstomers("SELECT * FROM clients WHERE table_number ='"+searchText.getText().trim()+"'");
    }

    @FXML
    private void backToHome(MouseEvent event) {
        Stage home=new Stage();
        Parent root=null;
        
        try {
            root=FXMLLoader.load(getClass().getResource("adminScreen.fxml"));
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
