package au.com.keithdavidson.productsellerapi.model;

import lombok.Data;
//import jakarta.persistence.*;
import javax.persistence.*;
import java.time.LocalDateTime;


@Data
@Entity
@Table(name="users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Unique login user name
    @Column(name= "username", unique = true, nullable = false, length = 100)
    private String userName;

    // login password
    @Column(name = "password", nullable = false)
    private String password;

    // Preferred display name?
    @Column(name = "name", nullable = false)
    private String name;

    // role = admin or user
    @Enumerated(EnumType.STRING)
    @Column(name = "role", nullable = false)
    private Role role;

    // when created
    @Column(name = "create_time", nullable = false)
    private LocalDateTime createTime;

    @Transient
    private String token;
}
