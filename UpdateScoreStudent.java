package ru.gb.java_3.hw_2;

import java.io.FileReader;
import java.io.IOException;
import java.sql.*;


public class UpdateScoreStudent {
    private static Connection connection;
    private static PreparedStatement pstmt;
    private static Statement stmt;

    synchronized static void connect() {
        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:SQL_DB_Java_3.db");
            stmt = connection.createStatement();
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
    public static void update(String columnName, String  param){
        try {
            connection.createStatement();
            pstmt = connection.prepareStatement("UPDATE Students SET Score = ? WHERE Name = ?;");
            pstmt.setString(1, param);
            pstmt.setString(2, columnName);
            pstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void updateFromTxtFile(String fileName) throws SQLException {
        StringBuilder buffer = new StringBuilder();
        try {
            FileReader textfr = new FileReader(fileName);
            int chars;
            while ((chars = textfr.read()) != -1) {
                buffer.append((char) chars);
            }
            textfr.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        connect();
        String[] arrText = buffer.toString().split("\n");
        String[][] matrixText = new String[arrText.length][];
        for (int i = 0; i < matrixText.length; i++) {
            matrixText[i] = arrText[i].split("  ");
        }
        for (int i = 0; i < matrixText.length ; i++) {
            update(matrixText[i][1], matrixText[i][2]);
        }
        disconnect();
    }
    public static void main(String[] args) throws SQLException {
        updateFromTxtFile("C:\\Users\\Sharov\\Downloads\\example.txt");
    }
}
