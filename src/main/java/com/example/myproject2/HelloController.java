package com.example.myproject2;



import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import javax.swing.*;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;

public class HelloController implements Initializable {

    /// First Tab ///

    @FXML
    private TableColumn<Users, Integer> books;

    @FXML
    private TableColumn<Users, String> email;

    @FXML
    private TableColumn<Users, String> fname;

    @FXML
    private TableColumn<Users, String> lname;

    @FXML
    private TableColumn<Users, String> phone;

    @FXML
    private TableColumn<Users, Integer> subs_id;

    @FXML
    private TableView<Users> table_users;

    @FXML
    private TextField txt_email;

    @FXML
    private TextField txt_fname;

    @FXML
    private TextField txt_lname;

    @FXML
    private TextField txt_phone;


    @FXML
    private TextField txt_id;

    //// Second Tab ///

    @FXML
    private TableView<Books> table_books;


    @FXML
    private TableColumn<Books, String> col_author;

    @FXML
    private TableColumn<Books, String> col_availability;

    @FXML
    private TableColumn<Books, Integer> col_book_id;

    @FXML
    private TableColumn<Books, String> col_btitle;

    @FXML
    private TextField txt_bauthor;

    @FXML
    private TextField txt_bid;

    @FXML
    private TextField txt_btitle;

    //Third Tab//

    @FXML
    private TextField Txt_B_title;

    @FXML
    private TextField Txt_F_name;

    @FXML
    private TextField Txt_L_name;

    @FXML
    private TextField Txt_sub_ID;

    //Book manager table//

    @FXML
    private TableView<Books> second_table_books;

    @FXML
    private TableColumn<Books, String> second_col_author;

    @FXML
    private TableColumn<Books, String> second_col_availability;

    @FXML
    private TableColumn<Books, Integer> second_col_book_id;

    @FXML
    private TableColumn<Books, String> second_col_btitle;

    // Subscription Manager Table //

    @FXML
    private TableView<Users> second_table_sub_manager;

    @FXML
    private TableColumn<Users, Integer> second_books;

    @FXML
    private TableColumn<Users, String> second_email;

    @FXML
    private TableColumn<Users, String> second_fname;

    @FXML
    private TableColumn<Users, String> second_lname;

    @FXML
    private TableColumn<Users, String> second_phone;

    @FXML
    private TableColumn<Users, Integer> second_subs_id;

    // Library manager table //

    @FXML
    private TableView<LentBooks> lent_books_table;

    @FXML
    private TableColumn<LentBooks, Integer> col_nr;

    @FXML
    private TableColumn<LentBooks, String> col_btitle_Repeat;

    @FXML
    private TableColumn<LentBooks, String> col_fname_Repeat;

    @FXML
    private TableColumn<LentBooks, String> col_lname_Repeat;

    @FXML
    private TableColumn<LentBooks, Integer> col_subID_repeat;



    //Book Manager Tab//
    ObservableList<Books> listB;
    int indexBooks = -1;

    Connection connBooks = null;
    ResultSet rsBooks = null;
    PreparedStatement pstBooks = null;


// Subscription Manager Tab//
    ObservableList<Users> listM;
    int index = -1;

    Connection conn = null;
    ResultSet rs = null;
    PreparedStatement pst = null;

    // Library manager tab //

    ObservableList<LentBooks> listU;

    int indexLent = -1;
    int indexLent2 = -1;
    Connection connLent = null;

    ResultSet rsLent = null;

    PreparedStatement pstLent = null;

// Methods for Subscription manager tab //
    public void Add_users(){
         conn = MySqlConnection.ConnectDb();
         String sql = "INSERT INTO `librarydatabase1`.`abonament_biblioteca` (`First Name`, `Last Name`, `Phone Number`, `Email Address`) VALUES(?,?,?,?)";
         try{
             pst = conn.prepareStatement(sql);
             pst.setString(1, txt_fname.getText());
             pst.setString(2, txt_lname.getText());
             pst.setString(3, txt_phone.getText());
             pst.setString(4, txt_email.getText());
             pst.execute();

             JOptionPane.showMessageDialog(null,"Succesfuly added");
            UpdateTable();
         }catch(Exception e){
             JOptionPane.showMessageDialog(null,e);

        }
    }

