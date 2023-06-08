package com.example.myproject2;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javax.swing.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class MySqlConnectionBooks {
    Connection connBooks= null;

    public static Connection ConnectDbBooks(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection connBooks = DriverManager.getConnection("jdbc:mysql://localhost/librarydatabase1","librarian","1234");
            JOptionPane.showMessageDialog(null,"Connection Books Established");
            return connBooks;
        }catch (Exception e){
            JOptionPane.showMessageDialog(null,"Books Connection Failed");
            return null;
        }

    }
    public static ObservableList<Books> getDatabooks(){
        Connection connBooks = ConnectDbBooks();
        ObservableList<Books> list = FXCollections.observableArrayList();
        try {
            PreparedStatement ps = connBooks.prepareStatement("select * from librarydatabase1.carti_din_biblioteca");
            ResultSet rs = ps.executeQuery();

            while (rs.next()){

                list.add(new Books(Integer.parseInt(rs.getString("ID")), rs.getString("Book Title"),rs.getString("Author"),rs.getString("Availability")));
            }
        }catch (Exception e){
            e.printStackTrace();
        }


        return list;
    }
}
