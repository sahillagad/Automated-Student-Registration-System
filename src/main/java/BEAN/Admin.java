package BEAN;

public class Admin {

    private int adminId;
    private String name;
    private String email;
    private  String mobile;
    private  String password;
    private  String username;

    public Admin(int adminId, String name, String email, String mobile, String password, String username) {
        this.adminId = adminId;
        this.name = name;
        this.email = email;
        this.mobile = mobile;
        this.password = password;
        this.username = username;
    }

    public Admin(String name, String email, String mobile, String password, String username) {
        this.name = name;
        this.email = email;
        this.mobile = mobile;
        this.password = password;
        this.username = username;
    }

    public Admin() {
    }

    public int getAdminId() {
        return adminId;
    }

    public void setAdminId(int adminId) {
        this.adminId = adminId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String toString() {
        return "Admin{" +
                "adminId=" + adminId +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", mobile='" + mobile + '\'' +
                ", password='" + password + '\'' +
                ", username='" + username + '\'' +
                '}';
    }
}
