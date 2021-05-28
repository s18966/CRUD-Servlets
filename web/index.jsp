<%@ page import="zad1.models.Book" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: kinatra
  Date: 5/25/21
  Time: 12:16 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>Library</title>
    <meta charset="UTF-8"/>
    <link rel="stylesheet" href="styles/styles.css">
  </head>
  <body>
  <div class="content">
  <h1>Library system</h1>
  <h2>Hello and welcome to our library!</h2>
  <h3>Here you can see all books available</h3>
  <table class="styled-table">
    <%
      List<Book> books = (List<Book>)request.getAttribute("allBooks");
    %>
    <tr>
      <th>Id Of The Book</th>
      <th>Book Author</th>
      <th>Book Title</th>
      <th>Price</th>
    </tr>
    <%for(Book b : books){%>
          <tr>
            <td><%=b.getId()%></td>
            <td><%=b.getAuthor()%></td>
            <td><%=b.getName()%></td>
            <td><%=b.getPrice()%></td>
          </tr>
      <%}%>
  </table>
  <h3>Here you can insert a new book</h3>
  <div class="container">
    <form method="POST" action="/TPO_7_s18966/books">
      <label for="name">Book name:</label>
      <input id="name" type="text" name="name"/><br/>
      <label for="author">Author name:</label>
      <input id="author" type="text" name="author"/><br/>
      <label for="price">Price of book:</label>
      <input id="price" type="number" name="price"/><br/>
      <button type="submit" value="Submit">Submit</button>
    </form>
  </div>
  <h3>Here you can delete a book by book id</h3>
  <div class="container">
    <form method="POST" action="/TPO_7_s18966/books">
      <input type="hidden" name="action" value="delete"/>
      <label for="id">Id of the book:</label>
      <input id="id" type="number" name="id"><br/>
      <button type="submit" value="Delete">Delete</button>
    </form>
  </div>
  <h3>Here you can update the book</h3>
  <div class="container">
    <form method="POST" action="/TPO_7_s18966/books">
      <label for="idupd">Id of the book:</label><br/>
      <input id="idupd" type="number" name="idupd"><br/>
      <input type="hidden" name="action" value="update"/>
      <label for="nameupd">Book name:</label><br/>
      <input id="nameupd" type="text" name="nameupd"/><br/>
      <label for="authorupd">Author name:</label><br/>
      <input id="authorupd" type="text" name="authorupd"/><br/>
      <label for="priceupd">Price of book:</label><br/>
      <input id="priceupd" type="number" name="priceupd"/><br/>
      <button type="submit" value="Update">Update</button>
    </form>
  </div>
  </div>
  </body>
</html>
