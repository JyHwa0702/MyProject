package project.first.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Timestamp;

@Getter @Setter
@Entity
@NoArgsConstructor
@Table(name = "Member")
public class User {

    @Id @GeneratedValue
    private Long id;
    private String username;
    private String password;
    private String email;
    private String role; // ROLE_USER , ROLE_ADMIN

    private String provider;
    private String providerId;

    private Timestamp createDate;

    @Builder
    public User(String username, String password, String email, String role,
                String provider, String providerId, Timestamp createDate){
        this.username = username;
        this.password = password;
        this.email = email;
        this.role = role;
        this.provider = provider;
        this.providerId = providerId;
        this.createDate = createDate;
    }
}
