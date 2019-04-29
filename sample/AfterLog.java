package sample;

import com.sun.javafx.charts.Legend;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class AfterLog implements Initializable {
    public Button logout;
    public Label afterLogName;
    public Label afterLogSurname;
    public Label afterLogPosition;

    public Label clientName;
    public Label clientSurname;
    public Label clientMail;

    ArrayList<Client> clientList;

    public void setupAfterlog(Employee person, String position) {

        String name = person.getFirstname();
        String surname = person.getSurname();

        afterLogName.setText(name);
        afterLogSurname.setText(surname);
        afterLogPosition.setText(position);


    }


    public void clients(ActionEvent actionEvent) {

    }

    public void newClient(ActionEvent actionEvent) {

    }
    @FXML
    ComboBox<String> combobox;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {


        fillDropdown();
    }
    public void fillDropdown(){
        try {
            System.out.println("Filled successfully");
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

    public void clientInfo() throws SQLException {

        Client selectedUser=Globals.db.selectClientInfo(clientList.get(getIDofSelected()).getID());
        System.out.println(selectedUser);
        clientName.setText(selectedUser.getFirstname());
        clientSurname.setText(selectedUser.getLastname());
        clientMail.setText(String.valueOf(selectedUser.getMail()));
        fillDropdown();
    }

    public int getIDofSelected() {
        System.out.println(combobox.getSelectionModel().getSelectedIndex());
        return combobox.getSelectionModel().getSelectedIndex();
    }

    public void logout(ActionEvent actionEvent) {
        Stage stage = (Stage) logout.getScene().getWindow();
        stage.close();
    }






}
