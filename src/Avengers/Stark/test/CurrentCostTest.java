package Avengers.Stark.test;

import Avengers.Stark.dao.CurrentCostDao;
import Avengers.Stark.dto.Current_Cost;
import Avengers.Stark.dto.User;

/**
 * @author AricSun
 * @date 2020.06.13 16:49
 */
public class CurrentCostTest {
    public static void main(String[] args) {
        User user = new User();
        user.setUsername("001");
        Current_Cost current_cost = new CurrentCostDao().QueryCurrentCost(user);
        System.out.println(current_cost);
    }
}
