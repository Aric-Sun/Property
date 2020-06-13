package Avengers.Stark.test;

import Avengers.Stark.dao.PaymentRecordDao;
import Avengers.Stark.dto.Payment_Record;
import Avengers.Stark.dto.User;

import java.util.List;

/**
 * @author AricSun
 * @date 2020.06.13 18:00
 */
public class PaymentRecordTest {
    public static void main(String[] args) {
        User user = new User();
        user.setUsername("001");
        List<Payment_Record> paymentRecordList = new PaymentRecordDao().QueryPaymentRecord(user);
        System.out.println(paymentRecordList);
        paymentRecordList = new PaymentRecordDao().QueryUnpaidRecord();
        System.out.println(paymentRecordList);
    }
}
