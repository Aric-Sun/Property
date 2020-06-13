package Avengers.Stark.web.servlet;

import Avengers.Stark.dao.CurrentCostDao;
import Avengers.Stark.dto.Current_Cost;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @author AricSun
 * @date 2020.06.13 18:37
 */
@WebServlet("/allCostRecordServlet")
public class AllCostRecordServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("GBK");
        List<Current_Cost> costList = new CurrentCostDao().queryAllCost();
        req.setAttribute("costList",costList);
        req.getRequestDispatcher("/fee/pre_fee_manager.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }
}
