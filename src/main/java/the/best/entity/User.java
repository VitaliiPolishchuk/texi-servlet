package the.best.entity;


import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Getter
@Setter
public class User {
    private String firstName;
    private String lastName;
    private String email;
    private String emailPassword;
    private int points;

    public User() {
    }

    public User(String firstName, String lastName, String email, String emailPassword, int points) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.emailPassword = emailPassword;
        this.points = points;
    }

    @Override
    public String toString() {
        return "User{" +
                "email='" + email + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", emailPassword='" + emailPassword + '\'' +
                ", points='" + points + '\'' +
                '}';
    }
}
