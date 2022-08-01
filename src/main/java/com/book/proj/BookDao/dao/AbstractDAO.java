package com.book.proj.BookDao.dao;

import java.sql.*;

public class AbstractDAO {
    private String url = "jdbc:mariadb://localhost/sba2";
    private final String USER = "root";
    private final String PASS = "rootpwd";
    protected Connection connection = null;
    protected PreparedStatement preparedStatement = null;
    protected ResultSet resultSet = null;

    protected void connect()
    {
        try{
            Class.forName("org.mariadb.jdbc.Driver");
            connection = DriverManager.getConnection(url, USER, PASS);
            //System.out.println("Data base connected "+connection.getMetaData().getDriverName());
        }catch (SQLException | ClassNotFoundException e)
        {
            System.out.println("Something wrong! Check connection");
        }
    }

    public void dispose() {
        try {

            if (!resultSet.equals(null)) {
                if (!resultSet.isClosed())
                    resultSet.close();
            }

            if (!preparedStatement.equals(null)) {
                if (!preparedStatement.isClosed())
                    preparedStatement.close();
            }

            if (!connection.equals(null)) {
                if (!connection.isClosed())
                    connection.close();
            }
        } catch (SQLException e) {
            e.getMessage();
        }
    }
}
