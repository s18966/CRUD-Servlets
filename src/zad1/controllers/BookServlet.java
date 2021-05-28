package zad1.controllers;

import zad1.models.Book;
import zad1.service.BookService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;


@WebServlet(name = "Book Servlet", urlPatterns = "/books")
public class BookServlet extends HttpServlet {
    private static final long serialVersionUID =1L;
    private BookService bookService;

    public BookServlet(){
        this.bookService = new BookService();
    }
    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        try {
            log("IN processRequest");
            request.setAttribute("allBooks", this.bookService.getAll());
            log(request.toString());
            RequestDispatcher dispatcher = request.getRequestDispatcher("/index.jsp");
            dispatcher.forward(request, response);
        }catch (SQLException e){
            log("SQL EX: " +e.getMessage());
        }
    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse response) throws ServletException, IOException {

        log("GOT REQUEST");
        processRequest(req, response);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if("delete".equals(req.getParameter("action"))){
            int id = Integer.parseInt(req.getParameter("id"));
            try {
                bookService.deleteBook(id);
            }catch (SQLException e){
                log("SQL EX: " + e.getMessage());
            }
            processRequest(req,resp);
        }else if("update".equals(req.getParameter("action"))){
            int id = Integer.parseInt(req.getParameter("idupd"));
            String bookName = (String) req.getParameter("nameupd");
            String authorName = (String) req.getParameter("authorupd");
            double price = Double.parseDouble(req.getParameter("priceupd"));
            Book b = new Book(id, bookName, authorName, price);
            try{
                bookService.updateBook(b);
                processRequest(req,resp);
            }catch (SQLException e){
                log("SQL EX: " + e.getMessage());
            }
        }
        else {
            String bookName = (String) req.getParameter("name");
            String authorName = (String) req.getParameter("author");
            double price = Double.parseDouble(req.getParameter("price"));
            Book b = new Book(bookName, authorName, price);
            try {
                bookService.insertBook(b);
            } catch (SQLException e) {
                log("SQL EX: " +e.getMessage());
            }
            processRequest(req, resp);
        }
    }
}
