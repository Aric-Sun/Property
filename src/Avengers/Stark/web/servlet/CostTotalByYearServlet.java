package Avengers.Stark.web.servlet;

import Avengers.Stark.dao.CostTotalDao;
import Avengers.Stark.dto.Cost_total;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * 年度费用总额，按业主分
 * @author AricSun
 * @date 2020.06.14 3:54
 */
@WebServlet("/costTotalByYearServlet")
public class CostTotalByYearServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("GBK");
        String year = req.getParameter("year");
        List<Cost_total> costTotalByYearList = new CostTotalDao().querySumFeeNeed2PayByYear(year);

//        System.out.println("allUserInfoServlet");
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/fee/sum_year_manger.jsp");
        req.setAttribute("costTotalByYearList", costTotalByYearList);
        requestDispatcher.forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }
}
