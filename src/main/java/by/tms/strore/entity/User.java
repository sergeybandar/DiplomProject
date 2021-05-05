package by.tms.strore.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Pattern;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Pattern(regexp = "[A-Z][a-z]{2,12}")
    private String userName;
    @Pattern(regexp = "[A-Z][a-z]{2,12}")
    private String login;
    @Pattern(regexp = "[A-Z][a-z]{2,12}")
    private String password;
    private Role role= Role.USER;

    public User(long id, @Pattern(regexp = "[A-Z][a-z]{2,12}") String userName, @Pattern(regexp = "[A-Z][a-z]{2,12}") String login, @Pattern(regexp = "[A-Z][a-z]{2,12}") String password) {
        this.id = id;
        this.userName = userName;
        this.login = login;
        this.password = password;
    }
}
