package Avengers.Stark.dao;

import Avengers.Stark.Util.JDBCUtils;
import Avengers.Stark.dto.Current_Cost;
import Avengers.Stark.dto.Payment_Record;
import Avengers.Stark.dto.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 操作数据库中的Property_Current_Cost表，主要是查询
 * 复用：加入时间查询Property_Cost_Record
 * @author AricSun
 * @date 2020.06.13 16:35
 */
public class CurrentCostDao {

    private Connection connection;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;

    public Current_Cost QueryCurrentCost(User user){
        if (user == null){
            return null;
        }
        connection = null;
        preparedStatement = null;
        resultSet = null;
        Current_Cost current_cost = null;
        try {
            connection = JDBCUtils.getConnection();
            String sql = "select * from Property_Current_Cost where username=?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, user.getUsername());
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()){
                current_cost = new Current_Cost();
                current_cost.setUsername(user.getUsername());
                current_cost.setWater(resultSet.getString("water"));
                current_cost.setElectricity(resultSet.getString("electricity"));
                current_cost.setGas(resultSet.getString("gas"));
                current_cost.setHeating(resultSet.getString("heating"));
                current_cost.setManagementFee(resultSet.getString("managementFee"));
                current_cost.setHousePayment(resultSet.getString("housePayment"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        } finally {
            JDBCUtils.close(resultSet, preparedStatement, connection);
        }
        return current_cost;
    }

    public List<Current_Cost> queryAllCost(){
        connection = null;
        preparedStatement = null;
        resultSet = null;
        Current_Cost cost = null;
        List<Current_Cost> allCostList = new ArrayList<>();
        try {
            connection = JDBCUtils.getConnection();
            String sql = "select * from Property_Cost_Record";
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                cost = new Current_Cost();
                cost.setUsername(resultSet.getString("username"));
                cost.setTime(resultSet.getString("time"));
                cost.setWater(resultSet.getString("water"));
                cost.setElectricity(resultSet.getString("electricity"));
                cost.setGas(resultSet.getString("gas"));
                cost.setHeating(resultSet.getString("heating"));
                cost.setManagementFee(resultSet.getString("managementFee"));
                cost.setHousePayment(resultSet.getString("housePayment"));
                allCostList.add(cost);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.close(resultSet, preparedStatement, connection);
        }
        return allCostList;
    }
}
