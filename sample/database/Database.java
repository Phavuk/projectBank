package sample.database;

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
            System.out.println("DriverLoaded");
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

            pst = conn.prepareStatement("SELECT * FROM Employee INNER JOIN loginemployee ON Employee.id = loginEmployee.IDEmployee WHERE login LIKE ? and password LIKE ?" );
            pst.setString(1,name);
            pst.setString(2,pass);
            rs = pst.executeQuery();
            while (rs.next()) {
                System.out.println("It works");
                System.out.println("Finally");

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

    public ArrayList<Client> selectClients() throws SQLException {
        Connection conn = getConnection();
        String query = "SELECT * FROM Client";
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

}

