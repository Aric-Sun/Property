package Avengers.Stark.dto;

/**
 * �¶���Ҫ�ɷѵķ����ܺͣ����û��֣�ʵ����
 * @author AricSun
 * @date 2020.06.14 3:32
 */
public class Cost_total {
    private String username;  // �û���
    private String month;  //  �·ݣ�����2020-04
    private double total_cost;  // �ܶ�

    @Override
    public String toString() {
        return "Cost_total{" +
                "username='" + username + '\'' +
                ", month='" + month + '\'' +
                ", total_cost=" + total_cost +
                '}';
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public double getTotal_cost() {
        return total_cost;
    }

    public void setTotal_cost(double total_cost) {
        this.total_cost = total_cost;
    }
}
