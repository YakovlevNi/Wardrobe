package wardrobe.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor

public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false)
    private int id;

    @Column(columnDefinition = "VARCHAR(255)", nullable = false)
    private String name;

    @Column(nullable = false)
    private String mail;

    @Column(nullable = false)
    private String login;
    @Column(nullable = false)
    private int phoneNumber;
    @Column
    private String sex;

    @Column
    private String password;


    public User(String login, String password) {
        this.login = login;
        this.password = password;
    }
}
