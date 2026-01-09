import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.*;


public class Mains {
    public static void main(String[] args) throws ClassNotFoundException {

        String url="jdbc:mysql://127.0.0.1:3306/student";
        String username="root";
        String password="";
        String query= "INSERT INTO image_table(image_data) VALUES (?)";
        //String image_path="C:\\Users\\SUBHAJIT\\Pictures\\Saved Pictures\\download (9).png";


        String folder_path="C:\\Users\\SUBHAJIT\\Pictures\\Saved Pictures\\";
        String query1="SELECT image_data FROM image_table WHERE image_id=(?)";



        try{
            Class.forName("com.mysql.jdbc.Driver");
            System.out.println("Driver Loaded Sucessfully..!!");
        }catch(ClassNotFoundException e){
            System.out.println(e.getMessage());
        }

        try{
            Connection con=DriverManager.getConnection(url, username, password);
            System.out.println("Connection sucessfully..!!");
            try (FileInputStream fileInputStream = new FileInputStream(image_path)) {
                byte[] imageData=new byte[fileInputStream.available()];
                fileInputStream.read(imageData);
                PreparedStatement preparedStatement=con.prepareStatement(query1);
                preparedStatement.setBytes(1, imageData);
                int affectedRows=preparedStatement.executeUpdate();
                if(affectedRows>0){
                    System.out.println("Insertion Suceessful");
                }else{
                    System.out.println("Image Not Inserted");
                }
            }
        }catch (FileNotFoundException e) {
            System.out.println("Driver not found: " + e.getMessage());
        } catch (SQLException e) {
            System.out.println("SQL Error: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("File Error: " + e.getMessage());
        }

    }

}
