package M151.M151.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.annotations.ColumnTransformer;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "meeting_user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_sequence")
    @SequenceGenerator(allocationSize = 1, name = "user_sequence")
    @Column(name = "id", nullable = false, updatable = false)
    private long id;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String firstname;

    @Column(nullable = false)
    private String lastname;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Column(nullable = false)
    @ColumnTransformer(write = "crypt(!, gen_salt('bf', 8))")
    private String password;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private UserGroup userGroup;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Invites> creator;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Invites> invited;

    public String getUsername() {
        return username;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public String getPassword() {
        return password;
    }

    public UserGroup getUserGroup() {
        return userGroup;
    }

    public User(final String username, final String firstname, final String lastname, final String password, final UserGroup userGroup) {
        this.username = username;
        this.firstname = firstname;
        this.lastname = lastname;
        this.password = password;
        this.userGroup = userGroup;
    }

    protected User() {
    }
}