    /////Select method /////

   public void getSelected(javafx.scene.input.MouseEvent mouseEvent) {
        index = table_users.getSelectionModel().getSelectedIndex();
        if(index <= -1){

            return;
        }

        txt_id.setText(subs_id.getCellData(index).toString());
       txt_email.setText(email.getCellData(index).toString());
        txt_phone.setText(phone.getCellData(index).toString());
        txt_fname.setText(fname.getCellData(index).toString());
        txt_lname.setText(lname.getCellData(index).toString());
    }



    public void Edit () {
        try{
            conn = MySqlConnection.ConnectDb();
            String value0 = txt_id.getText();
            String value1 = txt_fname.getText();
            String value2 = txt_lname.getText();
            String value3 = txt_phone.getText();
            String value4 = txt_email.getText();


            String sql = "UPDATE `librarydatabase1`.`abonament_biblioteca` SET `First Name` = '"+value1+"'," +
                    " `Last Name` = '"+value2+"', `Phone Number` = '"+value3+"', `Email Address` = '"+value4+"'WHERE (`Subscription ID` = '"+value0+"')";
            pst = conn.prepareStatement(sql);
            pst.execute();
            JOptionPane.showMessageDialog(null,"Updated Succesfuly");
            UpdateTable();
        }catch (Exception e){
            JOptionPane.showMessageDialog(null,"Failed to Updated");
        }
    }

        public void Delete(){
        conn = MySqlConnection.ConnectDb();
        String sql = "DELETE FROM librarydatabase1.abonament_biblioteca WHERE `Subscription ID` = ?";
        try{
            pst = conn.prepareStatement(sql);
            pst.setString(1, txt_id.getText());
            pst.execute();
            JOptionPane.showMessageDialog(null,"Deleted Succesfuly");
            UpdateTable();
        }catch (Exception e){
            JOptionPane.showMessageDialog(null,"Deletion Failed");
        }
        }
    public void UpdateTable(){
        subs_id.setCellValueFactory(new PropertyValueFactory<Users,Integer>("id"));
        fname.setCellValueFactory(new PropertyValueFactory<Users,String>("fname"));
        lname.setCellValueFactory(new PropertyValueFactory<Users,String>("lname"));
        phone.setCellValueFactory(new PropertyValueFactory<Users,String>("phone"));
        email.setCellValueFactory(new PropertyValueFactory<Users,String>("email"));
        books.setCellValueFactory(new PropertyValueFactory<Users,Integer>("books"));


        listM = MySqlConnection.getDatausers();
        table_users.setItems(listM);

    }

    // Second Tab book manager //

    public void Add_Book(){
        connBooks = MySqlConnectionBooks.ConnectDbBooks();
        String sql = "INSERT INTO `librarydatabase1`.`carti_din_biblioteca` (`Book Title`, `Author`) VALUES (?, ?)";
        try{
            pstBooks = connBooks.prepareStatement(sql);
            pstBooks.setString(1, txt_btitle.getText());
            pstBooks.setString(2, txt_bauthor.getText());

            pstBooks.execute();

            JOptionPane.showMessageDialog(null,"New Book Added");
            Update_Books();
        }catch (Exception e) {
                JOptionPane.showMessageDialog(null,"Could not add Book");
            e.printStackTrace();
        }
    }

    public void Update_Books(){
        col_book_id.setCellValueFactory(new PropertyValueFactory<Books, Integer>("id"));
        col_btitle.setCellValueFactory(new PropertyValueFactory<Books, String>("btitle"));
        col_author.setCellValueFactory(new PropertyValueFactory<Books, String>("author"));
        col_availability.setCellValueFactory(new PropertyValueFactory<Books, String>("Availability"));

        listB = MySqlConnectionBooks.getDatabooks();
        table_books.setItems(listB);
    }

    public void SelectBook(javafx.scene.input.MouseEvent mouseEvent){
        indexBooks = table_books.getSelectionModel().getSelectedIndex();
        if(indexBooks <= -1){
            return;
        }
        txt_bid.setText(col_book_id.getCellData(indexBooks).toString());
        txt_btitle.setText(col_btitle.getCellData(indexBooks).toString());
        txt_bauthor.setText(col_author.getCellData(indexBooks).toString());
    }

