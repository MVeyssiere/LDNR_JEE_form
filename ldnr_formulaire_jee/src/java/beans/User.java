package beans;

/**
 * @author Marine V
 */
public class User {

    private Integer id_user;
    private String name;
    private String email;
    private String password;

    public User() {
    }

    @Override
    public String toString() {
        return "User{" + "id_user=" + id_user + ", email=" + email + ", password=" + password + ", name=" + name + '}';
    }

    public User(String name, String password) {
        this.name = name;
        this.password = password;
    }

    public User(Integer id_user, String password) {
        this.id_user = id_user;
        this.password = password;
    }

    public User(Integer id_user, String name, String email, String password) {
        this.id_user = id_user;
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public Integer getId_user() {
        return id_user;
    }

    public void setId_user(Integer id_user) {
        this.id_user = id_user;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
