import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
public class Transaction {
    public static void main(String[] args) {
        String url = "jdbc:mysql://127.0.0.1:3306/student";
        String username = "root";
        String password = "";
        String withdreawQuery="UPDATE accounts SET balance=balance-? WHERE account_number=?";
        String  depositQuery="UPDATE accounts SET balance=balance+? WHERE account_number=?";
        try {
            Class.forName("com.mysql.jdbc.Driver");
            System.out.println("Driver Loaded Sucessfully..!!");
        } catch (ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
        try {
            Connection con = DriverManager.getConnection(url, username, password);
            System.out.println("Connection Established Sucessfully..!!");
            con.setAutoCommit(false);
            try {
                PreparedStatement withdrawStatement = con.prepareStatement(withdreawQuery);
            PreparedStatement depositStatement = con.prepareStatement(depositQuery);
            
             //WITHDRAW
            withdrawStatement.setDouble(1, 750.00);
            withdrawStatement.setString(2, "account789");
            //DEPOSIT
            depositStatement.setDouble(1, 400.00);
            depositStatement.setString(2, "account456");
            //EXECUTE
            withdrawStatement.executeUpdate();
            depositStatement.executeUpdate();
            con.commit();
            System.out.println("Transaction Completed Sucessfully..!!");
            } catch (Exception e) {
                con.rollback();
                System.out.println("Transaction Failed..!! Rolled Back.");
                //System.out.println(e.getMessage());
            }
        } catch (SQLException e) {
          System.out.println(e.getMessage());
        }
    }
}

