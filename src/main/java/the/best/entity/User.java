package the.best.entity;


import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Getter
@Setter
public class User {
    private String email;
    private String firstName;
    private String lastName;
    private String emailPassword;

    @Override
    public String toString() {
        return "User{" +
                "email='" + email + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", emailPassword='" + emailPassword + '\'' +
                '}';
    }
}
