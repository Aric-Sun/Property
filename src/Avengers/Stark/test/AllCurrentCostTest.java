package Avengers.Stark.test;

import Avengers.Stark.dao.CurrentCostDao;
import Avengers.Stark.dto.Current_Cost;
import Avengers.Stark.dto.User;

import java.util.List;

/**
 * @author AricSun
 * @date 2020.06.13 18:33
 */
public class AllCurrentCostTest {
    public static void main(String[] args) {
        List<Current_Cost> costList = new CurrentCostDao().queryAllCost();
        System.out.println(costList);
    }
}
