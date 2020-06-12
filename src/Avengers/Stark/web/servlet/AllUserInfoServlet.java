package Avengers.Stark.web.servlet;

import Avengers.Stark.dao.UserDao;
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
 * @date 2020.06.13 2:43
 */
@WebServlet("/allUserInfoServlet")
public class AllUserInfoServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("GBK");

        List<User> all_userInfo_list = new UserDao().selectAllUserInfo();

//        System.out.println("allUserInfoServlet");
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/userOperation/userInfo_manager.jsp");
        req.setAttribute("all_userInfo_list", all_userInfo_list);
        requestDispatcher.forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }
}
