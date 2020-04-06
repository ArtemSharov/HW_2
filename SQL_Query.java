package ru.gb.java_3.hw_2;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;
/*
1. Сделать методы для работы с БД (CREATE, UPDATE, DELETE, INSERT, SELECT)
 */

public class SQL_Query {
        private static Connection connection;
        private static Statement statement;
        private static PreparedStatement pstmt;
        public static Scanner sc = new Scanner(System.in);


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

        public static void createTable(){
            try {
                Statement stmt = connection.createStatement();
                boolean rs = stmt.execute("CREATE TABLE IF NOT EXISTS Students" +
                        "(ID INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," +
                        "  Name TEXT NOT NULL," +
                        "  Score INTEGER NOT NULL);");
                           } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        public static void update(String columnName, int param){
            try {
                Statement create = connection.createStatement();
                pstmt = connection.prepareStatement("UPDATE Students SET Score = ? WHERE Name = ?;");
                pstmt.setInt(1, param);
                pstmt.setString(2, columnName);
                ResultSet rs = pstmt.executeQuery();

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    public static void insert(int id, String name, int score){
        try {
            Statement create = connection.createStatement();
            pstmt = connection.prepareStatement("INSERT INTO Students (ID, Name, Score) VALUES (?, ?, ?);");
            pstmt.setInt(1, id);
            pstmt.setString(2, name);
            pstmt.setInt(3, id);
            pstmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }




    public static void main(String[] args) {
        connect();

        createTable();
        insert(1,"Bob",30);

        disconnect();
    }
    }


