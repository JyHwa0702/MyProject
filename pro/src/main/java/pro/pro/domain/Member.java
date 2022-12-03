package pro.pro.domain;

import lombok.*;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

@Entity
@Builder
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@DynamicUpdate
public class Member {

    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "memeber_id",length = 20,nullable = false)
    private String id;

    @Column(name = "memeber_password", nullable = false)
    private String password;

    @Column(name = "member_name",nullable = false)
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(name = "member_role", nullable = false)
    private MemberRole role;
}
