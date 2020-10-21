package org.brv.brv_logistics.models;

public class Users {
    private String phone;
    private String password;
    private String userName;

    public Users() {
    }

    public Users(String phone, String password, String userName) {
        this.phone = phone;
        this.password = password;
        this.userName = userName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
