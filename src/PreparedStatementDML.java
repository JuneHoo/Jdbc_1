import java.sql.*;
import java.util.Properties;
import java.util.Scanner;

public class PreparedStatementDML {
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
//        String sql = "select name , pwd from admin where name =? and pwd=?";
        String sql = "insert into admin values(?, ?)";
        String sql2 = "update admin set pwd = ? where name = ?";
        String sql3 = "delete from admin where name = ?";

        // get prepareStatement
        PreparedStatement preparedStatement = conn.prepareStatement(sql2);
        preparedStatement.setString(2, admin_name);
        preparedStatement.setString(1, admin_pwd);


        int rows = preparedStatement.executeUpdate();
        System.out.println(rows > 0 ? "成功" : "失败");

//        resultSet.close();
        conn.close();
    }
}
