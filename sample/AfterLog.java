package sample;

import com.sun.javafx.charts.Legend;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class AfterLog implements Initializable {
    public Button logout;


    public void clients(ActionEvent actionEvent) {

    }

    public void newClient(ActionEvent actionEvent) {

    }
    @FXML
    ComboBox<String> combobox;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println("ide");

        fillDropdown();
    }
    public void fillDropdown(){
        try {
            ArrayList<Client> clientList = Globals.db.selectClients();
            ObservableList<String> oblist = FXCollections.observableArrayList();
            for (int i = 0; i < clientList.size(); i++) {
                oblist.add(clientList.get(i).getFirstname() + " " + clientList.get(i).getLastname());
            }
            combobox.setItems(oblist);
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void logout(ActionEvent actionEvent) {
        Stage stage = (Stage) logout.getScene().getWindow();
        stage.close();
    }


    //TODO: SHOW USER NAME AFTER LOGIN
    //TODO: SHOW ERROR LABEL IF LOGIN IS NOT SUCCESFULL



}
