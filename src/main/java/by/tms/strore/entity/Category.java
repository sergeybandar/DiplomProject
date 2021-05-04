package by.tms.strore.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "categories")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotBlank
    @NotNull
    @Pattern(regexp = "[A-Z][a-z]+")
    String name;
    String url;

    public Category(@NotBlank @NotNull @Pattern(regexp = "[A-Z][a-z]+") String name, String url) {
        this.name = name;
        this.url = url;
    }
}
