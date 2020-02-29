package gr.ntua.ece.softeng19b.data.model;

public class User {

    private String username;
    private String password;
    private String email;
    private int admin;
    private int requestsPerDayQuota; //negative values indicate no quota

    public User() {
        //Keep this for json encoding/decoding
    }

    public User(String username, String email, int admin, int requestsPerDayQuota) {
        this.username = username;
        this.email = email;
        this.admin = admin;
        this.requestsPerDayQuota = requestsPerDayQuota;
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

}
