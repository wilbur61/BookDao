package com.book.proj.BookDao.dao;

import java.util.List;

import com.book.proj.BookDao.model.Book;


public interface BookDAO {

    public final static String QueryAddBook = "Insert into Books values(?,?,?,?)"; //add any three records to the MOVIES table
    //update one selected record (use the PreparedStatement)
    public final static String QueryUpdateBook = "UPDATE Books SET title= ?, author = ?, yearPublished = ? WHERE id = ?";
    public final static String QueryDeleteBook = "Delete from Books where id= ?"; //delete the selected record with the specified id
    public final static String QueryGetAllBook ="Select * FROM Books;"; //display all other records in the database
    public final static String QueryGetBookById = "Select * from Books where id= ?";

    public void addBook(Book book); //adding a record
    public void removeBookById(int id); //delete a record by ID
    public Book getBookById(int id); //searching for a movie by ID
    public void updateBook(int id, String newTitle,String newAuthor,int newYear); 
    public List<Book> displayAllBook(); //test at least two of your DAO methods
}
