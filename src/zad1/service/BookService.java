package zad1.service;

import zad1.DbHelper;
import zad1.models.Book;

import java.awt.image.DataBuffer;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class BookService {
    Connection connection;

    public BookService(){
        this.connection = DbHelper.getConnection("login", "qwerty12345");
    }
    public List<Book> getAll() throws SQLException {
        Statement statement = connection.createStatement();
        ResultSet rs = statement.executeQuery("SELECT * from " + DbHelper.tableName);
        ArrayList<Book> bookResults = new ArrayList<>();
        while(rs.next()){
            bookResults.add(new Book(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getDouble(4)));
        }
        rs.close();
        statement.close();
        return bookResults;

    }
    public Optional<Book> getBookById(int id) throws SQLException{
        Statement statement = connection.createStatement();
        ResultSet rs = statement.executeQuery("SELECT * from" +DbHelper.tableName + " where " + DbHelper.id + "=" + id);
        rs.next();
        statement.close();
        rs.close();
        return Optional.of(new Book(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getDouble(4)));
    }
    public void insertBook(Book b) throws SQLException{
        Statement statement = connection.createStatement();
        statement.executeUpdate("insert into " + DbHelper.tableName +"("+DbHelper.author+", " + DbHelper.book+ ", " + DbHelper.price + ") values (" + "'" +  b.getAuthor() +"'" + ", " +"'" +  b.getName() + "'"  + ", " +"'" +  b.getPrice() +"'" + ")");
        statement.close();
    }
    public void updateBook(Book b) throws SQLException{
        PreparedStatement statement = connection.prepareStatement(
                "update " + DbHelper.tableName + " set " + DbHelper.author + "=?, " + DbHelper.book + "=?, " + DbHelper.price + "=? where " + DbHelper.id + "=" + b.getId()
        );
        statement.setString(1, b.getAuthor());
        statement.setString(2, b.getName());
        statement.setString(3, Double.toString(b.getPrice()));
        statement.executeUpdate();
        statement.close();
    }
    public void deleteBook(int id) throws SQLException{
        PreparedStatement statement = connection.prepareStatement("delete from " + DbHelper.tableName + " where " + DbHelper.id + "=" + id);
        statement.executeUpdate();
        statement.close();
    }
}
