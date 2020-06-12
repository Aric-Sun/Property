package Avengers.Stark.test;

import Avengers.Stark.dao.UserDao;
import Avengers.Stark.dto.User;

import java.util.List;

/**
 * @author AricSun
 * @date 2020.06.13 3:37
 */
public class AllUserInfoTest {
    public static void main(String[] args) {
        List<User> users = new UserDao().selectAllUserInfo();
        for (User user : users) {
            System.out.println(user);
        }
    }
}
