package com.example.myproject2;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;

public class MySqlConnection {
     Connection conn = null;


    public static Connection ConnectDb(){
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/librarydatabase1","librarian","1234");
            JOptionPane.showMessageDialog(null, "Connection Established");
            return conn;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Connection Failed");
            return null;
        }
    }

    public static ObservableList<Users> getDatausers(){
        Connection conn = ConnectDb();
        ObservableList<Users> list = FXCollections.observableArrayList();
        try {
            PreparedStatement ps = conn.prepareStatement("select * from librarydatabase1.abonament_biblioteca");
            ResultSet rs = ps.executeQuery();

            while (rs.next()){

                list.add(new Users(Integer.parseInt(rs.getString("Subscription ID")),Integer.parseInt(rs.getString("Book in possesion ID")),rs.getString("First Name"),
                        rs.getString("Last Name"),rs.getString("Phone Number"),rs.getString("Email Address")));
            }
        } catch (Exception e){
            e.printStackTrace();
        }

        return list;
    }
}

