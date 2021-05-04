package by.tms.strore.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Pattern;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthUser {
    @Pattern(regexp = "[A-Z][a-z]{2,12}")
    private String login;
    @Pattern(regexp = "[A-Z][a-z]{2,12}")
    private String password;
}
