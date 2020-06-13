package Avengers.Stark.dto;

/**
 * ���·������ʵ���࣬������ʱ��
 * ���ã�����ʱ�����
 * @author AricSun
 * @date 2020.06.13 16:31
 */
public class Current_Cost {
    private String username;
    private String time;  // ����ʱ��
    private String water;  // ˮ��
    private String electricity;  // ���
    private String gas;  // ú����
    private String heating;  // ů����
    private String managementFee;  // ��ҵ�����
    private String housePayment;  // ����/����

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
