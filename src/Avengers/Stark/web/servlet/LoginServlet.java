package Avengers.Stark.web.servlet;

import Avengers.Stark.dao.UserDao;
import Avengers.Stark.dto.User;
import com.sun.javaws.progress.PreloaderDelegate;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @author AricSun
 * @date 2020.06.11 20:32
 */
@WebServlet("/loginServlet")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 编码
        req.setCharacterEncoding("GBK");
        // 获取请求参数
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String user_type = req.getParameter("user_type");
        // 封装User对象
        User loginUser = new User();
        loginUser.setUsername(username);
        loginUser.setPassword(password);
        loginUser.setUser_type(user_type);

        // 调用UserDao的login
        User user = new UserDao().login(loginUser);

        // 判断User
        if (user == null){ // 登录失败
            req.getRequestDispatcher("failServlet").forward(req, resp);
        } else{ //登录成功
            // 存储数据
            HttpSession session = req.getSession();
            session.setAttribute("user", user);

            // 跳转
            resp.sendRedirect(req.getContextPath()+"/successServlet");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }
}
