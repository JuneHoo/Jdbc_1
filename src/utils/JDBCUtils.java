package utils;

import jdk.nashorn.internal.ir.CatchNode;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

public class JDBCUtils {
    private static String user;
    private static String password;
    private static String url;
    private static String driver;

    //    private static String user;
    static {
        Properties properties = new Properties();
        try {
            properties.load(new FileInputStream("src\\mysql.properties"));
            user = properties.getProperty("user");
            password = properties.getProperty("password");
            url = properties.getProperty("url");
            driver = properties.getProperty("driver");


        } catch (IOException e) {
//        e.printStackTrace();
            throw new RuntimeException(e);
        }

    }

    public static Connection getconnection() {
        try {
            return DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
//            e.printStackTrace();  // 会报错！
            throw new RuntimeException(e);
        }
    }

    // 如果需要关闭资源 就传入对象 否则传入null
    public static void close(ResultSet set, Statement statement, Connection connection) throws SQLException {
        // 判断是否为null
        if (set != null) {
            set.close();
        }
        if (statement != null) {
            statement.close();
        }
        if (connection != null) {
            connection.close();
        }

    }


}