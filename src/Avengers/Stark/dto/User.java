package Avengers.Stark.dto;

/**
 * �û�ʵ���� JavaBean, ��������Ա
 * @author AricSun
 * @date 2020.06.11 16:32
 */
public class User {
    private String username;
    private String password;
    private String user_type; // user or manager
    private String IDNumber; // ���֤��
    private String buildNo;  // ¥��
    private String roomNo;  // ����
    private String name;  // manager
    private String jobUnit;  // ������λ
    private String phone;  // manager
    private String floorage;  // �������

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", user_type='" + user_type + '\'' +
                ", IDNumber='" + IDNumber + '\'' +
                ", buildNo='" + buildNo + '\'' +
                ", roomNo='" + roomNo + '\'' +
                ", name='" + name + '\'' +
                ", jobUnit='" + jobUnit + '\'' +
                ", phone='" + phone + '\'' +
                ", floorage='" + floorage + '\'' +
                '}';
    }

    public String getIDNumber() {
        return IDNumber;
    }

    public void setIDNumber(String IDNumber) {
        this.IDNumber = IDNumber;
    }

    public String getBuildNo() {
        return buildNo;
    }

    public void setBuildNo(String buildNo) {
        this.buildNo = buildNo;
    }

    public String getRoomNo() {
        return roomNo;
    }

    public void setRoomNo(String roomNo) {
        this.roomNo = roomNo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getJobUnit() {
        return jobUnit;
    }

    public void setJobUnit(String jobUnit) {
        this.jobUnit = jobUnit;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getFloorage() {
        return floorage;
    }

    public void setFloorage(String floorage) {
        this.floorage = floorage;
    }

    public String getUser_type() {
        return user_type;
    }

    public void setUser_type(String user_type) {
        this.user_type = user_type;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
