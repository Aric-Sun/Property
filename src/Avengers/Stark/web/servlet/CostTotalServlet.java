package Avengers.Stark.web.servlet;

import Avengers.Stark.dao.CostTotalDao;
import Avengers.Stark.dao.UserDao;
import Avengers.Stark.dto.Cost_total;
import Avengers.Stark.dto.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @author AricSun
 * @date 2020.06.14 3:54
 */
@WebServlet("/costTotalServlet")
public class CostTotalServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("GBK");
        String month = req.getParameter("month");
        List<Cost_total> costTotalList = new CostTotalDao().querySumFeeNeed2PayByMonth(month);

//        System.out.println("allUserInfoServlet");
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/fee/sum_month_manger.jsp");
        req.setAttribute("costTotalList", costTotalList);
        requestDispatcher.forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }
}
