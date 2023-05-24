package com.example.tobyspring3.db;

import org.springframework.context.support.StaticMessageSource;

import java.sql.*;
import java.util.*;

import static java.lang.System.getenv;


public class ConnectionChecker {
    public void check() throws ClassNotFoundException, SQLException {
        Map<String, String> env = getenv();
        String dbHost = env.get("DB_HOST");
        String dbUser = env.get("DB_USER");
        String dbPassword = env.get("DB_PASSWORD");

        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection conn = DriverManager.getConnection(
                "jdbc:mysql://ec2-3-35-241-61.ap-northeast-2.compute.amazonaws.com:3306/spring-db","root","password"
        );

        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("show databases");

        while (rs.next()){
            String line = rs.getString(1);
            System.out.println(line);
        }

    }

    public void add() throws ClassNotFoundException,SQLException{
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection conn = DriverManager.getConnection(
                "jdbc:mysql://ec2-3-35-241-61.ap-northeast-2.compute.amazonaws.com:3306/spring-db","root","password"
        );

        PreparedStatement pstmt = conn.prepareStatement(
                "insert into users(id,name,password) values (?,?,?)"
        );
        pstmt.setString(1,"1");
        pstmt.setString(2,"TaeWoo");
        pstmt.setString(3,"1234");
        pstmt.executeUpdate();
    }

    public void select() throws SQLException, ClassNotFoundException {


        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost/test-db1",
                "root", "12345678");

        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery("select * from users");
        rs = st.getResultSet();
        while (rs.next()) {
            String str = rs.getString(1);
            String str2 = rs.getString(2);
            String str3 = rs.getString(3);
            System.out.println(str + str2 + str3);
        }
    }

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        ConnectionChecker chck = new ConnectionChecker();
        chck.check();
        chck.add();
        chck.select();
    }
}
