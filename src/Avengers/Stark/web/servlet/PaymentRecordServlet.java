package Avengers.Stark.web.servlet;

import Avengers.Stark.dao.PaymentRecordDao;
import Avengers.Stark.dto.Payment_Record;
import Avengers.Stark.dto.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

/**
 * @author AricSun
 * @date 2020.06.13 18:02
 */
@WebServlet("/paymentRecordServlet")
public class PaymentRecordServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("GBK");
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");
        List<Payment_Record> paymentRecordList = new PaymentRecordDao().QueryPaymentRecord(user);
        req.setAttribute("paymentRecordList",paymentRecordList);
        req.getRequestDispatcher("/fee/post_fee_personal.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }
}
