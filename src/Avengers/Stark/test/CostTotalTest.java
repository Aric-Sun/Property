package Avengers.Stark.test;

import Avengers.Stark.dao.CostTotalDao;
import Avengers.Stark.dto.Cost_total;

import java.util.List;

/**
 * @author AricSun
 * @date 2020.06.14 3:51
 */
public class CostTotalTest {
    public static void main(String[] args) {
        List<Cost_total> costTotalList = new CostTotalDao().querySumFeeNeed2PayByMonth("2020-4");
        for (Cost_total cost_total : costTotalList) {
            System.out.println(cost_total);
        }
    }
}
