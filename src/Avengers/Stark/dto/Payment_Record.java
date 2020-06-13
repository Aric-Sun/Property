package Avengers.Stark.dto;

/**
 * �ɷ����ʵ����
 * @author AricSun
 * @date 2020.06.13 17:32
 */
public class Payment_Record {
    private String username;
    private String time;  // �ɷ�ʱ��
    private int water;  // 1 or 0����ʾ�ѽ�/δ��
    private int electricity;
    private int gas;
    private int heating;
    private int managementFee;
    private int housePayment;

    @Override
    public String toString() {
        return "Payment_Record{" +
                "username='" + username + '\'' +
                ", time='" + time + '\'' +
                ", water=" + water +
                ", electricity=" + electricity +
                ", gas=" + gas +
                ", heating=" + heating +
                ", managementFee=" + managementFee +
                ", housePayment=" + housePayment +
                '}';
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getWater() {
        return water;
    }

    public void setWater(int water) {
        this.water = water;
    }

    public int getElectricity() {
        return electricity;
    }

    public void setElectricity(int electricity) {
        this.electricity = electricity;
    }

    public int getGas() {
        return gas;
    }

    public void setGas(int gas) {
        this.gas = gas;
    }

    public int getHeating() {
        return heating;
    }

    public void setHeating(int heating) {
        this.heating = heating;
    }

    public int getManagementFee() {
        return managementFee;
    }

    public void setManagementFee(int managementFee) {
        this.managementFee = managementFee;
    }

    public int getHousePayment() {
        return housePayment;
    }

    public void setHousePayment(int housePayment) {
        this.housePayment = housePayment;
    }
}
