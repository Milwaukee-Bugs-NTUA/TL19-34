package gr.ntua.ece.softeng19b.data.model;

public class User {

    private String username;
    private String password;
    private String email;
    private int admin;
    private int requestsPerDayQuota;
    private int usedPerDayQuota;
    private String salt;

    public User() {
        //Keep this for json encoding/decoding
    }

    public User(String username, String email, int admin, int requestsPerDayQuota) {
        this.username = username;
        this.email = email;
        this.admin = admin;
        this.requestsPerDayQuota = requestsPerDayQuota;
        this.usedPerDayQuota = 0;
    }

    public String getUserName() {
        return username;
    }

    public void setUserName(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getAdmin() {
        return admin;
    }

    public void setAdmin(int admin) {
        this.admin = admin;
    }

    public void setRequestsPerDayQuota(int requestsPerDayQuota) {
        this.requestsPerDayQuota = requestsPerDayQuota;
    }

    public int getRequestsPerDayQuota() {
        return requestsPerDayQuota;
    }

    public void setUsedPerDayQuota(int usedPerDayQuota) {
        this.usedPerDayQuota = usedPerDayQuota;
    }

    public int getUsedPerDayQuota() {
        return usedPerDayQuota;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

}