    public void UpdateLibrary(){
        try { connBooks = MySqlConnectionBooks.ConnectDbBooks();
            String value1 = txt_btitle.getText();
            String value2 = txt_bauthor.getText();
            String value3 = txt_bid.getText();

            String sql = "UPDATE `librarydatabase1`.`carti_din_biblioteca` " +
                    "SET `Book Title` = '" + value1 + "', Author = '" + value2 + "' " +
                    "WHERE ID = '" + value3 + "'";
            pstBooks = connBooks.prepareStatement(sql);
            pstBooks.execute();
            JOptionPane.showMessageDialog(null,"Succes");
            Update_Books();
        }catch (Exception e){
            JOptionPane.showMessageDialog(null,"Failed");
            e.printStackTrace();
        }
    }
    public void Delete_Book(){
        connBooks = MySqlConnectionBooks.ConnectDbBooks();
        String sql = "DELETE FROM librarydatabase1.carti_din_biblioteca WHERE `ID` = ?";
        try{
            pstBooks = connBooks.prepareStatement(sql);
            pstBooks.setString(1, txt_bid.getText());
            pstBooks.execute();
            JOptionPane.showMessageDialog(null,"Deleted Succesfuly");
            Update_Books();
        }catch (Exception e){
            JOptionPane.showMessageDialog(null,"Failed");
            e.printStackTrace();
        }
    }
        //Function for Library manager tab
    public void lbm_Display_Subscription(){
        second_subs_id.setCellValueFactory(new PropertyValueFactory<Users,Integer>("id"));
        second_fname.setCellValueFactory(new PropertyValueFactory<Users,String>("fname"));
        second_lname.setCellValueFactory(new PropertyValueFactory<Users,String>("lname"));
        second_phone.setCellValueFactory(new PropertyValueFactory<Users,String>("phone"));
        second_email.setCellValueFactory(new PropertyValueFactory<Users,String>("email"));
        second_books.setCellValueFactory(new PropertyValueFactory<Users,Integer>("books"));

        listM = MySqlConnection.getDatausers();
        second_table_sub_manager.setItems(listM);
    }
    //Function for Library manager tab
    public void lbm_Display_Books(){
        second_col_book_id.setCellValueFactory(new PropertyValueFactory<Books, Integer>("id"));
        second_col_btitle.setCellValueFactory(new PropertyValueFactory<Books, String>("btitle"));
        second_col_author.setCellValueFactory(new PropertyValueFactory<Books, String>("author"));
        second_col_availability.setCellValueFactory(new PropertyValueFactory<Books, String>("Availability"));

        listB = MySqlConnectionBooks.getDatabooks();
        second_table_books.setItems(listB);
    }

    public void lbm_Display_LentBooks(){
        col_nr.setCellValueFactory(new PropertyValueFactory<LentBooks, Integer>("nr"));
        col_subID_repeat.setCellValueFactory(new PropertyValueFactory<LentBooks, Integer>("subscription_id"));
        col_fname_Repeat.setCellValueFactory(new PropertyValueFactory<LentBooks, String>("first_name"));
        col_lname_Repeat.setCellValueFactory(new PropertyValueFactory<LentBooks, String>("last_name"));
        col_btitle_Repeat.setCellValueFactory(new PropertyValueFactory<LentBooks, String>("book_title"));

        listU = SqlConnectLBM.getLentBooksinTable();
        lent_books_table.setItems(listU);

    }

