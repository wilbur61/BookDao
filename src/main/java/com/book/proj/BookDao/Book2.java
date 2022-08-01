package com.book.proj.BookDao;

import java.sql.SQLException;
import java.util.List;
import com.book.proj.BookDao.dao.BookDAOImpl;
import com.book.proj.BookDao.model.Book;


/**
 * Book 2
 * 
 * adding a record
 * delete a record by ID
 * searching for a movie by ID
 * update a movie 'title' found by ID
 * 
 * please see: AppTest.java in src/test/java
 * test at least two of your DAO methods
 *
 */
public class Book2
{

    public static void main(String[] args)
    			throws SQLException
    {

//    	add any three records to the Books table
    	
		Book bk = new Book();
		bk.setTitle("The Godfather");
		bk.setAuthor("Puzo");
		bk.setYearPublished(1974);
		bk.setId(0);
		BookDAOImpl bkDao = new BookDAOImpl();
		bkDao.addBook(bk);
			
		bk.setTitle("EAST OF EDEN");
		bk.setAuthor("JOHN STEINBECK");
		bk.setYearPublished(1874);
		bk.setId(0);
		bkDao.addBook(bk);
		
		bk.setTitle("I KNOW WHY THE CAGED BIRD SINGS");
		bk.setAuthor("MAYA ANGELOU");
		bk.setYearPublished(1969);
		bk.setId(0);
		bkDao.addBook(bk);
		
//    	update one selected record
//		System.out.println("UPDATE record");
//		bkDao.updateBook(1,"GONE WITH THE WIND","MARGARET MITCHELL",1937);
		
		
//    	delete the selected record with the specified id
//		System.out.println("DELETE record");
//		bkDao.removeBookById(1);
		
		//  searching for a movie by ID
		System.out.println("SEARCH for record");
		Book myBook = bkDao.getBookById(2);
    	System.out.println("Search Book ="+  myBook.getTitle());
    	System.out.println("            ="+  myBook.getAuthor());
		
//    	display all other records in the database
    	System.out.println("Display all record(s)");
        List<Book> list = bkDao.displayAllBook();
        int i = 0;
        while (i < list.size()) {
        	System.out.println( list.get(i).getId());
        	System.out.println( list.get(i).getTitle());
        	System.out.println( list.get(i).getAuthor());
        	System.out.println( list.get(i).getYearPublished());
        	System.out.println( "========End of Record =====");
            i++;
        }
    	
    }
}