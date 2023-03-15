package net.codejava;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.Assert;


import java.sql.*;
//подключение к бд(позитивный тест)
public class ConnectionTest {
    private Connection connection;


    //соединение с базой данных
    @Before
    public void setUp() throws SQLException {
        String url = "jdbc:mysql://localhost:3306/testapp1?serverTimezone=UTC";
        String user = "root";
        String password = "123Enjet!";
        this.connection = DriverManager.getConnection(url, user, password);
    }

    //Проверяем, что соединение действительно установлено
    @Test
    public void testDatabaseConnection() throws SQLException {
        Assert.assertNotNull(this.connection);
    }


    //закрытие соединения с бд
    @After
    public void tearDown() throws SQLException {
        this.connection.close();
    }

}
