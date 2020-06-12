package Avengers.Stark.test;

import Avengers.Stark.dao.UserDao;
import Avengers.Stark.dto.User;

/**
 * @author AricSun
 * @date 2020.06.11 18:44
 */
public class UserDaoTest {
    public static void main(String[] args) {
        User loginUser = new User();
        loginUser.setUsername("superbaby");
        loginUser.setPassword("123");
        User user = new UserDao().login(loginUser);
        System.out.println(user);
    }
}
