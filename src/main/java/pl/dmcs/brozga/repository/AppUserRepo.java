package pl.dmcs.brozga.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.dmcs.brozga.model.AppUser;

import javax.transaction.Transactional;
import java.util.List;


@Transactional
@Repository
public interface AppUserRepo extends JpaRepository<AppUser, Long> {

    List<AppUser> findBySurname(String surname);
    AppUser findById(long id);

    AppUser findByLogin(String login);




}
