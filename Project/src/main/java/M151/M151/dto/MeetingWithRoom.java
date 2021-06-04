package M151.M151.dto;

import M151.M151.model.MeetingRoom;
import M151.M151.model.Meeting;

public class MeetingWithRoom {
    private final Meeting meeting;
    private final long meetingRoomId;

    public MeetingWithRoom(final Meeting meeting, final long meetingRoomId) {
        this.meeting = meeting;
        this.meetingRoomId = meetingRoomId;
    }

    public Meeting getMeeting() {
        return meeting;
    }

    public long getMeetingRoom() {
        return meetingRoomId;
    }
}
