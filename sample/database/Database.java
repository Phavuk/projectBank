package sample.database;

import javafx.scene.control.Alert;
import sample.Account;
import sample.Client;
import sample.Employee;
import sample.Globals;

import javax.xml.crypto.Data;
import java.sql.*;
import java.util.ArrayList;

public class Database {


    private static Database db = new Database();

    private Database(){

    }

    public static Database getInstance() {
        return db;
    }

    public static Connection getConnection(){
        Connection connection;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(Globals.url, Globals.username, Globals.password);
            return connection;
        }
        catch (ClassNotFoundException e){
            e.printStackTrace();
        }
        catch (SQLException e){
            e.printStackTrace();
        }

        return null;
    }

    public Client showClient(String Firstname, String Lastname){
        Connection conn = getConnection();
        PreparedStatement pst = null;
        ResultSet rs;

        try {

            pst = conn.prepareStatement("SELECT * FROM Client");
            pst.setString(1,Firstname);
            pst.setString(2,Lastname);

            rs = pst.executeQuery();
            System.out.println(Firstname);
            System.out.println(Lastname);
            while (rs.next()) {
                System.out.println("It works");
                System.out.println("Finally");

                Client client = new Client(rs.getString("fname"), rs.getString("lname"),rs.getString("email"),rs.getInt("ID"));
                return client;
            }
            conn.close();

        }catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }


    public Employee compareEmployee(String name, String pass){

        Connection conn = getConnection();
        PreparedStatement pst = null;
        ResultSet rs;

        try {

            pst = conn.prepareStatement("SELECT * FROM Employee INNER JOIN loginEmp ON Employee.ID = loginEmp.IDE WHERE login LIKE ? and password LIKE ?" );
            pst.setString(1,name);
            pst.setString(2,pass);
            rs = pst.executeQuery();
            while (rs.next()) {


                Employee person = new Employee(rs.getString("fname"), rs.getString("lname"),
                        rs.getInt("position"), rs.getInt("id"));
                return person;
            }
            conn.close();

        }catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    public String getPosition(Employee emp){
        int position = emp.getPosition();
        String boss = "boss";
        String common = "common";

        if (position == 1) {
            return common;
        }
        else
        {
            return boss;
        }
    }
    public ArrayList<Client> selectClients() throws SQLException {
        Connection conn = getConnection();
        String query = "SELECT * FROM client";
        ArrayList <Client> clientList = new ArrayList<>();

        try{
            PreparedStatement pst = null;
            ResultSet rs = null;
            pst = conn.prepareStatement(query);
            rs = pst.executeQuery();
            while (rs.next()){
                Client client = new Client(rs.getString("fname"),rs.getString("lname"),rs.getString("email"),rs.getInt("ID"));
                clientList.add(client);
            }
            return clientList;
        }catch (SQLException e){
            e.printStackTrace();
        }
        return null;
  }


    public Client selectClientInfo(int id){
        Connection conn = getConnection();
        Client client = null;
        String query = "SELECT * FROM client where ID like ? ";

        try {
            PreparedStatement pst = null;
            ResultSet rs = null;
            pst = conn.prepareStatement(query);
            pst.setString(1,String.valueOf(id));
            rs = pst.executeQuery();

            while (rs.next()) {

                client = new Client(rs.getString("fname"),rs.getString("lname"),rs.getString("email"),
                        rs.getInt("ID"));

            }
            return client;

        }catch(SQLException e){
            e.printStackTrace();
        }

        return null;
    }

    public ArrayList<Account> selectAccountsToList(int id) {Connection conn = getConnection();
    ArrayList <Account> accounts = new ArrayList<>();
    try {              PreparedStatement pst = null;
            ResultSet rs = null;
            pst = conn.prepareStatement("SELECT * FROM Account where IDC like ? ");
            pst.setInt(1,id);             rs = pst.executeQuery();
            while (rs.next()) {Account acc = new Account(rs.getInt("ID"),rs.getString("AccNum"),rs.getDouble("amount"),rs.getInt("IDC"));                 System.out.println(acc.getAccNum());
        accounts.add(acc);
            }}catch (SQLException e){e.printStackTrace();
    }         return accounts;}

    public Account selectAccInfo(int id){
        Connection conn = getConnection();
        Account acc = null;

        try {
            PreparedStatement pst = null;
            ResultSet rs = null;
            pst = conn.prepareStatement("SELECT * FROM Account where id like ? ");
            pst.setInt(1,id);
            rs = pst.executeQuery();

            while (rs.next()) {

                acc = new Account(rs.getInt("id"),rs.getString("AccNum"),rs.getDouble("amount"),
                        rs.getInt("idc"));
            }
            return acc;

        }catch(SQLException e){
            e.printStackTrace();
        }

        return null;
    }

}

