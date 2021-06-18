package M151.M151.repo;

import M151.M151.model.Meeting;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MeetingRepo extends CrudRepository<Meeting, Long> {
    Meeting findById(long id);
}
