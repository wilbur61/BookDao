package com.book.proj.BookDao;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


//CREATE TABLE Books (
//	    id int NOT NULL AUTO_INCREMENT,
//	    title varchar(255),
//	    author varchar(255),
//	    yearPublished int,
//	    PRIMARY KEY (id)
//	);

//add any three records to the Books table
//update one selected record (use the PreparedStatement)
//delete the selected record with the specified id
//display all other records in the database

public class Book1 {
	
	Connection connection = null;
	String driverName = "org.mariadb.jdbc.Driver";
	String connectionUrl = "jdbc:mariadb://localhost/sba2";
	String userName = "root";
	String userPass = "rootpwd";

	public Book1() {
		try {
			Class.forName(driverName);
		} catch (ClassNotFoundException e) {
			System.out.println(e.toString());
		}
	}

	public Connection getConnection() {
		try {
			connection = DriverManager.getConnection(connectionUrl, userName,
					userPass);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return connection;
	}

	public int insertRecord(int id,String title,
			                String author,int yearPublished) throws SQLException{

		Connection con = this.getConnection();
		int rv=0;
		String insertQuery = "Insert into Books(id,title,author,yearPublished) values(?,?,?,?)";
		try {
			// Compiling query String
			PreparedStatement statement = con.prepareStatement(insertQuery);
			// setting parameter to the query
			statement.setInt(1, id);
			statement.setString(2, title);
			statement.setString(3, author);
			statement.setInt(4, yearPublished);
			//Updating Query
			rv = statement.executeUpdate();
			statement.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			con.close();
			System.out.println( "Record Inserted return value="+rv);
		}
		return rv;
		
	}
	
	public int updateRecord(int id,String title,
            String author,int yearPublished) throws SQLException{

			Connection con = this.getConnection();
			int rv=0;
			String updQuery = "UPDATE Books SET title= ?, author = ?, yearPublished = ? WHERE id = ?";
			try {
				// Compiling query String
				PreparedStatement statement = con.prepareStatement(updQuery);
				// setting parameter to the query				
				statement.setString(1, title);
				statement.setString(2, author);
				statement.setInt(3, yearPublished);
				statement.setInt(4, id);
				//Updating Query
				rv = statement.executeUpdate();
				statement.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				con.close();
				System.out.println( "Record Updated return value="+rv);
			}
			return rv;
	}
	
	public void deleteRecord(int id) throws SQLException{
		Connection con = this.getConnection();
		String delQuery = "DELETE FROM Books WHERE id = ?";
		try {
			// Compiling query String
			PreparedStatement statement = con.prepareStatement(delQuery);
			statement.setInt(1, id);
			//Updating Query
			statement.executeUpdate();
			statement.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			con.close();
			System.out.println( "Deleted Record id="+id);
		}
		
	}

	public void displayAll() throws SQLException{
		Connection con = this.getConnection();
		String selQuery = "SELECT id,title,author,yearPublished FROM Books";

		try {	
			
			Statement statement = con.createStatement();
			ResultSet result = statement.executeQuery(selQuery);
	
		 
			while (result.next()){
				int id  = result.getInt("id");
				String title = result.getString("title");
				String auth = result.getString("author");
				int year = result.getInt("yearPublished");
		 
				String output = "ID #%d: %s - %s - %d - ";
				System.out.println(String.format(output, id, title, auth, year));
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				con.close();
			}		
	}
	
	public static void main(String[] args) throws SQLException {
		Book1 t1 = new Book1();
		System.out.println( "Main Calling insertRecord");
		int rv = t1.insertRecord(1,"Carrie","Stephen King",1975);
		System.out.println( "Return Value ="+rv);
		rv = t1.insertRecord(2,"War & Peace","Tolstoy",1881);
		System.out.println( "Return Value ="+rv);
		rv = t1.insertRecord(3,"Joy of Cooking","Julia Childs",1983);
		System.out.println( "Return Value ="+rv);
		
		System.out.println( "Main Calling updateRecord");
		rv = t1.updateRecord(2,"The Shining","Stephen King",1977);
		System.out.println( "Return Value ="+rv);
		
		System.out.println( "Main Calling deleteRecord");
		t1.deleteRecord(1);

		System.out.println( "Main Calling displayAll");
		t1.displayAll();
		
	}
}
