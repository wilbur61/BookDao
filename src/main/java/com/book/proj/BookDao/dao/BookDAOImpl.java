package com.book.proj.BookDao.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.book.proj.BookDao.model.Book;

public class BookDAOImpl extends AbstractDAO implements BookDAO{

    @Override
    public void addBook(Book book) {
        try{
            this.connect(); //connect to database
            preparedStatement = connection.prepareStatement(BookDAO.QueryAddBook);
            preparedStatement.setInt(1, book.getId()); //matching : public final static String QueryAddEmployee = "Insert into Employee values(?,?,?,?)";
            preparedStatement.setString(2, book.getTitle());
            preparedStatement.setString(3, book.getAuthor());
            preparedStatement.setInt(4, book.getYearPublished());

            int result = preparedStatement.executeUpdate();
            preparedStatement.close();
            connection.close();
            System.out.println("Number of rows inserted = "+result);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void removeBookById(int id) {
        try {
            this.connect();
            preparedStatement = connection.prepareStatement(BookDAO.QueryDeleteBook );
            preparedStatement.setInt(1,id);
            int result = preparedStatement.executeUpdate();

            preparedStatement.close();
            connection.close();

            System.out.println("Number of rows deleted = "+result);
        } catch (SQLException e) {
            e.printStackTrace();  //throw new RuntimeException(e);
        }

    }

    @Override
    public Book getBookById(int id) {
        Book book = new Book();

        try{
            this.connect();
            preparedStatement = connection.prepareStatement(BookDAO.QueryGetBookById);
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();

            if(resultSet.next()) {
                book.setId(resultSet.getInt(1));
                book.setTitle(resultSet.getString(2));
                book.setAuthor(resultSet.getString(3));
                book.setYearPublished(resultSet.getInt(4));
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        this.dispose();
        return book;

    }

    @Override
    public void updateBook(int id, String newTitle,String newAuthor,int newYear) {
        Book book = new Book();
        try{
            this.connect(); //connect to database

            preparedStatement = connection.prepareStatement(BookDAO.QueryUpdateBook);
            preparedStatement.setString(1, newTitle);
            preparedStatement.setString(2, newAuthor);
            preparedStatement.setInt(3, newYear);
            preparedStatement.setInt(4, id);

            int result = preparedStatement.executeUpdate();
            preparedStatement.close();
            connection.close();
            System.out.println("Number of rows updated = "+result);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public List<Book> displayAllBook() {
        List<Book> books;
        books = new ArrayList<>();
        try {
            this.connect();
            preparedStatement=connection.prepareStatement(BookDAO.QueryGetAllBook);
            resultSet =preparedStatement.executeQuery();

            //Loop util last row of query
            while(resultSet.next())
            {
                Book bk1 = new Book();
                bk1.setId(resultSet.getInt(1));
                bk1.setTitle(resultSet.getString(2));
                bk1.setAuthor(resultSet.getString(3));
                bk1.setYearPublished(resultSet.getInt(4));
                books.add(bk1);

            }

        }catch(SQLException e)
        {
            e.printStackTrace();
        }
        finally{
            this.dispose();
            return books;
        }
    }
}
