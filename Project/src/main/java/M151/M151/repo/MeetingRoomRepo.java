package M151.M151.repo;

import M151.M151.model.MeetingRoom;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MeetingRoomRepo extends CrudRepository<MeetingRoom, Long> {
    MeetingRoom findById(long id);

    List<MeetingRoom> findByName(String name);
}
