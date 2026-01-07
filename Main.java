import java.sql.*;
import java.sql.DriverManager;


public class Main {
    public static void main(String[] args) throws ClassNotFoundException {

        String url="jdbc:mysql://127.0.0.1:3306/student";
        String username="root";
        String password="";
        String query= "Select * from employees";



        try{
            Class.forName("com.mysql.jdbc.Driver");
            System.out.println("Driver Loaded Sucessfully..!!");
        }catch(ClassNotFoundException e){
            System.out.println(e.getMessage());
        }

        try{
            Connection con=DriverManager.getConnection(url, username, password);
            System.out.println("Connection sucessfully..!!");
            Statement stmt=con.createStatement();
            ResultSet rs=stmt.executeQuery(query);
            while(rs.next()){
                int id=rs.getInt("id");
                String name=rs.getString("name");
                double salary=rs.getDouble("salary");
                System.out.println();
                System.out.println("+++++++++++++++++++++++");
                System.out.println("Id is :"+id);
                System.out.println("Name is :"+name);
                System.out.println("Salary is :"+salary);
            }
            rs.close();
            stmt.close();
            con.close();
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }

    }
}