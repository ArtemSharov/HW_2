package ru.gb.java_3.hw_2;
import java.sql.*;
import java.util.Scanner;
/*
1. Сделать методы для работы с БД (CREATE, UPDATE, DELETE, INSERT, SELECT)
 */

public class SQL_Query {
        private static Connection connection;
        private static Statement statement;
        private static PreparedStatement pstmt;
        static Scanner sc = new Scanner(System.in);

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
                stmt.execute("CREATE TABLE IF NOT EXISTS Students" +
                        "(ID INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," +
                        "  Name TEXT NOT NULL," +
                        "  Score INTEGER NOT NULL);");
                           } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        public static void update(String columnName, int param){
            try {
                connection.createStatement();
                pstmt = connection.prepareStatement("UPDATE Students SET Score = ? WHERE Name = ?;");
                pstmt.setInt(1, param);
                pstmt.setString(2, columnName);
                pstmt.executeUpdate();

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    public static void insert(String name, int score){
        try {
            connection.createStatement();
            pstmt = connection.prepareStatement("INSERT INTO Students (Name, Score) VALUES (?, ?);");
            pstmt.setString(1, name);
            pstmt.setInt(2, score);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static void delete(int id){
        try {
            connection.createStatement();
            pstmt = connection.prepareStatement("DELETE FROM Students WHERE ID=?;");
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static void select(){
        try {
            System.out.print("Введите запрос: ");
            String sqlQuery = sc.nextLine();
            connection.createStatement();
            pstmt = connection.prepareStatement(sqlQuery);
                       ResultSet rs = pstmt.executeQuery();
            while(rs.next()){
                String name = rs.getString("Name");
                int score = rs.getInt("Score");
                System.out.println(name + " " + score);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        connect();
        //createTable();
        //insert("Bob2",30);
        //update("Bob", 20);
        //delete(3);
        select();
        disconnect();
    }
    }


