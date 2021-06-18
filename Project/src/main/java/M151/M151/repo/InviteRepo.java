package M151.M151.repo;

import M151.M151.model.Invites;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InviteRepo extends CrudRepository<Invites, Long> {

}
