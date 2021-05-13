package M151.M151.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.annotations.ColumnTransformer;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
public class Meeting {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_sequence")
    @SequenceGenerator(allocationSize = 1, name = "meeting_sequence")
    @Column(name = "id", nullable = false, updatable = false)
    private long id;

    @Column(nullable = false, unique = true)
    private LocalDateTime startTime;

    @Column(nullable = false)
    private LocalDateTime endTime;

    @Column(nullable = false)
    private String name;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Invites> invites;

    protected Meeting() {
    }

    public Meeting(LocalDateTime startTime, LocalDateTime endTime, String name, Set<Invites> invites) {
        this.startTime = startTime;
        this.endTime = endTime;
        this.name = name;
        this.invites = invites;
    }
}
