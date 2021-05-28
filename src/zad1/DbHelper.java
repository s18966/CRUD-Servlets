package zad1;



import java.sql.Connection;
import java.sql.DriverManager;

public class DbHelper {

    public static String tableName = "books";
    public static String id = "id";
    public static String author = "author_name";
    public static String book = "book_name";
    public static String price = "price";

    public static Connection getConnection(String user, String password){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/library", user,password);
            return connection;
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return null;
    }
}
