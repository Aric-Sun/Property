package Avengers.Stark.web.servlet;

import Avengers.Stark.dao.CurrentCostDao;
import Avengers.Stark.dto.Current_Cost;
import Avengers.Stark.dto.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @author AricSun
 * @date 2020.06.13 16:52
 */
@WebServlet("/currentCostServlet")
public class CurrentCostServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("GBK");
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");
        Current_Cost current_cost = new CurrentCostDao().QueryCurrentCost(user);
        req.setAttribute("current_cost",current_cost);
        req.getRequestDispatcher("/fee/pre_fee_personal.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }
}
