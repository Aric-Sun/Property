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
        // ����
        req.setCharacterEncoding("GBK");
        // ��ȡ�������
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String user_type = req.getParameter("user_type");
        // ��װUser����
        User loginUser = new User();
        loginUser.setUsername(username);
        loginUser.setPassword(password);
        loginUser.setUser_type(user_type);

        // ����UserDao��login
        User user = new UserDao().login(loginUser);

        // �ж�User
        if (user == null){ // ��¼ʧ��
            req.getRequestDispatcher("failServlet").forward(req, resp);
        } else{ //��¼�ɹ�
            // �洢����
            HttpSession session = req.getSession();
            session.setAttribute("user", user);

            // ��ת
            resp.sendRedirect(req.getContextPath()+"/successServlet");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }
}
