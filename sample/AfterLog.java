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

    public Label accountIDField;
    public Label accNumField;
    public Label amountField;

    ArrayList<Client> clientList;
    ArrayList<Account> accList;

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
    @FXML
    ComboBox<String> comboboxAcc;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {


        fillDropdown();
    }
    public void fillDropdown(){
        try {
            System.out.println("Filled successfully");
            clientList = Globals.db.selectClients();
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

        Client selectedUser=Globals.db.selectClientInfo(getIDClient());
        System.out.println(selectedUser);
        clientName.setText(selectedUser.getFirstname());
        clientSurname.setText(selectedUser.getLastname());
        clientMail.setText(String.valueOf(selectedUser.getMail()));
        fillDropdownAccounts();
    }
    public int getIDAccount() {
        return accList.get(comboboxAcc.getSelectionModel().getSelectedIndex()).getIDacc();
    }

    public int getIDofSelected() {
        System.out.println(combobox.getSelectionModel().getSelectedIndex());
        return combobox.getSelectionModel().getSelectedIndex();
    }

    public int getIDClient() {
        System.out.println(clientList.get(combobox.getSelectionModel().getSelectedIndex()).getID());
        return clientList.get(combobox.getSelectionModel().getSelectedIndex()).getID();
    }

    public void logout(ActionEvent actionEvent) {
        Stage stage = (Stage) logout.getScene().getWindow();
        stage.close();
    }

    public void fillDropdownAccounts(){
        accList = Globals.db.selectAccountsToList(getIDClient());
        ObservableList<String> oblist = FXCollections.observableArrayList();

        for(int i=0; i<accList.size();i++) {
            oblist.add(accList.get(i).getIDacc() + " " + accList.get(i).getAccNum());
        }

        comboboxAcc.setItems(oblist);
    }

    public void AccInfo() throws SQLException {

        Account acc = Globals.db.selectAccInfo(getIDAccount());
        accountIDField.setText(String.valueOf(acc.getIDacc()));
        accNumField.setText(acc.getAccNum());
        amountField.setText(String.valueOf(acc.getMoney()));

    }








}
