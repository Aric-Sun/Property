package Avengers.Stark.Util;

import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.Properties;

/**
 * JDBC������
 * @author AricSun
 * @date 2019/8/20 23:50
 */
public class JDBCUtils {
    private static String url;
    private static String user;
    private static String password;
    private static String driver;

    /*
    * ��ȡjdbc.properties��Դ�ļ����ļ��Ķ�ȡ��ֻ��Ҫ��ȡһ�Σ��Ϳ����õ���Щֵ��ʹ�þ�̬�������Ա�ִֻ֤��һ��
    * */
    static {
        //��ȡ��Դ�ļ�

        try {
            //1.����Properties������
            Properties pro = new Properties();
            //2.�����ļ�
//            pro.load(new FileReader("src/jdbc.properties"));
            //���д�������FileNotFoundException��Ҳ�����Ҳ���src�µ�����ļ���
            // �������ҵĻ�������û������ģ������°汾������Զ��޸����������
            //��������Ľ����������
            //��ȡsrc·���µ��ļ��ķ�ʽ---��ClassLoader �������
            ClassLoader classLoader = JDBCUtils.class.getClassLoader();
            URL resource = classLoader.getResource("jdbc.properties");//ֱ����srcΪ��Եĸ�·������ȡ���Ǿ���·��
            String path = resource.getPath();
//            System.out.println(path);///D:/Downloads/day04_jdbc/out/production/day04_jdbc/jdbc.properties
            //outĿ¼�µ���Ŀ�����ļ����µ����ݾ��Ƕ�Ӧ��src
            pro.load(new FileInputStream(path));

            //��ȡ���ݣ���ֵ
            url = pro.getProperty("url");
            user = pro.getProperty("user");
            password = pro.getProperty("password");
            driver = pro.getProperty("driver");
            //ע������
            Class.forName(driver);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /*
    * ��ȡ����
    * */
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, user, password);
    }

    /*
    * �ͷ���Դ
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
    /*����*/
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
