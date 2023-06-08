package com.example.myproject2;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javax.swing.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class SqlConnectLBM {
    public static Connection ConnectLibraryManager(){
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
    public static ObservableList<LentBooks> getLentBooksinTable(){
        Connection conn = ConnectLibraryManager();
        ObservableList<LentBooks> list = FXCollections.observableArrayList();
        try {
            PreparedStatement ps = conn.prepareStatement("select * from librarydatabase1.carti_imprumutate");
            ResultSet rs = ps.executeQuery();

            while (rs.next()){

                list.add(new LentBooks
                        (Integer.parseInt(rs.getString("Nr.")),
                        Integer.parseInt(rs.getString("Subscription ID")),
                        rs.getString("First Name"),
                        rs.getString("Last Name"),
                        rs.getString("Book Title")));
            }
        } catch (Exception e){
            e.printStackTrace();
        }

        return list;
    }
}
