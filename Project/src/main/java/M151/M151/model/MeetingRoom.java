package M151.M151.model;

import javax.persistence.*;
import java.util.Set;

@Entity
public class MeetingRoom {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_sequence")
    @SequenceGenerator(allocationSize = 1, name = "meetingroom_sequence")
    @Column(name = "id", nullable = false, updatable = false)
    private long id;

    @Column(nullable = false, unique = true)
    private String name;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Meeting> meetings;

    protected MeetingRoom() {}

    public MeetingRoom(final String name) {
        this.name = name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
