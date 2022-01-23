import org.junit.Test;

import java.sql.*;
import java.util.Properties;

import static java.lang.Class.forName;

public class ConnectionTest {
    @Test
    public void testConnextion1() throws SQLException {
        Driver driver = new com.mysql.cj.jdbc.Driver();
        String url = "jdbc:mysql://localhost:3306/exercise02?serverTimezone=UTC";

        Properties info = new Properties();
        info.setProperty("user", "admin");
        info.setProperty("password", "Admin!123456");

        Connection conn = driver.connect(url, info);

        String sql = "update actor set name = '周星驰'";
        Statement statement = conn.createStatement();
//        statement.execute(sql);

        int rows = statement.executeUpdate(sql);
        System.out.println(rows > 0 ? "成功" : "失败");
        conn.close();
//        System.out.println(conn);
    }

    // 方式 2
    @Test
    public void connect02() throws ClassNotFoundException, IllegalAccessException, InstantiationException, SQLException {
        // 用反射来链接Mysql
        Class<?> aClass = forName("com.mysql.cj.jdbc.Driver");
        Driver driver = (Driver) aClass.newInstance();


        String url = "jdbc:mysql://localhost:3306/exercise02?serverTimezone=UTC";
        Properties properties = new Properties();
        properties.setProperty("user", "admin");
        properties.setProperty("password", "Admin!123456");

        Connection connection = driver.connect(url, properties);
        System.out.println("方式二：" + connection);
    }

    // 方式 3
    @Test
    public void connect03() throws ClassNotFoundException, IllegalAccessException, InstantiationException, SQLException {
        Class<?> aClass = Class.forName("com.mysql.cj.jdbc.Driver");
        Driver driver = (Driver) aClass.newInstance();

        String url = "jdbc:mysql://localhost:3306/exercise02?serverTimezone=UTC";
        String user = "admin";
        String password = "Admin!123456";
        DriverManager.registerDriver(driver);
        Connection connection = DriverManager.getConnection(url, user, password);
        System.out.println(connection);
    }


}
