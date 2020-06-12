package Avengers.Stark.web.servlet;

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
 * @date 2020.06.11 21:01
 */
@WebServlet("/successServlet")
public class SuccessServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取用户名
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        if (user != null){
            // 设置编码
            response.setContentType("text/html;charset=utf-8");
            response.getWriter().write("登录成功！"+user.getUsername()+"，欢迎您");
            response.setHeader("refresh","2;url=mainPage.jsp");
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
