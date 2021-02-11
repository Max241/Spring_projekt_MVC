package pl.dmcs.brozga.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.dmcs.brozga.model.AppUserRole;

import javax.transaction.Transactional;
import java.util.Optional;

@Transactional
@Repository
public interface AppUserRoleRepo extends JpaRepository<AppUserRole, Long> {
    Optional<AppUserRole> findByRole(String role);

    AppUserRole findById(Long id);
}
