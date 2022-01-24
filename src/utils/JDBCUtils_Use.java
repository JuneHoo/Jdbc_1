package utils;

import org.junit.Test;

import java.sql.*;
import java.util.Date;

// 完成dml和select
public class JDBCUtils_Use {
    public static void main(String[] args) {

    }

    @Test
    public void testDML() throws SQLException {
        Connection connection = JDBCUtils.getconnection();
        String sql = "update actor set name = ? where id = ?";

        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, "周星驰");
        preparedStatement.setString(2, "4");

        preparedStatement.executeUpdate();
        // 关闭资源
        JDBCUtils.close(null, preparedStatement, connection);
    }

    @Test
    public void testSelect() throws SQLException {
        Connection connection = JDBCUtils.getconnection();
        String sql = "select * from actor";
        ResultSet set = null;

        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(sql);
//            preparedStatement.setString();

            set = preparedStatement.executeQuery();
            // 遍历结果集
            while (set.next()) {
                int id = set.getInt("id");
                String name = set.getString("name");
                String sex = set.getString("sex");
                Date borndate = set.getDate("borndate");
                String phone = set.getString("phone");
                System.out.println(id + "\t" + name + "\t" + sex + "\t" + borndate + "\t" + phone);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.close(set, preparedStatement, connection);
        }
    }
}
