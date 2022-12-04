package com.example.dictionary;
import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class DbConnection {



        private static  final String DB_URL="jdbc:mysql://localhost:3306/data";
        private static final String user="root";
        private static final String password="root";

        public Statement getStatement(){
            Statement statement=null;
            Connection conn;
            try {
                conn= DriverManager.getConnection(DB_URL, user, password);
                statement = conn.createStatement();
            }catch(Exception e){
                e.printStackTrace();
            }
            return statement;
        }
        public ResultSet getQueryTable(String query){
            Statement statement=getStatement();
            try{
                return statement.executeQuery(query);
            }catch (Exception e){
                e.printStackTrace();
            }
            return null;

        }

//    public static void main(String[] args) {
//    DatabaseConnection dbconn=new DatabaseConnection();
//        String query="SELECT * FROM CUSTOMER";
//        ResultSet rs=dbconn.getQueryTable(query);
//        try {
//            while (rs!=null){
//                System.out.println("cid:"+rs.getInt("cid")+"name"+rs.getString("email"));
//            }
//
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//    }
    }


