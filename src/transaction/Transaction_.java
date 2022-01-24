package transaction;

import org.junit.Test;
import utils.JDBCUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Transaction_ {
    @Test
    public void noTransaction() {
        Connection connection = null;
        String sql = "update account set balance = balance - 100 where id = 1";
        String sql2 = "update account set balance = balance + 100 where id = 2";

        PreparedStatement preparedStatement1 = null;
//        PreparedStatement preparedStatement2 = null;
        try {
            // 在默认情况下 connection对象是自动提交的 （执行语句就提交 用事务解决）
            connection = JDBCUtils.getconnection();
            preparedStatement1 = connection.prepareStatement(sql);
            preparedStatement1.executeUpdate();
            int i = 1/0; // 抛出异常 数据库执行中断
            preparedStatement1 = connection.prepareStatement(sql2);
            preparedStatement1.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Test
    public void useTransaction() throws SQLException {
        Connection connection = null;
        String sql = "update account set balance = balance - 100 where id = 1";
        String sql2 = "update account set balance = balance + 100 where id = 2";

        PreparedStatement preparedStatement1 = null;
//        PreparedStatement preparedStatement2 = null;
        try {
            // 在默认情况下 connection对象是自动提交的 （执行语句就提交 用事务解决）
            connection = JDBCUtils.getconnection();

            // 将 connection 设置为不自动提交！ 相当于开启了事务
            connection.setAutoCommit(false);

            preparedStatement1 = connection.prepareStatement(sql);
            preparedStatement1.executeUpdate();
            int i = 1/0; // 抛出异常 数据库执行中断
            preparedStatement1 = connection.prepareStatement(sql2);
            preparedStatement1.executeUpdate();

            // 这里提交事务
            connection.commit();

        } catch (SQLException e) {
            // 这里可以进行回滚，即撤销执行的sql
            System.out.println("执行的时候发生了异常，撤销执行的sql");
            try {
                connection.rollback();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }

            e.printStackTrace();
        } finally {
            JDBCUtils.close(null, preparedStatement1, connection);
        }
    }
}
