package dataSource;

import org.junit.Test;
import utils.JDBCUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ConQuestion {

    @Test
    public void testCon() throws SQLException {
        long start = System.currentTimeMillis();
        for (int i = 0; i < 5000; i++) {
            // 使用传统方式链接5000次 会报错 Too many connections
            Connection connection = JDBCUtils.getconnection();
            connection.close();
            }
        long end = System.currentTimeMillis();
        System.out.println("传统5000次耗时：" + (end - start));
    }

}
