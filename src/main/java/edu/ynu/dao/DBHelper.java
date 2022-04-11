package edu.ynu.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;


public final class DBHelper {

    static String _connStr = "jdbc:sqlserver://localhost:1433;DatabaseName=elm;TrustServerCertificate=true;user=sa;password=20020810ll;";

    public static String getConnectionString() {
        return _connStr;
    }

    public static void setConnectionString(String connStr) {
        DBHelper._connStr = connStr;
    }

    public static Connection getConnection() throws SQLException {
        var conStr = getConnectionString();
        return getConnection(conStr);
    }

    public static Connection getConnection(String connStr) throws SQLException {
        //通过连接字符串打开一个特定数据库的连接
        String connectionUrl = connStr;
        Connection con = DriverManager.getConnection(connectionUrl);

        return con;
    }

    public static ResultSet executeQuery(String sql) throws SQLException {
        //通过连接字符串打开一个特定数据库的连接
        var con = DBHelper.getConnection();

        //通过连接创建一个语句sql执行器
        var stm = con.createStatement();
        var result = stm.executeQuery(sql);

        return result;
    }


    public static int executeUpdate(String sql) throws SQLException {
        //通过连接字符串打开一个特定数据库的连接
        var con = DBHelper.getConnection();

        //通过连接创建一个语句sql执行器
        var stm = con.createStatement();
        var result = stm.executeUpdate(sql);

        return result;
    }

    public static void close() throws SQLException {
        var con = DBHelper.getConnection();
        con.close();
    }
}
