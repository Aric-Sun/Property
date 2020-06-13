package Avengers.Stark.dto;

/**
 * 当月费用情况实体类，不包括时间
 * 复用：加入时间参数
 * @author AricSun
 * @date 2020.06.13 16:31
 */
public class Current_Cost {
    private String username;
    private String time;  // 抄表时间
    private String water;  // 水费
    private String electricity;  // 电费
    private String gas;  // 煤气费
    private String heating;  // 暖气费
    private String managementFee;  // 物业管理费
    private String housePayment;  // 房租/房贷

    @Override
    public String toString() {
        return "Current_Cost{" +
                "username='" + username + '\'' +
                ", time='" + time + '\'' +
                ", water='" + water + '\'' +
                ", electricity='" + electricity + '\'' +
                ", gas='" + gas + '\'' +
                ", heating='" + heating + '\'' +
                ", managementFee='" + managementFee + '\'' +
                ", housePayment='" + housePayment + '\'' +
                '}';
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getWater() {
        return water;
    }

    public void setWater(String water) {
        this.water = water;
    }

    public String getElectricity() {
        return electricity;
    }

    public void setElectricity(String electricity) {
        this.electricity = electricity;
    }

    public String getGas() {
        return gas;
    }

    public void setGas(String gas) {
        this.gas = gas;
    }

    public String getHeating() {
        return heating;
    }

    public void setHeating(String heating) {
        this.heating = heating;
    }

    public String getManagementFee() {
        return managementFee;
    }

    public void setManagementFee(String managementFee) {
        this.managementFee = managementFee;
    }

    public String getHousePayment() {
        return housePayment;
    }

    public void setHousePayment(String housePayment) {
        this.housePayment = housePayment;
    }
}
