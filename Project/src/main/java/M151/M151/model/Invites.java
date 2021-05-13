package M151.M151.model;

import javax.persistence.*;

@Entity
public class Invites {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_sequence")
    @SequenceGenerator(allocationSize = 1, name = "meeting_sequence")
    @Column(name = "id", nullable = false, updatable = false)
    private long id;

    @Column(nullable = false, unique = true)
    private MeetingStatus status;
}
