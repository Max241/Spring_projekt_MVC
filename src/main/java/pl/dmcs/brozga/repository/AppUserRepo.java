package pl.dmcs.brozga.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.dmcs.brozga.model.AppUser;
import pl.dmcs.brozga.model.AppUserRole;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;


@Transactional
@Repository
public interface AppUserRepo extends JpaRepository<AppUser, Long> {

    List<AppUser> findBySurname(String surname);

    boolean existsByEmail(String email);

    boolean existsByLogin(String login);

    AppUser findById(Long id);

    AppUser findByEmail(String email);

    AppUser findByLogin(String login);

    AppUser findByName(String name);

    Optional<AppUser> findByTokenToken(String token);

    List<AppUser> findAllByAppUserRole(AppUserRole role);

    Page<AppUser> findAllByIdIsNot(Long id, Pageable pageable);
}
