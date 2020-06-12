package Avengers.Stark.Util;

import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.Properties;

/**
 * JDBC工具类
 * @author AricSun
 * @date 2019/8/20 23:50
 */
public class JDBCUtils {
    private static String url;
    private static String user;
    private static String password;
    private static String driver;

    /*
    * 读取jdbc.properties资源文件，文件的读取，只需要读取一次，就可以拿到这些值，使用静态代码块可以保证只执行一次
    * */
    static {
        //读取资源文件

        try {
            //1.创建Properties集合类
            Properties pro = new Properties();
            //2.加载文件
//            pro.load(new FileReader("src/jdbc.properties"));
            //上行代码会产生FileNotFoundException，也就是找不到src下的这个文件，
            // 但是在我的机器上是没有问题的，估计新版本的软件自动修复了这个问题
            //上述问题的解决方法如下
            //获取src路径下的文件的方式---》ClassLoader 类加载器
            ClassLoader classLoader = JDBCUtils.class.getClassLoader();
            URL resource = classLoader.getResource("jdbc.properties");//直接以src为相对的根路径。获取的是绝对路径
            String path = resource.getPath();
//            System.out.println(path);///D:/Downloads/day04_jdbc/out/production/day04_jdbc/jdbc.properties
            //out目录下的项目名称文件夹下的内容就是对应着src
            pro.load(new FileInputStream(path));

            //获取数据，赋值
            url = pro.getProperty("url");
            user = pro.getProperty("user");
            password = pro.getProperty("password");
            driver = pro.getProperty("driver");
            //注册驱动
            Class.forName(driver);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /*
    * 获取连接
    * */
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, user, password);
    }

    /*
    * 释放资源
    * */
    public static void close(Statement stmt, Connection conn){
        if (stmt != null){
            try {
                stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (conn != null){
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    /*重载*/
    public static void close(ResultSet rs, Statement stmt, Connection conn){
        if (rs != null){
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (stmt != null){
            try {
                stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (conn != null){
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
