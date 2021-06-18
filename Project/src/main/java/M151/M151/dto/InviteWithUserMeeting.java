package M151.M151.dto;

public class InviteWithUserMeeting {
    private final long userId;
    private final long meetingId;

    public InviteWithUserMeeting(final long userId, final long meetingId) {
        this.userId = userId;
        this.meetingId = meetingId;
    }

    public long getUser() {
        return userId;
    }

    public long getMeeting() {
        return meetingId;
    }
}
