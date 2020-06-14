package Avengers.Stark.dao;

import Avengers.Stark.Util.JDBCUtils;
import Avengers.Stark.dto.Cost_total;
import oracle.jdbc.OracleTypes;
import oracle.jdbc.oracore.OracleType;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author AricSun
 * @date 2020.06.14 3:36
 */
public class CostTotalDao {

    private Connection connection;
    private CallableStatement callableStatement;
    private ResultSet resultSet;

    /*
     * function: 查询月度汇总 需要缴费的总额 管理员
     * @Param: [month]
     * @Return: java.util.List<Avengers.Stark.dto.Cost_total>
     */
    public List<Cost_total> querySumFeeNeed2PayByMonth(String month){
        if (month == null){
            return null;
        }
        connection = null;
        callableStatement = null;
        resultSet = null;
        Cost_total cost_total = null;
        List<Cost_total> costTotalList = new ArrayList<>();
        try {
            connection = JDBCUtils.getConnection();
            String procedure = "{call Total(?,?)}";
            callableStatement = connection.prepareCall(procedure);
            callableStatement.setString(1,month);
            callableStatement.registerOutParameter(2, OracleTypes.CURSOR);
            callableStatement.execute();
            resultSet = (ResultSet) callableStatement.getObject(2);
            while (resultSet.next()){
                cost_total = new Cost_total();
                cost_total.setUsername(resultSet.getString("username"));
                cost_total.setMonth(resultSet.getString("month"));
                cost_total.setTotal_cost(resultSet.getDouble("total_cost"));
                costTotalList.add(cost_total);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally{
            JDBCUtils.close(resultSet, callableStatement, connection);
        }
        return costTotalList;
    }

    /*
     * function:查询年度汇总 需要缴费的总额 管理员
     * @Param: 需要查询的年份
     * @Return: java.util.List<Avengers.Stark.dto.Cost_total>
     */
    public List<Cost_total> querySumFeeNeed2PayByYear(String year){
        if (year == null){
            return null;
        }
        connection = null;
        callableStatement = null;
        resultSet = null;
        Cost_total cost_total = null;
        List<Cost_total> costTotalList = new ArrayList<>();
        try {
            connection = JDBCUtils.getConnection();
            String procedure = "{call Total_Year(?,?)}";
            callableStatement = connection.prepareCall(procedure);
            callableStatement.setString(1,year);
            callableStatement.registerOutParameter(2, OracleTypes.CURSOR);
            callableStatement.execute();
            resultSet = (ResultSet) callableStatement.getObject(2);
            while (resultSet.next()){
                cost_total = new Cost_total();
                cost_total.setUsername(resultSet.getString("username"));
                cost_total.setMonth(resultSet.getString("year"));
                cost_total.setTotal_cost(resultSet.getDouble("total_cost"));
                costTotalList.add(cost_total);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally{
            JDBCUtils.close(resultSet, callableStatement, connection);
        }
        return costTotalList;
    }
}
