package Avengers.Stark.web.servlet;

import Avengers.Stark.dao.PaymentRecordDao;
import Avengers.Stark.dto.Payment_Record;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @author AricSun
 * @date 2020.06.13 19:10
 */
@WebServlet("/allPaymentRecordServlet")
public class AllPaymentRecordServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("GBK");
        List<Payment_Record> allPaymentRecord = new PaymentRecordDao().QueryAllPaymentRecord();
        req.setAttribute("allPaymentRecord",allPaymentRecord);
        req.getRequestDispatcher("/fee/post_fee_manager.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }
}