    public void lbm_GetInfoLent(javafx.scene.input.MouseEvent mouseEvent){
            indexLent = second_table_sub_manager.getSelectionModel().getSelectedIndex();


        if(indexLent <= -1){

            return;
        }
        try {
            Txt_sub_ID.setText(second_subs_id.getCellData(indexLent).toString());
            Txt_F_name.setText(second_fname.getCellData(indexLent).toString());
            Txt_L_name.setText(second_lname.getCellData(indexLent).toString());

        }catch (Exception e){
            JOptionPane.showMessageDialog(null,"Failed");
            e.printStackTrace();
        }

    }
    public void lbm_GetBookTitle(javafx.scene.input.MouseEvent mouseEvent){
        indexLent = second_table_books.getSelectionModel().getSelectedIndex();
        if(indexLent <= -1){
            return;
        }

        try {
            Txt_B_title.setText(second_col_btitle.getCellData(indexLent).toString());
        }catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Failed");
            e.printStackTrace();
        }
    }
    public void lbm_change_book_Status() {
        String value1 = Txt_sub_ID.getText();
        String value2 = Txt_B_title.getText();

        connBooks = MySqlConnectionBooks.ConnectDbBooks();
        String sql2 = "UPDATE `librarydatabase1`.`carti_din_biblioteca` SET `Availability` = 'Lent' WHERE (`Book Title` = '"+value2+"')";

        conn = MySqlConnection.ConnectDb();
        String sql1 = "UPDATE `librarydatabase1`.`abonament_biblioteca` SET `Book in possesion ID` = `Book in possesion ID` + 1 WHERE (`Subscription ID` = '"+value1+"')";

        try {
            Statement stmt = connBooks.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT `Availability` FROM `librarydatabase1`.`carti_din_biblioteca` WHERE `Book Title` = '"+value2+"'");

            if (rs.next()) {
                String availability = rs.getString("Availability");
                if (availability.equalsIgnoreCase("Lent")) {
                    System.out.println("The book is already lent."); // You can replace this with your desired message or UI update.
                    return; // Return without performing the update
                }
            }

            PreparedStatement ps2 = conn.prepareStatement(sql1);
            PreparedStatement ps = connBooks.prepareStatement(sql2);

            int rowsAffected2 = ps.executeUpdate();
            int rowsAffected1 = ps2.executeUpdate();

            if (rowsAffected2 > 0 && rowsAffected1 > 0) {
                System.out.println("Status Changed."); // You can replace this with your desired success message or UI update.
                lbm_Display_Books();
                Update_Books();
                UpdateTable();
                lbm_Display_Subscription();
            } else {
                System.out.println("Failed to change."); // You can replace this with your desired failure message or UI update.
            }
        } catch (Exception e) {
            e.printStackTrace();
            // Handle the exception appropriately, such as showing an error message.
        }
    }




    public void lbm_lend_a_Book(){
        connLent = SqlConnectLBM.ConnectLibraryManager();
        String value = Txt_B_title.getText();

        try{
            pstLent.setString(1, Txt_sub_ID.getText());
            pstLent.setString(2, Txt_F_name.getText());
            pstLent.setString(3, Txt_L_name.getText());
            pstLent.setString(4, Txt_B_title.getText());




        }catch(Exception e){
            JOptionPane.showMessageDialog(null,"Failed to Lend Book");

        }
        try {
            Statement stmt = connLent.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT `Availability` FROM `librarydatabase1`.`carti_din_biblioteca` WHERE `Book Title` = '"+value+"'");

            if (rs.next()) {
                String availability = rs.getString("Availability");
                if (availability.equalsIgnoreCase("Lent")) {
                    System.out.println("The book is already lent."); // You can replace this with your desired message or UI update.
                    return; // Return without performing the update
                }
            }
            String sql = "INSERT INTO `librarydatabase1`.`carti_imprumutate` (`Subscription ID`, `First Name`, `Last Name`, `Book Title`) VALUES(?,?,?,?)";
            PreparedStatement ps = connLent.prepareStatement(sql);
            int rowsAffected = ps.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Status Changed."); // You can replace this with your desired success message or UI update.
                lbm_Display_Books();
                Update_Books();
            } else {
                System.out.println("Failed to change."); // You can replace this with your desired failure message or UI update.
            }
        } catch (Exception e) {
            e.printStackTrace();
            // Handle the exception appropriately, such as showing an error message.
        }
//        lbm_increase_book_count();
        lbm_change_book_Status();
    }





    @Override
    public void initialize(URL url, ResourceBundle rb){


        lbm_Display_Subscription();
        lbm_Display_Books();
        lbm_Display_LentBooks();
        UpdateTable();
        Update_Books();

    }
}
