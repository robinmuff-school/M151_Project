package M151.M151.model;

import javax.persistence.*;

@Entity
public class Invites {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "invites_sequence")
    @SequenceGenerator(allocationSize = 1, name = "invites_sequence")
    @Column(name = "id", nullable = false, updatable = false)
    private long id;

    @Column(nullable = false, unique = true)
    private MeetingStatus status;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private User inviter;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private User invited;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Meeting meeting;
}
