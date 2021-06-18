package M151.M151.model;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
public class Meeting implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "meeting_sequence")
    @SequenceGenerator(allocationSize = 1, name = "meeting_sequence")
    @Column(name = "id", nullable = false, updatable = false)
    private long id;

    @Column(nullable = false, unique = false)
    private LocalDateTime startTime;

    @Column(nullable = false, unique = false)
    private LocalDateTime endTime;

    @Column(nullable = false, unique = false)
    private String name;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private MeetingRoom meetingRoom;

    protected Meeting() {
    }

    public Meeting(LocalDateTime startTime, LocalDateTime endTime, String name, MeetingRoom meetingRoom) {
        this.startTime = startTime;
        this.endTime = endTime;
        this.name = name;
        this.meetingRoom = meetingRoom;
    }

    public long getId() { return id; }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public MeetingRoom getMeetingRoom() {
        return meetingRoom;
    }

    public void setMeetingRoom(MeetingRoom meetingRoom) {
        this.meetingRoom = meetingRoom;
    }
}
