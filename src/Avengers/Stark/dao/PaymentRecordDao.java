package Avengers.Stark.dao;

import Avengers.Stark.Util.JDBCUtils;
import Avengers.Stark.dto.Payment_Record;
import Avengers.Stark.dto.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * �������ݿ��е�Property_Payment_Record����Ҫ�ǲ�ѯ
 * @author AricSun
 * @date 2020.06.13 17:45
 */
public class PaymentRecordDao {

    private Connection connection;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;
    private Payment_Record paymentRecord;

    /*
     * function: ҵ����ѯ�Լ��Ľɷ����������ʷ
     * @Param: ��ǰ��¼���û�
     * @Return: ��װ�ɷѼ�¼���б�
     */
    public List<Payment_Record> QueryPaymentRecord(User user){
        if (user == null){
            return null;
        }
        connection = null;
        preparedStatement = null;
        resultSet = null;
        paymentRecord = null;
        List<Payment_Record> paymentRecordList= new ArrayList<>();
        try {
            connection = JDBCUtils.getConnection();
            String sql = "select * from Property_Payment_Record where username=?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,user.getUsername());
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                paymentRecord = new Payment_Record();
                paymentRecord.setUsername(user.getUsername());
                paymentRecord.setTime(resultSet.getString("time"));
                paymentRecord.setWater(resultSet.getInt("water"));
                paymentRecord.setElectricity(resultSet.getInt("electricity"));
                paymentRecord.setGas(resultSet.getInt("gas"));
                paymentRecord.setHeating(resultSet.getInt("heating"));
                paymentRecord.setManagementFee(resultSet.getInt("managementFee"));
                paymentRecord.setHousePayment(resultSet.getInt("housePayment"));
                paymentRecordList.add(paymentRecord);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        } finally {
            JDBCUtils.close(resultSet, preparedStatement, connection);
        }
        return paymentRecordList;
    }

    /*
     * function: ��ѯδȫ�ɷѼ�¼��ͼ,����Ա
     * @Param:
     * @Return: ��װ�ɷѼ�¼���б�
     */
    public List<Payment_Record> QueryUnpaidRecord(){
        connection = null;
        preparedStatement = null;
        resultSet = null;
        paymentRecord = null;
        List<Payment_Record> paymentRecordList= new ArrayList<>();
        try {
            connection = JDBCUtils.getConnection();
            String sql = "select * from Unpaid_View";
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                paymentRecord = new Payment_Record();
                paymentRecord.setUsername(resultSet.getString("username"));
                paymentRecord.setTime(resultSet.getString("time"));
                paymentRecord.setWater(resultSet.getInt("water"));
                paymentRecord.setElectricity(resultSet.getInt("electricity"));
                paymentRecord.setGas(resultSet.getInt("gas"));
                paymentRecord.setHeating(resultSet.getInt("heating"));
                paymentRecord.setManagementFee(resultSet.getInt("managementFee"));
                paymentRecord.setHousePayment(resultSet.getInt("housePayment"));
                paymentRecordList.add(paymentRecord);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        } finally {
            JDBCUtils.close(resultSet, preparedStatement, connection);
        }
        return paymentRecordList;
    }

    /*
     * function: ��ѯ���нɷ����������Ա
     * @Param: []
     * @Return: java.util.List<Avengers.Stark.dto.Payment_Record>
     */
    public List<Payment_Record> QueryAllPaymentRecord(){
        connection = null;
        preparedStatement = null;
        resultSet = null;
        paymentRecord = null;
        List<Payment_Record> paymentRecordList= new ArrayList<>();
        try {
            connection = JDBCUtils.getConnection();
            String sql = "select * from Property_Payment_Record";
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                paymentRecord = new Payment_Record();
                paymentRecord.setUsername(resultSet.getString("username"));
                paymentRecord.setTime(resultSet.getString("time"));
                paymentRecord.setWater(resultSet.getInt("water"));
                paymentRecord.setElectricity(resultSet.getInt("electricity"));
                paymentRecord.setGas(resultSet.getInt("gas"));
                paymentRecord.setHeating(resultSet.getInt("heating"));
                paymentRecord.setManagementFee(resultSet.getInt("managementFee"));
                paymentRecord.setHousePayment(resultSet.getInt("housePayment"));
                paymentRecordList.add(paymentRecord);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        } finally {
            JDBCUtils.close(resultSet, preparedStatement, connection);
        }
        return paymentRecordList;
    }
}
