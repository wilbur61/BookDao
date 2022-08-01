package com.book.proj.BookDao;


import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import com.book.proj.BookDao.dao.BookDAO;
import com.book.proj.BookDao.dao.BookDAOImpl;
import com.book.proj.BookDao.model.Book;


/**
 *  test at least two of your DAO methods
 */
public class AppTest 
{
    /**
     * Rigorous Test :-)
     */

    @Test
    public void addBook() {
        //given
        BookDAO bkDAO = new BookDAOImpl();
        Book expectedBook = new Book();
        Book actual;

        //when
        expectedBook.setId(104);
        expectedBook.setTitle("Title4");
        expectedBook.setAuthor("Author4");
        expectedBook.setYearPublished(2024);
        bkDAO.addBook(expectedBook);

        //then
        actual = bkDAO.getBookById(104);
        assertNotNull(actual.getTitle());

        assertEquals(expectedBook.getId(),104);
        assertEquals(expectedBook.getTitle(),"Title4");
        assertEquals(expectedBook.getAuthor(),"Author4");
        assertEquals(expectedBook.getYearPublished(),2024);
    }
    
    @Test
    public void getBookById() {
        //given
        BookDAO bkDAO = new BookDAOImpl();
        Book expectedBook = new Book();
        Book actualBook;

        //when
        expectedBook.setId(104);
        expectedBook.setTitle("Title4");
        expectedBook.setAuthor("Author4");
        expectedBook.setYearPublished(2024);

        actualBook = bkDAO.getBookById(expectedBook.getId());

        //then
        Assertions.assertEquals(expectedBook,actualBook);

    }
 
}

