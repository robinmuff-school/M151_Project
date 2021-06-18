package M151.M151.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class Invites implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "invites_sequence")
    @SequenceGenerator(allocationSize = 1, name = "invites_sequence")
    @Column(name = "id", nullable = false, updatable = false)
    private long id;

    @Column(nullable = false, unique = true)
    private MeetingStatus status;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private User inviter;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private User invited;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Meeting meeting;

    protected Invites() {
    }

    public Invites(MeetingStatus meetingStatus, User inviter, User invited, Meeting meeting) {
        this.status = meetingStatus;
        this.inviter = inviter;
        this.invited = invited;
        this.meeting = meeting;
    }

    public long getInviteId() {
        return id;
    }

    public MeetingStatus getStatus() {
        return status;
    }

    public void setStatus(MeetingStatus status) {
        this.status = status;
    }

    public User getInviter() {
        return inviter;
    }

    public void setInviter(User inviter) {
        this.inviter = inviter;
    }

    public User getInvited() {
        return invited;
    }

    public void setInvited(User invited) {
        this.invited = invited;
    }

    public Meeting getMeeting() {
        return meeting;
    }

    public void setMeeting(Meeting meeting) {
        this.meeting = meeting;
    }
}
