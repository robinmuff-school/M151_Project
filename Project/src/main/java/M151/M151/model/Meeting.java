package M151.M151.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.annotations.ColumnTransformer;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
public class Meeting {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "meeting_sequence")
    @SequenceGenerator(allocationSize = 1, name = "meeting_sequence")
    @Column(name = "id", nullable = false, updatable = false)
    private long id;

    @Column(nullable = false, unique = true)
    private LocalDateTime startTime;

    @Column(nullable = false)
    private LocalDateTime endTime;

    @Column(nullable = false)
    private String name;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private MeetingRoom meetingRoom;

    protected Meeting() {
    }

    public Meeting(LocalDateTime startTime, LocalDateTime endTime, String name) {
        this.startTime = startTime;
        this.endTime = endTime;
        this.name = name;
    }
}
