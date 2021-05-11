package M151.M151.model;

import javax.persistence.*;
import java.util.Date;

public class Meeting {
    @Id
    @Column(name = "id", nullable = false, updatable = false)
    private long id;

    @Column(nullable = false, unique = true)
    private String title;

    @Column(nullable = false, unique = true)
    private Date startTime;

    @Column(nullable = false, unique = true)
    private Date endTime;

    protected Meeting() {
    }

    public Meeting(final String title, final Date startTime, final Date endTime) {
        this.title = title;
        this.startTime = startTime;
        this.endTime = endTime;
    }
}
