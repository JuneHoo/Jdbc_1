package batch_;

import org.junit.Test;
import utils.JDBCUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Batch_ {

    @Test
    public void noBatch() throws SQLException {

        Connection connection = JDBCUtils.getconnection();
        String sql = "insert into admin2 values(null, ?, ?)";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        long start = System.currentTimeMillis();
        for (int i = 0; i < 5000; i++) {
            preparedStatement.setString(1, "jack" + i);
            preparedStatement.setString(2, "666");
            preparedStatement.executeUpdate();
        }
        long end = System.currentTimeMillis();
        System.out.println(end - start);
        JDBCUtils.close(null, preparedStatement, connection);
    }

    @Test
    public void batch_() throws SQLException {
        // 使用批量处理
        Connection connection = JDBCUtils.getconnection();
        String sql = "insert into admin2 values(null, ?, ?)";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        long start = System.currentTimeMillis();
        for (int i = 0; i < 5000; i++) {
            preparedStatement.setString(1, "jack" + i);
            preparedStatement.setString(2, "666");
            // batch一般和preparedStatement结合使用
            preparedStatement.addBatch();
            if ((i + 1) % 1000 == 0) {
                preparedStatement.executeBatch();

                preparedStatement.clearBatch();
            }

//            preparedStatement.executeUpdate();
        }
        long end = System.currentTimeMillis();
        System.out.println(end - start);
        JDBCUtils.close(null, preparedStatement, connection);
    }
}
