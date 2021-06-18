package M151.M151.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class MeetingRoom implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "meetingroom_sequence")
    @SequenceGenerator(allocationSize = 1, name = "meetingroom_sequence")
    @Column(name = "id", nullable = false, updatable = false)
    private long id;

    @Column(nullable = false, unique = true)
    private String name;

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
