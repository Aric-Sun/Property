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
 * @date 2020.06.13 19:10
 */
@WebServlet("/unpaidRecordServlet")
public class UnpaidRecordServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("GBK");
        List<Payment_Record> unpaidRecordList = new PaymentRecordDao().QueryUnpaidRecord();
        req.setAttribute("unpaidRecordList",unpaidRecordList);
        req.getRequestDispatcher("/fee/unpaid_fee_manager.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }
}
