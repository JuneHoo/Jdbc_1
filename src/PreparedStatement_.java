import java.sql.*;
import java.util.Properties;
import java.util.Scanner;

public class PreparedStatement_ {
    // 预处理

    public static void main(String[] args) throws SQLException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入管理员名字："); // 1' or
        String admin_name = scanner.nextLine();
        String admin_pwd = "";
        System.out.println("请输入管理员密码："); // or '1' = '1
        admin_pwd = scanner.nextLine();

        Driver driver = new com.mysql.cj.jdbc.Driver();
        String url = "jdbc:mysql://localhost:3306/exercise02?serverTimezone=UTC";

        Properties info = new Properties();
        info.setProperty("user", "admin");
        info.setProperty("password", "Admin!123456");

        Connection conn = driver.connect(url, info);


        // sql语句的问号相当于占位符
        String sql = "select name , pwd from admin where name =? and pwd=?";

        // get prepareStatement
        PreparedStatement preparedStatement = conn.prepareStatement(sql);
        preparedStatement.setString(1, admin_name);
        preparedStatement.setString(2, admin_pwd);


//        ResultSet resultSet = preparedStatement.executeQuery(sql);
        ResultSet resultSet = preparedStatement.executeQuery();
//        statement.execute(sql);
        if (resultSet.next()) { // 查询到一条记录 说明管理员存在
            System.out.println("登录成功！");
        } else {
            System.out.println("登录失败");
        }

        conn.close();
    }
}
