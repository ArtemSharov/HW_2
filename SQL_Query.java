package ru.gb.java_3.hw_2;
import java.sql.*;
public class SQL_Query {
        private static Connection connection;
        private static Statement statement;

        synchronized static void connect() {
            try {
                Class.forName("org.sqlite.JDBC");
                connection = DriverManager.getConnection("jdbc:sqlite:SQL_DB_Java_3.db");
                statement = connection.createStatement();
            } catch (ClassNotFoundException | SQLException e) {
                throw new RuntimeException(e);
            }
        }

        synchronized static void disconnect() {
            try {
                connection.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }


