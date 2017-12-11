package rest.carRepair.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class User {

    @Id
    @Column(name = "user_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long userId;

    @Column(nullable = false, unique = true, length = 60)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private boolean userType;    // value true means ADMIN - value false means USER

    @OneToOne(optional = false, mappedBy = "user", targetEntity = Member.class, cascade = CascadeType.ALL)
    private Member member;

    public User(){}

    public User(String email, String password, boolean userType) {
        this.email = email;
        this.password = password;
        this.userType = userType;
    }

    public long getUserId() {
        return userId;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public boolean isUserType() {
        return userType;
    }

    public Member getMember() {
        return member;
    }
}
