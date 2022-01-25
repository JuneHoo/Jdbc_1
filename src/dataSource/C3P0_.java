package dataSource;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.junit.Test;

import javax.swing.plaf.basic.ComboPopup;
import java.beans.PropertyVetoException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

public class C3P0_ {
    @Test
    // method 1:
    public void testC3P0_01() throws IOException, PropertyVetoException, SQLException {
        // 1.创建一个数据源
        ComboPooledDataSource comboPooledDataSource = new ComboPooledDataSource();
        // 2. 通过配置文件获取相关信息
        Properties properties = new Properties();
        properties.load(new FileInputStream("src\\mysql.properties"));

        String user = properties.getProperty("user");
        String password = properties.getProperty("password");
        String url = properties.getProperty("url");
        String driver = properties.getProperty("driver");

        // 给数据源 comboPoolDataSource 设置相关参数
        // 注意：链接管理是由comboPooledDataSource管理
        comboPooledDataSource.setDriverClass(driver);
        comboPooledDataSource.setJdbcUrl(url);
        comboPooledDataSource.setUser(user);
        comboPooledDataSource.setPassword(password);

        comboPooledDataSource.setInitialPoolSize(10);
        comboPooledDataSource.setMaxPoolSize(20);
        long start = System.currentTimeMillis();
        for (int i = 0; i < 5000; i++) {
            Connection connection = comboPooledDataSource.getConnection();
//            System.out.println("ok");
            connection.close();
        }
        long end = System.currentTimeMillis();
        System.out.println("Time: " + (end - start));
        // 从DataSource接口实现的
        Connection connection = comboPooledDataSource.getConnection();
        System.out.println("ok");

    }

//    @Test
//    // method 2：Using config file
//    public void testC3P0_02() throws SQLException {
//        ComboPooledDataSource comboPooledDataSource = new ComboPooledDataSource("jboss:service=C3P0PooledDataSource");
//        Connection connection = comboPooledDataSource.getConnection();
//        System.out.println("ok");
//        connection.close();
//    }
}
