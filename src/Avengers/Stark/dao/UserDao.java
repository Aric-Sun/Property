package Avengers.Stark.dao;

import Avengers.Stark.Util.JDBCUtils;
import Avengers.Stark.dto.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * 操作数据库中Property_User表的类，包括增删改查
 * @author AricSun
 * @date 2020.06.11 16:43
 */
public class UserDao {

    private Connection connection;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;
    private User user;

    /*
     * function: 登录方法
     * @Param: [loginUser 只有用户名和密码]
     * @Return: Avengers.Stark.dto.User包含全部用户数据
     */
    public User login(User loginUser){
        if (loginUser.getUsername()==null ||
            loginUser.getPassword()==null){
            return null;
        }
        connection = null;
        preparedStatement = null;
        resultSet = null;
        user = null;
        if (loginUser.getUser_type().equals("user")){
            try {
                connection = JDBCUtils.getConnection();
                String sql = "select * from Property_User where username=? and password=?";
                preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setString(1,loginUser.getUsername());
                preparedStatement.setString(2,loginUser.getPassword());
                resultSet = preparedStatement.executeQuery();
//            System.out.println(resultSet.next());
                if (resultSet.next()){
                    user = new User();
                    user.setUsername(loginUser.getUsername());
                    user.setPassword(loginUser.getPassword());
                    user.setIDNumber(resultSet.getString("IDNumber"));
                    user.setBuildNo(resultSet.getString("buildNo"));
                    user.setRoomNo(resultSet.getString("roomNo"));
                    user.setName(resultSet.getString("name"));
                    user.setJobUnit(resultSet.getString("jobUnit"));
                    user.setPhone(resultSet.getString("phone"));
                    user.setFloorage(resultSet.getString("floorage"));
                    user.setUser_type("user");
                }
            } catch (SQLException e) {
                e.printStackTrace();
                return null;
            } finally {
                JDBCUtils.close(resultSet, preparedStatement, connection);
            }
        } else if (loginUser.getUser_type().equals("manager")){
            try {
                connection = JDBCUtils.getConnection();
                String sql = "select * from Property_Manager where username=? and password=?";
                preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setString(1,loginUser.getUsername());
                preparedStatement.setString(2,loginUser.getPassword());
                resultSet = preparedStatement.executeQuery();
//            System.out.println(resultSet.next());
                if (resultSet.next()){
                    user = new User();
                    user.setUsername(loginUser.getUsername());
                    user.setPassword(loginUser.getPassword());
                    user.setName(resultSet.getString("name"));
                    user.setPhone(resultSet.getString("phone"));
                    user.setUser_type("manager");
                }
            } catch (SQLException e) {
                e.printStackTrace();
                return null;
            } finally {
                JDBCUtils.close(resultSet, preparedStatement, connection);
            }
        } else{
            return null;
        }

        return user;
    }

    /*
     * function: 查询所有的业主信息
     * @Param: []
     * @Return: java.util.List<Avengers.Stark.dto.User> 以User为元素的List集合
     */
    public List<User> selectAllUserInfo(){
        connection = null;
        preparedStatement = null;
        resultSet = null;
        user = null;
        List<User> all_userInfo_list = new ArrayList<User>();
        try {
            connection = JDBCUtils.getConnection();
            String sql = "select * from Property_User";
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                user = new User();
                user.setUsername(resultSet.getString("username"));
                user.setIDNumber(resultSet.getString("IDNumber"));
                user.setBuildNo(resultSet.getString("buildNo"));
                user.setRoomNo(resultSet.getString("roomNo"));
                user.setName(resultSet.getString("name"));
                user.setJobUnit(resultSet.getString("jobUnit"));
                user.setPhone(resultSet.getString("phone"));
                user.setFloorage(resultSet.getString("floorage"));
                all_userInfo_list.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return all_userInfo_list;
    }
}
